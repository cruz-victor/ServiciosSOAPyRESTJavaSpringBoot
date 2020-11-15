package bo.gob.sin.sre.fac.domain;

import java.util.List;

import bo.gob.sin.sre.fac.model.SreSistemas;

public interface ISistemasConsultaDomain {

	/**
	 * Obtener Solicitud de Sistemas por sistemaId
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 19/06/2018
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 14/11/2018
	 * @param pSistemaId, número identificación de sistema
	 * @return   Devuelve objeto respuesta SreSistema.
	 */
	public SreSistemas recuperarDatosSistemas(long pSistemaId);

	public List<SreSistemas> verificacionSistemaRegistrado(String pNombreSistema, Integer pModalidadFacturacionId, Long pContribuyenteId);
}
