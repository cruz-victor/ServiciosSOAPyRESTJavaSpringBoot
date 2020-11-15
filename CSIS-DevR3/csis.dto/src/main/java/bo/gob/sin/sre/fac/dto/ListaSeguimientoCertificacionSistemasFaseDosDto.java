package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ListaSeguimientoCertificacionSistemasFaseDosDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private List<SeguimientoCertificacionSistemasFaseDosDto> listaSeguimientoEtapasComponentes;
	
	private Long datosEntradaSolicitudCertificacion;
	private Long datosEntradaSistemaId;
	private Long datosEntradaUsuarioRegistro;
	
	public ListaSeguimientoCertificacionSistemasFaseDosDto()
	{
		listaSeguimientoEtapasComponentes = new ArrayList<>();
	}

	/**
	 * @return the listaSeguimientoEtapasComponentes
	 */
	public List<SeguimientoCertificacionSistemasFaseDosDto> getListaSeguimientoEtapasComponentes() {
		return listaSeguimientoEtapasComponentes;
	}

	/**
	 * @param listaSeguimientoEtapasComponentes the listaSeguimientoEtapasComponentes to set
	 */
	public void setListaSeguimientoEtapasComponentes(
			List<SeguimientoCertificacionSistemasFaseDosDto> listaSeguimientoEtapasComponentes) {
		this.listaSeguimientoEtapasComponentes = listaSeguimientoEtapasComponentes;
	}
	

	public Long getDatosEntradaSolicitudCertificacion() {
		return datosEntradaSolicitudCertificacion;
	}

	public void setDatosEntradaSolicitudCertificacion(Long datosEntradaSolicitudCertificacion) {
		this.datosEntradaSolicitudCertificacion = datosEntradaSolicitudCertificacion;
	}

	public Long getDatosEntradaSistemaId() {
		return datosEntradaSistemaId;
	}

	public void setDatosEntradaSistemaId(Long datosEntradaSistemaId) {
		this.datosEntradaSistemaId = datosEntradaSistemaId;
	}

	public Long getDatosEntradaUsuarioRegistro() {
		return datosEntradaUsuarioRegistro;
	}

	public void setDatosEntradaUsuarioRegistro(Long datosEntradaUsuarioRegistro) {
		this.datosEntradaUsuarioRegistro = datosEntradaUsuarioRegistro;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListaSeguimientoCertificacionSistemasFaseDosDto [listaSeguimientoEtapasComponentes="
				+ listaSeguimientoEtapasComponentes + "]";
	}
}
