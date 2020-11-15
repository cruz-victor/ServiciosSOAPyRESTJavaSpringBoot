package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import bo.gob.sin.sap.cbre.dto.EstadoDerivacionDto;
import bo.gob.sin.sap.cead.componentes.controller.EmisionDocumentoAbstract;
import bo.gob.sin.sap.cead.componentes.controller.ScrDtoDocumentoEmitido;
import bo.gob.sin.sap.cead.componentes.controller.ScrDtoDocumentoEmitidoDetalle;
import bo.gob.sin.sap.cead.componentes.controller.ScrDtoDocumentoEmitidoLista;
import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.scn.empa.caco.dto.ContribuyenteMasDireccionFiscalDto;
import bo.gob.sin.sre.fac.dto.ComponentsCertificadosDto;
import bo.gob.sin.sre.fac.dto.DatosReporteComponentesCtbteDto;
import bo.gob.sin.sre.fac.dto.ListaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.RegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.ReporteComponentesRegistradosCtbteDto;
import bo.gob.sin.sre.fac.dto.RespuestaDatosSistemasSolCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.RespuestaRegistroVerificacionInsituDto;
import bo.gob.sin.sre.fac.dto.RespuestaResumenPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.dto.ResumenPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasDto;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;
import bo.gob.sin.sre.fac.model.MetodosPadron;
import bo.gob.sin.sre.fac.service.ServiciosConsultaFacturacionRestClient;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.sre.fac.service.ServiciosPadronRestClient;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean(name = "gestionDocumentoEmitidoController")
public class GestionDocumentoEmitidoController extends EmisionDocumentoAbstract implements Serializable {
	private static final long serialVersionUID = 1L;

	private EstadoDerivacionDto estadoDerivacionDto;
	
	@ManagedProperty(value = "#{tramiteCertificacionBean}")
	private TramiteCertificacionBean tramiteCertificacionBean;
	
	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	private ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;
	
	static final String RESOLUCION_NUMERO = "101800000026";
	static final String RESOLUCION_FECHA = "20 de noviembre de 2018";
	static final long PLANTILLA_ID_CERTIFICACION = 1317L;
	static final long PLANTILLA_ID_CERTIFICACION_RECHAZO = 1367L;
	static final long LUGAR = 61;
	static final long RAZON_SOCIAL = 17;
	static final long DOMICILIO_FISCAL = 19;
	static final long FECHA_CONCLUSION = 80;
	static final long NOMBRE_GERENCIA = 14;
	static final long SISTEMA_ID = 81;
	static final long VERSION_SISTEMA = 82;
	static final long CODIGO_SISTEMA = 83;
	static final long NUMERO_RND = 75;
	static final long FECHA_RND = 76;
	static final long NOMBRE_SISTEMA = 77;
	static final long FECHA_SOLICITUD = 78;
	static final long MODALIDADES = 79;
	static final long FECHA_EMISION_CERTIFICADO = 84;
	static final long NIT = 18;
	static final String REFERENCIA_APROBACION = "Aprobacion del Proceso de Certificación de Sistema de Facturación";
	static final String REFERENCIA_RECHAZO = "Rechazo del Proceso de Certificación de Sistema de Facturación";

	static final String CAMPO_DESCRIPCION = "descripcion";
	//static final String CAMPO_FECHA = "fecha";
	static final String CAMPO_TIPO = "tipo";
	static final String CAMPO_ESTADO = "estado";
	static final String CAMPO_FILA = "fila";
	static final String CAMPO_OBSERVACION = "observacion";
	static final String CAMPO_NOMBRE_ARCHIVO = "nombreArchivo";
	static final String CAMPO_COMPONENTE = "componente";
	static final String CAMPO_RUTA = "ruta";

	// CODIGO
	static final int CODIGO_TIPO_DESTINO = 241;
	static final int CODIGO_ESTADO = 805;
	
	@PostConstruct
	public void inicio() {
		this.obtenerContexto(estadoDerivacionDto);
		this.tramiteCertificacionBean.setearContexto();
		this.contextoSolicitudCertificacionSistemaModel.getSolicitud().setSolicitudId(
				tramiteCertificacionBean.getTramiteSolicitudCertificacion().getSolicitudCertificacionId());
		this.cargarDatos(tramiteCertificacionBean.getRecepcionBean().getEstado().getTramiteId());
	}
	
	@Override
	public void cargarDatos(long idTramite) {
		this.getListaEmisionDocumentos().cargarDocumentosEmitidosTramite(idTramite);
	}

