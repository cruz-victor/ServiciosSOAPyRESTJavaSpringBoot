package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaDetalleHuellaDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private ComponentesArchivosDto componentesArchivos;
	private List<ComponentsCertificadosDto> listaComponentsCertificados;
	
	private Long datosEntradaSolicitudCertificacion;
	private Long datosEntradaSistemaId;
	private Long datosEntradaUsuarioRegistro;
	
	public RespuestaDetalleHuellaDto()
	{
		componentesArchivos = new ComponentesArchivosDto();
		listaComponentsCertificados = new ArrayList<>();
	}
	
	public ComponentesArchivosDto getComponentesArchivos() {
		return componentesArchivos;
	}
	public void setComponentesArchivos(ComponentesArchivosDto componentesArchivos) {
		this.componentesArchivos = componentesArchivos;
	}
	public List<ComponentsCertificadosDto> getListaComponentsCertificados() {
		return listaComponentsCertificados;
	}
	public void setListaComponentsCertificados(List<ComponentsCertificadosDto> listaComponentsCertificados) {
		this.listaComponentsCertificados = listaComponentsCertificados;
	}
	public Long getDatosEntradaUsuarioRegistro() {
		return datosEntradaUsuarioRegistro;
	}
	public void setDatosEntradaUsuarioRegistro(Long datosEntradaUsuarioRegistro) {
		this.datosEntradaUsuarioRegistro = datosEntradaUsuarioRegistro;
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
}