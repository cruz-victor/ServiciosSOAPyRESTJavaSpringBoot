package bo.gob.sin.sre.fac.cfec.domain.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.fac.cfec.dao.IReporteDao;
import bo.gob.sin.sre.fac.cfec.domain.IReporteDomain;

@Service
@Transactional
public class ReporteDomainImpl implements IReporteDomain {

	@Autowired
	private IReporteDao iReporteDao;

	@Override
	public String obtenerArchivoXmlValido(String pSolicitud) {
		return iReporteDao.obtenerArchivoXmlValido(pSolicitud);
	}

}