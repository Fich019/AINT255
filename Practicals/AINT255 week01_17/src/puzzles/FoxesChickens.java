package puzzles;

import java.util.Set;
import java.util.HashSet;

import search.AbstractState;

/**
 * This class defines the state for the Chickens and Foxes problem as stated in
 * the book.
 *
 * @author Marcello
 */
public class FoxesChickens extends AbstractState {

    private int eastChickens = 3;
    private int eastFoxes = 3;
    private int westChickens = 0;
    private int westFoxes = 0;
    private boolean boatOnEast = true;

    /**
     * Constructs a new default foxes and goats state. This is specified as the
     * game start state with 3 Chickens and 3 Foxes on the east side with a
     * boat.
     */
    public FoxesChickens() {
    }

    /**
     * Constructs a move state from a parent state
     *
     * @param parent the parent state
     * @param deltaChickens the change in the number of chickens for this state
     * @param deltaFoxes the change in the number of foxes for this state
     */
    private FoxesChickens(FoxesChickens parent, int deltaChickens, int deltaFoxes) {
        super(parent);

        // switch banks for the boat
        boatOnEast = !parent.boatOnEast;

        // if the boat is now on the east bank
        // then move animals from west to east
        if (boatOnEast) {
            eastChickens = parent.eastChickens + deltaChickens;
            westChickens = parent.westChickens - deltaChickens;

            eastFoxes = parent.eastFoxes + deltaFoxes;
            westFoxes = parent.westFoxes - deltaFoxes;

        } else {
            // the boat is now on the west bank
            // so move animals from east to west
            eastChickens = parent.eastChickens - deltaChickens;
            westChickens = parent.westChickens + deltaChickens;

            eastFoxes = parent.eastFoxes - deltaFoxes;
            westFoxes = parent.westFoxes + deltaFoxes;
        }
    }

    /**
     * Returns a set of all possible moves from this state.
     */
    public Set<AbstractState> getPossibleMoves() {

        Set<AbstractState> moves = new HashSet<AbstractState>();

        FoxesChickens nextFoxesChickensState;

        nextFoxesChickensState = new FoxesChickens(this, 0, 2);
        addIfSafe(nextFoxesChickensState, moves);

        nextFoxesChickensState = new FoxesChickens(this, 2, 0);
        addIfSafe(nextFoxesChickensState, moves);

        nextFoxesChickensState = new FoxesChickens(this, 1, 1);
        addIfSafe(nextFoxesChickensState, moves);

        nextFoxesChickensState = new FoxesChickens(this, 1, 0);
        addIfSafe(nextFoxesChickensState, moves);

        nextFoxesChickensState = new FoxesChickens(this, 0, 1);
        addIfSafe(nextFoxesChickensState, moves);

        return moves;
    }

    /**
     * A move can be added to the list of states only if it obeys the rules of
     * the game
     *
     * @param moves
     */
    private void addIfSafe(FoxesChickens nextState, Set<AbstractState> moves) {
        boolean isSafe = false;

        if ((nextState.eastFoxes <= nextState.eastChickens || nextState.eastChickens == 0)
                && (nextState.westFoxes <= nextState.westChickens || nextState.westChickens == 0)
                && nextState.eastChickens >= 0 && nextState.eastFoxes >= 0
                && nextState.westChickens >= 0 && nextState.westFoxes >= 0) {

            isSafe = true;
        }

        if (isSafe) {
            moves.add(nextState);
        }
    }

    /**
     * The solution is specified as having all Chickens and Foxes on west side.
     * It does not matter where the boat is.
     *
     * @return true if this state is a solution
     */
    public boolean isSolution() {

        boolean isASolution = false;

        if (eastChickens == 0 && eastFoxes == 0) {
            isASolution = true;
        }

        return isASolution;
    }

    /**
     * Returns a heuristic approximation of the number of moves required to
     * solve this problem from this state. This is implemented as 2 times the
     * number of people on the east side
     */
    public double getHeuristic() {
        return 2 * (eastChickens + eastFoxes);
    }

    /**
     * Compares whether two objects are equal.
     */
    public boolean equals(Object o) {
        if (o == null || !(o instanceof FoxesChickens)) {
            return false;
        }
        FoxesChickens ms = (FoxesChickens) o;
        return this.boatOnEast == ms.boatOnEast
                && this.eastChickens == ms.eastChickens
                && this.eastFoxes == ms.eastFoxes;
    }

    /**
     * Returns a hashcode for this state (for lookup optimization).
     */
    public int hashCode() {
        return ((boatOnEast ? 1 : 0) << 16)
                | (eastChickens << 8)
                | (eastFoxes);
    }

    /**
     * Returns a string representation of this state
     */
    public String toString() {
        return "[EAST chickens: " + eastChickens + " Foxes: " + eastFoxes
                + "  " + (boatOnEast ? "B" : " ") + " | ~~~~~ | " + (boatOnEast ? " " : "B") + " WEST chickens: "
                + westChickens + " Foxes: " + westFoxes + "]     (move: " + getDistance() + ")";
    }
}
