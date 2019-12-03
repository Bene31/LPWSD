/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alunoces
 */
@Embeddable
public class UsuarioHasQuestaoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_id_usuario")
    private int usuarioIdUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "questao_id_questao")
    private int questaoIdQuestao;

    public UsuarioHasQuestaoPK() {
    }

    public UsuarioHasQuestaoPK(int usuarioIdUsuario, int questaoIdQuestao) {
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.questaoIdQuestao = questaoIdQuestao;
    }

    public int getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public int getQuestaoIdQuestao() {
        return questaoIdQuestao;
    }

    public void setQuestaoIdQuestao(int questaoIdQuestao) {
        this.questaoIdQuestao = questaoIdQuestao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuarioIdUsuario;
        hash += (int) questaoIdQuestao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioHasQuestaoPK)) {
            return false;
        }
        UsuarioHasQuestaoPK other = (UsuarioHasQuestaoPK) object;
        if (this.usuarioIdUsuario != other.usuarioIdUsuario) {
            return false;
        }
        if (this.questaoIdQuestao != other.questaoIdQuestao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.enadearthurterra.model.UsuarioHasQuestaoPK[ usuarioIdUsuario=" + usuarioIdUsuario + ", questaoIdQuestao=" + questaoIdQuestao + " ]";
    }
    
}
