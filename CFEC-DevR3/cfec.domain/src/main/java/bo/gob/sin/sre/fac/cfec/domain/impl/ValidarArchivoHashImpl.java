package bo.gob.sin.sre.fac.cfec.domain.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.cfec.domain.IValidarArchivoHash;

@Service
public class ValidarArchivoHashImpl implements IValidarArchivoHash {

	/**
	 * Verifica archivo Hash256.
	 * 
	 * @author wilson.limachi
	 * @param pArchivo     texto a encriptar
	 * @param pHashArchivo texto a comparar
	 * @return mensaje encriptado
	 **/
	@Override
	public boolean verificarArchivoSha256(String pArchivo, String pHashArchivo) {
		boolean vResultado = false;
		try {
			if (pArchivo != null && pHashArchivo != null && !pArchivo.isEmpty() && !pHashArchivo.isEmpty()) {
				// MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512
				String vAlgoritmo = "SHA-256";
				String vSha256 = this.sha(pArchivo, vAlgoritmo);

				if (vSha256.equals(pHashArchivo)) {
					vResultado = true;
				}
			}
		} catch (Exception e) {
			return false;
		}

		return vResultado;
	}

	public String sha(String message, String algorithm) {
		byte[] digest = null;
		byte[] buffer = message.getBytes();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.reset();
			messageDigest.update(buffer);
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println("Error creando Digest");
		}

		return toHexadecimal(digest);
	}

	private String toHexadecimal(byte[] digest) {
		// array digest == digerir
		String hash = "";
		/*
		 * ":", no es usado como operador, ":", sólo separa la definición de una
		 * variable byte llamado "aux" que se utilizará para iteración a través de la
		 * matriz de la matriz denominada "digest" #se conserva la variable aux e itera
		 * el array digest Convierte un arreglo de bytes a String usando valores
		 * hexadecimales
		 * 
		 * @param digest arreglo de bytes a convertir
		 * 
		 * @return String creado a partir de digest
		 */
		try {
			for (byte aux : digest) {
				int b = aux & 0xff;
				if (Integer.toHexString(b).length() == 1)
					hash += "0";
				hash += Integer.toHexString(b);
			}
		} catch (Exception e) {
			hash = null;
		}

		return hash;
	}

}