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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Index {
    private final Sistema sistema = Sistema.getInstance();

    @FXML private GridPane refeicoes;

    @FXML private Label cal;
    @FXML private Label lip;
    @FXML private Label prot;
    @FXML private Label carb;

    @FXML public TextField altura;
    @FXML public TextField peso;

    @FXML private PieChart pieChart;

    @FXML private ProgressBar calBar;
    @FXML private ProgressBar lipBar;
    @FXML private ProgressBar carbBar;
    @FXML private ProgressBar protBar;

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
            popup.setTitle("Adicionar refeição");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdicionaRefPopUp.fxml"));
            Pane parent = loader.load();

            //Para passar todos os controladores e scenes necessarios a nova stage
            AdicionaRefPopUp adicionaRefPopUp = loader.getController();

            adicionaRefPopUp.setPrimals(refeicoes, this);

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
            popup.setTitle("Edita refeição");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditRefPopUp.fxml"));
            Pane parent = loader.load();

            //Para passar todos os controladores e scenes necessarios a nova stage
            EditRefPopUp editRefPopUp = loader.getController();
            editRefPopUp.populateLista();
            editRefPopUp.setPrimals(this);

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
            refPane.setPrimals(this);

            sistema.searchRefeicao(id).setRefPaneController(refPane);

            //Adiciona novo refPane ao painel de refeiçoes do index
            int nrRef = refeicoes.getChildren().size();
            this.refeicoes.add(parent,(nrRef)%2,(nrRef)/2);

            return id;
        }catch (Exception e) {
            System.out.println("Index - adicionaRefeicao() : "+e.toString());
            return -1;
        }
    }

    /**
     * Atualiza os valores das labels da IndexRefPane de acordo com os novos valores do counter total do sistema
     * Atualiza as progress bars
     * @param c - Calorias
     * @param l - Lipidos
     * @param ca - Carbohidratos
     * @param p - Proteinas
     */
    public void updateCounterTotalLabels(String c, String l, String ca, String p) {
        cal.setText(c);
        lip.setText(l);
        carb.setText(ca);
        prot.setText(p);

        float[] dieta = sistema.getUtilizador().diet();

        //Atualiza os graficos na aba Inicio
        calBar.setProgress(Float.parseFloat(cal.getText()) / dieta[0]);
        lipBar.setProgress(Float.parseFloat(lip.getText()) / dieta[1]);
        carbBar.setProgress(Float.parseFloat(carb.getText()) / dieta[2]);
        protBar.setProgress(Float.parseFloat(prot.getText()) / dieta[3]);
    }

    public void updatePeso() {
        float p = Float.parseFloat(peso.getText());

        //Atualiza o peso do utilizador
        sistema.getUtilizador().setPeso(p);

        float[] dieta = sistema.getUtilizador().diet();

        //Cria o grafico na aba perfil
        pieChart.getData().clear();

        PieChart.Data lipidos = new PieChart.Data("Lipidos", dieta[1]);
        PieChart.Data carbohidratos = new PieChart.Data("Carbohidratos", dieta[2]);
        PieChart.Data proteinas = new PieChart.Data("Proteinas", dieta[3]);

        //Atualiza os graficos na aba Inicio
        calBar.setProgress(Float.parseFloat(cal.getText()) / dieta[0]);
        lipBar.setProgress(Float.parseFloat(lip.getText()) / dieta[1]);
        carbBar.setProgress(Float.parseFloat(carb.getText()) / dieta[2]);
        protBar.setProgress(Float.parseFloat(prot.getText()) / dieta[3]);

        pieChart.getData().addAll(lipidos,carbohidratos,proteinas);
    }

    public void updateAltura() {
        float a = Float.parseFloat(altura.getText());

        //Atualiza o peso do utilizador
        sistema.getUtilizador().setAltura(a);
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
}
