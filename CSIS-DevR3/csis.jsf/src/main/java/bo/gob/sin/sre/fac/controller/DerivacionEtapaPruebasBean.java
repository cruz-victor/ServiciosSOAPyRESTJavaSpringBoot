package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bo.gob.sin.sap.cder.componentes.controller.AbstractDerivacionController;
import bo.gob.sin.sap.cead.componentes.controller.ScrDtoDocumentoEmitido;

@ViewScoped
@ManagedBean(name = "derivacionEtapaPruebasBean")
public class DerivacionEtapaPruebasBean extends AbstractDerivacionController implements Serializable {
	private static final Logger LOG = LoggerFactory.getLogger(DerivacionEtapaPruebasBean.class);
	private static final long serialVersionUID = 1L;

	/*@ManagedProperty(value = "#{gestionDocumentoEmitidoController}")
	private GestionDocumentoEmitidoController gestionDocumentoEmitidoController;*/

	public DerivacionEtapaPruebasBean() {
		filtrarUsuariosOficina(true);
	}

	@Override
	public boolean esValidaDerivacion() {
		boolean respuesta = true;
		/*List<ScrDtoDocumentoEmitido> existeAprobacion = this.gestionDocumentoEmitidoController.getListaEmisionDocumentos().obtenerDocumentoEmitidosPorIdTramiteIdPlantilla(gestionDocumentoEmitidoController.getContextoSolicitudCertificacionSistemaModel().getSolicitud().getTramiteId(), gestionDocumentoEmitidoController.PLANTILLA_ID_CERTIFICACION);
		if (existeAprobacion == null || existeAprobacion.isEmpty()) {
			List<ScrDtoDocumentoEmitido> existeRechazo = this.gestionDocumentoEmitidoController.getListaEmisionDocumentos().obtenerDocumentoEmitidosPorIdTramiteIdPlantilla(gestionDocumentoEmitidoController.getContextoSolicitudCertificacionSistemaModel().getSolicitud().getTramiteId(), gestionDocumentoEmitidoController.PLANTILLA_ID_CERTIFICACION_RECHAZO);
			if (existeRechazo == null || existeRechazo.isEmpty()) {
				respuesta = false;
				RequestContext.getCurrentInstance().execute("toastr.error('Debe emitir el documento de Aprobación o Rechazo de la certificación', 'Información')");
			}
		}*/

		return respuesta;
	}

	@Override
	public boolean esValidaFinalizacion() {
		return false;
	}

	/*public GestionDocumentoEmitidoController getGestionDocumentoEmitidoController() {
		return gestionDocumentoEmitidoController;
	}

	public void setGestionDocumentoEmitidoController(
			GestionDocumentoEmitidoController gestionDocumentoEmitidoController) {
		this.gestionDocumentoEmitidoController = gestionDocumentoEmitidoController;
	}*/
}
