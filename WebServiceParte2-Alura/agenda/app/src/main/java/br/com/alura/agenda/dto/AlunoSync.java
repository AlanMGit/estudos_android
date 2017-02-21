package br.com.alura.agenda.dto;

import java.util.Date;
import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by alan on 21/02/17.
 */

public class AlunoSync {

    private List<Aluno> alunos;
    private String momentoDaUltimaModificacao;

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public String getMomentoDaUltimaModificacao() {
        return momentoDaUltimaModificacao;
    }
}
