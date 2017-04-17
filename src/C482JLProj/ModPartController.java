package C482JLProj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Created by Joe on 4/16/2017.
 */
public class ModPartController {
    @FXML  ToggleGroup PartTypeGroup;
    @FXML  HBox osBox, ihBox;
    @FXML  TextField machNameField, compNameField, partMinField, partMaxField;
    @FXML  TextField partInvField, partIDField, partPriceField, partNameField;
    @FXML RadioButton apIHRadio, apOSRadio;
    String sMachFieldDefText = "Machine ID", sCompNameFieldDefText="Company Name";
    private Part inputPart;
    private int partIndex;

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

    @FXML public void modPartSave(ActionEvent e)
    {
        //TODO: Make changes to part
        Part addedPart = null;
        if (ihBox.isVisible()){
            //(int partID, String partName, double price, int stock, int min, int max, int machineID )
            Inhouse interrim = null;
            try {
                interrim = new Inhouse(Integer.parseInt(partIDField.getText()), partNameField.getText(), Double.parseDouble(partPriceField.getText()),
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
            Main.getInventory().updatePart(partIndex, addedPart);

            Node source = (Node) e.getSource();
            Window window = source.getScene().getWindow();
            if (window instanceof Stage){
                ((Stage) window).hide();
            }
        }

    }

    //@FXML public void toggleGroup

    @FXML public void modPartCancel(ActionEvent e)
    {
        Node source = (Node) e.getSource();
        Window window = source.getScene().getWindow();
        if (window instanceof Stage){
            ((Stage) window).hide();
        }

        //M
        //source.getScene().get
    }

    public void modPart(int index, Part moddedPart){
        //Populate the fields
        this.partIndex = index;
        partPriceField.setText(""+moddedPart.getPrice());
        partIDField.setText(""+moddedPart.getPartID());
        partMinField.setText(""+moddedPart.getMin());
        partMaxField.setText(""+moddedPart.getMax());
        partNameField.setText(moddedPart.getName());
        partInvField.setText(""+moddedPart.getInstock());

        //Set the machine ID or company namej fields based on toggled radio
        if (moddedPart instanceof Outsourced){
            this.PartTypeGroup.selectToggle(apOSRadio);
            this.radioToggled(null);
            compNameField.setText(((Outsourced) moddedPart).getCompanyName());
        }
        else if (moddedPart instanceof Inhouse){
            machNameField.setText(""+((Inhouse)moddedPart).getMachineID());
        }
    }


}
