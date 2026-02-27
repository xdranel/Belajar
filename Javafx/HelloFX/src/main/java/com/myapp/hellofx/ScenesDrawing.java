package com.myapp.hellofx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScenesDrawing extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.SKYBLUE);
        Stage stage = new Stage();

        Text text = new Text();
        text.setText("Hello World!");
        text.setX(scene.getWidth() / 2 - text.getBoundsInLocal().getWidth() / 2);
        text.setY(scene.getHeight() / 2 - text.getBoundsInLocal().getHeight() / 2);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        text.setFill(Color.LIMEGREEN);

        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(200);
        line.setStrokeWidth(5);
        line.setStroke(Color.PURPLE);
        line.setOpacity(0.5);
        line.setRotate(45);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(200);
        rectangle.setY(200);
        rectangle.setWidth(50);
        rectangle.setHeight(50);
        rectangle.setFill(Color.BROWN);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.BLACK);
        rectangle.setOpacity(1);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
          200.0,200.0,
                300.0,300.0,
                200.0,300.0
        );
        triangle.setFill(Color.YELLOW);

        Circle circle = new Circle();
        circle.setCenterX(350);
        circle.setCenterY(350);
        circle.setRadius(50);
        circle.setFill(Color.GREEN);

        Image image = new Image("pizza.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(400);

        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(triangle);
        root.getChildren().add(circle);
        root.getChildren().add(imageView);
        stage.setScene(scene);
        stage.show();
    }
}