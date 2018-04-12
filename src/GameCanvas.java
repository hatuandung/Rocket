import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.awt.Color.black;

public class GameCanvas extends JPanel {
    private Random random;
    private List<Star> stars;
    private List<Enemy> enemies;
    private Background background;
    private Player player;

    private BufferedImage backBuffered;
    private Graphics graphics;
    private int count = 0;

    public GameCanvas(){
        //set Size
        this.random = new Random();
        this.setSize(1024,600);

        //khoi tao buffered
       this.setupBackBuffered();
       this.stars = new ArrayList<>();
       this.enemies = new ArrayList<>();
        //Load image

        this.setVisible(true);

    }

    private void setupBackBuffered(){
        this.backBuffered = new BufferedImage(1024,600,BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
    }

    @Override
    protected void paintComponent(Graphics g){

        g.drawImage(this.backBuffered,0,0,null);

    }

    public void renderAll(){

        this.drawBackground();
        this.stars.forEach(star -> star.render(this.graphics));
        this.enemies.forEach(enemy -> enemy.renderEnemy(this.graphics));
        //this.player.renderPlayer(this.graphics);
        this.repaint();
    }

    /*private void drawBackground(){
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0,0,1024,600);
    }*/

    private void drawBackground(){

        this.background = new Background(Color.black,0,0,1024,600);
        this.background.render(this.graphics);

    }

    public void runAll(){
        //cập nhật tất cả mọi thứ

        //Run Star
        this.createStar();
        this.stars.forEach(star -> star.run());
        //Run Enemy
        this.createEnemy();
        this.enemies.forEach(enemy -> enemy.runEnemy());
        //Run Player
        /*this.createPlayer();
        this.player.runPlayer();*/
    }

    private void createStar(){
        if(count == 10){
            Star star = new Star(1024, this.random.nextInt(600), this.loadImage("resources/images/star.png")
                               ,5,5,this.random.nextInt(2)+1);

            this.stars.add(star);
            this.count = 0;}
        else {
            this.count += 1;
        }
    }

    private void createEnemy(){
        if(count == 40){
            /*Enemy enemy = new Enemy(1024, this .random.nextInt(600),this.loadImage("resources/images/circle.png")
                                    ,25,25,this.random.nextInt(2)+1 ,0);*/
            Enemy enemy = new Enemy(this.random.nextInt(1024), 600,this.loadImage("resources/images/circle.png")
                                    ,25,25,0 ,this.random.nextInt(2) + 1);
            this.enemies.add(enemy);
            this.count = 0;
        }
        else{
            this.count += 1;
        }
    }

    /*private void createPlayer(){

        Player player = new Player(new int[] {10, 20, 30}, new  int[] {100, 20, 100}, 3,3,Color.white);
    }*/

    private BufferedImage loadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
