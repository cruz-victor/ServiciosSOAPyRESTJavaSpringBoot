package bo.gob.sin.sre.fac.model;

import java.util.List;
import java.util.function.Predicate;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import bo.gob.sin.sap.cder.dto.UsuarioAsignacionDto;


@FacesConverter(value = "funcionariosAsignacionConverter")
public class FuncionariosAsignacionConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		}

		try {
			  return this.getSelectedItemAsEntity(arg1, arg2);
		} catch (NumberFormatException e) {
			throw e;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return "";
		}

		if (arg2 instanceof UsuarioAsignacionDto) {
			return String.valueOf(((UsuarioAsignacionDto) arg2).getUsuarioId());
		} else {
			throw new ConverterException(new FacesMessage(arg2 + " Funcionario Invalido"));
		}
	}

	 private UsuarioAsignacionDto getSelectedItemAsEntity(UIComponent comp, String value) {
		 UsuarioAsignacionDto item = null;

	        List<UsuarioAsignacionDto> selectItems = null;
	        for (UIComponent uic : comp.getChildren()) {
	            if (uic instanceof UISelectItems) {
	                Long itemId = Long.valueOf(value);
	                selectItems = (List<UsuarioAsignacionDto>) ((UISelectItems) uic).getValue();
	                if (itemId != null && selectItems != null && !selectItems.isEmpty()) {
	                    Predicate<UsuarioAsignacionDto> predicate = i -> i.getUsuarioId().equals(itemId);
	                    item = selectItems.stream().filter(predicate).findFirst().orElse(null);
	                }
	            }
	        }

	        return item;
	    }

}
