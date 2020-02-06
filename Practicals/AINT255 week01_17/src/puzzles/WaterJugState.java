package puzzles;

import java.util.Set;
import java.util.HashSet;

import search.AbstractState;

/**
 * This class defines the state for the Water Jug problem as stated in the book.
 *
 * @author Marcello
 */
public class WaterJugState extends AbstractState { //implements State {

    private int left = 0;
    private int right = 0;
    private String action = "\t ";

    /**
     * Constructs a new default water jug state. Both jugs are empty.
     */
    public WaterJugState() {
        left = 0;
        right = 0;
    }

    /**
     * Constructs a move state from a parent state
     */
    private WaterJugState(WaterJugState parent, int left, int right, String action) {
        super(parent);
        this.left = left;
        this.right = right;
        
        this.action = action;
    }

    /**
     * Returns a set of all possible moves from this state.
     */
    public Set<AbstractState> getPossibleMoves() {

        Set<AbstractState> moves = new HashSet<AbstractState>();
  
        // Assume the 3 litre jug is on the left and the 5 litre on the right.
        
        // fill the left with 3 litres
        //moves.add(new WaterJugState(this, 3, right, " filling left with 3 "));
        
        // fill the right with 5 litres
        moves.add(new WaterJugState(this, left, 4,  " filling right with 4 "));

        
        // empty
        moves.add(new WaterJugState(this, 0, right,  " emptying left  container "));
        moves.add(new WaterJugState(this, left, 0,   " emptying right container "));
        
        // move water left to right
        int delta = Math.min(left, 5 - right);
        if (delta > 0) {
            moves.add(new WaterJugState(this, left - delta, right + delta, " moving " + delta + " from left to right"));
        }
        // move water right to left
        delta = Math.min(3 - left, right);
        if (delta > 0) {
            moves.add(new WaterJugState(this, left + delta, right - delta, " moving " + delta + " from right to left"));
        }

        return moves;
    }

    /**
     * The solution is specified as having 4 litres in the right jug.
     *
     * @return true if this state is a solution
     */
    public boolean isSolution() {
        return right == 2;
    }

    /**
     * Returns a heuristic approximation of the number of moves required to
     * solve this problem from this state. This is implemented as the difference
     * between the left jug being 2 and right jug being 5, the move that is
     * prior to winning.
     */
    public double getHeuristic() {
        if (right == 2 ) {
            return 0;
        }
        return 1 + Math.abs(left - 2) + Math.abs(right - 5);
    }

    /**
     * Compares whether two objects are equal.
     */
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WaterJugState)) {
            return false;
        }
        WaterJugState wjs = (WaterJugState) o;
        return this.left == wjs.left
                && this.right == wjs.right;
    }

    /**
     * Returns a hash code for this state (for lookup optimisation).
     */
    public int hashCode() {
        return left * 5 + right;
    }

    public String getAction() {
        return action;
    }

    /**
     * Returns a string representation of this state
     */
    public String toString() {
        return action + " \t contents (" + left + ") (" + right + ") (moves so far : " + getDistance() + ")";
       // return " \t contents (" + left + ") (" + right + ") (moves so far : " + getDistance() + ")";
    }
}