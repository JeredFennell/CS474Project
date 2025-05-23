/*
 * This file is part of choco-solver, http://choco-solver.org/
 *
 * Copyright (c) 2025, IMT Atlantique. All rights reserved.
 *
 * Licensed under the BSD 4-clause license.
 *
 * See LICENSE file in the project root for full license information.
 */
package org.chocosolver.solver.objective;

import org.chocosolver.solver.ResolutionPolicy;

import java.io.Serializable;

/**
 * interface to monitor bounds.
 *
 * @author Jean-Guillaume Fages, Charles Prud'homme, Arnaud Malapert
 */
public interface IBoundsManager extends Serializable {

    /**
     * @return the ResolutionPolicy of the problem
     */
    ResolutionPolicy getPolicy();

    /**
     * @return true iff the problem is an optimization problem
     */
    default boolean isOptimization() {
        return true;
    }

    /**
     * @return the best lower bound computed so far
     */
    Number getBestLB();

    /**
     * @return the best upper bound computed so far
     */
    Number getBestUB();

    /**
     * @return the best solution value found so far (returns the initial bound if no solution has been found yet)
     */
    Number getBestSolutionValue();

    /**
     * Reset best bounds to the initial domain of the objective variables
     */
    default void resetBestBounds(){}
}