package bo.gob.sin.sre.fac.dto;

import java.io.Serializable;
import java.util.Date;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;
public class DetallePruebasEtapa2Dto extends ListaMensajesAplicacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long logid;  
	long casopruebaid; 
	long sistemaid; 
	int estadomatch; 
	Date fechaprueba; 
	String documentosectoridlog; 
	long idrecepcion; 
	String casoprueba; 
	String documentosectoridprueba;
	String exlnro;	
	String exlparametrodeentrada;
	String exlvalorparametro;
	String exltipovalidacion;
	String exlprueba;
	String exlresultadoesperado;
	String exlsolucion;
	String exltipoemision;
	int opcional; 
	int totalpruebasesperadas; 
	int totalpruebascorrectas; 
	int totalpruebasincorrectas;
	int porcentajecasoprueba;
	boolean checado;
	String exltipoprueba;
	
	public long getLogid() {
		return logid;
	}
	public void setLogid(long logid) {
		this.logid = logid;
	}
	public long getCasopruebaid() {
		return casopruebaid;
	}
	public void setCasopruebaid(long casopruebaid) {
		this.casopruebaid = casopruebaid;
	}
	public long getSistemaid() {
		return sistemaid;
	}
	public void setSistemaid(long sistemaid) {
		this.sistemaid = sistemaid;
	}
	public int getEstadomatch() {
		return estadomatch;
	}
	public void setEstadomatch(int estadomatch) {
		this.estadomatch = estadomatch;
	}
	public Date getFechaprueba() {
		return fechaprueba;
	}
	public void setFechaprueba(Date fechaprueba) {
		this.fechaprueba = fechaprueba;
	}
	public String getDocumentosectoridlog() {
		return documentosectoridlog;
	}
	public void setDocumentosectoridlog(String documentosectoridlog) {
		this.documentosectoridlog = documentosectoridlog;
	}
	public long getIdrecepcion() {
		return idrecepcion;
	}
	public void setIdrecepcion(long idrecepcion) {
		this.idrecepcion = idrecepcion;
	}
	public String getCasoprueba() {
		return casoprueba;
	}
	public void setCasoprueba(String casoprueba) {
		this.casoprueba = casoprueba;
	}
	public String getDocumentosectoridprueba() {
		return documentosectoridprueba;
	}
	public void setDocumentosectoridprueba(String documentosectoridprueba) {
		this.documentosectoridprueba = documentosectoridprueba;
	}
	public String getExlnro() {
		return exlnro;
	}
	public void setExlnro(String exlnro) {
		this.exlnro = exlnro;
	}
	public String getExlparametrodeentrada() {
		return exlparametrodeentrada;
	}
	public void setExlparametrodeentrada(String exlparametrodeentrada) {
		this.exlparametrodeentrada = exlparametrodeentrada;
	}
	public String getExlvalorparametro() {
		return exlvalorparametro;
	}
	public void setExlvalorparametro(String exlvalorparametro) {
		this.exlvalorparametro = exlvalorparametro;
	}
	public String getExltipovalidacion() {
		return exltipovalidacion;
	}
	public void setExltipovalidacion(String exltipovalidacion) {
		this.exltipovalidacion = exltipovalidacion;
	}
	public String getExlprueba() {
		return exlprueba;
	}
	public void setExlprueba(String exlprueba) {
		this.exlprueba = exlprueba;
	}
	public String getExlresultadoesperado() {
		return exlresultadoesperado;
	}
	public void setExlresultadoesperado(String exlresultadoesperado) {
		this.exlresultadoesperado = exlresultadoesperado;
	}
	public String getExlsolucion() {
		return exlsolucion;
	}
	public void setExlsolucion(String exlsolucion) {
		this.exlsolucion = exlsolucion;
	}
	public String getExltipoemision() {
		return exltipoemision;
	}
	public void setExltipoemision(String exltipoemision) {
		this.exltipoemision = exltipoemision;
	}
	public int getOpcional() {
		return opcional;
	}
	public void setOpcional(int opcional) {
		this.opcional = opcional;
	}
	public int getTotalpruebasesperadas() {
		return totalpruebasesperadas;
	}
	public void setTotalpruebasesperadas(int totalpruebasesperadas) {
		this.totalpruebasesperadas = totalpruebasesperadas;
	}
	public int getTotalpruebascorrectas() {
		return totalpruebascorrectas;
	}
	public void setTotalpruebascorrectas(int totalpruebascorrectas) {
		this.totalpruebascorrectas = totalpruebascorrectas;
	}
	public int getTotalpruebasincorrectas() {
		return totalpruebasincorrectas;
	}
	public void setTotalpruebasincorrectas(int totalpruebasincorrectas) {
		this.totalpruebasincorrectas = totalpruebasincorrectas;
	}
	public int getPorcentajecasoprueba() {
		return porcentajecasoprueba;
	}
	public void setPorcentajecasoprueba(int porcentajecasoprueba) {
		this.porcentajecasoprueba = porcentajecasoprueba;
	}
	public boolean isChecado() {
		return checado;
	}
	public void setChecado(boolean checado) {
		this.checado = checado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getExltipoprueba() {
		return exltipoprueba;
	}
	public void setExltipoprueba(String exltipoprueba) {
		this.exltipoprueba = exltipoprueba;
	}
		
	
	
}
