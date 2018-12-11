import java.util.Random;

public class BotPlayer extends Player {
	private Random rand = new Random();
	
	public BotPlayer(int sight) {
		super(sight);
		mapChar = 'B';
	}
}