package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;


public class ConsultaSistemasCertificadosDto extends ListaMensajesAplicacion  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<SistemasCertificadosDto> sistemasCertificados;

	public List<SistemasCertificadosDto> getSistemasCertificados() {
		return sistemasCertificados;
	}

	public void setSistemasCertificados(List<SistemasCertificadosDto> sistemasCertificados) {
		this.sistemasCertificados = sistemasCertificados;
	}

	@Override
	public String toString() {
		return "ConsultaSistemasCertificadosDto [sistemasCertificados=" + sistemasCertificados + "]";
	}

}
