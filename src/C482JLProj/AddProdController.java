package C482JLProj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Created by Joe on 4/16/2017.
 */
public class AddProdController {
    @FXML private
    TableView<Part> AllPartTView, ProdPartTView;
    @FXML private ObservableList<Part> allPartsObservList, prodsObservList;
    @FXML private
    TextField ProdPartSearch, ProdMax, ProdMin, ProdInv, ProdName, ProdPrice;
    private Product addedProduct = new Product();

    @FXML private void initialize(){
        //partsTableView.getColumns();
        allPartsObservList= FXCollections.observableList(Main.getInventory().getParts());
        AllPartTView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        AllPartTView.setItems(allPartsObservList);

        prodsObservList =FXCollections.observableList(addedProduct.getPartsList());
        ProdPartTView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ProdPartTView.setItems(prodsObservList);

        AllPartTView.refresh();
        ProdPartTView.refresh();
    }
    @FXML public void prodSearchForPart(ActionEvent e){
        ObservableList<Part> filtered = FXCollections.observableArrayList();
        for(Part p: allPartsObservList){
            if (p.getName().contains(ProdPartSearch.getText())){
                filtered.add(p);
            }
        }
        AllPartTView.setItems(filtered);
        AllPartTView.refresh();
    }

    @FXML public void ProdAddPart(ActionEvent e){
        int index = AllPartTView.getSelectionModel().getSelectedIndex();
        try {
            prodsObservList.add(allPartsObservList.get(index));
        }catch (ArrayIndexOutOfBoundsException exc)
        {
            Alert error = new Alert(Alert.AlertType.ERROR, "Select a part to add.");
            error.showAndWait();
        }
        ProdPartTView.refresh();
    }

    @FXML public void ProdDelPart(ActionEvent e){
        int index = ProdPartTView.getSelectionModel().getSelectedIndex();
        //TODO: remove from product parts list for mod part?
        prodsObservList.remove(index);

    }

    @FXML public void ProdSave(ActionEvent e){
        //TODO
    }

    @FXML public void ProdCancel(ActionEvent e){
        Node source = (Node) e.getSource();
        Window window = source.getScene().getWindow();
        if (window instanceof Stage){
            ((Stage) window).hide();
        }
    }
}
