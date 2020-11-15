package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class ListaSistemasProveedorDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<SistemasContribuyentesDto> lista;
	private Long contribuyenteProveedorId;
	private Long contribuyenteId; 
	private Long usuarioId;
	
	public ListaSistemasProveedorDto(List<SistemasContribuyentesDto> lista, Long contribuyenteProveedorId,
			Long contribuyenteId, Long usuarioId) {
		this.lista = lista;
		this.contribuyenteProveedorId = contribuyenteProveedorId;
		this.contribuyenteId = contribuyenteId;
		this.usuarioId = usuarioId;
	}

	public ListaSistemasProveedorDto() 
	{
		this.lista = new ArrayList<>();
	}

	public List<SistemasContribuyentesDto> getLista() {
		return lista;
	}

	public void setLista(List<SistemasContribuyentesDto> lista) {
		this.lista = lista;
	}

	public Long getContribuyenteId() {
		return contribuyenteId;
	}

	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public Long getContribuyenteProveedorId() {
		return contribuyenteProveedorId;
	}

	public void setContribuyenteProveedorId(Long contribuyenteProveedorId) {
		this.contribuyenteProveedorId = contribuyenteProveedorId;
	}

	@Override
	public String toString() {
		return "RespuestaListaSistemasProveedorDto [lista=" + lista + ", contribuyenteProveedorId="
				+ contribuyenteProveedorId + ", contribuyenteId=" + contribuyenteId + ", usuarioId=" + usuarioId + "]";
	}
}
