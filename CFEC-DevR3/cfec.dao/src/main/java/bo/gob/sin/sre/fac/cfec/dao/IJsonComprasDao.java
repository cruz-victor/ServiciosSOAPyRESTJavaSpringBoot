package bo.gob.sin.sre.fac.cfec.dao;

import java.util.Date;
import java.util.List;

/**
 * @author edwin.coro
 *
 */
public interface IJsonComprasDao {
	
	public Object recepcionBdComprasIndividual(int pDocumentoSector, String pCuf, long pNumeroFactura, String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta, Integer pCodigoAmbiente);
	
	public List<Object> recepcionBdComprasPorDia(Date pFechaInicio, Date pFechaFin, String pNumeroDocumento, String pCodigoSistema, String pCufd, String pCuis, Integer pCodigoSucursal, Integer pCodigoPuntoVenta, Integer pCodigoAmbiente);

	/**
	 * @autor edwin.coro
	 * @descripción
	 * @param pSolicitud
	 * @return
	 * @fecha 22/04/2019
	 */
	public String recepcionBdEtapa1(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripción
	 * @param pSolicitud
	 * @return
	 * @fecha 22/04/2019
	 */
	public String recepcionBdEtapa2(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funciona para el servicio de validacion de
	 *              recepcion
	 * @param pSolicitud dto con parametros de servicio
	 * @return
	 * @fecha 26/03/2019
	 */
	public String validacionRecepcionBd(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que invoca funciona para el servicio de validacion de
	 *              recepcion paquete
	 * @param pSolicitud dto con parametros de servicio
	 * @return
	 * @fecha 26/03/2019
	 */
	public String validacionRecepcionPaqueteBd(String pSolicitud);

	/**
	 * @param registrarErroresBd
	 * @return
	 */
	public void registrarErroresBd(String pFactura, String pListaErrores);

}