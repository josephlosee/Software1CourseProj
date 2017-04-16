package C482JLProj;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @FXML private TableView<Part> partsTV;
    @FXML private TableColumn<Part, String> partsTVColName, partsTVColID, partsTVColInvLev, partsTVColPrice;
    protected static List<Part> partsList = new ArrayList<>();
    protected static List<Product> productList = new ArrayList<>();
    private static Inventory testInv = new Inventory();
    private Stage addPartStage = new Stage();
    Parent addPartScene;
    private Stage modPartStage;
    private Stage addProdStage;
    private Stage modProdStage;
    private ObservableList<Part> partsObservList = FXCollections.observableArrayList(Main.getPartsList());

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("InvMgmt.fxml"));
        //partsObservList
        //emailCol.setCellValueFactory(        new PropertyValueFactory<Person, String>("email"));
        //partsObservList.get(0);
        //partsObservList.get(0);
        //partsTV.getColumns();
        //partsTVColName.setCellValueFactory();
        //addPartScene = FXMLLoader.load(getClass().getResource("AddPart.fxml"));

        //addPartStage = new Stage();
        //addPartStage.setScene(new Scene(addPartScene));
        //addPartStage.show();

        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(new Scene(root, 800, 275));
        partsTV.getColumns();
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

    public static List<Part> getPartsList() {
        return partsList;
    }

    public static List<Product> getProductList() {
        return productList;
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

    public static void addPart(Part newPart){
        partsList.add(newPart);
    }

    public void addProduct(Product newProd){
        productList.add(newProd);
    }
}
