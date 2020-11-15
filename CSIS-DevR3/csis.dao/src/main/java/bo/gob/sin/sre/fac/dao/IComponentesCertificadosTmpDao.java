package bo.gob.sin.sre.fac.dao;
import java.util.List;
import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreComponentesCertificadosTmp;

public interface IComponentesCertificadosTmpDao extends GenericDao<SreComponentesCertificadosTmp>{
	
	/***
	 * verificar si existen registros de huellas de sistema del contribuyente
	 * 
	 * @author rosario.garcia
	 * @param pSistemaId = es el sistema ID de la tabla
	 * @return la lista de componentes certificados 
	 * @fecha 29/11/2018
	 */
	public List<SreComponentesCertificadosTmp> obtenerComponentesCertificadosTmp(Long pSistemaId);
	
}
