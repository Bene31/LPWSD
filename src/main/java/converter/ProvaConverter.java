
package converter;

import bd.PersistenceUtil;
import dao.ProvaDao;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import model.Prova;

/**
 *
 * @author alunoces
 */
@FacesConverter("ProvaConverter")
@ManagedBean
public class ProvaConverter implements Converter {
    
  @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (Prova) uic.getAttributes().get(value);
            }
        return null;
    }
  @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Prova) {
            Prova prova = (Prova) value;
            if (prova != null && prova instanceof Prova && prova.getIdProva()!= null) {
                uic.getAttributes().put(prova.getIdProva().toString(), prova);
                return prova.getIdProva().toString();
            }
        }
        return "";
    }
   
}
