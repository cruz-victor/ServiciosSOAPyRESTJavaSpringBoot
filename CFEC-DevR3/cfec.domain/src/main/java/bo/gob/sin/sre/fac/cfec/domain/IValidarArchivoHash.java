package bo.gob.sin.sre.fac.cfec.domain;

public interface IValidarArchivoHash {

	/**
	 * Verifica archivo Hash256.
	 * 
	 * @author wilson.limachi
	 * @param pArchivo     texto a encriptar
	 * @param pHashArchivo texto a comparar
	 * @return mensaje encriptado
	 **/
	public boolean verificarArchivoSha256(String pArchivo, String pHashArchivo);

}