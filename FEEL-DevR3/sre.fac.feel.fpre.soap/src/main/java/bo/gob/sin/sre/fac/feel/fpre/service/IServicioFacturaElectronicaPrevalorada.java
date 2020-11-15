package bo.gob.sin.sre.fac.feel.fpre.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import bo.gob.sin.sre.fac.cfec.dto.fpre.interfaces.ISolicitudAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.fpre.interfaces.ISolicitudRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.fpre.interfaces.ISolicitudRecepcionAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.fpre.interfaces.ISolicitudValidacionAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.fpre.interfaces.ISolicitudValidacionRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.fpre.interfaces.ISolicitudValidacionRecepcionPorCufp;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.IRespuesta;

@WebService(targetNamespace = "https://siat.impuestos.gob.bo/")
public interface IServicioFacturaElectronicaPrevalorada {

	@WebMethod(operationName = "verificarComunicacion")
	public int verificarConexion();

	@WebMethod(operationName = "recepcionFacturaElectronicaPrevalorada")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta recepcion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcion") ISolicitudRecepcion solicitud);

	@WebMethod(operationName = "recepcionPaqueteFacturaElectronicaPrevalorada")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta recepcionPaquete(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcion") ISolicitudRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionFacturaElectronicaPrevalorada")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcion") ISolicitudValidacionRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionPaqueteFacturaElectronicaPrevalorada")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcionPaquete(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcion") ISolicitudValidacionRecepcion solicitud);

	@WebMethod(operationName = "anulacionFacturaElectronicaPrevalorada")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta anulacion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioAnulacion") ISolicitudAnulacion solicitud);

	@WebMethod(operationName = "validacionAnulacionFacturaElectronicaPrevalorada")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionAnulacion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionAnulacion") ISolicitudValidacionAnulacion solicitud);

	@WebMethod(operationName = "recepcionMasivaFacturaElectronicaPrevalorada")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta recepcionMasiva(@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcion") ISolicitudRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionMasivaFacturaElectronicaPrevalorada")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcionMasiva(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcion") ISolicitudValidacionRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionPorCufpFacturaElectronicaPrevalorada")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcionPorCufp(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcionPorCuf") ISolicitudValidacionRecepcionPorCufp solicitud);

	@WebMethod(operationName = "obtenerRecepcionAnulacionFacturaElectronicaPrevalorada")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta obtenerRecepcionAnulacion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcionAnulacion") ISolicitudRecepcionAnulacion solicitud);
}