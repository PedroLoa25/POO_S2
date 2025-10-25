package pe.edu.upeu.sysventas.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ViewNavigator {

    private final ApplicationContext context;

    public ViewNavigator(ApplicationContext context) {
        this.context = context;
    }

    public Parent load(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(context::getBean);
        try {
            return loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo cargar la vista: " + fxmlPath, e);
        }
    }

    public Parent load(ViewId view) {
        return load(view.getFxmlPath());
    }

    public Scene createScene(String fxmlPath) {
        return new Scene(load(fxmlPath));
    }

    public Scene createScene(ViewId view) {
        return createScene(view.getFxmlPath());
    }

    public void setScene(String fxmlPath, Stage stage) {
        Scene scene = stage.getScene();
        if (scene == null) {
            stage.setScene(createScene(fxmlPath));
        } else {
            scene.setRoot(load(fxmlPath));
        }
    }

    public void setScene(ViewId view, Stage stage) {
        setScene(view.getFxmlPath(), stage);
    }

    public Tab createTab(String titulo, String fxmlPath, boolean wrapWithScroll) {
        Parent content = load(fxmlPath);
        if (wrapWithScroll) {
            ScrollPane scrollPane = new ScrollPane(content);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            content = scrollPane;
        }
        return new Tab(titulo, content);
    }

    /*@AllArgsConstructor
    @Getter*/
    public enum ViewId {
        LOGIN("/view/login.fxml"),
        MAIN_GUI("/view/maingui.fxml");

        private final String fxmlPath;

        ViewId(String fxmlPath) {
            this.fxmlPath = fxmlPath;
        }

        public String getFxmlPath() {
            return fxmlPath;
        }
    }
}
