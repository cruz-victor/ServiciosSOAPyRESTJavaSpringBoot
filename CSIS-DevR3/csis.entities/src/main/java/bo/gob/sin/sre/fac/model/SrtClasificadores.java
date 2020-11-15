package bo.gob.sin.sre.fac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "STR_CPS_CLASIFICADORES", schema="STR_TRANSVERSALES")
@NamedQuery(name = "SrtClasificadores.java.findAll", query = "SELECT c FROM SrtClasificadores c")
public class SrtClasificadores implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLASIFICADOR_ID")
	private Integer clasificadorId;
	
	@Column(name = "CODIGO_CLASIFICADOR")
	private Integer codigoClasificador;
	
	@Column(name = "CODIGO_CLASIFICADOR_EQUIVALENTE")
	private Integer codigoClasificadorEquivalente;
			
	@Column(name = "AGRUPADOR")
	private String agrupador;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "ABREVIATURA")
	private String abreviatura;
	
	@Column(name = "ESTADO_ID")
	private String estadoId;
		
	public SrtClasificadores(Integer clasificadorId, Integer codigoClasificador, Integer codigoClasificadorEquivalente,
			String agrupador, String descripcion, String abreviatura, String estadoId) {
		super();
		this.clasificadorId = clasificadorId;
		this.codigoClasificador = codigoClasificador;
		this.codigoClasificadorEquivalente = codigoClasificadorEquivalente;
		this.agrupador = agrupador;
		this.descripcion = descripcion;
		this.abreviatura = abreviatura;
		this.estadoId = estadoId;
	}

	public SrtClasificadores() {
	}

	public Integer getClasificadorId() {
		return clasificadorId;
	}

	public void setClasificadorId(Integer clasificadorId) {
		this.clasificadorId = clasificadorId;
	}

	public Integer getCodigoClasificador() {
		return codigoClasificador;
	}

	public void setCodigoClasificador(Integer codigoClasificador) {
		this.codigoClasificador = codigoClasificador;
	}

	public Integer getCodigoClasificadorEquivalente() {
		return codigoClasificadorEquivalente;
	}

	public void setCodigoClasificadorEquivalente(Integer codigoClasificadorEquivalente) {
		this.codigoClasificadorEquivalente = codigoClasificadorEquivalente;
	}

	public String getAgrupador() {
		return agrupador;
	}

	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	@Override
	public String toString() {
		return "SreClasificadores [clasificadorId=" + clasificadorId + ", codigoClasificador=" + codigoClasificador
				+ ", codigoClasificadorEquivalente=" + codigoClasificadorEquivalente + ", agrupador=" + agrupador
				+ ", descripcion=" + descripcion + ", abreviatura=" + abreviatura + ", estadoId=" + estadoId + "]";
	}
	

}
