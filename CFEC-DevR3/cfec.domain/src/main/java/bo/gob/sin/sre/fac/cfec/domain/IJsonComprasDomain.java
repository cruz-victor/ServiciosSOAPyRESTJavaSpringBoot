package bo.gob.sin.sre.fac.cfec.domain;

import java.util.Date;
import java.util.List;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaObtenerComprasDto;

public interface IJsonComprasDomain {

	public String recepcionEtapa1(String pSolicitud);

	public String recepcionEtapa2(String pSolicitud);

	public String validacionRecepcionXml(String pSolicitud);

	public String validacionRecepcionPaqueteXml(String pSolicitud);

	public void registrarErroresBd(String pFactura, String pListaErrores);

	public RespuestaObtenerComprasDto recepcionBdComprasIndividual(int pDocumentoSector, String pCuf, long pNumeroFactura, String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta, Integer pCodigoAmbiente);
	
	public List<RespuestaObtenerComprasDto> recepcionBdComprasPorDia(Date pFechaInicio, Date pFechaFin, String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta, Integer pCodigoAmbiente);

}