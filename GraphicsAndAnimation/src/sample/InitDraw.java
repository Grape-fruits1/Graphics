package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class InitDraw {
    public void initDraw(GraphicsContext gc, GraphicsContext gc_effect) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc_effect.setStroke(Color.BLACK);
        gc_effect.setLineWidth(2);
    }
}
