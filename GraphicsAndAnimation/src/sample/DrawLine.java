package sample;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;


public class DrawLine {
    Canvas canvas;
    GraphicsContext gc;
    Boolean isActive;
    public DrawLine(Canvas canvas, GraphicsContext gc) {
        this.canvas = canvas;
        this.gc = gc;
        isActive = false;
    }

    private final EventHandler<MouseEvent> pressed = (event) -> {
        gc.beginPath();
        gc.moveTo(event.getX(), event.getY());
        gc.stroke();
    };

    private final EventHandler<MouseEvent> dragged = (event) -> {
        gc.lineTo(event.getX(), event.getY());
        gc.stroke();
    };

    private final EventHandler<MouseEvent> released = (event) -> {

    };

    public void takeAction() {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, pressed);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, dragged);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, released);
        isActive = true;
    }

    public void removeAction() {
        canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED, pressed);
        canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED, dragged);
        canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED, released);
        isActive = false;
    }
}
