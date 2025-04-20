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
    public void testPad1len() {
        String expected = "M";
        String result = StringUtils.pad("M", 1, "#");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPad0len() {
        String expected = "M";
        String result = StringUtils.pad("M", 0, "#");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPad0lenEmpty() {
        String expected = "";
        String result = StringUtils.pad("", 0, "#");
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPadLongStr() {
        String expected = "Mxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        String result = StringUtils.pad("Mxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
                60, "#");
        Assert.assertEquals(result, expected);
    }

//    @Test
//    public void testPadNullStr() {
//        String result = StringUtils.pad(null, 10, "#");
//    }

    // TODO I think this is a bug - you can add 'null' as padding. null should not be accepted.
    @Test
    public void testPadNullPad() {
        String expected = "M";
        String result = StringUtils.pad("M", -10, null);
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPadNullPad2() {
        String expected = "M";
        String result = StringUtils.pad("M",2 , null);
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testPadEmpty() {
        String expected = "M";
        String result = StringUtils.pad("M", -10, "");
        Assert.assertEquals(result, expected);
    }
}
