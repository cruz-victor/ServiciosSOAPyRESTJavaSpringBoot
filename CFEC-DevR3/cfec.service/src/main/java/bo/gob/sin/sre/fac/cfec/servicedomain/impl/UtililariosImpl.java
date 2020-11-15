/**
 * 
 */
package bo.gob.sin.sre.fac.cfec.servicedomain.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.sre.fac.cfec.domain.IJsonDomain;
import bo.gob.sin.sre.fac.cfec.domain.impl.ValidarArchivoHashImpl;
import bo.gob.sin.sre.fac.cfec.dto.LogConsumoServicioDto;
import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.sre.fac.cfec.servicedomain.IUtilitarios;
import bo.gob.sin.sre.fac.cfec.servicedomain.IValidarRecepcion;
import bo.gob.sin.sre.fac.cfec.servicedomain.validation.CodigosMensajesServiciosSOAPServiceImpl;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.util.image.QRGenerator;

/**
 * @author edwin.coro
 * @fecha 12/11/2018
 */
@Service
@Transactional
public class UtililariosImpl implements IUtilitarios {
	private static final Logger LOG = LoggerFactory.getLogger(UtililariosImpl.class);

	@Autowired
	IValidarRecepcion iValidarRecepcion;
	@Autowired
	IJsonDomain iJsonDomain;

