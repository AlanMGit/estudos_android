package br.com.alura.agenda.tasks;

import android.os.AsyncTask;

import br.com.alura.agenda.converter.AlunoConverter;
import br.com.alura.agenda.modelo.Aluno;
import br.com.alura.agenda.web.WebClient;

/**
 * Created by Alan on 20/02/2017.
 */

public class InsereAlunoTask extends AsyncTask {

    private Aluno mAluno;

    public InsereAlunoTask(Aluno mAluno) {
        this.mAluno = mAluno;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        String json = new AlunoConverter().converteParaJSONCompleto(mAluno);
        new WebClient().insere(json);

        return null;
    }
}
