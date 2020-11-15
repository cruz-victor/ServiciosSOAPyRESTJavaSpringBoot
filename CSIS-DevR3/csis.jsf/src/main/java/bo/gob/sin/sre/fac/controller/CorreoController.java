package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import bo.gob.sin.sau.ausu.dto.SauUsuarioDto;
import bo.gob.sin.sen.enco.controller.MensajesBean;
import bo.gob.sin.str.cmsj.mail.Email;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@ManagedBean(name = "correoBean")
@ViewScoped
public class CorreoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String a;
	private String copia;
	private String asunto;
	private String mensaje;

	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajesBean;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getCopia() {
		return copia;
	}

	public void setCopia(String copia) {
		this.copia = copia;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public MensajesBean getMensajesBean() {
		return mensajesBean;
	}

	public void setMensajesBean(MensajesBean mensajesBean) {
		this.mensajesBean = mensajesBean;
	}

	public boolean submit() 
	{
		boolean vFueEnviado=false;
		Email envio = new Email();
		String[] destinos = new String[1];
		String[] copias = new String[1];
		String[] copiasOcultas = new String[1];
		String emisor = "";

		emisor = ConstFacturacion.CORREO_DIRECCION_EMISOR;
		destinos[0] = this.a;
		copias[0] = this.copia;
		copiasOcultas[0] = ConstFacturacion.CORREO_DIRECCION_COPIA_OCULTA;

		vFueEnviado = envio.envioCorreoHTML(destinos, copias, copiasOcultas, emisor, mensaje, asunto, false);
		return vFueEnviado;
	}

	public String envioMailTxt() {

		Email envio = new Email();
		String[] destinos = new String[1];
		String[] copias = new String[1];
		String[] copiasOcultas = new String[1];
		destinos[0] = a;
		copias[0] = copia;
		copiasOcultas[0] = "luis.landaeta@impuestos.gob.bo";

		envio.envioCorreoPlano(destinos, copias, copiasOcultas, "luis.landaeta@impuestos.gob.bo", mensaje, asunto,
				false);

		return "";
	}

	public String envioMensaje() {
		SauUsuarioDto mensajesP = new SauUsuarioDto();
		StrMensajeAplicacionDto mensajedto = new StrMensajeAplicacionDto();
		mensajedto.setCodigo(3000);
		mensajedto.setDescripcion("Mensaje enviado...");
		mensajedto.setDescripcionUi("Mensaje enviado...");
		mensajedto.setEstadoId("AC");
		mensajesP.addMensaje(mensajedto);
		mensajesBean.addMensajes(mensajesP);
		return "";
	}
	
	public void EnviarCorreo(String pDestino, String pCopia, String pCopiasOculta, String pEmisor, String pMensaje, String pAsunto)
	{		
		boolean vFueEnviado = false;
		
		try 
		{
			Email envio = new Email();			
			
			String[] destinos = new String[1];
			String[] copias = new String[1];
			String[] copiasOcultas = new String[1];
			
			destinos[0] = pDestino;
			
			if(!pCopia.isEmpty())
			{
				copias[0] = pCopia;
			}
			
			if(!pCopiasOculta.isEmpty())
			{
				copiasOcultas[0] = pCopiasOculta;
			}

			if(!pDestino.isEmpty() && !pEmisor.isEmpty() && !pMensaje.isEmpty() && !pAsunto.isEmpty())
			{
				vFueEnviado = envio.envioCorreoHTML(destinos, copias, copiasOcultas, pEmisor, pMensaje, pAsunto, false);
				
				if(vFueEnviado)
				{
					RequestContext.getCurrentInstance().execute("toastr.success('Se ha enviado el correo Electrónico exitosamente', '')");		
				}
				else
				{
					RequestContext.getCurrentInstance().execute("toastr.error('No se ha podido enviar el correo Electrónico', '')");		
				}
			}
			else
			{
				RequestContext.getCurrentInstance().execute("toastr.info('Existen campos vacios', '')");		
			}
		} 	
		catch (Exception e) 
		{
			RequestContext.getCurrentInstance().execute("toastr.error('No se ha podido enviar el correo Electrónico', '')");
		}
	}		
}
