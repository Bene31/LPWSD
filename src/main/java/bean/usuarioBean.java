/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import converter.CursoConverter;
import dao.CursoDao;
import model.Usuario;
import dao.UsuarioDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.faces.bean.ManagedBean;
import model.Curso;

/**
 *
 * @author alunoces
 */
@ManagedBean(name = "usuarioBean")
@ViewScoped
public class usuarioBean {
    
    Usuario usuario = new Usuario();
    List usuarios = new ArrayList();
    private List<Curso> cursos;
    private Curso idCurso;
    private UsuarioDao usuarioDao;
    private CursoDao cursoDao;
    private CursoConverter cuc;

    public usuarioBean() {
        usuarios = new UsuarioDao().buscarTodas();
        usuario = new Usuario();
        cursos = new CursoDao().buscarTodas();
        cursoDao = new CursoDao();
        usuarioDao = new UsuarioDao();
        cuc = new CursoConverter();
    }
    
    public void Record(ActionEvent actionEvent)
    {
        new UsuarioDao().persistir(usuario);
        usuarios = new UsuarioDao().buscarTodas();
        usuario = new Usuario();
    }
    
    public void Exclude(ActionEvent actionEvent)
    {
        new UsuarioDao().remover(usuario);
        usuarios = new UsuarioDao().buscarTodas();
        usuario = new Usuario();
    }
    
    public Usuario getUsuario()
    {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }
    
    public List getUsuarios()
    {
        return usuarios;
    }
    
    public void setUsuarios(List usuarios) {
        this.usuarios = usuarios;
    }
    
    public void login() throws IOException {
        Usuario usuarioLogado = new UsuarioDao().getLogin(this.usuario);
        
        if (usuarioLogado != null)
        {
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            session.setAttribute("usuario", usuarioLogado);
            
            response.sendRedirect("index.xhtml");
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Login ou senha incorretos!",
							"Favor inserir usuário e senha novamente!"));
        }
    }
    
    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
   

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public CursoDao getCursoDao() {
        return cursoDao;
    }

    public void setCursoDao(CursoDao cursoDao) {
        this.cursoDao = cursoDao;
    }

    public CursoConverter getCuc() {
        return cuc;
    }

    public void setCuc(CursoConverter cuc) {
        this.cuc = cuc;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }
    
}
