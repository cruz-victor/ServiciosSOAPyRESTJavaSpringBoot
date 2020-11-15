package bo.gob.sin.sre.fac.domain.impl;



import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.domain.IUtilesEEFFDomain;

@Service
@Transactional
public class UtilesEEFFDomainImpl implements IUtilesEEFFDomain, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(UtilesEEFFDomainImpl.class);
	
	@Override
	public String encoderB64DomainApp(byte[] pSolicitud) {
		String vResultado = Base64.getEncoder().encodeToString(pSolicitud);
		return vResultado;
	}

	@Override
	public byte[] decoderB64DomainApp(String pSolicitud) {
		byte[] vResultado = Base64.getDecoder().decode(pSolicitud);
		return vResultado;
	}
	
	public byte[] compressFile(byte[] data) {

        byte[] compressed=null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);
            GZIPOutputStream gzip = new GZIPOutputStream(baos);
            gzip.write(data);
            gzip.close();
            compressed = baos.toByteArray();
            baos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return compressed;
    }
	
	
}
