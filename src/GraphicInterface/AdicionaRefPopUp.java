package GraphicInterface;

import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Ref;

public class AdicionaRefPopUp {
    private final Sistema sistema = Sistema.getInstance();

    private GridPane indexRefPane;
    private Index indexController;

    @FXML
    private TextField textField;

    /**
     * Handler paara o evento de clicar no botão Adiciona
     *
     * Cria um novo pane "RefPane.fxml" com o nome da refeição e insere-o no Index na aba de Refeições(indexRefPane)
     */
    public void adiciona() {
        try {
            //Cria a refeicao no sistema com o nome colocado
            String nome = textField.getText();
            int id = sistema.createRefeicao(nome);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("RefPane.fxml"));
            Parent parent = loader.load();

            RefPane refPane = loader.getController();
            refPane.setName(nome);
            refPane.setId(id);
            refPane.setPrimals(indexController);

            sistema.searchRefeicao(id).setRefPaneController(refPane);

            //Adiciona novo refPane ao painel de refeiçoes do index
            int nrRef = indexRefPane.getChildren().size();
            indexRefPane.add(parent,(nrRef-1)%2,(nrRef-1)/2);

            Stage stage = (Stage)textField.getScene().getWindow();
            stage.close();
        }catch (Exception e) {
            System.out.println("AdicionaRefPopUp - adiciona() : "+e.toString());
        }
    }

    /**
     * Metodo para designar todos os controladores e paineis necessarios para este controlador.
     * Comunicação entre controladores
     * @param t - indexRefPane
     * @param i - indexController
     */
    public void setPrimals(GridPane t, Index i) {
        this.indexRefPane = t;
        this.indexController = i;
    }

    public void close() {
        Stage stage = (Stage)textField.getScene().getWindow();
        stage.close();
    }
}
