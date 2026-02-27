package com.myapp.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class EventHandlingController {

    @FXML
    private Circle circle;
    private double x;
    private double y;

    public void up(ActionEvent event) {
//        System.out.println("UP");
        circle.setCenterY(y = y - 10);
    }

    public void right(ActionEvent event) {
//        System.out.println("RIGHT");
        circle.setCenterX(x = x + 10);
    }

    public void down(ActionEvent event) {
//        System.out.println("DOWN");
        circle.setCenterY(y = y + 10);
    }

    public void left(ActionEvent event) {
//        System.out.println("LEFT");
        circle.setCenterX(x = x - 10);
    }
}
