/*
 * This file is part of choco-solver, http://choco-solver.org/
 *
 * Copyright (c) 2025, IMT Atlantique. All rights reserved.
 *
 * Licensed under the BSD 4-clause license.
 *
 * See LICENSE file in the project root for full license information.
 */
package org.chocosolver.solver.constraints.unary;

import org.chocosolver.sat.Reason;
import org.chocosolver.solver.constraints.Explained;
import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.constraints.PropagatorPriority;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.events.IntEventType;
import org.chocosolver.util.ESat;

/**
 * X <= C
 * <br/>
 *
 * @author Charles Prud'homme
 * @since 16/06/11
 */
@Explained
public class PropLessOrEqualXC extends Propagator<IntVar> {

    private final int constant;

    public PropLessOrEqualXC(IntVar var, int cste) {
        super(new IntVar[]{var}, PropagatorPriority.UNARY, false, true);
        this.constant = cste;
    }

    @Override
    public int getPropagationConditions(int vIdx) {
        return IntEventType.boundAndInst();
    }

    @Override
    public void propagate(int evtmask) throws ContradictionException {
        // with views such as abs(...), the prop can be not entailed after initial propagation
        if (vars[0].updateUpperBound(constant, this, Reason.undef()) || vars[0].getUB() <= constant) {
            this.setPassive();
        }
    }

    @Override
    public ESat isEntailed() {
        if (vars[0].getUB() <= constant) {
            return ESat.TRUE;
        }
        if (vars[0].getLB() > constant) {
            return ESat.FALSE;
        }
        return ESat.UNDEFINED;
    }

    @Override
    public String toString() {
        return vars[0].getName() + " <= " + constant;
    }

}
