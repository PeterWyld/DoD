import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Runs the game with a human player and contains code needed to read inputs.
 *
 */
public class HumanPlayer extends Player {
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private Display display = new Display();
	private int gold = 0;

    /**
     * Reads player's input from the console.
     * <p>
     * @return : A string containing the input the player entered.
     */
	
	public HumanPlayer(int sight) {
		super(sight);
		mapChar = 'P';
	}
	
    private String getInput() {
        try {
        	return reader.readLine();
        	
        } catch(IOException e) {
        	System.err.println(e.getMessage());
        	System.exit(1);
        }
        return null;
    }

    /**
     * Processes the command. It should return a reply in form of a String, as the protocol dictates.
     * Otherwise it should return null.
     *
     * @return : Processed output or Invalid if the @param command is wrong.
     */
    @Override
    protected String getNextAction() {
        return getInput();
    }
    
    @Override
    protected void see(char[][] map) {
    	display.displayMap(canSee(map));
    }

    protected int getGold() {
    	return gold;
    }

    protected void addGold() {
    	gold += 1;
    }
}