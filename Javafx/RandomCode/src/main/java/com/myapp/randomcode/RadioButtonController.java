package com.myapp.randomcode;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class RadioButtonController {

    @FXML
    private ToggleGroup animal;

    @FXML
    private RadioButton catRadio;

    @FXML
    private Text choose;

    @FXML
    private RadioButton dogRadio;

    @FXML
    void getAnimal(MouseEvent event) {
        if (animal.getSelectedToggle() == catRadio) {
            choose.setText("Cat");
        } else if (animal.getSelectedToggle() == dogRadio) {
            choose.setText("Dog");
        } else if (animal.getSelectedToggle() == null) {
            choose.setText("Choose One!!!");
        }
    }
}
