class NumArray {

    int[] tree;
    int n;

    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[4 * n];

        build(1, 0, n - 1, nums);
    }

    // Build Segment Tree
    void build(int index, int low, int high, int[] nums) {

        if (low == high) {
            tree[index] = nums[low];
            return;
        }

        int mid = (low + high) / 2;

        build(2 * index, low, mid, nums);
        build(2 * index + 1, mid + 1, high, nums);

        tree[index] = tree[2 * index] + tree[2 * index + 1];
    }

    // Update a particular index
    void updateTree(int index, int low, int high, int pos, int val) {

        if (low == high) {
            tree[index] = val;
            return;
        }

        int mid = (low + high) / 2;

        if (pos <= mid) {
            updateTree(2 * index, low, mid, pos, val);
        } else {
            updateTree(2 * index + 1, mid + 1, high, pos, val);
        }

        tree[index] = tree[2 * index] + tree[2 * index + 1];
    }

    // Range Sum Query
    int query(int index, int low, int high, int l, int r) {

        // No overlap
        if (high < l || r < low) {
            return 0;
        }

        // Complete overlap
        if (l <= low && high <= r) {
            return tree[index];
        }

        // Partial overlap
        int mid = (low + high) / 2;

        int left = query(2 * index, low, mid, l, r);

        int right = query(2 * index + 1, mid + 1, high, l, r);

        return left + right;
    }

    public void update(int index, int val) {
        updateTree(1, 0, n - 1, index, val);
    }

    public int sumRange(int left, int right) {
        return query(1, 0, n - 1, left, right);
    }
}