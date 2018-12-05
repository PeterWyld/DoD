import java.util.ArrayList;

/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {
	
	private Map map;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	/**
	 * Default constructor
	 */
	public GameLogic() {
		map = new Map();
		initialiseGame();
	}

    /**
	 * Checks if the game is running
	 *
     * @return if the game is running.
     */
    protected boolean gameRunning() {
        return false;
    }
    
    protected void addPlayer(Player newPlayer) {
    	newPlayer = map.addPlayerToMap(newPlayer);
    	players.add(newPlayer);
    }
    
    public void initialiseGame() {
    	addPlayer(new HumanPlayer(5, 2, 0));
    	addPlayer(new HumanPlayer(7, 0, 0));
    	addPlayer(new HumanPlayer(3, 0, 0));
    	addPlayer(new HumanPlayer(1, 0, 0));
    }
    
    protected void gameLoop() {
    	String[] command;
    	while(true) {
    		for(int i = 0; i <= players.size() -1; i++) {
    			command = players.get(i).getNextAction().split(" ");
    			switch (command[0]) { //the first word of input from the player
    			case "HELLO":
    				System.out.println(map.getGoldRequired());
    				break;
    			case "GOLD":
    				System.out.println(((HumanPlayer) players.get(i)).getGold());
    				break;
    			case "MOVE":
    				if (command.length > 1) {
    					move(command[1], players.get(i));
    				} else {
    					System.out.println("Command Invalid");
        				i--; //so it stays on that players turn
    				}
    				break;
    			case "PICKUP":
    				break;
    			case "LOOK":
    				players.get(i).see(map.getMap());
    				break;
    			case "QUIT":
    				quitGame();
    				break;
    			default:
    				System.out.println("Command Invalid");
    				i--; //so it stays on that players turn
    			}
    		}
    	}
    }

    /**
	 * Returns the gold required to win.
	 *
     * @return : Gold required to win.
     */
    protected String hello() {
        return null;
    }
	
	/**
	 * Returns the gold currently owned by the player.
	 *
     * @return : Gold currently owned.
     */
    protected String gold() {
        return null;
    }



    /**
     * Converts the map from a 2D char array to a single string.
     *
     * @return : A String representation of the game map.
     */
    protected String look() {
        return null;
    }

    /**
     * Processes the player's pickup command, updating the map and the player's gold amount.
     *
     * @return If the player successfully picked-up gold or not.
     */
    protected String pickup() {
        return null;
    }

    /**
     * Quits the game, shutting down the application.
     */
    protected void quitGame() {
    	System.exit(0);
    }
}