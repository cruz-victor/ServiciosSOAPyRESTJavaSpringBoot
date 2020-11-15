package bo.gob.sin.sre.fac.cfec.service.reporte;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.fac.Reports;
import bo.gob.sin.fac.reportes.IPametros;
import bo.gob.sin.sre.fac.cfec.domain.IReporteDomain;
import bo.gob.sin.sre.fac.cfec.dto.LogBDErroresDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaJsonDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudFacturaParcialDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudRevertirCadenaDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.service.IServiciosReversionClientRest;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;

@Service
@Transactional
public class ReporteService implements IReporteService {

	@Autowired
	IServiciosReversionClientRest iServiciosReversionClientRest;

	@Autowired
	IUtilitarios iutil;

	@Autowired
	IValidarRecepcion iValRec;

	@Autowired
	IReporteDomain iReporteDomain;

	public RespuestaJsonDto obtenerReporte(SolicitudFacturaParcialDto pSolicitud) {
		RespuestaJsonDto vResultado = new RespuestaJsonDto();
		try {
			int vModalidad = 0;
			// REVERSION DE CUF
			if (pSolicitud.getpNitEmisor() > 0 && !pSolicitud.getpCuf().isEmpty()) {
				RespuestaDatosCufDto vRespuestaCuf = iServiciosReversionClientRest
						.reversionCuf(new SolicitudRevertirCadenaDto(pSolicitud.getpCuf()));
				if (vRespuestaCuf.getNitEmisor() != null && vRespuestaCuf.getNitEmisor() > 0
						&& (pSolicitud.getpNitEmisor() == vRespuestaCuf.getNitEmisor())) {
					pSolicitud.setpTipoDocumentoFiscal(vRespuestaCuf.getDocumentoFiscal());
					pSolicitud.setpTipoDocumentoSector(vRespuestaCuf.getDocumentoSector());
					vModalidad = vRespuestaCuf.getModalidad();
				} else {
					vResultado.setRespuesta("");
					LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
							Parametros.SUBSISTEMA_RECAUDACIONES, "obtenerReporte", new Date(), "reporte",
							"valores reversion cuf",
							"reversionCuf nit-" + vRespuestaCuf.getNitEmisor() + "- parametro nit"
									+ pSolicitud.getpNitEmisor(),
							Parametros.ESTADO_ACTIVO, pSolicitud.getpNitEmisor(), 0L, 0L,
							pSolicitud.getpTipoDocumentoSector());
					iValRec.registrarLogExcepcionBDErrores(vErrorDto);
					return vResultado;
				}
			} else {
				vResultado.setRespuesta("");
				LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
						Parametros.SUBSISTEMA_RECAUDACIONES, "obtenerReporte", new Date(), "reporte",
						"parametros invalidos",
						"parametro nit:" + pSolicitud.getpNitEmisor() + " - parametro cuf:" + pSolicitud.getpCuf(),
						Parametros.ESTADO_ACTIVO, pSolicitud.getpNitEmisor(), 0L, 0L,
						pSolicitud.getpTipoDocumentoSector());
				iValRec.registrarLogExcepcionBDErrores(vErrorDto);
				return vResultado;
			}

			if (!pSolicitud.getpCuf().isEmpty() && pSolicitud.getpNitEmisor() > 0
					&& pSolicitud.getpNumeroFactura() > 0) {

				// RECUPERACION DE ARCHIVO XML
				ObjectMapper mapper = new ObjectMapper();
				JSONObject jsonRecepcion = new JSONObject(mapper.writeValueAsString(pSolicitud));
				JSONObject jsonFinal = new JSONObject();
				jsonFinal.put("jsonObtenerXmlValido", jsonRecepcion);

				// Envio de dto a funcion en Base de datos
				String vRequest = iReporteDomain.obtenerArchivoXmlValido(jsonFinal.toString());

				if (!vRequest.equals("0")) {
					// {"recepcion_correcta_id":17017,"archivo_xml_valido_id":27977,"archivo":"H4sIAAAAAAAAAI1W25aiOBT9o..."}
					JSONObject objeto = new JSONObject(vRequest);
					String vXmlB64 = objeto.getString("archivo");
					String vXml = iutil.decodificarToBase64(vXmlB64);

					Map<String, Object> vParametros = new HashMap<>();
					vParametros.put(IPametros.MODALIDAD, vModalidad);
					byte[] vReportePdf = Reports.generateReportXMLToBytes(pSolicitud.getpTipoDocumentoSector(), vXml,
							vParametros);
					String respuestaBase64 = vReportePdf == null ? "" : Base64.getEncoder().encodeToString(vReportePdf);
					vResultado.setRespuesta(respuestaBase64);
				} else {
					vResultado.setRespuesta("");
				}
			}
		} catch (Exception e) {
			vResultado.setRespuesta("");
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "obtenerReporte", new Date(), "reporte",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, pSolicitud.getpNitEmisor(), 0L, 0L,
					pSolicitud.getpTipoDocumentoSector());
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);
		}

		return vResultado;
	}

}