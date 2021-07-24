package GraphicInterface;

import Classes.Alimento;
import Classes.Counter;
import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Ref;

public class QuantidadePopUp {
    private final Sistema sistema = Sistema.getInstance();

    private Alimento selectedAlimento;
    private Pane refPane;
    private RefPane refPaneController;
    private Index indexController;

    @FXML private Label button;
    @FXML private TextField quantidade;

    /**
     * Handler para clicar no botão "Adicionar"
     *
     * Adiciona o alimento com a quantidade especificada a respetiva refeição no sistema.
     * Atualiza as labels com os novos valores nos counters do sistema.
     *
     * @param click
     */
    public void addAlim(MouseEvent click) {
            int qty = Integer.parseInt(quantidade.getText());
            System.out.println(qty);

            sistema.addAlimento(Integer.parseInt(refPane.getChildren().get(0).getId()), selectedAlimento.getId(), qty);

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

            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
    }

    /**
     * Metodo set para o alimento selecionado
     * @param a - Alimento selecionado
     */
    public void setSelectedAlimento(Alimento a) {
        this.selectedAlimento = a;
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
}
