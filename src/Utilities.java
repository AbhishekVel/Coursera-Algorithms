import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utilities {

	/**
	 * 
	 * @param location
	 * @return array of all the integers in the file
	 */
	public static int[] loadArrayFromFile(String location, int expectedSize) {
		int[] arr = new int[expectedSize]; 
		Scanner sc = null;
		try {
			sc = new Scanner(new File(location));
			int i = 0;
			while (sc.hasNextLine()) {
				arr[i++] = sc.nextInt();
			}
		} catch (FileNotFoundException io) {
			System.err.println("Error occured while loading data file.");
			System.exit(1);
		} finally {
			if (sc != null) sc.close();
		}
		return arr;
	}
}
