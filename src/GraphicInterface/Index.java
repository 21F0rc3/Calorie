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

    private Pane inicioPane, perfilPane, refeicoesPane;
    private Inicio inicioController;
    private Refeicoes refPaneController;
    private Perfil perfilController;

    public void Inicio() {
        inicioBar.setVisible(true);
        refeicoesBar.setVisible(false);
        perfilBar.setVisible(false);

        if(inicioController==null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
                Pane parent = loader.load();

                Inicio inicio = loader.getController();
                inicio.setPrimals(this);

                inicioPane = parent;
                inicioController = inicio;

            } catch (Exception e) {
                System.out.println("Index - Inicio(): " + e.toString());
            }
        }
        conteudo.getChildren().clear();
        conteudo.getChildren().add(inicioPane);
    }

    public void Refeicoes() {
        inicioBar.setVisible(false);
        refeicoesBar.setVisible(true);
        perfilBar.setVisible(false);

        if(refPaneController==null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Refeicoes.fxml"));
                Pane parent = loader.load();

                Refeicoes ref = loader.getController();
                ref.setPrimals(this);

                parent.setPrefWidth(conteudo.getWidth());
                parent.setPrefHeight(conteudo.getHeight());

                refeicoesPane = parent;
                refPaneController = ref;

            } catch (Exception e) {
                System.out.println("Index - Refeicoes(): " + e.toString());
            }
        }
        conteudo.getChildren().clear();
        conteudo.getChildren().add(refeicoesPane);
    }

    public void Perfil() {
        inicioBar.setVisible(false);
        refeicoesBar.setVisible(false);
        perfilBar.setVisible(true);

        if(perfilController==null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Perfil.fxml"));
                Pane parent = loader.load();

                Perfil perfil = loader.getController();
                perfil.setPrimals(this);

                perfilPane = parent;
                perfilController = perfil;
            } catch (Exception e) {
                System.out.println("Index - Perfil(): " + e.toString());
            }
        }
        conteudo.getChildren().clear();
        conteudo.getChildren().add(perfilPane);
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

    public AnchorPane getConteudo() {
        return this.conteudo;
    }

    public AnchorPane getMenu() {return this.menu;}
}
