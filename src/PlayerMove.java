import java.util.Random;

public class PlayerMove {
    public Vector2D velocity;
    public double angle = 0.0;
    private Random random;

    public PlayerMove() {
        this.velocity = new Vector2D(2,0);
        this.random = new Random();
    }

    public void runPlayer(Player player){
        this.velocity.set(this.velocity.rotate(this.angle));

        player.position.addUp(this.velocity);
        if (player.position.x > 1024) {
            player.position.set(0, this.random.nextInt(600));
        }
        if (player.position.y > 600) {
            player.position.set(this.random.nextInt(1024), 0);
        }
        if(player.position.x < 0){
            player.position.set(1024, this.random.nextInt(600));
        }
        if(player.position.y < 0){
            player.position.set(this.random.nextInt(1024), 600);
        }
    }
}
