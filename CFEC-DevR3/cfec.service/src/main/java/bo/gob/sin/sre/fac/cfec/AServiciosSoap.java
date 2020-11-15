package bo.gob.sin.sre.fac.cfec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaValidacionRecepcionPaqueteDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaXmlXsdDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudRevertirCadenaDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.service.IServicioPushClientRest;
import bo.gob.sin.sre.fac.cfec.service.IServiciosFirmaClientRest;
import bo.gob.sin.sre.fac.cfec.service.IServiciosReversionClientRest;
import bo.gob.sin.sre.fac.cfec.servicedomain.ICompras;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;
import bo.gob.sin.sre.fac.cfec.servicedomain.validation.CodigosMensajesServiciosSOAPServiceImpl;

public abstract class AServiciosSoap {

	@Autowired
	IUtilitarios iutil;

	@Autowired
	IValidarRecepcion iValRec;

	@Autowired
	IServiciosFirmaClientRest iServiciosFirmaClientRest;

	@Autowired
	IServiciosReversionClientRest iServiciosReversionClientRest;

	@Autowired
	IServicioPushClientRest iServicioPushClientRest;

	@Autowired
	ICompras iCompras;

	/**
	 * @autor edwin.coro
	 * @descripción metodo que determina que procedimiento seguir
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @param etapa        valor que determina que procedimiento seguir, Recepcion,
	 *                     RecepcionPaquete, ValidacionRecepcion, Anulacion,
	 *                     ValidacionAnulacion
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto controlador(XmlRecepcionGenericoDto xmlRecGenDto, Integer etapa) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		switch (etapa) {
		case Parametros.Recepcion:
			vRespuesta = this.recepcionar(xmlRecGenDto);
			break;
		case Parametros.RecepcionPaquete:
			vRespuesta = this.recepcionarPaquete(xmlRecGenDto);
			break;
		case Parametros.Masivo:
			vRespuesta = this.recepcionarMasivo(xmlRecGenDto);
			break;
		case Parametros.ValidacionRecepcion:
			vRespuesta = this.validarRecepcion(xmlRecGenDto);
			break;
		case Parametros.Anulacion:
			vRespuesta = this.anulacionFactura(xmlRecGenDto);
			break;
		case Parametros.ValidacionAnulacion:
			vRespuesta = this.validarAnulacionFactura(xmlRecGenDto);
			break;
		case Parametros.ValidacionRecepcionPorCuf:
			vRespuesta = this.validarRecepcionPorCuf(xmlRecGenDto);
			break;
		case Parametros.RecepcionCompras:
			vRespuesta = this.recepcionarCompras(xmlRecGenDto);
			break;
		case Parametros.RecepcionPaqueteCompras:
			vRespuesta = this.recepcionarComprasPaquete(xmlRecGenDto);
			break;
		case Parametros.ValidacionRecepcionCompras:
			vRespuesta = this.validarRecepcionCompras(xmlRecGenDto);
			break;
		case Parametros.ObtenerRecepcionAnulacionPorCufNumFactura:
			vRespuesta = this.ObtenerRecepcionAnulacionPorCufNumFactura(xmlRecGenDto);
			break;

		}

		return vRespuesta;
	}

	/**
	 * @autor edwin.coro
	 * @descripción metodo que determina que procedimiento seguir
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @param etapa        valor que determina que procedimiento seguir:
	 *                     ValidacionRecepcionPaquete.
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera), lista de detalles de error (si hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaValidacionRecepcionPaqueteDto controladorPaquete(XmlRecepcionGenericoDto xmlRecGenDto,
			Integer etapa) {
		RespuestaValidacionRecepcionPaqueteDto vRespuesta = new RespuestaValidacionRecepcionPaqueteDto();

		if (etapa == Parametros.ValidacionRecepcionPaquete)
			vRespuesta = this.validarRecepcionPaquete(xmlRecGenDto);

		if (etapa == Parametros.ValidacionMasivo)
			vRespuesta = this.validarRecepcionMasiva(xmlRecGenDto);

		if (etapa == Parametros.ValidacionRecepcionPaqueteCompras)
			vRespuesta = this.validarRecepcionComprasPaquete(xmlRecGenDto);

		return vRespuesta;
	}

	// template method
	/**
	 * @autor edwin.coro
	 * @descripción metodo que realiza ciertas validaciones (hash, archivo,
	 *              firma,...), seguidamente envia a funcion en BD, para la
	 *              generacion del codigo de recepcion (computarizada/electronica) .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto recepcionar(XmlRecepcionGenericoDto xmlRecGenDto) {
		List<Integer> vLisCodError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// Validar la hash con el archivo
		vLisCodError.addAll(iValRec.validarHashFactura(xmlRecGenDto.getV_archivo(), xmlRecGenDto.getV_hash_archivo()));

		// Validar la recepcion con el XSD (computarizada/electronica)
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarRecepcionIndXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		// Validar que el archivo pueda ser decodificado
		List<Integer> vCodError = iValRec.validarArchivoIndividual(xmlRecGenDto.getV_archivo());
		if (vCodError.isEmpty()) {
			xmlRecGenDto.setV_archivoXml(iutil.decodificarArchivo(xmlRecGenDto.getV_archivo()));

			// Validar documento fiscal con el XSD (Electronica)
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_FACTURA_ELECTRONICA
					|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA) {
				RespuestaXmlXsdDto vResXmlXsdElec = iValRec.validarDocumentoFiscalElectronicaXsd(
						xmlRecGenDto.getV_archivoXml(), xmlRecGenDto.getP_tipo_documento_fiscal_id(),
						xmlRecGenDto.getP_tipo_documento_sector_id());
				vLisCodError.addAll(vResXmlXsdElec.getListaCodigosRespuestas());
				vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsdElec.getListaDescripcionesRespuestas());
			}
			// Validar documento fiscal con el XSD (Computarizada)
			if (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_FACTURA_COMPUTARIZADA
					|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_COMPUTARIZADA) {
				RespuestaXmlXsdDto vResXmlXsdComp = iValRec.validarDocumentoFiscalXsd(xmlRecGenDto.getV_archivoXml(),
						xmlRecGenDto.getP_tipo_documento_fiscal_id(), xmlRecGenDto.getP_tipo_documento_sector_id());
				vLisCodError.addAll(vResXmlXsdComp.getListaCodigosRespuestas());
				vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsdComp.getListaDescripcionesRespuestas());
			}
		} else {
			vLisCodError.addAll(vCodError);
		}

		if (vLisCodError.isEmpty() && (xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_FACTURA_ELECTRONICA
				|| xmlRecGenDto.getvOrigenServicio() == Parametros.F_MODALIDAD_PREVALORADA_ELECTRONICA)) {
			// Valida la firma en caso de documento fiscal electronico
			vLisCodError.addAll(iValRec.validarFirmaElectronicaIndividual(xmlRecGenDto.getV_archivoXml()));
//			TODO: Se coment� por ser todas las pruebas declaradas y no es necesario el registro del log de la firma.
			// Registro de Log para el guardado de los Log de Firma Digital.
//			iutil.registrarLogCertificacionSistemas(xmlRecGenDto, vLisCodError);
		}

		// Enviar el JSON de recepcion a BD
		if (vLisCodError.isEmpty())
			vRespuesta = iValRec.enviarJsonRecepcionBDEtapa1(xmlRecGenDto);

		vRespuesta.getListaCodigosRespuestas().addAll(vLisCodError);

		if (vRespuesta.getListaCodigosRespuestas().isEmpty() && vRespuesta.getCodigoRecepcion() > 0) {
			// REGISTRAR EN BD
			xmlRecGenDto.setV_recepcionId(vRespuesta.getCodigoRecepcion());
			iValRec.enviarJsonFacturaHilo(xmlRecGenDto);
		} else {
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
		}

		return vRespuesta;
	}

	// template method
	/**
	 * @autor edwin.coro
	 * @descripción metodo que validacion estructura de parametros ingresados en
	 *              servicio, ademas de invocar a funcion en BD, para verificar el
	 *              estado de la recepcion (computarizada/electronica) .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto validarRecepcion(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// Validar la validacion de recepcion con el XSD (computarizada/electronica)
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarValidacionRecepcionIndXsd(xmlRecGenDto);
		List<Integer> vLisCodError = vResXmlXsd.getListaCodigosRespuestas();
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		if (vLisCodError.isEmpty()) {
			// valida parametros recibidos y actualizacion de estados en Base de datos
			vRespuesta = iValRec.validacionRecepcionBd(xmlRecGenDto);
		} else {
			vRespuesta.setListaCodigosRespuestas(vLisCodError);
		}
		// Realizar el registro de los log para el Consumo del Servicio.
		iValRec.registrarLogConsumoServicio(xmlRecGenDto, vRespuesta.getCodigoEstado(),
				Parametros.CONSUMO_SERVICIO_VERIFICACION_FACTURA, vRespuesta.getCodigoRecepcion());
		return vRespuesta;
	}

	// template method
	/**
	 * @autor edwin.coro
	 * @descripción metodo que validacion estructura de parametros ingresados en
	 *              servicio, ademas de invocar a funcion en BD, para verificar el
	 *              estado de la recepcion (computarizada/electronica) .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto validarRecepcionPorCuf(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// Recuperar codigoRecepcionId a partir del cuf
		Long vCodigoRecInd = iValRec.obtenerCodigoRecIndBd(xmlRecGenDto);
		if (vCodigoRecInd != null && vCodigoRecInd > 0) {
			xmlRecGenDto.setV_recepcionId(vCodigoRecInd);
			// Validar la validacion de recepcion con el XSD (computarizada/electronica)
			RespuestaXmlXsdDto vResXmlXsd = iValRec.validarValidacionRecepcionIndXsd(xmlRecGenDto);
			List<Integer> vLisCodError = vResXmlXsd.getListaCodigosRespuestas();
			vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

			if (vLisCodError.isEmpty()) {
				// valida parametros recibidos y actualizacion de estados en Base de datos
				vRespuesta = iValRec.validacionRecepcionBd(xmlRecGenDto);
			} else {
				vRespuesta.setListaCodigosRespuestas(vLisCodError);
			}

			// Realizar el registro de los log para el Consumo del Servicio.
			iValRec.registrarLogConsumoServicio(xmlRecGenDto, vRespuesta.getCodigoEstado(),
					Parametros.CONSUMO_SERVICIO_VERIFICACION_FACTURA, vRespuesta.getCodigoRecepcion());
		} else {
			vRespuesta.getListaCodigosRespuestas().add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_INEXISTENTE);
		}

		return vRespuesta;
	}

	// template method
	/**
	 * @autor edwin.coro
	 * @descripción metodo que realiza ciertas validaciones (hash, archivo,...),
	 *              seguidamente envia a funcion en BD, para la generacion del
	 *              codigo de recepcion (computarizada/electronica).
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto recepcionarPaquete(XmlRecepcionGenericoDto xmlRecGenDto) {
		List<Integer> vLisCodError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// TODO: Reynaldo Chambi, Se queta la marca paquete
		// xmlRecGenDto.setP_tipo_emision_id(Parametros.F_TIPO_EMISION_OFFLINE);

		// Validar la hash con el archivo
		vLisCodError.addAll(iValRec.validarHashFactura(xmlRecGenDto.getV_archivo(), xmlRecGenDto.getV_hash_archivo()));

		// Validar la recepcion paquete con el XSD
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarRecepcionPaqXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		// Validar que el archivo pueda ser decodificado
		vLisCodError.addAll(iValRec.validarArchivoPaquete(xmlRecGenDto.getV_archivo()));

		// Enviar el JSON de recepcion a BD
		if (vLisCodError.isEmpty())
			vRespuesta = iValRec.enviarJsonRecepcionBDEtapa1(xmlRecGenDto);

		vRespuesta.getListaCodigosRespuestas().addAll(vLisCodError);

		if (vRespuesta.getListaCodigosRespuestas().isEmpty() && vRespuesta.getCodigoRecepcion() > 0) {
			// REGISTRAR EN EL HILO
			xmlRecGenDto.setV_recepcionPaqueteId(vRespuesta.getCodigoRecepcion());
			iValRec.enviarJsonFacturaPaqueteHilo(xmlRecGenDto);
		} else {
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
		}

		return vRespuesta;
	}

	// template method
	/**
	 * @autor edwin.coro
	 * @descripción metodo que validacion estructura de parametros ingresados en
	 *              servicio, ademas de invocar a funcion en BD, para verificar el
	 *              estado de la recepcion (computarizada/electronica) .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera), lista de detalles de error (si hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaValidacionRecepcionPaqueteDto validarRecepcionPaquete(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaValidacionRecepcionPaqueteDto vRespuesta = new RespuestaValidacionRecepcionPaqueteDto();
		List<Integer> vLisCodError = new ArrayList<>();

		// Valida formato de parametros recibidos en servicio contra xsd de
		// validacionRecepcionpaquete del proyecto correspondiente
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarValidacionRecepcionPaqXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		if (vLisCodError.isEmpty()) {
			// valida parametros recibidos y actualizacion de estados en Base de datos
			vRespuesta = iValRec.validacionRecepcionPaqueteBd(xmlRecGenDto);
		} else {
			vRespuesta.setListaCodigosRespuestas(vLisCodError);
		}

		return vRespuesta;
	}

	// template method
	/**
	 * @autor edwin.coro
	 * @descripción metodo que realiza invocacion a funcion en BD, para el registro
	 *              de solicitud de anulacion de documento fiscal
	 *              (computarizada/electronica).
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto anulacionFactura(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		List<Integer> vLisCodError = new ArrayList<>();

		// Validar la anulacion con el XSD (computarizada/electronica)
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarAnulacionXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		if (vLisCodError.isEmpty()) {
			vRespuesta = iValRec.anulacionBd(xmlRecGenDto);
		} else {
			vRespuesta.setListaCodigosRespuestas(vLisCodError);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.ANULACION_RECHAZADA);
		}
		// Realizar el registro de los log para el Consumo del Servicio.
		iValRec.registrarLogConsumoServicio(xmlRecGenDto, vRespuesta.getCodigoEstado(),
				Parametros.CONSUMO_SERVICIO_ANULACION_FACTURA, vRespuesta.getCodigoRecepcion());
		return vRespuesta;
	}

	// template method
	/**
	 * @autor edwin.coro
	 * @descripción metodo que realiza invocacion a funcion en BD, para la
	 *              verificacion del estado de la solicitud de anulacion de
	 *              documento fiscal (computarizada/electronica).
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto validarAnulacionFactura(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		List<Integer> vLisCodError = new ArrayList<>();

		// Validar la validacion de anulacion con el XSD (computarizada/electronica)
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarValidacionAnulacionXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		if (vLisCodError.isEmpty()) {
			vRespuesta = iValRec.validacionAnulacionBd(xmlRecGenDto);
		} else {
			vRespuesta.setListaCodigosRespuestas(vLisCodError);
		}
		// Realizar el registro de los log para el Consumo del Servicio.
		iValRec.registrarLogConsumoServicio(xmlRecGenDto, vRespuesta.getCodigoEstado(),
				Parametros.CONSUMO_SERVICIO_VERIFICACION_ANULACION, vRespuesta.getCodigoRecepcion());
		iValRec.registrarLogAnulacionFactura(xmlRecGenDto, vRespuesta.getCodigoEstado(),
				Parametros.CONSUMO_SERVICIO_VERIFICACION_ANULACION, vRespuesta.getCodigoRecepcion());
		return vRespuesta;
	}

	/**
	 * @autor edwin.coro
	 * @descripción metodo que realiza ciertas validaciones (hash, archivo,...),
	 *              seguidamente envia a funcion en BD, para la generacion del
	 *              codigo de recepcion (computarizada/electronica).
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto recepcionarMasivo(XmlRecepcionGenericoDto xmlRecGenDto) {
		List<Integer> vLisCodError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// Validar la hash con el archivo
		vLisCodError.addAll(iValRec.validarHashFactura(xmlRecGenDto.getV_archivo(), xmlRecGenDto.getV_hash_archivo()));

		// Validar la recepcion masivo con el XSD
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarRecepcionMasXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		// Validar que el archivo pueda ser decodificado
		vLisCodError.addAll(iValRec.validarArchivoMasivo(xmlRecGenDto.getV_archivo(), xmlRecGenDto.getP_nit(),
				xmlRecGenDto.getP_sucursal_id()));

		// Enviar el JSON de recepcion a BD
		if (vLisCodError.isEmpty())
			vRespuesta = iValRec.enviarJsonRecepcionBDEtapa1Masivo(xmlRecGenDto);

		vRespuesta.getListaCodigosRespuestas().addAll(vLisCodError);

		if (vRespuesta.getListaCodigosRespuestas().isEmpty() && vRespuesta.getCodigoRecepcion() > 0) {
			// REGISTRAR EN EL HILO
			xmlRecGenDto.setV_recepcionPaqueteId(vRespuesta.getCodigoRecepcion());
			iValRec.enviarJsonFacturaMasivoHilo(xmlRecGenDto);
		} else {
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
		}

		return vRespuesta;
	}

	// template method
	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que validacion estructura de parametros ingresados en
	 *              servicio, ademas de invocar a funcion en BD, para verificar el
	 *              estado de la recepcion (computarizada/electronica) .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera), lista de detalles de error (si hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaValidacionRecepcionPaqueteDto validarRecepcionMasiva(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaValidacionRecepcionPaqueteDto vRespuesta = new RespuestaValidacionRecepcionPaqueteDto();
		List<Integer> vLisCodError = new ArrayList<>();

		// Valida formato de parametros recibidos en servicio contra xsd de
		// validacionRecepcionpaquete del proyecto correspondiente
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarValidacionRecepcionMasXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		if (vLisCodError.isEmpty()) {
			// valida parametros recibidos y actualizacion de estados en Base de datos
			vRespuesta = iValRec.validacionRecepcionMasivaBd(xmlRecGenDto);
		} else {
			vRespuesta.setListaCodigosRespuestas(vLisCodError);
		}

		return vRespuesta;
	}

	// template method
	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que realiza ciertas validaciones (hash, archivo,
	 *              firma,...), seguidamente envia a funcion en BD, para la
	 *              generacion del codigo de recepcion (computarizada/electronica) .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 02/08/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto recepcionarCompras(XmlRecepcionGenericoDto xmlRecGenDto) {
		List<Integer> vLisCodError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// Validar la hash con el archivo
		vLisCodError.addAll(iValRec.validarHashFactura(xmlRecGenDto.getV_archivo(), xmlRecGenDto.getV_hash_archivo()));

		// Validar CUF
		SolicitudRevertirCadenaDto vSolicitudCuf = new SolicitudRevertirCadenaDto();
		vSolicitudCuf.setCodigo(iValRec.obtenerCuf(xmlRecGenDto));
		RespuestaDatosCufDto vRespuestaCuf = iServiciosReversionClientRest.reversionCuf(vSolicitudCuf);
		if (vRespuestaCuf.getNitEmisor() != null && vRespuestaCuf.getNitEmisor() > 0) {
			if (xmlRecGenDto.getP_nit().equals(vRespuestaCuf.getNitEmisor())) {
				xmlRecGenDto.setP_tipo_modalidad_id(vRespuestaCuf.getModalidad());
				xmlRecGenDto.setP_tipo_emision_id(Parametros.F_TIPO_EMISION_ONLINE);
				xmlRecGenDto.setP_tipo_documento_fiscal_id(vRespuestaCuf.getDocumentoFiscal());
				xmlRecGenDto.setP_tipo_documento_sector_id(vRespuestaCuf.getDocumentoSector());
			} else {
				vLisCodError.add(CodigosMensajesServiciosSOAPServiceImpl.NIT_INVALIDO);
			}
		} else {
			vLisCodError.add(CodigosMensajesServiciosSOAPServiceImpl.CODIGO_UNICO_DE_FACTURA_CUF_INVALIDO);
		}

		// Validar la recepcion con el XSD (computarizada/electronica)
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarRecepcionComprasIndXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		// Validar que el archivo pueda ser decodificado
		List<Integer> vCodError = iValRec.validarArchivoIndividual(xmlRecGenDto.getV_archivo());
		if (vCodError.isEmpty()) {
			xmlRecGenDto.setV_archivoXml(iutil.decodificarArchivo(xmlRecGenDto.getV_archivo()));

			// Validar documento fiscal con el XSD (Computarizada)
			RespuestaXmlXsdDto vResXmlXsdComp = iValRec
					.validarDocumentoFiscalComprasXsd(xmlRecGenDto.getV_archivoXml());
			vLisCodError.addAll(vResXmlXsdComp.getListaCodigosRespuestas());
			vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsdComp.getListaDescripcionesRespuestas());
		} else {
			vLisCodError.addAll(vCodError);
		}

		// Enviar el JSON de recepcion a BD
		if (vLisCodError.isEmpty())
			vRespuesta = iValRec.enviarJsonComprasRecepcionBDEtapa1(xmlRecGenDto);

		vRespuesta.getListaCodigosRespuestas().addAll(vLisCodError);

		if (vRespuesta.getListaCodigosRespuestas().isEmpty() && vRespuesta.getCodigoRecepcion() > 0) {
			// REGISTRAR EN BD
			xmlRecGenDto.setV_recepcionId(vRespuesta.getCodigoRecepcion());
			iValRec.enviarJsonComprasFacturaHilo(xmlRecGenDto);
		} else {
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
		}

		return vRespuesta;
	}

	// template method
	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que realiza ciertas validaciones (hash, archivo,...),
	 *              seguidamente envia a funcion en BD, para la generacion del
	 *              codigo de recepcion (computarizada/electronica).
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto recepcionarComprasPaquete(XmlRecepcionGenericoDto xmlRecGenDto) {
		List<Integer> vLisCodError = new ArrayList<>();
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// Marca paquete
		xmlRecGenDto.setP_tipo_emision_id(Parametros.F_TIPO_EMISION_OFFLINE);

		// Validar la hash con el archivo
		vLisCodError.addAll(iValRec.validarHashFactura(xmlRecGenDto.getV_archivo(), xmlRecGenDto.getV_hash_archivo()));

		// Validar la recepcion paquete con el XSD
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarRecepcionPaqXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		// Validar que el archivo pueda ser decodificado
		vLisCodError.addAll(iValRec.validarArchivoPaquete(xmlRecGenDto.getV_archivo()));

		// Enviar el JSON de recepcion a BD
		if (vLisCodError.isEmpty())
			vRespuesta = iValRec.enviarJsonComprasRecepcionBDEtapa1(xmlRecGenDto);

		vRespuesta.getListaCodigosRespuestas().addAll(vLisCodError);

		if (vRespuesta.getListaCodigosRespuestas().isEmpty() && vRespuesta.getCodigoRecepcion() > 0) {
			// REGISTRAR EN EL HILO
			xmlRecGenDto.setV_recepcionPaqueteId(vRespuesta.getCodigoRecepcion());
			iValRec.enviarJsonComprasFacturaPaqueteHilo(xmlRecGenDto);
		} else {
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.RECEPCION_RECHAZADA);
		}

		return vRespuesta;
	}

	// template method
	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que validacion estructura de parametros ingresados en
	 *              servicio, ademas de invocar a funcion en BD, para verificar el
	 *              estado de la recepcion (computarizada/electronica) .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 02/08/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto validarRecepcionCompras(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// Validar la validacion de recepcion con el XSD (computarizada/electronica)
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarValidacionRecepcionIndXsd(xmlRecGenDto);
		List<Integer> vLisCodError = vResXmlXsd.getListaCodigosRespuestas();
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		if (vLisCodError.isEmpty()) {
			// valida parametros recibidos y actualizacion de estados en Base de datos
			vRespuesta = iValRec.validacionRecepcionComprasBd(xmlRecGenDto);
		} else {
			vRespuesta.setListaCodigosRespuestas(vLisCodError);
		}

		return vRespuesta;
	}

	// template method
	/**
	 * @autor reynaldo.chambi
	 * @descripción metodo que validacion estructura de parametros ingresados en
	 *              servicio, ademas de invocar a funcion en BD, para verificar el
	 *              estado de la recepcion (computarizada/electronica) .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera), lista de detalles de error (si hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaValidacionRecepcionPaqueteDto validarRecepcionComprasPaquete(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaValidacionRecepcionPaqueteDto vRespuesta = new RespuestaValidacionRecepcionPaqueteDto();
		List<Integer> vLisCodError = new ArrayList<>();

		// Valida formato de parametros recibidos en servicio contra xsd de
		// validacionRecepcionpaquete del proyecto correspondiente
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarValidacionRecepcionPaqXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		if (vLisCodError.isEmpty()) {
			// valida parametros recibidos y actualizacion de estados en Base de datos
			vRespuesta = iValRec.validacionRecepcionComprasPaqueteBd(xmlRecGenDto);
		} else {
			vRespuesta.setListaCodigosRespuestas(vLisCodError);
		}

		return vRespuesta;
	}

	// template method
	/**
	 * @autor edwin.coro
	 * @descripción metodo que validacion estructura de parametros ingresados en
	 *              servicio, ademas de invocar a funcion en BD, para verificar el
	 *              estado de la recepcion (computarizada/electronica) .
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 22/04/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto obtenerRecepcionAnulacionPorCuf(
			XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();

		// Recuperar codigoRecepcionId a partir del cuf
		// CodigosMensajesServiciosSOAPServiceImpl.FACTURA_INEXISTENTE
		// Validar la validacion de recepcion con el XSD (computarizada/electronica)
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarValidacionRecepcionIndXsd(xmlRecGenDto);
		List<Integer> vLisCodError = vResXmlXsd.getListaCodigosRespuestas();
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());
		if (vLisCodError.isEmpty()) {
			// valida parametros recibidos y actualizacion de estados en Base de datos
			vRespuesta = iValRec.validacionRecepcionBd(xmlRecGenDto);
		} else {
			vRespuesta.setListaCodigosRespuestas(vLisCodError);
		}
		// Realizar el registro de los log para el Consumo del Servicio.
		iValRec.registrarLogConsumoServicio(xmlRecGenDto, vRespuesta.getCodigoEstado(),
				Parametros.CONSUMO_SERVICIO_VERIFICACION_FACTURA, vRespuesta.getCodigoRecepcion());

		return vRespuesta;
	}
	
	/**
	 * @autor junior.flores
	 * @descripci�n m�todo que realiza invocacion a funcion en BD, para la obtenci�n
	 *              del c�digo unico de recepci�n de la anulaci�n de factura y factura de contingencia
	 *              (computarizada/electronica).
	 * @param xmlRecGenDto dto obtenido a traves de los parametros del servicio
	 * @return dto con codigo recepcion, codigo estado, lista de errores (si
	 *         hubiera)
	 * @fecha 25/10/2019
	 */
	public RespuestaListaRegistroRecepcionesSoapDto ObtenerRecepcionAnulacionPorCufNumFactura(XmlRecepcionGenericoDto xmlRecGenDto) {
		RespuestaListaRegistroRecepcionesSoapDto vRespuesta = new RespuestaListaRegistroRecepcionesSoapDto();
		List<Integer> vLisCodError = new ArrayList<>();

		// Validar la anulacion con el XSD (computarizada/electronica)
		RespuestaXmlXsdDto vResXmlXsd = iValRec.validarRecepcionAnulacionXsd(xmlRecGenDto);
		vLisCodError.addAll(vResXmlXsd.getListaCodigosRespuestas());
		vRespuesta.getListaDescripcionesRespuestas().addAll(vResXmlXsd.getListaDescripcionesRespuestas());

		if (vLisCodError.isEmpty()) {
			vRespuesta = iValRec.recepcionAnulacionBd(xmlRecGenDto);
		} else {
			vRespuesta.setListaCodigosRespuestas(vLisCodError);
			vRespuesta.setCodigoEstado(CodigosMensajesServiciosSOAPServiceImpl.ANULACION_RECHAZADA);
		}
		// Realizar el registro de los log para el Consumo del Servicio.
		iValRec.registrarLogConsumoServicio(xmlRecGenDto, vRespuesta.getCodigoEstado(),
				Parametros.CONSUMO_SERVICIO_ANULACION_FACTURA, vRespuesta.getCodigoRecepcion());
		return vRespuesta;
	}
}