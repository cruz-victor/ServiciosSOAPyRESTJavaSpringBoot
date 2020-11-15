package bo.gob.sin.sre.fac.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import bo.gob.sin.sre.fac.dto.RegistrosPruebasAutomaticasDto;
import bo.gob.sin.sre.fac.dto.RespuestaListaRegistroPruebasAutomaticasDto;
import bo.gob.sin.sre.fac.dto.RespuestaTamanioGenericoDto;
import bo.gob.sin.sre.fac.dto.SolicitudPruebasSistemasDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

/**
 * @author sergio.ichaso
 *
 */
public class PruebasAutomaticasLazyDataModel extends LazyDataModel<RegistrosPruebasAutomaticasDto> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SolicitudPruebasSistemasDto datosSolicitud = new SolicitudPruebasSistemasDto();

	private List<RegistrosPruebasAutomaticasDto> datosSolicitudLista = new ArrayList<>();

	ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;

	public PruebasAutomaticasLazyDataModel(
			ContextoSolicitudCertificacionSistemaModel pContextoSolicitudCertificacionSistemaModel) {
		System.out.println("--------- " + this + " -----------------");

		this.setContextoSolicitudCertificacionSistemaModel(pContextoSolicitudCertificacionSistemaModel);
		ServiciosFacturacionRest vRest = new ServiciosFacturacionRest();

		RespuestaTamanioGenericoDto vRespuesta = vRest.obtieneTotalRegistrosPruebasAutomaticas(getDatosSolicitud());
		this.setRowCount(vRespuesta.getTamanioLista().intValue());
	}

	@Override
	public List<RegistrosPruebasAutomaticasDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		getDatosSolicitud();
		datosSolicitud.setPrimerRegistro(first);
		datosSolicitud.setCampoOrden(sortField);
		datosSolicitud.setTamanioPagina(pageSize);
		datosSolicitud.setFiltros(filters);
		if (filters != null && filters.size() > 0) {
			filters = filters.values().toArray()[0].toString().trim() == "" ? new HashMap<String, Object>() : filters;
			if (filters.size() > 0) {
				ServiciosFacturacionRest vRest = new ServiciosFacturacionRest();
				datosSolicitud.setFiltros(filters);
				RespuestaTamanioGenericoDto vRespuesta = vRest
						.obtieneTotalRegistrosPruebasAutomaticas(getDatosSolicitud());
				this.setRowCount(vRespuesta.getTamanioLista().intValue());
			}
		}
	
		RespuestaListaRegistroPruebasAutomaticasDto vVerificacionInsituCompletada = new RespuestaListaRegistroPruebasAutomaticasDto();
		ServiciosFacturacionRest vRest = new ServiciosFacturacionRest();
		vVerificacionInsituCompletada = vRest.obtieneListaPaginadaPruebasAutomaticas(datosSolicitud);

		datosSolicitudLista = vVerificacionInsituCompletada.getPruebasAutomaticas();
		return vVerificacionInsituCompletada.getPruebasAutomaticas();
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	public SolicitudPruebasSistemasDto getDatosSolicitud() {
		if (contextoSolicitudCertificacionSistemaModel != null) {

			datosSolicitud.setSolicitudId(contextoSolicitudCertificacionSistemaModel.getSolicitud().getSolicitudId());
			datosSolicitud.setSistemaId(contextoSolicitudCertificacionSistemaModel.getSolicitud().getSistemaId());
			datosSolicitud
					.setContribuyenteId(contextoSolicitudCertificacionSistemaModel.getSolicitud().getContribuyenteId());
			datosSolicitud.setTramiteId(contextoSolicitudCertificacionSistemaModel.getSolicitud().getTramiteId());
		}
		return datosSolicitud;
	}

	public void setDatosSolicitud(SolicitudPruebasSistemasDto datosSolicitud) {
		this.datosSolicitud = datosSolicitud;
	}

	public List<RegistrosPruebasAutomaticasDto> getDatosSolicitudLista() {
		return datosSolicitudLista;
	}

	public void setDatosSolicitudLista(List<RegistrosPruebasAutomaticasDto> datosSolicitudLista) {
		this.datosSolicitudLista = datosSolicitudLista;
	}

}
