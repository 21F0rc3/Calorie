package GraphicInterface;

import Classes.Sistema;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    private static Sistema sistema = Sistema.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Calorie");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Index.fxml"));
        Parent parent = loader.load();

        //Para passar todos os controladores e scenes necessarios a nova stage
        Index indexController = loader.getController();

        indexController.Perfil();
        indexController.Refeicoes();
        indexController.Inicio();

        //sistema.loadData(indexController);

        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.show();

        stage.setMinWidth(971);
        stage.setMinHeight(600);

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            try {
                indexController.getRefPaneController().setWidth(stage.getWidth());
                indexController.getInicioController().setWidth(stage.getWidth());
                indexController.getPerfilController().setWidth(stage.getWidth());
                indexController.getHistoricoController().setWidth(stage.getWidth());
            }catch (NullPointerException nullPointerException) {
                
            }
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            try {
                indexController.getRefPaneController().setHeight(stage.getHeight());
                indexController.getInicioController().setHeight(stage.getHeight());
                indexController.getPerfilController().setHeight(stage.getHeight());
                indexController.getHistoricoController().setHeight(stage.getHeight());
            }catch (NullPointerException nullPointerException) {

            }
        });

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                //sistema.saveData(indexController);
            }
        });
    }
}
