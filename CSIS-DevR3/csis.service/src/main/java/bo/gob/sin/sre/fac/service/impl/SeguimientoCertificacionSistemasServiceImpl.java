package bo.gob.sin.sre.fac.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bo.gob.sin.sre.fac.domain.ISeguimientoCertificacionSistemasDomain;
import bo.gob.sin.sre.fac.dto.DetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaDetalleCasosDePruebaEtapa0Dto;
import bo.gob.sin.sre.fac.dto.ListaDetalleSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.ListaSeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.ListaSistemasProveedorDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SeguimientoCertificacionSistemasFaseDosDto;
import bo.gob.sin.sre.fac.dto.SeguimientoDetalleCertificacionSistemasDto;
import bo.gob.sin.sre.fac.dto.SistemasContribuyentesDto;
import bo.gob.sin.sre.fac.service.ISeguimientoCertificacionSistemasService;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
public class SeguimientoCertificacionSistemasServiceImpl implements ISeguimientoCertificacionSistemasService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RegistroVerificacionInsituServiceImpl.class);
	
	@Autowired
	private ISeguimientoCertificacionSistemasDomain iSeguimientoCertificacionSistemasDomain;
	
	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;
	
	/** 
	 * Obtener el porcentaje de la tabla Pruebas de Certificación de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 03/06/2019
	 * @param pSistemaId, Código unico de Sistema
	 * 		  pNit, Número de Identificación Tributaria
	 * @return   Devuelve un objeto tipo ListaSeguimientoCertificacionSistemasDto.
	 */
	public ListaSeguimientoCertificacionSistemasDto obtenerListaCertificacionSistemas(Long pSistemaId, Long pNit) {
		ListaSeguimientoCertificacionSistemasDto vRespuesta=new ListaSeguimientoCertificacionSistemasDto();
		vRespuesta.setOk(false);
		try {
			if(pSistemaId==null || pSistemaId <= 0 || pNit==null || pNit <= 0 ) {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			} 
			else {
				List<SeguimientoCertificacionSistemasDto> listaSeguimientoCertificacionSistemasDto= iSeguimientoCertificacionSistemasDomain.obtenerListaCertificacionSistemas(pSistemaId, pNit);
				HiloActualizarMatch hilo= new HiloActualizarMatch();
				hilo.setSistemaId(pSistemaId);
				hilo.setEtapaCertificacionSistemaId(0);
				hilo.setiValRec(iSeguimientoCertificacionSistemasDomain);
				hilo.start();
				hilo=null;
				if(listaSeguimientoCertificacionSistemasDto.size()>0)
				{				
					vRespuesta.setListaSeguimientoCertificacionSistemasDto(listaSeguimientoCertificacionSistemasDto);
					vRespuesta.setOk(true);
					LOG.info("Error al realizar la obtencion de datos");
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.LISTADO_PRUEBAS_AUTOMATICAS_DESPLEGADO_CORRECTAMENTE));
				}else
				{
					LOG.info("Error al realizar la obtencion de datos");
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.ERROR_DATOS_NULOS_LISTADO_PRUEBAS_AUTOMATICAS));
				}
			}
		} catch (Exception e) {
			LOG.info("Error al realizar la obtencion de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.ERROR_RECUPERA_LISTADO_PRUEBAS_AUTOMATICAS));
		}
		return vRespuesta;
	}
	
	/**
	 * @author rosario.garcia
	 * @fecha 05/06/2019
	 */
	public ListaDetalleCasosDePruebaEtapa0Dto obtenerListaDdetalleCasosDePruebaEtap0(Long pSistemaId, Long pNit) {
		ListaDetalleCasosDePruebaEtapa0Dto vRespuesta = new ListaDetalleCasosDePruebaEtapa0Dto();
		vRespuesta.setOk(false);
		try {
			List<DetalleCasosDePruebaEtapa0Dto> listaDetalleCasosDePruebaEtapa0 = iSeguimientoCertificacionSistemasDomain.obtenerListaDetalleCasosDePruebaEtapa0(pSistemaId, pNit);
			if(listaDetalleCasosDePruebaEtapa0.size() > 0) {
				vRespuesta.setListaDetalleCasosDePruebaEtapa0(listaDetalleCasosDePruebaEtapa0);
				vRespuesta.setOk(true);
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_EXITOSO));
				
			}else {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_DATOS_NULOS));
			}			
		}catch (Exception e) {
			vRespuesta.setOk(false);
			LOG.info("Error en obtencion de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_ERROR));
		}
		return vRespuesta;
	}
	
	/** 
	 * Obtener el detalle del seguimiento de Certificación de Sistemas
	 * 
	 * @author: junior.flores 
	 * @Fecha: 14/06/2019
	 * @param pSistemaId, Código unico de Sistema
	 * 		  pEtapaPruebaCertificacionId, Código unico de la etapa de Certificación de Sistemas 
	 * @return   Devuelve el objeto ListaDetalleSeguimientoCertificacionSistemasDto.
	 */	
	public ListaDetalleSeguimientoCertificacionSistemasDto obtenerListaDetalleCertificacionSistemas(Long pSistemaId,Integer pEtapaPruebaCertificacionId, Long pNit){
		ListaDetalleSeguimientoCertificacionSistemasDto vRespuesta=new ListaDetalleSeguimientoCertificacionSistemasDto();
		vRespuesta.setOk(false);
		try {
			if(pSistemaId==null || pSistemaId <= 0 ) {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			} 
			else {
				List<SeguimientoDetalleCertificacionSistemasDto> ListaDetalleSeguimientoCertificacionSistemasDto = iSeguimientoCertificacionSistemasDomain.obtenerListaDetalleCertificacionSistemas(pSistemaId,pEtapaPruebaCertificacionId,pNit);
				HiloActualizarMatch hilo= new HiloActualizarMatch();
				hilo.setSistemaId(pSistemaId);
				hilo.setEtapaCertificacionSistemaId(pEtapaPruebaCertificacionId);
				hilo.setiValRec(iSeguimientoCertificacionSistemasDomain);
				hilo.start();
				hilo=null;
				if(ListaDetalleSeguimientoCertificacionSistemasDto.size()>0)
				{				
					vRespuesta.setListaSeguimientoDetalleCertificacionSistemasDto(ListaDetalleSeguimientoCertificacionSistemasDto);
					vRespuesta.setOk(true);
					LOG.info("Error al realizar la obtencion de datos");
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.LISTADO_PRUEBAS_AUTOMATICAS_DESPLEGADO_CORRECTAMENTE));
				}else
				{
					LOG.info("Error al realizar la obtencion de datos");
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumFacturacionTipoMensaje.ERROR_DATOS_NULOS_LISTADO_PRUEBAS_AUTOMATICAS));
				}
			}
		} catch (Exception e) {
			LOG.info("Error al realizar la obtencion de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.ERROR_RECUPERA_LISTADO_PRUEBAS_AUTOMATICAS));
		}
		return vRespuesta;
	}
	
	/** 
	 * Obtener la lista de contribuyentes
	 * 
	 * @author: junior.flores 
	 * @Fecha: 18/06/2019
	 * @param  pSistemaContribuyenteId, Código unico del sistema contribuyente
	 * 		   pEstadoSistemaContribuyenteId, Código unico del estado del sistema contribuyente 
	 * @return   Devuelve el objeto ListaSistemasProveedorDto.
	 */
	@Override
	public ListaSistemasProveedorDto consultaSistemasAsociadosContribuyente(Long pSistemaContribuyenteId, Integer pEstadoSistemaContribuyenteId)  
	{
		ListaSistemasProveedorDto vRespuesta = new ListaSistemasProveedorDto();
		List<SistemasContribuyentesDto> vEntidadContribuyente = new ArrayList<>();
		
		vRespuesta.setOk(false);
		try {
			
			if(pSistemaContribuyenteId==null || pSistemaContribuyenteId <= 0 || pEstadoSistemaContribuyenteId==null || pEstadoSistemaContribuyenteId <= 0) {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			} 
			else {
				vEntidadContribuyente = iSeguimientoCertificacionSistemasDomain.obtenerListaSistemaContribuyente(pSistemaContribuyenteId, pEstadoSistemaContribuyenteId);
				HiloActualizarMatch hilo= new HiloActualizarMatch();
				hilo.setSistemaId(0L);
				hilo.setEtapaCertificacionSistemaId(0);
				hilo.setiValRec(iSeguimientoCertificacionSistemasDomain);
				hilo.start();
				hilo=null;
				if(vEntidadContribuyente != null) {
					if(vEntidadContribuyente.size() > 0)	{	
						vRespuesta.setOk(true);
						vRespuesta.setLista(vEntidadContribuyente);
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_ASOCIACION_CONTRIBUYENTE_PROVEEDOR_EXITO));
					}
					else {
						vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_ASOCIACION_CONTRIBUYENTE_PROVEEDOR_VACIO));
						vRespuesta.setOk(true);
					}
				} 
				else {
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_ASOCIACION_CONTRIBUYENTE_PROVEEDOR_ERROR));
				}
			}
		} 
		catch(Exception e) {
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_ASOCIACION_CONTRIBUYENTE_PROVEEDOR_ERROR));
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));			
		}
		return vRespuesta;
	}

	/**
	 * @descripcion Obtiene lista de casos de prueba sugeridas de la etaoa 0 - Generacion de CUF
	 * @author rosario.garcia
	 * @fecha 10/07/2019
	 */
	public ListaDetalleCasosDePruebaEtapa0Dto obtenerListaCasosDePruebaSugeridasEtap0(Long pSistemaId) {
		ListaDetalleCasosDePruebaEtapa0Dto vRespuesta = new ListaDetalleCasosDePruebaEtapa0Dto();
		vRespuesta.setOk(false);
		try {
			List<DetalleCasosDePruebaEtapa0Dto> vlistaCasosDePruebaSugeridasEtapa0 = iSeguimientoCertificacionSistemasDomain.obtenerListaCasosPruebaSugeridosEtapa0(pSistemaId);
			if(vlistaCasosDePruebaSugeridasEtapa0.size() > 0) {
				vRespuesta.setListaDetalleCasosDePruebaEtapa0(vlistaCasosDePruebaSugeridasEtapa0);
				vRespuesta.setOk(true);
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_EXITOSO));
				
			}else {
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_DATOS_NULOS));
			}			
		}catch (Exception e) {
			vRespuesta.setOk(false);
			LOG.info("Error en obtencion de datos");
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumFacturacionTipoMensaje.RECUPERACION_SOLICITUD_CERTIFICACION_ERROR));
		}
		return vRespuesta;
	}
	

	/**
	 * @descripcion Obtiene lista las etapas y avance de Seguimiento Certificacion Fase II
	 * @author wilson.limachi
	 * @fecha 13/09/2019
	 */
	@Override
	public ListaSeguimientoCertificacionSistemasFaseDosDto ListaEtapaFaseDos(ListaSeguimientoCertificacionSistemasFaseDosDto vSolictud)
	{
		ListaSeguimientoCertificacionSistemasFaseDosDto vRespuesta = new ListaSeguimientoCertificacionSistemasFaseDosDto();
		List<SeguimientoCertificacionSistemasFaseDosDto> listaSeguimientoEtapasComponentes= new ArrayList<>();		
		listaSeguimientoEtapasComponentes=iSeguimientoCertificacionSistemasDomain.listaEtapaFaseDos(vSolictud);
		vRespuesta.setListaSeguimientoEtapasComponentes(listaSeguimientoEtapasComponentes);
		vRespuesta.setOk(true);
		
		return vRespuesta;
	}
}
