package C482JLProj;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    protected static List<Part> partsList;
    protected static List<Product> productList;
    private static Inventory testInv = new Inventory();
    private Stage addPartStage = new Stage();
    Parent addPartScene;
    private Stage modPartStage;
    private Stage addProdStage;
    private Stage modProdStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        partsList = new ArrayList<>();
        productList = new ArrayList<>();

        Parent root = FXMLLoader.load(getClass().getResource("InvMgmt.fxml"));
        //addPartScene = FXMLLoader.load(getClass().getResource("AddPart.fxml"));

        //addPartStage = new Stage();
        //addPartStage.setScene(new Scene(addPartScene));
        //addPartStage.show();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Inhouse privTest = new Inhouse();
        privTest.setPartID(01);
        launch(args);
    }

    public static Inventory getInventory(){
        return testInv;
    }

    @FXML public void addPartButtonClick(ActionEvent e){

        Parent test = new GridPane();

        try {
             test = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        }catch (IOException ioExc){
            ioExc.printStackTrace();
        }

        addPartStage.setScene(new Scene(test));

        addPartStage.show();
    }

    @FXML public void invPartSearchButton(ActionEvent e){

    }

    public Stage getAddPartStage(){
        return addPartStage;
    }

    public Stage getModPartStage(){
        return modPartStage;
    }

    public Stage getAddProdStage(){
        return addProdStage;
    }

    public Stage getModProdStage(){
        return modProdStage;
    }
}
