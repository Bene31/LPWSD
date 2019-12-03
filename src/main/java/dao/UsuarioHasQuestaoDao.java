/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.PersistenceUtil;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author terra
 */
public class UsuarioHasQuestaoDao implements Serializable{

    public static UsuarioHasQuestaoDao usuarioHasQuestaoDao;

    public static UsuarioHasQuestaoDao getInstance() {
        if (usuarioHasQuestaoDao == null) usuarioHasQuestaoDao = new UsuarioHasQuestaoDao();
       
        return usuarioHasQuestaoDao;
    }
   
    public void persistir(int idQuestao,int id) {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery("insert into usuarioHasQuestaoDao values(usuario_id_usuario, questao_id_questao)");
       query.executeUpdate();
       em.getTransaction().commit();
    }
}
