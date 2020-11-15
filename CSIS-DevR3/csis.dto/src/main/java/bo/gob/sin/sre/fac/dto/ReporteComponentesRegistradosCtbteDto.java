package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ReporteComponentesRegistradosCtbteDto extends ListaMensajesAplicacion implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String nombreSistema;
	private String descripcionSistema;
	private String version;
	private String descripcionModalidadFacturacionId;
	private String descripcionTipoSistema;
	
	private Date fechaRegistro;
	private List<DatosReporteComponentesCtbteDto> listaComponentesContribuyente;
	
	public ReporteComponentesRegistradosCtbteDto()
	{
		listaComponentesContribuyente = new ArrayList<>();
	}
	
	public String getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	public String getDescripcionSistema() {
		return descripcionSistema;
	}

	public void setDescripcionSistema(String descripcionSistema) {
		this.descripcionSistema = descripcionSistema;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescripcionModalidadFacturacionId() {
		return descripcionModalidadFacturacionId;
	}

	public void setDescripcionModalidadFacturacionId(String descripcionModalidadFacturacionId) {
		this.descripcionModalidadFacturacionId = descripcionModalidadFacturacionId;
	}

	public String getDescripcionTipoSistema() {
		return descripcionTipoSistema;
	}

	public void setDescripcionTipoSistema(String descripcionTipoSistema) {
		this.descripcionTipoSistema = descripcionTipoSistema;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<DatosReporteComponentesCtbteDto> getListaComponentesContribuyente() {
		return listaComponentesContribuyente;
	}

	public void setListaComponentesContribuyente(List<DatosReporteComponentesCtbteDto> listaComponentesContribuyente) {
		this.listaComponentesContribuyente = listaComponentesContribuyente;
	}
	
}
