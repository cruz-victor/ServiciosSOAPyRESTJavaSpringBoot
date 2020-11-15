package bo.gob.sin.sre.fac.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.scn.empa.caco.dto.ContribuyenteMasDireccionFiscalDto;
import bo.gob.sin.scn.empa.casu.dto.SucursalContribuyenteDto;
import bo.gob.sin.scn.empa.ccoc.dto.ActividadesXNitDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitActivoDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitDatosBasicosMasDireccionFiscalDto;
import bo.gob.sin.scn.empa.ccoc.dto.RespuestaServicioDto;
import bo.gob.sin.scn.empa.ccoc.dto.TallerContribuyenteListaDto;
import bo.gob.sin.scn.empa.para.dto.ResultadoGenericoDto;
import bo.gob.sin.scn.empa.para.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sen.enco.model.Json;
import bo.gob.sin.sre.fac.dto.ListaSistemasSolicitudCertificacionDto;
import bo.gob.sin.sre.fac.dto.RespuestaDetalleHuellaDto;
import bo.gob.sin.sre.fac.dto.RespuestaOperacionDto;
import bo.gob.sin.sre.fac.dto.SucursalContribuyenteListaDTO;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import io.undertow.util.StatusCodes;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ServiciosFacturacionQueryRestClient extends PeticionesRest  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String getHost() {
		return host;
	}

	public OkHttpClient getClient() {
		return client;
	}
	
	/**
	 * Realiza el listado de sistemas
	 * 
	 * @author: Wilson Limachi
	 * @Fecha: 23/09/2019
	 * @param pSolicitud, objeto de tipo solicitud
	 * @return Devuelve objeto Dto de tipo ListaSistemasSolicitudCertificacionDto.*
	 */
	public ListaSistemasSolicitudCertificacionDto obtenerListadoSistemasFase2(ListaSistemasSolicitudCertificacionDto  pSolicitud) 
	{
		configFacturacionCsisRestQuery();
		ListaSistemasSolicitudCertificacionDto resultado = new ListaSistemasSolicitudCertificacionDto();

		try 
		{
			String json = "";
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(pSolicitud);
			String ruta = host + "/SeguimientoCertificacionPruebas/obtenerListadoSistemasFase2";
			TypeReference<ListaSistemasSolicitudCertificacionDto> typeRef = new TypeReference<ListaSistemasSolicitudCertificacionDto>() {
            };

            Response response = ejecutarPostConParametroMapper(ruta, json);

            if (response.code() == StatusCodes.NOT_FOUND) 
            {
            	resultado = new ListaSistemasSolicitudCertificacionDto();
            } 
            else 
            {
            	resultado = Json.serializer().fromInputStream(response.body().byteStream(), typeRef);
            }
	    } 
		catch (Exception e) 
		{
	         resultado = new ListaSistemasSolicitudCertificacionDto();
	    }
	
		return resultado;
	}
}