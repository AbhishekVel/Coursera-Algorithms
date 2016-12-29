
public class QuickSort {


	
	public static int comparisons = 0;
	
	public static int[] applyQuickSort(int[] array, int leftIndex, int rightIndex) {
		int n = rightIndex - leftIndex;
		
		if (n < 1)
			return array;
		
		comparisons += n;
		// pivot point
		//int partitionIndex = chooseMedianPivot(array, leftIndex, rightIndex); 
		int partitionIndex = rightIndex;
		int i = leftIndex + 1;// position between the two partitioned arrays
		
		swap(array, partitionIndex, leftIndex);
		partitionIndex = leftIndex;
		
		for (int pos = leftIndex + 1; pos < rightIndex+1;pos++) {
			if (array[pos] < array[partitionIndex]) {
				swap(array, i, pos);
				i++;
			}
		}
		
		swap(array, i-1, partitionIndex);
		applyQuickSort(array, leftIndex, i-2);
		applyQuickSort(array, i, rightIndex);
		
		return array;
	}
	
	private static int chooseMedianPivot(int[] array, int leftIndex, int rightIndex) {
		double doubleMidIndex = (rightIndex+leftIndex)/2;
		int midIndex = (int) ((doubleMidIndex / 10 != 0) ? doubleMidIndex : doubleMidIndex+1);

		if ((array[leftIndex] > array[rightIndex] && array[leftIndex] < array[midIndex]) || 
				(array[leftIndex] > array[midIndex] && array[leftIndex] < array[rightIndex]))
			return leftIndex;
		else if ((array[midIndex] > array[leftIndex] && array[midIndex] < array[rightIndex]) || 
				(array[midIndex] > array[rightIndex] && array[midIndex] < array[leftIndex]))
			return midIndex;
		else
			return rightIndex;
	}
	
	private static int[] swap(int[] array, int firstIndex, int secondIndex) {
		int temp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;
		return array;
	}
	
	
	
	public static void main(String[] args) {
		/*int[] array = {4, 8, 7, 3, 1, 6, 2}; // test case
		for (int num : applyQuickSort(array, 0, array.length-1)) {
			System.out.print(num + ", ");
		}*/
		
		int[] array = Utilities.loadArrayFromFile("./data/QuickSort", 10_000);
		for (int num : applyQuickSort(array, 0, array.length-1)) {
			System.out.print(num + ", ");
		}
		System.out.println();
		System.out.println("Comparisons: " + comparisons);
	}

	
	
}
