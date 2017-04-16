package C482JLProj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * ${FILENAME}
 * Created by Joseph Losee on 4/16/2017.
 */
public class InvController {
    @FXML private TableView partsTableView;
    @FXML private TableColumn<Part, String> partsTVColName, partsTVColID, partsTVColInvLev, partsTVColPrice;
    @FXML private TextField invPartSearchField;
    @FXML private ObservableList<Part> partObservList;
    private Stage addPartStage;

    @FXML private void initialize(){
        //partsTableView.getColumns();
        partObservList=FXCollections.observableList(Main.getInventory().getParts());
        partsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        partsTableView.setItems(partObservList);
        //partsTableView = new TableView(partObservList);
        //partsTableView
        partsTableView.refresh();
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

        Parent test = new GridPane();

        try {
            test = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        }catch (IOException ioExc){
            ioExc.printStackTrace();
        }
        addPartStage = new Stage();
        addPartStage.setScene(new Scene(test));

        addPartStage.showAndWait();

        partsTableView.refresh();
    }

    @FXML public void delPartButtonClick(ActionEvent e){
        int index = partsTableView.getSelectionModel().getSelectedIndex();
        partObservList.remove(index);
        partsTableView.refresh();
    }

}
