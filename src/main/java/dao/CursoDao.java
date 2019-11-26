/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Curso;
import bd.PersistenceUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author alunoces
 */
public class CursoDao implements Serializable{
    public static CursoDao cursoDao;
    
    public static CursoDao getInstance(){
        if(cursoDao == null){
            cursoDao = new CursoDao();
        }
        return cursoDao;
    }
    
    public Curso buscar(int idCurso){
        try{
            EntityManager em = PersistenceUtil.getEntityManager();
            Query query = em.createQuery("SELECT c FROM Curso c WHERE c.idCurso = :idCurso");
            query.setParameter("idCurso", idCurso);
            Curso curso = (Curso) query.getSingleResult();
            if(curso != null && curso.getIdCurso() > 0){
                return curso;
            }else {
                Logger.getLogger(PersistenceUtil.class.getName()).log(Level.INFO, "ERRO");
                return null;
            }
        }catch (Exception e){
            Logger.getLogger(PersistenceUtil.class.getName()).log(Level.WARNING, "ERRO");
            return null;
        }
    }
    
    public Curso buscar(Curso c){
        try{
            EntityManager em = PersistenceUtil.getEntityManager();
            Query query = em.createQuery("SELECT c FROM Curso c WHERE c.idCurso = :idCurso");
            query.setParameter("idCurso", c.getIdCurso());
            Curso curso = (Curso) query.getSingleResult();
            if(curso != null && curso.getIdCurso() > 0){
                return curso;
            }else {
                Logger.getLogger(PersistenceUtil.class.getName()).log(Level.INFO, "ERRO");
                return null;
            }
        }catch (Exception e){
            Logger.getLogger(PersistenceUtil.class.getName()).log(Level.WARNING, "ERRO");
            return null;
        }
    }
    
    public List<Curso> buscarTodas(){
        try{
            EntityManager em = PersistenceUtil.getEntityManager();
            Query query = em.createQuery("SELECT c FROM Curso c");
            return query.getResultList();
        }catch (Exception e){
            Logger.getLogger(PersistenceUtil.class.getName()).log(Level.WARNING, "ERRO");
            return new ArrayList<>();
        }
    }
    
    public String excluir(Curso curso){
        try{
            EntityManager em = PersistenceUtil.getEntityManager();
            em.getTransaction().begin();
            curso = em.merge(curso);
            em.remove(curso);
            em.getTransaction().commit();
            Logger.getLogger(PersistenceUtil.class.getName()).log(Level.INFO, "Curso removido com sucesso!");
            return "Curso " + curso.getNmCurso() + " removido com sucesso!";
        }catch (Exception e){
            Logger.getLogger(PersistenceUtil.class.getName()).log(Level.WARNING, "ERRO");
            return "Não foi possível remover o curso " + curso.getNmCurso() + "pois está vinculado a um ou mais alunos";
        }
    }
    
    public String persistir(Curso curso){
        EntityManager em = PersistenceUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            curso = em.merge(curso);
            em.getTransaction().commit();
            Logger.getLogger(PersistenceUtil.class.getName()).log(Level.INFO, "Curso Salvo com sucesso!");
            return "Curso " + curso.getNmCurso() + " salvo com sucesso!";
        }catch(Exception e){
            em.getTransaction().rollback();
            Logger.getLogger(PersistenceUtil.class.getName()).log(Level.WARNING, "Não foi possível salvar o curso!", e);
            if(e.getMessage().contains("ConstraintViolationException")){
                return "Não foi possível salvar o curso " + curso.getNmCurso() + ", pois o curso deve ser único ";
            }
        }return "Não foi possível salvar o curso " + curso.getNmCurso() + "!";
    }
            
    public String removeAll(){
        try{
            EntityManager em = PersistenceUtil.getEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Curso");
            query.executeUpdate();
            em.getTransaction().commit();
            Logger.getLogger(PersistenceUtil.class.getName()).log(Level.INFO, "Todos os cursos foram deletados com sucesso!");
            return "Todos os cursos foram deletados!";
        }catch(Exception e){
            Logger.getLogger(PersistenceUtil.class.getName()).log(Level.WARNING, "Não foi possível deletar todos os cursos");
            return "Não foi possível deletar todos os cursos!";
        }
    }
}
