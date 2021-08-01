package GraphicInterface;

import Classes.Alimento;
import Classes.Refeicao;
import Classes.Sistema;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Index {
    private final Sistema sistema = Sistema.getInstance();

    @FXML private AnchorPane conteudo;
    @FXML private AnchorPane menu;

    @FXML private Group inicioBar;
    @FXML private Group refeicoesBar;
    @FXML private Group perfilBar;
    @FXML private Group historicoBar;

    //@FXML private WebView logo;

    private Pane inicioPane, perfilPane, refeicoesPane, historicoPane;
    private Inicio inicioController;
    private Refeicoes refPaneController;
    private Perfil perfilController;
    private Historico historicoController;

    public void Start() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Pane parent = loader.load();

            Inicio inicio = loader.getController();
            inicio.setPrimals(this);

            inicioPane = parent;
            inicioController = inicio;

            conteudo.getChildren().add(inicioPane);
        } catch (Exception e) {
            System.out.println("Index - Inicio(): " + e.toString());
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Refeicoes.fxml"));
            Pane parent = loader.load();

            Refeicoes ref = loader.getController();
            ref.setPrimals(this);

            parent.setPrefWidth(conteudo.getWidth());
            parent.setPrefHeight(conteudo.getHeight());

            refeicoesPane = parent;
            refPaneController = ref;

            conteudo.getChildren().add(refeicoesPane);
        } catch (Exception e) {
            System.out.println("Index - Refeicoes(): " + e.toString());
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Perfil.fxml"));
            Pane parent = loader.load();

            Perfil perfil = loader.getController();
            perfil.setPrimals(this);

            perfilPane = parent;
            perfilController = perfil;

            conteudo.getChildren().add(perfilPane);
        } catch (Exception e) {
            System.out.println("Index - Perfil(): " + e.toString());
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Historico.fxml"));
            Pane parent = loader.load();

            Historico historico = loader.getController();
            historico.setPrimals(this);

            historicoPane = parent;
            historicoController = historico;

            conteudo.getChildren().add(historicoPane);
        } catch (Exception e) {
            System.out.println("Index - Historico(): " + e.toString());
        }

        Inicio();
    }

    public void Inicio() {
        inicioBar.setVisible(true);
        refeicoesBar.setVisible(false);
        perfilBar.setVisible(false);
        historicoBar.setVisible(false);

        inicioPane.setVisible(true);
        refeicoesPane.setVisible(false);
        perfilPane.setVisible(false);
        historicoPane.setVisible(false);
    }

    public void Refeicoes() {
        inicioBar.setVisible(false);
        refeicoesBar.setVisible(true);
        perfilBar.setVisible(false);
        historicoBar.setVisible(false);

        inicioPane.setVisible(false);
        refeicoesPane.setVisible(true);
        perfilPane.setVisible(false);
        historicoPane.setVisible(false);
    }

    public void Perfil() {
        inicioBar.setVisible(false);
        refeicoesBar.setVisible(false);
        perfilBar.setVisible(true);
        historicoBar.setVisible(false);

        inicioPane.setVisible(false);
        refeicoesPane.setVisible(false);
        perfilPane.setVisible(true);
        historicoPane.setVisible(false);
    }

    public void Historico() {
        inicioBar.setVisible(false);
        refeicoesBar.setVisible(false);
        perfilBar.setVisible(false);
        historicoBar.setVisible(true);

        inicioPane.setVisible(false);
        refeicoesPane.setVisible(false);
        perfilPane.setVisible(false);
        historicoPane.setVisible(true);
    }

    public Refeicoes getRefPaneController() {
        return this.refPaneController;
    }

    public Inicio getInicioController() {
        return this.inicioController;
    }

    public Perfil getPerfilController() {
        return this.perfilController;
    }

    public Historico getHistoricoController() { return this.historicoController;}

    public void close() {
        Stage stage = (Stage) conteudo.getScene().getWindow();
        stage.close();
    }

    public AnchorPane getConteudo() {
        return this.conteudo;
    }

    public AnchorPane getMenu() {return this.menu;}
}
