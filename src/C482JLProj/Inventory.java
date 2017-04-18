package C482JLProj;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        parts.add(new Inhouse());
        parts.add(new Outsourced());
        products.add(new Product());
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
        }
        else{
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
            e.getCause().toString();
        }
        return lookup;
    }

    //Todo: make the calls to create the AddProd/ModProd stage ehre
    public void updateProduct(int iIndex){
        Product updateProd;
        try {
            updateProd = products.get(iIndex);
            //Setup to show the window
            Parent modProdPane = new GridPane();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProd.fxml"));
            try {
                //Setup the parent
                modProdPane = (Parent)loader.load();
                //Get the reference to the controller class so
                AddProdController controller =loader.<AddProdController>getController();
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

        }
    }

    public ArrayList getProductList()
    {
        return products;
    }

    public void addProduct(Product newProd) throws Exception{
        if (newProd.getPartsList().size() == 0){
            throw new Exception("A product must have at least one part.");
        }
        else {
            products.add(newProd);
        }
    }

    public void addPart(Part newPart){
            parts.add(newPart);
    }

    public void updatePart(int index, Part updatedPart){
        parts.set(index, updatedPart);
    }



}
