package bo.gob.sin.sre.fac.cfec.servicedomain;

import java.awt.image.BufferedImage;
import java.util.List;

import bo.gob.sin.sre.fac.cfec.dto.XmlRecepcionGenericoDto;

public interface IUtilitarios {

	/**
	 * @autor edwin.coro
	 * @descripción decodifica archivo enviado en servicio SOAP
	 * @param pArchivo archivo recibido en servicio SOAP en formato string
	 * @return archivo xml en formato String, null si existe error
	 * @fecha 12/11/2018
	 */
	public String decodificarArchivo(String pArchivo);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que descomprime paquete y obtiene un listado con los
	 *              archivos xml que contenga el paquete
	 * @param compressed
	 * @return obtiene listado de documentos fiscales en formato xml
	 * @fecha 15/11/2018
	 */
	public List<String> decompressLoteFacturas(byte[] compressed);

	/**
	 * @param <T>
	 * @autor edwin.coro
	 * @descripción mapea facturea de archivo xml a estructura dto
	 *              (cabecera/detalle)
	 * @param vXmlFactura factura en formato xml
	 * @param pDto        dto que contiene cabecera detalle de la factura
	 * @return null si existio error, object con mapeo realizado
	 * @fecha 13/11/2018
	 */
	public <T> T mapearXmlToDto(String pXmlFactura, Class<?> pObjetoDto);

	/**
	 * @autor reynaldo chambi
	 * @descripción
	 * @param Encode
	 * @return
	 * @fecha 13/12/2018
	 */
	public String codificarToBase64(String pArchivo);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que compara si un archivo xml cumple la estructura de un
	 *              archivo xsd
	 * @param pArchivoXml      archivo xml en formato string
	 * @param nombreArchivoXsd nombre del archivo xsd (dichos archivos deben estar
	 *                         en el mismo repositorio que la clase IUtilitarios)
	 * @return true si es valido, caso contrario false,
	 * @fecha 14/12/2018
	 */
	public boolean validarXmlXsd(String pArchivoXml, String nombreArchivoXsd);

	/**
	 * @autor edwin.coro
	 * @descripción obtiene cadena xml exceptuando tag signature, a partir de un
	 *              documento fiscal electronico
	 * @param pArchivoXml documento fiscal electronico en formato xml
	 * @return cadena en formato xml (quitando tag signature)
	 * @fecha 06/05/2019
	 */
	public String formatearSignature(String pArchivoXml);

	/**
	 * @autor edwin.coro
	 * @descripción decodifica cadena codificada en base 64
	 * @param pArchivo cadena codificada en base 64
	 * @return cadena decodificada
	 * @fecha 06/05/2019
	 */
	public String decodificarToBase64(String pArchivo);

	/**
	 * @autor edwin.coro
	 * @descripción valida una cadena en formato xml contra un archivo xsd
	 * @param archivoXml       cadena en formato xml
	 * @param nombreArchivoXsd nombre del archivo xsd con el que se validara el
	 *                         archivo xml
	 * @return lista con codigo de error si xml no cumple estructura de archivo xsd
	 * @fecha 06/05/2019
	 */
	public List<Integer> validarFacturaXml(String archivoXml, String nombreArchivoXsd);

	/**
	 * @autor edwin.coro
	 * @descripción metodo que compara si un archivo xml cumple la estructura de un
	 *              archivo xsd y lista la descripcion de los errores
	 *              correspondientes
	 * @param pArchivoXml      archivo xml en formato string
	 * @param nombreArchivoXsd nombre del archivo xsd (dichos archivos deben estar
	 *                         en el mismo repositorio que la clase IUtilitarios)
	 * @return lista de descripcion de errores.
	 * @fecha 08/05/2019
	 */
	public List<String> obtenerErroresXmlXsd(String pArchivoXml, String nombreArchivoXsd);

	/**
	 * @autor freddy yuca
	 * @descripción metodo que obtiene los string de archivos codificados desde una
	 *              ruta local de una factura xml
	 * @param pRuta archivo xml de la factura
	 * @return lista de cadenas, archivo codificado y sha.
	 * @fecha 24/05/2019
	 */
	public List<String> obtenerArchivosEnvio(String pRuta);

	/**
	 * @autor junior.flores
	 * @descripción Método que realiza la inserción de los log para la certificación
	 *              de sistemas
	 * @param pXmlRecGenDto archivo en formato xml pListaErrores Listado de los
	 *                      codigos de los errores luego de la validación
	 * @fecha 04/06/2019
	 */
	public void registrarLogCertificacionSistemas(XmlRecepcionGenericoDto pXmlRecGenDto, List<Integer> pListaErrores);

	public BufferedImage generarImagenQR(String pUrl, String pCuf, Long pNitEmisor, Integer pNumeroFactura);
	
	/**
	 * @autor reynaldo.chambi
	 * @descripción conteo de cantidad de archivos
	 * @fecha 27/09/2019
	 */
	public int obtenerCantidadPaquete(String pArchivo);
}