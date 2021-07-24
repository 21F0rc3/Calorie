package GraphicInterface;

import Classes.Alimento;
import Classes.Counter;
import Classes.Refeicao;
import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditRefAlimPopUp {
    private final Sistema sistema = Sistema.getInstance();

    private Alimento selectedAlimento;
    private Pane refPane;
    private RefPane refPaneController;
    private Index indexController;
    private AlimentosRef alimentosRefController;

    @FXML
    public Label label;

    /**
     * Metodo para designar todos os controladores e paineis necessarios para este controlador.
     * Comunicação entre controladores
     * @param i - indexController
     * @param r - refPaneController
     * @param p - refPane
     * @param a - alimentosRefController
     */
    public void setPrimals(Index i, RefPane r, Pane p, AlimentosRef a) {
        indexController = i;
        refPaneController = r;
        refPane = p;
        alimentosRefController = a;
    }

    /**
     * Metodo set do alimento selecionado
     * @param a - Alimento selecionado
     */
    public void setSelectedAlimento(Alimento a) {
        this.selectedAlimento = a;
    }

    /**
     * Handler para o evento de clicar no botão "Sim"
     *
     * Elimina o alimento da refeição no sistema, e atualiza as labels com os novos valores dos counters
     */
    public void yes() {
        Refeicao ref = sistema.searchRefeicao(Integer.parseInt(refPane.getChildren().get(0).getId()));
        ref.removeAlimento(selectedAlimento);

        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();

        Counter c = sistema.searchRefeicao(Integer.parseInt(refPane.getChildren().get(0).getId())).counter;
        Counter ct = sistema.getCounterTotal();

        //Arredonda para duas casas o counter da ref
        String cLip = Float.toString(Math.round(c.getLip() * 100.0f) / 100.0f);
        String cCarb = Float.toString(Math.round(c.getCarb() * 100.0f) / 100.0f);
        String cProt = Float.toString(Math.round(c.getProt() * 100.0f) / 100.0f);

        //Arredonda para duas casas o counter total
        String ctLip = Float.toString(Math.round(ct.getLip() * 100.0f) / 100.0f);
        String ctCarb = Float.toString(Math.round(ct.getCarb() * 100.0f) / 100.0f);
        String ctProt = Float.toString(Math.round(ct.getProt() * 100.0f) / 100.0f);

        refPaneController.updateLabels(Integer.toString(c.getCal()), cLip, cCarb, cProt);
        indexController.getInicioController().updateCounterTotalLabels(Integer.toString(ct.getCal()), ctLip,ctCarb,ctProt);

        //Atualizar a lista
        alimentosRefController.lista.getItems().remove(selectedAlimento);
    }

    /**
     * Hanaler para o evento de clicar no botão "Não"
     *
     * Fecha a stage, apenas
     */
    public void no() {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo set para definir o texto da label
     */
    public void setLabelText() {
        label.setText("Pretendes eleminar "+selectedAlimento.getNome()+"("+selectedAlimento.getQty()+") ?");
    }
}
