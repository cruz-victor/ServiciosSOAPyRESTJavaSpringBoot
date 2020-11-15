package bo.gob.sin.sre.fac.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.modelmapper.ModelMapper;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.MeterGaugeChartModel;

import bo.gob.sin.sre.fac.dto.DetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.DetallePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.ListaDetalleSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.model.ContextoSolicitudCertificacionSistemaModel;
import bo.gob.sin.sre.fac.model.ContextoUsuarioModel;
import bo.gob.sin.sre.fac.model.SeguimientoCertificacionSistemasList;
import bo.gob.sin.sre.fac.reports.ReportesController;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import net.sf.jasperreports.engine.JRException;
import javax.faces.event.ActionEvent;

/**
 * @Descripcion: Muestra el listado de los sistemas asociados al proveedor.
 *               Adicionalmente el listado de las etapas de la fase I de
 *               implementación
 * @author: junior.flores
 * @fecha: 03/06/2019.
 */

@ManagedBean(name = "seguimientoCertificacionSistemasController")
@RequestScoped
public class SeguimientoCertificacionSistmasController implements Serializable 
{
	@ManagedProperty(value = "#{contextoSolicitudCertificacionSistemaModel}")
	ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel;
	
	@ManagedProperty(value = "#{seguimientoCertificacionSistemasList}")
	private SeguimientoCertificacionSistemasList seguimientoCertificacionSistemasList;		
	
	@ManagedProperty(value = "#{contextoUsuarioModel}")
	ContextoUsuarioModel contextoUsuarioModel;

	private static final long serialVersionUID = 1L;
	
	private MeterGaugeChartModel meterGaugeModel;

	@ManagedProperty(value = "#{reportesController}")
	ReportesController reportesController;

	public void Load() 
	{
		this.getSeguimientoCertificacionSistemasList().consultaSistemasAsociadosContribuyente(this.getContextoUsuarioModel().getContribuyente().getIfc());
		getSeguimientoCertificacionSistemasList().setMostrarPanel(true);
	}
	
	public void Load2(long pIFC) 
	{
		this.getSeguimientoCertificacionSistemasList().consultaSistemasAsociadosContribuyente(pIFC);
		getSeguimientoCertificacionSistemasList().setMostrarPanel(true);
	}

	private MeterGaugeChartModel initMeterGaugeModel(int pValue) 
	{
		List<Number> intervals = new ArrayList<Number>() {
			{
				add(10);
				add(25);
				add(60);
				add(100);
			}
		};

		return new MeterGaugeChartModel(pValue, intervals);
	}

	public MeterGaugeChartModel getMeterGaugeModel() 
	{
		return meterGaugeModel;
	}

	public MeterGaugeChartModel setMeterGaugeModel(int p) 
	{
		meterGaugeModel = initMeterGaugeModel(p);
		meterGaugeModel.setSeriesColors("ECECED,ECECED,ECECED,ECECED");
		meterGaugeModel.setGaugeLabelPosition("bottom");
		meterGaugeModel.setMin(0);
		meterGaugeModel.setMax(100);
		meterGaugeModel.setShowTickLabels(true);
		return meterGaugeModel;
	}

	/**
	 * @Descipcion: Devuelve el detalle de los sistemas asociados al contribuyente.
	 * @author: junior.flores.
	 * @Fecha: 12/06/2019
	 * @param pPruebaCertificacionSistemaId: Código unico de la tabla Prueba
	 *        Certificación Sistemas. pEtapaCertificacionSistemaId: Código unico de
	 *        la tabla Etapa Certificación Sistemas.
	 */
	public void verDetalleSistema(SistemasContribuyentesDto pSistemasContribuyentesDto)	throws IOException, JRException, ParseException 
	{
		getSeguimientoCertificacionSistemasList().setMostrarPanel(false);
		getSeguimientoCertificacionSistemasList().obtenerListadoSeguimientoCertificacionSistemas(pSistemasContribuyentesDto.getSistemaId(), this.getContextoUsuarioModel().getContribuyente().getNit());
		getSeguimientoCertificacionSistemasList().setSistemasContribuyentesDto(pSistemasContribuyentesDto);
		getSeguimientoCertificacionSistemasList().setSistemaId(pSistemasContribuyentesDto.getSistemaId());		

	}

