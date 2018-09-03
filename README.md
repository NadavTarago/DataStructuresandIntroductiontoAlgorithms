# DataStructuresandIntroductiontoAlgorithms
This my final project in the course Data Structures and Introduction to Algorithms. 
The assigment is: 

Known that in Binary Search Tree exist n+1 left and right pointers that their values is NIL. in other words, half of the memory that contains the links is wasted. we will make the following change in each node z in the tree: if left[z] = NIL, we give to left[z] the value of TREE-PREDECESSOR(z). If right[z] = NIL , we give to right[z] the value of TREE-SUCCESSOR(z). tree that built that way named Threaded Binary Search Tree. His links named "wires".
a. how can you different between "wires" to a real sons of the given node? 
b. create a data structure of this kind of BST that will support insert, delete, search, return successor, return predecessor, return minimum and return maximum in linear time complexity to the height of the tree. 
c. add functions that return preorder, inorder, and postorder traversal scans in linear time complexity to the number of values in the tree. 
d. add function that return median in O(1). 
e. make it possible to enter input from keyboard and text file.
