/* *****************************************************************************
 *  Name:              冀全喜
 *  Coursera User ID:  errofatal89@gmail.com
 *  Last modified:     2021.11.05
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumPromblem {

    /**
     * 2-SUM实现
     * @param nums 数组
     * @param i 数组起始索引
     * @param target 目标数
     * @param result 为0的数组索引
     */
    public static void twoSum(int[] nums, int i, int target, List<List<Integer>> result) {
        int j = nums.length - 1;
        int b = i - 1;

        while (i < j) {
            if (nums[i] + nums[j] == target) {
                List<Integer> group = new ArrayList<>(3);
                group.add(nums[b]);
                group.add(nums[i]);
                group.add(nums[j]);
                result.add(group);

                i++;
                j--;
            } else {
                if (nums[i] + nums[j] < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }
    }

    /**
     * 选择一个数字进行2-SUM
     * @param nums 数组
     */
    public static void threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 为什么是-nums[i]？
            // 因为选择一个数出来作为目标数的负数，然后查找两个数相加等于这个目标数的组合
            // 这样三个数相加就得到了0
            twoSum(nums, i + 1, -nums[i], result);
        }
    }

    public static void main(String[] args) {
        // 全部是0
        int[] test = {0, 0, 0, 0, 0, 0};
        threeSum(test);


    }
}
