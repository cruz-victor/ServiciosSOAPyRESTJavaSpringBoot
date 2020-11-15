package bo.gob.sin.sre.fac.cfec.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bo.gob.sin.sre.fac.cfec.dto.LogBDErroresDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaJsonDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudFacturaParcialDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.service.reporte.IReporteService;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/reporte")
public class ReporteController {

	@Autowired
	IUtilitarios iutil;

	@Autowired
	IValidarRecepcion iValRec;

	@Autowired
	IReporteService iRepService;

	@PostMapping(value = "/obtenerReporte", headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<RespuestaJsonDto> obtenerReporte(
			@RequestBody SolicitudFacturaParcialDto pSolicitud) {
		try {
			RespuestaJsonDto vResultado = iRepService.obtenerReporte(pSolicitud);
			return new ResponseEntity<>(vResultado, HttpStatus.OK);
		} catch (Exception e) {
			LogBDErroresDto vErrorDto = new LogBDErroresDto(0, Parametros.USUARIO_GENERICO,
					Parametros.SUBSISTEMA_RECAUDACIONES, "obtenerReporte", new Date(), "reporte",
					e.getLocalizedMessage(), e.getMessage(), Parametros.ESTADO_ACTIVO, pSolicitud.getpNitEmisor(), 0L, 0L,
					0);
			iValRec.registrarLogExcepcionBDErrores(vErrorDto);

			return new ResponseEntity<>(new RespuestaJsonDto(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}