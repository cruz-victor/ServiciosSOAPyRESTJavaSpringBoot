package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bo.gob.sin.sre.fac.dto.ObtenerListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaListadoSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudListadoSistemasDto;
import bo.gob.sin.sre.fac.service.IRecuperarListaSolicitudesCertificacionEnProcesoService;

@CrossOrigin(maxAge = 3600)
@RestController
// @CacheConfig(cacheNames = { "books" })
@RequestMapping(value = "/certificacion1")
public class ListadoProveedoresController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	IRecuperarListaSolicitudesCertificacionEnProcesoService iRecuperarListaSolicitudesCertificacionEnProcesoService;

	
	/**
	 * Obtiene listado Sistema sCertificados general.
	 * 
	 * @author: Sergio Mallea
	 * @Fecha: 13/12/2018
	 * @param 
	 * @return Devuelve objeto Dto de respuesta
	 */
	@RequestMapping(value = "/listadoSistemasCertificados", method = RequestMethod.GET)
	public ResponseEntity<ObtenerListaSistemasCertificacionDto> listadoSistemasCertificados() {

		ObtenerListaSistemasCertificacionDto vRespuestaDto = new ObtenerListaSistemasCertificacionDto();
			
		vRespuestaDto = iRecuperarListaSolicitudesCertificacionEnProcesoService.recuperaListaSolicitudSistemasCertificacion();
					
			ResponseEntity<ObtenerListaSistemasCertificacionDto> vResponseEntity = new ResponseEntity<ObtenerListaSistemasCertificacionDto>(vRespuestaDto, HttpStatus.OK);

			return vResponseEntity;
		}
	
	/**
	 * Obtiene listado Sistemas Certificados general Paginado.
	 * 
	 * @author: Freddy Yuca
	 * @Fecha: 13/12/2018
	 * @param 
	 * @return Devuelve objeto Dto de respuesta
	 */
	@PostMapping(value = "/obtenerSistemasCertificacionPaginado")
	public ResponseEntity<RespuestaListadoSistemasDto> obtenerSistemasCertificacionPaginado(@RequestBody SolicitudListadoSistemasDto pSolicitud) {
		RespuestaListadoSistemasDto vRespuestaDto = iRecuperarListaSolicitudesCertificacionEnProcesoService.obtenerSistemasCertificacionPaginado(pSolicitud);
		ResponseEntity<RespuestaListadoSistemasDto> vResponseEntity = new ResponseEntity<RespuestaListadoSistemasDto>(vRespuestaDto, HttpStatus.OK);
		return vResponseEntity;
		}
	
}
