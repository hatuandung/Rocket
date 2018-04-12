import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {
    public int x;
    public int y;
    public BufferedImage image;
    public int width;
    public int height;
    public int velocityX;
    public int velocityY;

    public Enemy(int x, int y, BufferedImage image, int width, int height, int veocityX, int veocityY) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
        this.velocityX = veocityX;
        this.velocityY = veocityY;
    }

    /*public void runEnemy(){
        this.x += this.velocityX;
    }*/

    public void runEnemy(){
        this.y += this.velocityY;
    }


    public void renderEnemy(Graphics graphics){
        graphics.drawImage(this.image, this.x , this.y , this.width , this.height ,null);

    }
}
