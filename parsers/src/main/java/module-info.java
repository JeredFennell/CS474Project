/*
 * This file is part of choco-parsers, http://choco-solver.org/
 *
 * Copyright (c) 2025, IMT Atlantique. All rights reserved.
 *
 * Licensed under the BSD 4-clause license.
 *
 * See LICENSE file in the project root for full license information.
 */
/**
 * <br/>
 *
 * @author Charles Prud'homme
 * @since 15/10/2019
 */
module org.chocosolver.parsers {
    exports org.chocosolver.parser;
    requires transitive org.chocosolver.solver;
    requires xcsp3.tools;
    requires args4j;
    requires java.sql;
    requires org.antlr.antlr4.runtime;
    requires trove4j;
    requires java.management;

    opens org.chocosolver.parser to args4j, org.testng;
    opens org.chocosolver.parser.mps to args4j;
    opens org.chocosolver.parser.flatzinc to args4j;
    opens org.chocosolver.parser.xcsp to args4j;
    opens org.chocosolver.parser.dimacs to args4j;
    exports org.chocosolver.parser.handlers;
    opens org.chocosolver.parser.handlers to args4j, org.testng;
}