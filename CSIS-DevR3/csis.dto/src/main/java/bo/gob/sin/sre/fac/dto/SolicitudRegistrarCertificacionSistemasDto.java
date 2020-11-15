package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

public class SolicitudRegistrarCertificacionSistemasDto extends ListaMensajesAplicacion implements Serializable{

	//IASC
	private Integer tipoDepartamentoId;
	private Integer tipoSistemaId;
	private String nombreSistema;
	private String version;
	
	private Long usuarioId; 
	private Long solicitudCertificacionId;
	private int tipoSolicitudCertificacionId;
	private Long sistemaId;
	private Long contribuyenteId;
	private long nit;
	private int sucursal;
	private int oficinaId;
	
	private List<String> tiposModulos;
	private List<SolicitudContactosCertificacionesDto> listaContactos; 
	private List<String> listaDocumentos;
	private List<String> listaModalidad;
	
	private List<ClasificadorDto> listaDocumentos2;

	public SolicitudRegistrarCertificacionSistemasDto(Integer tipoDepartamentoId, Integer tipoSistemaId,
			String nombreSistema, String version, Long usuarioId, Long solicitudCertificacionId, Long contribuyenteId,
			long nit, int sucursal, int oficinaId, List<String> tiposModulos,
			List<SolicitudContactosCertificacionesDto> listaContactos, List<String> listaDocumentos,
			List<String> listaModalidad, int tipoSolicitudCertificacionId,  Long sistemaId) {
		super();
		this.tipoDepartamentoId = tipoDepartamentoId;
		this.tipoSistemaId = tipoSistemaId;
		this.nombreSistema = nombreSistema;
		this.version = version;
		this.usuarioId = usuarioId;
		this.solicitudCertificacionId = solicitudCertificacionId;
		this.contribuyenteId = contribuyenteId;
		this.nit = nit;
		this.sucursal = sucursal;
		this.oficinaId = oficinaId;
		this.tiposModulos = tiposModulos;
		this.listaContactos = listaContactos;
		this.listaDocumentos = listaDocumentos;
		this.listaModalidad = listaModalidad;
		this.tipoSolicitudCertificacionId = tipoSolicitudCertificacionId;
		this.sistemaId= sistemaId;
	}

	public SolicitudRegistrarCertificacionSistemasDto() {
		this.tiposModulos = new ArrayList<>();
		this.listaContactos = new ArrayList<>();
		this.listaDocumentos = new ArrayList<>();
		this.listaDocumentos2 = new ArrayList<>();
		this.listaModalidad = new ArrayList<>();
	}

	public Integer getTipoDepartamentoId() {
		return tipoDepartamentoId;
	}

	public void setTipoDepartamentoId(Integer tipoDepartamentoId) {
		this.tipoDepartamentoId = tipoDepartamentoId;
	}

	public Integer getTipoSistemaId() {
		return tipoSistemaId;
	}

	public void setTipoSistemaId(Integer tipoSistemaId) {
		this.tipoSistemaId = tipoSistemaId;
	}

	public String getNombreSistema() {
		return nombreSistema;
	}

	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getContribuyenteId() {
		return contribuyenteId;
	}

	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}



	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public List<SolicitudContactosCertificacionesDto> getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(List<SolicitudContactosCertificacionesDto> listaContactos) {
		this.listaContactos = listaContactos;
	}
	
	
	
	public List<String> getTiposModulos() {
		return tiposModulos;
	}

	public void setTiposModulos(List<String> tiposModulos) {
		this.tiposModulos = tiposModulos;
	}

	public List<String> getListaDocumentos() {
		return listaDocumentos;
	}

	public void setListaDocumentos(List<String> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}

	public List<String> getListaModalidad() {
		return listaModalidad;
	}

	public void setListaModalidad(List<String> listaModalidad) {
		this.listaModalidad = listaModalidad;
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public int getSucursal() {
		return sucursal;
	}

	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	
	public int getOficinaId() {
		return oficinaId;
	}

	public void setOficinaId(int oficinaId) {
		this.oficinaId = oficinaId;
	}
	
	public Long getSolicitudCertificacionId() {
		return solicitudCertificacionId;
	}

	public void setSolicitudCertificacionId(Long solicitudCertificacionId) {
		this.solicitudCertificacionId = solicitudCertificacionId;
	}
	
	

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public List<ClasificadorDto> getListaDocumentos2() {
		return listaDocumentos2;
	}

	public void setListaDocumentos2(List<ClasificadorDto> listaDocumentos2) {
		this.listaDocumentos2 = listaDocumentos2;
	}

	public int getTipoSolicitudCertificacionId() {
		return tipoSolicitudCertificacionId;
	}

	public void setTipoSolicitudCertificacionId(int tipoSolicitudCertificacionId) {
		this.tipoSolicitudCertificacionId = tipoSolicitudCertificacionId;
	}

	
	
}
