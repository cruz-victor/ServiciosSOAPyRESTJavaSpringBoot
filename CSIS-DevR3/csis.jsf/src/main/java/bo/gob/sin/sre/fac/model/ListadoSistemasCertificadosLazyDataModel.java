package bo.gob.sin.sre.fac.model;



import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import bo.gob.sin.sre.fac.dto.RecuperaListaSistemasCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaListadoSistemasDto;
import bo.gob.sin.sre.fac.dto.SolicitudListadoSistemasDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

/**
 * @author freddy.yuca
 *
 */
public class ListadoSistemasCertificadosLazyDataModel extends LazyDataModel<RecuperaListaSistemasCertificacionDto>  implements Serializable {
	private static final long serialVersionUID = 1L;

	public ListadoSistemasCertificadosLazyDataModel() {
		ServiciosFacturacionRest vRest = new ServiciosFacturacionRest();
		SolicitudListadoSistemasDto vSolicitud = new SolicitudListadoSistemasDto();
		vSolicitud.setTotalRegistros(true);
		RespuestaListadoSistemasDto vRespuesta = vRest.obtenerListaSistemasCertificacion(vSolicitud);
		this.setRowCount(vRespuesta.getListaTamanio());
	}

	@Override
	public List<RecuperaListaSistemasCertificacionDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		ServiciosFacturacionRest vRest = new ServiciosFacturacionRest();
		if (filters.size() > 0) {
			SolicitudListadoSistemasDto vSolicitud = new SolicitudListadoSistemasDto();
			vSolicitud.setFiltros(filters);
			vSolicitud.setTotalRegistros(true);
			RespuestaListadoSistemasDto vRespuesta = vRest.obtenerListaSistemasCertificacion(vSolicitud);
			this.setRowCount(vRespuesta.getListaTamanio());
		}
		SolicitudListadoSistemasDto vSolicitud = new SolicitudListadoSistemasDto();
		vSolicitud.setTotalRegistros(false);
		vSolicitud.setPrimerRegistro(first);
		vSolicitud.setTamanioPagina(pageSize);
		vSolicitud.setCampoOrden(sortField);
		vSolicitud.setAscendente(sortOrder.ordinal() == 0);
		vSolicitud.setFiltros(filters);
		RespuestaListadoSistemasDto vRespuesta = vRest.obtenerListaSistemasCertificacion(vSolicitud);
		return vRespuesta.getLista();
	}

}
