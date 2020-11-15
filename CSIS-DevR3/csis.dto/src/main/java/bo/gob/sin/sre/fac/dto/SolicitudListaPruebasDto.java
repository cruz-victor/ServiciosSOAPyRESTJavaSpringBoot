package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.List;

public class SolicitudListaPruebasDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long pruebaId;
	private String nombre;
	private String descripcion;
	private Integer tipoPrueba;
	private String estadoId;
	
	private List<SolicitudCertificacionDto> Lista;
	
	public Long getPruebaId() {
		return pruebaId;
	}
	public void setPruebaId(Long pruebaId) {
		this.pruebaId = pruebaId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getTipoPrueba() {
		return tipoPrueba;
	}
	public void setTipoPrueba(Integer tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}
	public String getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}
	public List<SolicitudCertificacionDto> getLista() {
		return Lista;
	}
	public void setLista(List<SolicitudCertificacionDto> lista) {
		Lista = lista;
	}

	
	
	
}
