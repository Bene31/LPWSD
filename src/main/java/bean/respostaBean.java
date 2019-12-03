/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import converter.QuestaoConverter;
import dao.QuestaoDao;
import model.Resposta;
import dao.RespostaDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import model.Questao;

/**
 *
 * @author alunoces
 */
@ManagedBean
@ViewScoped
public class respostaBean {
    
    Resposta resposta = new Resposta();
    private List<Questao> questoes;
    private Questao idQuestao;
    private RespostaDao respostaDao;
    private QuestaoDao questaoDao;
    private QuestaoConverter qcc;
    List respostas = new ArrayList();

    public respostaBean() {
        respostas = new RespostaDao().buscarTodas();
        resposta = new Resposta();
        questoes = new QuestaoDao().buscarTodas();
        questaoDao = new QuestaoDao();
        respostaDao = new RespostaDao();
        qcc = new QuestaoConverter();
    }
    
    public void Record(ActionEvent actionEvent)
    {
        new RespostaDao().persistir(resposta);
        respostas = new RespostaDao().buscarTodas();
        resposta = new Resposta();
    }
    
    public void Exclude(ActionEvent actionEvent)
    {
        new RespostaDao().remover(resposta);
        respostas = new RespostaDao().buscarTodas();
        resposta = new Resposta();
    }
    
    public Resposta getResposta()
    {
        return resposta;
    }
    
    public void setResposta(Resposta resposta)
    {
        this.resposta = resposta;
    }
    
    public List getRespostas()
    {
        return respostas;
    }
    
    public void setRespostas(List respostas) {
        this.respostas = respostas;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public Questao getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Questao idQuestao) {
        this.idQuestao = idQuestao;
    }

    public RespostaDao getRespostaDao() {
        return respostaDao;
    }

    public void setRespostaDao(RespostaDao respostaDao) {
        this.respostaDao = respostaDao;
    }

    public QuestaoDao getQuestaoDao() {
        return questaoDao;
    }

    public void setQuestaoDao(QuestaoDao questaoDao) {
        this.questaoDao = questaoDao;
    }

    public QuestaoConverter getQcc() {
        return qcc;
    }

    public void setQcc(QuestaoConverter qcc) {
        this.qcc = qcc;
    }
    
    
    
}
