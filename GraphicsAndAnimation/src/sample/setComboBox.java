package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;

public class setComboBox {
    DrawLine drawLine;
    DrawCircle drawCircle;
    DrawOval drawOval;
    DrawRect drawRect;

    public setComboBox(StackPane root, Canvas canvas_effect, GraphicsContext gc, GraphicsContext gc_effect) {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "线",
                        "圆",
                        "椭圆",
                        "矩形"
                );
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setStyle("-fx-font-size: 18px; -fx-pref-width: 100px; -fx-pref-height: 50px");
        root.getChildren().addAll(comboBox);
        comboBox.getSelectionModel().selectFirst();
        StackPane.setAlignment(comboBox, Pos.TOP_LEFT);
        drawLine = new DrawLine(canvas_effect, gc);
        drawCircle = new DrawCircle(canvas_effect, gc, gc_effect);
        drawOval = new DrawOval(canvas_effect, gc, gc_effect);
        drawRect = new DrawRect(canvas_effect, gc, gc_effect);
        drawLine.takeAction();
        comboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (drawLine.isActive)
                        drawLine.removeAction();
                    if (drawCircle.isActive)
                        drawCircle.removeAction();
                    if (drawOval.isActive)
                        drawOval.removeAction();
                    if (drawRect.isActive)
                        drawRect.removeAction();
                    switch (newValue) {
                        case "线":
                            drawLine.takeAction();
                            break;
                        case "圆":
                            drawCircle.takeAction();
                            break;
                        case "椭圆":
                            drawOval.takeAction();
                            break;
                        case "矩形":
                            drawRect.takeAction();
                            break;
                    }
                }
        );
    }
}
