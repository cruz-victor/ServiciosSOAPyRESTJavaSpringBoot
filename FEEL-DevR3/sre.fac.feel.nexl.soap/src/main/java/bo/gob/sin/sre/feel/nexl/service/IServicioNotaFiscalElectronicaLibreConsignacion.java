package bo.gob.sin.sre.feel.nexl.service;

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
public interface IServicioNotaFiscalElectronicaLibreConsignacion {

	@WebMethod(operationName = "verificarComunicacion")
	public int verificarConexion();

	@WebMethod(operationName = "recepcionNotaFiscalElectronicaLibreConsignacion")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta recepcion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcion") ISolicitudRecepcion solicitud);

	@WebMethod(operationName = "recepcionPaqueteNotaFiscalElectronicaLibreConsignacion")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta recepcionPaquete(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcion") ISolicitudRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionNotaFiscalElectronicaLibreConsignacion")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcion") ISolicitudValidacionRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionPaqueteNotaFiscalElectronicaLibreConsignacion")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcionPaquete(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcion") ISolicitudValidacionRecepcion solicitud);

	@WebMethod(operationName = "anulacionNotaFiscalElectronicaLibreConsignacion")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta anulacion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioAnulacion") ISolicitudAnulacion solicitud);

	@WebMethod(operationName = "validacionAnulacionNotaFiscalElectronicaLibreConsignacion")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionAnulacion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionAnulacion") ISolicitudValidacionAnulacion solicitud);
	
	@WebMethod(operationName = "recepcionMasivaNotaFiscalElectronicaLibreConsignacion")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta recepcionMasiva(@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcion") ISolicitudRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionMasivaNotaFiscalElectronicaLibreConsignacion")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcionMasiva(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcion") ISolicitudValidacionRecepcion solicitud);

	@WebMethod(operationName = "validacionRecepcionPorCufNotaFiscalElectronicaLibreConsignacion")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta validacionRecepcionPorCuf(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioValidacionRecepcionPorCuf") ISolicitudValidacionRecepcionPorCuf solicitud);

	@WebMethod(operationName = "obtenerRecepcionAnulacionNotaFiscalElectronicaLibreConsignacion")
	@WebResult(name = "RespuestaServicioFacturacion")
	public IRespuesta obtenerRecepcionAnulacion(
			@XmlElement(required = true) @WebParam(name = "SolicitudServicioRecepcionAnulacion") ISolicitudRecepcionAnulacion solicitud);
}