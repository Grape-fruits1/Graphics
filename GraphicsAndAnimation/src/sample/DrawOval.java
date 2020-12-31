package sample;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class DrawOval {
    //  解决问题1.通过GraphicsContext调用作图方法在Canvas中留下“画图轨迹”
    //  * 通过调用GraphicsContext的clearRect()方法解决问题。
    //  解决问题2.通过以上方法生成图形但是下一次作图时会清空整个canvas
    //  * 通过两个canvas对象叠加，其中一个背景设置为透明，对其中一个canvas进行临时操作，MOUSE_RELEASED后通过另一个canvas的GraphicsContext在另一个canvas对象中作图。
    double x1, y1, x2, y2;
    Canvas canvas_effect;
    GraphicsContext gc, gc_effect;
    Boolean isActive;

    public DrawOval(Canvas canvas_effect, GraphicsContext gc, GraphicsContext gc_effect) {
        this.canvas_effect = canvas_effect;
        this.gc = gc;
        this.gc_effect = gc_effect;
        isActive = false;
    }

    private final EventHandler<MouseEvent> pressed = (event) -> {
        x1 = event.getX();
        y1 = event.getY();
    };

    private final EventHandler<MouseEvent> dragged = (event) -> {
        x2 = event.getX();
        y2 = event.getY();
        gc_effect.clearRect(0, 0, canvas_effect.getWidth(), canvas_effect.getHeight());
        if (x2 > x1 && y2 > y1) {
            gc_effect.strokeOval(x1, y1, x2 - x1, y2 - y1);
        } else if (x2 > x1 && y2 < y1) {
            gc_effect.strokeOval(x1, y1, x2 - x1, Math.abs(y2 - y1));
        } else if (x2 < x1 && y2 > y1) {
            gc_effect.strokeOval(x1, y1, Math.abs(x1 - x2), y2 - y1);
        } else if (x2 < x1 && y2 < y1) {
            gc_effect.strokeOval(x1, y1, Math.abs(x1 - x2), Math.abs(y2 - y1));
        }
    };

    private final EventHandler<MouseEvent> released = (event) -> {
        gc_effect.clearRect(0, 0, canvas_effect.getWidth(), canvas_effect.getHeight());
        if (x2 > x1 && y2 > y1) {
            gc.strokeOval(x1, y1, x2 - x1, y2 - y1);
        } else if (x2 > x1 && y2 < y1) {
            gc.strokeOval(x1, y1, x2 - x1, Math.abs(y2 - y1));
        } else if (x2 < x1 && y2 > y1) {
            gc.strokeOval(x1, y1, Math.abs(x1 - x2), y2 - y1);
        } else if (x2 < x1 && y2 < y1) {
            gc.strokeOval(x1, y1, Math.abs(x1 - x2), Math.abs(y2 - y1));
        }
    };

    void takeAction() {
        canvas_effect.addEventHandler(MouseEvent.MOUSE_PRESSED, pressed);
        canvas_effect.addEventHandler(MouseEvent.MOUSE_DRAGGED, dragged);
        canvas_effect.addEventHandler(MouseEvent.MOUSE_RELEASED, released);
        isActive = true;
    }

    public void removeAction() {
        canvas_effect.removeEventHandler(MouseEvent.MOUSE_PRESSED, pressed);
        canvas_effect.removeEventHandler(MouseEvent.MOUSE_DRAGGED, dragged);
        canvas_effect.removeEventHandler(MouseEvent.MOUSE_RELEASED, released);
        isActive = false;
    }
}
