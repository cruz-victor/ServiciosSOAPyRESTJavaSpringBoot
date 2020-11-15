package bo.gob.sin.sre.fac.cfec.servicedomain.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaXmlXsdDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.servicedomain.IMasivos;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;
import bo.gob.sin.sre.fac.cfec.servicedomain.validation.CodigosMensajesServiciosSOAPServiceImpl;

@Service
@Transactional
public class MasivosImpl implements IMasivos {
	
	private static final Logger LOG = LoggerFactory.getLogger(MasivosImpl.class);

	@Autowired
	IValidarRecepcion iValRec;

	public RespuestaListaRegistroRecepcionesSoapDto recepcionarMasivos(XmlRecepcionGenericoDto xmlRecGenDto) {
		List<Integer> vLisCodError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// Validar la hash con el archivo
		vLisCodError.addAll(iValRec.validarHashFactura(xmlRecGenDto.getV_archivo(), xmlRecGenDto.getV_hash_archivo()));

		// Validar la recepcion paquete con el XSD
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarRecepcionMasXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		// Validar que el archivo pueda ser decodificado
		vLisCodError.addAll(iValRec.validarArchivoPaquete(xmlRecGenDto.getV_archivo()));

		// Enviar el JSON de recepcion a BD
		if (vLisCodError.isEmpty())
			vRespuesta = iValRec.enviarJsonRecepcionBDEtapa1Masivo(xmlRecGenDto);

		vRespuesta.getListaCodigosRespuestas().addAll(vLisCodError);

		if (vRespuesta.getListaCodigosRespuestas().isEmpty() && vRespuesta.getCodigoRecepcion() > 0) {
			// REGISTRAR EN EL HILO
			xmlRecGenDto.setV_recepcionPaqueteId(vRespuesta.getCodigoRecepcion());
			iValRec.enviarJsonFacturaMasivoHilo(xmlRecGenDto);
			        
		} else {
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
		}

		return vRespuesta;
	}

	
}