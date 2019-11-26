/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import model.Curso;
import dao.CursoDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author alunoces
 */
@ManagedBean
@ViewScoped
public class cursoBean {
    
    Curso curso = new Curso();
    
    List cursos = new ArrayList();

    public cursoBean() {
        cursos = new CursoDao().buscarTodas();
        curso = new Curso();
    }
    
    public void Record(ActionEvent actionEvent)
    {
        new CursoDao().persistir(curso);
        cursos = new CursoDao().buscarTodas();
        curso = new Curso();
    }
    
    public void Exclude(ActionEvent actionEvent)
    {
        new CursoDao().excluir(curso);
        cursos = new CursoDao().buscarTodas();
        curso = new Curso();
    }
    
    public Curso getCurso()
    {
        return curso;
    }
    
    public void setCurso(Curso curso)
    {
        this.curso = curso;
    }
    
    public List getCursos()
    {
        return cursos;
    }
    
    public void setCursos(List cursos) {
        this.cursos = cursos;
    }
    
}
