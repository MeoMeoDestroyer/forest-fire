import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Fire {
    /**
     * Returns how long it takes for all vulnerable trees to be set on fire if a
     * match is lit at a given location.
     * 
     * The forest is represented via a rectangular 2d char array where t represents a tree
     * and . represents an empty space.
     * 
     * At time 0, the tree at location [matchR, matchC] is set on fire. At every subsequent
     * time step, any tree that is adjacent (up/down/left/right) to a burning tree is also
     * set on fire. 
     * 
     * 
     * EXAMPLE 
     * forest:
     * 
     * t..tttt.t
     * ..tt....t
     * ..ttttttt
     * tttt.....
     * 
     * matchR: 2
     * matchC: 6
     * 
     * Result: 8
     * 
     * Explanation:
     * At time 0, the tree at (2, 6) is set on fire. At time 1, its adjacent trees also catch on fire
     * At time 2, the trees adjacent to those catch as well. At time 8, the last tree to catch is at
     * (0,6). In this example, there is one tree that never burns, so it is not included in the time calculation.
     * 
     * 
     * @param forest a 2d array where t represents a tree and . represents the ground
     * @param matchR The row the match is lit at
     * @param matchC The column the match is lit at
     * @return the time at which the final tree to be incinerated starts burning
     */
    public static int timeToBurn(char[][] forest, int matchR, int matchC) {
        // if true, tree is on fire
        boolean[][] visited = new boolean[forest.length][forest[0].length];

        Queue<int[]> queue = new LinkedList<>();
        // row, column and time
        queue.add(new int[] {matchR, matchC, 0});
        visited[matchR][matchC] = true;


        int lastTime = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int time = current[2];
            lastTime = time;

            // up down left right 
            for (int[] move : nextMoves(forest, current)) {
                if (!visited[move[0]][move[1]]) {
                    visited[move[0]][move[1]] = true;

                    queue.add(new int[] {move[0], move[1], time + 1});
                }
            }
        }
        return lastTime;
    }

    public static List<int[]> nextMoves(char[][] forest, int[] current) {
        int curR = current[0];
        int curC = current[1];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<int[]> moves = new ArrayList<>();

        for (int[] direction : directions) {
            int newR = curR + direction[0];
            int newC = curC + direction[1];

            if (newR >= 0 && newR < forest.length
                    && newC >= 0 && newC < forest[newR].length
                    && forest[newR][newC] == 't') {
                moves.add(new int[] {newR, newC});
            }
        }
        return moves;
}

}