

public class Main {
	public static void main(String[] args) {
//		Map map = new Map();
//		map.setMap("Maps\\map.txt");
//		System.out.println(Arrays.deepToString(map.getMap()));
		GameLogic game = new GameLogic();
		game.gameLoop();
		System.exit(0);
	}
}
