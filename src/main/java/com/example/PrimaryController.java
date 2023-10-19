package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    @FXML
    Pagination pagination;

    private int pagina = 1;

    public FlowPane carregar() {
        try {
            URL url = new URL("https://supernatural-quotes-api.cyclic.app/characters?page=" + pagina);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String json = reader.readLine();
            List<Personagem> lista = jsonParaLista(json);
            return mostrarPersonagens(lista);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensagem("Erro. " + e.getMessage());
        }
        return null;
    }

    private FlowPane mostrarPersonagens(List<Personagem> lista) {
        FlowPane flow = new FlowPane();
        flow.setVgap(20);
        flow.setHgap(20);

        lista.forEach(personagem -> {
            ImageView image = new ImageView(new Image(personagem.getImg()));
            image.setFitHeight(150);
            image.setFitWidth(150);
            Label labelName = new Label(personagem.getName());
            Label labelLocation = new Label(personagem.getLocation());
            flow.getChildren().add(new VBox(image, labelName, labelLocation));
        });

        return flow;
    }

    private List<Personagem> jsonParaLista(String json) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataNode;
        try {
            dataNode = mapper.readTree(json).get("data");
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        List<Personagem> lista = new ArrayList<>();
        dataNode.forEach(personagemNode -> {
            try {
                lista.add(mapper.readValue(personagemNode.toString(), Personagem.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return lista;
    }

    private void mostrarMensagem(String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pagination.setPageFactory(pag -> {
            pagina = pag + 1;
            return carregar();
        });
    }
}
