import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {
    public Vector2D position;
    public BufferedImage image;
    public int width;
    public int height;
    public Vector2D velocity;

    public Enemy(Vector2D position, BufferedImage image, int width, int height, Vector2D velocity) {
        this.position = position;
        this.image = image;
        this.width = width;
        this.height = height;
        //this.velocityX = velocityX;
        this.velocity = velocity;
    }

    public void runEnemy(){
        this.position.addUp(this.velocity);
    }

    /*public void runEnemy(){
        this.y += this.velocityY;
    }*/


    public void renderEnemy(Graphics graphics){
        graphics.drawImage(this.image, (int)position.x, (int)position.y, this.width, this.height, null);
    }
}
