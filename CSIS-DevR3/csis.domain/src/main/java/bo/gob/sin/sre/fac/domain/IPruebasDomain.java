package bo.gob.sin.sre.fac.domain;



import java.util.List;

import bo.gob.sin.sre.fac.dto.RespuestaListaPruebasDto;
import bo.gob.sin.sre.fac.dto.SolicitudListaPruebasDto;
import bo.gob.sin.sre.fac.model.SrePruebas;

public interface IPruebasDomain {


	/**
	 * Obtener nombre prueba del sistema
	 * 
	 * @author: Ivan Salas
	 * @Fecha:
	 * @modificadoPor: Carmen Rosa Silva
	 * @FechaModificacion: 19/06/2018
	 * @param pruebaId, objeto de tipo solicitud
	 * @return Devuelve objeto respuesta Dto de tipo Respuesta Pruebas Sistemas de
	 *         Certificacion.
	 */
	public String obtenerNombrePrueba(long pPruebaId);

	public List<SrePruebas> recuperarListaSistemasAutorizados(Integer pTipoPruebaId);

    public RespuestaListaPruebasDto recuperaListaPruebasPorTipo(SolicitudListaPruebasDto pSolicitud);

	public SrePruebas recuperaListaPruebasPorTipo1(SrePruebas pSolicitud);

	// prueba de beto recuperarlistapruebasportipo
	public SrePruebas recuperarListaPruebasPorTipo(Integer pTipoPruebaId);

	/**
	 * Recuperar datos pruebas
	 * 
	 * @author: Carmen Rosa Silva
	 * @Fecha: 29/06/2018
	 * @modificadoPor:
	 * @FechaModificacion:
	 * @param pruebaId, objeto de tipo solicitud
	 * @return Devuelve objeto SrePruebas
	 */
	public SrePruebas recuperarSistema(Long pPruebaId);

	public RespuestaListaPruebasDto recuperaListaPruebasPorTipo(Integer pSolicitud);

}
