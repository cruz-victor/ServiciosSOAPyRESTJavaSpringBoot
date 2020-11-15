package bo.gob.sin.sre.fac.cfec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaXmlXsdDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;
import bo.gob.sin.sre.fac.cfec.servicedomain.validation.CodigosMensajesServiciosSOAPServiceImpl;

@Service
@Transactional
public class PortalWebImpl implements IPortalWeb {

	@Autowired
	IUtilitarios iutil;
	@Autowired
	IValidarRecepcion iValRec;

	/**
	 * @autor edwin.coro
	 * @descripción metodo que realiza ciertas validaciones (hash, archivo,
	 *              firma,...), seguidamente envia a funcion en BD, para la
	 *              generacion del codigo de recepcion, validacion de recepcion.
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con resultado de la recepcion/validacion, codigo recepcion,
	 *         codigo estado, lista de errores (si hubiera)
	 * @fecha 12/06/2019
	 */
	@Override
	public RespuestaListaRegistroRecepcionesSoapDto recepcionarValidarPortalWeb(XmlRecepcionGenericoDto xmlRecGenDto) {
		List<Integer> vLisCodError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// Validar la hash con el archivo
		vLisCodError.addAll(iValRec.validarHashFactura(xmlRecGenDto.getV_archivo(), xmlRecGenDto.getV_hash_archivo()));

		// Validar la recepcion con el XSD (computarizada/electronica)
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarRecepcionIndXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		// Validar que el archivo pueda ser decodificado
		List<Integer> vCodError = iValRec.validarArchivoIndividual(xmlRecGenDto.getV_archivo());
		if (vCodError.isEmpty()) {
			// Validacion de CUF
			boolean vResValCuf = iValRec.validarCuf(xmlRecGenDto, xmlRecGenDto.getV_archivo(),
					Parametros.F_TIPO_EMISION_ONLINE);
			if (!vResValCuf) {
				vLisCodError.add(CodigosMensajesServiciosSOAPServiceImpl.CODIGO_UNICO_DE_FACTURA_CUF_INVALIDO);
			}
		} else {
			vLisCodError.addAll(vCodError);
		}

		if (vLisCodError.isEmpty()) {
			// Enviar el JSON de recepcion a BD
			vRespuesta = iValRec.recepcionarValidarPortalWeb(xmlRecGenDto);
		} else {
			vRespuesta.getListaCodigosRespuestas().addAll(vLisCodError);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
		}

		if (!vRespuesta.getListaCodigosRespuestas().isEmpty()) {
			vRespuesta.getListaDescripcionesRespuestas()
					.addAll(iValRec.obtenerDescripcionesCodigosRespuesta(vRespuesta.getListaCodigosRespuestas()));
		}

		return vRespuesta;
	}

}