package bo.gob.sin.sre.fac.service;

import java.util.List;

import bo.gob.sin.sre.fac.dto.ListaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.ListaRegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.RegistroHuellasDigitalesDto;
import bo.gob.sin.sre.fac.dto.ReporteComponentesRegistradosCtbteDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.model.SreComponentesCertificadosTmp;

public interface IRegistroHuellasSistemaContribuyenteService {

	public RegistroHuellasDigitalesDto registrarHuellaDigitalSistemaContribuyente(RegistroHuellasDigitalesDto pSolicitud);
	
	public RegistroHuellasDigitalesDto registrarHuellasDigitalesSistemas(ListaRegistroHuellasDigitalesDto pSolicitud);
	
	public ReporteComponentesRegistradosCtbteDto obtenerListaComponentesRegistradosPorContribuyente(Long pSistemaId);
	
	public void actualizarEstadoComponenteCertificado(List<SreComponentesCertificadosTmp> pListaComponenteCertificado);
	
	public ReporteComponentesRegistradosCtbteDto obtenerListaComponentesRegistradosPorContribuyente(Long pSistemaId, Long pIdContribuyente);
	
	public ListaDetalleHuellaDto  obtieneComponentesArchivosCertificados(Long pSistemaId, Long pSolicitudCertificacionId, Long pContribuyenteId);
	
	/***
	 * @Descripcion: Cambia el estado de cancelado a los archivos componente certificados.
	 * @author wilson.limachi
	 * @return dto respuesta
	 * @fecha 24/09/2019
	 */
	public RespuestaOperacionDto actualizaComponentesArchivosCertificados(Long pArchivoId, Long pUsuarioId);
}