	@Override
	public void generarDocumento() {
		try {
			// Cargar plantillas del proceso
			Date vFechaEmision = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(vFechaEmision);
			DateFormat vFormateadorFecha = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy");
			DateFormat vFormateadorFechaNormal = new SimpleDateFormat("dd/MM/yyyy");
			long vPersonaContribuyente = tramiteCertificacionBean.getTramiteSolicitudCertificacion()
					.getPersonaContribuyenteId();
			MetodosPadron vMetodosPadron = new MetodosPadron();
			ContribuyenteDto nitDatosBasicos = vMetodosPadron
					.ObtenerDatosBasicosXIFCParaCertificado(vPersonaContribuyente);
			// 0. cargar datos solicitud
			ServiciosFacturacionRest servicio = new ServiciosFacturacionRest();
			RespuestaDatosSistemasSolCertificacionDto pSolicitud = new RespuestaDatosSistemasSolCertificacionDto();
			pSolicitud.setSistemaId(this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getSistemaId());
			pSolicitud.setSolicitudCertificacionId(
					this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getSolicitudId());
			RespuestaDatosSistemasSolCertificacionDto vDatosSistema = servicio
					.recuperarDatosComponenteSistema(pSolicitud);
			// obtener domicilio fiscal
			ServiciosPadronRestClient servicioP = new ServiciosPadronRestClient();
			ContribuyenteMasDireccionFiscalDto datosContribuyente = servicioP
					.ObtenerDatosBasicosMasDomicilioXNIT(vDatosSistema.getNit());
			String domicilioFiscal = (datosContribuyente.getDomiciliosSucursal().get(0) == null) ? ""
					: datosContribuyente.getDomiciliosSucursal().get(0).getDomicilioSucursal().getDomicilio()
							.getDomicilioConcatenado();
			List<ScrDtoDocumentoEmitido> lista = new ArrayList<>();
			ScrDtoDocumentoEmitido vDocumentoAprobacion = new ScrDtoDocumentoEmitido();
			ScrDtoDocumentoEmitido vDocumentoRechazo = new ScrDtoDocumentoEmitido();
			vDocumentoAprobacion.setPlantillaId(PLANTILLA_ID_CERTIFICACION);
			vDocumentoRechazo.setPlantillaId(PLANTILLA_ID_CERTIFICACION_RECHAZO);
			vDocumentoAprobacion.setReferencia(REFERENCIA_APROBACION);
			vDocumentoRechazo.setReferencia(REFERENCIA_RECHAZO);
			// 3. cargar datos del detalle
			List<ScrDtoDocumentoEmitidoDetalle> documentoEmitidoDetalle = new ArrayList<>();
			// 3.1 LUGAR
			documentoEmitidoDetalle.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, LUGAR, "La Paz"));
			// 3.2 RAZONSOCIAL
			documentoEmitidoDetalle
					.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, RAZON_SOCIAL, vDatosSistema.getRazonSocial()));
			// 3.3 DOMICILIOFISCAL
			documentoEmitidoDetalle.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, DOMICILIO_FISCAL, domicilioFiscal));
			// 3.4 FECHACONCLUSION
			documentoEmitidoDetalle.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, FECHA_CONCLUSION,
					(vDatosSistema.getFechaAprobacion() == null ? vFormateadorFechaNormal.format(vFechaEmision)
							: vFormateadorFechaNormal.format(vDatosSistema.getFechaAprobacion()))));
			// 3.5 NOMBREGERENCIA
			documentoEmitidoDetalle.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, NOMBRE_GERENCIA,
					nitDatosBasicos.getJurisdiccionDescripcion()));
			// 3.6 SISTEMAID
			documentoEmitidoDetalle.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, SISTEMA_ID,
					String.valueOf(vDatosSistema.getSistemaId())));
			// 3.7 VERSIONSISTEMA
			documentoEmitidoDetalle
					.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, VERSION_SISTEMA, vDatosSistema.getVersion()));
			// 3.8 CODIGOSISTEMA
			documentoEmitidoDetalle
					.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, CODIGO_SISTEMA, vDatosSistema.getCodigoSistema()));
			// 3.9 NUMERORND
			documentoEmitidoDetalle.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, NUMERO_RND, RESOLUCION_NUMERO));
			// 3.10 FECHARND
			documentoEmitidoDetalle.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, FECHA_RND, RESOLUCION_FECHA));
			// 3.11 NOMBRESISTEMA
			documentoEmitidoDetalle
					.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, NOMBRE_SISTEMA, vDatosSistema.getNombreSistema()));
			// 3.12 FECHASOLICITUD
			documentoEmitidoDetalle.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, FECHA_SOLICITUD,
					vFormateadorFechaNormal.format(vDatosSistema.getFechaSolicitud())));
			// 3.13 MODALIDADES
			documentoEmitidoDetalle
					.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, MODALIDADES, vDatosSistema.getModalidad()));
			// 3.14 FECHAEMISIONCERTIFICADO
			documentoEmitidoDetalle.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, FECHA_EMISION_CERTIFICADO,
					vFormateadorFecha.format(vFechaEmision)));
			// 3.15 NIT
			documentoEmitidoDetalle
					.add(new ScrDtoDocumentoEmitidoDetalle(0L, 0L, NIT, String.valueOf(vDatosSistema.getNit())));
			vDocumentoAprobacion.setDocumentoEmitidoDetalle(documentoEmitidoDetalle);
			vDocumentoRechazo.setDocumentoEmitidoDetalle(documentoEmitidoDetalle);

			// ADICIONAR LISTAS
			// 5. cargar datos de las listas
			List<ScrDtoDocumentoEmitidoLista> listas = new ArrayList<>();
			ScrDtoDocumentoEmitidoLista lComponente = new ScrDtoDocumentoEmitidoLista();
			lComponente.setNombre("tabla_a");
			List<Map<String, String>> regComponentes = new ArrayList<>();
			ListaDetalleHuellaDto pSolCom = new ListaDetalleHuellaDto();
			pSolCom.setDatoEntradaIdContribuyente(vPersonaContribuyente);
			pSolCom.setDatoEntradaIdSistema(
					this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getSistemaId());
			pSolCom.setDatoEntradaIdSolicitudCertificacion(
					this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getSolicitudId());
			ListaDetalleHuellaDto listaComponentesArchivos = new ServiciosConsultaFacturacionRestClient()
					.obtieneComponentesArchivosCertificados(pSolCom);
			int vFila = 1;
			if (listaComponentesArchivos.isOk() && !listaComponentesArchivos.getListaDetalleHuella().isEmpty()) {
				for (RespuestaDetalleHuellaDto vComponente : listaComponentesArchivos.getListaDetalleHuella()) {
					Map<String, String> comp1 = new HashMap<>();
					comp1.put(CAMPO_FILA, String.valueOf(vFila));
					comp1.put(CAMPO_NOMBRE_ARCHIVO,
							vComponente.getComponentesArchivos().getNombre() != null
									? vComponente.getComponentesArchivos().getNombre()
									: "");
					comp1.put(CAMPO_RUTA,
							vComponente.getComponentesArchivos().getRuta() != null
									? vComponente.getComponentesArchivos().getRuta()
									: "");
					StringJoiner joiner = new StringJoiner(",");
					for (ComponentsCertificadosDto dtoCom : vComponente.getListaComponentsCertificados()) {
						if (dtoCom.getTipoCompoonenteDescripcion() != null) {
							joiner.add(dtoCom.getTipoCompoonenteDescripcion());
						}
					}
					comp1.put(CAMPO_COMPONENTE, joiner.toString());
					regComponentes.add(comp1);
					vFila++;
				}
			}

			ScrDtoDocumentoEmitidoLista lPrueba = new ScrDtoDocumentoEmitidoLista();
			lPrueba.setNombre("tabla_b");
			List<Map<String, String>> regPruebas = new ArrayList<>();

			ListaSeguimientoCertificacionSistemasDto pruebas = new ServiciosFacturacionRest()
					.recuperarListaSeguimientoCertificacionSistemas(
							this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getSistemaId(),
							this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getNit());
			RespuestaResumenPruebaCertificacionSistemaDto resumenPruebas = new ServiciosFacturacionRest()
					.obtenerResumenPruebaCertificacionSistema(
							this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getSistemaId());
			vFila = 1;
			if (pruebas.getListaSeguimientoCertificacionSistemasDto() != null) {
				for (SeguimientoCertificacionSistemasDto vPrueba : pruebas
						.getListaSeguimientoCertificacionSistemasDto()) {
					Map<String, String> prueba1 = new HashMap<>();
					prueba1.put(CAMPO_FILA, String.valueOf(vFila));
					prueba1.put(CAMPO_DESCRIPCION, vPrueba.getDescripcion_prueba());
					prueba1.put(CAMPO_ESTADO, vPrueba.getPorcentaje().toString() + " %");
					/*Optional<ResumenPruebaCertificacionSistemaDto> match = resumenPruebas.getLista().stream()
							.filter(item -> (long) item.getEtapaCertificacionSistemasId() == (long) vPrueba
									.getEtapa_certificacion_sistemas_id() && item.isEtapaCompletada())
							.findFirst();
					if (match.isPresent() && match.get().getFechaFinPrueba() != null) {
						prueba1.put(CAMPO_FECHA, vFormateadorFechaNormal.format(match.get().getFechaFinPrueba()));
					} else {
						prueba1.put(CAMPO_FECHA, "");
					}*/

					regPruebas.add(prueba1);
					vFila++;
				}
			}

			// verificar si las listas estan vacias
			if (regComponentes.isEmpty()) {
				Map<String, String> comp1 = new HashMap<>();
				comp1.put(CAMPO_FILA, "");
				comp1.put(CAMPO_NOMBRE_ARCHIVO, "");
				comp1.put(CAMPO_COMPONENTE, "");
				comp1.put(CAMPO_RUTA, "");
				regComponentes.add(comp1);
			}
			if (regPruebas.isEmpty()) {
				Map<String, String> prueba1 = new HashMap<>();
				prueba1.put(CAMPO_FILA, "");
				prueba1.put(CAMPO_DESCRIPCION, "");
				prueba1.put(CAMPO_TIPO, "");
				prueba1.put(CAMPO_ESTADO, "");
				//prueba1.put(CAMPO_FECHA, "");
				regPruebas.add(prueba1);
			}

			lComponente.setRegistros(regComponentes);
			lPrueba.setRegistros(regPruebas);
			listas.add(lComponente);
			listas.add(lPrueba);
			vDocumentoAprobacion.setDocumentoEmitidoListas(listas);

			// lista rechazo
			List<ScrDtoDocumentoEmitidoLista> listas2 = new ArrayList<>();
			ScrDtoDocumentoEmitidoLista lPruebasVerificadas = new ScrDtoDocumentoEmitidoLista();
			lPruebasVerificadas.setNombre("tabla_a");
			List<Map<String, String>> regPruebasVerificadas = new ArrayList<>();

			RespuestaRegistroVerificacionInsituDto pruebasVerificadas = new ServiciosFacturacionRest()
					.obtenerListaVerificacionInsituPorSolicitudCertificacionId(
							this.contextoSolicitudCertificacionSistemaModel.getSolicitud().getSolicitudId());
			int vFila1 = 1;
			if (pruebasVerificadas.getLista() != null) {
				for (RegistroVerificacionInsituDto vPrueba : pruebasVerificadas.getLista()) {
					Map<String, String> prueba1 = new HashMap<>();
					prueba1.put(CAMPO_FILA, String.valueOf(vFila1));
					prueba1.put(CAMPO_DESCRIPCION, vPrueba.getDescripcionPrueba());
					prueba1.put(CAMPO_ESTADO, vPrueba.getEstadoPruebaDescripcion());
					prueba1.put(CAMPO_OBSERVACION, vPrueba.getObservacion() != null ? vPrueba.getObservacion() : "");
					regPruebasVerificadas.add(prueba1);
					vFila1++;
				}
			}

			// verificar si las listas estan vacias
			if (regPruebasVerificadas.isEmpty()) {
				Map<String, String> prueba1 = new HashMap<>();
				prueba1.put(CAMPO_FILA, "");
				prueba1.put(CAMPO_DESCRIPCION, "");
				prueba1.put(CAMPO_ESTADO, "");
				prueba1.put(CAMPO_OBSERVACION, "");
				regPruebasVerificadas.add(prueba1);
			}

			lPruebasVerificadas.setRegistros(regPruebasVerificadas);
			listas2.add(lPruebasVerificadas);
			vDocumentoRechazo.setDocumentoEmitidoListas(listas2);

			lista.add(vDocumentoAprobacion);
			lista.add(vDocumentoRechazo);
			this.getListaEmisionDocumentos().getEmisionDocumento().setListaPersonalizadaDeDocumentoEmitido(lista);
			// Fin Cargar plantillas del proceso
		} catch (Exception e) {
			this.getListaEmisionDocumentos().getEmisionDocumento().setListaPersonalizadaDeDocumentoEmitido(new ArrayList<>());
		}
		this.getListaEmisionDocumentos().getEmisionDocumento().setAdjuntarDocumentoAutomaticamente(true);
		this.getListaEmisionDocumentos().generarDocumento();
	}

	public void obtenerContexto(EstadoDerivacionDto estado) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			estado = (EstadoDerivacionDto) sessionMap.get("EstadoProceso");
			this.estadoDerivacionDto = estado;
		} catch (Exception e) {
			this.estadoDerivacionDto = null;
		}
	}

	public EstadoDerivacionDto getEstadoDerivacionDto() {
		return estadoDerivacionDto;
	}

	public void setEstadoDerivacionDto(EstadoDerivacionDto estadoDerivacionDto) {
		this.estadoDerivacionDto = estadoDerivacionDto;
	}

	public TramiteCertificacionBean getTramiteCertificacionBean() {
		return tramiteCertificacionBean;
	}

	public void setTramiteCertificacionBean(TramiteCertificacionBean tramiteCertificacionBean) {
		this.tramiteCertificacionBean = tramiteCertificacionBean;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}

	@Override
	public void cargarDocumentoProceso() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generarDocumentoProceso() {
		// TODO Auto-generated method stub
		
	}	
}
