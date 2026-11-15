import java.util.*;

class Solution {
    public int minimumEffortPath(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        // Store minimum effort required to reach each cell
        int[][] effort = new int[m][n];

        for (int[] row : effort) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Starting cell has effort 0
        effort[0][0] = 0;

        // Min Heap: {effort, row, col}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        pq.offer(new int[]{0, 0, 0});

        // Four directions: up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int currEffort = curr[0];
            int row = curr[1];
            int col = curr[2];

            // Destination reached
            if (row == m - 1 && col == n - 1) {
                return currEffort;
            }

            // Skip outdated entries
            if (currEffort > effort[row][col]) {
                continue;
            }

            // Explore four neighbours
            for (int i = 0; i < 4; i++) {

                int newRow = row + dr[i];
                int newCol = col + dc[i];

                // Check boundaries
                if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n) {

                    // Calculate height difference
                    int diff = Math.abs(
                        heights[row][col] - heights[newRow][newCol]
                    );

                    // Maximum effort along this path
                    int newEffort = Math.max(currEffort, diff);

                    // Update if we found a better path
                    if (newEffort < effort[newRow][newCol]) {

                        effort[newRow][newCol] = newEffort;

                        pq.offer(new int[]{
                            newEffort, newRow, newCol
                        });
                    }
                }
            }
        }

        return 0;
    }
}