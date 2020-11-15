package bo.gob.sin.sre.fac.cfec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dto.RespuestaListaRegistroRecepcionesSoapDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaValidacionRecepcionPaqueteDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.service.IServicioPushClientRest;
import bo.gob.sin.sre.fac.cfec.service.IServiciosFirmaClientRest;
import bo.gob.sin.sre.fac.cfec.service.IServiciosReversionClientRest;
import bo.gob.sin.sre.fac.cfec.servicedomain.ICoreFacturacionSoap;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;

@Service
@Transactional
public class FacturacionPrincipal implements ICoreFacturacionSoap {

	@Autowired
	private IValidarRecepcion iValidarRecepcion;
	@Autowired
	private IUtilitarios iutil;
	@Autowired
	private IServiciosFirmaClientRest iServiciosFirmaClientRest;
	@Autowired
	IServiciosReversionClientRest iServiciosReversionClientRest;
	@Autowired
	IServicioPushClientRest iServicioPushClientRest;

	/**
	 * @param iValidarRecepcion
	 * @param iutil
	 */
	public FacturacionPrincipal(IValidarRecepcion iValidarRecepcion, IUtilitarios iutil,
			IServiciosFirmaClientRest iServiciosFirmaClientRest,
			IServiciosReversionClientRest iServiciosReversionClientRest,
			IServicioPushClientRest iServicioPushClientRest) {
		this.iValidarRecepcion = iValidarRecepcion;
		this.iutil = iutil;
		this.iServiciosFirmaClientRest = iServiciosFirmaClientRest;
	}

	@Override
	public RespuestaListaRegistroRecepcionesSoapDto CoreFacturacionPrincipal(XmlRecepcionGenericoDto vArchivoXml,
			Integer vEtapa) {

		vArchivoXml.setV_usuarioRegistroId(Parametros.USUARIO_GENERICO);
		vArchivoXml.setV_usuarioUltimaModificacionId(Parametros.USUARIO_GENERICO);
		AServiciosSoap tipoFactura;

		tipoFactura = new ServicioSoapImpl(iValidarRecepcion, iutil);
		tipoFactura.iutil = iutil;
		tipoFactura.iValRec = iValidarRecepcion;
		tipoFactura.iServiciosFirmaClientRest = iServiciosFirmaClientRest;
		tipoFactura.iServiciosReversionClientRest = iServiciosReversionClientRest;
		tipoFactura.iServicioPushClientRest = iServicioPushClientRest;

		return tipoFactura.controlador(vArchivoXml, vEtapa);
	}

	@Override
	public RespuestaValidacionRecepcionPaqueteDto CoreFacturacionPrincipalPaquete(XmlRecepcionGenericoDto vArchivoXml,
			Integer vEtapa) {
		vArchivoXml.setV_usuarioRegistroId(Parametros.USUARIO_GENERICO);
		vArchivoXml.setV_usuarioUltimaModificacionId(Parametros.USUARIO_GENERICO);
		AServiciosSoap estandar = new ServicioSoapImpl(iValidarRecepcion, iutil);
		estandar.iutil = iutil;
		estandar.iValRec = iValidarRecepcion;
		estandar.iServiciosFirmaClientRest = iServiciosFirmaClientRest;
		estandar.iServiciosReversionClientRest = iServiciosReversionClientRest;

		return estandar.controladorPaquete(vArchivoXml, vEtapa);
	}

	@Override
	public RespuestaValidacionRecepcionPaqueteDto CoreFacturacionPrincipalMasiva(XmlRecepcionGenericoDto vArchivoXml,
			Integer vEtapa) {
		vArchivoXml.setV_usuarioRegistroId(Parametros.USUARIO_GENERICO);
		vArchivoXml.setV_usuarioUltimaModificacionId(Parametros.USUARIO_GENERICO);
		AServiciosSoap estandar = new ServicioSoapImpl(iValidarRecepcion, iutil);
		estandar.iutil = iutil;
		estandar.iValRec = iValidarRecepcion;
		estandar.iServiciosFirmaClientRest = iServiciosFirmaClientRest;

		return estandar.controladorPaquete(vArchivoXml, vEtapa);
	}
}