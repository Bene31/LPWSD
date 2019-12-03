/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import converter.ProvaConverter;
import dao.ProvaDao;
import model.Questao;
import dao.QuestaoDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import model.Prova;

/**
 *
 * @author alunoces
 */
@ManagedBean
@ViewScoped
public class questaoBean {
    
    Questao questao = new Questao();
    private List<Prova> provas;
    private Prova idProva;
    private QuestaoDao questaoDao;
    private ProvaDao provaDao;
    private ProvaConverter pcc;
    List questoes = new ArrayList();

    public questaoBean() {
        questoes = new QuestaoDao().buscarTodas();
        questao = new Questao();
        provas = new ProvaDao().buscarTodas();
        provaDao = new ProvaDao();
        questaoDao = new QuestaoDao();
        pcc = new ProvaConverter();
    }
    
    public void Record(ActionEvent actionEvent)
    {
        new QuestaoDao().persistir(questao);
        questoes = new QuestaoDao().buscarTodas();
        questao = new Questao();
    }
    
    public void Exclude(ActionEvent actionEvent)
    {
        new QuestaoDao().remover(questao);
        questoes = new QuestaoDao().buscarTodas();
        questao = new Questao();
    }
    
    public Questao getQuestao()
    {
        return questao;
    }
    
    public void setQuestao(Questao questao)
    {
        this.questao = questao;
    }
    
    public List getQuestoes()
    {
        return questoes;
    }
    
    public void setQuestoes(List questoes) {
        this.questoes = questoes;
    }

    public List<Prova> getProvas() {
        return provas;
    }

    public void setProvas(List<Prova> provas) {
        this.provas = provas;
    }

    public Prova getIdProva() {
        return idProva;
    }

    public void setIdProva(Prova idProva) {
        this.idProva = idProva;
    }

    public QuestaoDao getQuestaoDao() {
        return questaoDao;
    }

    public void setQuestaoDao(QuestaoDao questaoDao) {
        this.questaoDao = questaoDao;
    }

    public ProvaDao getProvaDao() {
        return provaDao;
    }

    public void setProvaDao(ProvaDao provaDao) {
        this.provaDao = provaDao;
    }

    public ProvaConverter getPcc() {
        return pcc;
    }

    public void setPcc(ProvaConverter pcc) {
        this.pcc = pcc;
    }
    
    
}
