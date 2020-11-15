package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.sre.fac.dto.DetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.DetallePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.ListaDetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaDetalleSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasProveedorDto;
import bo.gob.sin.sre.fac.dto.RespuestaActualizacionGenericoDto;
import bo.gob.sin.sre.fac.dto.RespuestaCalcularPorcentajePruebasEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaListaDetallePruebasEtapas2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaMatchLogCasosPruebaEtapa2Dto;
import bo.gob.sin.sre.fac.dto.RespuestaPruebaSugeridaDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasContribuyentesDto;
import bo.gob.sin.sre.fac.dto.SolicitudActualizaPruebaCertificacionSistemaDto;
import bo.gob.sin.sre.fac.dto.SolicitudRegistroProveedorDto;
import bo.gob.sin.sre.fac.service.ServiciosFacturacionRest;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@ManagedBean(name ="seguimientoCertificacionSistemasList")
@ViewScoped
public class SeguimientoCertificacionSistemasList implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<SeguimientoCertificacionSistemasDto> listaSeguimientoPruebasSistemas;
	
	private List<SeguimientoCertificacionSistemasDto> listaSeguimientoPruebasSistemasAuxiliar;
	
	private List<SeguimientoDetalleCertificacionSistemasDto> listaSeguimientoDetallePruebasSistemas;	
	
	private List<DetallePruebasEtapa2Dto> listaDetallePruebasEtapa2;
	
	private ListaSistemasProveedorDto listaSistemasProveedorDto;
	
	private SistemasContribuyentesDto sistemasContribuyentesDto; 
	
	private SeguimientoCertificacionSistemasDto dtoSeguimientoPruebasSistemas;
	
	private DetallePruebasEtapa2Dto detallePruebasEtapa2Dto;
	
	private List<DetalleCasosDePruebaEtapa0Dto> listaCasosPruebaSugeridasEtapa0;
	
	
	private boolean mostrarPanel;
	private boolean mostrarPanelDetalle;
	private Long sistemaId;
	private Integer etapaCertificacionSistemasId;
	private Integer cantidadAgrupador;
	private boolean mostrarTabla;
	@ManagedProperty(value = "#{mensajesBean}")
    private MensajesBean mensajesBean;
	
	@PostConstruct
	void init()
	{
		listaSeguimientoPruebasSistemas = new ArrayList<>();	
		listaSeguimientoPruebasSistemasAuxiliar = new ArrayList<>();
		dtoSeguimientoPruebasSistemas =new SeguimientoCertificacionSistemasDto(); 
		mostrarPanel=true;
		mostrarTabla=true;
		mostrarPanelDetalle=false;
	}
	
	/**
	* @Descipcion: Despliega la lista de sistemas filtrados por contribuyente.
	* @author: junior.flores
	* @Fecha: 03/06/2019
	* @param pIdContribuyente: Representa la llave primaria del contribuyente
	*/
	public void consultaSistemasAsociadosContribuyente(long pIdContribuyente)
	{
		ServiciosFacturacionRest vServiciosFacturacionRest= new ServiciosFacturacionRest();
		SolicitudRegistroProveedorDto vSolicitudRegistroProveedorDto = new SolicitudRegistroProveedorDto();
		vSolicitudRegistroProveedorDto.setContribuyenteId(pIdContribuyente);            
		vSolicitudRegistroProveedorDto.setEstadoSistemaContribuyenteId(ConstFacturacion.ESTADO_SISTEMA_CONTRIBUYENTE_ID_APROBADA);
		listaSistemasProveedorDto = vServiciosFacturacionRest.consultaSistemasAsociadosContribuyente(vSolicitudRegistroProveedorDto);		
		
		if (listaSistemasProveedorDto!=null && !listaSistemasProveedorDto.isOk()) 
		{
			mensajesBean.addMensajes(listaSistemasProveedorDto);
		}
	}
	
	/**
	* @Descipcion: Despliega la lista del seguimiento de sistemas filtrada por el sistema.
	* @author: Peter Flores.
	* @Fecha: 03/06/2019
	* @param sistemaId: Representa la llave primaria del sistema
	* 		 pNit: Número de Identificación Tributaria
	* @return Devuelve el listado de las etapas de la Fase I de implementación
	*/
	public List<SeguimientoCertificacionSistemasDto> obtenerListadoSeguimientoCertificacionSistemas(Long sistemaId, Long pNit)
	{
		ServiciosFacturacionRest serviciosFacturacionRest= new ServiciosFacturacionRest();
		ListaSeguimientoCertificacionSistemasDto vResultado = new ListaSeguimientoCertificacionSistemasDto();
		
		vResultado = serviciosFacturacionRest.recuperarListaSeguimientoCertificacionSistemas(sistemaId, pNit);
		listaSeguimientoPruebasSistemas.clear();
		if (vResultado.isOk()) 
		{
			listaSeguimientoPruebasSistemas = vResultado.getListaSeguimientoCertificacionSistemasDto();					
		} 
		else 
		{
			listaSeguimientoPruebasSistemas = new ArrayList<>();
			mensajesBean.addMensajes(vResultado);
		}					
		
		return listaSeguimientoPruebasSistemas;
	}
	
	/**
	* @Descipcion: Despliega el detalle del seguimiento de sistemas filtrada por el sistema.
	* @author: Peter Flores.
	* @Fecha: 14/06/2019
	* @param sistemaId: Representa la llave primaria del sistema.
	* 		 pEtapaPruebaCertificacionId: Código de la Etapa de Prueba de Certificación de Sistemas.
	*  	     pNit: Número de Identificación Tributaria.
	* @return Devuelve el detalle del listado de las etapas de la Fase I de implementación
	*/
	public void obtenerDetalleListadoSeguimientoCertificacionSistemas(Long sistemaId,Integer pEtapaPruebaCertificacionId,Long pNit)
	{
		ServiciosFacturacionRest serviciosFacturacionRest= new ServiciosFacturacionRest();
		ListaDetalleSeguimientoCertificacionSistemasDto vResultado = new ListaDetalleSeguimientoCertificacionSistemasDto();
		
		vResultado = serviciosFacturacionRest.recuperarListaDetalleSeguimientoCertificacionSistemas(sistemaId, pEtapaPruebaCertificacionId, pNit);
		listaSeguimientoPruebasSistemas.clear();
		if (vResultado.isOk()) 
		{  
			listaSeguimientoDetallePruebasSistemas = vResultado.getListaSeguimientoDetalleCertificacionSistemasDto();			
		} 
		else 
		{
			listaSeguimientoPruebasSistemas = new ArrayList<>();
			mensajesBean.addMensajes(vResultado);
		}					
	}
	
	/**
	* @Descipcion: Inicia o finaliza la etapa de Certificación de Sistemas
	* @author: Peter Flores.
	* @Fecha: 06/06/2019
	* @param pSolicitud: Información necesaria para actualizar la etapa de prueba
	* @return Devuelve un valor booleano que indica la transacción
	*/
	public boolean actualizarPruebaCertificacionSistema(SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud)
	{
		ServiciosFacturacionRest serviciosFacturacionRest= new ServiciosFacturacionRest();
		RespuestaActualizacionGenericoDto vResultado = new RespuestaActualizacionGenericoDto();
		
		vResultado = serviciosFacturacionRest.actualizarPruebaCertificacionSistema(pSolicitud);
		mensajesBean.addMensajes(vResultado);
		
		return vResultado.isOk();
	}
	
	/**
	* @Descipcion: Reinicia la etapa de Certificación de Sistemas
	* @author: Peter Flores.
	* @Fecha: 11/06/2019
	* @param pSolicitud: Código de prueba de solicitud de certifición.
	* @return Devuelve un valor booleano que indica la transacción
	*/
	public boolean reiniciarPruebaCertificacionSistema(SolicitudActualizaPruebaCertificacionSistemaDto pSolicitud)
	{
		ServiciosFacturacionRest serviciosFacturacionRest= new ServiciosFacturacionRest();
		RespuestaActualizacionGenericoDto vResultado = new RespuestaActualizacionGenericoDto();
		
		vResultado = serviciosFacturacionRest.reiniciaPruebaCertificacionSistema(pSolicitud);
		mensajesBean.addMensajes(vResultado);
		
		return vResultado.isOk();
	}

	
	/**
	 * @Descipcion:Identifica los registros LOG y CASOS DE PRUEBAS que sean iguales. Es decir, segun el codigo hash almacenado en tabla LOG y 
	 * CASOS DE PRUEBA, identifica los registro que sean iguales. En caso de ser iguales actualiza los valores de los campos: 
					estado_match=1; 
					caso_prueba_id=Identficador del caso de prueba;
	 * @author: Victor Cruz Gomez. 
	 * @param sistemaId - Codigo del sistema para identificar los registros de la tabla LOG y CASOS DE PRUEBA de la etapa 2.
	 * @param documentoSectorId - Codigo del documento sector.
	 * @return 1 - Ejecucion exitosa  0 - Ocurrio una excepcion.
	 */	
	public int matchLogCasosPruebaEtapa2(long pSistemaId, int pDocumentoSectorId)
	{
		ServiciosFacturacionRest serviciosFacturacionRest= new ServiciosFacturacionRest();
		RespuestaMatchLogCasosPruebaEtapa2Dto vResultado = new RespuestaMatchLogCasosPruebaEtapa2Dto();
		
		vResultado = serviciosFacturacionRest.matchLogCasosPruebaEtapa2(pSistemaId, pDocumentoSectorId);
		mensajesBean.addMensajes(vResultado);
		
		return vResultado.getEstado();
	}

	/**
	 * @Descipcion: Calcula el porcentaje de pruebas realizadas en la etapa 2 por sector.
	 * @author: Victor Cruz Gomez. 
	 * @param sistemaId - Codigo del sistema para identificar los registros de la tabla LOG y CASOS DE PRUEBA de la etapa 2.
	 * @param documentoSectorId - Codigo del documento sector.
	 * @return Porcentaje - Porcentaje de pruebas efectuadas.
	 */
	public float calcularPorcentajePruebasEtapa2(long pSistemaId, int pDocumentoSectorId)
	{
		ServiciosFacturacionRest serviciosFacturacionRest= new ServiciosFacturacionRest();
		RespuestaCalcularPorcentajePruebasEtapa2Dto vResultado = new RespuestaCalcularPorcentajePruebasEtapa2Dto();
		
		vResultado = serviciosFacturacionRest.calcularPorcentajePruebasEtapa2(pSistemaId, pDocumentoSectorId);
		mensajesBean.addMensajes(vResultado);
		
		return vResultado.getPorcentaje();
	}
	
	/**
	 * @Descipcion: Obtiene una lista con todos los casos de prueba de la etapa 2 efectuados por el contribuente.
	 * @author: Victor Cruz Gomez.  
	 * @param sistemaId - Codigo del sistema para identificar los registros de la tabla LOG y CASOS DE PRUEBA de la etapa 2.
	 * @param documentoSectorId - Codigo del documento sector.
	 * @return Detalle de pruebas realizadas en la etapa 2.
	 */
	public List<DetallePruebasEtapa2Dto> obtenerDetallePruebasEtapa2(long pSistemaId)
	{
		ServiciosFacturacionRest serviciosFacturacionRest= new ServiciosFacturacionRest();
		RespuestaListaDetallePruebasEtapas2Dto vResultado = new RespuestaListaDetallePruebasEtapas2Dto();
		
		vResultado = serviciosFacturacionRest.obtenerDetallePruebasEtapa2(pSistemaId);	
		listaDetallePruebasEtapa2=vResultado.getListaDetallePruebas();
		mensajesBean.addMensajes(vResultado);
		
		return listaDetallePruebasEtapa2;
	}
	
	/**
	* @Descipcion: Realiza el registro del log seleccionado en la grilla del Detalle de Certificación de Pruebas.
	* @author: Peter Flores.
	* @Fecha: 08/07/2019
	* @param pSolicitud: Información necesaria para actualizar la etapa de prueba
	* @return Devuelve un valor booleano que indica la transacción
	*/
	public boolean registroPruebaDetalleOpcional(SeguimientoDetalleCertificacionSistemasDto pSolicitud)
	{
		ServiciosFacturacionRest serviciosFacturacionRest= new ServiciosFacturacionRest();
		RespuestaActualizacionGenericoDto vResultado = new RespuestaActualizacionGenericoDto();
		
		vResultado = serviciosFacturacionRest.registroPruebaDetalleOpcional(pSolicitud);
		mensajesBean.addMensajes(vResultado);
		
		return vResultado.isOk();
	}
	
	
	/**
	* @Descipcion: Realiza el registro de un caso de prueba sugerido de la etapa 2.
	* @author: Victor Cruz
	* @Fecha: 18/07/2019
	* @param pSolicitud: Información necesaria para actualizar la etapa de prueba
	* @return Devuelve un valor booleano que indica la transacción
	*/
	public boolean registroCasoPruebaSugeridoEtapa2(DetallePruebasEtapa2Dto pDetallePruebasEtapa2Dto)
	{
		ServiciosFacturacionRest vServiciosFacturacionRest= new ServiciosFacturacionRest();
		RespuestaPruebaSugeridaDto vResultado = new RespuestaPruebaSugeridaDto();
		vResultado=vServiciosFacturacionRest.registrarCasoPruebaSugeridaEtapa2(pDetallePruebasEtapa2Dto);
		mensajesBean.addMensajes(vResultado);	
		
		return vResultado.isOk();
	}	
	
	
	/**
	 * @descripcion Obtiene la lista de pruebas sugeridas de la etapa 0
	 * @author rosario.garcia
	 * @fecha 15/07/2019
	 * @param pSistema
	 * @return
	 */
	public List<DetalleCasosDePruebaEtapa0Dto> obtenerCasosPruebaSugeridosEtapa0(Long pSistema){
		System.out.println("sistemaID:"+pSistema);
		ServiciosFacturacionRest vServicioFacturacionRest = new ServiciosFacturacionRest();
		ListaDetalleCasosDePruebaEtapa0Dto vResultado = new ListaDetalleCasosDePruebaEtapa0Dto();
		vResultado = vServicioFacturacionRest.obtenerCasosPruebaSugeridasEtapa0(pSistema);
		listaCasosPruebaSugeridasEtapa0 = vResultado.getListaDetalleCasosDePruebaEtapa0();
		mensajesBean.addMensajes(vResultado);
		
		return listaCasosPruebaSugeridasEtapa0;
	}
	
	
	public List<SeguimientoDetalleCertificacionSistemasDto> getListaSeguimientoDetallePruebasSistemas() {
		return listaSeguimientoDetallePruebasSistemas;
	}

	public void setListaSeguimientoDetallePruebasSistemas(
			List<SeguimientoDetalleCertificacionSistemasDto> listaSeguimientoDetallePruebasSistemas) {
		this.listaSeguimientoDetallePruebasSistemas = listaSeguimientoDetallePruebasSistemas;
	}

	public List<SeguimientoCertificacionSistemasDto> getlistaSeguimientoPruebasSistemas() {
		return listaSeguimientoPruebasSistemas;
	}

	public void setlistaSeguimientoPruebasSistemas(List<SeguimientoCertificacionSistemasDto> listaSeguimientoPruebasSistemas) {
		this.listaSeguimientoPruebasSistemas = listaSeguimientoPruebasSistemas;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public ListaSistemasProveedorDto getListaSistemasProveedorDto() {
		return listaSistemasProveedorDto;
	}

	public void setListaSistemasProveedorDto(ListaSistemasProveedorDto listaSistemasProveedorDto) {
		this.listaSistemasProveedorDto = listaSistemasProveedorDto;
	}

	public boolean isMostrarPanel() {
		return mostrarPanel;
	}

	public void setMostrarPanel(boolean mostrarPanel) {
		this.mostrarPanel = mostrarPanel;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public SistemasContribuyentesDto getSistemasContribuyentesDto() {
		return sistemasContribuyentesDto;
	}

	public void setSistemasContribuyentesDto(SistemasContribuyentesDto sistemasContribuyentesDto) {
		this.sistemasContribuyentesDto = sistemasContribuyentesDto;
	}

	public boolean isMostrarPanelDetalle() {
		return mostrarPanelDetalle;
	}

	public void setMostrarPanelDetalle(boolean mostrarPanelDetalle) {
		this.mostrarPanelDetalle = mostrarPanelDetalle;
	}

	public Integer getEtapaCertificacionSistemasId() {
		return etapaCertificacionSistemasId;
	}

	public void setEtapaCertificacionSistemasId(Integer etapaCertificacionSistemasId) {
		this.etapaCertificacionSistemasId = etapaCertificacionSistemasId;
	}

	public List<DetallePruebasEtapa2Dto> getListaDetallePruebasEtapa2() {
		return listaDetallePruebasEtapa2;
	}

	public void setListaDetallePruebasEtapa2(List<DetallePruebasEtapa2Dto> listaDetallePruebasEtapa2) {
		this.listaDetallePruebasEtapa2 = listaDetallePruebasEtapa2;
	}		
	
		public SeguimientoCertificacionSistemasDto getDtoSeguimientoPruebasSistemas() {
		return dtoSeguimientoPruebasSistemas;
	}

	public void setDtoSeguimientoPruebasSistemas(SeguimientoCertificacionSistemasDto dtoSeguimientoPruebasSistemas) {
		this.dtoSeguimientoPruebasSistemas = dtoSeguimientoPruebasSistemas;
	}

	public List<DetalleCasosDePruebaEtapa0Dto> getListaCasosPruebaSugeridasEtapa0() {
		return listaCasosPruebaSugeridasEtapa0;
	}

	public void setListaCasosPruebaSugeridasEtapa0(List<DetalleCasosDePruebaEtapa0Dto> listaCasosPruebaSugeridasEtapa0) {
		this.listaCasosPruebaSugeridasEtapa0 = listaCasosPruebaSugeridasEtapa0;
	}

	public DetallePruebasEtapa2Dto getDetallePruebasEtapa2Dto() {
		return detallePruebasEtapa2Dto;
	}

	public void setDetallePruebasEtapa2Dto(DetallePruebasEtapa2Dto detallePruebasEtapa2Dto) {
		this.detallePruebasEtapa2Dto = detallePruebasEtapa2Dto;
	}
	
	public Integer getCantidadAgrupador() {
		return cantidadAgrupador;
	}

	public void setCantidadAgrupador(Integer cantidadAgrupador) {
		this.cantidadAgrupador = cantidadAgrupador;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	/**
	 * @return the listaSeguimientoPruebasSistemas
	 */
	public List<SeguimientoCertificacionSistemasDto> getListaSeguimientoPruebasSistemas() {
		return listaSeguimientoPruebasSistemas;
	}

	/**
	 * @param listaSeguimientoPruebasSistemas the listaSeguimientoPruebasSistemas to set
	 */
	public void setListaSeguimientoPruebasSistemas(
			List<SeguimientoCertificacionSistemasDto> listaSeguimientoPruebasSistemas) {
		this.listaSeguimientoPruebasSistemas = listaSeguimientoPruebasSistemas;
	}

	/**
	 * @return the listaSeguimientoPruebasSistemasAuxiliar
	 */
	public List<SeguimientoCertificacionSistemasDto> getListaSeguimientoPruebasSistemasAuxiliar() {
		return listaSeguimientoPruebasSistemasAuxiliar;
	}

	/**
	 * @param listaSeguimientoPruebasSistemasAuxiliar the listaSeguimientoPruebasSistemasAuxiliar to set
	 */
	public void setListaSeguimientoPruebasSistemasAuxiliar(
			List<SeguimientoCertificacionSistemasDto> listaSeguimientoPruebasSistemasAuxiliar) {
		this.listaSeguimientoPruebasSistemasAuxiliar = listaSeguimientoPruebasSistemasAuxiliar;
	}
}
