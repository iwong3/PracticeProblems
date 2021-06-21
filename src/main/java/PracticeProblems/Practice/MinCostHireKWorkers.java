import java.util.*;

class MinCostHireKWorkers {

    public static void main(String[] args) {

        // quality = [10, 20,  5]
        // wage    = [70, 50, 30]
        // k       = 2
        // output = 105
        // explanation: cheapest total wage would be to hire worker 0 for 70.
        // then, you could hire worker 2 for 35 (half of 70 because 5 is half of 10)
        // all other combinations are more expensive

        int[] quality = new int[]{10, 20, 5};
        int[] wage = new int[]{70, 50, 30};
        int k = 2;

        System.out.println(minCostHireKWorkers(quality, wage, k) + " should be 105");

    }

    // Every worker performs with quality and for minimum wage at the same index 'i'
    // Hire k workers such that you minimize cost of wages
    // Every worker hired must have the same ratio of quality to wage
    public static double minCostToHireWorkers(int[] quality, int[] wage, int k) {
        return -1;
    }

}

// Solution - Greedy - O(N^2 logN) - fails for large data sets

// class Solution {
//     public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
//         int N = quality.length;
//         double ans = 1e9;

//         for (int captain = 0; captain < N; ++captain) {
//             // Must pay at least wage[captain] / quality[captain] per qual
//             double factor = (double) wage[captain] / quality[captain];
//             double prices[] = new double[N];
//             int t = 0;
//             for (int worker = 0; worker < N; ++worker) {
//                 double price = factor * quality[worker];
//                 if (price < wage[worker]) continue;
//                 prices[t++] = price;
//             }

//             if (t < K) continue;
//             Arrays.sort(prices, 0, t);
//             double cand = 0;
//             for (int i = 0; i < K; ++i)
//                 cand += prices[i];
//             ans = Math.min(ans, cand);
//         }

//         return ans;
//     }
// }

// Solution - Heap - O(NlogN)

// Intuition
// As in Approach #1, at least one worker is paid their minimum wage expectation.
// Additionally, every worker has some minimum ratio of dollars to quality that they demand. For example, if wage[0] = 100 and quality[0] = 20, then the ratio for worker 0 is 5.0.
// The key insight is to iterate over the ratio. Let's say we hire workers with a ratio R or lower. Then, we would want to know the K workers with the lowest quality, and the sum of that quality. We can use a heap to maintain these variables.

// Algorithm
// Maintain a max heap of quality. (We're using a minheap, with negative values.) We'll also maintain sumq, the sum of this heap.
// For each worker in order of ratio, we know all currently considered workers have lower ratio. (This worker will be the 'captain', as described in Approach #1.) We calculate the candidate answer as this ratio times the sum of the smallest K workers in quality.

// class Solution {
//     public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
//         int N = quality.length;
//         Worker[] workers = new Worker[N];
//         for (int i = 0; i < N; ++i)
//             workers[i] = new Worker(quality[i], wage[i]);
//         Arrays.sort(workers);

//         double ans = 1e9;
//         int sumq = 0;
//         PriorityQueue<Integer> pool = new PriorityQueue();
//         for (Worker worker: workers) {
//             pool.offer(-worker.quality);
//             sumq += worker.quality;
//             if (pool.size() > K)
//                 sumq += pool.poll();
//             if (pool.size() == K)
//                 ans = Math.min(ans, sumq * worker.ratio());
//         }

//         return ans;
//     }
// }

// class Worker implements Comparable<Worker> {
//     public int quality, wage;
//     public Worker(int q, int w) {
//         quality = q;
//         wage = w;
//     }

//     public double ratio() {
//         return (double) wage / quality;
//     }

//     public int compareTo(Worker other) {
//         return Double.compare(ratio(), other.ratio());
//     }
// }

// Solution - Other - O(NlogN)

// public double minCostToHireWorkers(int[] q, int[] w, int k) {
//     double[][] workers = new double[q.length][2];
//     for (int i = 0; i < q.length; i++) {
//         workers[i] = new double[]{(double)(w[i]) / q[i], (double)q[i]};
//     }
//     Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
//     double res = Double.MAX_VALUE, qsum = 0;
//     PriorityQueue<Double> pq = new PriorityQueue<>();
//     for (double[] worker : workers) {
//         qsum += worker[1];
//         pq.add(-worker[1]);
//         if (pq.size() > k) {
//             qsum += pq.poll();
//         }
//         if (pq.size() == k) {
//             res = Math.min(res, qsum * worker[0]);
//         }
//     }
//     return res;
// }