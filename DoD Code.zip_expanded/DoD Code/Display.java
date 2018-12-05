
public class Display {

	public void displayMap(char[][] map) {
    	for (int y = 0; y <= map[0].length -1; y++) {
    		for (int x = 0; x <=  map.length -1; x++) {
    			System.out.print(map[x][y]);
    		}
    		System.out.println();
    	}
	}
	
	public void dispSuccessOrFail(boolean isSuccessful) {
		if(isSuccessful) {
			System.out.print("SUCCESS");
		} else {
			System.out.print("FAIL");
		}
	}
}
