import java.awt.*;

public class Bullet {
    public Vector2D position;
    public Vector2D velocity;
    public ImageRenderer renderer;

    public Bullet() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png",3,3);
    }

    public void shootBullet() {
        this.position.addUp(this.velocity);
    }

    public void renderBullet(Graphics graphics) {
        this.renderer.render(graphics, this.position);
    }
}
