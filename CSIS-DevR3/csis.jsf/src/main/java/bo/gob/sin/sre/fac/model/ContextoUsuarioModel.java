package bo.gob.sin.sre.fac.model;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import bo.gob.sin.sau.ausu.dto.UsuarioContextoDto;
import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.sen.enco.model.ContextoJSF;
import bo.gob.sin.sen.enco.model.CookieHelper;
import bo.gob.sin.sen.enco.service.RestClientAuthentication;
import bo.gob.sin.sre.fac.model.parameter.ParametrosCsis;
import bo.gob.sin.sre.fac.service.ServiciosPadronRestClient;
import bo.gob.sin.str.caut.security.jwt.JWTAlternativeValidator;
import bo.gob.sin.str.ccs.jsfconf.RestUrl;
import bo.gob.sin.str.constantes.recaudaciones.ConstFacturacion;

@SuppressWarnings("deprecation")
@ManagedBean(name ="contextoUsuarioModel")
@ViewScoped
public class ContextoUsuarioModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ContextoJSF contexto;
	private UsuarioContextoDto usuario;
	private ContribuyenteDto contribuyente;
	private transient RestClientAuthentication servicioAutenticacionRest;

	@PostConstruct
	public void init() 
	{
		try
		{
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			if (sessionMap.get("UsuarioSIN") == null) {
				CookieHelper cookie = new CookieHelper();
	
				System.out.println("Iniciamos recuperacion Galleta.......");
				Cookie galleta = cookie.getCookie("UserSIN");
				if (galleta != null) {
					System.out.println("Galleta recuperada.......");
					if (galleta.getValue() != "") {
						System.out.println("Valor Galleta recuperada:" + galleta.getValue() + " ........");
						JWTAlternativeValidator validator = new JWTAlternativeValidator();
						UsuarioContextoDto vUsuarioContexto = validator.validate(galleta.getValue());
	
						RestUrl conf = new RestUrl();
						String dir = conf.getPropetyValue("str_cau_caut_rest");
						this.servicioAutenticacionRest = new RestClientAuthentication(dir);
						if (vUsuarioContexto.getNit() == null) {
							System.out.println("recuperando usuario.......");
							vUsuarioContexto = servicioAutenticacionRest.getUsuario(vUsuarioContexto.getUsuarioId());
						} else {
							System.out.println("recuperando usuario con nit.......");
							vUsuarioContexto = servicioAutenticacionRest.getUsuarioConNit(vUsuarioContexto.getUsuarioId(),
									vUsuarioContexto.getNit(), vUsuarioContexto.getToken());
						}
	
						vUsuarioContexto.setNit(vUsuarioContexto.getNit());
						vUsuarioContexto.setToken(galleta.getValue());
						sessionMap = externalContext.getSessionMap();
						sessionMap.put("UsuarioSIN", vUsuarioContexto);
	
					}
				} else {
					System.out.println("Galleta no recuperada.......");
				}
			} else {
				System.out.println("Session UsuarioSIN recuperanda.......");
			}
	
			contexto = new ContextoJSF();
			usuario = new UsuarioContextoDto();
			contribuyente = new ContribuyenteDto();
	
			if (contexto.getUsuario() != null) {
				usuario = contexto.getUsuario();
				// CONTRIBUYENTE
	
				if (contexto.getUsuario().getTipoUsuario().equals(ConstFacturacion.TIPO_USUARIO_CONTRIBUYENTE)) 
				{
					System.out.println("Cargar Datos Adicionales Contexto Contributente............");
					this.CargarContribuyenteXNIT(Long.parseLong(contexto.getUsuario().getNit()));
					// se agrega la siguiente linea para la oficina
					usuario.setOficinaId(contribuyente.getOficinaRegistroId());
				}
				// FUNCIONARIO
//				if (contexto.getUsuario().getTipoUsuario().equals(ParametrosCsis.TIPO_USUARIO_FUNCIONARIO)) {
//					
//	
//				}
			} 
			else 
			{
				 usuario = contexto.getUsuario();
				 String desplegarMensajesError = "No se tiene en el contexto del Usuario."; 
				 FacesContext.getCurrentInstance().getExternalContext().getFlash().put("desplegarMensajesError", desplegarMensajesError);
				 this.RedireccionarIndex();
			}
		}
		catch (Exception e) 
		{
			System.out.println("ContextoUsuarioModel: " + e.getMessage());
			 String desplegarMensajesError = "Error al inicializar datos de Contexto."; 
			 FacesContext.getCurrentInstance().getExternalContext().getFlash().put("desplegarMensajesError", desplegarMensajesError);
			 this.RedireccionarIndex();
		}
	}

	public void CargarContribuyenteXNIT(Long pNit) {
		ServiciosPadronRestClient vServicio = new ServiciosPadronRestClient();
		ContribuyenteDto vResultadoNitDatosBasicosDto = new ContribuyenteDto();
		vResultadoNitDatosBasicosDto = vServicio.ObtenerDatosBasicosXNIT(pNit);
		if(vResultadoNitDatosBasicosDto.isOk())
		{
			contribuyente = vResultadoNitDatosBasicosDto;
		}
		else
		{
			String desplegarMensajesError = "Error al obtener datos del contribuyente"; 
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("desplegarMensajesError", desplegarMensajesError);
			this.RedireccionarIndex();
		}
	}

	public void CargarContribuyenteXIFC(Long pIfc) 
	{
		ServiciosPadronRestClient vServicio = new ServiciosPadronRestClient();
		ContribuyenteDto vResultadoNitDatosBasicosDto = new ContribuyenteDto();
		vResultadoNitDatosBasicosDto = vServicio.ObtenerDatosBasicosXIFC(pIfc);
		if(vResultadoNitDatosBasicosDto.isOk())
		{
			contribuyente = vResultadoNitDatosBasicosDto;
		}
	}

	public UsuarioContextoDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioContextoDto usuario) {
		this.usuario = usuario;
	}

	public void RedireccionarIndex() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.faces");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void RedireccionarMensajesErroresPagina() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
							+ "/errorValidacion.faces");
		} catch (Exception e) {
		}
	}
	
	public String RedireccionarBandejaRecepcionPagina() {
		RestUrl vConf = new RestUrl();
		return vConf.getPropetyValue("workflow_bandeja_recepcion");
	}
	
	public String RedireccionarBitacoraPagina() {
		RestUrl vConf = new RestUrl();
		return vConf.getPropetyValue("workflow_bitacora");
	}

	public ContextoJSF getContexto() {
		return contexto;
	}

	public void setContexto(ContextoJSF contexto) {
		this.contexto = contexto;
	}

	public RestClientAuthentication getServicioAutenticacionRest() {
		return servicioAutenticacionRest;
	}

	public void setServicioAutenticacionRest(RestClientAuthentication servicioAutenticacionRest) {
		this.servicioAutenticacionRest = servicioAutenticacionRest;
	}

	public ContribuyenteDto getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(ContribuyenteDto contribuyente) {
		this.contribuyente = contribuyente;
	}
}
