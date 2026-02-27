package com.myapp.randomcode;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class TextFieldController {

    @FXML
    private TextField usernameIn;

    @FXML
    private Text usernameOut;

    @FXML
    void loginButton(MouseEvent event) {
        usernameOut.setText(usernameIn.getText());
    }
}
