package bo.gob.sin.sre.fac.csis.query.controller;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bo.gob.sin.sre.fac.csis.query.IClasificadorQuery;
import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;
import bo.gob.sin.str.cps.clas.model.StrCpsClasificador;

@CrossOrigin(maxAge = 3600)
@RestController

@RequestMapping("/clasificador")
public class ClasificadorController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOG = LoggerFactory.getLogger(ClasificadorController.class);

	@Autowired
	IClasificadorQuery clasificadorQuery;

	@RequestMapping(value = "/tipoClasificador/{tipoClasificador}", method = RequestMethod.GET)
	public ResponseEntity<List<ClasificadorDto>> listaClasificadoresPorTIpo(@PathVariable String tipoClasificador) {
		//System.out.println("Ingresando al metodos");

		List<StrCpsClasificador> listaClasificadores = clasificadorQuery.listarClasificadorPorTipo(tipoClasificador);

		ModelMapper clasificadorMapper = new ModelMapper();

		// Definimos el tipo de lista de destino para el mapeo
		Type strClasificadorDtoType = new TypeToken<List<ClasificadorDto>>() {
		}.getType();

		// Mapeamos de Entidad -> DTO
		List<ClasificadorDto> listaClasificadorDto = clasificadorMapper.map(listaClasificadores,
				strClasificadorDtoType);

		if (listaClasificadorDto.size() > 0) {
			return new ResponseEntity<List<ClasificadorDto>>(listaClasificadorDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ClasificadorDto>>(HttpStatus.NO_CONTENT);
		}
	}
}
