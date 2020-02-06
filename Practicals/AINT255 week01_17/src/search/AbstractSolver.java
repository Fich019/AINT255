package search;

import java.lang.Iterable;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

/**
 * This class implements Solver and provides functionality for automating state
 * solving using a generic iterable interface. The closed set is handled
 * implicitly (defined as a HashSet), so states need to define a valid
 * hashCode() and equals() function for this solver to function correctly.
 *
 * @author Marcello
 */
public abstract class AbstractSolver {

    protected String solverName;

    public abstract List<AbstractState> solve(AbstractState initialState);

    public abstract int getVisitedStateCount();

    public abstract int getToVisitStateCount();

    public String toString() {

        return solverName;
    }

//    protected abstract boolean hasElements();
//
//    protected abstract State nextState();
//
//    protected abstract void addState(State s);
//
//    protected abstract void clearOpen();
}
