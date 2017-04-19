package C482JLProj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;

/**
 * Created by Joe on 4/16/2017.
 */
public class AddProdController {
    @FXML private
    TableView<Part> AllPartTView, productPartsTableView;
    @FXML private Text prodTitle;
    @FXML private ObservableList<Part> allPartsObservList, prodPartsObservList;
    @FXML private
    TextField prodPartSearch, prodMax, prodMin, prodInv, prodName, prodPrice, prodID;
    private Product currentProduct = new Product();
    private boolean isProdMod = false;
    private ArrayList<Part> tempList = new ArrayList<>();

    /**
     * Default initializer, used for both add product and modify product logic. prodTitle text is changed in setProd
     */
    @FXML private void initialize(){
        //partsTableView.getColumns();
        prodTitle.setText("Add Product");
        allPartsObservList= FXCollections.observableList(Main.getInventory().getParts());
        AllPartTView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        AllPartTView.setItems(allPartsObservList);
        tempList.addAll(currentProduct.getPartsList());
        prodPartsObservList = FXCollections.observableList(tempList);
        productPartsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productPartsTableView.setItems(prodPartsObservList);

        AllPartTView.refresh();
        productPartsTableView.refresh();
    }

    /**
     * Search bar logic
     * @param e
     */
    @FXML public void prodSearchForPart(ActionEvent e){
        ObservableList<Part> filtered = FXCollections.observableArrayList();
        for(Part p: allPartsObservList){
            if (p.getName().contains(prodPartSearch.getText())){
                filtered.add(p);
            }
        }
        AllPartTView.setItems(filtered);
        AllPartTView.refresh();
    }

    /**
     * Adds the selected part to the product
     * @param e
     */
    @FXML public void ProdAddPart(ActionEvent e){
        int index = AllPartTView.getSelectionModel().getSelectedIndex();
        try {
            //prodsObservList.add(allPartsObservList.get(index));
            tempList.add(allPartsObservList.get(index));
        }catch (ArrayIndexOutOfBoundsException exc)
        {
            Alert error = new Alert(Alert.AlertType.ERROR, "Select a part to add.");
            error.showAndWait();
        }
        //clear the selection in the Prod Parts Table View
        productPartsTableView.getSelectionModel().clearSelection();//doesn't work
        productPartsTableView.getSortOrder().setAll(productPartsTableView.getColumns());//DOES WORK! Allows immediate selection of parts
        productPartsTableView.getSortOrder().clear();
        productPartsTableView.refresh();

    }

    /**
     * Deletes the selected part from the product
     * @param e
     */
    @FXML public void ProdDelPart(ActionEvent e){
        int index = productPartsTableView.getSelectionModel().getSelectedIndex();
        tempList.remove(index);
        productPartsTableView.getSelectionModel().clearSelection();
        productPartsTableView.refresh();

    }

    @FXML public void ProdSave(ActionEvent e){
        try{
            Product dummyProduct = new Product();
            //Make sure there are parts to assign to the product, and that the sum of parts does not exceed the price
            if (tempList.isEmpty()){
                throw new Exception("A product must have at least one part.");
            }
            dummyProduct.getPartsList().addAll(tempList);
            dummyProduct.setPrice(Double.parseDouble(prodPrice.getText()));

            //After this we can check the rest of the fields
            //Try and set the fields
            currentProduct.setName(prodName.getText());
            currentProduct.setPrice(Double.parseDouble(prodPrice.getText()));
            currentProduct.setMax(Integer.parseInt(prodMax.getText()));
            currentProduct.setMin(Integer.parseInt(prodMin.getText()));
            currentProduct.setInstock(Integer.parseInt(prodInv.getText()));

            if (currentProduct.getProductID() < 0){
                currentProduct.setProductID(Product.getNextProdID());
            }

            //Add the parts to the product
            currentProduct.getPartsList().clear(); //sanity clear
            for (Part p:tempList){
                currentProduct.addPart(p);
            }

            if (!isProdMod) {
                //Quick flag check if the form is in Add or Modify mode to use the same form and most of the same logic.
                Main.getInventory().addProduct(currentProduct);
            }
            Node source = (Node) e.getSource();
            Window window = source.getScene().getWindow();
            window.hide();

        } catch (Exception e1) {
            Alert error = new Alert(Alert.AlertType.ERROR, e1.getMessage());
            error.showAndWait();
        }
    }

    /**
     *
     * @param e
     */
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

    /**
     * Sets up the stage for modifying a product instead of adding one.
     * @param modProd
     */
    public void setProduct(Product modProd){
        currentProduct = modProd;
        this.initialize();
        prodTitle.setText("Modify Product");
        this.isProdMod =true;
        prodID.setText(String.valueOf(modProd.getProductID()));
        prodMax.setText(String.valueOf(modProd.getMax()));
        prodMin.setText(String.valueOf(modProd.getMin()));
        prodInv.setText(String.valueOf(modProd.getInstock()));
        prodName.setText(modProd.getName());
        prodPrice.setText(String.format("%1.2f", modProd.getPrice()));
    }
}