	/**
	 * @Descipcion: Actualiza el estado de las etapas de Certificación de Sistemas.
	 * @author: junior.flores.
	 * @Fecha: 13/06/2019
	 * @param pPruebaCertificacionSistemaId: Código unico de la tabla Prueba
	 *        Certificación Sistemas. pEtapaCertificacionSistemaId: Código unico de
	 *        la tabla Etapa Certificación Sistemas.
	 */
	public void actualizarPruebaCertificacionSistema(Long pPruebaCertificacionSistemaId, Long pEtapaCertificacionSistemaId) 
	{
		SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud = new SolicitudActualizaPruebaCertificacionSistemaDto();
		pSolicitud.setPruebaCertificacionSistemaId(pPruebaCertificacionSistemaId);
		pSolicitud.setEtapaCertificacionSistemaId(pEtapaCertificacionSistemaId);
		pSolicitud.setInicioPrueba(pPruebaCertificacionSistemaId == null ? true : false);
		pSolicitud.setContribuyenteId(this.getContextoUsuarioModel().getContribuyente().getIfc());
		pSolicitud.setModalidadFacturacion(getSeguimientoCertificacionSistemasList().getSistemasContribuyentesDto().getModalidadFacturacion());
		pSolicitud.setSistemaId(getSeguimientoCertificacionSistemasList().getSistemaId());
		
		if (getSeguimientoCertificacionSistemasList().actualizarPruebaCertificacionSistema(pSolicitud))
		{
			getSeguimientoCertificacionSistemasList().obtenerListadoSeguimientoCertificacionSistemas(getSeguimientoCertificacionSistemasList().getSistemaId(), this.getContextoUsuarioModel().getContribuyente().getNit());
		}
	}

	/**
	 * @Descipcion: Reinicia el estado de las etapas de Certificación de Sistemas.
	 * @author: junior.flores.
	 * @Fecha: 14/06/2019
	 * @param pPruebaCertificacionSistemaId: Código unico de la tabla Prueba
	 *        Certificación Sistemas.
	 */
	public void reiniciarPruebaCertificacionSistema(Long pPruebaCertificacionSistemaId) 
	{
		SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud = new SolicitudActualizaPruebaCertificacionSistemaDto();
		pSolicitud.setPruebaCertificacionSistemaId(pPruebaCertificacionSistemaId);
		
		if (getSeguimientoCertificacionSistemasList().reiniciarPruebaCertificacionSistema(pSolicitud))
		{
			getSeguimientoCertificacionSistemasList().obtenerListadoSeguimientoCertificacionSistemas(getSeguimientoCertificacionSistemasList().getSistemaId(), this.getContextoUsuarioModel().getContribuyente().getNit());
		}
	}

	/**
	 * @Descipcion: Devuelve el reporte de la etapa seleccionada
	 * @author: junior.flores.
	 * @Fecha: 17/06/2019
	 * @param pEtapaCertificacionSistemaId: Código unico de la tabla Etapa
	 *        Certificación Sistemas.
	 */
	public StreamedContent ReporteSeguimientoSolicitudSistemas() 
	{
		StreamedContent salida = null;
		/*
		 * try { salida =
		 * this.getReportesController().ReporteSeguimientoCertiticacionSistemas(
		 * getSeguimientoCertificacionSistemasList().getSistemasContribuyentesDto(),
		 * getSeguimientoCertificacionSistemasList().
		 * getListaSeguimientoDetallePruebasSistemas(),
		 * getSeguimientoCertificacionSistemasList().getDtoSeguimientoPruebasSistemas())
		 * ; RequestContext.getCurrentInstance().
		 * execute("toastr.success('Reporte Generado.', '')");
		 * 
		 * }catch (Exception e) { // TODO: handle exception }
		 */
		return salida;
	}

