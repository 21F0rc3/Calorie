package GraphicInterface;

import Classes.Alimento;
import Classes.Refeicao;
import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.text.html.ListView;

public class EditRefPopUp {
    private final Sistema sistema = Sistema.getInstance();

    private Index indexController;

    @FXML private TableView<Refeicao> lista;
    @FXML private TableColumn<Refeicao,String> nome;

    public void populateLista() {
        lista.setRowFactory(tv -> {
            TableRow<Refeicao> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    refSelect(row.getItem());
                }
            });
            return row ;
        });

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        for(Refeicao r : sistema.getRefeicoes()) {
            lista.getItems().add(r);
        }
    }

    public void refSelect(Refeicao ref) {
        sistema.removeRefeicao(ref.getId());

        ref.getRefPaneController().delete();

        lista.getItems().remove(ref);
    }

    public void setPrimals(Index i) {
        this.indexController = i;
    }

    public void close() {
        Stage stage = (Stage)lista.getScene().getWindow();
        stage.close();
    }
}
