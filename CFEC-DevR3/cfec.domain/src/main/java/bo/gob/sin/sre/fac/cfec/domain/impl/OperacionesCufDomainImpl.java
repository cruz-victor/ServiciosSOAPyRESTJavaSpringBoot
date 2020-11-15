package bo.gob.sin.sre.fac.cfec.domain.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//Genera codigo Unico de factura 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.fac.cfec.dao.IJsonDao;
import bo.gob.sin.sre.fac.cfec.dao.IOperacionCufDao;
import bo.gob.sin.sre.fac.cfec.domain.IOperacionesCufDomain;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaDatosCufDto;
import bo.gob.sin.sre.fac.cfec.dto.RespuestaFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.SolicitudObtencionFacturaVentasDto;
import bo.gob.sin.sre.fac.cfec.dto.parameter.Parametros;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.util.facturacion.Utiles;

@Service
@Transactional

public class OperacionesCufDomainImpl implements IOperacionesCufDomain {
	
	private static final Logger LOG = LoggerFactory.getLogger(OperacionesCufDomainImpl.class);

	@Autowired
	private IOperacionCufDao iOperacionCufDao;
	
	public boolean verificacionCuf(Long pNit, Integer pSucursal, String pFecha, Integer pModalidad, Integer pTipoEmision,  Integer pTipoDocumentoFiscal, Integer pTipoDocumentoSector, String pCuf, Long pNumFactura, Integer pPos) {
        
        String vCuf = "";
        try {
               vCuf = this.generadorCUF(pNit, pSucursal, pFecha, pModalidad, pTipoEmision, pTipoDocumentoFiscal,pTipoDocumentoSector, pNumFactura,pPos);
               if (vCuf.equals(pCuf))  {
                      return true;       
               }
               else {
                      return false;
               }

        } catch (Exception e) {
               LogExcepcion.registrar(e, LOG, MethodSign.build(pNit));
               System.out.println("Error de Validaciones");
               LOG.info("error:" + e.toString());
               return false;
        }
  }             

	
	//Genera Codigo Unico de Factura

	public  String generacuf(String input) {
		
	    BigInteger toHex = new BigInteger(input,10);
		String hexa=toHex.toString(16);
//		System.out.println("The value in Hex is: "+ hexa);
		return hexa.toUpperCase();
	}
	
	// Metodo que permite realizar validaciones basicas
	//Mod. IASC - Se añadio parámetro Nro. Factura - 23/04/2018
	public int validar(Long pNit, Integer pSucursal, String pFecha, Integer pModalidad, Integer pTipoEmision,  Integer pTipoDocumentoFiscal, Integer pTipoDocumentoSector, Long pNumFactura, Integer pPos){

		int sw = 0;
	
		String vNit = String.valueOf(pNit);
		String vSucursal = String.valueOf(pSucursal);
		String vFecha = pFecha;
		String vModalidad = String.valueOf(pModalidad);
		String vTipoEmision = String.valueOf(pTipoEmision);
		String vTipoDocFiscal = String.valueOf(pTipoDocumentoFiscal);
		String vTipoDocSector = String.valueOf(pTipoDocumentoSector);
		String vNumFactura = String.valueOf(pNumFactura);
		//String vPos = String.valueOf(pPos);
		 
		for (int i = 0;i <= vNit.length()-1;i++) {
			String caracter = String.valueOf(vNit.charAt(i));
			try {
				Integer number = Integer.valueOf(caracter);
			} catch (Exception e) {
				System.out.println("Solo se admiten nÃºmeros");
				sw = 1;
				return sw;
			}
		}
		
		if (vNit.length()<1 || vNit.length()>13 ){
			System.out.println("Long Nit invalida");
			sw = 1;
			return sw;
		}
		
		if (vSucursal.length()<1 || vSucursal.length()>4 || vSucursal == "null"){
			System.out.println("Long Sucursal invalida");
			sw = 1;
			return sw;
		}
		
		if (vFecha.length()<1 || vFecha.length()>17 ){
			System.out.println("Long fecha invalida");
			sw = 1;
			return sw;
		}
		
		if (vModalidad.length() < 1 || vModalidad  == "null"){
			System.out.println("Long modalidad invalida");
			sw = 1;
			return sw;
		}
		
		if (vTipoEmision.length() < 1 ||  vTipoEmision == "null"){
			System.out.println("Long tipo emision invalida");
			sw = 1;
			return sw;
		}
		
		if (vTipoDocFiscal.length() < 1 ||  vTipoDocFiscal == "null"){
			System.out.println("Long tipodoc invalida");
			sw = 1;
			return sw;
		}
		
		if (vTipoDocSector.length() < 1 ||  vTipoDocSector == "null"){
			System.out.println("Long tipodoc invalida");
			sw = 1;
			return sw;
		}
		
		if (vNumFactura.length() < 1 ||  vNumFactura == "null"){
			System.out.println("Long Numero Factura invalida");
			sw = 1;
			return sw;
		}
		
		if (pPos < 0){
			System.out.println("Long Numero Pos invalida");
			sw = 1;
			return sw;
		}
		
		return sw;
	}	
		
	//Revierte Numero Unico de Factura Generado

	public  String reviertecuf(String input) {
		
	    BigInteger todec = new BigInteger(input,16);
		String deca = todec.toString(10);
		System.out.println(deca);
		return deca;
	}

