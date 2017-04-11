package C482JLProj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    protected static List<Part> partsList;
    protected static List<Product> productList;
    @Override
    public void start(Stage primaryStage) throws Exception{
        partsList = new ArrayList<>();
        productList = new ArrayList<>();

        Parent root = FXMLLoader.load(getClass().getResource("InvMgmt.fxml"));
        Parent addPartScene = FXMLLoader.load(getClass().getResource("AddPart.fxml"));

        Stage addPartStage = new Stage();
        addPartStage.setScene(new Scene(addPartScene));
        addPartStage.show();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Inhouse privTest = new Inhouse();
        privTest.setPartID(01);


        launch(args);
    }
}
