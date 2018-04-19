import java.awt.*;
import java.awt.image.BufferedImage;

public class Star {

    public Vector2D position;
    public Vector2D velocity;
    private ImageRenderer renderer;

    public Star() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/star.png",5,5);
    }

    public void runStar() {
        this.position.subtractBy(this.velocity);
    }

    public void renderStar(Graphics graphics) {
        this.renderer.render(graphics, this.position);
    }

}
