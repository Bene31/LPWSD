/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alunoces
 */
@Entity
@Table(name = "questao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questao.findAll", query = "SELECT q FROM Questao q"),
    @NamedQuery(name = "Questao.findByIdQuestao", query = "SELECT q FROM Questao q WHERE q.idQuestao = :idQuestao"),
    @NamedQuery(name = "Questao.findByDescricaoQuestao", query = "SELECT q FROM Questao q WHERE q.descricaoQuestao = :descricaoQuestao"),
    @NamedQuery(name = "Questao.findByTipoQuestao", query = "SELECT q FROM Questao q WHERE q.tipoQuestao = :tipoQuestao")})
public class Questao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_questao")
    private int idQuestao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descricao_questao")
    private String descricaoQuestao;
    @Size(max = 21)
    @Column(name = "tipo_questao")
    private String tipoQuestao;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "questao")
    private List<UsuarioHasQuestao> usuarioHasQuestaoList;*/
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "questaoIdQuestao")
    private List<Resposta> respostaList;*/
    @JoinColumn(name = "prova_id_prova", referencedColumnName = "id_prova", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Prova prova;
    

    public Questao() {
    }

    public Questao(Integer idQuestao, String descricaoQuestao, String tipoQuestao) {
        this.idQuestao = idQuestao;
        this.descricaoQuestao = descricaoQuestao;
        this.tipoQuestao = tipoQuestao;
    }

    public int getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(int idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getDescricaoQuestao() {
        return descricaoQuestao;
    }

    public void setDescricaoQuestao(String descricaoQuestao) {
        this.descricaoQuestao = descricaoQuestao;
    }

    public String getTipoQuestao() {
        return tipoQuestao;
    }

    public void setTipoQuestao(String tipoQuestao) {
        this.tipoQuestao = tipoQuestao;
    }
    
    /*@XmlTransient
    public List<UsuarioHasQuestao> getUsuarioHasQuestaoList() {
        return usuarioHasQuestaoList;
    }

    public void setUsuarioHasQuestaoList(List<UsuarioHasQuestao> usuarioHasQuestaoList) {
        this.usuarioHasQuestaoList = usuarioHasQuestaoList;
    }

    @XmlTransient
    public List<Resposta> getRespostaList() {
        return respostaList;
    }

    public void setRespostaList(List<Resposta> respostaList) {
        this.respostaList = respostaList;
    }
*/
    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idQuestao;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Questao other = (Questao) obj;
        if (this.idQuestao != other.idQuestao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Questao{" + "idQuestao=" + idQuestao + ", descricaoQuestao=" + descricaoQuestao + ", tipoQuestao=" + tipoQuestao + ", prova=" + prova + '}';
    }

}
