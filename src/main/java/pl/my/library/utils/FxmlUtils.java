package pl.my.library.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Admin on 2017-04-09.
 */
public class FxmlUtils {

    public static Pane fxmlLoader(String fxmlPath){

        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());
        try {
            return loader.load();
        } catch (Exception e) {
            DialogsUtils.errorDialog(e.getMessage());
           // e.printStackTrace();
        }
        return null;

    }

    public static FXMLLoader getLoader(String fxmlPath){

        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());
            return loader;

    }

    public static ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle("bundles.messages");
    }
}
