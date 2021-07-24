package GraphicInterface;

import Classes.Alimento;
import Classes.Counter;
import Classes.Refeicao;
import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RefPane {
    private final Sistema sistema = Sistema.getInstance();

    private Index indexController;

    @FXML private Pane refPane;
    @FXML private Label title;

    @FXML private Label cal;
    @FXML private Label lip;
    @FXML private Label carb;
    @FXML private Label prot;

    /**
     * Apresenta a lista de alimentos na base de dados do sistema.
     *
     * Cria uma nova stage "ListaAlimentos.fxml"
     *
     */
    public void showLista(MouseEvent click) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Adicionar alimento");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListaAlimentos.fxml"));
            Pane parent = loader.load();

            ListaAlimentos listaAlimentos = loader.getController();
            listaAlimentos.setPrimals(refPane,this,indexController);
            listaAlimentos.populateLista();

            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.show();
        }catch (Exception e) {
            System.out.println("RefPane - showLista() : "+e.toString());
        }
    }

    /**
     * Coloca no titulo do refPane o nome da refeição associada
     * @param nome - Nome da refeição
     */
    public void setName(String nome) {
        title.setText(nome);
    }

    /**
     * Coloca o no id do refPane o id da refeição associada
     * @param id - Id da refeição
     */
    public void setId(int id) {
        refPane.getChildrenUnmodifiable().get(0).setId(id+"");
    }

    /**
     * Atualiza os valores das labels de macros da refeição
     * @param c - Calorias
     * @param l - Lipidos
     * @param ca - Carbohidratos
     * @param p - Proteinas
     */
    public void updateLabels(String c, String l, String ca, String p) {
        cal.setText(c);
        lip.setText(l);
        carb.setText(ca);
        prot.setText(p);
    }

    /**
     * Metodo para designar todos os controladores e paineis necessarios para este controlador.
     * Comunicação entre controladores
     * @param i - indexController
     */
    public void setPrimals(Index i) {
        this.indexController = i;
    }

    /**
     * Handler do evento de clicar no botão clear
     *
     * Chama a função clear da refeição associada, que elimina todas as refeições. Atualiza as labels da refeição e do menu principal(counter total).
     *
     * @param click
     */
    public void clear(MouseEvent click) {
        int id = Integer.parseInt(refPane.getChildrenUnmodifiable().get(0).getId());

        sistema.searchRefeicao(id).clearRef();

        Counter ct = sistema.getCounterTotal();

        updateLabels("0","0","0","0");

        //Arredonda para duas casas o counter total
        String ctLip = Float.toString(Math.round(ct.getLip() * 100.0f) / 100.0f);
        String ctCarb = Float.toString(Math.round(ct.getCarb() * 100.0f) / 100.0f);
        String ctProt = Float.toString(Math.round(ct.getProt() * 100.0f) / 100.0f);

        indexController.getInicioController().updateCounterTotalLabels(Integer.toString(ct.getCal()), ctLip,ctCarb,ctProt);
    }

    /**
     * Handler do evento de clicar no botão Edit
     *
     * Cria uma nova stage "AlimentosRef.fxml", com todos os alimentos
     * da refeição
     *
     * @param click
     */
    public void editAlim(MouseEvent click) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Editar alimentos");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AlimentosRef.fxml"));
            Pane parent = loader.load();

            AlimentosRef listaAlimentos = loader.getController();
            listaAlimentos.setPrimals(refPane,this,indexController);
            listaAlimentos.populateLista();

            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.show();

        }catch (Exception e) {
            System.out.println("RefPane - editAlim() : "+e.toString());
        }
    }

    public void delete() {
        indexController.getRefPaneController().delete(refPane);
    }
}
