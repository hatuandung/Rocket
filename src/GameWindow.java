import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

public class GameWindow extends JFrame {

    private GameCanvas gameCanvas;
    private long lastTime = 0;



    public GameWindow() {
        //set size cho window
        this.setSize(1024, 600);
        //this.setVisible(true);// cho phep window hien thi
        this.setupGameCanvas();
        this.eventKeyboard();
        this.windowEvent();
        this.setVisible(true);// cho phep window hien thi // nen de sau khi cau hinh xong
    }

    private void setupGameCanvas() {
        this.gameCanvas = new GameCanvas();
        this.add(this.gameCanvas);
    }

    private void eventKeyboard(){
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    gameCanvas.player.playerMove.angle -= 5;
                    //gameCanvas.player.playerMove.rotate(gameCanvas.player.playerMove.angle);
                    //gameCanvas.player.runPlayer();
                    gameCanvas.player.position.rotate(gameCanvas.player.playerMove.angle);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    gameCanvas.player.playerMove.angle += 5;
                    //gameCanvas.player.runPlayer();
                    //gameCanvas.vector2D.rotate(gameCanvas.player.playerMove.angle);
                    //gameCanvas.player.runPlayer();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    gameCanvas.player.playerMove.velocity = new Vector2D(10,0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    gameCanvas.player.playerMove.angle = 0;
                    //gameCanvas.vector2D.rotate(gameCanvas.player.playerMove.angle);
                    //gameCanvas.player.runPlayer();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    gameCanvas.player.playerMove.angle = 0;
                    //gameCanvas.vector2D.rotate(gameCanvas.player.playerMove.angle);
                    //gameCanvas.player.runPlayer();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    //gameCanvas.player.playerMove.velocity = new Vector2D(10,0);
                    return;
                }
            }
        });
    }

    private void windowEvent(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    public void gameLoop() {
        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - lastTime >= 17_000_000) {

                this.gameCanvas.runAll();
                this.gameCanvas.renderAll();
                this.lastTime = currentTime;
            }

        }
    }

}
