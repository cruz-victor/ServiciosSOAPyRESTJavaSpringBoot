package bo.gob.sin.sre.fac.cfec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bo.gob.sin.sre.fac.cfec.dto.ValidarCufDto;
import bo.gob.sin.sre.fac.cfec.servicedomain.IOperacionesCufService;
import bo.gob.sin.sre.fac.cfec.servicedomain.validation.CodigosMensajesServiciosSOAPServiceImpl;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/validaciones")
public class RepositorioValidacionesController {
	private static final Logger LOG = LoggerFactory.getLogger(RepositorioValidacionesController.class);

	@Autowired
	IOperacionesCufService iOperacionesCufService;

	@RequestMapping(value = "/verificarConexion", method = RequestMethod.GET, headers = "Accept=application/json")
	public int verificarComunicacion() {
		return CodigosMensajesServiciosSOAPServiceImpl.COMUNICACION_EXITOSA;
	}

	/**
	 * Recuperar Reporte Cabecera y Detalle de Solicitud de certificacion
	 * 
	 * @author: Reynaldo Chambi Rojas
	 * @Fecha: 03/05/2019
	 * @param pSolicitud,objeto de tipo solicitud
	 * @return Devuelve cuf validado.
	 */
	@PostMapping(value = "/validarCuf")
	public Boolean validarCuf(@RequestBody ValidarCufDto pSolicitud) {
		LOG.info("Inicio solicitud:" + pSolicitud);
		Boolean vRespuesta = iOperacionesCufService.verificacionCuf(pSolicitud);
		LOG.info("Finalizacion:" + vRespuesta.toString());
		return vRespuesta;
	}
}