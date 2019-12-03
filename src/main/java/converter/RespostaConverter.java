
package converter;

import bd.PersistenceUtil;
import model.Resposta;
import dao.RespostaDao;
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
@FacesConverter("RespostaConverter")
@ManagedBean
public class RespostaConverter implements Converter {
    
  @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (Resposta) uic.getAttributes().get(value);
            }
        return null;
    }
  @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Resposta) {
            Resposta resposta = (Resposta) value;
            if (resposta != null && resposta instanceof Resposta && resposta.getIdResposta()!= null) {
                uic.getAttributes().put(resposta.getIdResposta().toString(), resposta);
                return resposta.getIdResposta().toString();
            }
        }
        return "";
    }
   
}
