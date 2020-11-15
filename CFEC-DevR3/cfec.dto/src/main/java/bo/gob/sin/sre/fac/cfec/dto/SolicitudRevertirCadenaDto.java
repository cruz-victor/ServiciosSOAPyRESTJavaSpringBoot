package bo.gob.sin.sre.fac.cfec.dto;

//IASC
public class SolicitudRevertirCadenaDto {

	private String codigo;

	public SolicitudRevertirCadenaDto(String codigo) {
		this.codigo = codigo;
	}

	public SolicitudRevertirCadenaDto() {
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "SolicitudRevertirCadenaDto [codigo=" + codigo + "]";
	}
}
