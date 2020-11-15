package bo.gob.sin.sre.fac.cfec.domain;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudObtencionFacturaVentasDto;

public interface IOperacionesCufDomain {
	
	String generacuf(String input);
	
	public int validar(Long pNit, Integer pSucursal, String pFecha, Integer pModalidad, Integer pTipoEmision,  Integer pTipoDocumentoFiscal, Integer pTipoDocumentoSector, Long pNumFactura, Integer pPos);
	
	String reviertecuf(String input);
	
	public  String concatenar(Long pNit, Integer pSucursal, String pFecha, Integer pModalidad, Integer pTipoEmision,  Integer pTipoDocumentoFiscal, Integer pTipoDocumentoSector, Long pNumFactura, Integer pPos);
	
	String calculaDigitoMod11(String dado, int numDig, int limMult, boolean x10);
	
	public  String generadorCUF(Long pNit, Integer pSucursal, String pFecha, Integer pModalidad, Integer pTipoEmision,  Integer pTipoDocumentoFiscal, Integer pTipoDocumentoSector, Long pNumFactura, Integer pPos);
	
	public boolean verificacionCuf(Long pNit, Integer pSucursal, String pFecha, Integer pModalidad, Integer pTipoEmision,  Integer pTipoDocumentoFiscal, Integer pTipoDocumentoSector, String pCuf, Long pNumFactura, Integer pPos);
	
	public RespuestaFacturaVentasDto obtenerFacturaVentas(SolicitudObtencionFacturaVentasDto pSolicitud, RespuestaDatosCufDto vRespuestaCuf);
}