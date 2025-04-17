/*
 * This file is part of choco-solver, http://choco-solver.org/
 *
 * Copyright (c) 2025, IMT Atlantique. All rights reserved.
 *
 * Licensed under the BSD 4-clause license.
 *
 * See LICENSE file in the project root for full license information.
 */
package org.chocosolver.util.tools;

import org.chocosolver.solver.expression.continuous.arithmetic.RealIntervalConstant;
import org.chocosolver.util.objects.RealInterval;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * <br/>
 *
 * @author Charles Prud'homme
 * @since 08/07/2020
 */
public class RealUtilsTest {

    @DataProvider
    public Object[][] forDiv() {
        return new Object[][]{
                {1., 3., 1., 3., 1. / 3., 3.}, // ++
                {-3., -1., 1., 3., -3, -1. / 3.}, // -+
                {1., 3., -3., -1., -3., -1. / 3.}, // +-
                {-3., -1., -3., -1., 1. / 3., 3.}, // --
                {-3., 2., 1., 3., -3., 2.}, // 0+
                {-3., 2., -3., -1., -2., 3.}, // 0-
                {-3., 2., -1., 4., Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY},  // 00
                {-2., 3., -4., 1., Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY}, // 00
                {.0, .2, -2., 2., Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY}, // +0
                {-.2, 0., -2., 2., Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY},// -0
        };
    }

    @Test(groups = "1s", dataProvider = "forDiv")
    public void testOdiv1(double xl, double xh, double yl, double yh, double rl, double rh) {
        RealInterval x = new RealIntervalConstant(xl, xh);
        RealInterval y = new RealIntervalConstant(yl, yh);
        RealInterval r = RealUtils.odiv(x, y);
        Assert.assertEquals(RealUtils.prevFloat(rl), r.getLB());
        Assert.assertEquals(RealUtils.nextFloat(rh), r.getUB());
    }