	/**
	 * @Descipcion: Muestra el detalle de las Etapas de Certificación de Sistmas
	 * @author: junior.flores.
	 * @Fecha: 18/06/2019
	 * @param pSistemasContribuyentesDto: Objento con la información del
	 *        contribuyente
	 */
	public void verDetalleEtapa(SeguimientoCertificacionSistemasDto pSeguimientoCertificacionSistemasDto)throws IOException, JRException, ParseException 
	{
		getSeguimientoCertificacionSistemasList().setMostrarPanelDetalle(true);
		getSeguimientoCertificacionSistemasList().setDtoSeguimientoPruebasSistemas(pSeguimientoCertificacionSistemasDto);
		getSeguimientoCertificacionSistemasList().obtenerDetalleListadoSeguimientoCertificacionSistemas(getSeguimientoCertificacionSistemasList().getSistemaId(),pSeguimientoCertificacionSistemasDto.getEtapa_certificacion_sistemas_id(),pSeguimientoCertificacionSistemasDto.getEtapa_certificacion_sistemas_id()!=3052?this.getContextoUsuarioModel().getContribuyente().getNit():0);
		
		if (getSeguimientoCertificacionSistemasList().getListaSeguimientoDetallePruebasSistemas()!=null && pSeguimientoCertificacionSistemasDto.getEtapa_certificacion_sistemas_id() == 2853)
		{
			getSeguimientoCertificacionSistemasList().setCantidadAgrupador( getSeguimientoCertificacionSistemasList().getListaSeguimientoDetallePruebasSistemas().size() / (getSeguimientoCertificacionSistemasList().getListaSeguimientoDetallePruebasSistemas().stream().collect(Collectors.groupingBy(SeguimientoDetalleCertificacionSistemasDto::getAgrupador)).size()==0 ? 1 : getSeguimientoCertificacionSistemasList().getListaSeguimientoDetallePruebasSistemas().stream().collect(Collectors.groupingBy(SeguimientoDetalleCertificacionSistemasDto::getAgrupador)).size()));
		}
		else if (pSeguimientoCertificacionSistemasDto.getEtapa_certificacion_sistemas_id() == 2852)
			this.getSeguimientoCertificacionSistemasList().obtenerDetallePruebasEtapa2(getSeguimientoCertificacionSistemasList().getSistemaId());
		else if (pSeguimientoCertificacionSistemasDto.getEtapa_certificacion_sistemas_id() == 2850)
			this.getSeguimientoCertificacionSistemasList().obtenerCasosPruebaSugeridosEtapa0(getSeguimientoCertificacionSistemasList().getSistemaId());
	}

	/**
	 * @Descipcion: Registra el log por cada etapa de las pruebas sugeridas.
	 * @author: junior.flores.
	 * @Fecha: 08/07/2019
	 * @param pSistemasContribuyentesDto: Objento con la información del detalle de
	 *        las pruebas de certificación de sistemas.
	 */
	public void registroPruebaDetalleOpcional(SeguimientoDetalleCertificacionSistemasDto pSeguimientoDetalleCertificacionSistemasDto) throws IOException, JRException, ParseException 
	{
		pSeguimientoDetalleCertificacionSistemasDto.setEtapaPrueba(getSeguimientoCertificacionSistemasList().getDtoSeguimientoPruebasSistemas().getEtapa_certificacion_sistemas_id());		
		pSeguimientoDetalleCertificacionSistemasDto.setSistemaId(getSeguimientoCertificacionSistemasList().getSistemaId());
		
		if (getSeguimientoCertificacionSistemasList().registroPruebaDetalleOpcional(pSeguimientoDetalleCertificacionSistemasDto))
		{
			pSeguimientoDetalleCertificacionSistemasDto.setSatisfactorio(true);
			pSeguimientoDetalleCertificacionSistemasDto.setEstado_match(1);
			pSeguimientoDetalleCertificacionSistemasDto.setPorcentaje_avance(100);
		}
	}

