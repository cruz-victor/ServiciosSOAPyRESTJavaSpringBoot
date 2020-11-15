package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sre.fac.service.ServiciosParametricaRest;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;
//import bo.gob.sin.transversales.parametricas.dto.ClasificadorDto;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@ManagedBean(name ="certificacionParametrics")
@ViewScoped
public class CertificacionParametrics implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private List<ClasificadorDto> listaTipoSistema;
	private List<ClasificadorDto> listaModalidadFacturacion;
	private List<ClasificadorDto> listaCertificacionModalidadFacturacion;
	private List<ClasificadorDto> listaOficinas;
	private List<ClasificadorDto> listaTipoModulos;
	private List<ClasificadorDto> listaTipoDocumentoFactura;
	
	private List<ClasificadorDto> listaTipoInactivacion;
	private List<ClasificadorDto> listaEstadoFactura;
	private List<ClasificadorDto> listaOficinasFiltrada;
	private List<ClasificadorDto> listaTipoDocumentoFiscal;
	private List<ClasificadorDto> listaTipoEnvio;
	private List<ClasificadorDto> listaComponentes;
	private List<ClasificadorDto> listaTipoDocumentoIdentidad;
	
	private List<ClasificadorDto> listaMotivoAnulacion;
	private List<ClasificadorDto> listaEstadoImprenta;
	private List<ClasificadorDto> listaDepartamentos;
	private List<ClasificadorDto> listaEstadoPruebas;
	private List<ClasificadorDto> listaTipoImpresionImprenta;
	
	private List<ClasificadorDto> listaEstadoSolicitudImpresion;
	private ServiciosParametricaRest vServiciosParametricaRest;
	
	private List<ClasificadorDto> listaTipoPrueba;
	
	private List<ClasificadorDto> listaMotivoSolicitud;
	
	private List<ClasificadorDto> listaTipoFacturaManual;
	private List<ClasificadorDto> listaPaisOrigen;
	private List<ClasificadorDto> listaTipoMoneda;
	private List<ClasificadorDto> listaTipoNotaFiscal;
	private List<ClasificadorDto> listaComponentesMinimos;
	private List<ClasificadorDto> listaTipoResultadoCertificacion;
	private List<ClasificadorDto> listaArchivoExtensionValido;
	private List<ClasificadorDto> listaModalidadServicio;
	
	private List<ClasificadorDto> listaTipoPuntoVenta;
	private List<ClasificadorDto> listaTipoOperacion;
	
	private List<ClasificadorDto> listaCertificacionModalidadFacturacionGrupo;
	
	@PostConstruct
	void init()
	{
		this.listaTipoSistema = new ArrayList<>();
		this.listaModalidadFacturacion = new ArrayList<>();
		this.listaCertificacionModalidadFacturacion = new ArrayList<>(); 
		this.listaOficinas = new ArrayList<>();
		this.listaTipoModulos = new ArrayList<>();
		this.listaTipoDocumentoFactura = new ArrayList<>(); 
			
		this.listaTipoInactivacion = new ArrayList<>();
		this.listaTipoDocumentoFiscal= new ArrayList<>();
		this.listaOficinasFiltrada = new ArrayList<>();
		this.listaEstadoFactura = new ArrayList<>();
		this.listaTipoEnvio = new ArrayList<>();
		this.listaComponentes = new ArrayList<>();
		this.listaTipoDocumentoIdentidad = new ArrayList<>();
		
		this.listaMotivoAnulacion=new ArrayList<>();
		this.listaEstadoImprenta = new ArrayList<>();
		this.listaDepartamentos = new ArrayList<>();
		this.listaEstadoPruebas = new ArrayList<>();
		this.listaTipoImpresionImprenta = new ArrayList<>();
		this.listaEstadoSolicitudImpresion = new ArrayList<>();
		this.listaTipoPrueba = new ArrayList<>();
		this.listaMotivoSolicitud = new ArrayList<>();
		this.listaTipoFacturaManual = new ArrayList<>();
		this.listaPaisOrigen = new ArrayList<>();
		
		vServiciosParametricaRest = new ServiciosParametricaRest();
		this.listaComponentesMinimos = new ArrayList<>();
		this.listaTipoResultadoCertificacion = new ArrayList<>();
		this.listaArchivoExtensionValido = new ArrayList<>();
		this.listaModalidadServicio = new ArrayList<>();
		
		this.listaTipoOperacion = new ArrayList<>();
		this.listaCertificacionModalidadFacturacionGrupo = new ArrayList<>();
	}
	


	public void obtenerCertificacionModalidadFacturacionGrupo()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.recuperarClasificadorPorGrupo(ConstFacturacion.MODALIDAD_CERTIFICACION_ID);		
		
		this.listaCertificacionModalidadFacturacionGrupo = resultado;
	}
	
	public void obtenerTipoEnvio()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();		
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_envio_id");
		
		this.listaTipoEnvio = resultado;
	}
	
	public void obtenerTipoInactivacion()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_inactivacion_id");
		
		this.listaTipoInactivacion = resultado;
	}

	public void obtenerTipoSistema()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_sistema_id");
		
		this.listaTipoSistema = resultado;
	}
	
	public void obtenerModalidadFacturacion()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("modalidad_facturacion_id");
		
		this.listaModalidadFacturacion = resultado;
	}
	
	public void obtenerCertificacionModalidadFacturacion()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("modalidad_facturacion_id");		
		resultado.stream().filter(sistema ->  (sistema.getAbreviatura()).equals("MODCOMP")).findFirst().orElse(null);
		
		this.listaCertificacionModalidadFacturacion = resultado;
	}
	
	public void obtenerOficinas()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_nivel_oficina_id");
		
		this.listaOficinas = resultado;
	}
	
	public void obtenerTipoDocumentoFactura()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_documento_sector_id");
		
		this.listaTipoDocumentoFactura = resultado;
	}
	
	public void obtenerTipoModulos()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_modulo_id");
		
		this.listaTipoModulos = resultado;
	}
	
	public void obtenerEstadoPrueba()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("estado_prueba_id");
		
		this.listaEstadoPruebas = resultado;
	}
	
	public void obtenerListaComponentes()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_componente_id");
		
		this.listaComponentes = resultado;
	}
	
	public void obtenerTipodocumentoIdentidad()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_documento_identidad_id");
		
		this.listaTipoDocumentoIdentidad = resultado;
	}
	
	public void obtenerEstadoFactura()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("estado_factura_id");
		
		this.listaEstadoFactura = resultado;
	}
	
	public void obtenerTipoDocumentoFiscal()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_documento_fiscal_id");
		
		this.listaTipoDocumentoFiscal = resultado;
	}
	
	// FRRA
	public void obtenerMotivoAnulacion() {
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("motivo_anulacion_id");
		
		this.listaMotivoAnulacion = resultado;
	}
		
		// CRSP
	public void obtenerEstadoImprenta() {
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("estado_imprenta_id");
		
		this.listaEstadoImprenta = resultado;
	}
	
	// CRSP
	public void obtenerDepartamentos() {
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_departamento_id");
		
		this.listaDepartamentos = resultado;
	}
	
	public void obtenerTipoImpresionImprenta() {
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_impresion_imprenta_id");
		
		this.listaTipoImpresionImprenta = resultado;		
	}
	
	// CRSP
	public void obtenerEstadoSolicitudImpresion() {
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("estado_solicitud_impresion_id");
		
		this.listaEstadoSolicitudImpresion = resultado;
	}
	
	public void obtenerTipoPrueba() {
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_prueba_id");
		
		this.listaTipoPrueba = resultado;
	}

	/**
	 * @autor edwin.coro
	 * @descripción método que obtiene datos paramétrica tipos de motivos de anulación
	 * @fecha 02/10/2018
	 */
	public void obtenerMotivoSolicitud() {
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("motivo_solicitud_id");

		this.listaMotivoSolicitud = resultado;
	}
	
	/**
	 * @autor edwin.coro
	 * @descripción método que obtiene datos paramétrica tipos factura manual
	 * @fecha 02/10/2018
	 */
	public void obtenerTipoFacturaManual() {	
		this.listaTipoFacturaManual = vServiciosParametricaRest.obtenerParametrica("tipo_factura_id");		
	}
	
	/**
	 * @autor edwin.coro
	 * @descripción método que obtiene datos paramétrica paises
	 * @fecha 02/10/2018
	 */
	public void obtenerPaisOrigen() {	
		this.listaPaisOrigen = vServiciosParametricaRest.obtenerParametrica("pais_origen_id");
	}
	
	/**
	 * @autor edwin.coro
	 * @descripción método que obtiene datos paramétrica tipos monedas
	 * @fecha 02/10/2018
	 */
	public void obtenerTipoMoneda() {	
		this.listaTipoMoneda = vServiciosParametricaRest.obtenerParametrica("tipo_moneda_id");
	}
	
	/**
	 * @autor edwin.coro
	 * @descripción método que obtiene datos paramétrica tipos notas fiscales
	 * @fecha 02/10/2018
	 */
	public void obtenerTipoNotaFiscal() {	
		this.listaTipoNotaFiscal = vServiciosParametricaRest.obtenerParametrica("tipo_nota_fiscal_id");	
	}
	

	/**
	 * @autor wilson.limachi
	 * @descripción método que obtiene datos paramétrica tipo de punto de venta
	 * @fecha 02/10/2018
	 */
	public void obtenerListaTipoPuntoVenta()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_punto_venta_id");
		
		this.listaTipoPuntoVenta = resultado;
	}
	
	/**
	 * @autor wilson.limachi
	 * @descripción método que obtiene tipos de operacion
	 * @fecha 08/10/2018
	 */
	public void obtenerListaTipoOperacion()
	{
		List<ClasificadorDto> resultado = new ArrayList<>();
		resultado = vServiciosParametricaRest.obtenerParametrica("tipo_operacion_pos_id");
		
		this.listaTipoOperacion = resultado;
	}
	
	public List<ClasificadorDto> getListaTipoSistema() 
	{
		return listaTipoSistema;
	}

	public void setListaTipoSistema(List<ClasificadorDto> listaTipoSistema) {
		this.listaTipoSistema = listaTipoSistema;
	}

	public List<ClasificadorDto> getListaModalidadFacturacion() {
		return listaModalidadFacturacion;
	}

	public void setListaModalidadFacturacion(List<ClasificadorDto> listaModalidadFacturacion) {
		this.listaModalidadFacturacion = listaModalidadFacturacion;
	}

	public List<ClasificadorDto> getListaCertificacionModalidadFacturacion() {
		return listaCertificacionModalidadFacturacion;
	}

	public void setListaCertificacionModalidadFacturacion(List<ClasificadorDto> listaCertificacionModalidadFacturacion) {
		this.listaCertificacionModalidadFacturacion = listaCertificacionModalidadFacturacion;
	}

	public List<ClasificadorDto> getListaOficinas() {
		return listaOficinas;
	}

	public void setListaOficinas(List<ClasificadorDto> listaOficinas) {
		this.listaOficinas = listaOficinas;
	}

	public List<ClasificadorDto> getListaTipoModulos() {
		return listaTipoModulos;
	}

	public void setListaTipoModulos(List<ClasificadorDto> listaTipoModulos) {
		this.listaTipoModulos = listaTipoModulos;
	}

	public List<ClasificadorDto> getListaTipoInactivacion() {
		return listaTipoInactivacion;
	}

	public void setListaTipoInactivacion(List<ClasificadorDto> listaTipoInactivacion) {
		this.listaTipoInactivacion = listaTipoInactivacion;
	}

	public List<ClasificadorDto> getListaEstadoFactura() {
		return listaEstadoFactura;
	}

	public void setListaEstadoFactura(List<ClasificadorDto> listaEstadoFactura) {
		this.listaEstadoFactura = listaEstadoFactura;
	}

	public List<ClasificadorDto> getListaOficinasFiltrada() {
		return listaOficinasFiltrada;
	}

	public void setListaOficinasFiltrada(List<ClasificadorDto> listaOficinasFiltrada) {
		this.listaOficinasFiltrada = listaOficinasFiltrada;
	}

	public List<ClasificadorDto> getListaTipoDocumentoFiscal() {
		return listaTipoDocumentoFiscal;
	}

	public void setListaTipoDocumentoFiscal(List<ClasificadorDto> listaTipoDocumentoFiscal) {
		this.listaTipoDocumentoFiscal = listaTipoDocumentoFiscal;
	}

	public List<ClasificadorDto> getListaTipoEnvio() {
		return listaTipoEnvio;
	}

	public void setListaTipoEnvio(List<ClasificadorDto> listaTipoEnvio) {
		this.listaTipoEnvio = listaTipoEnvio;
	}

	public List<ClasificadorDto> getListaComponentes() {
		return listaComponentes;
	}

	public void setListaComponentes(List<ClasificadorDto> listaComponentes) {
		this.listaComponentes = listaComponentes;
	}

	public List<ClasificadorDto> getListaTipoDocumentoIdentidad() {
		return listaTipoDocumentoIdentidad;
	}

	public void setListaTipoDocumentoIdentidad(List<ClasificadorDto> listaTipoDocumentoIdentidad) {
		this.listaTipoDocumentoIdentidad = listaTipoDocumentoIdentidad;
	}
	
	public List<ClasificadorDto> getListaMotivoAnulacion() {				
		return listaMotivoAnulacion;
	}

	public void setListaMotivoAnulacion(List<ClasificadorDto> listaMotivoAnulacion) {
		this.listaMotivoAnulacion = listaMotivoAnulacion;
	}

	public List<ClasificadorDto> getListaEstadoImprenta() {
		return listaEstadoImprenta;
	}

	public void setListaEstadoImprenta(List<ClasificadorDto> listaEstadoImprenta) {
		this.listaEstadoImprenta = listaEstadoImprenta;
	}

	public List<ClasificadorDto> getListaDepartamentos() {		
		return listaDepartamentos;
	}

	public void setListaDepartamentos(List<ClasificadorDto> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}

	public List<ClasificadorDto> getListaEstadoPruebas() {
		return listaEstadoPruebas;
	}

	public void setListaEstadoPruebas(List<ClasificadorDto> listaEstadoPruebas) {
		this.listaEstadoPruebas = listaEstadoPruebas;
	}

	public List<ClasificadorDto> getListaTipoImpresionImprenta() {		
		return listaTipoImpresionImprenta;
	}

	public List<ClasificadorDto> getListaEstadoSolicitudImpresion() {
		return listaEstadoSolicitudImpresion;
	}

	public void setListaEstadoSolicitudImpresion(List<ClasificadorDto> listaEstadoSolicitudImpresion) {
		this.listaEstadoSolicitudImpresion = listaEstadoSolicitudImpresion;
	}

	public List<ClasificadorDto> getListaTipoPrueba() {
		return listaTipoPrueba;
	}

	public void setListaTipoPrueba(List<ClasificadorDto> listaTipoPrueba) {
		this.listaTipoPrueba = listaTipoPrueba;
	}

	public void setListaTipoImpresionImprenta(List<ClasificadorDto> listaTipoImpresionImprenta) {
		this.listaTipoImpresionImprenta = listaTipoImpresionImprenta;
	}

	public List<ClasificadorDto> getListaMotivoSolicitud() {		
		return listaMotivoSolicitud;
	}

	public void setListaMotivoSolicitud(List<ClasificadorDto> listaMotivoSolicitud) {
		this.listaMotivoSolicitud = listaMotivoSolicitud;
	}

	public List<ClasificadorDto> getListaTipoFacturaManual() {				
		return listaTipoFacturaManual;
	}
	
	/**
	 * @autor Peter Flores
	 * @descripción método que obtiene la información del tipo de resultado de certificación.
	 * @fecha 13/11/2018
	 */
	public void obtenerResultadoCertificacion() {
		
		listaTipoResultadoCertificacion = vServiciosParametricaRest.obtenerParametrica("tipo_resultado_certificacion_id");				
	}
	
	/**
	 * @autor Peter Flores
	 * @descripción método que obtiene la información de los componentes mínimos.
	 * @fecha 13/11/2018
	 */
	public void obtenerComponentesMinimos() {
		
		listaComponentesMinimos=vServiciosParametricaRest.obtenerParametrica("tipo_componente_id");	
	}
	
	/**
	 * @autor Peter Flores
	 * @descripción método que obtiene la información las extensiones de los archivos válidos
	 * @fecha 13/11/2018
	 */
	public void obtenerArchivoExtensionValido() {
		
		listaArchivoExtensionValido=vServiciosParametricaRest.obtenerParametrica("archivo_extension_valido");	
	}


	public void setListaTipoFacturaManual(List<ClasificadorDto> listaTipoFacturaManual) {
		this.listaTipoFacturaManual = listaTipoFacturaManual;
	}

	public List<ClasificadorDto> getListaPaisOrigen() {
		return listaPaisOrigen;
	}

	public void setListaPaisOrigen(List<ClasificadorDto> listaPaisOrigen) {
		this.listaPaisOrigen = listaPaisOrigen;
	}

	public List<ClasificadorDto> getListaTipoMoneda() {
		return listaTipoMoneda;
	}

	public List<ClasificadorDto> getListaTipoNotaFiscal() {
		return listaTipoNotaFiscal;
	}

	public void setListaTipoMoneda(List<ClasificadorDto> listaTipoMoneda) {
		this.listaTipoMoneda = listaTipoMoneda;
	}

	public void setListaTipoNotaFiscal(List<ClasificadorDto> listaTipoNotaFiscal) {
		this.listaTipoNotaFiscal = listaTipoNotaFiscal;
	}

	public List<ClasificadorDto> getListaComponentesMinimos() {		
		return listaComponentesMinimos ;
	}

	public void setListaComponentesMinimos(List<ClasificadorDto> listaComponentesMinimos) {
		this.listaComponentesMinimos = listaComponentesMinimos;
	}

	public List<ClasificadorDto> getListaTipoResultadoCertificacion() {
		return listaTipoResultadoCertificacion;
	}

	public void setListaTipoResultadoCertificacion(List<ClasificadorDto> listaTipoResultadoCertificacion) {
		this.listaTipoResultadoCertificacion = listaTipoResultadoCertificacion;
	}

	public List<ClasificadorDto> getListaArchivoExtensionValido() {
		return listaArchivoExtensionValido;
	}

	public void setListaArchivoExtensionValido(List<ClasificadorDto> listaArchivoExtensionValido) {
		this.listaArchivoExtensionValido = listaArchivoExtensionValido;
	}

	public List<ClasificadorDto> getListaTipoDocumentoFactura() {
		return listaTipoDocumentoFactura;
	}

	public void setListaTipoDocumentoFactura(List<ClasificadorDto> listaTipoDocumentoFactura) {
		this.listaTipoDocumentoFactura = listaTipoDocumentoFactura;
	}
	
	public List<ClasificadorDto> getListaModalidadServicio() {
		return listaModalidadServicio;
	}

	public void setListaModalidadServicio(List<ClasificadorDto> listaModalidadServicio) {
		this.listaModalidadServicio = listaModalidadServicio;
	}

	/**
	 * @autor Peter Flores
	 * @descripción método que obtiene la información de las modalidades de servicio.
	 * @fecha 7/12/2018
	 */
	public void obtenerModalidadServicio() {
		
		listaModalidadServicio = vServiciosParametricaRest.obtenerParametrica("modalidad_servicio_id");				
	}

	public List<ClasificadorDto> getListaTipoPuntoVenta() {
		return listaTipoPuntoVenta;
	}

	public void setListaTipoPuntoVenta(List<ClasificadorDto> listaTipoPuntoVenta) {
		this.listaTipoPuntoVenta = listaTipoPuntoVenta;
	}

	public List<ClasificadorDto> getListaTipoOperacion() {
		return listaTipoOperacion;
	}

	public void setListaTipoOperacion(List<ClasificadorDto> listaTipoOperacion) {
		this.listaTipoOperacion = listaTipoOperacion;
	}
	
	public List<ClasificadorDto> getListaCertificacionModalidadFacturacionGrupo() {
		return listaCertificacionModalidadFacturacionGrupo;
	}

	public void setListaCertificacionModalidadFacturacionGrupo(
			List<ClasificadorDto> listaCertificacionModalidadFacturacionGrupo) {
		this.listaCertificacionModalidadFacturacionGrupo = listaCertificacionModalidadFacturacionGrupo;
	}
}