	//Concatena campos recibidos
	//Mod. IASC - Se añadio parámetro Nro. Factura - 23/04/2018
	public  String concatenar(Long pNit, Integer pSucursal, String pFecha, Integer pModalidad, Integer pTipoEmision,  Integer pTipoDocumentoFiscal, Integer pTipoDocumentoSector, Long pNumFactura, Integer pPos) {

		Utiles utiles = new Utiles();
		
		String vNit = String.valueOf(pNit);
		String vSucursal = String.valueOf(pSucursal);
		String vFecha = pFecha;
		String vModalidad = String.valueOf(pModalidad);
		String vTipoEmision = String.valueOf(pTipoEmision);
		String vTipoDocFiscal = String.valueOf(pTipoDocumentoFiscal);
		String vTipoDocSector = String.valueOf(pTipoDocumentoSector);
		String vNumFactura = String.valueOf(pNumFactura);
		String vPos = String.valueOf(pPos);
		
		String concatena = null;
		
		if(pTipoDocumentoSector == Parametros.DOCUMENTO_SECTOR_FACTURA_BOLETO_AEREO) {
			concatena = utiles.corregirLongitudCadena(Parametros.CORREGIR_LONGITUD_NIT_CUF, vNit)
					+ vFecha
					+ utiles.corregirLongitudCadena(Parametros.CORREGIR_LONGITUD_SUCURSAL_CUF, vSucursal)
					+ vModalidad + vTipoEmision + vTipoDocFiscal
					+ utiles.corregirLongitudCadena(Parametros.CORREGIR_LONGITUD_NIT_DOC_SECTOR, vTipoDocSector)
					+ utiles.corregirLongitudCadena(Parametros.CORREGIR_LONGITUD_NUMERO_FACTURA_CUF_BOLETO_AEREO, vNumFactura)
					+ utiles.corregirLongitudCadena(Parametros.CORREGIR_LONGITUD_POS_CUF, vPos);	
		} else {
			concatena = utiles.corregirLongitudCadena(Parametros.CORREGIR_LONGITUD_NIT_CUF, vNit)
					+ vFecha
					+ utiles.corregirLongitudCadena(Parametros.CORREGIR_LONGITUD_SUCURSAL_CUF, vSucursal)
					+ vModalidad + vTipoEmision + vTipoDocFiscal
					+ utiles.corregirLongitudCadena(Parametros.CORREGIR_LONGITUD_NIT_DOC_SECTOR, vTipoDocSector)
					+ utiles.corregirLongitudCadena(Parametros.CORREGIR_LONGITUD_NUMERO_FACTURA_CUF, vNumFactura)
					+ utiles.corregirLongitudCadena(Parametros.CORREGIR_LONGITUD_POS_CUF, vPos);
		}

		return concatena;
	}
	
	//Calcula Modulo 11
	public  String calculaDigitoMod11(String dado, int numDig, int limMult, boolean x10) {

	    int mult, soma, i, n, dig;
	    
	    if(!x10) numDig = 1;
	    for(n=1; n<=numDig; n++){
	        soma = 0;
	        mult = 2;
	        for(i=dado.length() - 1; i >= 0; i--){
	            soma += (mult * Integer.parseInt(dado.substring(i, i + 1)));
	            if(++mult > limMult) mult = 2;
	        }
	        if(x10){
	            dig = ((soma * 10) % 11) % 10;
	        } else {
	            dig = soma % 11;
	        }
	        
	        if (x10) {
				dig = ((soma * 10) % 11) % 10;
			} else {
				dig = soma % 11;
			}
			
			if (dig == 10) {
				dado += "1";
			} 
			if (dig == 11) { 
				dado += "0";
			}
			if (dig < 10) {
				dado += String.valueOf(dig);
			}	
	    }
	    return dado.substring(dado.length() - numDig, dado.length());
	}
	
	// Metodo generador de codigo CUF
	// Mod. IASC - Se añadio parámetro Nro. Factura - 23/04/2018
	public String generadorCUF(Long pNit, Integer pSucursal, String pFecha, Integer pModalidad, Integer pTipoEmision,  Integer pTipoDocumentoFiscal, Integer pTipoDocumentoSector, Long pNumFactura, Integer pPos){
	
		String scuf = "";
		try {
			 Date vFechaEmision = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(pFecha);
			SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String vFecha = formateador.format(vFechaEmision);
//			System.out.println("Fecha: " + vFecha);
			
			if(pPos== null)	pPos=0;
			int valida = this.validar(pNit, pSucursal, vFecha, pModalidad, pTipoEmision,  pTipoDocumentoFiscal, pTipoDocumentoSector, pNumFactura, pPos);
		
			if (valida == 0){
				String concatena = this.concatenar(pNit, pSucursal, vFecha, pModalidad, pTipoEmision,  pTipoDocumentoFiscal, pTipoDocumentoSector, pNumFactura, pPos);
//				System.out.println("Concatenado: " + concatena);
				String dig = this.calculaDigitoMod11(concatena, 1, 9, false);
//				System.out.println("Mod11: " + dig);
				concatena = concatena + dig;
//				System.out.println("ConcatenaFinal: " + concatena);
				scuf=  this.generacuf(concatena);
				return scuf;
			} 
			else {
				System.out.println("Error de Validaciones");
				return scuf;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error("Exception || generadorCUF : " + e.getMessage());
		}
		return scuf;
	}
	
	public RespuestaFacturaVentasDto obtenerFacturaVentas(SolicitudObtencionFacturaVentasDto pSolicitud, RespuestaDatosCufDto vRespuestaCuf) {
		
		return iOperacionCufDao.obtenerFacturaVentas(pSolicitud, vRespuestaCuf);
	}
	
	
}
