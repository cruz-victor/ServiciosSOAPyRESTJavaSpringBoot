package bo.gob.sin.sre.fac.cfec.dto;

import java.io.Serializable;

public class SolicitudFacturaParcialDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int pTipoDocumentoFiscal;
	private int pTipoDocumentoSector;
	private long pNumeroFactura;
	private long pNitEmisor;
	private String pCuf;

	public SolicitudFacturaParcialDto() {
	}

	@Override
	public String toString() {
		return "SolicitudFacturaParcialDto [pTipoDocumentoFiscal=" + pTipoDocumentoFiscal + ", pTipoDocumentoSector="
				+ pTipoDocumentoSector + ", pNumeroFactura=" + pNumeroFactura + ", pNitEmisor=" + pNitEmisor + ", pCuf="
				+ pCuf + "]";
	}

	/**
	 * @return the pTipoDocumentoFiscal
	 */
	public int getpTipoDocumentoFiscal() {
		return pTipoDocumentoFiscal;
	}

	/**
	 * @param pTipoDocumentoFiscal the pTipoDocumentoFiscal to set
	 */
	public void setpTipoDocumentoFiscal(int pTipoDocumentoFiscal) {
		this.pTipoDocumentoFiscal = pTipoDocumentoFiscal;
	}

	/**
	 * @return the pTipoDocumentoSector
	 */
	public int getpTipoDocumentoSector() {
		return pTipoDocumentoSector;
	}

	/**
	 * @param pTipoDocumentoSector the pTipoDocumentoSector to set
	 */
	public void setpTipoDocumentoSector(int pTipoDocumentoSector) {
		this.pTipoDocumentoSector = pTipoDocumentoSector;
	}

	/**
	 * @return the pNumeroFactura
	 */
	public long getpNumeroFactura() {
		return pNumeroFactura;
	}

	/**
	 * @param pNumeroFactura the pNumeroFactura to set
	 */
	public void setpNumeroFactura(long pNumeroFactura) {
		this.pNumeroFactura = pNumeroFactura;
	}

	/**
	 * @return the pNitEmisor
	 */
	public long getpNitEmisor() {
		return pNitEmisor;
	}

	/**
	 * @param pNitEmisor the pNitEmisor to set
	 */
	public void setpNitEmisor(long pNitEmisor) {
		this.pNitEmisor = pNitEmisor;
	}

	/**
	 * @return the pCuf
	 */
	public String getpCuf() {
		return pCuf;
	}

	/**
	 * @param pCuf the pCuf to set
	 */
	public void setpCuf(String pCuf) {
		this.pCuf = pCuf;
	}

}