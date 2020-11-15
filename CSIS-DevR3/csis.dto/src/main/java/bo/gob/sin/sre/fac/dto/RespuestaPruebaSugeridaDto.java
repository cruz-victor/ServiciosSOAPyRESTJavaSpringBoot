package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

public class RespuestaPruebaSugeridaDto extends ListaMensajesAplicacion implements Serializable  {	
	private static final long serialVersionUID = 1L;
	private int resultado;

	public int getResultado() {
		return resultado;
	}
	
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}			
}
