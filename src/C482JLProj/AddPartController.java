package C482JLProj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import javax.swing.*;

/**
 * Created by Joe on 4/9/2017.
 */
public class AddPartController {
    @FXML     ToggleGroup PartTypeGroup;
    @FXML     HBox osBox, ihBox;
    @FXML     TextField machNameField, compNameField;
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
        try {
           Part addedPart;
        }catch(Exception except){

        }
    }

    //@FXML public void toggleGroup

    @FXML public void addPartCancel(ActionEvent e)
    {
        Control source = (Control) e.getSource();
        M
        //source.getScene().get
    }
}
