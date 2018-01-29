import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private Step bestAnswer;

    public Solver(Board initial) {
        // find a solution to the initial board (using the A* algorithm)
        MinPQ<Step> steps = new MinPQ<>();
        MinPQ<Step> twins = new MinPQ<>();
        steps.insert(new Step(initial));
        twins.insert(new Step(initial.twin()));
        while (true) {
            bestAnswer = handleAndFindTheBest(steps);
            if (bestAnswer != null) {
                break;
            }
            if (handleAndFindTheBest(twins) != null) {
                break;
            }
        }
    }

    /**
     * Find a best step.
     * Add the next steps of the best step in the minPQ.
     * 
     * @param minPQ minPQ
     * @return return the best next step, return null if it does not exist.
     */

    private Step handleAndFindTheBest(MinPQ<Step> minPQ) {
        if (minPQ.isEmpty()) {
            return null;
        }
        Step best = minPQ.delMin();
        for (Board board : best.board.neighbors()) {
            if (best.pre != null && best.pre.board.equals(board)) {
                continue;
            }
            minPQ.insert(new Step(board, best));
        }
        if (best.board.isGoal()) {
            return best;
        } else {
            return null;
        }
    }

    public boolean isSolvable() {
        // is the initial board solvable?
        return bestAnswer != null;
    }

    public int moves() {
        // min number of moves to solve initial board; -1 if unsolvable
        return isSolvable() ? bestAnswer.stepNums : -1;
    }

    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        if (!isSolvable()) {
            return null;
        }
        Stack<Board> solutions = new Stack<>();
        Step step = bestAnswer;
        while (step != null) {
            solutions.push(step.board);
            step = step.pre;
        }
        return solutions;
    }

    private class Step implements Comparable<Step> {

        Step pre;
        Board board;
        int stepNums;

        Step(Board board, Step pre) {
            this.board = board;
            this.pre = pre;
            this.stepNums = pre.stepNums + 1;
        }

        Step(Board board) {
            this.board = board;
            this.stepNums = 0;
        }

        @Override
        public int compareTo(Step o1) {
            return this.board.manhattan() + this.stepNums - (o1.board.manhattan() + o1.stepNums);
        }

    }

    public static void main(String[] args) {
        // solve a slider puzzle (given below)
        
    }
}