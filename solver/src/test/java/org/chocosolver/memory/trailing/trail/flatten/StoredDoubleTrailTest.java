package org.chocosolver.memory.trailing.trail.flatten;

import org.chocosolver.memory.trailing.EnvironmentTrailing;
import org.chocosolver.memory.trailing.StoredDouble;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoredDoubleTrailTest {
    @Test(groups = "1s")
    public void testStoredDoubleTrail() {
        StoredDoubleTrail trail = new StoredDoubleTrail(5,5,2);
        trail.worldPush(0);
        trail.worldPush(1);
        trail.worldPop(1);
        trail.worldPop(0);
        Assert.expectThrows(AssertionError.class, () -> trail.worldPop(0));
    }

    @Test(groups = "1s")
    public void testResizeUpdate() {
        StoredDoubleTrail trail = new StoredDoubleTrail(5,5,2);
        trail.savePreviousState(new StoredDouble(new EnvironmentTrailing(),0.5),0.5,0);
        trail.savePreviousState(new StoredDouble(new EnvironmentTrailing(),0.5),0.5,1);
        trail.savePreviousState(new StoredDouble(new EnvironmentTrailing(),0.5),0.5,2);
        trail.savePreviousState(new StoredDouble(new EnvironmentTrailing(),0.5),0.5,3);
        trail.savePreviousState(new StoredDouble(new EnvironmentTrailing(),0.5),0.5,4);
    }

    @Test(groups = "1s")
    public void testWorldComit() {
        StoredDoubleTrail trail = new StoredDoubleTrail(5,5,2);
        trail.worldPush(0);
        trail.worldPush(1);
        trail.worldPush(2);
        trail.worldPush(3);
        trail.worldPush(4);
        trail.worldPush(5);
        trail.savePreviousState(new StoredDouble(new EnvironmentTrailing(),0.5),0.5,0);
        trail.savePreviousState(new StoredDouble(new EnvironmentTrailing(),0.5),0.5,1);
        trail.savePreviousState(new StoredDouble(new EnvironmentTrailing(),0.5),0.5,2);
        trail.worldCommit(0);
    }
}
