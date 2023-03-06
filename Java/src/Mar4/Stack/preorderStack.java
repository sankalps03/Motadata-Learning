package Mar4.Stack;

import java.util.Stack;

class TreeNode{

    int data;
    TreeNode left, right;

    TreeNode(int data){
        this.data=data;
        left=right=null;
    }
}
public class preorderStack {

    TreeNode root;

    void preorderIterative(){
        preorderIterative(root);
    }


    void preorderIterative(TreeNode node){

        if(node==null){

            return;
        }

        Stack<TreeNode> preorderStack = new Stack<>();

        TreeNode current =node;

        while(current !=null || !preorderStack.isEmpty()){

            while(current !=null){

                System.out.println(current.data + " ");


                //store right child in stack and print left's data
                if(current.right !=null){

                    preorderStack.push(current.right);
                }

                current = current.left;
            }

            if(!preorderStack.isEmpty()){

                current = preorderStack.pop();
            }



        }


    }

    public static void main(String[]args){

        preorderStack tree = new preorderStack();

        tree.root = new TreeNode(10);

        tree.root.left = new TreeNode(20);

        tree.root.right = new TreeNode(30);

        tree.root.left.left = new TreeNode(40);

        tree.root.left.left.left = new TreeNode(70);

        tree.root.left.right = new TreeNode(50);

        tree.preorderIterative();


    }

}