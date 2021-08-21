package GraphicInterface;

import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Perfil {
    private final Sistema sistema = Sistema.getInstance();

    @FXML public TextField altura;
    @FXML public TextField peso;

    @FXML private TextField lipPerc;
    @FXML private TextField protPerc;
    @FXML private TextField carbPerc;

    @FXML private PieChart pieChart;
    @FXML private ProgressBar progressBar;

    @FXML private Pane perfilPane;

    private Index indexController;

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
        indexController.getInicioController().calBar.setProgress(Float.parseFloat(indexController.getInicioController().cal.getText().split("/")[0]) / dieta[0]);
        indexController.getInicioController().lipBar.setProgress(Float.parseFloat(indexController.getInicioController().lip.getText().split("/")[0]) / dieta[1]);
        indexController.getInicioController().carbBar.setProgress(Float.parseFloat(indexController.getInicioController().carb.getText().split("/")[0]) / dieta[2]);
        indexController.getInicioController().protBar.setProgress(Float.parseFloat(indexController.getInicioController().prot.getText().split("/")[0]) / dieta[3]);

        Inicio inicioController = indexController.getInicioController();

        indexController.getInicioController().updateCounterTotalLabels(inicioController.cal.getText().split("/")[0], inicioController.lip.getText().split("/")[0], inicioController.carb.getText().split("/")[0], inicioController.prot.getText().split("/")[0]);

        pieChart.getData().addAll(lipidos,carbohidratos,proteinas);
    }

    public void updateAltura() {
        float a = Float.parseFloat(altura.getText());

        //Atualiza o peso do utilizador
        sistema.getUtilizador().setAltura(a);
    }

    public void setPrimals(Index i) {
        this.indexController = i;
    }

    public void setWidth(double newVal) {
        perfilPane.setPrefWidth(newVal-171);
        pieChart.setPrefWidth(perfilPane.getWidth()-325);
        progressBar.setPrefWidth(perfilPane.getWidth()-325);
    }

    public void setHeight(double newVal) {
        perfilPane.setPrefHeight(newVal);
        pieChart.setPrefHeight(perfilPane.getHeight()-200);
    }

    public void updateDiet() {
        Float l = Float.parseFloat(lipPerc.getText())/100;
        Float c = Float.parseFloat(carbPerc.getText())/100;
        Float p = Float.parseFloat(protPerc.getText())/100;

        sistema.getUtilizador().setLipPerc(l);
        sistema.getUtilizador().setCarbPerc(c);
        sistema.getUtilizador().setProtPerc(p);

        updatePeso();
    }
}
