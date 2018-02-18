### Algorithms-Part-I Interview Questions

- [Merging with smaller auxiliary array](src/MergeWithSmallerAux.java)

    Suppose that the subarray a[0] to a[n−1] is sorted and the subarray a[n] to a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0] to a[2∗n−1] is sorted using an auxiliary array of length n (instead of 2n)?

- [Counting inversions](src/CountInversion.java)
  
    An inversion in an array a[] is a pair of entries a[i] and a[j] 
    such that i<j but a[i]>a[j]. Given an array, 
    design a linearithmic algorithm to count the number of inversions.

- [Shuffling a linked list](src/ShuffleLinkedList.java)

    Given a singly-linked list containing n items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to nlogn in the worst case.

- [Nuts and bolts](src/NutsBolts.java)
  
    A disorganized carpenter has a mixed pile of n nuts and n bolts.
    The goal is to find the corresponding pairs of nuts and bolts.
    Each nut fits exactly one bolt and each bolt fits exactly one nut.
    By fitting a nut and a bolt together, the carpenter can see
    which one is bigger (but the carpenter cannot compare two nuts or two bolts directly).
    Design an algorithm for the problem that uses nlogn  compares (probabilistically).
 
- Selection in two sorted arrays

    Given two sorted arrays a[] and b[], of sizes n1 and n2, respectively, design an algorithm to find the kth largest key. The order of growth of the worst case running time of your algorithm should be logn, where n=n1+n2.

    - Version 1: n1=n2 and k=n/2
    - Version 2: k=n/2
    - Version 3: no restrictions

- [Decimal dominants](src/DecimalDominants.java)

    Given an array with n keys, design an algorithm to find all values that occur more than n/10 times. The expected running time of your algorithm should be linear.
    