package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

//IASC
public class RespuestaListaDatosSistemaContribuyenteDto extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<SistemasContribuyentesDto> listaDatosSistemaContribuyenteDto;
	Long contribuyenteId; 
	Integer estadoSistemaContribuyenteId;
	
	public List<SistemasContribuyentesDto> getListaDatosSistemaContribuyenteDto() {
		return listaDatosSistemaContribuyenteDto;
	}
	public void setListaDatosSistemaContribuyenteDto(List<SistemasContribuyentesDto> listaDatosSistemaContribuyenteDto) {
		this.listaDatosSistemaContribuyenteDto = listaDatosSistemaContribuyenteDto;
	}
	public Long getContribuyenteId() {
		return contribuyenteId;
	}
	public void setContribuyenteId(Long contribuyenteId) {
		this.contribuyenteId = contribuyenteId;
	}
	public Integer getEstadoSistemaContribuyenteId() {
		return estadoSistemaContribuyenteId;
	}
	public void setEstadoSistemaContribuyenteId(Integer estadoSistemaContribuyenteId) {
		this.estadoSistemaContribuyenteId = estadoSistemaContribuyenteId;
	}
	public RespuestaListaDatosSistemaContribuyenteDto(List<SistemasContribuyentesDto> listaDatosSistemaContribuyenteDto,
			Long contribuyenteId, Integer estadoSistemaContribuyenteId) {
		super();
		this.listaDatosSistemaContribuyenteDto = listaDatosSistemaContribuyenteDto;
		this.contribuyenteId = contribuyenteId;
		this.estadoSistemaContribuyenteId = estadoSistemaContribuyenteId;
	}
	
	public RespuestaListaDatosSistemaContribuyenteDto() 
	{
		this.listaDatosSistemaContribuyenteDto = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return "RespuestaListaDatosSistemaContribuyenteDto [listaDatosSistemaContribuyenteDto="
				+ listaDatosSistemaContribuyenteDto + ", contribuyenteId=" + contribuyenteId
				+ ", estadoSistemaContribuyenteId=" + estadoSistemaContribuyenteId + "]";
	}
}
