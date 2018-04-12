import java.awt.*;
import java.awt.image.BufferedImage;

public class Star {
    public int x;
    public int y;
    public BufferedImage image;
    public int width;
    public int height;
    public int velocityX;

    public Star(int x, int y, BufferedImage image, int width, int height, int veocityX) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
        this.velocityX = veocityX;
    }

    public void run(){
        this.x -= this.velocityX;
    }

    public void render(Graphics graphics){
        graphics.drawImage(this.image , this.x , this.y , this.width , this.height ,null);
    }

}
