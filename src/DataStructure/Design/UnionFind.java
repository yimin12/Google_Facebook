package DataStructure.Design;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/11/12 15:55
 *   @Description :
 *
 */
public class UnionFind {

    // The number of elements in this union find
    private int size;

    // Used to track the size of each of the component
    private int[] sz;

    // parent[i] points to itself if it is the root node
    private int[] parent;

    // Tracks the number of components in the union find (distinct set)
    private int numComponents;

    // Depth of current value if we regard uf as tree
    private int[] rank;

    public UnionFind(int size){
        if(size < 0) throw new IllegalArgumentException("Size should greater than 0");
        this.size = size;
        sz = new int[size];
        parent = new int[size];
        rank = new int[size];
        // init
        for(int i =0; i < size; i++){
            parent[i] = i; // Link to itself (self root)
            sz[i] = 1; // Each component is originally of size one
            rank[i] = 1;
        }
    }

    // Path compression in iterative way
    public int findIter(int p){
        int root = p;
        while(root != parent[root]) root = parent[root];
        // Compress the path leading back to the root.
        while(p != root){
            int next = parent[p];
            parent[p] = root;
            p = next;
        }
        return root;
    }

    // Path compression in recursive way, compress the path in backtracking
    public int findRecur(int p){
        if(p == parent[p]){
            return p;
        }
        return parent[p] = findRecur(parent[p]);
    }

    // Return size of the components (# of component in this group)
    public int componentSize(int p){
        return sz[findIter(p)];
    }

    // Return whether or not the elements are in the same set
    public boolean connected(int a, int b){
        return findIter(a) == findIter(b);
    }

    // Return number of elements in the same set
    public int components(){
        return numComponents;
    }

    // Return the # of element in this uf
    public int size(){
        return size;
    }

    // Connect the components
    public void unify(int a, int b){
        if(connected(a, b)) return;
        int root_a = findIter(a);
        int root_b = findIter(b);

        // Merge smaller component/set in the larger one.
        if(sz[root_a] < sz[root_b]){
            parent[root_a] = root_b;
            sz[root_b] += sz[root_a];
        } else {
            parent[root_b] = root_a;
            sz[root_a] += sz[root_b];
        }

        // Or you can merge base on the rank to reduce depth of find(). We will not use this one because we use compress path
//        if(rank[root_a] < rank[root_b]){
//            parent[root_a] = root_b;
//        } else if(rank[root_a] > rank[root_b]){
//            parent[root_b] = root_a;
//        } else {
//            parent[root_a] = root_b; // Or reverse it
//            rank[root_a] += 1;
//        }

        numComponents--;
    }
}
