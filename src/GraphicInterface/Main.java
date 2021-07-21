package GraphicInterface;

import Classes.Sistema;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        sistema.loadData(indexController);

        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                sistema.saveData(indexController);
            }
        });
    }
}
