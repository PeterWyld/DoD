import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
//		Map map = new Map();
//		map.setMap("Maps\\map.txt");
//		System.out.println(Arrays.deepToString(map.getMap()));
		ArrayList<int[]> intList = new ArrayList<int[]>();
		intList.add(new int[]{0,1});

		Menu menu = new Menu();
		GameLogic game;
		
		while(!menu.pressedStart()) {
			menu.displayMenu();
		}
		
		if(menu.getMapFile() != null) {
			game = new GameLogic(menu.getMapFile());
		} else {
			game = new GameLogic();
		}
		game.gameLoop();
		System.exit(0);
	}
}
