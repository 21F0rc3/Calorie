package GraphicInterface;

import Classes.Sistema;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Perfil {
    private final Sistema sistema = Sistema.getInstance();

    @FXML public TextField altura;
    @FXML public TextField peso;

    @FXML private PieChart pieChart;

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
        indexController.getInicioController().calBar.setProgress(Float.parseFloat(indexController.getInicioController().cal.getText()) / dieta[0]);
        indexController.getInicioController().lipBar.setProgress(Float.parseFloat(indexController.getInicioController().lip.getText()) / dieta[1]);
        indexController.getInicioController().carbBar.setProgress(Float.parseFloat(indexController.getInicioController().carb.getText()) / dieta[2]);
        indexController.getInicioController().protBar.setProgress(Float.parseFloat(indexController.getInicioController().prot.getText()) / dieta[3]);

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
    }

    public void setHeight(double newVal) {
        perfilPane.setPrefHeight(newVal);
    }
}
