import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numberOfSteps(int num) {
        int result = 0;
        while (num != 0) {
            result++;
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = num - 1;
            }
        }
        return result;
    }

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int result = 0;
        int[] subArr = new int[k];
        for (int i = 0; i <= arr.length - k; i++) {
            subArr = Arrays.copyOfRange(arr, i, i + k);
            if (average(subArr) >= threshold) result++;
        }
        return result;
    }

    private double average(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return (double) sum / (double) arr.length;
    }

    public double angleClock(int hour, int minutes) {
        double h, m;
        h = hour * 30 + (double) minutes / 2;
        m = minutes * 6;

        if (h > m) {
            if (h - m > 180) return 360 - (h - m);
            else return h - m;
        } else {
            if (m - h > 180) return 360 - (m - h);
            else return m - h;
        }
    }

    class Node {
        int index;
        int move;
        boolean success;
        Node prev;

        public Node(int index, int move, boolean success, Node prev) {
            this.index = index;
            this.move = move;
            this.success = success;
            this.prev = prev;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj.getClass() == this.getClass())) return false;
            Node that = (Node) obj;
            if (this.index == that.index && this.prev == that.prev)
                return true;
            return false;
        }
    }

    ArrayList<Node> openList;

    public int minJumps(int[] arr) {
        int size = arr.length;
        if (size <= 2) return size - 1;
        int steps = Integer.MAX_VALUE;

        Node current;

        openList = new ArrayList<>();
        openList.add(new Node(0, 0, false, null));
        while (!openList.isEmpty()) {
            current = openList.remove(0);
            if (current.move >= steps) continue;
            if (current.index > 0) {
                if (!isInPrev(current.index - 1, current))
                    openList.add(new Node(current.index - 1, current.move - 1, false, current));
            }
            if (current.index < size - 1) {
                if (!isInPrev(current.index + 1, current)) {
                    if (current.index + 1 == size - 1 && current.move + 1 < steps) steps = current.move + 1;
                } else {

                }
            }
        }
        return 0;
    }

    boolean isInPrev(int index, Node current) {
        Node temp = current;
        while (temp != null) {
            if (index == temp.index) return true;
            temp = temp.prev;
        }
        return false;
    }

    public boolean checkIfExist(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (arr[i] * 2 == arr[j]) return true;
                if (arr[j] * 2 == arr[i]) return true;
            }
        }
        return false;
    }

    public int minSteps(String s, String t) {
        int result = 0;
        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();
        int sValue = 0;
        int tValue = 0;
        for (int i = 0; i < s.length(); i++) {
            if (sMap.containsKey(s.charAt(i))) {
                sValue = sMap.get(s.charAt(i)) + 1;
                sMap.put(s.charAt(i), sValue);
            } else sMap.put(s.charAt(i), 1);

            if (tMap.containsKey(t.charAt(i))) {
                tValue = tMap.get(t.charAt(i)) + 1;
                tMap.put(t.charAt(i), tValue);
            } else tMap.put(t.charAt(i), 1);
        }

        char ch;
        int dis = 0;
        for (Map.Entry<Character, Integer> entry : sMap.entrySet()) {
            ch = entry.getKey();
//            System.out.print("the char is : " + ch);
            sValue = entry.getValue();
//            System.out.print(" the svalue is : " + sValue);
            if (tMap.containsKey(ch)) {
                tValue = tMap.get(ch);
                if (sValue > tValue) {
                    dis = sValue - tValue;
//                    System.out.print(" the tvalue is : " + tValue);
//                    System.out.println();
                } else {
                    dis = 0;
                }
            } else
                dis = sValue;
            result += dis;
//            System.out.println(" the dis is " + dis);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(s.minJumps(new int[]{1, 2}));
//        System.out.println(s.checkIfExist(new int[]{10, 2, 5, 3}));
//        System.out.println(s.checkIfExist(new int[]{7, 1, 14, 11}));
//        System.out.println(s.checkIfExist(new int[]{3, 1, 7, 11}));

//        System.out.println(s.minSteps("bab", "aba"));
//        System.out.println(s.minSteps("leetcode", "practice"));
//        System.out.println(s.minSteps("anagram", "mangaar"));
//        System.out.println(s.minSteps("xxyyzz", "xxyyzz"));
//        System.out.println(s.minSteps("friend", "family"));


    }

}