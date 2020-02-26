import java.util.*;

public class Codec {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuffer stringBuffer = new StringBuffer();

        TreeNode cur;
        queue.offer(root);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur == null) {
                stringBuffer.append("null ");
            } else {
                stringBuffer.append(cur.val + " ");
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }

        String[] temp = stringBuffer.toString().split(" ");
        for (int i = temp.length - 1; i >= 0; i--) {
            if (!temp[i].equals("null")) {
                temp = Arrays.copyOfRange(temp, 0, i + 1);
                break;
            } else {
                temp[i] = "";
            }
        }
        String result = "[" + String.join(",", temp) + "]";

//        System.out.println(result);

        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        TreeNode root=null;

        //handle the string


        //using the string to make the tree

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));