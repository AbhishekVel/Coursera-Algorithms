
public class MergeSort {

	public static void main(String[] args) {
		int[] unsorted = {5, 2, 1, 6, 7, 2, 3, 1, 4, 7};
		int[] sorted = mergeSort(unsorted);
		for (int num: sorted)
			System.out.println(num);
	}
	
	private static int[] mergeSort(int[] unsorted) {
		if (unsorted.length <= 1)
			return unsorted;
		
		int[] firstHalf = new int[unsorted.length/2];
		for (int i = 0; i < firstHalf.length;i++) {
			firstHalf[i] = unsorted[i];
		}
		
		
		
		int[] secondHalf = new int[unsorted.length - (unsorted.length/2)];
		int k = unsorted.length/2;
		for (int i = 0; i < secondHalf.length;i++) {
			secondHalf[i] = unsorted[k++];
		}
		
		firstHalf = mergeSort(firstHalf);
		secondHalf = mergeSort(secondHalf);
		
		int[] sorted = new int[unsorted.length];
		int i = 0, j = 0;
		for (int pos = 0; pos < sorted.length; pos++) {
			if (i < firstHalf.length && j < secondHalf.length) {
				if (firstHalf[i] < secondHalf[j]) {
					sorted[pos] = firstHalf[i];
					i++;
				} else if (secondHalf[j] <= firstHalf[i]) { // no need for this comparison, but written for readability purposes
					sorted[pos] = secondHalf[j];
					j++;
				}
			} else {
				if (i < firstHalf.length)
					sorted[pos] = firstHalf[i++];
				else if (j < secondHalf.length) // no need for this comparison either for same reason as ^
					sorted[pos] = secondHalf[j++];
			}
		}
		
		return sorted;
	}
	
}
