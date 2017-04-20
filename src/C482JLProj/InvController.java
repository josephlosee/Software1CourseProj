package C482JLProj;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    private Stage secondaryStage;

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
    public void invPartSearchButton(){
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
            addPartPane = FXMLLoader.load(getClass().getResource("partView.fxml"));
        }catch (IOException ioExc){
            ioExc.printStackTrace();
        }
        Node source = (Node) e.getSource();
        Window window = source.getScene().getWindow();
        window.hide();

        secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(addPartPane));

        secondaryStage.showAndWait();

        Stage currentStage = (Stage)window;
        currentStage.show();

        partTableSelectWorkaround();

    }

    //Workaround for new items not being selectable.
    private void partTableSelectWorkaround(){
        partsTableView.getSortOrder().setAll(partsTableView.getColumns());
        partsTableView.getSortOrder().clear();
    }

    private void prodTableSelectWorkAround(){
        prodTableView.getSortOrder().setAll(prodTableView.getColumns());
        prodTableView.getSortOrder().clear();
    }

    @FXML public void modPartClick(ActionEvent e){

        int index = partsTableView.getSelectionModel().getSelectedIndex();

        if (index>=0){
            Node source = (Node) e.getSource();
            Window window = source.getScene().getWindow();
            window.hide();
            Main.getInventory().updatePart(index);

            Stage currentStage = (Stage)window;
            currentStage.show();

            partTableSelectWorkaround();
        }

        /*
        //Setup to show the window
        Parent modPartPane = new GridPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        try {
            //Setup the parent
            modPartPane = (Parent)loader.load();
            //Get the reference to the controller class so
            PartController controller =loader.<PartController>getController();
            //We can populate the view with the part to be modified.
            int index = partsTableView.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                Part partToModify= partObservList.get(index);

                controller.modPart(index, partToModify);

                //Resume setting up
                secondaryStage = new Stage();
                secondaryStage.setScene(new Scene(modPartPane));
                //Show and Wait to take away input from the main window
                secondaryStage.showAndWait();
            }
        }catch (IOException ioExc){
            ioExc.printStackTrace();
        }*/

    }

    @FXML public void delPartButtonClick(ActionEvent e){
        int index = partsTableView.getSelectionModel().getSelectedIndex();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Delete the "+partObservList.get(index).getName()+"?");

        //
        //Using lambdas to get used to them.
        confirmation.showAndWait()
                .filter(response->response==ButtonType.OK)
                .ifPresent(response->partObservList.remove(index));

        partTableSelectWorkaround();
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
        Parent addPartPane = new GridPane();

        try {
            addPartPane = FXMLLoader.load(getClass().getResource("productView.fxml"));
        }catch (IOException ioExc){
            ioExc.printStackTrace();
        }
        secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(addPartPane));

        Node source = (Node) e.getSource();
        Window window = source.getScene().getWindow();
        window.hide();
        Stage currentStage = (Stage)window;

        secondaryStage.showAndWait();


        currentStage.show();

        prodTableSelectWorkAround();
    }

    /**
     * Handling for modify product button.
     * @param e
     */
    @FXML public void modProdClick(ActionEvent e){
        int index = prodTableView.getSelectionModel().getSelectedIndex();

        if (index>-1) {
            Node source = (Node) e.getSource();
            Window window = source.getScene().getWindow();
            window.hide();

            Main.getInventory().updateProduct(index);

            Stage currentStage = (Stage) window;
            currentStage.show();

            prodTableSelectWorkAround();
        }
    }

    /**
     * Handling for product delete button
     * @param e
     */
    @FXML public void delProdButtonClick(ActionEvent e){
        int index = prodTableView.getSelectionModel().getSelectedIndex();
        try {
            Main.getInventory().removeProduct(index);
        }
        catch (Exception e1){
            Alert error = new Alert(Alert.AlertType.ERROR, e1.getMessage());
            error.showAndWait();
        }
        prodTableSelectWorkAround();
    }

    /**
     * Exit via the button in the lower right
     * @param e
     */
    @FXML public void exitButtonClicked(ActionEvent e){
        Alert exitConfirm = new Alert(Alert.AlertType.CONFIRMATION, "Exit the program?");
        exitConfirm.showAndWait()
                .filter(response->response==ButtonType.OK)
                .ifPresent(response-> Platform.exit());
    }

}
