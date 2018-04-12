import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class GameWindow extends JFrame {

    private GameCanvas gameCanvas;
    private long lastTime = 0;


    public GameWindow(){
        //set size cho window
        this.setSize(1024, 600);
        //this.setVisible(true);// cho phep window hien thi
        this.setupGameCanvas();
        this.setVisible(true);// cho phep window hien thi // nen de sau khi cau hinh xong
    }

    private void setupGameCanvas(){
        this.gameCanvas = new GameCanvas();
        this.add(this.gameCanvas);
    }

    public void gameLoop(){
        while(true){
            long currentTime  = System.nanoTime();
            if(currentTime - lastTime >= 17_000_000){

                this.gameCanvas.runAll();
                this.gameCanvas.renderAll();
                this.lastTime = currentTime;
            }

        }
    }

}
