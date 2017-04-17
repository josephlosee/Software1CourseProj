package C482JLProj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/16/2017.
 */
public class InvController {
    @FXML private TableView partsTableView, prodTableView;
    //Unused: @FXML private TableColumn<Part, String> partsTVColName, partsTVColID, partsTVColInvLev, partsTVColPrice;
    @FXML private TextField invPartSearchField, invProdSearchField;
    @FXML private ObservableList<Part> partObservList;
    @FXML private ObservableList<Product> prodsObservList;
    private Stage addPartStage;
    private Stage modPartStage;

    @FXML private void initialize(){
        //partsTableView.getColumns();
        partObservList=FXCollections.observableList(Main.getInventory().getParts());
        partsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        partsTableView.setItems(partObservList);

        prodsObservList =FXCollections.observableList(Main.getInventory().getProductList());
        prodTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        prodTableView.setItems(prodsObservList);

        partsTableView.refresh();
        prodTableView.refresh();
    }
    @FXML
    public void invPartSearchButton(ActionEvent e){
        ObservableList<Part> filtered = FXCollections.observableArrayList();
        for(Part p: partObservList){
            if (p.getName().contains(invPartSearchField.getText())){
                filtered.add(p);
            }
        }
        partsTableView.setItems(filtered);
        partsTableView.refresh();
    }

    @FXML public void addPartButtonClick(ActionEvent e){
        Parent addPartPane = new GridPane();

        try {
            addPartPane = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        }catch (IOException ioExc){
            ioExc.printStackTrace();
        }
        addPartStage = new Stage();
        addPartStage.setScene(new Scene(addPartPane));

        addPartStage.showAndWait();

        partsTableView.refresh();
    }

    @FXML public void modPartClick(ActionEvent e){

        //Setup to show the window
        Parent modPartPane = new GridPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModPart.fxml"));
        try {
            //Setup the parent
            modPartPane = (Parent)loader.load();
            //Get the reference to the controller class so
            ModPartController controller =loader.<ModPartController>getController();
            //We can populate the view with the part to be modified.
            int index = partsTableView.getSelectionModel().getSelectedIndex();
            controller.modPart(index, partObservList.get(index));
        }catch (IOException ioExc){
            ioExc.printStackTrace();
        }

        //Resume setting up
        modPartStage = new Stage();
        modPartStage.setScene(new Scene(modPartPane));

        //Show and Wait to take away input from the main window
        modPartStage.showAndWait();
        partsTableView.refresh();
    }

    //TODO: Add logic to the data model in Inventory to prevent parts from being deleted if assigned to a product. Wishing for SQL
    @FXML public void delPartButtonClick(ActionEvent e){
        int index = partsTableView.getSelectionModel().getSelectedIndex();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Delete the "+partObservList.get(index).getName()+"?");

        //Using lambdas to get used to them.
        confirmation.showAndWait()
                .filter(response->response==ButtonType.OK)
                .ifPresent(response->partObservList.remove(index));

        partsTableView.refresh();
    }

    @FXML public void invProdSearchButton(ActionEvent e){
        ObservableList<Product> filtered = FXCollections.observableArrayList();
        for(Product p: prodsObservList){
            if (p.getName().contains(invProdSearchField.getText())){
                filtered.add(p);
            }
        }
        prodTableView.setItems(filtered);
        prodTableView.refresh();
    }

    @FXML public void addProdButtonClick(ActionEvent e){

    }

    @FXML public void modProdClick(ActionEvent e){

    }

    @FXML public void delProdButtonClick(ActionEvent e){

    }

}
