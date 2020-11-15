package bo.gob.sin.sre.fac.cfec.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author edwin.coro
 * @fecha 01/12/2018
 */
@Entity
@Table(name = "SRE_FAC_ARCHIVOS_XML_VALIDOS", schema = "SRE_RECAUDACIONES")

public class SreFacArchivoXmlValido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARCHIVO_XML_VALIDO_ID")
	private long archivoXmlValidoId;

	@Column(name = "ARCHIVO")
	private byte[] archivo;

	@Column(name = "EXTESION", length = 5)
	private String extension;

	@Column(name = "MIME", length = 100)
	private String mime;

	@Column(name = "ESTADO_ID", length = 2)
	private String estadoId;

	public SreFacArchivoXmlValido() {
		super();
	}

	public SreFacArchivoXmlValido(long archivoXmlValidoId, byte[] archivo, String extension, String mime,
			String estadoId) {
		super();
		this.archivoXmlValidoId = archivoXmlValidoId;
		this.archivo = archivo;
		this.extension = extension;
		this.mime = mime;
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "SreArchivoXmlInvalido [archivoXmlValidoId=" + archivoXmlValidoId + ", archivo="
				+ Arrays.toString(archivo) + ", extension=" + extension + ", mime=" + mime + ", estadoId=" + estadoId
				+ "]";
	}

	/**
	 * @return the archivoXmlInvalidoId
	 */
	public long getArchivoXmlValidoId() {
		return archivoXmlValidoId;
	}

	/**
	 * @param archivoXmlInvalidoId the archivoXmlInvalidoId to set
	 */
	public void setArchivoXmlValidoId(long archivoXmlValidoId) {
		this.archivoXmlValidoId = archivoXmlValidoId;
	}

	/**
	 * @return the archivo
	 */
	public byte[] getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the mime
	 */
	public String getMime() {
		return mime;
	}

	/**
	 * @param mime the mime to set
	 */
	public void setMime(String mime) {
		this.mime = mime;
	}

	/**
	 * @return the estadoId
	 */
	public String getEstadoId() {
		return estadoId;
	}

	/**
	 * @param estadoId the estadoId to set
	 */
	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}