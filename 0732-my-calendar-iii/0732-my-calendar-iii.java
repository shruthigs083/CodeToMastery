
class MyCalendarThree {

    class Node {
        int max;
        int lazy;
        Node left, right;
    }

    Node root;

    public MyCalendarThree() {
        root = new Node();
    }

    void push(Node node) {
        if (node.lazy == 0) return;

        if (node.left == null)
            node.left = new Node();

        if (node.right == null)
            node.right = new Node();

        node.left.max += node.lazy;
        node.left.lazy += node.lazy;

        node.right.max += node.lazy;
        node.right.lazy += node.lazy;

        node.lazy = 0;
    }

    void update(Node node, int start, int end, int l, int r) {

        if (r < start || end < l)
            return;

        if (l <= start && end <= r) {
            node.max++;
            node.lazy++;
            return;
        }

        push(node);

        int mid = start + (end - start) / 2;

        if (l <= mid) {
            if (node.left == null)
                node.left = new Node();

            update(node.left, start, mid, l, r);
        }

        if (r > mid) {
            if (node.right == null)
                node.right = new Node();

            update(node.right, mid + 1, end, l, r);
        }

        int leftMax = (node.left == null) ? 0 : node.left.max;
        int rightMax = (node.right == null) ? 0 : node.right.max;

        node.max = Math.max(leftMax, rightMax);
    }

    public int book(int startTime, int endTime) {
        update(root, 0, 1000000000, startTime, endTime - 1);
        return root.max;
    }
}
