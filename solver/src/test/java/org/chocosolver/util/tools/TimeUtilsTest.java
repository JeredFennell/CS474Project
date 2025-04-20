package org.chocosolver.util.tools;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TimeUtilsTest {

    @Test
    public void convertToSeconds1() {
        long expected = 9000;
        long result = TimeUtils.convertInSeconds("2h30m");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convertToSeconds2() {
        long expected = 11;
        long result = TimeUtils.convertInSeconds("11.5s");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convertToSeconds3() {
        long expected = 100799;
        long result = TimeUtils.convertInSeconds("1d3h59m59s");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convertToSeconds4() {
        // it accepts 0 as an input, but NOT 0s or 0m on their own -- is that how it should work?
        // it also accepts 0m30s
        long expected = 0;
        long result = TimeUtils.convertInSeconds("0");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convertToSeconds5() {
        long expected = 30;
        long result = TimeUtils.convertInSeconds("0m30s");
        Assert.assertEquals(result, expected);
    }
}
