import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int zi, zj;
    private final int[][] blocks;
    private int hamming;
    private int manhattan;

    public Board(int[][] blocks) {
        // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        if (blocks == null) {
            throw new IllegalArgumentException();
        }
        this.blocks = new int[blocks.length][blocks.length];
        int zii = -1;
        int zjj = -1;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                this.blocks[i][j] = blocks[i][j];
                if (blocks[i][j] == 0) {
                    zii = i;
                    zjj = j;
                } else {
                    if (blocks[i][j] != (i * blocks.length + j + 1)) {
                        // calc hamming...
                        hamming++;
                    }
                    // calc manhattan...
                    int ri = (blocks[i][j] - 1) / blocks.length;
                    int rj = (blocks[i][j] - 1) % blocks.length;
                    manhattan += Math.abs(ri - i);
                    manhattan += Math.abs(rj - j);
                }
            }
        }
        if (zii < 0 || zjj < 0) {
            throw new IllegalArgumentException("no space block found");
        }
        zi = zii;
        zj = zjj;
    }

    public int dimension() {
        // board dimension n
        return blocks.length;
    }

    public int hamming() {
        // number of blocks out of place
        return hamming;
    }

    public int manhattan() {
        // sum of Manhattan distances between blocks and goal
        return manhattan;
    }

    public boolean isGoal() {
        // is this board the goal board?
        return hamming == 0 && manhattan == 0;
    }

    public Board twin() {
        int ai = -1, aj = -1;
        int bi = -1, bj = -1;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (ai < 0) {
                    if (blocks[i][j] != 0) {
                        ai = i;
                        aj = j;
                        continue;
                    }
                } else if (bi < 0) {
                    if (blocks[i][j] != 0) {
                        bi = i;
                        bj = j;
                        continue;
                    }
                } else {
                    break;
                }
            }
        }
        swapBlocks(ai, aj, bi, bj);
        Board board = new Board(blocks);
        swapBlocks(ai, aj, bi, bj);
        return board;
    }

    public boolean equals(Object y) {
        // does this board equal y?
        if (y == null) {
            return false;
        }
        if (!(y instanceof Board)) {
            return false;
        }
        Board that = (Board) y;
        if (that.dimension() != this.dimension()) {
            return false;
        }
        for (int i = 0; i < this.blocks.length; i++) {
            for (int j = 0; j < this.blocks.length; j++) {
                if (this.blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void swapBlocks(int ai, int aj, int bi, int bj) {
        int temp = blocks[ai][aj];
        blocks[ai][aj] = blocks[bi][bj];
        blocks[bi][bj] = temp;
    }

    public Iterable<Board> neighbors() {
        // all neighboring boards
        List<Board> boards = new ArrayList<>();
        Board board = null;
        if (zi > 0) {
            // above
            swapBlocks(zi, zj, zi - 1, zj);
            board = new Board(blocks);
            boards.add(board);
            swapBlocks(zi, zj, zi - 1, zj);
        }
        if (zj < blocks.length - 1) {
            // right
            swapBlocks(zi, zj, zi, zj + 1);
            board = new Board(blocks);
            boards.add(board);
            swapBlocks(zi, zj, zi, zj + 1);
        }
        if (zi < blocks.length - 1) {
            // below
            swapBlocks(zi, zj, zi + 1, zj);
            board = new Board(blocks);
            boards.add(board);
            swapBlocks(zi, zj, zi + 1, zj);
        }
        if (zj > 0) {
            // left
            swapBlocks(zi, zj, zi, zj - 1);
            board = new Board(blocks);
            boards.add(board);
            swapBlocks(zi, zj, zi, zj - 1);
        }
        return boards;
    }

    public String toString() {
        // string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        // unit tests (not graded)
        
    }

}