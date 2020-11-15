package bo.gob.sin.sre.fac.cfec.dao;

/**
 * @author edwin.coro
 *
 */
public interface IReporteDao {

	/**
	 * @autor edwin.coro
	 * @descripción metodo que recupera datos de la tabla
	 *              sre_fac_archivos_xml_validos
	 * @param pSolicitud
	 * @return ejemplo
	 *         {"recepcion_correcta_id":17017,"archivo_xml_valido_id":27977,"archivo":"44a7445585651774c..."}
	 * @fecha 27/08/2019
	 */
	public String obtenerArchivoXmlValido(String pSolicitud);

}