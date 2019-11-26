/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import model.Resposta;
import dao.RespostaDao;
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
public class respostaBean {
    
    Resposta resposta = new Resposta();
    
    List respostas = new ArrayList();

    public respostaBean() {
        respostas = new RespostaDao().buscarTodas();
        resposta = new Resposta();
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
    
}
