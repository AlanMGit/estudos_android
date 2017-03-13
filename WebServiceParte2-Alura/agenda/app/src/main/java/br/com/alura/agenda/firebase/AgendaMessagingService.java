package br.com.alura.agenda.firebase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.util.Map;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.dto.AlunoSync;
import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by alan on 13/03/17.
 */

public class AgendaMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> data = remoteMessage.getData();

        converteParaAluno(data);

    }

    private void converteParaAluno(Map<String, String> data) {

        try {
            String chaveAcesso = "alunoSync";
            if (data.containsKey(chaveAcesso)) {
                String jsonAluno = data.get(chaveAcesso);
                ObjectMapper mapper = new ObjectMapper();
                AlunoSync alunoSync = mapper.readValue(jsonAluno, AlunoSync.class);
                AlunoDAO alunoDAO = new AlunoDAO(this);
                alunoDAO.insere(alunoSync.getAlunos());
                alunoDAO.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
