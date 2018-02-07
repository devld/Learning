### Algorithms-Part-I Interview Questions

- [Queue with two stacks](src/QueueWithStacks.java)

    Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.

- [Stack with max](src/StackWithMax.java)

    Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are reals numbers so that you can compare them.

- [Intersection of two sets](src/IntersectionTwoSets.java)
    
    Given two arrays a[] and b[], each containing n distinct 2D points in the plane, design a subquadratic algorithm to count the number of points that are contained both in array a[] and array b[].

- [Permutation](src/Permutation.java)
    
    Given two integer arrays of size n, design a subquadratic algorithm to determine whether one is a permutation of the other. That is, do they contain exactly the same entries but, possibly, in a different order.

- [Dutch national flag](src/DutchNationalFlag.java)

    Given an array of n buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:

    - swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
    - color(i): determine the color of the pebble in bucket i.
    
    The performance requirements are as follows:

    - At most n calls to color().
    - At most n calls to swap().
    - Constant extra space.