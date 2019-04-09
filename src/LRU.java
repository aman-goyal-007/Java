import java.util.HashMap;
import java.util.Map;

public class LRU {
	
	public static void main(String...args){
		MYLRU<Integer,String> myLRU = new MYLRU(3);
		myLRU.put(1, "One");
		myLRU.put(2, "Two");
		myLRU.put(3, "Three");
		System.out.println(myLRU.getNode(2));
		myLRU.put(4, "Four");
		System.out.println(myLRU.getNode(1));
		System.out.println(myLRU.getNode(2));
		myLRU.put(5, "Five");
		System.out.println(myLRU.getNode(2));

	}

}

class MYLRU<K,V>{
	Map<K, Node<K,V>> myLRU;
	Node start,end;
	final int MAXSIZE;
	MYLRU(int size){
		MAXSIZE=size;
		myLRU = new HashMap<>(MAXSIZE);
	}

	public V getNode(K key){
		if(myLRU.containsKey(key)){
			Node node = myLRU.get(key);
			removeNode(node);
			addAtTop(node);
			return (V)node.getValue();
		}else{
			return null;
		}
	}
	
	public void put(K key, V value){
		if(myLRU.containsKey(key)){
			Node node = myLRU.get(key);
			node.setValue(node.getValue());
			removeNode(node);
			addAtTop(node);

		}else{
			Node newNode = new Node();
			newNode.setKey(key);
			newNode.setValue(value);
			if(myLRU.size()==MAXSIZE){
				myLRU.remove(end.getKey());
				removeNode(end);
			}

			addAtTop(newNode);
			myLRU.put(key, newNode);
		}
	}

	private void addAtTop(Node node){
		node.setRight(start);
		node.setLeft(null);
		if(start !=null){
			start.setLeft(node);
		}
		start = node;
		if(end == null){
			end = node;
		}
	}

	private void removeNode(Node deleteNode){
		if(deleteNode.getLeft()!=null){
			deleteNode.getLeft().setRight(null) ;
		}else{
			start = deleteNode.getRight();
		}

		if(deleteNode.getRight()!=null){
			deleteNode.getRight().setLeft(null);
		}else{
			end = deleteNode.getLeft();
		}
	}






	private class Node<K,V>{
		K key;
		V value;
		Node left;
		Node right;

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
	}


}
