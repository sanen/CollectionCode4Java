package org.young.algorithm.sort;

import org.junit.Test;
import org.young.util.Utils;

public class InsertionSortTest {

	private int[] arr = new int[]{5, 2, 4, 6, 1, 3};
	
	@Test
	public void testSortByAsc() {
		InsertionSort.sortByAsc(arr);
		Utils.printArray(arr);
	}
	
	@Test
	public void testSortByDesc() {
		InsertionSort.sortByDesc(arr);
		Utils.printArray(arr);
	}

}