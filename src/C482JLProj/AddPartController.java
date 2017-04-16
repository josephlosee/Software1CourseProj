package C482JLProj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Created by Joe on 4/9/2017.
 */
public class AddPartController {
    @FXML  ToggleGroup PartTypeGroup;
    @FXML  HBox osBox, ihBox;
    @FXML  TextField machNameField, compNameField, partMinField, partMaxField;
    @FXML  TextField partInvField, partIDField, partPriceField, partNameField;
    String sMachFieldDefText = "Machine ID", sCompNameFieldDefText="Company Name";

    @FXML public void radioToggled(ActionEvent e){
        machNameField.clear();
        compNameField.clear();
        machNameField.setPromptText(sMachFieldDefText);
        compNameField.setPromptText(sCompNameFieldDefText);
        RadioButton selectedToggle = (RadioButton)PartTypeGroup.getSelectedToggle();
        if (selectedToggle.getId().equals("apIHRadio")){
            ihBox.setVisible(true);
            osBox.setVisible(false);
        }
        else{
            ihBox.setVisible(false);
            osBox.setVisible(true);
        }
    }

    @FXML public void addPartSave(ActionEvent e)
    {
        Part addedPart = null;
        if (ihBox.isVisible()){
            //(int partID, String partName, double price, int stock, int min, int max, int machineID )
            Inhouse interrim = null;
            try {
                interrim = new Inhouse(Part.getNextPartID(), partNameField.getText(), Double.parseDouble(partPriceField.getText()),
                        Integer.parseInt(partInvField.getText()), Integer.parseInt(partMinField.getText()),
                        Integer.parseInt(partMaxField.getText()), Integer.parseInt(machNameField.getText()));
                addedPart=interrim;
            } catch (Exception e1) {
                Alert errorDialog = new Alert(Alert.AlertType.ERROR, e1.getMessage());
                errorDialog.showAndWait();
            }
        }
        else{
            Outsourced interrim = null;
            try {
                interrim = new Outsourced(Part.getNextPartID(), partNameField.getText(), Double.parseDouble(partPriceField.getText()),
                        Integer.parseInt(partInvField.getText()), Integer.parseInt(partMinField.getText()),
                        Integer.parseInt(partMaxField.getText()), compNameField.getText());
                addedPart=interrim;
            } catch (Exception e1) {
                Alert errorDialog = new Alert(Alert.AlertType.ERROR, e1.getMessage());
                errorDialog.showAndWait();
            }
        }

        if (addedPart!=null){
            Main.addPart(addedPart);
            Node source = (Node) e.getSource();
            Window window = source.getScene().getWindow();
            if (window instanceof Stage){
                ((Stage) window).hide();
            }
        }

    }

    //@FXML public void toggleGroup

    @FXML public void addPartCancel(ActionEvent e)
    {
        Node source = (Node) e.getSource();
        Window window = source.getScene().getWindow();
        if (window instanceof Stage){
            ((Stage) window).hide();
        }

        //M
        //source.getScene().get
    }
}
