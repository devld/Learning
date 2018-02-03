### Algorithms-Part-I Interview Questions

- Union-find with specific canonical element

    Add a method find() to the union-find data type so that find(i) returns the largest element in the connected component containing i. The operations, union(), connected(), and find() should all take logarithmic time or better.
    
    For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9 for each of the four elements in the connected components.

- Successor with delete

    Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:

    - Remove x from S
    - Find the successor of x: the smallest y in S such that y≥x.

    design a data type so that all operations (except construction) take logarithmic time or better in the worst case.

- Search in a bitonic array

    An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer values, determines whether a given integer is in the array.

    - Standard version: Use ∼3lgn compares in the worst case.
    - Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can guarantee to perform fewer than ∼2lgn compares in the worst case).