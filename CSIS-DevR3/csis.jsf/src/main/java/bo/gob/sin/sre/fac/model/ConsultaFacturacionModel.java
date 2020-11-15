package bo.gob.sin.sre.fac.model;





import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sre.fac.dto.RespuestaConsultaFacturacionDto;
import bo.gob.sin.sre.fac.dto.SolicitudConsultaFacturacionDto;
import bo.gob.sin.sre.fac.service.ServiciosConsultaFacturacionRestClient;


@ManagedBean(name = "consultaFacturacionModel")
@ViewScoped
public class ConsultaFacturacionModel implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private long nit;
	private String grupo;
	private String modalidadDestino;
	private String fechaAdecuacion;
	private String reporte;
	
	private String reporteBase64 = "";
	
	private boolean buscadoExito;
	
	@PostConstruct
    public void init() {
		
		this.limpiar();
		this.buscadoExito = false;
    }
	
	public void reporteVoucherCpt()  {
				
		this.reporteBase64=Base64.getEncoder().encodeToString(decompressFileService(Base64.getDecoder().decode(reporte)));

	}

	public RespuestaConsultaFacturacionDto consultaFacturacion(long nit) {
		
		ServiciosConsultaFacturacionRestClient serviciosEEFF= new ServiciosConsultaFacturacionRestClient();
		RespuestaConsultaFacturacionDto resultado = new RespuestaConsultaFacturacionDto();
		
			SolicitudConsultaFacturacionDto solicitud=new SolicitudConsultaFacturacionDto();
			solicitud.setNit(nit);
			resultado=serviciosEEFF.consultaFacturacion(solicitud);
		
		return resultado;
	}
	
	public void limpiar() {
		
		this.nit=0;
		this.grupo="";
		this.modalidadDestino="";
		this.fechaAdecuacion="";
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getModalidadDestino() {
		return modalidadDestino;
	}

	public void setModalidadDestino(String modalidadDestino) {
		this.modalidadDestino = modalidadDestino;
	}

	public String getFechaAdecuacion() {
		return fechaAdecuacion;
	}

	public void setFechaAdecuacion(String fechaAdecuacion) {
		this.fechaAdecuacion = fechaAdecuacion;
	}

	public boolean isBuscadoExito() {
		return buscadoExito;
	}

	public void setBuscadoExito(boolean buscadoExito) {
		this.buscadoExito = buscadoExito;
	}
	
	
	
	public byte[] decompressFileService(byte[] compressed) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            ByteArrayInputStream bais = new ByteArrayInputStream(compressed);
            GZIPInputStream gis = new GZIPInputStream(bais);
            int len;
            while ((len = gis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            gis.close();
            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return out.toByteArray();
    }

	public String getReporteBase64() {
		return reporteBase64;
	}

	public void setReporteBase64(String reporteBase64) {
		this.reporteBase64 = reporteBase64;
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	
}
