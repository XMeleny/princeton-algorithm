import javafx.scene.effect.Blend;

import java.util.*;

class Solution {

    public boolean isPossible(int[] target) {
        //当前最大值是上一个数组的和
        //所以可以用最大值减去剩下的元素和
        //得到该位置上的值
        int size = target.length;
        int sum = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < size; i++) {
            queue.offer(target[i]);
            sum += target[i];
        }

        int current;
        int previous;
        int rest;

        while (sum != size) {
            current = queue.poll();
            rest = sum - current;
            previous = current - rest;
            if (previous > current || previous < 1) return false;
            sum -= rest;
            queue.offer(previous);
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isPossible(new int[]{9, 3, 5}));//t
        System.out.println(s.isPossible(new int[]{1, 1, 1, 2}));//f
        System.out.println(s.isPossible(new int[]{8, 5}));//t

    }

}