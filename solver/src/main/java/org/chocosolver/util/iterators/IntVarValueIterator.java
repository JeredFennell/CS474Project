/*
 * This file is part of choco-solver, http://choco-solver.org/
 *
 * Copyright (c) 2025, IMT Atlantique. All rights reserved.
 *
 * Licensed under the BSD 4-clause license.
 *
 * See LICENSE file in the project root for full license information.
 */
package org.chocosolver.util.iterators;

import java.util.NoSuchElementException;
import org.chocosolver.solver.variables.IntVar;

import java.util.Iterator;

/**
 * Object to iterate over an IntVar values using
 * <code>
 *     for(int value:var){
 *         ...
 *     }
 * </code>
 * that is equivalent to
 * <code>
 *     int ub = var.getUB();
 *     for(int value = var.getLB(); values <= ub; value = var.nextValue(value)){
 *         ...
 *     }
 * </code>
 *
 * @author Jean-Guillaume Fages
 */
public class IntVarValueIterator implements Iterator<Integer> {

	/**
	 * Variable to iterate on
	 */
	private final IntVar var;
    /**
     * current returned value
     */
	private int value;
    /**
     * upper bound of {@link #var}
     */
	private int ub;

	/* --- Sequential properties ---
		Rule 1: After initialization, reset() must be called before hasNext() or next() can be called.
		Rule 2: A call to next() must be preceded by a call to hasNext() that returns true.
	 */
	private boolean resetCalled;
	private boolean hasNextVal;

	/**
	 * Creates an object to iterate over an IntVar values using
	 * <code>
	 *     for(int value:var){
	 *         ...
	 *     }
	 * </code>
	 * that is equivalent to
	 * <code>
	 *     int ub = var.getUB();
	 *     for(int value = var.getLB(); values <= ub; value = var.nextValue(value)){
	 *         ...
	 *     }
	 * </code>
	 * @param v an integer variables
	 */
	public IntVarValueIterator(IntVar v){
		this.var = v;
		resetCalled = false;
		hasNextVal = false;
	}

	/**
	 * Reset iteration (to avoid creating a new IntVarValueIterator() for every iteration)
	 * Stores the current upper bound
	 */
	public void reset(){
		value = var.getLB()-1;
		ub = var.getUB();
		resetCalled = true;
	}

	@Override
	public boolean hasNext() {
		// --- Assert reset has been called before this ---
		assert resetCalled: "IntVarValueIterator.hasNext() error: reset has not been called yet.";
		// -------
		hasNextVal = var.nextValue(value) <= ub;
		return hasNextVal;
	}

	@Override
	public Integer next() {
		// --- Assert reset has been called before this ---
		assert resetCalled: "IntVarValueIterator.next() error: reset has not been called yet.";
		// --- Assert hasNext has been called and returned true ---
		assert hasNextVal: "IntVarValueIterator.next() error: hasNext has not been called or returned false";
		// -------

		value = var.nextValue(value);
		if(value > ub) {
			throw new NoSuchElementException("IntVarValueIterator for IntVar "+var+" has no more element");
		}

		hasNextVal = false; // reset val
		return value;
	}
}