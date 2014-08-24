package memo.basis.everysort;

public class SortSet {

	/**
	 * sort array
	 */
	public static int[] array = {42,70,29,99,66,93,39,58};

	/**
	 * print mothod
	 */
	public static void printArray(int[] array){
		System.out.println("------------the array is------------");
		for(int arr : array){
			System.out.print(" "+ arr);
		}
		System.out.println();
	} 
	
	
	/**
	 * 冒泡排序法
	 */
	public static int[] bubbleSort(int[] array){
		for(int i=0; i<array.length;i++){
			for(int j=i+1; j<array.length; j++){
				if(array[i]>array[j]){
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp; 
				}
			}
		}
		return array;  
	}
	
	
	/**
	 * 选择排序法
	 */
	public static int[] selectionSort(int[] array){
		for(int i=0; i<array.length;i++){
			int k=i;
			for(int j=i+1; j<array.length; j++){
				if(array[k]>array[j]){
					k = j; 
				}
			}
			
			if(i!=k){
				int temp = array[i];
				array[i] = array[k];
				array[k] = temp; 
			}
		}
		return array;  
	}
	

	/**
	 * 快速排序法
	 */
	public static int[] quickSort(int[] array, int left, int right){
		if (left < right) {
			int key = array[left];
			int low = left;
			int high = right;
			while (low < high) {
				while (low < high && array[high] > key) {
					high--;
				}
				array[low] = array[high];
				while (low < high && array[low] < key) {
					low++;
				}
				array[high] = array[low];
			}
			array[low] = key;
			quickSort(array, left, low - 1);
			quickSort(array, low + 1, right);
		}
		return array;
	}
	
	/**
	 * 希尔排序法
	 * (步长比较，小步长再比较)
	 */
	public static int[] shellSort(int[] R){
		int gap = array.length / 2;
		int temp;
		while(gap>0){
			for(int i=gap; i<array.length; i++){
				temp = array[i];
				int j = i-gap;
				while(j>=0 && temp<array[j]){
					array[j+gap] = array[j];
					j = j-gap;		//!!
				}
				array[j+gap] = temp;
			}
			gap  = gap/2;
		}
		return array;
	}
	
}
