package memo.basis.everysort;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SortSetTest {
	private static SortSet sortSet = new SortSet();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBubbleSort() {
		SortSet.printArray(SortSet.array);
		int[] array = SortSet.bubbleSort(SortSet.array);
		SortSet.printArray(array);
	}
	
	@Test
	public void testSelectionSort() {
		SortSet.printArray(SortSet.array);
		int[] array = SortSet.selectionSort(SortSet.array);
		SortSet.printArray(array);
	}
	
	@Test
	public void testQuickSort() {
		SortSet.printArray(SortSet.array);
		int[] array = SortSet.quickSort(SortSet.array, 0, SortSet.array.length-1);
		SortSet.printArray(array);
	}

	@Test
	public void testShellSort() {
		SortSet.printArray(SortSet.array);
		int[] array = SortSet.shellSort(SortSet.array);
		SortSet.printArray(array);
	}
}
