package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Author: Chris liu
 * Email: chrisliu0810@163.com
 * 1.目前已发现一问题：由于使用GraphicsContext的作图方法，图形仅能从左上角往右下角，待解决。
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
//        画板控件canvas
        Canvas canvas = new Canvas(1225, 560);
        Canvas canvas_effect = new Canvas(1225, 560);
        canvas_effect.setStyle("-fx-background-color: rgba(255, 255, 255, 0)");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext gc_effect = canvas_effect.getGraphicsContext2D();
        InitDraw initDraw = new InitDraw();
        initDraw.initDraw(gc, gc_effect);
//        清屏按钮
        Button button_clear = new Button("清屏");
        button_clear.setStyle("-fx-font-size: 20px; -fx-pref-height: 50px; -fx-pref-width: 100px");
        button_clear.setOnAction(event -> gc.clearRect(0, 0, canvas_effect.getWidth(), canvas_effect.getHeight()));
//        布局
        root.setStyle("-fx-background-color: WHITE");
        root.getChildren().addAll(canvas, canvas_effect, button_clear);
        root.setAlignment(Pos.CENTER);
        StackPane.setAlignment(canvas, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(canvas_effect, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(button_clear, Pos.TOP_RIGHT);
//        下拉框ComboBox
        new setComboBox(root, canvas_effect, gc, gc_effect);
        Scene scene = new Scene(root, 1200, 600);
        primaryStage.setTitle("简易画图 Version 1.0");
        primaryStage.getIcons().add(new Image("file:src/res/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
