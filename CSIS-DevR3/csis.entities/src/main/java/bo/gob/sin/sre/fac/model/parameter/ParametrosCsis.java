package bo.gob.sin.sre.fac.model.parameter;

public final class ParametrosCsis {

	/*
	// Estados genericos del campo Estado_Id
	public static final String ESTADO_ACTIVO = "AC";
	public static final String ESTADO_ANULADO = "AN";

	// Estados tabla SreSistemas
	public static final int ESTADO_SISTEMA_INICIADO = 616;
	public static final int ESTADO_SISTEMA_CERTIFICADO = 617;
	public static final int ESTADO_SISTEMA_OBSERVADO = 618;
	public static final int ESTADO_SISTEMA_BAJA = 619;
	public static final int ESTADO_SISTEMA_CANCELADO = 1287;

	// Estados tabla SreSistemasContribuyentes
	public static final int ESTADO_SISTEMA_CONTRIBUYENTE_ID_PENDIENTE = 636;
	public static final int ESTADO_SISTEMA_CONTRIBUYENTE_ID_APROBADA = 637;
	public static final int ESTADO_SISTEMA_CONTRIBUYENTE_ID_RECHAZADA = 638;
	public static final int ESTADO_SISTEMA_CONTRIBUYENTE_ID_CANCELADA = 639;

	// Valores campo TipoSolicitudCertificacionId de la tabla
	// SreSolicitudCertificacion
	public static final int TIPO_SOLICITUD_ID_NUEVA = 1378;
	public static final int TIPO_SOLICITUD_ID_ACTUALIZACION = 1379;

	// Estados tabla SreSolicitudCertificacion
	public static final int ESTADO_SOLICITUD_CERTIFICACION_EN_PROCESO = 610;
	public static final int ESTADO_SOLICITUD_CERTIFICACION_AUTORIZADO = 611;
	public static final int ESTADO_SOLICITUD_CERTIFICACION_RECHAZADO = 612;
	public static final int ESTADO_SOLICITUD_CERTIFICACION_CANCELADO = 613;
	public static final int ESTADO_SOLICITUD_CERTIFICACION_HISTORICO = 342;
	public static final int ESTADO_SOLICITUD_CERTIFICACION_PARA_AUTORIZAR = 1011;
	public static final int ESTADO_SOLICITUD_CERTIFICACION_VERIFICACION = 1259;

	public static final int ESTADO_PRUEBA_SOLICITUD_PENDIENTE = 1440;
	public static final int ESTADO_PRUEBA_SOLICITUD_ERRONEA = 1441;
	public static final int ESTADO_PRUEBA_SOLICITUD_APROBADA = 1442;

	// Estados tabla SreTiposDocumentosSistemas
	public static final int ESTADO_TIPO_DOCUMENTO_SISTEMA_VALIDO = 1379;
	public static final int ESTADO_TIPO_DOCUMENTO_SISTEMA_INVALIDO = 1380;

	// Estados de la tabla SrePruebasSistemas
	public static final int ESTADO_PRUEBA_PENDIENTE = 597;
	public static final int ESTADO_PRUEBA_APROBADA = 598;
	public static final int ESTADO_PRUEBA_ERRONEA = 599;
	public static final int ESTADO_PRUEBA_CANCELADO = 1289;

	// Para verificar longitud en la generacion del Cuis
	public static final int CORREGIR_LONGITUD_MODALIDAD = 4;
	public static final int CORREGIR_LONGITUD_CADENA_SUCURSAL = 3;
	public static final int CORREGIR_LONGITUD_CADENA_NIT = 13;
	public static final int CORREGIR_LONGITUD_CADENA_IDSISTEMA = 9;
	public static final int CORREGIR_LONGITUD_CADENA_CUIS = 20;

	// Valores Tipo Sistema
	public static final int TIPO_SISTEMA_PROPIO = 614;
	public static final int TIPO_SISTEMA_PROVEEDOR = 615;

	// Valores Tipo Usuario
	public static final int TIPO_USUARIO_FUNCIONARIO = 861;
	public static final int TIPO_USUARIO_CONTRIBUYENTE = 862;

	// Valores de Regex
	public static final String REGEX_WINDOWS = "^(?:[a-zA-Z]\\:|\\\\\\\\[\\w\\.]+\\\\[\\w.$]+)\\\\(?:[\\w]+\\\\)*\\w([\\w.])+$";;
	public static final String REGEX_NO_WINDOWS = "^(\\/)?(\\/[a-zA-Z0-9_.-]+)+\\/?$";



	// Instruccion Inicio de Tramite Automatico
	public static final String INSTRUCCION_INICIO_TRAMITE_AUTOMATICO = "Asignar Equipo de Trabajo para la solicitud de certificación de sistema";

	
	// Estados tabla sreasignacionescertificaciones
	public static final int ESTADO_ASINGACION_EQUIPO_CERTIFICACION_RECHAZADO = 1828;
	public static final int ESTADO_ASINGACION_EQUIPO_CERTIFICACION_FINALIZADO = 1829;
	public static final int ESTADO_ASINGACION_EQUIPO_CERTIFICACION_ASIGNADO = 1827;
	
	//CEDULA IDENTIDAD TIPO	
	public static final int TIPO_DOCUMENTO_IDENTIDAD = 337;

	//Valor de los servicios comunes
	public static final int RUTA_SERVICIOS_COMUNES = 1856;
	
	// Valores del Tipo Documento Sector
	public static final int TIPO_DOCUMENTO_SECTOR_ID_ESTANDAR = 1382;	
	public static final int TIPO_DOCUMENTO_SECTOR_ID_SECTOR_EDUCATIVO = 1383;
	public static final int TIPO_DOCUMENTO_SECTOR_ID_HIDROCARBUROS = 1385;
	public static final int TIPO_DOCUMENTO_SECTOR_ID_ALQUILER = 1384;
	public static final int TIPO_DOCUMENTO_SECTOR_ID_HOSPITALES = 1390;
	public static final int TIPO_DOCUMENTO_SECTOR_ID_HOTELES = 1389;
	public static final int TIPO_DOCUMENTO_SECTOR_ID_JUEGOS_AZAR = 1391;
	public static final int TIPO_DOCUMENTO_SECTOR_ID_EMBOTELLADORAS = 1387;
	public static final int TIPO_DOCUMENTO_SECTOR_ID_SERVICIOS = 1386;
	public static final int TIPO_DOCUMENTO_SECTOR_ID_ENTIDADES = 1388;
	public static final int TIPO_DOCUMENTO_SECTOR_ID_EXPORTACION = 1393;
	public static final int TIPO_DOCUMENTO_SECTOR_ID_ARTISTAS = 1392;
	
*/
	
