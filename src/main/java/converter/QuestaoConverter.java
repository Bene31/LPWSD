
package converter;

import model.Questao;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author alunoces
 */
@FacesConverter("QuestaoConverter")
@ManagedBean
public class QuestaoConverter implements Converter {
    
  @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return (Questao) uic.getAttributes().get(value);
        }
        return null;
    }
  @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Questao) {
            Questao questao = (Questao) value;
            if (questao != null && questao instanceof Questao && questao.getIdQuestao()!= null) {
                uic.getAttributes().put(questao.getIdQuestao().toString(), questao);
                return questao.getIdQuestao().toString();
            }
        }
        return "";
    }
   
}
