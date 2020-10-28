package com.study.algorithm.list;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    // 1 0
    // 2 1
    // 3 3

    //Cm2

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        int n = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int m = entry.getValue();
            n += m * (m - 1) / 2;
        }
        return n;
    }

}
