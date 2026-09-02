package com.myapp.randomcode;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageViewController {

    @FXML
    private ImageView image;

    @FXML
    void showImage(MouseEvent event) {
        image.setVisible(true);
    }


}
