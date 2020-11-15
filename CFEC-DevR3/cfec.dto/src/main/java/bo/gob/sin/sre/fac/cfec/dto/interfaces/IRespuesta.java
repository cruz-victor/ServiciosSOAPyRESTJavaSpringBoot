package bo.gob.sin.sre.fac.cfec.dto.interfaces;

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bo.gob.sin.sre.fac.cfec.dto.RecepcionErrorDetalleDto;

@XmlJavaTypeAdapter(RespuestaAdapter.class)
public interface IRespuesta {

	public int getCodigoEstado();

	public long getCodigoRecepcion();

	public List<Integer> getListaCodigosRespuestas();

	public List<String> getListaDescripcionesRespuestas();

	public List<RecepcionErrorDetalleDto> getListaErroresDetalles();

	public boolean isTransaccion();

}