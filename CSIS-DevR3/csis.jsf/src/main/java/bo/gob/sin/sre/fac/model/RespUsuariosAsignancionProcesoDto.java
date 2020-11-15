package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.sap.cder.dto.UsuarioAsignacionDto;
import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespUsuariosAsignancionProcesoDto  extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	List<UsuarioAsignacionDto> usuariosAsignacion;

	public List<UsuarioAsignacionDto> getUsuariosAsignacion() {
		return usuariosAsignacion;
	}

	public void setUsuariosAsignacion(List<UsuarioAsignacionDto> usuariosAsignacion) {
		this.usuariosAsignacion = usuariosAsignacion;
	}

	public RespUsuariosAsignancionProcesoDto() {
		this.usuariosAsignacion=new ArrayList<>();
	}	
}
