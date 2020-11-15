package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "SRE_FAC_ARCHIVOS_TMP", schema="SRE_RECAUDACIONES")
public class SreArchivosTmp implements Serializable {
	
	//IASC - Para guardar los archivos de la huella del sistema - 16/04/2018
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "ARCHIVO_ID")
	private Long archivoId;

	@Column(name = "ARCHIVO")
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] archivo;
	
	@Column(name = "ESTADO_ID")
	private String estadoId;

	public SreArchivosTmp(Long archivoId, byte[] archivo, String estadoId) {
		this.archivoId = archivoId;
		this.archivo = archivo;
		this.estadoId = estadoId;
	}

	public SreArchivosTmp() {
	}

	public Long getArchivoId() {
		return archivoId;
	}

	public void setArchivoId(Long archivoId) {
		this.archivoId = archivoId;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "SreArchivos [archivoId=" + archivoId + ", archivo=" + Arrays.toString(archivo) + ", estadoId="
				+ estadoId + "]";
	}

}