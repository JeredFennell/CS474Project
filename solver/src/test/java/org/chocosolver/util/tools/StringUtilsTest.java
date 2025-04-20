package org.chocosolver.util.tools;

import dk.brics.automaton.RegExp;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StringUtilsTest {

    @Test
    public void testPadRight() {
        String expected = "M#########";
        String result = StringUtils.pad("M", 10, "#");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPadLeft() {
        String expected = "#########M";
        String result = StringUtils.pad("M", -10, "#");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPadEmpty() {
        String expected = "M";
        String result = StringUtils.pad("M", 1, "#");
        Assert.assertEquals(result, expected);
    }

}
