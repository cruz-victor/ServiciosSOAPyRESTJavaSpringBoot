package bo.gob.sin.sre.fac.model;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import bo.gob.sin.str.cps.clas.dto.ClasificadorDto;

@FacesConverter("modalidadConverter")
public class ModalidadConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) {
    	ClasificadorDto cla = new ClasificadorDto();
		cla.setClasificadorId(1424);
		cla.setDescripcion("PREVALORADA ELECTRONICA");
		cla.setAbreviatura("mo");
		cla.setTipoClasificador("tipo");
    	
        return cla;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (object == null) {
            return "";
        }
        if (object instanceof ClasificadorDto) {
        	ClasificadorDto clasificador = (ClasificadorDto) object;
            String name = clasificador.getAbreviatura();
            return name;
        } else {
            throw new ConverterException(new FacesMessage(object + " no es válido"));
        }
    }
}