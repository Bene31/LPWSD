/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Questao;
import bd.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author alunoces
 */
public class QuestaoDao {
    
    public static QuestaoDao questaoDao;

    public static QuestaoDao getInstance() {
        if (questaoDao == null) {
            questaoDao = new QuestaoDao();
        }
        return questaoDao;
    }
    
    public Questao buscar(int idQuestao) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select q from questao q where q.idQuestao =:idQuestao ");
        query.setParameter("idQuestao", idQuestao);

        List<Questao> assunto = query.getResultList();
        if (assunto != null && assunto.size() > 0) {
            return assunto.get(0);
        }
        return null;
    }
    
    public Questao buscarQuestao(int idQuestao) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery(
                "select q from Questao q where q.idQuestao =:idQuestao ");
        query.setParameter("idQuestao", idQuestao);

        List<Questao> questao = query.getResultList();
        if (questao != null && questao.size() > 0) {
            return questao.get(0);
        }
        return null;
    }

    public List<Questao> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Questao As q");
        return query.getResultList();
    }

    public List<Questao> buscarQuestaoInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct q from Questao q ");
        return query.getResultList();
    }
    
    public void remover(Questao questao) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(questao)) {
            questao = em.merge(questao);
        }
        em.remove(questao);
        em.getTransaction().commit();
    }

    public Questao persistir(Questao questao) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            questao = em.merge(questao);
            em.getTransaction().commit();
            System.out.println("Registro Questao gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questao;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Questao ");
       query.executeUpdate();
       em.getTransaction().commit();
    }
}