package bo.gob.sin.sre.fac.cfec.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.config.AppConfig;
import bo.gob.sin.sre.fac.cfec.dao.IJsonDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback
public class TestDaoRecepcionBDEtapa1P01 {
	private static final Logger LOG = LoggerFactory.getLogger(TestDaoRecepcionBDEtapa1P01.class);

	@Autowired
	IJsonDao iJsonDao;

	@Rollback(false)
	@Test
	public void enviarJsonRecepcionBDEtapa1P01() throws Exception {
		LOG.info("recepcionFactura ||Iniciando");
		try {
			String vRespuesta = iJsonDao.recepcionBdEtapa1(
					"{\"XmlFactura\":\"\",\"xmlRecepcionGenerica\":{\"v_archivo\":\"4sICKAF71wEAGFyY2hpdm9fYWN0dWFsaXphZG8ueG1sAKVWybKaQBT9IDeAQ2SRhcogRlpFBmFHwysRGkNKZfr6nEbfCyZmqMqCgmr6jufc071V5BH1qmOcu00ksZKmwsncjyrjNGc0J2Wgs1vQCifXtfS1fTxt29GXrR4k/tASo1xTqCRW8cFKopxNfM9KrAMRfG+c+Qfj+KaLF3o2J29LUmz3q6/x0qo2p2kZD+Ph+hy161xugmbamMqsXosJsTWShrp79fdiQc+dnzTYww/sN/m4tHPtGhxI5XuEORL5GnhiYuty6h+sgkoj2cj/Ka8b/GVGOvpiLGbHrU4SP3fT4LBKtsevR6zxZ0pzJlieWESS3GxTtTLT2dBUTLzN6VoaF7HqXsMDKaOHn7uvsUg9t6HCex7qwLRnlalUJT27V8QorVxL46Xb+HbfjohBOkYeZg3/ArFniMHXftqT7wZm5rS7dKVtVEvb2HNllxJ9o+xGO8dSSHpsSGYtTEHTSarCx91mcbr7MJQ6pZJVBJJsxx4RozNJqDKqtwv5xXo/tlVE6JHf8WO8jD3tgp41sT4Fb9iF6loT5TKji1l9709/f99Px6czFeciPVulm7u3WFff8S2oIpzi5UoE5mUfiwC+Ql3TqMfa0JNv29SoTDu7mopzNVPmmPZxQpTZBBjdwKUK9eSBR776TocR50a/Bzdw44T89v6hKCkjJfJEveMvqOtmaHOGfxP4+M2+V/WgXl1WqUSAP69JXgU6f7PM93YDYlewlbPQi0tXZxUVrNIfgg/eWKACy/g7BEcD5VcuPe1tR7WpGBVpZy3eLc8x9kTk2489/QlvsaL6w76ZjyIpm1BghnkUorPLjNPsqdfvee6kuuA2gT0SiW3NdxnH9VGvQC5hF8t5xS0SHCzsk3c+/GB2enYiQ28y4J8E0lOe73U4/mH1DfaJsUza0C5uoVfJxtlqYs85GYsnmyuVUJNolbGuXbapU63xoCdP608z7ckcGxUaksb3Xv2Ve+/9sGEbAKNt6o82H3j+WO/bxB3GmCFvVXDMkJMQHuboqXb1c1a+yMmhQyuhqljS3M189Nxc4IFuvP7f50nN3lBLoKsDW3fHhvppcVnMoCHmxFDrMmrmwB76ODSPge4ckfc19saFL2lpGG1aepqDfyuGdxXl6N0wYBHX/sW84VrLsTR0ixl65yuDhjFfSu7f+/mF+40PRARuXYy3/TxF/1vgWYAD8GHeUP8lODCcJ899+rBrx7qrVtP10IWd1oTe9AWvnubLxnwLdGgMzB4WFs4CziOuLY7kptjXbI/Cl8W+N6uCO/Ilt+q0KeOz9afZeOSh8BjaCTHBUfWxVqMXwAT9f+IL8goPAWrXMssjJUVPQ4+AN9BGW605pr/b84p3znJVBsuuFjv0RgPu46PmX/+/6tsOuGbQda4fD33+0OEGe0ScVc96q7utP1xB0+7aiVip3+FaJ4aiHqnOhCifcp1nkUQacDvluvycv/ZD//aj7gzsrfXzFGnO1yzCecp57OeyEHj1xUed4GrXJ9tzs/CnOYuWK8ZxdLEn1jve8PP2hgdcAn895C+6t/B+Dyj/VwuioXtydRn3iGpAEId0ccBh9qvWPM57zkPk5SAvoyUKbi2Cpu7aqvz5/+KY9XVwBZ4X25yBm4zfCx64vZ8JfD374CbwQk71JYAu8LOP8yvGfWaHWY+W+IcZe8u1DOdhizxv6Cfm4vPn71OUsqL8CQAA\",\"p_cuis\":\"10DC8801\",\"p_motivo_anulacion_id\":null,\"p_numero_documento_fiscal\":null,\"vOrigenServicio\":2,\"v_tipoDepartamentoId\":null,\"p_tipo_modalidad_id\":2,\"p_tipo_emision_id\":1,\"p_sucursal_id\":0,\"p_cuape\":null,\"v_oficinaId\":null,\"p_tipo_documento_fiscal_id\":1,\"p_cufd\":\"8F35E3A6970F81B67E41535CC2C492CE\",\"p_codigo_sistema\":\"E79A306\",\"v_usuarioRegistroId\":1000,\"p_nit\":1020703023,\"v_archivoXml\":null,\"p_sistema_id\":null,\"p_persona_contribuyente_id\":null,\"p_cufp\":null,\"v_usuarioUltimaModificacionId\":1000,\"p_fecha_envio\":\"2019-06-04T17:46:14.355\",\"v_recepcionId\":null,\"v_archivoXmlB64\":null,\"p_recepcion_anulacion_id\":null,\"p_caed\":null,\"p_tipo_documento_sector_id\":1,\"p_cuf\":null,\"v_hash_archivo\":\"bff1757a08de2df9a4df9d689f2465ad95551b8300a5178759d4b26c4c58f5b9\",\"v_recepcionPaqueteId\":null,\"p_punto_venta_id\":null,\"p_tipo_ambiente_id\":2}}");
			LOG.info("TestDaoRecepcionBDEtapa1P01 ==>" + vRespuesta.toString());
		} catch (Exception e) {
			LOG.info("TestDaoRecepcionBDEtapa1P01||Excepcion:" + e.getMessage());
		}
	}

}