    @Test(groups = "1s", expectedExceptions = ArithmeticException.class)
    public void testOdiv2() {
        testOdiv1(1., 2., 0., 0., Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    @DataProvider
    public Object[][] forMul() {
        return new Object[][]{
                {1., 3., 1., 3., 1., 9.},
                {-3., -1., 1., 3., -9, -1.},
                {1., 3., -1., 3., -3., 9.},
                {1., 3., -3., -1., -9., -1.},
                {-3., -1., -3., -1., 1., 9.},
                {-3., -1., -3., 1., -3., 9.},
                {-3., 2., 1., 3., -9., 6.},
                {-3., 2., -3., -1., -6, 9.},
                {-3., 2., -1., 4., -12., 8},
                {-2., 3., -4., 1., -12., 8},
                {0., 0., -4., 1., RealUtils.nextFloat(-0.), RealUtils.prevFloat(0.)},
                {-2., 3., 0., 0., RealUtils.nextFloat(-0.), RealUtils.prevFloat(0.)},

        };
    }

    @Test(groups = "1s", dataProvider = "forMul")
    public void testMul1(double xl, double xh, double yl, double yh, double rl, double rh) {
        RealInterval x = new RealIntervalConstant(xl, xh);
        RealInterval y = new RealIntervalConstant(yl, yh);
        RealInterval r = RealUtils.mul(x, y);
        Assert.assertEquals(RealUtils.prevFloat(rl), r.getLB());
        Assert.assertEquals(RealUtils.nextFloat(rh), r.getUB());
    }

    /**
     * Test added to cover the odiv_wrt method when interval y does not contain 0.
     */
    @Test
    public void odivwrt_nonzero() {
        // dividing intervals, y does not contain 0
        RealInterval x = new RealIntervalConstant(1, 2);
        RealInterval y = new RealIntervalConstant(1, 2);
        RealInterval r = new RealIntervalConstant(-10, 10);
        RealInterval result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), RealUtils.prevFloat(0.5));
        Assert.assertEquals(result.getUB(), RealUtils.nextFloat(2));

        x = new RealIntervalConstant(-4, -2);
        y = new RealIntervalConstant(1, 2);
        r = new RealIntervalConstant(-10, 10);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), RealUtils.prevFloat(-4));
        Assert.assertEquals(result.getUB(), RealUtils.nextFloat(-1));

        x = new RealIntervalConstant(1, 2);
        y = new RealIntervalConstant(-2, -1);
        r = new RealIntervalConstant(-10, 10);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), RealUtils.prevFloat(-2));
        Assert.assertEquals(result.getUB(), RealUtils.nextFloat(-0.5));

        x = new RealIntervalConstant(-1, 1);
        y = new RealIntervalConstant(10, 20);
        r = new RealIntervalConstant(-10, 10);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), RealUtils.prevFloat(-0.1));
        Assert.assertEquals(result.getUB(), RealUtils.nextFloat(0.1));
    }

    /**
     * Test added to cover the odiv_wrt method when interval y contains 0 and x is positive.
     */
    @Test
    public void odivwrt_y0_xpos() {
        // dividing intervals, y contains 0 and x is positive.
        // res outside gap, negative side
        RealInterval x = new RealIntervalConstant(2, 4);
        RealInterval y = new RealIntervalConstant(-1, 2);
        RealInterval r = new RealIntervalConstant(-10, -5);
        RealInterval result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), -10);
        Assert.assertEquals(result.getUB(), -5);

        // res outside gap, positive side
        x = new RealIntervalConstant(2, 4);
        y = new RealIntervalConstant(-1, 2);
        r = new RealIntervalConstant(5, 10);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), 5);
        Assert.assertEquals(result.getUB(), 10);

        // res entirely inside gap
        x = new RealIntervalConstant(2, 4);
        y = new RealIntervalConstant(-1, 2);
        r = new RealIntervalConstant(-1, 0.5);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), RealUtils.prevFloat(1));
        Assert.assertEquals(result.getUB(), RealUtils.nextFloat(-2));

        // res overlaps gap on right
        x = new RealIntervalConstant(2, 4);
        y = new RealIntervalConstant(-1, 2);
        r = new RealIntervalConstant(-10, 0.5);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), -10);
        Assert.assertEquals(result.getUB(), RealUtils.nextFloat(-2));

        // res overlaps gap on left
        x = new RealIntervalConstant(2, 4);
        y = new RealIntervalConstant(-1, 2);
        r = new RealIntervalConstant(-1, 10);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), RealUtils.prevFloat(1));
        Assert.assertEquals(result.getUB(), 10);

        // res spans gap
        x = new RealIntervalConstant(2, 4);
        y = new RealIntervalConstant(-1, 2);
        r = new RealIntervalConstant(-10, 10);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), -10);
        Assert.assertEquals(result.getUB(), 10);

        // x = 0
        x = new RealIntervalConstant(0, 0);
        y = new RealIntervalConstant(-1, 1);
        r = new RealIntervalConstant(-1, 1);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), -1);
        Assert.assertEquals(result.getUB(), 1);
    }

    /**
     * Test added to cover the odiv_wrt method when interval y contains 0 and x is negative.
     */
    @Test
    public void odivwrt_y0_xneg() {
        // dividing intervals, y contains 0 and x is negative.
        // res outside gap, negative side
        RealInterval x = new RealIntervalConstant(-4, -2);
        RealInterval y = new RealIntervalConstant(-1, 2);
        RealInterval r = new RealIntervalConstant(-10, -5);
        RealInterval result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), -10);
        Assert.assertEquals(result.getUB(), -5);

        // res outside gap, positive side
        x = new RealIntervalConstant(-4, -2);
        y = new RealIntervalConstant(-1, 2);
        r = new RealIntervalConstant(5, 10);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), 5);
        Assert.assertEquals(result.getUB(), 10);

        // res entirely inside gap
        x = new RealIntervalConstant(-4, -2);
        y = new RealIntervalConstant(-1, 2);
        r = new RealIntervalConstant(0, 1);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), RealUtils.nextFloat(2));
        Assert.assertEquals(result.getUB(), RealUtils.nextFloat(-1));

        // res spans gap
        x = new RealIntervalConstant(-4, -2);
        y = new RealIntervalConstant(-1, 2);
        r = new RealIntervalConstant(-10, 10);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), -10);
        Assert.assertEquals(result.getUB(), 10);
    }

    /**
     * Test added to cover the odiv_wrt method when interval y contains 0 and interval x contains 0.
     */
    @Test
    public void odivwrt_y0_x0() {
        RealInterval x = new RealIntervalConstant(-1, 1);
        RealInterval y = new RealIntervalConstant(-1, 1);
        RealInterval r = new RealIntervalConstant(-10, 10);
        RealInterval result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), -10);
        Assert.assertEquals(result.getUB(), 10);

        x = new RealIntervalConstant(-1, 1);
        y = new RealIntervalConstant(-1, 1);
        r = new RealIntervalConstant(5, 10);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), 5);
        Assert.assertEquals(result.getUB(), 10);

        x = new RealIntervalConstant(-1, 1);
        y = new RealIntervalConstant(-1, 1);
        r = new RealIntervalConstant(-10, 5);
        result = RealUtils.odiv_wrt(x,y,r);
        Assert.assertEquals(result.getLB(), -10);
        Assert.assertEquals(result.getUB(), 5);
    }

}