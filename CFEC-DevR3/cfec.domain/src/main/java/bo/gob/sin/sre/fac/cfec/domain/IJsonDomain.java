package bo.gob.sin.sre.fac.cfec.domain;

public interface IJsonDomain {

	public String recepcionEtapa1(String pSolicitud);

	public String recepcionEtapa2(String pSolicitud);

	public String validacionRecepcionXml(String pSolicitud);

	public String validacionRecepcionPaqueteXml(String pSolicitud);

	public String anulacionXml(String pSolicitud);

	public String validacionAnulacionXml(String pSolicitud);

	public Integer validarNitBd(long pNit);

	public void registrarErroresBd(String pFactura, String pListaErrores);

	public String obtenerDescripcionCodigosRespuesta(String pSolicitud);

	public String registroLogCertificacionSistemas(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripci贸n realiza el registro de envio paquete
	 * @param pSolicitud dto XmlRecepcionGenericoDto en formato json
	 * @fecha 10/06/2019
	 */
	public void registrarLogEtapa6EnvioPaquetesBd(String pSolicitud);

	/**
	 * @autor reynaldo.chambi
	 * @descripci贸n realiza el registro de envio masivo
	 * @param pSolicitud dto XmlRecepcionGenericoDto en formato json
	 * @fecha 23/06/2019
	 */
	public String recepcionEtapa1Masivo(String pSolicitud);

	/**
	 * @autor reynaldo.chambi
	 * @descripci贸n realiza el registro de envio masivo
	 * @param pSolicitud dto XmlRecepcionGenericoDto en formato json
	 * @fecha 23/06/2019
	 */
	public String recepcionEtapa2Masivo(String pSolicitud);

	/**
	 * @author edwin.coro
	 * @descripcion verifica que existe registro de recepcion
	 * @param pTipoDocSector   tipo documento sector
	 * @param pCodigoRecepcion codigo de recepcion a verificar
	 * @return valor mayor a 0 si existe recepcion caso contrario 0 no existencia o
	 *         null en caso de excepcion
	 * @fecha 24/06/2019
	 */
	public Integer verificarExistenciaCodigoRecepcion(int pTipoDocSector, long pCodigoRecepcion);

	/**
	 * @autor edwin.coro
	 * @descripci贸n metodo que invoca funcion para la obtencion de codigo recepcion
	 *              individual, apartir del cuf y valores obtenidos en el servicio
	 * @param pSolicitud dto con parametros de servicio
	 * @return codigo recepcion individual o valor 0
	 * @fecha 10/07/2019
	 */
	public Long obtenerCodigoRecIndBd(String pSolicitud);

	/**
	 * @autor reynaldo.chambi
	 * @descripci贸n realiza el registro de envio masivo
	 * @param pSolicitud dto XmlRecepcionGenericoDto en formato json
	 * @fecha 22/07/2019
	 */
	public String validacionRecepcionMasivaXml(String pSolicitud);

	/**
	 * @autor reynaldo.chambi
	 * @descripci贸n realiza el registro de envio masivo
	 * @param pSolicitud dto XmlRecepcionGenericoDto en formato json
	 * @fecha 24/07/2019
	 */
	public int obtenerCantidadMaximaMasivo(long pContribuyenteId, int pSucursal);

	/**
	 * @autor edwin.coro
	 * @descripci贸n
	 * @param pSolicitud
	 * @fecha 07/08/2019
	 */
	public void registrarLogPruebaObligatoriaEtapa6(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripci贸n
	 * @param pSolicitud
	 * @return
	 * @fecha 19/08/2019
	 */
	public String obtenerFacturaDatosParcial(String pSolicitud);

	/**
	 * @autor edwin.coro
	 * @descripci贸n metodo que invoca funcion para registro de excepciones
	 * @param pSolicitud dto con datos correspondientes a tabla
	 *                   sre_fac_logbd_errores
	 * @fecha 26/08/2019
	 */
	public void registrarLogExcepcionBDErrores(String pSolicitud);

	/**
	 * @autor reynaldo.chambi
	 * @descripci贸n metodo que invoca funcion para registro de excepciones
	 * @param pSolicitud dto con datos correspondientes a tabla
	 *                   sre_fac_logbd_errores
	 * @fecha 26/08/2019
	 */
	public String obtenerFacturaPush(int pCodigoDocumentoSector, long pRecepcionId, String pCuf);

	public String modificarEtapaProcesada(int pTipoDocumentoFiscal, int pSector, long pCodigoRecepcion,
			int estadoPaquete);

	public long obtenerRecepcionAnulacion(String pCuf);

	public String modificarRecepcionPaqueteCantidadArchivo(long pArchivoValidoId, long pRecepcionPaqueteId,
			int pCantidadPaquete, int pSector);

	public Integer obtenerCantidadMaximaPaquete();
	
	/**
	 * @autor junior.flores
	 * @descripci贸n metodo que invoca la funcion de base de datos para la obtenci髇 del c骴igo de recepcion de anulaci髇
	 * @param pSolicitud dto con datos correspondientes para obtener la recepci髇 de la anulaci髇
	 * @fecha 26/08/2019
	 */
	public String recepcionAnulacionXml(String pSolicitud);
}