	@Override
	public String decodificarArchivo(String pArchivo) {
		try {
			if (pArchivo != null && !pArchivo.isEmpty()) {
				byte[] bytesA = Base64.getDecoder().decode(pArchivo);
				String vXmlB64 = this.descomprimirByteToString(bytesA);
				byte[] bytesB = Base64.getDecoder().decode(vXmlB64);
				return new String(bytesB, StandardCharsets.UTF_8);
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error("decodificarArchivo || Exception");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}

	@Override
	public List<String> decompressLoteFacturas(byte[] pCompressed) {
		List<String> listaFacturas = new ArrayList<>();
		try {
			if (pCompressed != null) {
				ByteArrayInputStream bais = new ByteArrayInputStream(pCompressed);
				GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(bais);
				TarArchiveInputStream tarIn = new TarArchiveInputStream(gzipIn);
				TarArchiveEntry entry;
				ByteArrayOutputStream dest = new ByteArrayOutputStream();
				int index = 0;

				while ((entry = (TarArchiveEntry) tarIn.getNextEntry()) != null) {
					/** If the entry is a directory, create the directory. **/
					if (entry.isDirectory()) {
						File f = new File(entry.getName());
						boolean created = f.mkdir();
						if (!created) {
							System.out.printf(
									"Unable to create directory '%s', during extraction of archive contents.\n",
									f.getAbsolutePath());
						}
					} else {
						int count;
						byte data[] = new byte[2048];
						// FileOutputStream fos = new FileOutputStream(entry.getName(), false);
						// try (BufferedOutputStream dest = new BufferedOutputStream(fos, 2048)) {
						dest = new ByteArrayOutputStream();
						while ((count = tarIn.read(data, 0, 2048)) != -1) {
							dest.write(data, 0, count);
						}

						listaFacturas.add(index, dest.toString());
						index++;
					}
				}

				tarIn.close();
				return listaFacturas;
			} else {
				return new ArrayList<>();
			}
		} catch (Exception e) {
			LOG.error("decompressLoteFacturas || Exception");
			LogExcepcion.registrar(e, LOG);
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T mapearXmlToDto(String pXmlFactura, Class<?> pObjetoDto) {
		try {
			if (pXmlFactura != null && !pXmlFactura.isEmpty() && pObjetoDto != null) {
				Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(pObjetoDto).createUnmarshaller();
				return (T) jaxbUnmarshaller.unmarshal(new StringReader(pXmlFactura));
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error("mapearXmlToDto || Exception");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}

	@Override
	public String codificarToBase64(String pArchivo) {
		try {
			if (pArchivo != null && !pArchivo.isEmpty()) {
				String vXmlB64 = Base64.getEncoder().encodeToString(pArchivo.getBytes(StandardCharsets.UTF_8));
				return vXmlB64;
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error("codificarToBase64 || Exception");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}

	@Override
	public String decodificarToBase64(String pArchivo) {
		try {
			if (pArchivo != null && !pArchivo.isEmpty()) {
				byte[] bytes = Base64.getDecoder().decode(pArchivo.trim());
				return new String(bytes, StandardCharsets.UTF_8);
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error("decodificarToBase64 || Exception");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}

	@Override
	public boolean validarXmlXsd(String pArchivoXml, String nombreArchivoXsd) {
		try {
			if (pArchivoXml != null && !pArchivoXml.isEmpty() && nombreArchivoXsd != null
					&& !nombreArchivoXsd.isEmpty()) {
				SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
				((schemaFactory.newSchema(new File(nombreArchivoXsd))).newValidator())
						.validate(new StreamSource(new StringReader(pArchivoXml)));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			LOG.error("validarXmlXsd || Excepcion");
			LogExcepcion.registrar(e, LOG, MethodSign.build(pArchivoXml));
			return false;
		}
	}

	@Override
	public String formatearSignature(String pArchivoXml) {
		try {

			if (pArchivoXml != null && !pArchivoXml.isEmpty()) {
				// Codigo Fuente adaptado de pagina web
				// https://www.journaldev.com/1237/java-convert-string-to-xml-document-and-xml-document-to-string
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setValidating(false);

				// Convierte cadena del archivo xml en objeto Document
				Document doc = this.convertStringToDocument(pArchivoXml);

				if (doc != null) {
					// Recupera el elemento 'Signature'
					Element elementSignature = (Element) doc.getElementsByTagName("Signature").item(0);
					// verifica que exista el tag Signature
					if (elementSignature != null) {
						// Eliminar el nodo recuperado
						elementSignature.getParentNode().removeChild(elementSignature);

						// Normalize the DOM tree, puts all text nodes in the
						// full depth of the sub-tree underneath this node
						doc.normalize();

						// Transforma objeto Document en Cadena, para enviar a validacion xml-xsd
						DOMSource domSource = new DOMSource(doc);
						Transformer transformer = TransformerFactory.newInstance().newTransformer();
						StringWriter sw = new StringWriter();
						StreamResult sr = new StreamResult(sw);
						transformer.transform(domSource, sr);
						String vNuevoXml = sw.toString();
						// LIberando memoria
						doc = null;
						domSource = null;
						sr = null;
						sw = null;

						return vNuevoXml;
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error("formatearSignature || Exception");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}

	public List<Integer> validarFacturaXml(String pArchivoXml, String pNombreArchivoXsd) {
		List<Integer> vListaErrores = new ArrayList<>();
		if (pArchivoXml != null && !pArchivoXml.isEmpty() && pNombreArchivoXsd != null
				&& !pNombreArchivoXsd.isEmpty()) {
			if (!validarXmlXsd(pArchivoXml, pNombreArchivoXsd)) {
				vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
			}
		} else {
			vListaErrores.add(CodigosMensajesServiciosSOAPServiceImpl.FACTURA_FORMATO_XSD_INCORRECTO);
		}
		return vListaErrores;
	}

	/**
	 * @autor edwin.coro
	 * @descripción metodo que convierte cadena comprimida base 64 en String
	 * @param pCadenaComprimida cadena comprimida base 64
	 * @return cadena descromprida
	 * @fecha 06/05/2019
	 */
	private String descomprimirByteToString(byte[] pCadenaComprimida) {
		StringBuilder sb = new StringBuilder();
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(pCadenaComprimida);
			GZIPInputStream gis = new GZIPInputStream(bais);
			BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			gis.close();
			bais.close();
			return sb.toString();
		} catch (Exception e) {
			LOG.error("descomprimirByteToString || Exception");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}

	/**
	 * @autor edwin.coro
	 * @descripción metodo que convierte una cadena en formato xml a formato
	 *              Document
	 * @param xmlStr archivo en formato xml
	 * @return objeto Document correspondiente a cadena en formato xml
	 * @fecha 06/05/2019
	 */
	private Document convertStringToDocument(String xmlStr) {
		// Codigo Fuente adaptado de pagina web
		// https://www.journaldev.com/1237/java-convert-string-to-xml-document-and-xml-document-to-string

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			LOG.error("convertStringToDocument || Exception");
			LogExcepcion.registrar(e, LOG);
			return null;
		}
	}

	@Override
	public List<String> obtenerErroresXmlXsd(String pArchivoXml, String nombreArchivoXsd) {
		List<String> vListaErrores = new ArrayList<>();
		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			Validator validator = schemaFactory.newSchema(new File(nombreArchivoXsd)).newValidator();

			validator.setErrorHandler(new ErrorHandler() {
				@Override
				public void warning(SAXParseException exception) throws SAXException {
					int vInicio = exception.getMessage().indexOf(':') + 2;
					vListaErrores.add(exception.getMessage().substring(vInicio));
				}

				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					int vInicio = exception.getMessage().indexOf(':') + 2;
					vListaErrores.add(exception.getMessage().substring(vInicio));
				}

				@Override
				public void error(SAXParseException exception) throws SAXException {
					int vInicio = exception.getMessage().indexOf(':') + 2;
					vListaErrores.add(exception.getMessage().substring(vInicio));
				}
			});
			StringReader vXmlReader = new StringReader(pArchivoXml);
			validator.validate(new StreamSource(vXmlReader));

		} catch (SAXException e) {
		} catch (IOException e) {
		} catch (Exception e) {
		}

		return vListaErrores;
	}

	/**
	 * @autor junior.flores
	 * @descripción Método que realiza la inserción de los log para la certificación
	 *              de sistemas
	 * @param pXmlRecGenDto archivo en formato xml pListaErrores Listado de los
	 *                      codigos de los errores luego de la validación
	 * @fecha 04/06/2019
	 */
	public void registrarLogCertificacionSistemas(XmlRecepcionGenericoDto pXmlRecGenDto, List<Integer> pListaErrores) {
		// Realizar el registro de los logs
		LogConsumoServicioDto vObjLogConsumoServicioDto = new LogConsumoServicioDto();
		ObjectMapper mapper = null;
		ModelMapper vMapper = null;
		Document vXmlRecGenDocument = null;
		if (pXmlRecGenDto != null && pXmlRecGenDto.getP_tipo_ambiente_id() == 2) {
			try {
				vXmlRecGenDocument = convertStringToDocument(pXmlRecGenDto.getV_archivoXml());
				String vDigestValue = obtenerNodoDeArchivoDocument(vXmlRecGenDocument, "DigestValue");
				String bSignatureValuee = obtenerNodoDeArchivoDocument(vXmlRecGenDocument, "SignatureValue");
				bSignatureValuee = bSignatureValuee == null ? bSignatureValuee : bSignatureValuee.trim();
				mapper = new ObjectMapper();
				vMapper = new ModelMapper();
				vObjLogConsumoServicioDto = vMapper.map(pXmlRecGenDto, LogConsumoServicioDto.class);
				vObjLogConsumoServicioDto.setP_codigo_error(vDigestValue);
				vObjLogConsumoServicioDto.setP_descripcion_respuesta(bSignatureValuee);
				vObjLogConsumoServicioDto.setP_etapa_certificacion_sistema_id(Parametros.ETAPA_VII_FIRMA_DIGITAL);
				JSONObject jsonRecepcion = new JSONObject();
				JSONObject jsonFinal = new JSONObject();
				jsonRecepcion = new JSONObject(mapper.writeValueAsString(vObjLogConsumoServicioDto));
				jsonFinal.put("xmlRecepcionLog", jsonRecepcion);
				iJsonDomain.registroLogCertificacionSistemas(jsonFinal.toString());
				jsonFinal = null;
				jsonRecepcion = null;
			} catch (Exception e) {
				// TODO: handle exception
				LOG.error("Exception || registrarLogCertificacionSistemas : " + e.getMessage());
			}
		}
		mapper = null;
		vMapper = null;
		vObjLogConsumoServicioDto = null;
	}

	/**
	 * @autor junior.flores
	 * @descripción Método que devuelve el valor del nodo de un archivo tipo
	 *              Document
	 * @param pDocument archivo en formato xml pNombreNodo Descripción del nombre
	 *                  del nodo
	 * @return Cadena con el valor resultante del nodo buscado
	 * @fecha 30/05/2019
	 */
	public String obtenerNodoDeArchivoDocument(Document pDocument, String pNombreNodo) {
		String vDescripcionTag = "";
		try {
			pDocument.getDocumentElement().normalize();
			NodeList nlist = pDocument.getElementsByTagName(pNombreNodo);
			Node nNode = nlist.item(0);
			vDescripcionTag = nNode.getTextContent();
		} catch (Exception e) {

		}
		return vDescripcionTag;
	}

	/**
	 * @autor freddy.yuca
	 * @descripción implentacion para obtencion de archivo a partir de una ruta
	 *              (pruebas unitarias)
	 * @param ruta
	 * @return
	 * @fecha 18/04/2019
	 */
	@Override
	public List<String> obtenerArchivosEnvio(String ruta) {
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(ruta));
			String content = new String(bytes);
			String archivoBase64 = codificarToBase64(content);
			String archivo = obtenerGzipBase64DeCadena(archivoBase64);
			ValidarArchivoHashImpl validarHash = new ValidarArchivoHashImpl();
			String archivoHash = validarHash.sha(archivo, "SHA-256");
			List<String> respuesta = new ArrayList<>();
			respuesta.add(archivo);
			respuesta.add(archivoHash);
			return respuesta;
		} catch (IOException e) {
			return null;
		}
	}

	private String obtenerGzipBase64DeCadena(String content) throws IOException {
		String respuesta = "";
		byte[] bytes = zip(content);
		String contenidoZip = Base64.getEncoder().encodeToString(bytes);
		respuesta = contenidoZip;
		return respuesta;
	}

	private byte[] zip(final String str) {
		if ((str == null) || (str.length() == 0)) {
			throw new IllegalArgumentException("Cannot zip null or empty string");
		}

		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
				gzipOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
			}
			return byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException("Failed to zip content", e);
		}
	}

	// metodo implementado por equipo de Vigmar Carlo Morales
	@Override
	public BufferedImage generarImagenQR(String pUrl, String pCuf, Long pNitEmisor, Integer pNumeroFactura) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(pUrl).append("var1=" + pCuf).append("&var2=" + pNitEmisor)
				.append("&var3=" + pNumeroFactura);
		try {
			return this.generarImagenQR(stringBuilder.toString());
		} catch (Exception e) {
			return null;
		}
	}

	private BufferedImage generarImagenQR(String pCodigoQR) throws Exception {
		QRGenerator vGenerador = new QRGenerator();
		byte[] image = vGenerador.generateQR(pCodigoQR, 354, 354, "png");
		return ImageIO.read(new ByteArrayInputStream(image));
	}

	//RCR
	@Override
	public int obtenerCantidadPaquete(String pArchivo) {
		try {
			if (pArchivo != null && !pArchivo.isEmpty()) {
				
				byte[] compressed = Base64.getDecoder().decode(pArchivo);
				ByteArrayInputStream bais = new ByteArrayInputStream(compressed);
				GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(bais);
				TarArchiveInputStream tarIn = new TarArchiveInputStream(gzipIn);
				TarArchiveEntry entry;
				ByteArrayOutputStream dest = new ByteArrayOutputStream();
				int index = 0;

				while ((entry = (TarArchiveEntry) tarIn.getNextEntry()) != null) {
					/** If the entry is a directory, create the directory. **/
					if (entry.isDirectory()) {
						File f = new File(entry.getName());
						boolean created = f.mkdir();
						if (!created) {
							System.out.printf(
									"Unable to create directory '%s', during extraction of archive contents.\n",
									f.getAbsolutePath());
						}
					} else {
						int count;
						byte data[] = new byte[2048];
						dest = new ByteArrayOutputStream();
						while ((count = tarIn.read(data, 0, 2048)) != -1) {
							dest.write(data, 0, count);
						}
						index++;
					}
				}
				tarIn.close();
				return index;
			} else {
				return 0;
			}
		} catch (Exception e) {
			LOG.error("revisionCantidadFacturas || Exception");
			LogExcepcion.registrar(e, LOG);
			return 0;
		}
	}
}