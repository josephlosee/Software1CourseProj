package C482JLProj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Inventory testInv = new Inventory();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("inventoryView.fxml"));
        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(new Scene(root, 960, 480));
        primaryStage.show();
        //partsTableView = root.node;
    }

    public static void main(String[] args) {
        Inhouse privTest = new Inhouse();
        privTest.setPartID(01);
        launch(args);
    }

    public static Inventory getInventory() {
        return testInv;
    }


}
