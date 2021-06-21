# Interview Problems Tips

# TODO
- create quizlet
    - data structures / algos
        - runtime & memory complexity
        - pros & cons
        - what to look for to know when to use
    - java
        - different keywords
    - SQL
        - basic operations and what they do
    - infrastructure
        - what to consider
        - different ways to implement principles (scalability, parallelization)
    - testing
        - unit, integration, baseline, stress
    - coding principles
        - builder, singleton, factory, mvc
    - databases
        - differences between major types (sql, no-sql)
        - considerations when choosing a database (pricing, speed)
    - technologies
        - how popular technologies work (aws, kubernetes, docker, load balancers)
        - jenkins
    - basic math
        - combinatorics & probability (how to do, basic practice questions)

### general
- what are all the possible cases/combinations?
    - what's the worst valid case?

### arrays
- does starting from the left or right make a difference?
- will sorting the array help?

### `char`
- if the problem involves counting/comparing chars in strings, consider using int[] to keep track of char count where int[0] = a, int[25] = z. you avoid expensive string iterations.

### Dynamic Programming
- if the problem involves finding ALL possibilities, consider using dynamic programming / recursion / caching.
- you can use recursion to go backwards to the first state, then start caching, building off the base cases. (coin change)

### DFS
runtime: O(v+e)

- recursion to explore deep into one neighbor branch
- cycle = run into node that is currently being explored (not finished yet)
- good for finding cycles

### BFS
runtime: O(v+e)

- todo queue, loop until queue is empty
- add all neighbors w/ each iteration

### k smallest/largest
- if order doesn't matter, then find the kth largest/smallest. then, iterate through all possible elements and return all that are larger/smaller.

# geeksforgeeks top 25
1.  LowestCommonAncestor.java
2.  Other -> MaxProduct3.java
3.  BST -> RightSideView.java
6.  https://leetcode.com/problems/middle-of-the-linked-list/
7.  https://leetcode.com/problems/swap-nodes-in-pairs/submissions/
14. https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/
16. https://leetcode.com/problems/implement-stack-using-queues/submissions/

# other resources
- https://dev.to/bitpunchz/51-important-algorithms-coding-interview-questions-asked-at-faang-chf
