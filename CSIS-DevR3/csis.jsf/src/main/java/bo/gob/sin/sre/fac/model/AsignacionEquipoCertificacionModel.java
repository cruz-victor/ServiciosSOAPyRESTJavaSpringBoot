package bo.gob.sin.sre.fac.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.gob.sin.sap.cder.dto.UsuarioAsignacionDto;

@ManagedBean(name = "asignacionEquipoCertificacionModel")
@ViewScoped
public class AsignacionEquipoCertificacionModel  implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean asignarseUnoMismo;

	private RespUsuariosAsignancionProcesoDto funcionarios;
	
	private UsuarioAsignacionDto funcionarioResponsable;

	private RespUsuariosAsignancionProcesoDto funcionariosAsignados;
	
	private String instruccion;

	public RespUsuariosAsignancionProcesoDto getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(RespUsuariosAsignancionProcesoDto funcionarios) {
		this.funcionarios = funcionarios;
	}

	public RespUsuariosAsignancionProcesoDto getFuncionariosAsignados() {
		return funcionariosAsignados;
	}

	public void setFuncionariosAsignados(RespUsuariosAsignancionProcesoDto funcionariosAsignados) {
		this.funcionariosAsignados = funcionariosAsignados;
	}

	public boolean isAsignarseUnoMismo() {
		return asignarseUnoMismo;
	}

	public void setAsignarseUnoMismo(boolean asignarseUnoMismo) {
		this.asignarseUnoMismo = asignarseUnoMismo;
	}

	public void quitarUsuarioPorId(long pIdUsuario) {
		this.getFuncionariosAsignados().getUsuariosAsignacion().removeIf(x -> x.getUsuarioId() == pIdUsuario);
	}

	public void agregarUsuario(UsuarioAsignacionDto pUsuario) {
		this.getFuncionariosAsignados().getUsuariosAsignacion().add(pUsuario);
	}

	public String getInstruccion() {
		return instruccion;
	}

	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}

	public UsuarioAsignacionDto getFuncionarioResponsable() {
		return funcionarioResponsable;
	}

	public void setFuncionarioResponsable(UsuarioAsignacionDto funcionarioResponsable) {
		this.funcionarioResponsable = funcionarioResponsable;
	}
}
