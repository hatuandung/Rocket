import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
    public Vector2D position;
    public Vector2D velocity;
    public Color color;
    public Polygon polygon;
    private List<Vector2D> vertices;
    Random random;


    public Player(Vector2D position,Vector2D velocity, Color color) {
        this.position = position;
        this.color = color;
        this.velocity = velocity;
        this.polygon = new Polygon();
        this.vertices = Arrays.asList(
                new Vector2D().rotate(45),
                new Vector2D(0, 16).rotate(45),
                new Vector2D(20,8).rotate(45)
        );
        random = new Random();
        this.vertices.forEach(vector2D -> polygon.addPoint((int) vector2D.x, (int) vector2D.y));
    }

    public void runPlayer(){
        if (this.position.x > 1024){
            this.position.x = 0;
            this.position.y = random.nextInt(600);
        }else if (this.position.y>600){
            this.position.y = 0;
            this.position.x = random.nextInt(1024);
        }
        this.position.addUp(velocity);
    }

    public void renderPlayer(Graphics graphics){
        update();
        graphics.setColor(this.color);

        graphics.fillPolygon(this.polygon);
    }

    private void update(){
        this.polygon.reset();
        Vector2D center = this.vertices.stream()
                .reduce(new Vector2D(), (v1, v2) -> v1.add(v2))//tat ca vector ttrong list se duoc cong het lai
                .multiply(1.0f / (float)this.vertices.size());

        Vector2D translate = this.position.subtract(center);
        this.vertices.stream()
                .map(vector2D -> vector2D.add(translate))//duyet tat ca phan tu -> vector se duoc dong them 1 vector khac
                .forEach(vector2D -> polygon.addPoint((int) vector2D.x, (int) vector2D.y));

    }
}
