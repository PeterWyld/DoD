import java.util.Random;

public class BotPlayer extends Player {
	private Random rand = new Random();
	
	public BotPlayer(int sight, int xPos, int yPos) {
		super(sight, xPos, yPos);
		mapChar = 'B';
	}
}