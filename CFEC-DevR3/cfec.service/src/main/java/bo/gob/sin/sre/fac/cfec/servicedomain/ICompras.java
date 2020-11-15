/**
 * 
 */
package bo.gob.sin.sre.fac.cfec.servicedomain;

import java.util.Date;
import java.util.List;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaObtenerComprasDto;

/**
 * @author reynaldo.chambi
 * @fecha 12/06/2019
 */
public interface ICompras {
	
	public RespuestaObtenerComprasDto recepcionBdComprasIndividual(String pCuf, long pNumeroFactura, String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta, Integer pCodigoAmbiente);

	public List<RespuestaObtenerComprasDto> recepcionBdComprasPorDia(Date pFechaInicio, Date pFechaFin, String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta, Integer pCodigoAmbiente);
}