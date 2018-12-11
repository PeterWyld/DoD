import java.io.File;
import java.util.ArrayList;

/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {
	
	private Map map;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Display disp = new Display();
	
	/**
	 * Default constructor
	 */
	public GameLogic() {
		map = new Map();
		initialiseGame();
	}
	
	public GameLogic(File mapFile) {
		map = new Map(mapFile);
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
    	addPlayer(new HumanPlayer(9, 0, 0));
    }
    
    protected void gameLoop() {
    	String[] command;
    	boolean moved;
    	while(true) {
    		for(int i = 0; i <= players.size() -1; i++) {
    			command = players.get(i).getNextAction().split(" ");
    			switch (command[0]) { //the first word of input from the player
    			case "HELLO":
    				System.out.println("Gold  to win: " + map.getGoldRequired());
    				break;
    			case "GOLD":
    				System.out.println("Gold owned: " + ((HumanPlayer) players.get(i)).getGold());
    				break;
    			case "MOVE":
    				if (command.length > 1) {
    					moved = move(command[1], players.get(i));
    					disp.dispSuccessOrFail(moved);
    					System.out.println();
    				} else {
    					System.out.println("Command Invalid");
        				i--; //so it stays on that players turn
    				}
    				break;
    			case "PICKUP":
    				disp.dispSuccessOrFail(pickup((HumanPlayer) players.get(i)));
    				System.out.println(". Gold owned: " + ((HumanPlayer) players.get(i)).getGold());
    				break;
    			case "LOOK":
    				players.get(i).see(map.getMapWithPlayers(players));
    				break;
    			case "QUIT":
    				quitGame((HumanPlayer) players.get(i));
    				break;
    			default:
    				System.out.println("Command Invalid");
    				i--; //so it stays on that players turn
    			}
    		}
    	}
    }
    
    protected boolean move(String direction, Player player) {
    	int incrementX = 0;
		int incrementY = 0;
		int newX = 0;
		int newY = 0;
		switch(direction) {
		case "N": 
			incrementY = -1;
			break;
		case "E":
			incrementX = 1;
			break;
		case "S":
			incrementY = 1;
			break;
		case "W":
			incrementX = -1;
			break;
		default:
			//invalid direction
			return false;
		}
		newX = player.getX() + incrementX;
		newY = player.getY() + incrementY;
		
		switch(map.getCharAtPos(newX, newY, players)) {
	    	case '#':
	    		return false;
	    	case 'B':
	    		if(player instanceof HumanPlayer) {
	    			killPlayer(player.getX(), player.getY());
	    		} else { //is a bot
	    			return false;
	    		}
	    		break;
	    	case 'P':
	    		if(player instanceof HumanPlayer) {
	    			return false;
	    		} else { //is a bot
	    			killPlayer(newX, newY);
	    			player.setXY(newX, newY);
	    			return true;
	    		}
	    	case 'E': case 'G': case '.':
	    		player.setXY(newX, newY);
	    		return true;
	    }
		return false;
    }
    
    private void killPlayer(int xPos, int yPos) {
    	int index = lookUpPlayer(xPos, yPos);
    	players.remove(index);
    	if(numbOfHumans() == 1) {
    		System.out.println("LOSE");
    		System.exit(0);
    	}
    }
    
    private int lookUpPlayer(int xPos, int yPos) {
    	for (int i = 0; i <= players.size() -1; i++) {
    		if (xPos == players.get(i).getX() && yPos == players.get(i).getY()) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    private int numbOfHumans() {
    	int count = 0;
    	for (int i = 0; i <= players.size() -1; i++) {
    		if (players.get(0) instanceof HumanPlayer) {
    			count++;
    		}
    	}
    	return count;
    }


    /**
     * Processes the player's pickup command, updating the map and the player's gold amount.
     *
     * @return If the player successfully picked-up gold or not.
     */
    protected boolean pickup(HumanPlayer player) {    	
    	if (map.pickupGold(player.getX(), player.getY())) {
    		player.addGold();
    		return true;
    	} else {
    		return false;
    	}
    }

    /**
     * Quits the game, shutting down the application.
     */
    protected void quitGame(HumanPlayer player) {
    	char charAtPlayer = map.getMap()[player.getX()][player.getY()];
    	
    	if (charAtPlayer == 'E') {
    		if(player.getGold() >= map.getGoldRequired()) {
    			System.out.println("WIN");
    		} else {
    			System.out.println("LOSE");
    		}
    	}
    	System.exit(0);
    }
}