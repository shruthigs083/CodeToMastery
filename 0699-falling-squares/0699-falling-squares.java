
class Solution {

    int[] tree;
    int[] lazy;
    boolean[] hasLazy;

    // Push pending updates to children
    void push(int node) {

        if (hasLazy[node]) {

            int left = 2 * node;
            int right = 2 * node + 1;

            tree[left] = lazy[node];
            tree[right] = lazy[node];

            lazy[left] = lazy[node];
            lazy[right] = lazy[node];

            hasLazy[left] = true;
            hasLazy[right] = true;

            hasLazy[node] = false;
        }
    }

    // Range assignment update
    void update(int node, int start, int end,
                int l, int r, int val) {

        // No overlap
        if (start > r || end < l) {
            return;
        }

        // Complete overlap
        if (start >= l && end <= r) {

            tree[node] = val;
            lazy[node] = val;
            hasLazy[node] = true;

            return;
        }

        // Partial overlap
        push(node);

        int mid = start + (end - start) / 2;

        update(2 * node, start, mid, l, r, val);

        update(2 * node + 1, mid + 1, end, l, r, val);

        // Recalculate maximum height
        tree[node] = Math.max(
            tree[2 * node],
            tree[2 * node + 1]
        );
    }

    // Range maximum query
    int query(int node, int start, int end,
              int l, int r) {

        // No overlap
        if (start > r || end < l) {
            return 0;
        }

        // Complete overlap
        if (start >= l && end <= r) {
            return tree[node];
        }

        // Partial overlap
        push(node);

        int mid = start + (end - start) / 2;

        int leftMax = query(
            2 * node, start, mid, l, r
        );

        int rightMax = query(
            2 * node + 1, mid + 1, end, l, r
        );

        return Math.max(leftMax, rightMax);
    }

    public List<Integer> fallingSquares(int[][] positions) {

        int n = positions.length;

        List<Integer> result = new ArrayList<>();

        if (n == 0) {
            return result;
        }

        // STEP 1: Coordinate Compression

        int[] coords = new int[2 * n];

        int k = 0;

        for (int[] p : positions) {

            int left = p[0];
            int right = p[0] + p[1];

            coords[k++] = left;
            coords[k++] = right;
        }

        Arrays.sort(coords);

        // Remove duplicates
        int unique = 0;

        for (int i = 0; i < coords.length; i++) {

            if (unique == 0 || coords[i] != coords[unique - 1]) {
                coords[unique++] = coords[i];
            }
        }

        // Number of elementary intervals
        int m = unique - 1;

        tree = new int[4 * m + 5];
        lazy = new int[4 * m + 5];
        hasLazy = new boolean[4 * m + 5];

        // STEP 2: Drop each square

        int globalMax = 0;

        for (int[] p : positions) {

            int left = p[0];
            int side = p[1];
            int right = left + side;

            // Find compressed indices
            int l = Arrays.binarySearch(coords, 0, unique, left);
            int r = Arrays.binarySearch(coords, 0, unique, right) - 1;

            // STEP 3: Find maximum height in this range
            int maxHeight = query(1, 0, m - 1, l, r);

            // STEP 4: Calculate new square's top height
            int newHeight = maxHeight + side;

            // STEP 5: Update the entire covered range
            update(1, 0, m - 1, l, r, newHeight);

            // STEP 6: Maintain overall maximum
            globalMax = Math.max(globalMax, newHeight);

            result.add(globalMax);
        }

        return result;
    }
}
