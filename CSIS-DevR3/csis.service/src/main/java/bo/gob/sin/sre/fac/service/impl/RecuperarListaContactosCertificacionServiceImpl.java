package bo.gob.sin.sre.fac.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.domain.IContactosCertificacionesConsultaDomain;
import bo.gob.sin.sre.fac.dto.DatosContactosDto;
import bo.gob.sin.sre.fac.dto.RecuperarListaContactosCertificacion;
import bo.gob.sin.sre.fac.model.SreContactosCertificaciones;
import bo.gob.sin.sre.fac.service.IParametricasDomain;
import bo.gob.sin.sre.fac.service.IRecuperarListaContactosCertificacionService;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import bo.gob.sin.str.enumeradores.recaudaciones.EnumFacturacionTipoMensaje;

@Service
@Transactional
public class RecuperarListaContactosCertificacionServiceImpl implements IRecuperarListaContactosCertificacionService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RecuperarListaContactosCertificacionServiceImpl.class);

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;
	
	@Autowired
	private IContactosCertificacionesConsultaDomain iContactosCertificacionesConsultaDomain;
	
	@Autowired
	private IParametricasDomain iClasificadorDomain;

	@Override
	public RecuperarListaContactosCertificacion recuperarListaContactosCertificacion(long pSolicitudCertificacionId) {
		LOG.info("Ingresando recuperarListaContactosCertificacion ");

		RecuperarListaContactosCertificacion vRespuesta = new RecuperarListaContactosCertificacion();
		List<SreContactosCertificaciones> vLista = new ArrayList<SreContactosCertificaciones>();
		List<DatosContactosDto> vListaDto = new ArrayList<DatosContactosDto>();
		ClasificadorDto vClasificador= new ClasificadorDto();

		try {
			if (pSolicitudCertificacionId > 0) {
				vLista = iContactosCertificacionesConsultaDomain.obtieneListaContactosSolicitud(pSolicitudCertificacionId);
				if (!vLista.isEmpty()) {
					for (SreContactosCertificaciones vDatos : vLista) {
						DatosContactosDto dto = new DatosContactosDto();
						ModelMapper vMapper = new ModelMapper();
						vMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
						dto = vMapper.map(vDatos, DatosContactosDto.class);
						
						vClasificador = iClasificadorDomain.obtenerDatosParametrica(vDatos.getTipoDocumentoIdentidadId());
						dto.setTipoDocumentoIdentidad(vClasificador.getDescripcion());
					
						vListaDto.add(dto);
						LOG.info("vListaDto:" + vListaDto.toString());
					}

					vRespuesta.setLista(vListaDto);
					vRespuesta.setOk(true);

				} else {
					vRespuesta.setOk(false);
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_CONTACTOS_CERTIFICACION_VACIO));
				}
			} else {
				LOG.info("No se pudo recuperar los datos incorrectos de envio.");
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,EnumFacturacionTipoMensaje.VALIDACION_DATOS_ENTRADA_RECHAZADO));
			}
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(vRespuesta));
			vRespuesta.setOk(false);
		}

		LOG.info("Saliendo recuperarListaContactosCertificacion vRespuesta={}", vRespuesta);
		return vRespuesta;
	}
}
