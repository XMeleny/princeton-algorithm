import java.util.*;

class Solution {


    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // 一个点
        // 只有根的入度为0，其余的入度为1

        int[] in = new int[n];
        int[] test = new int[3];//0,1,>=2

        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1)
                in[leftChild[i]]++;
            if (rightChild[i] != -1)
                in[rightChild[i]]++;
        }

        for (int i = 0; i < n; i++) {
            if (in[i] > 2) {
                test[2]++;
            } else {
                test[in[i]]++;
            }
        }

        if (test[0] != 1 || test[2] > 0) return false;
        return true;
    }


    public int[] closestDivisors(int num) {
        //how to divide?
        //获得num+1的因数和num+2的因数
        ArrayList<Integer> factor1 = new ArrayList<>();
        ArrayList<Integer> factor2 = new ArrayList<>();

        divide(factor1, num + 1);
        divide(factor2, num + 2);

        //handle factor1 & factor2
        int[] result1 = separate(factor1);
        int[] result2 = separate(factor2);

        int div1 = Math.abs(result1[0] - result1[1]);
        int div2 = Math.abs(result2[0] - result2[1]);

        if (div1 < div2) return result1;
        return result2;
    }

    private void divide(ArrayList<Integer> result, int num) {
        boolean flag = false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            while (num % i == 0) {
                result.add(i);
                num = num / i;
                flag = true;
            }
            if (i > num) break;
        }
        if (!flag) result.add(1);
        if (num != 1) result.add(num);
    }

    private int[] separate(ArrayList<Integer> input) {
        int result1 = 1;
        int result2 = 1;
        for (int i = input.size() - 1; i >= 0; i--) {
            if (result1 <= result2) result1 *= input.get(i);
            else result2 *= input.get(i);
        }

        return new int[]{result1, result2};
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        return search(s, 0, new HashSet<>(wordDict), new Boolean[s.length()]);
    }

    public boolean search(String s, int start, HashSet<String> set, Boolean[] check) {
        if (start == s.length()) return true;

        if (check[start] != null) return check[start];

        for (String str : set) {
            if (s.startsWith(str, start) && search(s, start + str.length(), set, check)) {
                return check[start] = true;
            }
        }

        return check[start] = false;
    }


    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue;
                if (nums[j] < nums[i]) result[i]++;
            }
        }
        return result;
    }

    public String rankTeams(String[] votes) {
        HashMap<Character, int[]> map = new HashMap<>();

        char[] result = new char[votes[0].length()];

        for (String string : votes) {
            for (int i = 0; i < string.length(); i++) {
                char ch = string.charAt(i);
                if (map.containsKey(ch)) {
                    map.get(ch)[i]++;
                } else {
                    int[] temp = new int[26];
                    temp[i] = 1;
                    map.put(ch, temp);
                }
            }
        }

        return new String(result);
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfs(head, root, new Stack<>());
    }

    private boolean dfs(ListNode head, TreeNode root, Stack<Integer> integerStack) {
        if (root == null) return false;

        integerStack.push(root.val);

        if (check(head, integerStack)) return true;

        if (root.left == null && root.right == null) {
            integerStack.pop();
            return false;
        }

        if (dfs(head, root.left, integerStack) || dfs(head, root.right, integerStack)) return true;

        integerStack.pop();
        return false;
    }

    private boolean check(ListNode head, Stack<Integer> stack) {
        if (head == null) return true;
        if (stack == null) return false;


        ListNode cur = head;
        for (int i = 0; i < stack.size(); i++) {
            for (int j = i; j < stack.size(); j++) {
                if (cur == null) return true;
                if (stack.get(j) == cur.val) cur = cur.next;
                else break;
            }
            if (cur == null) return true;
            cur = head;
        }
        return false;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        Solution s = new Solution();


    }

}