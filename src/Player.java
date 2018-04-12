import java.awt.*;

public class Player {
    public int xPoints[];
    public int yPoints[];
    public int velocityX;
    public int points;
    public Color color;

    public Player(int[] xPoints, int[] yPoints, int velocityX, int points, Color color) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.velocityX = velocityX;
        this.points = points;
        this.color = color;
    }

    /*public void drawPolygon(int xPoints[], int yPoints[], int points){

        }*/
    public void runPlayer(){
        this.xPoints[0] += velocityX;
        this.xPoints[1] += velocityX;
        this.xPoints[2] += velocityX;
    }

    public void renderPlayer(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillPolygon(xPoints,yPoints,points);
    }

    //graphics.drawPolygon(new int[] {10, 20, 30}, new int[] {100, 20, 100}, 3);
}
