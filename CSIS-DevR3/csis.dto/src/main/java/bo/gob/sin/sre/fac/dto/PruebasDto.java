package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.List;

public class PruebasDto  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//IASC
	private long pruebaId;
	private String nombre;
	private int tipoPruebaId;
	
	private List<PruebasDto> lista;

	public long getpruebaId() {
		return pruebaId;
	}

	public void setpruebaId(long pruebaId) {
		this.pruebaId = pruebaId;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}	
	
	public int getTipoPruebaId() {
		return tipoPruebaId;
	}

	public void setTipoPruebaId(int tipoPruebaId) {
		this.tipoPruebaId = tipoPruebaId;
	}

	public List<PruebasDto> getLista() {
		return lista;
	}

	public void setLista(List<PruebasDto> lista) {
		this.lista = lista;
	}
	
	
}
