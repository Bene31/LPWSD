/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import model.Questao;
import dao.QuestaoDao;
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
public class questaoBean {
    
    Questao questao = new Questao();
    
    List questoes = new ArrayList();

    public questaoBean() {
        questoes = new QuestaoDao().buscarTodas();
        questao = new Questao();
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
    
}
