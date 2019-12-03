/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import converter.CursoConverter;
import dao.CursoDao;
import model.Prova;
import dao.ProvaDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Curso;

/**
 *
 * @author alunoces
 */
@ManagedBean
@ViewScoped
public class provaBean {
    
    Prova prova = new Prova();
    private List<Curso> cursos;
    private Curso idCurso;
    private ProvaDao provaDao;
    private CursoDao cursoDao;
    private CursoConverter cuc;
    List provas = new ArrayList();

    public provaBean() {
        provas = new ProvaDao().buscarTodas();
        prova = new Prova();
        cursos = new CursoDao().buscarTodas();
        cursoDao = new CursoDao();
        provaDao = new ProvaDao();
        cuc = new CursoConverter();
    }
    
    public void Record(ActionEvent actionEvent)
    {
        new ProvaDao().persistir(prova);
        provas = new ProvaDao().buscarTodas();
        prova = new Prova();
    }
    
    public void Exclude(ActionEvent actionEvent)
    {
        new ProvaDao().remover(prova);
        provas = new ProvaDao().buscarTodas();
        prova = new Prova();
    }
    
    public Prova getProva()
    {
        return prova;
    }
    
    public void setProva(Prova prova)
    {
        this.prova = prova;
    }
    
    public List getProvas()
    {
        return provas;
    }
    
    public void setProvas(List provas) {
        this.provas = provas;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public ProvaDao getProvaDao() {
        return provaDao;
    }

    public void setProvaDao(ProvaDao provaDao) {
        this.provaDao = provaDao;
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
    
    
    
}
