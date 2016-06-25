package dictionary.muktobangla;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(this.getClass().getResource("view/MainGUI.fxml"));
        primaryStage.setTitle("Mukto Bangla Ovidhan");
        Scene scene = new Scene(root, 540, 585);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(this.getClass().getResource("view/bnFont.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.getIcons().add( new Image(this.getClass().getResourceAsStream("/images/mbo_icon.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}