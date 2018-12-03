/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {
	
	private Map map;
	private Player[] players;
	
	/**
	 * Default constructor
	 */
	public GameLogic() {
		map = new Map();
		players = new Player[] {new HumanPlayer(3, 2, 2)};
	}

    /**
	 * Checks if the game is running
	 *
     * @return if the game is running.
     */
    protected boolean gameRunning() {
        return false;
    }
    
    public void initialiseGame() {
    
    protected void gameLoop() {
    	int index = 0;
    	String command;
    	while(true) {
    		index = 0;
    		for(int i = 0; i <= players.length -1; i++) {
    			command = players[index].getNextAction();
    			switch (command.split(" ")[0]) { //the first word of input from the player
    			case "HELLO":
    				System.out.println(map.getGoldRequired());
    				break;
    			case "GOLD":
    				System.out.println(((HumanPlayer) players[index]).getGold());
    				break;
    			case "MOVE":
    				move()
    				break;
    			case "PICKUP":
    				break;
    			case "LOOK":
    				players[index].see(map.getMap());
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
     * Checks if movement is legal and updates player's location on the map.
     *
     * @param direction : The direction of the movement.
     * @return : Protocol if success or not.
     */
    protected String move(String direction, Player player) {
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