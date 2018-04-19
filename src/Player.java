import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
    public Vector2D position;
    private PolygonRenderer renderer;
    public PlayerMove playerMove;

    public Player() {

        this.position = new Vector2D();
        this.renderer = new PolygonRenderer(Color.red,
                new Vector2D(),//.rotate(playerMove.angle),
                new Vector2D(0, 16),//.rotate(playerMove.angle),
                new Vector2D(20, 8)//.rotate(playerMove.angle)
        );



        this.playerMove = new PlayerMove();
    }

    public void runPlayer() {
        //this.renderer.angle = this.angle;

        this.playerMove.runPlayer(this);
        this.renderer.angle = this.playerMove.angle;
        position.rotate(renderer.angle);

    }

    public void renderPlayer(Graphics graphics) {
        this.renderer.renderPlayer(graphics, this.position);
    }


}
