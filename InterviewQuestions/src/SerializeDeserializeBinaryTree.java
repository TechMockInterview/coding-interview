import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBinaryTree {
     //Definition for a binary tree node.
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public String serialize(TreeNode root) { //Serialization: takes a tree node, returns a string representing the node
        if (root==null) return "";
        Queue<TreeNode> qu=new LinkedList<>(); //Using queue for level-oder traversal of binary tree (BFS)
        StringBuilder sb=new StringBuilder();
        qu.offer(root);
        sb.append(String.valueOf(root.val));
        sb.append(' '); //Nodes separated by ' ' character
        while (!qu.isEmpty()) {
            TreeNode x=qu.poll();
            if (x.left==null) sb.append("null "); // Handling nodes with no left child
            else {
                qu.offer(x.left);
                sb.append(String.valueOf(x.left.val)); // Left node ID is added first to the string
                sb.append(' ');
            }
            if (x.right==null) sb.append("null "); // Handling nodes with no right child
            else {
                qu.offer(x.right);
                sb.append(String.valueOf(x.right.val)); // Right node ID is added last to the string
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) { //Deserialization: takes a string, returns the root node of the tree it represents
        if (data.length()==0) return null;
        String[] node=data.split(" "); //Split the string to a string array for easier iteration
        Queue<TreeNode> qu=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.valueOf(node[0])); //Make tree root with '0' as its identifier
        qu.offer(root);
        int i=1;
        // For each node in the queue, its left and right children are assigned based on the level-order traversal
        while (!qu.isEmpty()) {
            TreeNode x=qu.poll();
            if (node[i].equals("null")) x.left=null; // Handling nodes with no left child
            else {
                x.left=new TreeNode(Integer.valueOf(node[i]));
                qu.offer(x.left); //Child node is added to queue for further traversal of tree. Left node should be first.
            }
            i++;
            if (node[i].equals("null")) x.right=null; // Handling nodes with no right child
            else {
                x.right=new TreeNode(Integer.valueOf(node[i]));
                qu.offer(x.right); //Child node is added to queue for further traversal of tree. Right node should be last.
            }
            i++;
        }

        return root;
    }
}

