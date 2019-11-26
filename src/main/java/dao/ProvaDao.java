/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Prova;
import bd.PersistenceUtil;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author alunoces
 */
public class ProvaDao {
    
    public static ProvaDao provaDao;

    public static ProvaDao getInstance() {
        if (provaDao == null) {
            provaDao = new ProvaDao();
        }
        return provaDao;
    }
    
    public Prova buscar(int id_prova) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select p from Prova p where p.idProva =:id_prova ");
        query.setParameter("id_prova", id_prova);

        List<Prova> provaList = query.getResultList();
        if (provaList != null && provaList.size() > 0) {
            return provaList.get(0);
        }
        return null;
    }
    
    public Prova buscarProva(int id_prova, Date ano, int id_curso) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery(
                "select p from Prova p where p.idProva =:id_prova "
                /*+ "and p.ano =:ano "
                + "and p.idCurso =:id_curso"*/);
        query.setParameter("id_prova", id_prova);
        //query.setParameter("ano", ano);
        //query.setParameter("id_curso", id_curso);

        List<Prova> provaList = query.getResultList();
        if (provaList != null && provaList.size() > 0) {
            return provaList.get(0);
        }
        return null;
    }

    public List<Prova> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Prova As p");
        return query.getResultList();
    }

    public List<Prova> buscarProvaInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct p from Prova p ");
        return query.getResultList();
    }
    
    public void remover(Prova prova) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(prova)) {
            prova = em.merge(prova);
        }
        em.remove(prova);
        em.getTransaction().commit();
    }

    public Prova persistir(Prova prova) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            prova = em.merge(prova);
            em.getTransaction().commit();
            System.out.println("Registro prova gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prova;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Prova ");
       query.executeUpdate();
       em.getTransaction().commit();
    }
}