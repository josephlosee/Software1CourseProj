package C482JLProj;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/6/2017.
 */
public class Inventory {
    private ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Part> getParts() {
        return parts;
    }

    private ArrayList<Part> parts = new ArrayList<>();

    public Inventory(){
        //Default constructor, sets placeholder options
        Inhouse ihPlaceholder = new Inhouse();
        ihPlaceholder.setPartID(Part.getNextPartID());
        Outsourced ohPlaceholder = new Outsourced();
        ohPlaceholder.setPartID(Part.getNextPartID());

        Product placeholder = new Product();
        placeholder.setProductID(Product.getNextProdID());

        parts.add(ihPlaceholder);
        parts.add(ohPlaceholder);
        products.add(new Product());
    }

    public boolean removeProduct(int iIndex) throws Exception{
        boolean isRemoved = false;
        Product remProd;
        try{
            remProd = products.get(iIndex);
        } catch (ArrayIndexOutOfBoundsException aioe){
            throw new Exception ("Index out of bounds "+aioe.getMessage());
        }
        if (!remProd.getPartsList().isEmpty()){
            throw new Exception("Products with parts associated cannot be deleted.");
        }else{
            products.remove(iIndex);
            isRemoved=true;
        }
        return isRemoved;
    }

    public Product lookupProduct(int iIndex){
        Product lookup = null;
        try {
            lookup = products.get(iIndex);
        } catch (IndexOutOfBoundsException e){
            System.err.println(e.getCause().toString());
        }
        return lookup;
    }

    public void updateProduct(int iIndex){
        Product updateProd;
        try {
            updateProd = this.lookupProduct(iIndex);
            //Setup to show the window
            Parent modProdPane = new GridPane();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("productView.fxml"));
            try {
                //Setup the parent
                modProdPane = (Parent)loader.load();
                //Get the reference to the controller class so
                ProductController controller =loader.<ProductController>getController();
                //We can populate the view with the part to be modified.
                controller.setProduct(updateProd);

            }catch (IOException ioExc){
                ioExc.printStackTrace();
            }
            //Resume setting up
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(new Scene(modProdPane));

            //Show and Wait to take away input from the main window
            secondaryStage.showAndWait();

        } catch (Exception e){
            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage());
            error.showAndWait();
        }
    }

    public ArrayList getProductList()
    {
        return products;
    }

    public void addProduct(Product newProd) throws Exception{
        if (newProd.getPartsList().size() == 0) {
            throw new Exception("A product must have at least one part.");
        }
        else {
            products.add(newProd);
        }
    }

    public void addPart(Part newPart){
        int i = parts.indexOf(newPart);
        if (i == -1){
            parts.add(newPart);
        }else{
            parts.set(i, newPart);
        }

    }

    public void updatePart(int index){
        Stage secondaryStage;
        //Setup to show the window
        Parent modPartPane = new GridPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("partView.fxml"));
        try {
            //Setup the parent
            modPartPane = (Parent)loader.load();
            //Get the reference to the controller class so
            PartController controller =loader.<PartController>getController();
            //We can populate the view with the part to be modified.
            if (index >= 0) {
                Part partToModify= this.getParts().get(index);

                controller.modPart(index, partToModify);

                //Resume setting up
                secondaryStage = new Stage();
                secondaryStage.setScene(new Scene(modPartPane));

                //Show and Wait to take away input from the main window
                secondaryStage.showAndWait();

                Part possibleChangedPart = this.getParts().get(index);

                if (((possibleChangedPart instanceof Inhouse && partToModify instanceof Outsourced) ||
                    (possibleChangedPart instanceof Outsourced && partToModify instanceof Inhouse))){
                    //It's changed!
                    propagatePartChanged(index, possibleChangedPart);
                }
            }
        }catch (IOException ioExc){
            ioExc.printStackTrace();
        }
    }

    /**
     * Propagates parts that have changed properties from Inhouse to Outsourced to all affected products.
     * @param index
     * @param changedPart
     */
    public void propagatePartChanged(int index, Part changedPart){
        parts.set(index, changedPart);
        int changePartID = changedPart.getPartID();

        for (Product p:products){
            for (int i=0; i<p.getPartsList().size(); i++){
                if (p.getPartsList().get(i).getPartID()==changePartID){
                    p.getPartsList().set(i, changedPart);
                }
            }
        }
    }
}
