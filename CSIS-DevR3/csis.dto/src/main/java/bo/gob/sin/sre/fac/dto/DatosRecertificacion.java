package bo.gob.sin.sre.fac.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

public class DatosRecertificacion extends ListaMensajesAplicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	List<ClasificadorDto> salidaClasificadorModalidades;
	List<ClasificadorDto> salidaClasificadorTipoDocSistemas;

	long entradaSistemaId; 
	long entradaSolicitudCertificacionId;
	
	public DatosRecertificacion() {
		this.salidaClasificadorModalidades= new ArrayList<>();
		this.salidaClasificadorTipoDocSistemas= new ArrayList<>();
	}

	public List<ClasificadorDto> getSalidaClasificadorModalidades() {
		return salidaClasificadorModalidades;
	}

	public void setSalidaClasificadorModalidades(List<ClasificadorDto> salidaClasificadorModalidades) {
		this.salidaClasificadorModalidades = salidaClasificadorModalidades;
	}

	public List<ClasificadorDto> getSalidaClasificadorTipoDocSistemas() {
		return salidaClasificadorTipoDocSistemas;
	}

	public void setSalidaClasificadorTipoDocSistemas(List<ClasificadorDto> salidaClasificadorTipoDocSistemas) {
		this.salidaClasificadorTipoDocSistemas = salidaClasificadorTipoDocSistemas;
	}

	public long getEntradaSistemaId() {
		return entradaSistemaId;
	}

	public void setEntradaSistemaId(long entradaSistemaId) {
		this.entradaSistemaId = entradaSistemaId;
	}

	public long getEntradaSolicitudCertificacionId() {
		return entradaSolicitudCertificacionId;
	}

	public void setEntradaSolicitudCertificacionId(long entradaSolicitudCertificacionId) {
		this.entradaSolicitudCertificacionId = entradaSolicitudCertificacionId;
	}

}
