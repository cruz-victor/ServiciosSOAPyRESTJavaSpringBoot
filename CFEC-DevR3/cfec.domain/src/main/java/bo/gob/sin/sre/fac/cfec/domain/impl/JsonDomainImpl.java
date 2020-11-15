package bo.gob.sin.sre.fac.cfec.domain.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.cfec.dao.IJsonDao;
import bo.gob.sin.sre.fac.cfec.domain.IJsonDomain;

@Service
@Transactional
public class JsonDomainImpl implements IJsonDomain {

	@Autowired
	private IJsonDao iJsonDao;

	@Override
	public String recepcionEtapa1(String pSolicitud) {
		return iJsonDao.recepcionBdEtapa1(pSolicitud);
	}

	@Override
	public String recepcionEtapa2(String pSolicitud) {
		return iJsonDao.recepcionBdEtapa2(pSolicitud);
	}

	@Override
	public String validacionRecepcionXml(String pSolicitud) {
		return iJsonDao.validacionRecepcionBd(pSolicitud);
	}

	@Override
	public String validacionRecepcionPaqueteXml(String pSolicitud) {
		return iJsonDao.validacionRecepcionPaqueteBd(pSolicitud);
	}

	@Override
	public String anulacionXml(String pSolicitud) {
		return iJsonDao.anulacionBd(pSolicitud);
	}

	@Override
	public String validacionAnulacionXml(String pSolicitud) {
		return iJsonDao.validacionAnulacionBd(pSolicitud);
	}

	@Override
	public Integer validarNitBd(long pNit) {
		return iJsonDao.validarNitBd(pNit);
	}

	@Override
	public void registrarErroresBd(String pFactura, String pListaErrores) {
		iJsonDao.registrarErroresBd(pFactura, pListaErrores);
	}

	@Override
	public String obtenerDescripcionCodigosRespuesta(String pSolicitud) {
		return iJsonDao.obtenerDescripcionCodigosRespuesta(pSolicitud);
	}

	@Override
	public String registroLogCertificacionSistemas(String pSolicitud) {
		return iJsonDao.registroLogCertificacionSistemasBd(pSolicitud);
	}

	@Override
	public void registrarLogEtapa6EnvioPaquetesBd(String pSolicitud) {
		iJsonDao.registrarLogEtapa6EnvioPaquetesBd(pSolicitud);
	}

	@Override
	public String recepcionEtapa1Masivo(String pSolicitud) {
		return iJsonDao.recepcionBdEtapa1Masivo(pSolicitud);
	}

	@Override
	public String recepcionEtapa2Masivo(String pSolicitud) {
		return iJsonDao.recepcionBdEtapa2Masivo(pSolicitud);
	}

	@Override
	public Integer verificarExistenciaCodigoRecepcion(int pTipoDocSector, long pCodigoRecepcion) {
		return iJsonDao.verificarExistenciaCodigoRecepcion(pTipoDocSector, pCodigoRecepcion);
	}

	@Override
	public Long obtenerCodigoRecIndBd(String pSolicitud) {
		return iJsonDao.obtenerCodigoRecIndBd(pSolicitud);
	}

	@Override
	public String validacionRecepcionMasivaXml(String pSolicitud) {
		return iJsonDao.validacionRecepcionMasivoBd(pSolicitud);
	}

	@Override
	public int obtenerCantidadMaximaMasivo(long pContribuyenteId, int pSucursal) {
		return iJsonDao.obtenerCantidadMaximoMasivo(pContribuyenteId, pSucursal);
	}

	@Override
	public void registrarLogPruebaObligatoriaEtapa6(String pSolicitud) {
		iJsonDao.registrarLogPruebaObligatoriaEtapa6(pSolicitud);
	}

	@Override
	public String obtenerFacturaDatosParcial(String pSolicitud) {
		return iJsonDao.obtenerFacturaDatosParcial(pSolicitud);
	}

	@Override
	public void registrarLogExcepcionBDErrores(String pSolicitud) {
		iJsonDao.registrarLogExcepcionBDErrores(pSolicitud);
	}

	@Override
	public String obtenerFacturaPush(int pCodigoDocumentoSector, long pRecepcionId, String pCuf) {
		return iJsonDao.obtenerFacturaPush(pCodigoDocumentoSector, pRecepcionId, pCuf);
	}

	@Override
	public String modificarEtapaProcesada(int pTipoDocumentoFiscal, int pSector, long pCodigoRecepcion,
			int estadoPaquete) {
		return iJsonDao.modificarBdEtapaProcesada(pTipoDocumentoFiscal, pSector, pCodigoRecepcion, estadoPaquete);
	}

	@Override
	public long obtenerRecepcionAnulacion(String pCuf) {
		return iJsonDao.obtenerRecepcionAnulacion(pCuf);
	}

	@Override
	public String modificarRecepcionPaqueteCantidadArchivo(long pArchivoValidoId, long pRecepcionPaqueteId,
			int pCantidadPaquete, int pSector) {
		return iJsonDao.modificarRecepcionPaqueteCantidadArchivo(pArchivoValidoId, pRecepcionPaqueteId,
				pCantidadPaquete, pSector);
	}

	@Override
	public Integer obtenerCantidadMaximaPaquete() {
		return iJsonDao.obtenerCantidadMaximaPaquete();
	}
	
	@Override
	public String recepcionAnulacionXml(String pSolicitud) {
		return iJsonDao.validacionRecepcionAnulacionBd(pSolicitud);
	}
}