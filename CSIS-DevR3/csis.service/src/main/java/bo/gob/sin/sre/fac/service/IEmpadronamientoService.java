package bo.gob.sin.sre.fac.service;

import bo.gob.sin.scn.empa.caco.dto.ContribuyenteDto;
import bo.gob.sin.scn.empa.ccoc.dto.NitDatosBasicosDto;

public interface IEmpadronamientoService {
	
	public ContribuyenteDto obtenerDatosBasicosXIFC(Long pIFC);
	public ContribuyenteDto obtenerDatosBasicosXNIT(Long pNIT);
}
