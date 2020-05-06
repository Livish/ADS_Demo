package fibonacci_heap;

public class FibonNode<T extends Comparable<T>> {

    public T key;
    
    public FibonNode<T> child;
    
    public FibonNode<T> left;
    
    public FibonNode<T> right;
    
    public boolean mark;
    
    public FibonNode<T> parent;
    
    public int degree;
    
    public FibonNode(T key){
        this.degree = 0;
        this.key = key;
        this.parent = null;
        this.child = null;
        this.left = null;
        this.right = null;
        this.mark = false;
    }
}
