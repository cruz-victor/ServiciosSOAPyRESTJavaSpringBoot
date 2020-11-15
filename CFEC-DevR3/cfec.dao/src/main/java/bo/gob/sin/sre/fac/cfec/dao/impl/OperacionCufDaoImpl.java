package bo.gob.sin.sre.fac.cfec.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dao.IJsonDao;
import bo.gob.sin.sre.fac.cfec.dao.IOperacionCufDao;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudObtencionFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.model.SreFacArchivoXmlInvalido;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Repository
@Transactional
public class OperacionCufDaoImpl extends AbstractGenericDao<SreFacArchivoXmlInvalido> implements IOperacionCufDao {
	private static final Logger LOG = LoggerFactory.getLogger(OperacionCufDaoImpl.class);

	@Override
	public RespuestaFacturaVentasDto obtenerFacturaVentas(SolicitudObtencionFacturaVentasDto pSolicitud, RespuestaDatosCufDto vRespuestaCuf) {
		
		RespuestaFacturaVentasDto vRespuesta = new RespuestaFacturaVentasDto();
		try {
			if (pSolicitud != null && vRespuestaCuf != null) {
				Query query = getSession()
						.createNativeQuery(
								"select sre_recaudaciones.sre_fac_servicio_recepcion_factura(:pJson,:pEtapa)")
						.setParameter("pJson", pSolicitud).setParameter("pEtapa", "1");
				Object x = query.getSingleResult();
				
				return vRespuesta;
			} else {
				return null;
			}
		} catch (NoResultException e) {
			return null;
		}
	}
}