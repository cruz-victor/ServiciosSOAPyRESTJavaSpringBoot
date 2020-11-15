package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.sql.Date;


public class SeguimientoDetalleCertificacionSistemasDto  implements Serializable
{
	private static final long serialVersionUID = 1L;

	
	//ATRIBUTOS ETAPA 7 
	private String nombre_archivo_xml;
	private String nombre_archivo_xml_firmado;
	private String nombre_archivo_xml_firmado_base64;
	private String nombre_archivo_xml_firmado_base64_envio;
	private String fecha_registro;
	private String fecha_inicio_prueba;
	private String fecha_fin_prueba;
	private Integer porcentaje_avance;
	private boolean satisfactorio;
	private String descripcion_sector;
	
	//ATRIBUTOS ETAPA 5
	private Integer caso_prueba_id;
	private String tipo_evento;
	private String motivo_evento;
	private String descripcion_prueba_evento;
	private String procedimiento_evento;
	private String parametro_evento;
	private String resultado_evento;
	private String accion_evento;
	
	
	//ATRIBUTOS ETAPA 0
	private Long nit;
	private String fecha_emision;
	private int sucursal;
	private int modalidad;
	private int emision;
	private int documento_fiscal;
	private int sector;
	private String numero_factura;
	private Integer punto_venta;
	private Long cantidad_casos_esperados; 
	private Long cantidad_casos_correctos;
	private Long cantidad_casos_incorrectos;
	private Date fecha_de_prueba;
	private boolean es_caso_prueba_terminada;
	private String fecha_prueba;
	private String hash;
	private String hash_descripcion;
	private int opcional;
	
	//ATRIBUTOS GENERALES
	private Integer etapaPrueba;
	private Long sistemaId;
	
	// ATRIBUTOS ETAPA CUFD
	private String codigo_sistema;
	private String cuis;
	private String nit_requerimiento;
	private String codigo_punto_venta;
	private String resultado_esperado;
	private String modalidad_requerimiento;
	private String sucursal_requerimiento;
	private String tipo_prueba;
	private String solucion;
	
	//ATRIBUTOS ETAPA 4
	private String codigo_ambiente;
	private String tipo_validacion;
	private String descripcion_prueba;
	private String agrupador;
	
	//ATRIBUTOS ETAPA 3
	private String requisito_prueba;
	private String codigo_autorizacion;
	
	//ATRIBUTOS ETAPA 8	  
	private long sistema_id;
	private int estado_match; 
	private String nro; 
	private String direccion;
	private boolean checado; 
	
	//ATRIBUTOS ETAPA 9
	private String valor_parametro;
	private String cuf;
	private String parametros_importantes;
	
	//ATRIBUTOS ETAPA 6
	private String codigo_emision;
	private String codigo_modalidad;	
	private String cufd;	
	private String documento_fiscal_paquete;
	private String documento_sector;	
	private String fecha_envio; 
	private String archivo;
	private String documento_sector_paquete;	
	private String cantidad_documentos_fiscales;
	private String evento_significativo_registrado;
	private String resultado_esperado_paquete;	
		
	//ATRIBUTOS ETAPA 11
	private String numero_documento_fiscal;
	
	//ATRIBUTOS ETAPA 12
	private String nombre_sistema;
	private String version;
	
