package GraphicInterface;

import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class Refeicoes {
    private final Sistema sistema = Sistema.getInstance();

    @FXML private GridPane refeicoes;
    @FXML private Pane refeicoesPane;
    @FXML private ScrollPane scrollPane;

    private Index indexController;

    /**
     * Handler para quando clicamos no butão de adicionar um alimento
     *
     * Cria uma nova stage do "AdicionaRefPopUp.fxml"
     *
     * @param
     */
    public void adicionaRefeicao() {
        try {
            Stage popup = new Stage();
            popup.initStyle(StageStyle.UNDECORATED);
            popup.setTitle("Adicionar refeição");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdicionaRefPopUp.fxml"));
            Pane parent = loader.load();

            //Para passar todos os controladores e scenes necessarios a nova stage
            AdicionaRefPopUp adicionaRefPopUp = loader.getController();

            adicionaRefPopUp.setPrimals(refeicoes, indexController);

            Scene scene = new Scene(parent);

            popup.setScene(scene);
            popup.show();
        }catch (Exception e) {
            System.out.println("Index - adicionaRefeicao() : " +e.toString());
        }
    }

    public void editaRefeicao() {
        try {
            Stage popup = new Stage();
            popup.initStyle(StageStyle.UNDECORATED);
            popup.setTitle("Edita refeição");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditRefPopUp.fxml"));
            Pane parent = loader.load();

            //Para passar todos os controladores e scenes necessarios a nova stage
            EditRefPopUp editRefPopUp = loader.getController();
            editRefPopUp.populateLista();
            editRefPopUp.setPrimals(indexController);

            Scene scene = new Scene(parent);

            popup.setScene(scene);
            popup.show();
        }catch (Exception e) {
            System.out.println("Index - editaRefeicao() : " +e.toString());
        }
    }

    /**
     * Preset para criar refeicoes pelo codigo
     * @param nome - Nome da refeição
     */
    public int adicionaRefeicao(String nome) {
        try {
            //Cria a refeicao no sistema com o nome colocado
            int id = sistema.createRefeicao(nome);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("RefPane.fxml"));
            Parent parent = loader.load();

            RefPane refPane = loader.getController();
            refPane.setName(nome);
            refPane.setId(id);
            refPane.setPrimals(indexController);

            sistema.searchRefeicao(id).setRefPaneController(refPane);

            //Adiciona novo refPane ao painel de refeiçoes do index
            int nrRef = refeicoes.getChildren().size();
            this.refeicoes.add(parent,(nrRef-1)%2,(nrRef-1)/2);

            return id;
        }catch (Exception e) {
            System.out.println("Index - adicionaRefeicao() : "+e.toString());
            return -1;
        }
    }

    public void setPrimals(Index i) {
        this.indexController = i;
    }

    public void delete(Parent refPane) {
        refeicoes.getChildren().remove(refPane);

        sortGrid();
    }

    private void sortGrid() {
        ArrayList<Node> refPanes = new ArrayList<>();
        for(Node n : refeicoes.getChildren()) {
            refPanes.add(n);
        }

        refeicoes.getChildren().clear();

        for(int i=0; i<refPanes.size(); i++) {
            refeicoes.add(refPanes.get(i), i%2, i/2);
        }
    }

    public void setWidth(double newVal) {
        refeicoesPane.setPrefWidth(newVal-171);
        refeicoes.setPrefWidth(newVal-191);
    }

    public void setHeight(double newVal) {
        refeicoesPane.setPrefHeight(newVal);
    }
}
