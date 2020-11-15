package bo.gob.sin.sre.fac.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.sre.fac.model.MetodosPadron;
import bo.gob.sin.sre.fac.model.VerificacionInsituComponentesModel;
/**
 * @Descripcion: Muestra el listado de los sistemas asociados al proveedor.
 *               Adicionalmente el listado de los 
 * @author: Junior Flores
 * @fecha: 22/11/2019.
 */

@ManagedBean(name = "verificacionInsituComponentesController")
@RequestScoped
public class VerificacionInsituComponentesController implements Serializable 
{

	@ManagedProperty(value = "#{verificacionInsituComponentesModel}")
	private VerificacionInsituComponentesModel verificacionInsituComponentesModel;
	
	@ManagedProperty(value = "#{metodosPadron}")
	private MetodosPadron metodosPadron;

	private static final long serialVersionUID = 1L;
	
	public void borrarNit() {
		verificacionInsituComponentesModel.setNit(0);		
	}
	
	public void buscarSistemasPorNit() {
		
		ContribuyenteDto vContribuyenteDto = metodosPadron.ObtenerDatosBasicosXNIT(verificacionInsituComponentesModel.getNit());
		System.out.print(vContribuyenteDto);
	}

	public VerificacionInsituComponentesModel getVerificacionInsituComponentesModel() {
		return verificacionInsituComponentesModel;
	}

	public void setVerificacionInsituComponentesModel(
			VerificacionInsituComponentesModel verificacionInsituComponentesModel) {
		this.verificacionInsituComponentesModel = verificacionInsituComponentesModel;
	}

	public MetodosPadron getMetodosPadron() {
		return metodosPadron;
	}

	public void setMetodosPadron(MetodosPadron metodosPadron) {
		this.metodosPadron = metodosPadron;
	}
}
