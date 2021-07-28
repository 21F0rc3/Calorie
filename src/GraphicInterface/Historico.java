package GraphicInterface;

import Classes.*;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Historico {
    private final Sistema sistema = Sistema.getInstance();

    private Index indexController;

    @FXML private AnchorPane historicoPane;
    @FXML private AnchorPane calGraph;

    @FXML private DatePicker from;
    @FXML private DatePicker to;

    private AreaChart<String, Number> areaChart;

    public void setPrimals(Index i) {
        this.indexController = i;
    }

    public void updateCalGraph() {
        LocalDate f = from.getValue();
        LocalDate t = to.getValue();

        Date start = new Date(f.getDayOfMonth(),f.getMonthValue(),f.getYear());
        Date end = new Date(t.getDayOfMonth(),t.getMonthValue(),t.getYear());

        if(from.getValue()!=null && to.getValue()!=null) {
            CategoryAxis xAxis = new CategoryAxis();

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Calorias");

            areaChart = new AreaChart<>(xAxis, yAxis);

            XYChart.Series serie = new XYChart.Series();
            serie.setName("Calories");

            for(Date d = start; d.before(end); d=Date.nextDate(d)) {
                int calorias=0;

                try{
                    File file = new File("C:\\Users\\Gabri\\OneDrive\\Ambiente de Trabalho\\Calorie\\src\\Assets\\Data\\"+d.toString()+".txt");
                    if(!file.exists()) {
                        throw new FileNotFoundException();
                    }
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);

                    int noRef = Integer.parseInt(br.readLine());
                    for(int i=0; i<noRef; i++) {
                        String[] refTks = br.readLine().split(",");
                        int noAlim = Integer.parseInt(refTks[1]);
                        for(int j=0; j<noAlim; j++) {
                            String[] alimTks = br.readLine().split(",");
                            Alimento alimento = new Alimento(sistema.searchAlim(Integer.parseInt(alimTks[0])), Integer.parseInt(alimTks[1]));
                            calorias+=alimento.getCal();
                        }
                    }
                }catch (FileNotFoundException fe) {
                    System.out.println("Historico - updateCalGraph() : "+fe.toString());
                }catch (Exception e) {
                    System.out.println("Sistema - loadData() : "+e.toString());
                }

                serie.getData().add(new XYChart.Data(d.toString(),calorias));
            }
            areaChart.getData().add(serie);

            areaChart.setPrefWidth(calGraph.getWidth());
            areaChart.setPrefHeight(calGraph.getHeight());

            calGraph.getChildren().clear();
            calGraph.getChildren().add(areaChart);
        }
    }

    public void setWidth(double newVal) {
        historicoPane.setPrefWidth(newVal-171);
        areaChart.setPrefWidth(calGraph.getWidth());
    }

    public void setHeight(double newVal) {
        historicoPane.setPrefHeight(newVal);
        areaChart.setPrefHeight(calGraph.getHeight());
    }
}
