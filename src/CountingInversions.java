import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Abhishek Velayudham
 *
 */

public class CountingInversions {

	public static void main(String[] args) {
		//int[] array = {3, 1, 4, 5, 2, 6, 3};// test case
		//System.out.println(countInversions(array));
		int[] array = Utilities.loadArrayFromFile("data/IntegerArray", 100_000);
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
		
		// algorithm uses merge sort algorithm 
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
		return inversions;
	}
	
	
	/**
	 * Inefficient way of getting the number of inversions (Time complexity is n^2)
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
	
	
	
	
}
