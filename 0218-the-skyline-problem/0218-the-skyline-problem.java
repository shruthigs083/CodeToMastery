
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<List<Integer>> result = new ArrayList<>();
        List<int[]> events = new ArrayList<>();

        for (int[] b : buildings) {
            int left = b[0];
            int right = b[1];
            int height = b[2];

            events.add(new int[]{left, -height});
            events.add(new int[]{right, height});
        }

        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        PriorityQueue<Integer> pq =
            new PriorityQueue<>(Collections.reverseOrder());

        pq.offer(0);

        int prevMax = 0;

        for (int[] event : events) {

            int x = event[0];
            int h = event[1];

            if (h < 0) {
                pq.offer(-h);
            } else {
                pq.remove(h);
            }

            int currMax = pq.peek();

            if (currMax != prevMax) {
                result.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return result;
    }
}
