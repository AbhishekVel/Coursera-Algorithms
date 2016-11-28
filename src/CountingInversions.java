import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Abhishek Velayudham
 *
 */

public class CountingInversions {

	// algorithm uses merge sort algorithm 
	public static void main(String[] args) {
		//int[] array = {3, 1, 4, 5, 2, 6, 3};// test case
		//System.out.println(countInversions(array));
		int[] array = loadArrayFromFile("data/IntegerArray");
		System.out.println(countInversions(array));
		//System.out.println(getInvCount(array, array.length));
	}
	

	
	
	/**
	 * 
	 * @param array
	 * @return # of inversions
	 */
	public static long countInversions(int[] array) {
		if (array == null || array.length <= 1)
			return 0;
		
		
		int[] firstHalf = new int[array.length/2];
		for (int i = 0; i < firstHalf.length; i++)
			firstHalf[i] = array[i];	
		
		int[] secondHalf = new int[array.length - array.length/2];
		int k = array.length/2;
		for (int i = 0; i < secondHalf.length;i++)
			secondHalf[i] = array[k++];
	
		long x = countInversions(firstHalf);
		long y = countInversions(secondHalf);
		
		long z = countSplitInversions(MergeSort.mergeSort(firstHalf), MergeSort.mergeSort(secondHalf));
		
		return x+y+z;
	}
	
	/**
	 * 
	 * @param firstHalf
	 * @param secondHalf
	 * @return number of inversions
	 */
	private static long countSplitInversions(int[] firstHalf, int[] secondHalf) {
		int i = 0, j = 0;
		long inversions = 0;
		
		for (int pos = 0; pos < firstHalf.length + secondHalf.length; pos++) {
			if (i < firstHalf.length && j < secondHalf.length) {
				if (firstHalf[i] < secondHalf[j]) {
					i++;
				} else if (secondHalf[j] <= firstHalf[i]) { // no need for this comparison, but written for readability purposes
					j++;
					inversions += firstHalf.length - i;
				}
			}
		}
		//System.out.println(inversions);
		return inversions;
	}
	
	
	/**
	 * Inefficient way of getting the number of inversions
	 * @param array
	 * @param array's length
	 * @return inversions count
	 */
	private static long countInversionsNonRecursive(int arr[], int n) {
	  long inversions = 0;
	  for (int i = 0; i < n - 1; i++)
	    for (int j = i+1; j < n; j++)
	      if (arr[i] > arr[j])
	        inversions++;
	  return inversions;
	}
	
	
	/**
	 * 
	 * @param location
	 * @return array of all the integers in the file
	 */
	private static int[] loadArrayFromFile(String location) {
		int[] arr = new int[100_000]; // expected input size is 100,000
		try {
			Scanner sc = new Scanner(new File(location));
			int i = 0;
			while (sc.hasNextLine()) {
				arr[i++] = sc.nextInt();
			}
		} catch (FileNotFoundException io) {
			System.err.println("Error occured while loading data file.");
			System.exit(1);
		}
		return arr;
	}
	
}
