import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PolygonRenderer {
    private Color color;
    private Polygon polygon;
    public List<Vector2D> vertices;
    public double angle = 0.0;

    public PolygonRenderer(Color color, Vector2D... vertices) {
        this.color = color;
        this.polygon = new Polygon();
        this.vertices = Arrays.asList(vertices);
        this.vertices.forEach(vector2D -> polygon.addPoint((int) vector2D.x, (int) vector2D.y));

    }

    /*public void rotatePolygon(double angle){
        this.vertices.forEach(vector2D -> vector2D.rotate(angle));

    }*/


    public void renderPlayer(Graphics graphics, Vector2D position) {
        this.update(position);
        graphics.setColor(this.color);

        graphics.fillPolygon(this.polygon);
    }

    private void update(Vector2D position) {
        this.polygon.reset();
        Vector2D center = this.vertices.stream()
                .reduce(new Vector2D(), (v1, v2) -> v1.add(v2))//tat ca vector trong list se duoc cong het lai
                .multiply(1.0f / (float) this.vertices.size());

        Vector2D translate = position.subtract(center);
        this.vertices.stream()
                .map(vector2D -> vector2D.rotate(angle))//xoay dinh
                .map(vector2D -> vector2D.add(translate))//duyet tat ca phan tu -> vector se duoc dong them 1 vector khac
                .forEach(vector2D -> polygon.addPoint((int) vector2D.x, (int) vector2D.y));

    }
}
