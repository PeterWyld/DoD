import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private int menuState = 0;
	private File mapFile = null;
	private boolean starting = false;

	public Menu() {

	}

	public void displayMenu() {
		switch (menuState) {
		case 0:
			displayMainMenu();
			break;
		case 1:
			displayMapMenu();
			break;
		}
	}

	private void displayMainMenu() {
		final String[] mainOptions = { "Start Game", "Select Map", "Exit" };
		printOptions(mainOptions);
		switch (getIntInput()) {
		case 0:
			starting = true;
			break;
		case 1:
			menuState = 1;
			break;
		case 3:
			System.exit(0);
		}
	}

	private void displayMapMenu() {
		File[] maps = (new File("Maps")).listFiles();
		String[] options = new String[maps.length + 1];
		int input = 0;
		String line = "";
		BufferedReader lineReader;

		for (int i = 0; i <= maps.length - 1; i++) {
			try {
				lineReader = new BufferedReader(new FileReader(maps[i]));
				line = lineReader.readLine();
				if (line.length() >= 6) {
					options[i] = line.substring(5);
				} else {
					options[i] = "(Incorrect Formatting) " + line;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {

			}
		}
		options[maps.length] = "Back to Menu";
		printOptions(options);
		input = getIntInput();
		if (input == options.length - 1) {
			menuState = 0;
		} else {
			try {
				lineReader = new BufferedReader(new FileReader(maps[input]));
				while ((line = lineReader.readLine()) != null) {
					System.out.println(line);
				}
				printOptions(new String[] { "Yes", "No" });
				lineReader.close();
				if (getIntInput() == 0) { // Yes
					menuState = 0;
					mapFile = maps[input];
				}
			} catch (FileNotFoundException e) {
				System.out.println("File could not be found");
			} catch (IOException e) {
				System.out.println("Input/Output error has occured");
			}
		}
	}

//	private void displayMapAdder() {
//		FileWriter fileWriter;
//		BufferedWriter writer;
//		String line = "";
//		File file;
//		
//		try {
//			System.out.println("Please type in the name of the map");
//			line = reader.readLine();
//			while() {
//				
//			}
//			System.out.println();
//			System.out.println("Please, write out/paste in, the map on the console and write DONE (case sensitive) on a newline to finish");
//			while(!(line = reader.readLine()).equals("DONE")) {
//				
//			}
//		} catch (IOException e) {
//			
//		}
//	}

	private int getIntInput() {
		boolean gotInt = false;
		int output = 0;
		System.out.println("please input number for the desired option");
		while (!gotInt) {
			System.out.println("Input :");
			try {
				output = Integer.parseInt(reader.readLine());
				gotInt = true;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			} catch (IOException e) {
				System.out.println("IOException has occured");
				e.printStackTrace();
				System.exit(0);
			}
		}
		return output;
	}

	private void printOptions(String[] options) {
		for (int i = 0; i <= options.length - 1; i++) {
			System.out.println(i + "> " + options[i]);
		}
	}

	public boolean pressedStart() {
		return starting;
	}

	public File getMapFile() {
		return mapFile;
	}
}
