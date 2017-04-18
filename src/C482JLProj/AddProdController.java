package C482JLProj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
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
            //prodsObservList.add(allPartsObservList.get(index));
            addedProduct.getPartsList().add(allPartsObservList.get(index));
        }catch (ArrayIndexOutOfBoundsException exc)
        {
            Alert error = new Alert(Alert.AlertType.ERROR, "Select a part to add.");
            error.showAndWait();
        }
        //clear the selection in the Prod Parts Table View
        ProdPartTView.getSelectionModel().clearSelection();
        ProdPartTView.refresh();

    }

    //DONE
    @FXML public void ProdDelPart(ActionEvent e){
        int index = ProdPartTView.getSelectionModel().getSelectedIndex();
        //TODO: remove from product parts list for mod part?
        addedProduct.removePart(index);
        ProdPartTView.getSelectionModel().clearSelection();
        ProdPartTView.refresh();

    }


    @FXML public void ProdSave(ActionEvent e){
        try{
            addedProduct.setName(ProdName.getText());
            addedProduct.setPrice(Double.parseDouble(ProdPrice.getText()));
            addedProduct.setMax(Integer.parseInt(ProdMax.getText()));
            addedProduct.setMin(Integer.parseInt(ProdMin.getText()));
            addedProduct.setInstock(Integer.parseInt(ProdInv.getText()));
            if (addedProduct.getProductID() < 0){
                addedProduct.setProductID(Product.getNextProdID());
            }
            Main.getInventory().addProduct(addedProduct);
            Node source = (Node) e.getSource();
            Window window = source.getScene().getWindow();
            window.hide();
        } catch (Exception e1) {
            Alert error = new Alert(Alert.AlertType.ERROR, e1.getMessage());
            error.showAndWait();
        }
    }

    @FXML public void ProdCancel(ActionEvent e){
        Node source = (Node) e.getSource();
        Window window = source.getScene().getWindow();
        if (window instanceof Stage){
            Alert confirmCancel = new Alert(Alert.AlertType.CONFIRMATION, "Discard changes?");
            confirmCancel.showAndWait()
                    .filter(response-> response==ButtonType.OK)
                    .ifPresent(response->window.hide());
        }
        //Close the window

    }

    public void setProduct(Product modProd){
        addedProduct = modProd;
        ProdMax.setText(String.valueOf(modProd.getMax()));
        ProdMin.setText(String.valueOf(modProd.getMin()));
        ProdInv.setText(String.valueOf(modProd.getInstock()));
        ProdName.setText(modProd.getName());
        ProdPrice.setText(String.valueOf(modProd.getPrice()));
    }
}
