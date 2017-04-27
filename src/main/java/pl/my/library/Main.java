package pl.my.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.my.library.utils.FxmlUtils;
import pl.my.library.database.dbutils.DbManager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Admin on 2017-04-06.
 */
public class Main extends Application {

    public static final String FXML_BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

    public static void main(String[] args) {
     launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Locale.setDefault(new Locale("en"));

        Pane borderPane = FxmlUtils.fxmlLoader(FXML_BORDER_PANE_MAIN_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.show();

        DbManager.initDatabase();

    }



}
