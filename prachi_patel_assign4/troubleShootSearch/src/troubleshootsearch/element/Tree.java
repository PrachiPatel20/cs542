package troubleshootsearch.element;
import troubleshootsearch.element.MyTree;
class Tree {
  class Node {
    String key;
    Node left,right;
    public Node(String item) {
      key = item;
      left = right = null;
    }
  }
  String searchNode(Node root, String keyIn) 
  { 
    // Base Cases: root is null or key is present at root 
    if (root==null) {
      return null;
    } 
    if(keyIn.contains(root.key)) {
      System.out.println(root.key+"----Ch------");
      return root.key;
    } 
         
  
    // val is greater than root's key 
    if (keyIn.compareTo(root.key) < 0) {
        searchNode(root.left, keyIn); 
    }
    else if(keyIn.compareTo(root.key) > 0) {
      //val is less than root's key 
      searchNode(root.right, keyIn); 
    }
    return null;
  } 
  Node root;
  Tree() {
    root = null;
  }
  void insert(String key) {
    root = insertRec(root,key);
  }
  Node insertRec(Node root,String key) {
    if(root == null) {
      root = new Node(key); 
      return root;
    }
    
    if(key.compareTo(root.key) > 0) {
      root.right = insertRec(root.right, key);
    }
    else if(key.compareTo(root.key) < 0) {
      root.left = insertRec(root.left, key);
    }
    
    return root;
  }
  void inorder()  { 
    inorderRec(root); 
  } 
  String getNo(String key) {
    System.out.println(key);
    String s = searchNode(root,key);
    System.out.println(s);
    return s;
  }
  void inorderRec(Node root) { 
    if (root != null) { 
      inorderRec(root.left); 
      System.out.println(root.key); 
      inorderRec(root.right); 
    } 
  } 
}