	public void Cancelar(ActionEvent a) 
	{
		try 
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("SeguimientoCertificacionSistemas.xhtml");
		} 
		catch (Exception e2) 
		{
			RequestContext.getCurrentInstance().execute("toastr.error('error al cambiar de p�gina', '')");
		}
	}

	/**
	 * @descripcion permite realizar el registro de la prueba sugerida
	 * @author rosario.garcia
	 * @fecha 11/07/2019
	 * @param pDetalleCasosDePruebaEtapa0Dto pSistemaId
	 */
	public void registrarPruebaSugeridaEtapa0(DetalleCasosDePruebaEtapa0Dto pDetalleCasosDePruebaEtapa0Dto, Long pSistemaId) throws IOException, JRException, ParseException 
	{		
		SeguimientoDetalleCertificacionSistemasDto pSeguimientoDetalle = new SeguimientoDetalleCertificacionSistemasDto();
		ModelMapper vMapper = new ModelMapper();
		pSeguimientoDetalle = vMapper.map(pDetalleCasosDePruebaEtapa0Dto, SeguimientoDetalleCertificacionSistemasDto.class);
		pSeguimientoDetalle.setEtapaPrueba(2850);
		pSeguimientoDetalle.setSistemaId(pSistemaId);
		
		if (getSeguimientoCertificacionSistemasList().registroPruebaDetalleOpcional(pSeguimientoDetalle)) 
		{
			pDetalleCasosDePruebaEtapa0Dto.setEs_caso_prueba_registrada(true);
		}
	}

	public void volver() throws IOException, JRException, ParseException 
	{		
			getSeguimientoCertificacionSistemasList().obtenerListadoSeguimientoCertificacionSistemas(getSeguimientoCertificacionSistemasList().getSistemaId(), this.getContextoUsuarioModel().getContribuyente().getNit());
			getSeguimientoCertificacionSistemasList().setMostrarPanelDetalle(false);
			getSeguimientoCertificacionSistemasList().setMostrarTabla(true);
	}
	
	public void volverProveedor() throws IOException, JRException, ParseException 
	{
		getSeguimientoCertificacionSistemasList().obtenerDetalleListadoSeguimientoCertificacionSistemas(getSeguimientoCertificacionSistemasList().getSistemaId(),getSeguimientoCertificacionSistemasList().getDtoSeguimientoPruebasSistemas().getEtapa_certificacion_sistemas_id(),0L);
		getSeguimientoCertificacionSistemasList().setMostrarTabla(true);
	}

	/**
	 * @Descipcion: Registra una prueba sugerida de la etapa 2.
	 * @author: victor.cruz
	 * @Fecha: 18/07/2019
	 */
	public void registroCasoPruebaSugeridoEtapa2() 
	{		
		getSeguimientoCertificacionSistemasList().getDetallePruebasEtapa2Dto().setChecado(true);		
		if(this.getSeguimientoCertificacionSistemasList().registroCasoPruebaSugeridoEtapa2(getSeguimientoCertificacionSistemasList().getDetallePruebasEtapa2Dto()))
		{
			getSeguimientoCertificacionSistemasList().getDetallePruebasEtapa2Dto().setPorcentajecasoprueba(100);
			getSeguimientoCertificacionSistemasList().getDetallePruebasEtapa2Dto().setEstadomatch(1);					
		}
		
		RequestContext.getCurrentInstance().execute("toastr.success('Se registro los datos correctamente .', '')"); 
	}

	/**
	 * @Descipcion: Selecciona un caso de prueba de la etapa 2.
	 * @author: victor.cruz
	 * @Fecha: 18/07/2019
	 * @param pSistemasContribuyentesDto: Objento con la información del detalle de
	 *        las pruebas de certificación de sistemas.
	 */
	public void seleccionarCasoPrueba(DetallePruebasEtapa2Dto pDetallePruebasEtapa2Dto) 
	{		
		getSeguimientoCertificacionSistemasList().setDetallePruebasEtapa2Dto(pDetallePruebasEtapa2Dto);
	}

	/**
	 * @Descipcion: Cierra el dialgo de confirmacion. Al cerrar cambia el estado del check.
	 * @author: victor.cruz
	 * @Fecha: 18/07/2019
	 */
	public void cerrarDialogoCasoPruebaSugerida() 
	{
		getSeguimientoCertificacionSistemasList().getDetallePruebasEtapa2Dto().setChecado(false);
	}
	
	/**
	 * @Descipcion: Devuelve el detalle de los sistemas asociados al proveedor.
	 * @author: junior.flores.
	 * @Fecha: 16/08/2019
	 * 
	 */
	public void verDetalleSistemaProveedor(SeguimientoDetalleCertificacionSistemasDto pSeguimientoDetalleCertificacionSistemasDto)	throws IOException, JRException, ParseException 
	{		
		getSeguimientoCertificacionSistemasList().obtenerDetalleListadoSeguimientoCertificacionSistemas(getSeguimientoCertificacionSistemasList().getSistemaId(),getSeguimientoCertificacionSistemasList().getDtoSeguimientoPruebasSistemas().getEtapa_certificacion_sistemas_id(),pSeguimientoDetalleCertificacionSistemasDto.getNit());
		getSeguimientoCertificacionSistemasList().setMostrarTabla(false);
	}

	public ReportesController getReportesController() {
		return reportesController;
	}

	public void setReportesController(ReportesController reportesController) {
		this.reportesController = reportesController;
	}

	public ContextoUsuarioModel getContextoUsuarioModel() {
		return contextoUsuarioModel;
	}

	public void setContextoUsuarioModel(ContextoUsuarioModel contextoUsuarioModel) {
		this.contextoUsuarioModel = contextoUsuarioModel;
	}
	
	public SeguimientoCertificacionSistemasList getSeguimientoCertificacionSistemasList() {
		return seguimientoCertificacionSistemasList;
	}

	public void setSeguimientoCertificacionSistemasList(SeguimientoCertificacionSistemasList seguimientoCertificacionSistemasList) {
		this.seguimientoCertificacionSistemasList = seguimientoCertificacionSistemasList;
	}

	public ContextoSolicitudCertificacionSistemaModel getContextoSolicitudCertificacionSistemaModel() {
		return contextoSolicitudCertificacionSistemaModel;
	}

	public void setContextoSolicitudCertificacionSistemaModel(
			ContextoSolicitudCertificacionSistemaModel contextoSolicitudCertificacionSistemaModel) {
		this.contextoSolicitudCertificacionSistemaModel = contextoSolicitudCertificacionSistemaModel;
	}
	
}
