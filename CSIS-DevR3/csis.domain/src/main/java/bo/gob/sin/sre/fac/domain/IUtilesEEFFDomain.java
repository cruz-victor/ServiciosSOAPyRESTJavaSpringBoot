package bo.gob.sin.sre.fac.domain;

public interface IUtilesEEFFDomain {
	
	public String encoderB64DomainApp(byte[] pSolicitud);
	public byte[] decoderB64DomainApp(String pSolicitud);
	public byte[] compressFile(byte[] data);
}
