package Mar4.Stack;

import java.util.Stack;

public class InorderStack {

    TreeNode root;

    void inorderStack(){

        if(root==null) {
            return;

        }
        Stack<TreeNode> stackInorder = new Stack<TreeNode>();

        TreeNode current = root;

        //traversing the tree

        while(current != null || stackInorder.size()>0){

            //reach left most node of current node

            while(current !=null){

                //traversing the left subtree:

                stackInorder.push(current);

                current = current.left;

            }

            //current must be null at this point
            current = stackInorder.pop();

            System.out.println(current.data + " ");

            //we have visited node and its left subtree , now same for the right
            current =current.right;

        }
    }
    public static void main(String[]args){

        InorderStack tree = new InorderStack();

        tree.root = new TreeNode(1);

        tree.root.left = new TreeNode(2);

        tree.root.right = new TreeNode(3);

        tree.root.left.left = new TreeNode(4);

        tree.root.left.right = new TreeNode(5);

        tree.inorderStack();




    }
}
