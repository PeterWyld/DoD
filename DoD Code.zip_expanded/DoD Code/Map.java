import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Reads and contains in memory the map of the game.
 *
 */
public class Map {

	/* Representation of the map */
	private char[][] map;
	
	/* Map name */
	private String mapName;
	
	/* Gold required for the human player to win */
	private int goldRequired;
	
	private char[][] playerMap;
	
	/**
	 * Default constructor, creates the default map "Very small Labyrinth of doom".
	 */
	public Map() {
		mapName = "Very small Labyrinth of Doom";
		goldRequired = 2;
		map = new char[][]{
		{'#','#','#','#','#','#','#','#','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','E','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','G','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','G','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','E','.','#'},
		{'#','.','.','.','.','.','.','.','#'},
		{'#','#','#','#','#','#','#','#','#'}
		};
		
		playerMap = new char[map.length][map[0].length];
		
		for(int i = 0; i <= playerMap.length -1; i++) {
			Arrays.fill(playerMap[i], 'X');
		}
	}
	
	/**
	 * Constructor that accepts a map to read in from.
	 * 
	 * @param : The filename of the map file.
	 */
	public Map(String fileName) {
		setMap(fileName);
	}
	
	protected Player addPlayerToMap(Player player) {
		Random rand = new Random();
		int x = 0;
		int y = 0;
		x = rand.nextInt(map.length-1);
		y = rand.nextInt(map[0].length-1);
		if(map[x][y] != '#' && map[x][y] != 'E' && playerMap[x][y] == 'X') {
			playerMap[x][y] = player.getMapChar();
		}
		player.setXY(x, y);
		return player;
	}
	
	public char getCharAtPos(int x, int y, ArrayList<Player> players) {
		char[][] mapWithPlayers = getMapWithPlayers(players);
		return mapWithPlayers[x][y];
	}

    /**
     * @return : Gold required to exit the current map.
     */
    protected int getGoldRequired() {
        return goldRequired;
    }

    /**
     * @return : The map as stored in memory.
     */
    protected char[][] getMap() {
        return map;
    }
    
    /**
     * @return : The name of the current map.
     */
    protected String getMapName() {
        return mapName;
    }


    /**
     * Reads the map from file.
     *
     * @param : Name of the map's file.
     */
    protected void setMap(String fileName) {
    	char[][] newMap = new char[0][0];
    	FileReader fileReader = null;
    	BufferedReader lineReader = null;
		String line = "";
		char[] lineChars;
		int numbOfLines = 0;
		int longestLine = 0;
		String newMapName = "";
		int newGoldRequired = 0;
		boolean valid = true;
    	
    	try {
			fileReader = new FileReader(fileName);
			lineReader = new BufferedReader(fileReader);
					
			if ((line = lineReader.readLine()) != null) {
				newMapName = line;
			} else {
				//send error to user via display
			}
			
			if ((line = lineReader.readLine()) != null) {
				newGoldRequired = Utilities.parseIntDefault(line.substring(line.length() -1), 0);
			} else {
				//send error to user via display
			}
			
			/* First finding number of lines in file (for array size)
			 * then using that to set the y coordinate's Length of newMap
			 * 
			 * During the process of finding the length of the maps longest line
			 * then using that to set the x coordinate's length
			 */
			while ((line = lineReader.readLine()) != null) {
				if (line.length() > longestLine) {
					longestLine = line.length();
					
				}
				numbOfLines++;
			}
			
			newMap = new char[longestLine][numbOfLines];
			
			//resetting the file and line Reader so that they start the file again
			fileReader = new FileReader(fileName);
			lineReader = new BufferedReader(fileReader);
			//go past the first two lines (the name and gold required)
			lineReader.readLine();
			lineReader.readLine();
			
			//characters added to the newMap array in such a way as to make it so that
			//the tiles are in coordinate (Cartesian) format 
			//(so [1][3] would be one across and 3 up from the bottom left corner of the map)
			for(int i = 0; i <= numbOfLines -1; i++) {
				lineChars = lineReader.readLine().toCharArray();
				for(int j = 0; j <= longestLine -1; j++) {
					if (j <= lineChars.length -1) {
						newMap[j][i] = lineChars[j];
					} else {
						newMap[j][i] = '#';
					}
				}
			}
			
			lineReader.close();
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Map file could not be found.");
			//send error message via display
			valid = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	/* 
    	 * if the file was found and there were no IO/FileNotFound Exceptions,
    	 * then the map is changed
    	 */
    	if (valid) {
	    	this.mapName = newMapName;
	    	this.goldRequired = newGoldRequired;
	    	this.map = newMap;
    	}
    }
    
    protected char[][] getMapWithPlayers(ArrayList<Player> players) {
    	char[][] mapWithPlayers= new char[map.length][map[0].length];
    	Player player;
    	for(int i = 0; i <= map.length -1; i++) {
    		mapWithPlayers[i] = map[i].clone();
    	}
    	
    	for(int i = 0; i <= players.size() -1; i++) {
    		player = players.get(i);
    		mapWithPlayers[player.getX()][player.getY()] = player.mapChar;
    	}
    	
    	return mapWithPlayers;
    }
    
    protected boolean pickupGold(int x, int y) {
    	if (map[x][y] == 'G') {
    		map[x][y] = '.';
    		return true;
    	} else {
    		return false;
    	}
    }


}
