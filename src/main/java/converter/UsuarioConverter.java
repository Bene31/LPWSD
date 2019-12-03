
package converter;

import bd.PersistenceUtil;
import model.Usuario;
import dao.UsuarioDao;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

/**
 *
 * @author alunoces
 */
@FacesConverter("UsuarioConverter")
@ManagedBean
public class UsuarioConverter implements Converter {
    
  @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (Usuario) uic.getAttributes().get(value);
            }
        return null;
    }
  @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Usuario) {
            Usuario usuario = (Usuario) value;
            if (usuario != null && usuario instanceof Usuario && usuario.getId()!= null) {
                uic.getAttributes().put(usuario.getId().toString(), usuario);
                return usuario.getId().toString();
            }
        }
        return "";
    }
   
}
