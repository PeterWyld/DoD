/**
 * Runs the game with a human player and contains code needed to read inputs.
 *
 */
public class Player {
	private int sight = 0;
	private int xPos;
	private int yPos;
	protected char mapChar = 'A';
    /**
     * Processes the command. It should return a reply in form of a String, as the protocol dictates.
     * Otherwise it should return the string "Invalid".
     *
     * @return : Processed output or Invalid if the @param command is wrong.
     */
	
	public Player(int sight, int xPos, int yPos) {
		this.sight = sight;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
    protected String getNextAction() {
        return null;
    }
    
    protected char[][] see(char[][] map) {
    	char[][] seeMap = new char[2*sight +1][2*sight +1];
    	for (int i = 0; i <= 2*sight; i++) {
    		for (int j = 0; j <= 2*sight; j++) {
    			if (map.length > xPos-sight+i && xPos-sight+i >= 0 &&
    					map[i].length > yPos-sight+j && yPos-sight+j >= 0) {
    				
    				seeMap[i][j] = map[xPos-sight+i][yPos-sight+j];
    			} else {
    				seeMap[i][j] = '+';
    			}
    		}
    	}
    	
    	return seeMap;
    }
    
    protected int getX() {
    	return xPos;
    }
    
    protected int getY() {
    	return yPos;
    }
    
    protected char getMapChar() {
    	return mapChar;
    }
}
