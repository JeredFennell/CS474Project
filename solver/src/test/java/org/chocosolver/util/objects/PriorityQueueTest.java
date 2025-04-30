package org.chocosolver.util.objects;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PriorityQueueTest {
    @Test(groups = "1s")
    public void testPriorityQueue() {
        PriorityQueue q = new PriorityQueue(6);
        q.addElement(10,0);
        q.addElement(5,0);
        q.addElement(2,1);
        q.addElement(3,5);
        q.addElement(7,-1);
        Assert.assertEquals(q.pop(),7);
        Assert.assertEquals(q.pop(),10);
        Assert.assertEquals(q.pop(),5);
        Assert.assertEquals(q.pop(),2);
        Assert.assertEquals(q.pop(),3);
    }
}
