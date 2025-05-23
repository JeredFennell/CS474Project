Choco Solver ChangeLog
======================

This file is dedicated to sum up the new features added and bugs fixed in Choco-solver since the version, 4.0.0.
**Note**: double-space is replaced by "\t" character on release process. Make sure the format is ok.

5.0.0-beta.1 - 17 Feb 2025
--------------------------

### Major features:

This version introduces CP-SAT in Choco-solver which 
replaces the previous explanation framework.
The Lazy Clause Generation (LCG) is a technique that allows the solver to generate clauses during search.
The current implementation is inspired by Chuffed 
and "Lazy Clause Generation Reengineered" - Feydy, T., Stuckey, P.J. (2009).
This new version has necessitated a rather intrusive revision of the way propagators filter.                                         

By default, LCG is not enabled. The following code shows how to enable it:
```java
Model model = new Model(Settings.init().setLCG(true));
```

At this stage, this is a [beta version](https://github.com/orgs/chocoteam/projects/1).
First, only integer and boolean variables are supported. 
Second, some constraints are explained with dedicated functions and 
others are decomposed into explained ones.
More importantly, others are neither explained nor decomposed (for the moment). 
In the latter case, an exception is raised to inform the user of the situation. 


### Other closed issues and pull requests:
See [milestone 5.0.0](https://github.com/chocoteam/choco-solver/milestone/40)

#### Contributors to this release:
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)

**Full Changelog**: https://github.com/chocoteam/choco-solver/compare/v4.10.18...v5.0.0-beta.1

4.10.18 - 27 Jan 2025
---------------------

### Major features:
- Fix unexpected behaviour when using `neg()` intvar in arithm #1115
- Fix bin packing initial propagation (#1113)
- Fixed an error in `increasing` constraint documentation and updated `decreasing` constraint documentation (#1103)
- 
### Deprecated API (to be removed in next release):

**Full Changelog**: https://github.com/chocoteam/choco-solver/compare/v4.10.17...v4.10.18

4.10.17 - 23 Sep 2024
-------------------

### Hotfix
- Fix bug in `PropHybridTable` (#1102)

**Full Changelog**: https://github.com/chocoteam/choco-solver/compare/v4.10.16...v4.10.17

4.10.16 - 12 Sep 2024
-------------------

### Hotfix
- Fix bug in `IntAffineView` (#1101)

**Full Changelog**: https://github.com/chocoteam/choco-solver/compare/v4.10.15...v4.10.16

4.10.15 - 09 Sep 2024
-------------------

### Major features:

- Add `PickOnDom` and variants (see `Search.pickOnDom`)       
- Enhance MiniZinc output
- Revamp scripts to solver flatzinc files
- Fix bugs (see closed issues)

### Deprecated API (to be removed in next release):

### Other closed issues and pull requests:
See [milestone 4.10.15](https://github.com/chocoteam/choco-solver/milestone/39)

#### Contributors to this release:
- [Arthur Godet](https://github.com/ArthurGodet) (@ArthurGodet) 
- [Paul King](https://github.com/paulk-asert) (@paulk-asert)
- [Zhang Yang](https://github.com/zy-nesime) (@zy-nesime)
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)
                                                                         

**Full Changelog**: https://github.com/chocoteam/choco-solver/compare/v4.10.14...v4.10.15

4.10.14 - 02 Nov 2023
---------------------

### Major features:
- NEW: `solver..showRestarts();` to display the number of restarts during the search. 
- NEW: hybrid table constraints (see `model.table(...)`)
- NEW: partial assignement generator for COP (see `Search.generatePartialAssignment()`)
- NEW: Add Blackbox configuration (#1058)
- Revamp views (#564)
- Improve `pow(base, exponent, result)`
- Remove `BitsetArrayIntVarImpl` now managed in `BitSetIntVarImpl`

### Deprecated API (to be removed in next release):

### Other closed issues and pull requests:
See [milestone 4.10.14](https://github.com/chocoteam/choco-solver/milestone/38)

#### Contributors to this release:
- [Jean-Guillaume Fages](https://github.com/jgFages) (@jgFages)
- [Arthur Godet](https://github.com/ArthurGodet) (@ArthurGodet) 
- [Fabien Hermenier](https://github.com/fhermeni) (@fhermeni)
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)

**Full Changelog**: https://github.com/chocoteam/choco-solver/compare/v4.10.13...v4.10.14
    
4.10.13 - 05 Jun 2023
---------------------

### Major features: 
Mainly bug fixes and improvements of Pareto front performances.

### Deprecated API (to be removed in next release):

### Other closed issues and pull requests:
See [milestone 4.10.13](https://github.com/chocoteam/choco-solver/milestone/xx)

#### Contributors to this release:
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)
- [Jani Simomaa](https://github.com/jsimomaa) (@jsimomaa)
    
4.10.12 - 06 Mar 2023
-------------------

### Major features:
- Fix bug #1006, #1011 and #1014

### Deprecated API (to be removed in next release):

### Other closed issues and pull requests:
See [milestone 4.10.12](https://github.com/chocoteam/choco-solver/milestone/xx)

#### Contributors to this release:
- [Dimitri Justeau-Allaire](https://github.com/dimitri-justeau) (@dimitri-justeau)
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)
    
4.10.11 - 07 Feb 2023
---------------------

### Major features:
- Correcting the lack of filtering in `PropIncreasing`
- Change the SetType for the undirected graph stored in DiffN for efficiency reason
- `SparseBitSet` for a backtrackable bitset with mostly 0s (#935)
- Change the way restarting works: not a `Move` anymore, directly included in `Solver` (#950)
- Simplification of objective manager (#949)
- Calling `getValue()` on an uninstantiated variable throws an IllegalStateException (#957)
- Change the MiniZinc complementary search to a FirstFail (instead of DomOverWDeg) (#958)
- Reduce object creation during solving (#985)
- Reduce object creation during propagation (#969)
- Reduce memory footprint of `Model` (#964)
- Add `model.getEstimatedMemory()` method
- Add `Model` analysis (#939)
- Add log file as RegParser option (for parsers)
- creation of PropPower propagators with exponent as int

### Deprecated API (to be removed in next release):
From `Solver`:
- `Propagate getPropagate()`
- `void setPropagate(Propagate p)`
- `Closeable outputSearchTreeToCPProfiler(boolean domain)`

Others:
- `Propagate` class
- `IntEventType`: `int combine(IntEventType... evts)`

### Other closed issues and pull requests:
See [milestone 4.10.11](https://github.com/chocoteam/choco-solver/milestone/xx)

#### Contributors to this release:
- [Jean-Guillaume Fages](https://github.com/jgFages) (@jgFages)
- [Arthur Godet](https://github.com/ArthurGodet) (@ArthurGodet) 
- [Fabien Hermenier](https://github.com/fhermeni) (@fhermeni)
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)
        
4.10.10 - 11 Oct 2022
---------------------

### Major features:
- Fix performance issue of ViewDeltaMonitor
- Fix lack of filtering in STR2+ when domain is bounded
- Fix issue relative to `OR` and `AND` in `LogOp`
- Add missing checks in OneWordS32BitSet and OneWordS64BitSet + update constructors
- Add new API to VariableUtils
- Add CITATION.cff
- Update paper.md

### Deprecated API (to be removed in next release):

### Other closed issues and pull requests:
See [milestone 4.10.10](https://github.com/chocoteam/choco-solver/milestone/xx)

#### Contributors to this release:
- [Jean-Guillaume Fages](https://github.com/jgFages) (@jgFages)
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)
- [Mehmet Hakan Satman](https://github.com/jbytecode) (@jbytecode)
      
4.10.9 - 22 Aug 2022
--------------------

### Major features:
- It is now possible to declare hints to help the search finding a first solution. See `solver.addHint(var, val)`.
- Add `Solver.tableSampling` resolution policy which returns a stream of `Solution` randomly sampled.
- Add `increasing` and `decreasing` constraints
- Add `costFlow` constraint, which is composed of scalar products and a MinCostMaxFlow propagator
- Revamp `pow` constraint
- Add threshold for `element` with array of variables signature
- Add a propagator for 0/1-knapsack (thanks to @PoltyPoltou)
- Remove `CPProfiler` since the project is not active anymore.
- Make possible to deal with large domains for some constraints (#894)
- Add a `LinearProgram` class to deal with linear program (!) and a two-phase Simplex (not connected yet)
- Fix bug related to delta in boolean views
- Improvements relative to reified propagators
- Change signature of AtLeastNValues
- Record time to best in `Measures(IMeasures measures)` constructor
 
####  Change the way propagators are stored in variables to ease swapping (#889)
Each variable is in charge of scheduling its propagators. 
This impacts the way propagators are stored in a variable. 
Consequently, the `getPropagators()` and `getPropagator(int)` methods are deprecated, `streamPropagators()` replaces them. 
Unless otherwise stated, a propagator that can be entailed is automatically swapped in its variables' internal structure.

#### SetVar
- Extend some black-box strategies to `SetVar`
- Add `union(SetVar unionSet, SetVar indices, SetVar[] sets)` constraint
- Add free strategy for SetVar (RegParser and ParallelPortfolio)
- Fix `SetVar#getDomainSize`
- Add `SetDomainMax`
- Add 'setLe' and 'setLt' constraint (as a decomposition) in `ISetConstraintFactory`

#### Flatzinc, XCSP3 and MPS
- Change default search of Flatzinc
- Increase interval for Flatzinc unbounded intvar
- Remove default ANSI in parsers
- Update FZN grammar to deal with 'set of int'
- Flatzinc: deal with warm_start
- Add ignored search annotation warning in FGoal
- Update XCSPParser + add `model.amongDec`

### JAR Files
Since 4.10.9, a new jar file is available for download. 
This is a lighter version of the one with dependencies, 
namely `choco-parsers-light-VERSION.jar`.
The following dependencies have been excluded from this archive:
- `org.apache.pdfbox:pdfbox`
- `org.apache.fontbox:fontbox`
- `com.ibm.icu:icu4j`

This results in 82% smaller archive.
Note that the non-alleged versions are still available for download.

### Deprecated API (to be removed in next release):
- `IOutputFactory.outputSearchTreeToCPProfiler(boolean domain)`

### Other closed issues and pull requests:
See [milestone 4.10.9](https://github.com/chocoteam/choco-solver/milestone/xx)

#### Contributors to this release:
- [Mathieu Vavrille](https://github.com/MathieuVavrille) (@MathieuVavrille)
- [Nicolas Pierre](https://github.com/PoltyPoltou) (@PoltyPoltou)
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)
- [Jean-Guillaume Fages](https://github.com/jgFages) (@jgFages)
- [Arthur Godet](https://github.com/ArthurGodet) (@ArthurGodet)
    
4.10.8 - 07 Jan 2022
--------------------

### Major features:
- Propagation is now observable, `solver.observePropagation(PropagationObserver)`. 
Consequently, it is now possible to profil the propagation by calling `solver.profilePropagation()`. 
See Javadoc for details and usages (#832).
- Release 4.10.7 introduces a bug related to delta monitors, which is now fixed (#837).
- Add new black-box strategy: failure based variable ordering heuristics (@lihb905)
- Add `model.streamVars()` and `model.streamCstrs()`
- Bounded domains checking for table constraints
- Change complementary search in FlatZincParser
- Bump XCSP3
### Deprecated API (to be removed in next release):

### Other closed issues and pull requests:
See [milestone 4.10.8](https://github.com/chocoteam/choco-solver/milestone/xx)

#### Contributors to this release:
- [Jean-Guillaume Fages](https://github.com/jgFages) (@jgFages)
- [Fabien Hermenier](https://github.com/fhermeni) (@fhermeni)
- [Hongbo Li](https://github.com/lihb905) (@lihb905)
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)

4.10.7 - 11 Oct 2021
--------------------

### Major features:
- Simplify the way deltamonitors work. There is no need to `freeze` and `unfreeze` 
them before calling `forEach...` methods. But, a call to `forEach...` consumes all values stored.
- Fix a bug related to incremental propagators, views and missing events.
- STR2+ now deals with STAR tuples. Can be used when CT+ is not efficient (mainly due to very large domain size)
- Resetting cutoff strategies now possible
- Change restart behavior to reset cutoff on solutions (can be disabled though, calling `solver.setRestarts(..)` API).
- Display occurrences of variable types and occurrences of propagator types
- Now `IntDomainBest` offers API to break ties (see `Search.ValH.BLAST` for an example).
- Add `solver.defaultSolution()` which creates lazily a solution recording everything, plugs it and returns it. 
This is helpful when a Solution object is required in many places. 
- Modification of the management of expressions in order to reduce the number of created variables (WIP).
- Add `IntVar.stream()` that streams a variable's values (in increasing order)
- Add `Search.ValH.BMIN` and `Search.ValH.BLAST`
- Add DIMACS CNF parser (`org.chocosolver.parser.mps.ChocoDIMACS`)
- Add Logger (`solver.log()`) to trace from Model/Solver.
- Change some default settings
- Revamp `Settings`, now is defined as a factory pattern + add `Settings.dev()` and `Settings.prod()` profiles.
- Make *half reification* possible. Seed `c.implies(b)` or `c.impliedBy(b)` 
  where `c` is a Constraint and `b` a BoolVar.
- Update MiniZinc constraints definition + flatzinc files (for testing).
- Update `choco.msc` (for MiniZinc IDE) + `./minizinc/README.md`   
- Add `Argmax` and `Argmin` constraints
- Add `IfThenElse` as a decomposed constraint
- Improvement of `solver.findParetoFront()`

### Deprecated API (to be removed in next release):


### Other closed issues and pull requests:
See [milestone 4.10.7](https://github.com/chocoteam/choco-solver/milestone/xx)

#### Contributors to this release:
- [Dimitri Justeau-Allaire](https://github.com/dimitri-justeau) (@dimitri-justeau)
- [Jean-Guillaume Fages](https://github.com/jgFages) (@jgFages)
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom) 
- [Charles Vernerey](https://github.com/ChaVer) (@chaver)

4.10.6 - 11 Dec 2020
--------------------             

### Major features:
- Add new resolution helper in `Solver`, namely `findOptimalSolutionWithBounds`. See Javadoc for details and usages.
- `ParallelPortfolio` now allows to add *unreliable* models, that is models whose resolution is deliberately made incomplete. 
These models should not stop the parallel resolution process when they no longer find a solution. 
Only complete models can inform the portfolio that they have proven the full exploration of the search space. 
- Add `org.chocosolver.util.tools.PreProcessing` class, and a first preprocessing rule: equalities detection
- Upgrade ibex integration to support ibex-java [v1.2.0](https://github.com/ibex-team/ibex-java/releases/tag/1.2.0). 
Fixes for issues #653 and #740. 
- Add QuickXPlain algorithm to find the Minimum Conflicting Set (see issue #509)
- Update XCSP3 parser.
- Fix `InDomainMedian` when domain size is even
- Add new way to watch solving: `solver.verboseSolving()`
- Deal with annotations for some Flatzinc constraints (allDifferent and inverse)
- Add `MultiArmedBandit` strategy sequencer


### Deprecated API (to be removed in next release):

### Other closed issues and pull requests:
See [milestone 4.10.6](https://github.com/chocoteam/choco-solver/milestone/30)

#### Contributors to this release:
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)
- [João Pedro Schmitt](https://github.com/schmittjoaopedro) (@schmittjoaopedro) 

4.10.5 - 02 Oct 2020
-------------------

### Major features:
- add `IN` arithmetic int expression.
 
### Deprecated API (to be removed in next release):
- `Settings.enableACOnTernarySum()` removed
- `Settings.setEnableACOnTernarySum(boolean)` removed

### Other closed issues and pull requests:
See [milestone 4.10.5](https://github.com/chocoteam/choco-solver/milestone/29) 

#### Contributors to this release:
- [Guillaume Le Louët](https://github.com/glelouet) (@glelouet)
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom)
- [João Pedro Schmitt](https://github.com/schmittjoaopedro) (@schmittjoaopedro) 

4.10.4 - 08 Sep 2020
--------------------

### Major features:
- Change search strategies in ParallelPortfolio
- Make "CT+" available to binary table constraint
- Update [Dockerfile](https://github.com/chocoteam/choco-solver/blob/master/parsers/src/main/minizinc/docker/Dockerfile_Choco.dms), now automatically released in [hub.docker.com](https://hub.docker.com/repository/docker/chocoteam/choco-solver-mzn)
- Migrate to ANTLR 4.8-1
- Support nested `seq_search` in FlatZinc file
- Add missing operations in `model.unpost(c)`
- Add new constraint, named `conditional`, that posts constraints on condition
- Merge `cutoffseq` in `solver`
- Merge `pf4cs` in `parsers`
- Remove `geost` from `parsers`

### Deprecated API (to be removed in next release):

### Other closed issues and pull requests:
\#692, #698, #700, #702, #703, #704, #705

#### Contributors to this release:
- [Dimitri Justeau-Allaire](https://github.com/dimitri-justeau) (@dimitri-justeau)
- [João Pedro Schmitt](https://github.com/schmittjoaopedro) (@schmittjoaopedro)
- [Charles Prud'homme](https://github.com/cprudhom) (@cprudhom) 

4.10.3 - 03 Jul 2020
--------------------
Multi-modules and JPMS-ready.

### Major features:
- Move `cutoffseq`, `choco-sat`, `choco-solver`, `pf4cs`, `choco-parsers` and `samples` projects into a (maven) multi-modules project, JPMS-ready 
#### Additions
- Add Conflict History Search (["Conflict history based search for constraint satisfaction problem." Habetand Terrioux,SAC 19](https://dblp.org/rec/conf/sac/HabetT19) (#676)
- Add dom/wdeg with refinement (["Refining Constraint Weighting." Wattez et al. ICTAI 2019.](https://dblp.org/rec/conf/ictai/WattezLPT19))
- Default AC algorithm for `AllDifferent` is now from IJCAI-18 "A Fast Algorithm for Generalized Arc Consistency of the Alldifferent Constraint", Zhang et al. (#644)
- Add a pure java alternative to Ibex (#666)
- LNS can now be defined with a solution as bootstrap.
- Add simplify API for current Solver operations (#659)
- Simplify code for the nValues constraint (using a watching/witnessing reasoning) (#674)
- Replace former Bin Packing propagators by Paul Shaw propagator (#671)
- Improving PropDiffN performance (#663)
- Add nogood stealing for `ParallelPortfolio` (#669)
- Adding of new constructors for Task objects (#662)
#### Removals
- Remove JSON writer/parser (which was actually partially supported and not maintained) (#664)

### Deprecated API (to be removed in next release):
- `Task(IntVar s, IntVar d, IntVar e, boolean declareMonitor)`
- `AbstractProblem.readArgs(String... args)`


### Other closed issues and pull requests:
\#617, #633, #637, #639, #645, #646, #647, #648, #658, #665, #667, #678, #682, #686, #689, #691

4.10.2 - 14 Oct 2019
-------------------

### Major features:
- fix issues relative to propagation 
- change constraints' status checking
- change stop conditions in `ExplanationForSignedClause`
- add stable module name

### Closed issues and pull requests:
\#618
    
    
4.10.1 - 26 Jun 2019
-------------------

### Major features:
- LNS on other variables (e.g. SetVarLNS)
- Continuous integration fixed
- `IntDomainMiddle` now allows an external definition of what middle is, thanks to `ToDoubleFunction<IntVar>`

### Deprecated API (to be removed in next release):

### Closed issues and pull requests:
\#538, #600, #611, #612, #613, #614, #615, #617, #619, #627, #630

4.10.0 - 12 Dec 2018
-------------------

This release comes with several major modifications.
The most important one is related to explanations.
The previous framework is replaced by a new one based on "A Proof-Producing CSP Solver", M.Vesler and O.Strichman, AAI'10.

See [notebooks](https://github.com/chocoteam/notebooks) for an example of use.

### Major features:

- Update statistic dashboard (see `solver.showDashboard()`)
- Fix `Settings.load` on missing property
- Fix issue in `Cumulative` simplified API
- Add additional views `model.intEqView(x,c)`, `model.intNeView(x,c)`, `model.intLeView(x,c)` and `model.intGeView(x,c)` 
- Detect when the same views is created twice on the same pair <variable, value?>
- Revamp the way LNS' neighbor is declared (simplication)
- Add `AbstractStrategy.remove()` method to remove a declared strategy and its dependencies
- Add new strategies to `Search`
- Add new decomposition to `IDecompositionFactory`
- Improve initialization of CT+ and CT*
- Improve `IntVar#isInstantiatedTo(int)`

### Deprecated API (to be removed in next release):

- `INeighbor` interface is deprecated and replaced by `Neighbor` abstract class
- `INeighborFactory#explanationBased(IntVar...)` is deprecated, no replacement.
- `ILearnFactory#setCBJLearning(boolean,boolean)` and `ILearnFactory#setDBTLearning(boolean,boolean)` are deprecated, see `ILearnFactory#setLearningSignedClauses()` instead

### Closed issues and pull requests:
\#604, #605, #606

4.0.9 - 30 Oct 2018
-------------------

Minor release

### Major features:

- Offer possibility to store and load `Settings` from a property file.
- Add API for `cumulative` when only starts are variable
- Add decomposition of cumulative: `model.cumulativeTimeDecomp(...)`
- Logical expression XOR manages more than 2 variables
- Add new API to IOutputFactory (to Gephi and to Graphviz)
- Add constraint network output (to gexf format), see `solver.constraintNetworkToGephi(file)`
- add `ParallelPortfolio.streamSolutions` (#579)

### Deprecated API (to be removed in next release):

### Closed issues and pull requests:
\#596, #600, #601, #602


4.0.8 - 23 Jul 2018
-------------------

Update DefaultSettings with right version

4.0.7 - 19 Jul 2018
-------------------

**JAR file names have changed**:  
- the suffix 'with-dependencies' disappears,
- the suffix '-no-dep' (for no dependencies) appears.

This should intends to clarify the selection for new comers.

Add a PayPal button for donations.

Move to Ibex-2.6.5.

### Major features:

- Revamp `Settings`: no default method anymore, add setters. A concrete class `DefaultSettings` provides
the default behavior.
- `IViewFactory.intScaleView` now manages negative constants,
- `IViewFactory.intAffineView` is now available
- add new constraint for mixed linear equation (over real/int variables and double/int coefficients)
- Dow/WDeg now manages variables in a bipartite set (instantiated variables are swaped)
- Assert that a propagator that is passive is not allowed to filter anymore
- An exception is thrown when a sum (or scalar) constraint is candidate for integer over/underflow (an alternative should be provided later)
- `BoolVar` now handles modifications in different way (may impact performances)
- Propagation engine has changed: no alternative to seven-queue one anymore + simplification of code (may impact performances)
- add new relation expression `ift(e1,e2)`

### Deprecated API (to be removed in next release):
-  `Model.set(Settings)` is deprecated. Now settings are declared in the `Model` constructor.
- `Settings.debugPropagation()` is deprecated. There is no alternative.


### Closed issues and pull requests:
\#527, #564, #569, #576, #578, #581, #586

4.0.6 - 23 Nov 2017
-------------------

Move to Ibex-2.6.3.

### Major features:
- Ibex instance is no longer static, that offers better stability and reduce
memory consumption when adding/removing functions. Reification no longer managed by Choco but 
delegated to Ibex. 
- `Search.realVarSearch(...)` offers possibility to define minimal range size, known as `epsilon`
- `Search.ibexSolving(model)` let Ibex iterates over solutions **once all integer variables are instantiated**
- add detection of min/max sub-cases
- add simple dashboard in Swing to show resolution statistics, see `solver.showDashboard()`

### Deprecated API (to be removed in next release):
- `IntEqRealConstraint` will be removed in next release, Ibex managed this concept (int to real)
- `Model.getIbex()` should not be used. A `IbexHandler` manages Ibex instances (one per model).

### Closed issues and pull requests:
\#558, #561, #565, #566, #568, #570

4.0.5 - 28 Sep 2017
-------------------

The current release was submitted to [MiniZinc Challenge 2017](http://www.minizinc.org/challenge2017/results2017.html) 
and at [XCSP3 Competition 2017](http://www.cril.univ-artois.fr/XCSP17/) and won medals.

[choco-parsers](https://github.com/chocoteam/choco-parsers) provides utility to export a `Model` to JSON format
and or import JSON data into a `Model`.

### Major features:
- Compact-Table now deals with short tuples (#531)
- Checking if a created constraint is free (neither posted or reified) is now possible with `Settings.checkDeclaredConstraints()`
- Improvements on BoolVarImpl and BoolNotView.
- Remove code deprecated in last release.
- Fix error in Views.
- Add scalar detection in `BiReExpression`
- fix errors in Impact-based Search
- update Search.intVarSearch() + Search.defaultSearch(Model)
- update ParallelPortfolio default strategies

### Deprecated API (to be removed in next release):

### Closed issues and pull requests:
- fix bug in `PropNogoods` when dealing with negative values (impact `solver..setNoGoodRecordingFromRestarts()` and `solver..setNoGoodRecordingFromSolutions(...)`)
- fix bug in `model.sum(...)` and `model.scalar(...)` when dealing with arity greater than 100 and all operators except `=`
- fix bug in `model.table(...)` with binary scope and universal value  
- fix bug related to Ibex and GC.

\#531 ,#545, #546.

4.0.4 - 28 Apr 2017
-------------------

### Major features:
- add logical operator to expression (#520). Now it is possible, f-ex., to declare expression like:
```x.eq(y.add(1)).or(x.eq(y).and(x.eq(1)))```
- add new API to `Solver` to print features in a single line
- enable ignoring passivate propagators when iterating over propagators of a modified variable (false by default; see Settings)

### Deprecated API (to be removed in next release):
- `IPropagationEngine.fails(c,v,m)` is replaced by `Solver.throwsException(c,v,m)` (#524)
- `IPropagationEngine.getContradictionException()` is replaced by `Solver.getContradictionException()` (#524)
- `MathUtils.bounds(values)` is replaced by a call to `MathUtils.min(values)` and `MathUtils.max(values)`

### Remove dead code:
- SparseSet
- IFeatures, Features, IAttribute and Attribute

### Closed issues and pull requests:

\#516, #517, #518, #519, #520, #521, #524.


4.0.3 - 31 Mar 2017
-------------------

### Major features:
- `arithm(IntVar,String,IntVar,String,int)` and `arithm(IntVar,String,IntVar,String,IntVar)` manage '*' and '/'
- add new APIs to `ArrayUtils`
- fix error in `PropBoolMin` and `PropBoolMax`

### Deprecated API:

### Closed issues and pull requests:

\#500, #502, #507, #510, #512, #514, #515.


4.0.2 - 20 Jan 2017
-------------------

### Major features:
  - restrict calls to `Solver.setEngine(...)` when propagation started. See javadoc for details.
  - remove global constructive disjunction, only local constructive disjunction is allowed.
  - add `Solution.restore()` to restore a solution (#354).
  - deep reset of `Solver` (#490, #491)
    
### Deprecated API:
  - `Solver.getState()` (#485)
  - `Measures.IN_SEC` (related to #486)
  - `Settings.outputWithANSIColors`, `IOutputFactory.ANSI_*`
  - `IMoveFactory.setLubyRestart(int, int, ICounter, int)`

### Closed issues and pull requests: 

\#468, #479, #480, #481, #484, #487, #488, #489, #492, #493, #494, #495, #496, #497, #499.

4.0.1 - 16 Dec 2016
-------------------


4.0.0 - 13 Sep 2016
-------------------