	public long getSistema_id() {
		return sistema_id;
	}
	public void setSistema_id(long sistema_id) {
		this.sistema_id = sistema_id;
	}
	public int getEstado_match() {
		return estado_match;
	}
	public void setEstado_match(int estado_match) {
		this.estado_match = estado_match;
	}
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public boolean isChecado() {
		return checado;
	}
	public void setChecado(boolean checado) {
		this.checado = checado;
	}
	public Long getCantidad_casos_esperados() {
		return cantidad_casos_esperados;
	}
	public void setCantidad_casos_esperados(Long cantidad_casos_esperados) {
		this.cantidad_casos_esperados = cantidad_casos_esperados;
	}
	public String getNombre_archivo_xml() {
		return nombre_archivo_xml;
	}
	public void setNombre_archivo_xml(String nombre_archivo_xml) {
		this.nombre_archivo_xml = nombre_archivo_xml;
	}
	public String getNombre_archivo_xml_firmado() {
		return nombre_archivo_xml_firmado;
	}
	public void setNombre_archivo_xml_firmado(String nombre_archivo_xml_firmado) {
		this.nombre_archivo_xml_firmado = nombre_archivo_xml_firmado;
	}
	public String getNombre_archivo_xml_firmado_base64() {
		return nombre_archivo_xml_firmado_base64;
	}
	public void setNombre_archivo_xml_firmado_base64(String nombre_archivo_xml_firmado_base64) {
		this.nombre_archivo_xml_firmado_base64 = nombre_archivo_xml_firmado_base64;
	}
	public String getNombre_archivo_xml_firmado_base64_envio() {
		return nombre_archivo_xml_firmado_base64_envio;
	}
	public void setNombre_archivo_xml_firmado_base64_envio(String nombre_archivo_xml_firmado_base64_envio) {
		this.nombre_archivo_xml_firmado_base64_envio = nombre_archivo_xml_firmado_base64_envio;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public boolean isSatisfactorio() {
		return satisfactorio;
	}
	public void setSatisfactorio(boolean satisfactorio) {
		this.satisfactorio = satisfactorio;
	}
	public String getFecha_inicio_prueba() {
		return fecha_inicio_prueba;
	}
	public void setFecha_inicio_prueba(String fecha_inicio_prueba) {
		this.fecha_inicio_prueba = fecha_inicio_prueba;
	}
	public String getFecha_fin_prueba() {
		return fecha_fin_prueba;
	}
	public void setFecha_fin_prueba(String fecha_fin_prueba) {
		this.fecha_fin_prueba = fecha_fin_prueba;
	}
	public Integer getCaso_prueba_id() {
		return caso_prueba_id;
	}
	public void setCaso_prueba_id(Integer caso_prueba_id) {
		this.caso_prueba_id = caso_prueba_id;
	}
	public String getTipo_evento() {
		return tipo_evento;
	}
	public void setTipo_evento(String tipo_evento) {
		this.tipo_evento = tipo_evento;
	}
	public String getMotivo_evento() {
		return motivo_evento;
	}
	public void setMotivo_evento(String motivo_evento) {
		this.motivo_evento = motivo_evento;
	}
	public String getProcedimiento_evento() {
		return procedimiento_evento;
	}
	public void setProcedimiento_evento(String procedimiento_evento) {
		this.procedimiento_evento = procedimiento_evento;
	}
	public String getParametro_evento() {
		return parametro_evento;
	}
	public void setParametro_evento(String parametro_evento) {
		this.parametro_evento = parametro_evento;
	}
	public String getResultado_evento() {
		return resultado_evento;
	}
	public void setResultado_evento(String resultado_evento) {
		this.resultado_evento = resultado_evento;
	}
	public String getAccion_evento() {
		return accion_evento;
	}
	public void setAccion_evento(String accion_evento) {
		this.accion_evento = accion_evento;
	}
	public String getDescripcion_prueba_evento() {
		return descripcion_prueba_evento;
	}
	public void setDescripcion_prueba_evento(String descripcion_prueba_evento) {
		this.descripcion_prueba_evento = descripcion_prueba_evento;
	}
	public Long getNit() {
		return nit;
	}
	public void setNit(Long nit) {
		this.nit = nit;
	}
	public String getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public int getSucursal() {
		return sucursal;
	}
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	public int getModalidad() {
		return modalidad;
	}
	public void setModalidad(int modalidad) {
		this.modalidad = modalidad;
	}
	public int getEmision() {
		return emision;
	}
	public void setEmision(int emision) {
		this.emision = emision;
	}
	public int getDocumento_fiscal() {
		return documento_fiscal;
	}
	public void setDocumento_fiscal(int documento_fiscal) {
		this.documento_fiscal = documento_fiscal;
	}
	public int getSector() {
		return sector;
	}
	public void setSector(int sector) {
		this.sector = sector;
	}
	public String getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}
	public Integer getPunto_venta() {
		return punto_venta;
	}
	public void setPunto_venta(Integer punto_venta) {
		this.punto_venta = punto_venta;
	}
	public Long getCantidad_casos_correctos() {
		return cantidad_casos_correctos;
	}
	public void setCantidad_casos_correctos(Long cantidad_casos_correctos) {
		this.cantidad_casos_correctos = cantidad_casos_correctos;
	}
	public Long getCantidad_casos_incorrectos() {
		return cantidad_casos_incorrectos;
	}
	public void setCantidad_casos_incorrectos(Long cantidad_casos_incorrectos) {
		this.cantidad_casos_incorrectos = cantidad_casos_incorrectos;
	}
	public String getDescripcion_sector() {
		return descripcion_sector;
	}
	public void setDescripcion_sector(String descripcion_sector) {
		this.descripcion_sector = descripcion_sector;
	}
	public Date getFecha_de_prueba() {
		return fecha_de_prueba;
	}
	public void setFecha_de_prueba(Date fecha_de_prueba) {
		this.fecha_de_prueba = fecha_de_prueba;
	}
	public boolean isEs_caso_prueba_terminada() {
		return es_caso_prueba_terminada;
	}
	public void setEs_caso_prueba_terminada(boolean es_caso_prueba_terminada) {
		this.es_caso_prueba_terminada = es_caso_prueba_terminada;
	}
	public String getFecha_prueba() {
		return fecha_prueba;
	}
	public void setFecha_prueba(String fecha_prueba) {
		this.fecha_prueba = fecha_prueba;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getHash_descripcion() {
		return hash_descripcion;
	}
	public void setHash_descripcion(String hash_descripcion) {
		this.hash_descripcion = hash_descripcion;
	}
	public int getOpcional() {
		return opcional;
	}
	public void setOpcional(int opcional) {
		this.opcional = opcional;
	}
	public Integer getEtapaPrueba() {
		return etapaPrueba;
	}
	public void setEtapaPrueba(Integer etapaPrueba) {
		this.etapaPrueba = etapaPrueba;
	}
	public Long getSistemaId() {
		return sistemaId;
	}
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}
	public Integer getPorcentaje_avance() {
		return porcentaje_avance;
	}
	public void setPorcentaje_avance(Integer porcentaje_avance) {
		this.porcentaje_avance = porcentaje_avance;
	}
	public String getCodigo_sistema() {
		return codigo_sistema;
	}
	public void setCodigo_sistema(String codigo_sistema) {
		this.codigo_sistema = codigo_sistema;
	}
	public String getCuis() {
		return cuis;
	}
	public void setCuis(String cuis) {
		this.cuis = cuis;
	}
	public String getNit_requerimiento() {
		return nit_requerimiento;
	}
	public void setNit_requerimiento(String nit_requerimiento) {
		this.nit_requerimiento = nit_requerimiento;
	}
	public String getCodigo_punto_venta() {
		return codigo_punto_venta;
	}
	public void setCodigo_punto_venta(String codigo_punto_venta) {
		this.codigo_punto_venta = codigo_punto_venta;
	}
	public String getResultado_esperado() {
		return resultado_esperado;
	}
	public void setResultado_esperado(String resultado_esperado) {
		this.resultado_esperado = resultado_esperado;
	}
	public String getModalidad_requerimiento() {
		return modalidad_requerimiento;
	}
	public void setModalidad_requerimiento(String modalidad_requerimiento) {
		this.modalidad_requerimiento = modalidad_requerimiento;
	}
	public String getSucursal_requerimiento() {
		return sucursal_requerimiento;
	}
	public void setSucursal_requerimiento(String sucursal_requerimiento) {
		this.sucursal_requerimiento = sucursal_requerimiento;
	}
	public String getTipo_prueba() {
		return tipo_prueba;
	}
	public void setTipo_prueba(String tipo_prueba) {
		this.tipo_prueba = tipo_prueba;
	}
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	public String getCodigo_ambiente() {
		return codigo_ambiente;
	}
	public void setCodigo_ambiente(String codigo_ambiente) {
		this.codigo_ambiente = codigo_ambiente;
	}
	public String getTipo_validacion() {
		return tipo_validacion;
	}
	public void setTipo_validacion(String tipo_validacion) {
		this.tipo_validacion = tipo_validacion;
	}
	public String getRequisito_prueba() {
		return requisito_prueba;
	}
	public void setRequisito_prueba(String requisito_prueba) {
		this.requisito_prueba = requisito_prueba;
	}
	public String getCodigo_autorizacion() {
		return codigo_autorizacion;
	}
	public void setCodigo_autorizacion(String codigo_autorizacion) {
		this.codigo_autorizacion = codigo_autorizacion;
	}
	public String getDescripcion_prueba() {
		return descripcion_prueba;
	}
	public void setDescripcion_prueba(String descripcion_prueba) {
		this.descripcion_prueba = descripcion_prueba;
	}
	public String getAgrupador() {
		return agrupador;
	}
	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
	}
	public String getCodigo_emision() {
		return codigo_emision;
	}
	public void setCodigo_emision(String codigo_emision) {
		this.codigo_emision = codigo_emision;
	}
	public String getCodigo_modalidad() {
		return codigo_modalidad;
	}
	public void setCodigo_modalidad(String codigo_modalidad) {
		this.codigo_modalidad = codigo_modalidad;
	}
	public String getCufd() {
		return cufd;
	}
	public void setCufd(String cufd) {
		this.cufd = cufd;
	}
	public String getDocumento_fiscal_paquete() {
		return documento_fiscal_paquete;
	}
	public void setDocumento_fiscal_paquete(String documento_fiscal_paquete) {
		this.documento_fiscal_paquete = documento_fiscal_paquete;
	}
	public String getDocumento_sector() {
		return documento_sector;
	}
	public void setDocumento_sector(String documento_sector) {
		this.documento_sector = documento_sector;
	}
	public String getFecha_envio() {
		return fecha_envio;
	}
	public void setFecha_envio(String fecha_envio) {
		this.fecha_envio = fecha_envio;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public String getDocumento_sector_paquete() {
		return documento_sector_paquete;
	}
	public void setDocumento_sector_paquete(String documento_sector_paquete) {
		this.documento_sector_paquete = documento_sector_paquete;
	}
	public String getCantidad_documentos_fiscales() {
		return cantidad_documentos_fiscales;
	}
	public void setCantidad_documentos_fiscales(String cantidad_documentos_fiscales) {
		this.cantidad_documentos_fiscales = cantidad_documentos_fiscales;
	}
	public String getEvento_significativo_registrado() {
		return evento_significativo_registrado;
	}
	public void setEvento_significativo_registrado(String evento_significativo_registrado) {
		this.evento_significativo_registrado = evento_significativo_registrado;
	}
	public String getResultado_esperado_paquete() {
		return resultado_esperado_paquete;
	}
	public void setResultado_esperado_paquete(String resultado_esperado_paquete) {
		this.resultado_esperado_paquete = resultado_esperado_paquete;
	}
	public String getValor_parametro() {
		return valor_parametro;
	}
	public void setValor_parametro(String valor_parametro) {
		this.valor_parametro = valor_parametro;
	}
	public String getCuf() {
		return cuf;
	}
	public void setCuf(String cuf) {
		this.cuf = cuf;
	}
	public String getNumero_documento_fiscal() {
		return numero_documento_fiscal;
	}
	public void setNumero_documento_fiscal(String numero_documento_fiscal) {
		this.numero_documento_fiscal = numero_documento_fiscal;
	}
	public String getNombre_sistema() {
		return nombre_sistema;
	}
	public void setNombre_sistema(String nombre_sistema) {
		this.nombre_sistema = nombre_sistema;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getParametros_importantes() {
		return parametros_importantes;
	}
	public void setParametros_importantes(String parametros_importantes) {
		this.parametros_importantes = parametros_importantes;
	}	
}