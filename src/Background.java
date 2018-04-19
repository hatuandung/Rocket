import java.awt.*;

public class Background {

    public Vector2D position;
    private BackgroundRenderer renderer;

    public Background() {
        this.position = new Vector2D();
        this.renderer = new BackgroundRenderer(Color.black,1024,600);
    }

    public void render(Graphics graphics) {
        this.renderer.render(graphics, this.position);
    }
}
