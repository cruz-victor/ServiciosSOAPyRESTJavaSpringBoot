package bo.gob.sin.sre.fac.fee.fhid.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import bo.gob.sin.sre.fac.cfec.dto.interfaces.IRespuesta;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.ISolicitudAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.ISolicitudRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.ISolicitudRecepcionAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.ISolicitudValidacionAnulacion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.ISolicitudValidacionRecepcion;
import bo.gob.sin.sre.fac.cfec.dto.interfaces.ISolicitudValidacionRecepcionPorCuf;

@WebService(targetNamespace = "https://siat.impuestos.gob.bo/")
public interface IServicioFacturaElectronicaHidrocarburo {

	@WebMethod(operationName = "verificarComunicacion")
	public int verificarConexion();

	@WebMethod(operationName = "recepcionFacturaElectronicaHidrocarburo")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta recepcion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcion") ISolicitudRecepcion solicitud);

	@WebMethod(operationName = "recepcionPaqueteFacturaElectronicaHidrocarburo")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta recepcionPaquete(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcion") ISolicitudRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionFacturaElectronicaHidrocarburo")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcion") ISolicitudValidacionRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionPaqueteFacturaElectronicaHidrocarburo")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcionPaquete(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcion") ISolicitudValidacionRecepcion solicitud);

	@WebMethod(operationName = "anulacionFacturaElectronicaHidrocarburo")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta anulacion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioAnulacion") ISolicitudAnulacion solicitud);

	@WebMethod(operationName = "validacionAnulacionFacturaElectronicaHidrocarburo")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionAnulacion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionAnulacion") ISolicitudValidacionAnulacion solicitud);

	@WebMethod(operationName = "recepcionMasivaFacturaElectronicaHidrocarburo")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta recepcionMasiva(@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcion") ISolicitudRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionMasivaFacturaElectronicaHidrocarburo")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcionMasiva(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcion") ISolicitudValidacionRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionPorCufFacturaElectronicaHidrocarburo")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcionPorCuf(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcionPorCuf") ISolicitudValidacionRecepcionPorCuf solicitud);

	@WebMethod(operationName = "obtenerRecepcionAnulacionFacturaElectronicaHidrocarburo")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta obtenerRecepcionAnulacion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcionAnulacion") ISolicitudRecepcionAnulacion solicitud);
}