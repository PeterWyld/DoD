
public class MapValidator {
	//smallest valid map = 
	//Gold require 0 to 3
	//####
	//#..#
	//#E.#
	//####
	//(each . can be replaced with G)
	public static final char[] validChars = {'#', 'G', 'E', '.'};

	public MapValidator() {
		
	}
	
	public boolean checkMap(char[][] map, int goldRequired) {		
		if (!(isRectangle(map) && validMinDimensions(map) && hasOuterWalls(map) && hasEnoughGold(map, goldRequired) && hasExit(map) &&
				onlyValidChars(map) && hasMinEmptySpace(map))) {
			return false;
		} else {
			return true;
		}
		
		
		
	}
	
	public char[][] forceMapValidity(char[][] map, int goldRequired) throws MapUnfixableException {
		char[][] newMap = null;
		if(!validMinDimensions(map) || !hasMinEmptySpace(map)) {
			throw new MapUnfixableException();
		}
		
		if(!isRectangle(map)) {
			map = forceRectangle(map);
		}
		
		if(!hasOuterWalls(map)) { 
			map = addWalls(map);
		}
		
		if(!hasEnoughGold(map, goldRequired)) {
			addGold(map, goldRequired);
		}
		
		if(!hasExit(map)) {
			addExit(map);
		}
		return newMap;
	}
	
	private char[][] addGold(char[][] map, int goldRequired) throws MapUnfixableException {
		int goldOnMap = 0;
		for(int i = 0; i <= map.length -1; i++) {
			for(int j = 0; j <= map[0].length -1; j++) {
				if(map[i][j] == '.') {
					map[i][j] = 'G';
					goldOnMap++;
				} else if (map[i][j] == 'G') {
					goldOnMap++;
				}
			}
		}
		
		if(goldOnMap < goldRequired) {
			throw new MapUnfixableException();
		} else {
			return map;
		}
	}
	
	private char[][] addExit(char[][] map) throws MapUnfixableException {
		boolean addedExit = false;
		for(int i = 0; i <= map.length -1; i++) {
			for(int j = 0; j <= map[0].length -1 && !addedExit; j++) {
				if(map[i][j] == '.') {
					map[i][j] = 'E';
					addedExit = true;
				}
			}
		}
		
		if(addedExit) {
			return map;
		} else {
			throw new MapUnfixableException();
		}
	}
	
	private char[][] forceRectangle(char[][] map) {
		int longestLine = 0;
		char[][] newMap;
		
		for(int i = 0; i <= map.length -1; i++) {
			if(map[i].length > longestLine) {
				longestLine = map[i].length;
			}
		}
		
		newMap = new char[map.length][longestLine];
		
		for(int i = 0; i <= map.length -1; i++) {
			for(int j = 0; j <= longestLine -1; j++) {
				if(j <= map[j].length -1) {
					newMap[i][j] = map[i][j];
				} else {
					newMap[i][j] = '#';
				}
			}
		}
		
		return newMap;
	}
	
	//Requires that the map is rectangular and has one entry
	private char[][] addWalls(char[][] map) {
		int lineLength = map[0].length + 2;
		char[][] newMap = new char[map.length + 2][lineLength];
		
		// done this way in case of non-rectangular map
		for(int i = 0; i <= lineLength -1; i++) {
			newMap[0][i] = '#';
			newMap[newMap.length -1][i] = '#';
		}
		
		
		for(int i = 1; i <= newMap.length -2; i++) {
			newMap[i][0] = '#';
			newMap[i][lineLength -1] = '#';
			for(int j = 1; j <= lineLength -2; j++) {
				newMap[i][j] = map[i-1][j-1]; 
			}
		}
		return newMap;
	}
	
	private boolean hasMinEmptySpace(char[][] map) {
		int emptySpace = 0;
		for(int i = 0; i <= map.length -1; i++) {
			for(int j = 0; j <= map[0].length -1; j++) {
				if(map[i][j] == '.' || map[i][j] == 'G') {
					emptySpace++;
				}
			}
		}
		
		if(emptySpace >= 2) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean onlyValidChars(char[][] map) {
		boolean onlyValidChars = true;
		boolean isValidChar;
		for(int i = 0; i <= map.length -1; i++) {
			for(int j = 0; j <= map[0].length -1; j++) {
				isValidChar = false;
				for(int k = 0; k <= validChars.length -1; k++) {
					if(map[i][j] == validChars[k]) {
						isValidChar = true;
					}
				}
				if(!isValidChar) {
					onlyValidChars = false;
				}
			}
		}
		return onlyValidChars;
	}
	
	private boolean isRectangle(char[][] map) {
		boolean isRectangle = true;
		int firstLineLength = map[0].length;
		for(int i = 0; i <= map.length -1; i++) {
			if(map[i].length != firstLineLength) {
				isRectangle = false;
			}
		}
		return isRectangle;
	}
	
	private boolean hasExit(char[][] map) {
		boolean hasExit = false;
		for(int i = 0; i <= map.length -1; i++) {
			for(int j = 0; j <= map[0].length -1; j++) {
				if(map[i][j] == 'E') {
					hasExit = true;
				}
			}
		}
		return hasExit;
	}
	
	private boolean hasEnoughGold(char[][] map, int goldRequired) {
		int goldCount = 0;
		for(int i = 0; i <= map.length -1; i++) {
			for(int j = 0; j <= map[0].length -1; j++) {
				if(map[i][j] == 'G') {
					goldCount++;
				}
			}
		}
		return (goldRequired <= goldCount);
	}
	
	private boolean validMinDimensions(char[][] map) {
		//min map dimension = 2x2
		if (map.length >= 4 && map[0].length >= 4) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean hasOuterWalls(char[][] map) {
		boolean valid = true;
		for(int i = 0; i <= map[0].length -1; i++) {
			if(map[0][i] != '#' || map[map.length-1][i] != '#') {
				valid = false;
			}
		}
		for (int i = 0; i <= map.length - 1; i++) {
			if(map[i][0] != '#' || map[i][map.length -1] != '#') {
				valid = false;
			}
		}
		return valid;
	}
}
