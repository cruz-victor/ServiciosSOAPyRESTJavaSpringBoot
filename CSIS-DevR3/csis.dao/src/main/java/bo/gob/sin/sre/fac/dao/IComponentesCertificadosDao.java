package bo.gob.sin.sre.fac.dao;


import java.util.List;
import bo.gob.sin.str.ccs.dao.GenericDao;
import bo.gob.sin.sre.fac.model.SreComponentesCertificados;

//IASC
public interface IComponentesCertificadosDao extends GenericDao<SreComponentesCertificados>{
	
	/**
     * Objetivo: recuperar lista huella sistema
     * Creado por: Ivan Angel Salas.
     * Fecha: 14/05/2018
     * Modificado por: Gualberto Condori
     * Fecha de Modificacion: 08/08/2018
     */
	public List<SreComponentesCertificados> obtenerListaComponentesCertificados(Long pSistemaId);
	
	//IASC
	public Long verificarComponentesCertificados(Long pSistemaId);

	//IASC
	public Long verificaExisteComponente(Long pSistemaId, Integer pTipoComponenteId);
	
	//IASC
	public SreComponentesCertificados recuperaDatosComponente(Long pSistemaId, Integer pComponenteId);
	
	/**
     * Objetivo: obtener la lista de componentes de certificado a través de los tipos de componentes
     * Creado por: Peter Flores.
     * Fecha: 20/11/2018     
     */
	public List<SreComponentesCertificados> obtenerComponentesCertificados(List<Long> pComponenteArchivoId);

	/**
     * Objetivo: obtener la lista de componentes de certificado por id componentearchivoid
     * Creado por: Wilson Limachi.
     * Fecha: 18/18/2019     
     */
	public List<SreComponentesCertificados> obtenerComponentesCertificados(Long pComponenteArchivoId); 
}
