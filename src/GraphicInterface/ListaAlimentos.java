package GraphicInterface;

import Classes.Alimento;
import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ListaAlimentos {
    private final Sistema sistema = Sistema.getInstance();

    private Pane refPane;
    private RefPane refPaneController;
    private Index indexController;

    @FXML private TableView<Alimento> lista;

    @FXML private TextField searchBar;

    @FXML private TableColumn<Alimento,String> nome;
    @FXML private TableColumn<Alimento,Integer> cal;
    @FXML private TableColumn<Alimento,Float> lip;
    @FXML private TableColumn<Alimento,Float> carb;
    @FXML private TableColumn<Alimento,Float> prot;

    /**
     * Populaciona a lista com todos os alimentos registados no sistema
     *
     * Implementa um handler para quando clicamos numa row/alimento. Chama a alimentoSelect()
     */
    public void populateLista() {
        lista.setRowFactory(tv -> {
            TableRow<Alimento> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    alimentoSelect(row.getItem());
                }
            });
            return row ;
        });

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
        lip.setCellValueFactory(new PropertyValueFactory<>("lip"));
        carb.setCellValueFactory(new PropertyValueFactory<>("carb"));
        prot.setCellValueFactory(new PropertyValueFactory<>("prot"));

        for(Alimento a : sistema.getListaAlimentos()) {
            lista.getItems().add(a);
        }
    }

    /**
     * Cria uma nova stage com o "QuantidadePopUp.fxml"
     * @param alimento - Alimento/Row selecionado
     */
    private void alimentoSelect(Alimento alimento) {
        try {
            Stage popup = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuantidadePopUp.fxml"));
            Pane parent = loader.load();

            //Para passar o alimento selecionado para o novo scene
            QuantidadePopUp quantidadePopUp = loader.getController();
            quantidadePopUp.setSelectedAlimento(alimento);
            quantidadePopUp.setPrimals(refPane, refPaneController, indexController);

            Scene cena = new Scene(parent);

            popup.initStyle(StageStyle.UNDECORATED);
            popup.setTitle("Quantidade");
            popup.setScene(cena);
            popup.show();
        }catch (Exception e) {
            System.out.println("ListaAlimentos - alimentosSelect() : "+ e.toString());
        }
    }

    /**
     * Metodo para designar todos os controladores e paineis necessarios para este controlador.
     * Comunica????o entre controladores
     * @param p - refPane
     * @param r - refPaneController
     * @param i - indexController
     */
    public void setPrimals(Pane p, RefPane r, Index i) {
        refPane = p;
        refPaneController = r;
        indexController = i;
    }

    public void procuraAlimento() {
        String alimento = searchBar.getText();

        lista.getItems().clear();

        lista.setRowFactory(tv -> {
            TableRow<Alimento> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    alimentoSelect(row.getItem());
                }
            });
            return row ;
        });

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
        lip.setCellValueFactory(new PropertyValueFactory<>("lip"));
        carb.setCellValueFactory(new PropertyValueFactory<>("carb"));
        prot.setCellValueFactory(new PropertyValueFactory<>("prot"));

        for(Alimento a : sistema.searchAlim(alimento)) {
            lista.getItems().add(a);
        }

        sistema.searchAlim(alimento);
    }

    public void close() {
        Stage stage = (Stage)lista.getScene().getWindow();
        stage.close();
    }
}
