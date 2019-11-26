/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Usuario;
import bd.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author alunoces
 */
public class UsuarioDao {
    
    public static UsuarioDao usuarioDao;
    

    public static UsuarioDao getInstance() {
        if (usuarioDao == null) {
            usuarioDao = new UsuarioDao();
        }
        return usuarioDao;
    }
    
    public Usuario buscar(int id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select u from Usuario u where u.id =:id ");
        query.setParameter("id", id);

        List<Usuario> usuarioList = query.getResultList();
        if (usuarioList != null && usuarioList.size() > 0) {
            return usuarioList.get(0);
        }
        return null;
    }
    
    public Usuario buscarUsuario(int id) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery(
                "select u from Usuario u where u.id =:id ");
        query.setParameter("id", id);

        List<Usuario> usuarioList = query.getResultList();
        if (usuarioList != null && usuarioList.size() > 0) {
            return usuarioList.get(0);
        }
        return null;
    }

    public List<Usuario> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Usuario As u");
        return query.getResultList();
    }

    public List<Usuario> buscarUsuarioInstancia() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select distinct u from Usuario u ");
        return query.getResultList();
    }
    
    public void remover(Usuario usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(usuario)) {
            usuario = em.merge(usuario);
        }
        em.remove(usuario);
        em.getTransaction().commit();
    }

    public Usuario persistir(Usuario usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            usuario = em.merge(usuario);
            em.getTransaction().commit();
            System.out.println("Registro Usuario gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void removeAll() {
       EntityManager em = PersistenceUtil.getEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery(" delete from Usuario ");
       query.executeUpdate();
       em.getTransaction().commit();
    }

    public Usuario getLogin(Usuario usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery(
            "select u from Usuario u where u.cpf =:cpf "
            + "and u.senha = :senha");
        query.setParameter("cpf", usuario.getCpf());
        query.setParameter("senha", usuario.getSenha());
        
        List<Usuario> usuariosRetornados = query.getResultList();
        if (usuariosRetornados != null && usuariosRetornados.size() > 0) {
            return usuariosRetornados.get(0);
        }
        return null;
    }
    
    /*
    public void excluirCursoAluno(Usuario usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from curso_has_usuario where id =:id");
        query.executeUpdate();
        em.getTransaction().commit();
    }
    */
}

