package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaOperacionDto extends ListaMensajesAplicacion   implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long datosEntradaUsuarioRegistro;
	private Long datosEntradaArchivoId;
	private Long datosEntradaId;

	@Override
	public String toString() {
		return "RespuestaOperacion []";
	}

	public RespuestaOperacionDto() {
	}

	public Long getDatosEntradaUsuarioRegistro() {
		return datosEntradaUsuarioRegistro;
	}

	public void setDatosEntradaUsuarioRegistro(Long datosEntradaUsuarioRegistro) {
		this.datosEntradaUsuarioRegistro = datosEntradaUsuarioRegistro;
	}

	public Long getDatosEntradaArchivoId() {
		return datosEntradaArchivoId;
	}

	public void setDatosEntradaArchivoId(Long datosEntradaArchivoId) {
		this.datosEntradaArchivoId = datosEntradaArchivoId;
	}

	/**
	 * @return the datosEntradaId
	 */
	public Long getDatosEntradaId() {
		return datosEntradaId;
	}

	/**
	 * @param datosEntradaId the datosEntradaId to set
	 */
	public void setDatosEntradaId(Long datosEntradaId) {
		this.datosEntradaId = datosEntradaId;
	}	
}
