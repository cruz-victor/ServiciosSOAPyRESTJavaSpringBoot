package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class SolicitudNotificacionDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long usuarioId;
	private Integer oficinaId;
	private Integer dependenciaId;
	public long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Integer getOficinaId() {
		return oficinaId;
	}
	public void setOficinaId(Integer oficinaId) {
		this.oficinaId = oficinaId;
	}
	public Integer getDependenciaId() {
		return dependenciaId;
	}
	public void setDependenciaId(Integer dependenciaId) {
		this.dependenciaId = dependenciaId;
	}


//	
//
//	
//    vParametros.put("usuario", obtenerObjectUsuario(pSolicitud.getUsuario()));//DtoUsuario
//    vParametros.put("ifc", pSolicitud.getIfc());
//    vParametros.put("documentoAdjuntoId", pSolicitud.getDocumentoAdjuntoId());

}
