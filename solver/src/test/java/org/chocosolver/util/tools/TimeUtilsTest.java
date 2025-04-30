package org.chocosolver.util.tools;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TimeUtilsTest {

    @Test
    public void convert_basic() {
        long expected = 9000;
        long result = TimeUtils.convertInSeconds("2h30m");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convert_half_second() {
        long expected = 11;
        long result = TimeUtils.convertInSeconds("11.5s");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convert_59m59s() {
        long expected = 100799;
        long result = TimeUtils.convertInSeconds("1d3h59m59s");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convert_0_duration() {
        // it accepts 0 as an input, but NOT 0s or 0m on their own -- is that how it should work?
        // it also accepts 0m30s
        long expected = 0;
        long result = TimeUtils.convertInSeconds("0");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convert_0m30s() {
        long expected = 30;
        long result = TimeUtils.convertInSeconds("0m30s");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convert_neg() {
        long expected = 90;
        long result = TimeUtils.convertInSeconds("-1m30s");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convert_syntax_error() {
        long expected = 0;
        long result = TimeUtils.convertInSeconds("20");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void convert_out_of_order() {
        long expected = 200;
        long result = TimeUtils.convertInSeconds("20s3m0h");
        Assert.assertEquals(result, expected);
    }

    // null duration is not handled in code
    @Test
    public void convertToSecondsNull() {
        long result = TimeUtils.convertInSeconds(null);
    }
}
