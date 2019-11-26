
package converter;

import bd.PersistenceUtil;
import model.Curso;
import dao.CursoDao;
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
@FacesConverter("CursoConverter")
@ManagedBean
public class CursoConverter implements Converter {
    
  @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            //return (Curso) uic.getAttributes().get(value);
            return PersistenceUtil.getEntityManager().find(Curso.class, Integer.parseInt(value));
        }
        return null;
    }
  @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        /*if (value instanceof Curso) {
            Curso curso = (Curso) value;
            if (curso != null && curso instanceof Curso && curso.getIdCurso()!= null) {
                uic.getAttributes().put(curso.getIdCurso().toString(), curso);
                return curso.getIdCurso().toString();
            }
        }
        return "";*/
        
        if(value == null){
            return null;
        }
        Curso cu = (Curso) value;
        return cu.getIdCurso().toString();
    }
   
}
