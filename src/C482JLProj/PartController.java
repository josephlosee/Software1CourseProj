package C482JLProj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Created by Joe on 4/9/2017.
 */
public class PartController {
    @FXML  ToggleGroup PartTypeGroup;
    @FXML  HBox osBox, ihBox;
    @FXML  TextField machNameField, compNameField, partMinField, partMaxField;
    @FXML  TextField partInvField, partIDField, partPriceField, partNameField;
    String sMachFieldDefText = "Machine ID", sCompNameFieldDefText="Company Name";
    @FXML   Text partTitle;
    @FXML RadioButton apIHRadio, apOSRadio;
    private Part inputPart;
    private int partIndex = -1;

    /**
     * Handle the radio toggle
     * @param e
     */
    @FXML public void radioToggled(ActionEvent e){
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
        if ( inputPart != null && ((inputPart instanceof Inhouse && ihBox.isVisible()) || inputPart instanceof Outsourced && osBox.isVisible()))
        {
                //Set part fields
                try{
                    inputPart.setName(partNameField.getText());
                    inputPart.setPrice(Double.parseDouble(partPriceField.getText()));
                    inputPart.setMax(Integer.parseInt(partMaxField.getText()));
                    inputPart.setMin(Integer.parseInt(partMinField.getText()));
                    inputPart.setInstock(Integer.parseInt(partInvField.getText()));
                }catch (Exception e1){
                    Alert error = new Alert(Alert.AlertType.ERROR, e1.getMessage());
                }
        }else{
            inputPart=createPart();
        }

        if (inputPart!=null){
           //add the part to the inventory list if it isn't already
            if (this.partIndex==-1){
                Main.getInventory().addPart(inputPart);
            }else{
                Main.getInventory().getParts().set(partIndex, inputPart);
            }
            //Close the dialog
            Node source = (Node) e.getSource();
            Window window = source.getScene().getWindow();
            if (window instanceof Stage){
                ((Stage) window).hide();
            }
        }
    }

    /**
     * Attempts to create a new part with the entered data
     * @return the created part, null if the part could not be created
     */
    private Part createPart(){
        Part createdPart = null;
        int partID = -1;
        if (inputPart!=null){
            partID=inputPart.getPartID();
        }
        if (ihBox.isVisible()){
            Inhouse interrim = null;
            try {


                interrim = new Inhouse(-1, partNameField.getText(), Double.parseDouble(partPriceField.getText()),
                        Integer.parseInt(partInvField.getText()), Integer.parseInt(partMinField.getText()),
                        Integer.parseInt(partMaxField.getText()), Integer.parseInt(machNameField.getText()));
                createdPart = interrim;
                if (partID >= 0){
                    createdPart.setPartID(partID);
                }else {
                    createdPart.setPartID(Part.getNextPartID());
                }
            } catch (Exception e1) {
                Alert errorDialog = new Alert(Alert.AlertType.ERROR, e1.getMessage());
                errorDialog.showAndWait();
            }
        }
        else {//osBox.isVisible assumed{
            //Create an outsourced part
            Outsourced interrim = null;
            try {
                interrim = new Outsourced(-1, partNameField.getText(), Double.parseDouble(partPriceField.getText()),
                        Integer.parseInt(partInvField.getText()), Integer.parseInt(partMinField.getText()),
                        Integer.parseInt(partMaxField.getText()), compNameField.getText());
                createdPart = interrim;
                if (partID >= 0){
                    createdPart.setPartID(partID);
                }
            } catch (Exception e1) {
                Alert errorDialog = new Alert(Alert.AlertType.ERROR, e1.getMessage());
                errorDialog.showAndWait();
            }
        }

        return createdPart;
    }

    /**
     * Discards changes and exits after confirmation
     * @param e
     */
    @FXML public void addPartCancel(ActionEvent e)
    {
        Node source = (Node) e.getSource();
        Window window = source.getScene().getWindow();
        if (window instanceof Stage){
            Alert confirmCancel = new Alert(Alert.AlertType.CONFIRMATION, "Discard changes?");
            confirmCancel.showAndWait()
                    .filter(response-> response==ButtonType.OK)
                    .ifPresent(response->window.hide());
        }
    }

    public void modPart(int index, Part moddedPart){
        //Populate the fields
        this.partIndex = index;
        partTitle.setText("Modify Part");
        partPriceField.setText(""+moddedPart.getPrice());
        partIDField.setText(""+moddedPart.getPartID());
        partMinField.setText(""+moddedPart.getMin());
        partMaxField.setText(""+moddedPart.getMax());
        partNameField.setText(moddedPart.getName());
        partInvField.setText(""+moddedPart.getInstock());
        this.inputPart=moddedPart;

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
}//End of File
