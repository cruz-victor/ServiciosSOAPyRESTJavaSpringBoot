package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.Date;

public class ModalidadDto implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String modalidad;
	private Long modalidadId;
	
	public ModalidadDto() {
		
	}

	

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Long getModalidadId() {
		return modalidadId;
	}

	public void setModalidadId(Long modalidadId) {
		this.modalidadId = modalidadId;
	}



	@Override
	public String toString() {
		return "ModalidadDto [modalidad=" + modalidad + ", modalidadId=" + modalidadId + "]";
	}
}