	public static final String CTX_DATOS_TRAMITE_CERTIFICACION = "DatosTramiteCertificacion";
	// Valores Tipo Usuario
	public static final int TIPO_USUARIO_FUNCIONARIO = 861;
	public static final int TIPO_USUARIO_CONTRIBUYENTE = 862;

	// Etapa gestion de pruebas, verificacion in situ
	public static final Long ETAPA_GESTION_PRUEBAS_INSITU = 2006L;

	// Estados tabla sreasignacionescertificaciones
	public static final int ESTADO_ASINGACION_EQUIPO_CERTIFICACION_RECHAZADO = 1828;
	public static final int ESTADO_ASINGACION_EQUIPO_CERTIFICACION_FINALIZADO = 1829;
	public static final int ESTADO_ASINGACION_EQUIPO_CERTIFICACION_ASIGNADO = 1827;
	
	// Valor Tramite WorkFlow
	public static final Long PROCESO_DE_CERTIFICACION = 1001L;
	public static final Long ETAPA_ASIGNACION_CERTIFICACION = 1002L;
	
	public static final long SISTEMA_ID_FACTURADOR_WEB = 1000;
	
	public static final int DOCUMENTO_NOTIFICADO = 1458;
	public static final int DOCUMENTO_CONCLUIDO = 943;
	

	
	public static final int TIPO_RUBRICA_FIRMA = 711;
	public static final int TIPO_RUBRICA_VOBO = 700;
	public static final String EXTENSION_ARCHIVO_PDF = "PDF";
	public static final Long ETAPA_SISTEMA_PROVEEDORES = 3052L;
}
