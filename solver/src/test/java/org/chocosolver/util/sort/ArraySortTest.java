package org.chocosolver.util.sort;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;

public class ArraySortTest {

    @Test(groups = "1s")
    public void testSwapInt() {
        ArraySort<Integer> sort = new ArraySort<Integer>(5,false,true);
        int[] a = {5,4,3,2,1};
        sort.sort(a, 5, new IntComparator() {
            @Override
            public int compare(int i1, int i2) {
                if(i1 > i2) return 1;
                else if(i1 < i2) return -1;
                else return 0;
            }
        });

        Assert.assertEquals(a[0], 1);
        Assert.assertEquals(a[1], 2);
        Assert.assertEquals(a[2], 3);
        Assert.assertEquals(a[3], 4);
        Assert.assertEquals(a[4], 5);
    }

    @Test(groups = "1s")
    public void testSwapInteger(){
        ArraySort<Integer> sort = new ArraySort<Integer>(5,true,false);
        Integer[] a = {5,4,3,2,1};
        sort.sort(a, 5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        Assert.assertEquals(a[0], 1);
        Assert.assertEquals(a[1], 2);
        Assert.assertEquals(a[2], 3);
        Assert.assertEquals(a[3], 4);
        Assert.assertEquals(a[4], 5);
    }

    @Test(groups = "1s")
    public void testBadSwap() {
        ArraySort<Integer> sort = new ArraySort<Integer>(5,false,true);
        Integer[] a = {5,4,3,2,1};
        Assert.expectThrows(AssertionError.class, () -> sort.sort(a, 5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        }));
    }
}
