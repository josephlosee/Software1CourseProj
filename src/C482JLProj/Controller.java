package C482JLProj;

import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

//TODO: Make 3 controllers?
public class Controller {
    //@FXML private ObservableArray<Part> partObservableList; //Needed for the parts list, add ModelList for selection
    //@FXML private ObservableArray<Product> prodObservableList;
    //@FXML private TableView partsTable, productTable //needed for InvMgmt products and parts
    //@FXML private Button addPartButton, modPartButton, delPartButton, addProdButton, modProdButton, delProdButton, searchButton

    @FXML private TextField targetText;
    @FXML private Text actionTarget;
    @FXML private Button testButton;

    public void invPartSearchButton(ActionEvent e){
        Inventory inventory = Main.getInventory();
        //get search field text
        ////if empty, display the full parts list
        //do a search on the names
        //display the results in the box


    }
    @FXML public void handleButtonTest(ActionEvent e){

        //Inhouse test = new Inhouse();
        //test.setName(targetText.getText());
        //actionTarget.setText(targetText.getText());
        /*if (partObservableList == null)
        {
            synchronized (partObservableList=new ObservableArray<Part>());
        }
        //partObservableList.
        partObservableList.add(test);*/
        //partObservableList.add()
    }
}
