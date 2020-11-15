package bo.gob.sin.sre.fac.service;

import bo.gob.sin.sre.fac.dto.ConsultaFacturasVentasDto;
import bo.gob.sin.sre.fac.dto.RespuestaVentasFacturasDto;

public interface IFacturasVentasService {

	/** 
	 * Obtiene la información de las siguientes facturas de ventas:   
	 *  			Factura Estándar
	 *				Factura Sectores Educativos
	 *				Factura De Alquiler De Bienes Inmuebles
	 *				Factura De Comercialización De Hidrocarburos
	 *				Factura De Servicios Básicos
	 *				Factura De Embotelladoras
	 *				Factura De Entidades Financieras
	 *				Factura De Hoteles
	 *				Facturas De Hospitales/Clínicas
	 *				Factura De Juegos De Azar
	 *				Factura De Artistas Internacionales
	 *				Factura Comercial De Exportación
	 * @author: Peter Flores 
	 * @Fecha: 28/12/2018
	 * @param pConsultaFacturas, parámetros de entrada para realizar la búsqueda
	 * @return   Devuelve objeto Dto de tipo RespuestaVentasFacturasDto.	 
	 */	
	public RespuestaVentasFacturasDto obtenerConsultaFacturasVentas(ConsultaFacturasVentasDto pConsultaFacturas); 
}
