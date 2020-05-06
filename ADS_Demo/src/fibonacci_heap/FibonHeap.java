package fibonacci_heap;

import java.util.HashMap;
import java.util.Map;

public class FibonHeap<T extends Comparable<T>> {

	private int keyNum;
	private FibonNode<T> min;
	private FibonNode<T> current;
	private Map<Integer, FibonNode<T>> degreeNodes;

	// Constructor
	public FibonHeap(T key) {
		min = new FibonNode<T>(key);
		keyNum += 1;
		min.left = min;
		min.right = min;
	}

	// Insert value
	public void insert(T key) {
		FibonNode<T> node = new FibonNode<T>(key);
		insert(node);
	}

	// Delete Min
	public void deleteMin() {
		degreeNodes = new HashMap<Integer, FibonNode<T>>();
		removeMinNode();
		consolidate();

	}

	// Delete Node
	public void deleteNode(FibonNode<T> node) {
		T everSmall = null;
		decrease(node, everSmall);
		deleteMin();
	}

	// Union heap
	public FibonHeap<T> union(FibonHeap<T> heapA, FibonHeap<T> heapB) {
		FibonNode<T> minA = heapA.min;
		FibonNode<T> minB = heapB.min;
		minA.right = minB;
		minA.right.left = minB.right;
		minB.left = minA;
		minB.right.left = minA.right;
		FibonNode<T> min = minA;
		if (minB.key.compareTo(minB.key) < 0) {
			min = minB;
		}
		heapA.min = min;
		heapA.keyNum += heapB.keyNum;
		return heapA;
	}

	private void insert(FibonNode<T> node) {
		
		min.left.right = node;
		node.left = min.left;
		node.right = min;
		min.left = node;
		T minKey = min.key;
		if (node.key.compareTo(minKey) < 0) {
			min = node;
		}
		keyNum += 1;
	}

	// Remove Min Node
	private void removeMinNode() {
		FibonNode<T> left = min.left;
		if (left == min) {

			if (min.child != null) {
				min = null;
			}
		} else {
			deleteInList(min);
			addChToR(min);
			min = left;
		}
		keyNum--;
	}

	
	private void consolidate() {
		current = min;
		do {
			current = putDegreeNodes(current);
			if (current.key.compareTo(min.key) < 0) {
				min = current;
			}
			current = current.right;
		} while (current != min && current.left != current);
	}

	
	private FibonNode<T> putDegreeNodes(FibonNode<T> node) {
		int nodeDegree = node.degree;
		
		FibonNode<T> nodeInMap = degreeNodes.get(nodeDegree);
		if (nodeInMap == null) {
			degreeNodes.put(nodeDegree, node);
		} else {
			if (node.key.compareTo(nodeInMap.key) < 0) {
				deleteInList(nodeInMap);
				nodeInMap.left = nodeInMap;
				nodeInMap.right = nodeInMap;
				node = fibLink(node, nodeInMap);
				nodeInMap = node;
			} else {
				deleteInList(node);
				node.left = node;
				node.right = node;
				nodeInMap = fibLink(nodeInMap, node);

				node = nodeInMap;
			}
			degreeNodes.put(nodeDegree, null);
			node = putDegreeNodes(node);
		}
		return node;
	}

	private FibonNode<T> fibLink(FibonNode<T> parent, FibonNode<T> child) {
		if (parent.child == null) {
			parent.child = child;

		} else {
			parent.child = insertCyle(parent.child, child);
		}
		child.parent = parent;
		parent.degree += 1;
		return parent;
	}

	
	private void deleteInList(FibonNode<T> node) {
		FibonNode<T> left = node.left;
		FibonNode<T> right = node.right;
		left.right = right;
		right.left = left;
	}

	
	private FibonNode<T> insertCyle(FibonNode<T> target, FibonNode<T> node) {
		FibonNode<T> left = target.left;
		left.right = node;
		node.left = target;
		node.right = target;
		target.left = node;
		return target;
	}

	
	private void addChToR(FibonNode<T> node) {
		FibonNode<T> aChild = node.child;
		if (aChild == null) {
			return;
		}
		do {
			
			FibonNode<T> right = aChild.right;
			min.right = insertCyle(min.right, aChild);
			aChild = right;

		} while (aChild != node.child);
	}

	public void decrease(FibonNode<T> target, T key) {
		FibonNode<T> parent = target.parent;
		if (target.key.compareTo(key) < 0) {
			System.out.println("只能减少key值");
			return;
		}
		if (parent == null) {

			target.key = key;
			if (key.compareTo(min.key) < 0) {

				min = target;
			}
			return;
		}
		if (parent.key.compareTo(key) < 0) {

			target.key = key;
			return;
		}
		cutAndMeld(target);
		parent = cascadingCut(parent);
	}

	private void cutAndMeld(FibonNode<T> target) {
		target.parent = null;
		target.mark = false;
		insert(target);
	}

	private FibonNode<T> cascadingCut(FibonNode<T> parent) {
		if (null == parent) {
			return null;
		}
		parent.degree--;
		if (parent.mark == false) {
			parent.mark = true;
		} else {
			cutAndMeld(parent);
			parent = cascadingCut(parent);
		}
		return parent;
	}

}
