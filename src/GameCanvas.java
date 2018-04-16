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
    private int count = 0;//delay

    public GameCanvas(){
        //set Size
        this.random = new Random();
        this.setSize(1024,600);
        //this.player = new Player(new Vector2D(200,200),Color.red);
        //khoi tao buffered
       this.setupBackBuffered();
       this.stars = new ArrayList<>();
       this.enemies = new ArrayList<>();
       createPlayer();
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
        this.player.renderPlayer(this.graphics);
        this.repaint();
    }

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
        //this.createPlayer();
        this.player.runPlayer();

    }

    private void createStar(){
        if(count == 10){
            Star star = new Star(new Vector2D(1024, this.random.nextInt(600)),
                                this.loadImage("resources/images/star.png"),
                                5,5,
                                new Vector2D(this.random.nextInt(2)+1,0));

            this.stars.add(star);
            this.count = 0;}
        else {
            this.count += 1;
        }
    }

    private void createEnemy(){
        if(count == 10){
            Enemy enemy = new Enemy(new Vector2D(this.random.nextInt(1024), this.random.nextInt(600)),
                                    this.loadImage("resources/images/circle.png"),
                                    20,20,
                                     new Vector2D(0,this.random.nextInt(2)+1));

            this.enemies.add(enemy);
            this.count = 0;
        }
        else{
            this.count += 1;
        }
    }

    private void createPlayer(){
        this.player = new Player(new Vector2D(200,200),new Vector2D(5,5),Color.red);
    }

    private BufferedImage loadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
