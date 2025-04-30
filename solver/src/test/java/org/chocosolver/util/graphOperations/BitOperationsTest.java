package org.chocosolver.util.graphOperations;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BitOperationsTest {
    @Test(groups = "1s")
    public void testGetMax(){
        Assert.assertEquals(BitOperations.getMaxExp(4),2);
    }

    @Test(groups = "1s")
    public void testGetFist(){
        Assert.assertEquals(BitOperations.getFirstExp(7),0);
        Assert.assertEquals(BitOperations.getFirstExp(6),1);
    }

    @Test(groups = "1s")
    public void testPow(){
        Assert.assertEquals(BitOperations.pow(2,7),128);
    }

    @Test(groups = "1s")
    public void testFirstBoth(){
        Assert.assertEquals(BitOperations.getFirstExpInBothXYfromI(27,20,0),4);
    }

    @Test(groups = "1s")
    public void testLCA(){
        Assert.assertEquals(BitOperations.binaryLCA(27,128),128);
    }
}
