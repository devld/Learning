### Algorithms-Part-I Interview Questions

- [Union-find with specific canonical element](src/UnionFindMaxElement.java)

    Add a method find() to the union-find data type so that find(i) returns the largest element in the connected component containing i. The operations, union(), connected(), and find() should all take logarithmic time or better.
    
    For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9 for each of the four elements in the connected components.

- [Successor with delete](src/SuccessorWithDelete.java)

    Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:

    - Remove x from S
    - Find the successor of x: the smallest y in S such that y≥x.

    design a data type so that all operations (except construction) take logarithmic time or better in the worst case.

- [Search in a bitonic array](src/SearchBitonic.java)

    An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer values, determines whether a given integer is in the array.

    - Standard version: Use ∼3lgn compares in the worst case.
    - Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can guarantee to perform fewer than ∼2lgn compares in the worst case).

- [3-SUM in quadratic time](src/Sum3.java)
  
    Design an algorithm for the [3-SUM](https://en.wikipedia.org/wiki/3SUM) problem that
    takes time proportional to n2 in the worst case.
    You may assume that you can sort the n integers
    in time proportional to n^2 or better.

- [Egg drop](src/EggDrop.java)

    Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs. An egg breaks if it is dropped from floor T or higher and does not break otherwise. Your goal is to devise a strategy to determine the value of T given the following limitations on the number of eggs and tosses:

    - Version 0: 1 egg, ≤T tosses.
    - Version 1: ∼1lgn eggs and ∼1lgn tosses.
    - Version 2: ∼lgT eggs and ∼2lgT tosses.
    - Version 3: 2 eggs and ∼2n√ tosses.
    - Version 4: 2 eggs and ≤cT−−√ tosses for some fixed constant c.
