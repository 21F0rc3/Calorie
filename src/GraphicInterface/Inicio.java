package GraphicInterface;

import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Inicio {
    private final Sistema sistema = Sistema.getInstance();

    @FXML public Label cal;
    @FXML public Label lip;
    @FXML public Label prot;
    @FXML public Label carb;

    @FXML public ProgressBar calBar;
    @FXML public ProgressBar lipBar;
    @FXML public ProgressBar carbBar;
    @FXML public ProgressBar protBar;

    @FXML private Pane inicioPane;

    private Index indexController;

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

    public void setPrimals(Index i) {
        this.indexController = i;
    }

    public void setWidth(double newVal) {
        inicioPane.setPrefWidth(newVal*0.9);
    }

    public void setHeight(double newVal) {
        inicioPane.setPrefHeight(newVal);
    }
}
