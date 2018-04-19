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
    public Vector2D vector2D;
    private Random random;
    private List<Star> stars;
    private List<Enemy> enemies;
    private Background background;
    private List<Bullet> bullets;
    public Player player;

    private BufferedImage backBuffered;
    private Graphics graphics;
    private int count = 0;
    private int countEnemy = 0;//delay enemy
    private int countBullet = 0;

    public GameCanvas() {
        //set Size
        this.random = new Random();
        this.setSize(1024, 600);
        //khoi tao buffered
        this.setupBackBuffered();
        this.stars = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.bullets = new ArrayList<>();
        createPlayer();
        //Load image
        this.setVisible(true);

    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
        BufferedImage back2 = this.backBuffered;
        back2.setRGB(20, 20, 255);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.drawBackground();
        this.stars.forEach(star -> star.renderStar(this.graphics));
        this.enemies.forEach(enemy -> enemy.renderEnemy(this.graphics));
        this.player.renderPlayer(this.graphics);
        this.bullets.forEach(bullet -> bullet.renderBullet(this.graphics));
        this.repaint();
    }

    private void drawBackground() {
        /*this.background = new Background(Color.black, 0, 0, 1024, 600);
        this.background.render(this.graphics);*/
        Background background = new Background();
        background.position.set(0, 0);
        background.render(this.graphics);

    }

    public void runAll() {
        //cập nhật tất cả mọi thứ

        //Run Star
        this.createStar();
        this.stars.forEach(star -> star.runStar());

        //Run Enemy
        this.createEnemy();
        this.enemies.forEach(enemy -> enemy.velocity.set(this.player.position
                .subtract(enemy.position)
                .normalize()
        ).multiply(2.0f));
        this.enemies.forEach(enemy -> enemy.runEnemy());

        //Run Player
        this.player.runPlayer();

        this.shootBullet();
        this.bullets.forEach(bullet -> bullet.shootBullet());
    }

    private void createStar() {
        if (this.count == 30) {
            Star star = new Star();
            star.position.set(1024, this.random.nextInt(600));
            star.velocity.set(this.random.nextInt(2) + 1, 0);
            this.stars.add(star);
            this.count = 0;
        } else {
            this.count += 1;
        }

    }

    private void createEnemy() {
        /*if (countEnemy == 40) {
            Enemy enemy = new Enemy(new Vector2D(this.random.nextInt(1024), this.random.nextInt(600)),
                    this.loadImage("resources/images/circle.png"),
                    20, 20,
                    new Vector2D(10, 10));*/
        if (countEnemy == 40) {
            Enemy enemy = new Enemy();
            enemy.position.set(this.random.nextInt(1024), this.random.nextInt(600));
            enemy.velocity.set(10, 10);
            this.enemies.add(enemy);
            this.countEnemy = 0;
        } else {
            this.countEnemy += 1;
        }
    }

    private void createPlayer() {
        this.player = new Player();
        this.player.position.set(200, 200).rotate(player.playerMove.angle);
    }

    private void shootBullet() {
        if (countBullet == 10){
            Bullet bullet = new Bullet();
            bullet.position.set(this.player.position.x, this.player.position.y);
            bullet.velocity.set(this.player.playerMove.velocity.x+20,this.player.playerMove.velocity.y).rotate(player.playerMove.angle);
            this.bullets.add(bullet);
            this.countBullet = 0;
        }else {
            this.countBullet += 1;
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
