import java.util.*;

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;

        // Check whether start or destination is blocked
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }

        // Create queue
        Queue<int[]> q = new LinkedList<>();

        // Add starting cell with distance 1
        q.offer(new int[]{0, 0, 1});

        // Mark starting cell visited
        grid[0][0] = 1;

        // All 8 possible directions
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1,  0,  1,-1, 1,-1, 0, 1};

        // BFS
        while (!q.isEmpty()) {

            int[] curr = q.poll();

            int row = curr[0];
            int col = curr[1];
            int dist = curr[2];

            // Destination reached
            if (row == n - 1 && col == n - 1) {
                return dist;
            }

            // Explore all 8 directions
            for (int i = 0; i < 8; i++) {

                int newRow = row + dr[i];
                int newCol = col + dc[i];

                // Check boundaries
                if (newRow >= 0 && newRow < n &&
                    newCol >= 0 && newCol < n) {

                    // Check whether cell is open
                    if (grid[newRow][newCol] == 0) {

                        // Mark visited
                        grid[newRow][newCol] = 1;

                        // Add to queue
                        q.offer(new int[]{
                            newRow, newCol, dist + 1
                        });
                    }
                }
            }
        }

        return -1;
    }
}