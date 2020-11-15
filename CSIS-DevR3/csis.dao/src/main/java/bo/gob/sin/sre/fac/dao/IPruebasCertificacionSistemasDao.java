package bo.gob.sin.sre.fac.dao;

import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.model.SrePruebasCertificacionSistemas;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface IPruebasCertificacionSistemasDao extends GenericDao<SrePruebasCertificacionSistemas>{

	/**
	 * Obtiene la entidad Prueba Certificaci贸n Sistemas a travez de la prueba etapa certificaci贸n
	 * 
	 * @author: junior.flores
	 * @Fecha: 06/06/2019
	 * @param pEtapaCertificacionSistemasId, c贸digo de la etapa de Certificaci贸n de Sistemas
	 * 		  pSistemaId, C贸digo unico de Sistema	 
	 * @return Devuelve una entidad tipo SrePruebasCertificacionSistemas.
	 */
	public SrePruebasCertificacionSistemas obtenerPruebasCertificacionSistemasPorEtapa(Long pPruebaEtapaCertificacionId, Long pSistemaId);
	
	
	/**
	* @Descipcion: Realiza el registro del log seleccionado en la grilla del Detalle de Certificaci贸n de Pruebas.
	* @author: Peter Flores.
	* @Fecha: 08/07/2019
	* @param pSolicitud: Informaci贸n necesaria para registrar la etapa de prueba (detalle)
	* @return Devuelve un valor booleano que indica la transacci贸n
	*/
	public boolean registroPruebaDetalleOpcional(SeguimientoDetalleCertificacionSistemasDto pSolicitud);
	
	/**
	* @Descipcion: Realiza el registro en las siguientes tablas: 
	* 				- sre_fac_nit_asignados_clientes_proveedor
	* 				- sre_fac_sistemas_contribuyentes
	* 				- re_fac_inicios_sistemas
	* @author: Peter Flores.
	* @Fecha: 22/08/2019
	* @param pSistemaId: C骴igo unico del Sistema 
	* 		 pContribuyenteId: C骴igo unico del contribuyente
	* 		 pModalidadesFacturacion: Son las modalidades de facturaci髇 que pertenece al sistema del contribuyente
	* @return Devuelve un valor booleano que indica la transacci贸n
	*/
	public boolean asignarNitProveedores(Long pSistemaId,Long pContribuyenteId, String pModalidadesFacturacion);
}
