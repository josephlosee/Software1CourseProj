package C482JLProj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Control;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import javax.swing.*;

/**
 * Created by Joe on 4/9/2017.
 */
public class AddPartController {
    @FXML     ToggleGroup PartTypeGroup;
    @FXML     HBox osBox, ihBox;
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
        try {
           Part addedPart;
        }catch(Exception except){

        }
    }

    //@FXML public void toggleGroup

    @FXML public void addPartCancel(ActionEvent e)
    {
        Control source = (Control) e.getSource();
        //source.getScene().get
    }
}
