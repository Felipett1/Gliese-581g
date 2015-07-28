package co.convertidores;
 
import co.entidades.Rol;
import co.interfaces.persistencia.IPersistenciaRol;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
 
@ManagedBean
@RequestScoped
public class ConvertidorRoles implements Converter {
 
    @EJB
    IPersistenciaRol persistenciaRol;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return persistenciaRol.obtenerRol(new BigDecimal(value));
            } catch(NumberFormatException e) {
                return null;
            }
        }
        else {
            return null;
        }
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Rol) object).getId());
        }
        else {
            return null;
        }
    }   
}         
    