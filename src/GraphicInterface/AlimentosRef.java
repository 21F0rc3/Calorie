package GraphicInterface;

import Classes.Alimento;
import Classes.Refeicao;
import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class AlimentosRef {
    private final Sistema sistema = Sistema.getInstance();

    private Pane refPane;
    private RefPane refPaneController;
    private Index indexController;

    @FXML public TableView<Alimento> lista;

    @FXML private TableColumn<Alimento,String> nome;
    @FXML private TableColumn<Alimento,Integer> qty;
    @FXML private TableColumn<Alimento,Integer> cal;
    @FXML private TableColumn<Alimento,Float> lip;
    @FXML private TableColumn<Alimento,Float> carb;
    @FXML private TableColumn<Alimento,Float> prot;

    /**
     * Populaciona a lista com todos os alimentos da refeição selecionada
     *
     * Implementa uma event handler para cada row(Alimento) clicado
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
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
        lip.setCellValueFactory(new PropertyValueFactory<>("lip"));
        carb.setCellValueFactory(new PropertyValueFactory<>("carb"));
        prot.setCellValueFactory(new PropertyValueFactory<>("prot"));

        int id = Integer.parseInt(refPane.getChildren().get(0).getId());

        Refeicao ref = sistema.searchRefeicao(id);

        for(Alimento a : ref.alimentos) {
            lista.getItems().add(a);
        }
    }

    /**
     * Metodo para designar todos os controladores e paineis necessarios para este controlador.
     * Comunicação entre controladores
     * @param p - refPane
     * @param r - refPaneController
     * @param i - indexController
     */
    public void setPrimals(Pane p, RefPane r, Index i) {
        this.refPane = p;
        this.refPaneController = r;
        this.indexController = i;
    }

    /**
     * Handler paara o evento de clicar numa row/alimento da lista
     *
     * Cria um novo pane "EditRefAlimPopUp.fxml" que abre uma stage que pergunta se queremos eleminar o alimento
     * selecionado, da respetiva refeição
     *
     * @param alimento - Alimento selecionado
     */
    private void alimentoSelect(Alimento alimento) {
        try {
            Stage popup = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditRefAlimPopUp.fxml"));
            Pane parent = loader.load();

            //Para passar o alimento selecionado para o novo scene
            EditRefAlimPopUp editRefAlimPopUp = loader.getController();
            editRefAlimPopUp.setPrimals(indexController, refPaneController, refPane,this);
            editRefAlimPopUp.setSelectedAlimento(alimento);
            editRefAlimPopUp.setLabelText();

            Scene cena = new Scene(parent);

            popup.setTitle("Quantidade");
            popup.setScene(cena);
            popup.show();
        }catch (Exception e) {
            System.out.println("AlimentosRef - alimentoSelect() : "+ e.toString());
        }
    }
}
