package com.jyjx.yxdl.controller.admin;

import com.jyjx.yxdl.common.BaseController;

import java.util.*;

public class AdminBaseController extends BaseController {

    protected Map<String,Object> displaySuccess(Object object){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("stateCode",1);
        resultMap.put("data",object);
        return resultMap;
    }


    public static void main(String[] args) {
//        int[] arr = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
//        int count = 0;
//        for (int i = 0;i < arr.length - 1;i++){
//            boolean flag = true;
//            for (int j = 0;j < arr.length - 1;j++){
//                if(arr[j] > arr[j+1] ){
//                    int temp = arr[j+1];
//                    arr[j+1] = arr[j];
//                    arr[j] = temp;
//                    flag = false;
//                }
//                count++;
//            }
//            if(flag){
//                break;
//            }
//        }
//        System.out.println(count);
//        System.out.println(Arrays.toString(arr));

//        int[] arr = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
//
//        for (int i = 0;i < arr.length-1;i++ ){
//            int index = i;
//            for(int j = i+1;j < arr.length;j++ ){
//                if(arr[j] < arr[index]){
//                    index = j;
//                }
//            }
//            int temp = arr[index];
//            arr[index] = arr[i];
//            arr[i] = temp;
//        }
//        System.out.println(Arrays.toString(arr));
        String str = "aa;aaa;aaaa;bb;bbb;bbbb;";
        System.out.println(Arrays.toString(str.split(";")));

//        int[] arr = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
//        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
//            // 对数组元素进行分组
//            for (int i = gap; i < arr.length; i++) {
//                // 遍历各组中的元素
//                for (int j = i - gap; j >= 0; j -= gap) {
//                    // 交换元素
//                    if (arr[j] > arr[j + gap]) {
//                        int temp = arr[j];
//                        arr[j] = arr[j + gap];
//                        arr[j + gap] = temp;
//                    }
//                }
//            }
//        }
//        System.out.println(arr);

    }


}
