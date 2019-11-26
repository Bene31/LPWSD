/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Resposta;
import bd.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Questao;

/**
 *
 * @author alunoces
 */
public class RespostaDao {
    
    public static RespostaDao respostaDao;

    public static RespostaDao getInstance() {
        if (respostaDao == null) {
            respostaDao = new RespostaDao();
        }
        return respostaDao;
    }
    
    public Resposta buscar(int id_resposta) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select r from Resposta r where r.idResposta =:id_resposta ");
        query.setParameter("id_resposta", id_resposta);

        List<Resposta> respostaList = query.getResultList();
        if (respostaList != null && respostaList.size() > 0) {
            return respostaList.get(0);
        }
        return null;
    }
    
    public Resposta buscarResposta(int id_resposta) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery(
                "select r from Resposta r where r.idResposta =:id_resposta ");
        query.setParameter("id_resposta", id_resposta);

        List<Resposta> respostaList = query.getResultList();
        if (respostaList != null && respostaList.size() > 0) {
            return respostaList.get(0);
        }
        return null;
    }

    public List<Resposta> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Resposta As r");
        return query.getResultList();
    }

    public List<Resposta> buscarRespostaInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct r from Resposta r ");
        return query.getResultList();
    }
    
    public void remover(Resposta resposta) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(resposta)) {
            resposta = em.merge(resposta);
        }
        em.remove(resposta);
        em.getTransaction().commit();
    }

    public Resposta persistir(Resposta resposta) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            resposta = em.merge(resposta);
            em.getTransaction().commit();
            System.out.println("Registro resposta gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resposta;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Resposta ");
       query.executeUpdate();
       em.getTransaction().commit();
    }
}