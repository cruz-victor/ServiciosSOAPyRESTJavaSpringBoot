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
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback 
public class testAformatoXML {
	private static final Logger LOG = LoggerFactory.getLogger(testAformatoXML.class);
	
	@Autowired
	IUtilitarios iUtilitarios;
		
	@Rollback(false)
	@Test
	public void TestAnulacionDetalleCertificacion() throws Exception{
		String pArchivoXml = null;
		LOG.info("recepcionFactura ||Iniciando");
		try {
			//pArchivoXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><factura xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><cabecera><numeroFactura>3873</numeroFactura><direccion>Jorge Wilstermann</direccion><fechaEmision>2019-01-15T21:08:40</fechaEmision><codigoTipoDocumentoIdentidad>3</codigoTipoDocumentoIdentidad><cuf>000102849902902019011521084008000220101000014551</cuf><numeroDocumento>0</numeroDocumento><complemento>Complemento</complemento><nombreRazonSocial>S/N</nombreRazonSocial><montoTotal>15.000000</montoTotal><montoDescuento>0.00001</montoDescuento><codigoCliente>0</codigoCliente><codigoTipoFactura>0</codigoTipoFactura><nitEmisor>1028499029</nitEmisor><codigoMetodoPago>0</codigoMetodoPago><numeroTarjeta>0</numeroTarjeta><leyenda>Ley N° 453: Los servicios deben suministrarse en condiciones de inocuidad, calidad y seguridad.</leyenda><usuario>SISTEMA_SABSA</usuario></cabecera><detalle><actividadEconomica>2</actividadEconomica><codigoProductoSin>3</codigoProductoSin><codigoProducto>3</codigoProducto><descripcion>M1REQUENA#ANA         E2833760CBBVVI8J 989   15Y011FCBBVVI100</descripcion><cantidad>1</cantidad><precioUnitario>15.000000</precioUnitario><montoDescuentoDetalle>0.00001</montoDescuentoDetalle><subTotal>15.000000</subTotal><numeroSerie>0</numeroSerie></detalle><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\" /><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\" /></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\" /><DigestValue>u3q/cPxht4x6iiS1w2ujjfZJuHM=</DigestValue></Reference></SignedInfo><SignatureValue>BAgdRx6Qx+h4BIhz1nAKTbzey/yoDUqW/5qEfy1Bu+ntfi+1TfrOrbv+uMSdaohsq0DRnNjfXJOdS/eNEerfO0dv0TpYjjFK0FEIrlJhOA5lPpNRGFrmKJjj/QHby4lTtfMGRe00syj0ETVd3YBgWRSrIcMuuBn6PBpUpJgC6+O/x7icGIxXSLfGs+87ljz+QQ5pVHPRas4dp8QIs0561Kz/6llM7bToBjauwDlAZYX0X1M6+AwId+br7uEnpoWUk3p1+SLmcZIOKoJWwgMNb7vsg2QBbZLR9cuJVFJF8nx9eU9UFc2V0B3WxS6wQ8N4qnxaP+0NuzUPX8WZQHBGgA==</SignatureValue><KeyInfo><X509Data><X509Certificate>MIIE/jCCA+agAwIBAgIQEHOV2J5vCFV6ylgfOA+Q4jANBgkqhkiG9w0BAQsFADB/MQswCQYDVQQGEwJVUzEdMBsGA1UEChMUU3ltYW50ZWMgQ29ycG9yYXRpb24xHzAdBgNVBAsTFlN5bWFudGVjIFRydXN0IE5ldHdvcmsxMDAuBgNVBAMTJ1N5bWFudGVjIENsYXNzIDMgU0hBMjU2IENvZGUgU2lnbmluZyBDQTAeFw0xODA0MDMwMDAwMDBaFw0yMDA1MDIyMzU5NTlaMIGVMQswCQYDVQQGEwJCTzExMC8GA1UECgwoU0VSVklDSU9TIERFIEFFUk9QVUVSVE9TIEJPTElWSUFOT1MgUy5BLjExMC8GA1UEAwwoU0VSVklDSU9TIERFIEFFUk9QVUVSVE9TIEJPTElWSUFOT1MgUy5BLjEgMB4GA1UEBwwXU2FudGEgQ3J1cyBkZSBsYSBTaWVycmEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCqeERPL0QJS0KgxFPI7cCYtWJOfGeTbaEeCEG2gOA6Clx0caBzubCfCSguuuyK2fFK10RyQE1muTR9K3e6Mq0GbiYVTjB7kQHugWyvVfNnLTUJz6sees5/S5a+PIuzDGaB9eXPO5YL247Ucw0eFOwZMISvkJQn3WItbW9OAAaJNqwq2lo2PPdDLZn2ElAnUOMWwYrXPS3263uJ1rBIyckwUlb6Gm/G+Bs/OjV+iTfrQy+e4dQWdduRICfWlWzCOgRiidItnIE37YNwFbHeU1Arfe5nbmRs+F5G9euv5VapzmnwwlLvuXIqP8t9HhkoYAxdQPq7lXou6mmUaUvL7bLXAgMBAAGjggFdMIIBWTAJBgNVHRMEAjAAMA4GA1UdDwEB/wQEAwIHgDArBgNVHR8EJDAiMCCgHqAchhpodHRwOi8vc3Yuc3ltY2IuY29tL3N2LmNybDBhBgNVHSAEWjBYMFYGBmeBDAEEATBMMCMGCCsGAQUFBwIBFhdodHRwczovL2Quc3ltY2IuY29tL2NwczAlBggrBgEFBQcCAjAZDBdodHRwczovL2Quc3ltY2IuY29tL3JwYTATBgNVHSUEDDAKBggrBgEFBQcDAzBXBggrBgEFBQcBAQRLMEkwHwYIKwYBBQUHMAGGE2h0dHA6Ly9zdi5zeW1jZC5jb20wJgYIKwYBBQUHMAKGGmh0dHA6Ly9zdi5zeW1jYi5jb20vc3YuY3J0MB8GA1UdIwQYMBaAFJY7U/B5M5evfYPvLivMyreGHnJmMB0GA1UdDgQWBBQh4A3MH3PYJTCfnzWZsL55Hq0+UjANBgkqhkiG9w0BAQsFAAOCAQEAihAKAo6SzQbDHW5nIoDMx/n+YglPBKiFpSdGDvlOFBgho6S3lcxX+etR7U198MI7d0va412oP9AYn92MrJylXDUdjxLwKraSZM70s1FaRqdRdrZCkdAkskctOVitFY4bDrYdpilqjj7CT/zF5KDbwJqeIWO4I9s74pahl8/fmRdObUWNyT8uPlCRXXIf4ILBX3wFmSpKdEi8oXipv0EMNr4R5HSc8Fc3XEfKH2CWEEvVMxgBt+LamnxyEX5W0z9OSssuGfJZbeL8MByhpsKL9VMI7J+GqJ6GvK6mAI6zNm6Ds+fnS9SZeIUy2MbFLs6ocKLgexadqvYVKIWc4yiNgA==</X509Certificate></X509Data></KeyInfo></Signature></factura>";
			pArchivoXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 
					"<facturaElectronicaServicioBasico xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"facturaElectronicaServicioBasico.xsd\">\r\n" + 
					"        <cabecera>\r\n" + 
					"            <nitEmisor>1023213028</nitEmisor>\r\n" + 
					"            <numeroFactura>1120759</numeroFactura>\r\n" + 
					"            <cuf>0001023213028201910021533484560000111180112075900000</cuf>\r\n" + 
					"            <cufd>BQUE+Q05nQ0DAzMDQ0QkRFQmFBSkRLVFVJBMDAwMDU3O</cufd>\r\n" + 
					"            <codigoSucursal>0</codigoSucursal>         \r\n" + 
					"            <direccion>Av. Heroinas 0-686</direccion>\r\n" + 
					"            <codigoPuntoVenta xsi:nil=\"true\"/>\r\n" + 
					"            <mes>Octubre</mes> \r\n" + 
					"             <gestion>2019</gestion>\r\n" + 
					"             <ciudad>Cochabamba</ciudad>\r\n" + 
					"             <zona>U - URBANO</zona>\r\n" + 
					"             <numeroMedidor>04201252</numeroMedidor>\r\n" + 
					"             <fechaEmision>2019-10-02T00:00:00.000</fechaEmision>\r\n" + 
					"             <nombreRazonSocial>BLANCO VALLEJOS ROSE MERY</nombreRazonSocial>\r\n" + 
					"             <domicilioComprador>AV CIRCUNVALACION BEIJING SN </domicilioComprador>    \r\n" + 
					"             <codigoTipoDocumentoIdentidad>5</codigoTipoDocumentoIdentidad>  \r\n" + 
					"             <numeroDocumento>3144786</numeroDocumento>\r\n" + 
					"             <complemento xsi:nil=\"true\"/>\r\n" + 
					"             <codigoCliente>1080067</codigoCliente>\r\n" + 
					"             <codigoMetodoPago>1</codigoMetodoPago>\r\n" + 
					"             <numeroTarjeta xsi:nil=\"true\"/>\r\n" + 
					"             <montoTotal>134.70</montoTotal>\r\n" + 
					"             <montoDescuento xsi:nil=\"true\"/>\r\n" + 
					"             <montoTotalSujetoIva>111.80</montoTotalSujetoIva>\r\n" + 
					"             <consumoKwh>139</consumoKwh>\r\n" + 
					"             <consumoMetrosCubicos xsi:nil=\"true\"/>\r\n" + 
					"             <montoDescuentoLey1886 xsi:nil=\"true\"/>\r\n" + 
					"             <tasaAseo>11.20</tasaAseo>\r\n" + 
					"             <tasaAlumbrado>11.70</tasaAlumbrado>\r\n" + 
					"             <codigoMoneda>688</codigoMoneda>\r\n" + 
					"             <tipoCambio>1</tipoCambio>\r\n" + 
					"             <montoTotalMoneda>134.70</montoTotalMoneda>\r\n" + 
					"             <leyenda>Tienes derecho a un trato equitativo</leyenda>\r\n" + 
					"             <usuario>DLOPEZ</usuario>\r\n" + 
					"             <codigoDocumentoSector>5</codigoDocumentoSector>\r\n" + 
					"             <descuentoSinAfectacion xsi:nil=\"true\"/>\r\n" + 
					"            <codigoExcepcionDocumento xsi:nil=\"true\"/>\r\n" + 
					"        </cabecera>\r\n" + 
					"        <detalle>\r\n" + 
					"            <actividadEconomica>620100</actividadEconomica>\r\n" + 
					"            <codigoProductoSin>83143</codigoProductoSin>\r\n" + 
					"            <codigoProducto>12345</codigoProducto>\r\n" + 
					"            <descripcion>Venta de energia electrica</descripcion>\r\n" + 
					"            <cantidad>1</cantidad>\r\n" + 
					"            <unidadMedida>24</unidadMedida>    \r\n" + 
					"            <precioUnitario>111.80</precioUnitario>\r\n" + 
					"            <montoDescuento xsi:nil=\"true\"/> \r\n" + 
					"            <subTotal>111.80</subTotal>\r\n" + 
					"      </detalle>\r\n" + 
					"    <ds:Signature Id=\"SignIMM\">\r\n" + 
					"  <ds:SignedInfo><ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments\"/>\r\n" + 
					"    <ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/>\r\n" + 
					"  <ds:Reference URI=\"\"><ds:Transforms><ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/></ds:Transforms><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><ds:DigestValue>gs76m8/ykfuk7gjTVHOeNTY8KN54EBlCsx4t6y2Jy1U=</ds:DigestValue></ds:Reference></ds:SignedInfo><ds:SignatureValue>Ev6b3a5rWUp69YwJovoW1MWPr2z/OAURkQwOGu+RqQcaMeV1Me8e6ebXR2aETQhVdZubEKteodXs+QuQlgMMbrkUix9jWc/sb1WhClN/6OqAzgyVHwfQHTovIkUofhv91gnAjllEk9nM1exADYiXvAdY6R5NtXMZcrXmVpYbq4cxAP59XCJxMU03Ow0dbbdec+0kVpKdYenkYKZ9zV5b+v21z40jfERnXIndnqfiJpy233QLOYYLPkT4kFBMW3W9wX+Zuqp3kDB/MDD8aKJiTu/6nnorOA9Lx944QSU8sFzPu+LdXpoqW4Qf0H3TAlcPAbha7DJcAdAS+H1IbKDb+w==</ds:SignatureValue>\r\n" + 
					"<ds:KeyInfo><ds:X509Data><ds:X509Certificate>MIIHWzCCBUOgAwIBAgIIcDGesrIEWBMwDQYJKoZIhvcNAQELBQAwSzEsMCoGA1UEAwwjRW50aWRhZCBDZXJ0aWZpY2Fkb3JhIFB1YmxpY2EgQURTSUIxDjAMBgNVBAoMBUFEU0lCMQswCQYDVQQGEwJCTzAeFw0xOTA5MDkyMTM1MDBaFw0yMDA4MjYyMjU4MDBaMIHmMQswCQYDVQQuEwJDSTEdMBsGA1UEAwwUUlVCRU4gQ0FSVkFKQUwgTU9MTE8xEzARBgNVBAUTCjEwMjMyMTMwMjgxITAfBgNVBAwMGEdlcmVudGUgR2VuZXJhbCBJbnRlcmlubzELMAkGA1UECwwCR0cxEzARBgNVBAoMCkVMRkVDIFMuQS4xCzAJBgNVBAYTAkJPMRQwEgYHKwYBAQEBAAwHMzc2NjQ3NTE7MDkGA1UEDQwyUGVyc29uYSBKdXJpZGljYSBTZWd1cmlkYWQgTm9ybWFsIEZpcm1hIEF1dG9tYXRpY2EwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDlCWMhlFJoKON2HHq0msJppuVZIC3xLqfVkANv2VviEpiB6SdDTSTjXwsGsqakA/PnZWUiQfPOUSw79qFWLIPlvVtx8dLJKzFckiY1Msm/RGuGn+wzWYeSj0E/wr/l1tPgP1NbVAGBXQRtlPgZRoKIdaT8sYyn3k8Un+rQzE6gYp+vM/3f2pd4q7WGZ0yjnpUwWbiIONijuB8MqKvdaAP6qjiOaSDtz7dNmi3DSlA8tMRPvgekXQhVNETp32Z3vsPMPhGAb3kKYRBy2xKM6h7zYwDM9mgqjz5Wr/1IPa/QGdi54JvT1raP0esitKDqW/gnE/nVq+1mRB4rSneXtGj3AgMBAAGjggKlMIICoTB5BggrBgEFBQcBAQRtMGswOwYIKwYBBQUHMAKGL2h0dHBzOi8vd3d3LmZpcm1hZGlnaXRhbC5iby9maXJtYWRpZ2l0YWxfYm8ucGVtMCwGCCsGAQUFBzABhiBodHRwOi8vd3d3LmZpcm1hZGlnaXRhbC5iby9vY3NwLzAdBgNVHQ4EFgQUncgnrTqjBiueWANiDC0GUGZcdtcwCQYDVR0TBAIwADAfBgNVHSMEGDAWgBTSmd3BbyUuJ6gL69zZ1pJbYlWgwjCB5wYDVR0gBIHfMIHcMIGHBg9gRAAAAAEOAQIAAQIAAAEwdDByBggrBgEFBQcCAjBmHmQAUABlAHIAcwBvAG4AYQAgAEoAdQByAGkAZABpAGMAYQAgAFMAZQBnAHUAcgBpAGQAYQBkACAATgBvAHIAbQBhAGwAIABGAGkAcgBtAGEAIABBAHUAdABvAG0AYQB0AGkAYwBhMFAGDmBEAAAAAQ4BAgABAAAAMD4wPAYIKwYBBQUHAgEWMGh0dHBzOi8vd3d3LmZpcm1hZGlnaXRhbC5iby9wb2xpdGljYWp1cmlkaWNhLnBkZjCBkwYDVR0fBIGLMIGIMIGFoDKgMIYuaHR0cDovL3d3dy5maXJtYWRpZ2l0YWwuYm8vZmlybWFkaWdpdGFsX2JvLmNybKJPpE0wSzEsMCoGA1UEAwwjRW50aWRhZCBDZXJ0aWZpY2Fkb3JhIFB1YmxpY2EgQURTSUIxDjAMBgNVBAoMBUFEU0lCMQswCQYDVQQGEwJCTzALBgNVHQ8EBAMCBPAwJwYDVR0lBCAwHgYIKwYBBQUHAwIGCCsGAQUFBwMDBggrBgEFBQcDBDAjBgNVHREEHDAagRhtYXJjby52ZWxhc3F1ZXpAZWxmZWMuYm8wDQYJKoZIhvcNAQELBQADggIBAHrZpvbIyTf+V4ToEV45igY8FkRu34N2DvOTbPQFLB/XJgcfsg2sLaf24feRf7Sj+TJt+NjmBrKHhCGBFHcudJmejFMum9wjZvBTdLFjpD0B2XrssFyQtF5MvG03o/j/NkFJvx12/LLGTk5JAQxdOpcawWoyFhrpRSZfMeSfos3BN6tBZSlm97U1GjE0cfynRXIkp7Cj3TSk/BdOiWgBq3WqncXM6diCa10EyVdPa/08AHDQdv5u7J1BEfL0NIqBUFnPVzjrR15DnYdnFLQpO75WSu8w9sRhhMush+SLn3jxKIQJVGpOeBtc2vGSqZ3Z/bWxXXqinv3WVFRlfdAovidho/KLlkOS4m4i5NeyFjpgBKqwXLv36Edw9L4Cabku8e/IgAUVX1XbzWdwilRtIAmGFmQOJBJRiUGWcXclUkJNofNKEkonJMdWFiBjODsYmQREo79GWE7nmQJPTL9sZnx24HweYjBMiHYXiZvxP2aEBrqOSKd3W02KZUbnYU5n1hnvYcqCEw0drAna+9xKH5We5POw/W9emiVoRA6FcgJ4iDmVe/QyqHhuEGr/hihr/hnPWj2MJADUbNSJAuriGmM9Cwrvcm2rjInKRQ2IOW3cBvATybO5JJXo3xjUVTqR0doi1DlbILKV9B6BKXiXSHqetBGwvFIs1LxavtNjGUHH</ds:X509Certificate></ds:X509Data></ds:KeyInfo></ds:Signature></facturaElectronicaServicioBasico>\r\n" + 
					"";
			iUtilitarios.formatearSignature(pArchivoXml);
			LOG.info("registrarRecepcion CompEstandar ==>" + pArchivoXml);
		} catch (Exception e) {
			LogExcepcion.registrar(e, LOG, MethodSign.build(pArchivoXml));
			LOG.info("registrarRecepcion Estandar||Excepcion");
		}
	}
}
