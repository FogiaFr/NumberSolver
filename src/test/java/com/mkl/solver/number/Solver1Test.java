package com.mkl.solver.number;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author MKL.
 */
public class Solver1Test {
    @Test
    public void testParamResult() {
        Assert.assertTrue(Solver1.solve(1, 1).size() > 0);
        Assert.assertFalse(Solver1.solve(2, 1).size() > 0);
        Assert.assertFalse(Solver1.solve(1, 2).size() > 0);
    }

    @Test
    public void testWith2Params() {
        Assert.assertTrue(Solver1.solve(4, 2, 2).size() > 0);
        Assert.assertTrue(Solver1.solve(1, 2, 2).size() > 0);
        Assert.assertFalse(Solver1.solve(3, 2, 2).size() > 0);
        Assert.assertFalse(Solver1.solve(5, 2, 2).size() > 0);
    }

    @Test
    public void testReallyComplex() {
        Assert.assertTrue(Solver1.solve(459, 1, 2, 3, 4, 5, 9).size() > 0);
        Assert.assertFalse(Solver1.solve(458, 1, 2, 3, 4, 5, 9).size() > 0);
    }
}
