package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.BitacoraObservacionComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.BitacorasObservacionesComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.RegistroObservacionesComponentesInsituDto;
import bo.gob.sin.sre.fac.dto.RegistroObservacionesComponentesInsituListaDto;
import bo.gob.sin.sre.fac.dto.RegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistrosObservacionesInsituDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;

@SuppressWarnings("deprecation")
@ManagedBean(name = "verificacionInsituModel")
@ViewScoped

public class VerificacionInsituModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RegistroObservacionesComponentesInsituDto> listaComponentes;
	private List<RegistroObservacionesComponentesInsituDto> filtroComponentes;
	private List<RegistrosObservacionesInsituDto> listaVerificaciones;
	private RegistroObservacionesComponentesInsituDto componenteConObs; // Copia de la fila a Editar de lista
																		// Componentes recuperados
	private BitacorasObservacionesComponentesInsituDto observacionAcomp;
	private List<BitacoraObservacionComponentesInsituDto> listaObservacionAcomp;
	private BitacoraObservacionComponentesInsituDto nuevaObservacion; // Para agregar/reeemplazar la lista de
																		// Observaciones
	private String obs;
	private int cont;
	private int filaEdit;
	private int filaEditObs;
	private RegistrosObservacionesInsituDto nuevaVerificacion;

	@ManagedProperty(value = "#{contextoUsuarioModel}")
	private ContextoUsuarioModel contextoUsuarioModel;

	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajesBean;

	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	private ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;

	@PostConstruct
	public void main() {
		limpiaListaComponentes();
		limpiaFiltroComponentes();
		limpiaListaVerificaciones();
		limpiaNuevaObservacion();
		limpiaNuevaVerificacion();
		this.getNuevaVerificacion().setFechaVerificacion(new Date());
		this.filaEdit = -1;
		this.filaEditObs = -1;
		this.cont = 0;
		System.out.println("Modelo iniciado!");
	}

	private void limpiaListaComponentes() {
		listaComponentes = new ArrayList<>();
	}

	private void limpiaFiltroComponentes() {
		filtroComponentes = new ArrayList<>();
	}

	private void limpiaListaVerificaciones() {
		listaVerificaciones = new ArrayList<>();
	}

	private void limpiaNuevaObservacion() {
		nuevaObservacion = new BitacoraObservacionComponentesInsituDto();
	}

	private void limpiaNuevaVerificacion() {
		nuevaVerificacion = new RegistrosObservacionesInsituDto();
	}

	public void obtenerlistaComponentes() {
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		RegistroObservacionesComponentesInsituDto pListaObservacionesComp = new RegistroObservacionesComponentesInsituDto();
		listaComponentes = new ArrayList<>();

		RegistroObservacionesComponentesInsituListaDto vResultado = new RegistroObservacionesComponentesInsituListaDto();

		//pListaObservacionesComp.setSolicitudCertificacionId(contextoSolicitudCertificacionSistemaModel.getSolicitud().getSolicitudId());
		pListaObservacionesComp.setSolicitudCertificacionId(14L);
		long vUsuarioId = contextoUsuarioModel.getUsuario().getUsuarioId();
		pListaObservacionesComp.setUsuarioRegistroId(vUsuarioId);
		pListaObservacionesComp.setUsuarioUltimaModificacionId(vUsuarioId);

		vResultado = vServiciosFacturacionRest.obtenerDatosInicialesObservacionesCompSistema(pListaObservacionesComp);

		listaComponentes = vResultado.getRegistrosObservacionesComponentesInsituDto();
		// Date hora = new Date();
		// System.err.println(hora.toString() + ">Se han recuperado " +
		// listaComponentes.size() + " componentes.");
	}

	public void obtenerlistaVerificaciones() {
		ServiciosFacturacionRest vServiciosFacturacionRest = new ServiciosFacturacionRest();
		RegistrosObservacionesInsituDto pSolicitudListaVerificaciones = new RegistrosObservacionesInsituDto();
		listaVerificaciones = new ArrayList<>();

		RespuestaRegistrosObservacionesInsituDto vResultado = new RespuestaRegistrosObservacionesInsituDto();

		long vSolicitudCertificacionId = contextoSolicitudCertificacionSistemaModel.getSolicitud().getSolicitudId();
		pSolicitudListaVerificaciones.setSolicitudCertificacionId(vSolicitudCertificacionId);
		vResultado = vServiciosFacturacionRest.obtenerListaVerificacionesInsitu(pSolicitudListaVerificaciones);

		listaVerificaciones = vResultado.getLista();
		if (listaVerificaciones != null) {
		} else {
			System.err.println("No se han recuperado verificaciones. " + vResultado.getMensajes());
		}
	}

	public List<RegistroObservacionesComponentesInsituDto> getListaComponentes() {
		return listaComponentes;
	}

	public void setListaComponentes(List<RegistroObservacionesComponentesInsituDto> listaComponentes) {
		this.listaComponentes = listaComponentes;
	}

	public List<RegistroObservacionesComponentesInsituDto> getFiltroComponentes() {
		return filtroComponentes;
	}

	public void setFiltroComponentes(List<RegistroObservacionesComponentesInsituDto> filtroComponentes) {
		this.filtroComponentes = filtroComponentes;
	}

	public List<RegistrosObservacionesInsituDto> getListaVerificaciones() {
		return listaVerificaciones;
	}

	public void setListaVerificaciones(List<RegistrosObservacionesInsituDto> listaVerificaciones) {
		this.listaVerificaciones = listaVerificaciones;
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public RegistroObservacionesComponentesInsituDto getComponenteConObs() {
		return componenteConObs;
	}

	public void setComponenteConObs(RegistroObservacionesComponentesInsituDto componenteConObs) {
		this.componenteConObs = componenteConObs;
	}

	public BitacorasObservacionesComponentesInsituDto getObservacionAcomp() {
		return observacionAcomp;
	}

	public void setObservacionAcomp(BitacorasObservacionesComponentesInsituDto observacionAcomp) {
		this.observacionAcomp = observacionAcomp;
	}

	public BitacoraObservacionComponentesInsituDto getNuevaObservacion() {
		return nuevaObservacion;
	}

	public void setNuevaObservacion(BitacoraObservacionComponentesInsituDto nuevaObservacion) {
		this.nuevaObservacion = nuevaObservacion;
	}

	public List<BitacoraObservacionComponentesInsituDto> getListaObservacionAcomp() {
		return listaObservacionAcomp;
	}

	public void setListaObservacionAcomp(List<BitacoraObservacionComponentesInsituDto> listaObservacionAcomp) {
		this.listaObservacionAcomp = listaObservacionAcomp;
	}

	public RegistrosObservacionesInsituDto getNuevaVerificacion() {
		return nuevaVerificacion;
	}

	public void setNuevaVerificacion(RegistrosObservacionesInsituDto nuevaVerificacion) {
		this.nuevaVerificacion = nuevaVerificacion;
	}

	public int getFilaEdit() {
		return filaEdit;
	}

	public void setFilaEdit(int filaEdit) {
		this.filaEdit = filaEdit;
	}

	public int getFilaEditObs() {
		return filaEditObs;
	}

	public void setFilaEditObs(int filaEditObs) {
		this.filaEditObs = filaEditObs;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
