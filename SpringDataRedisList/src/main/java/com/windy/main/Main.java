package com.windy.main;

import com.windy.service.ListService;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/6 15:20
 */
public class Main {
    public static void main(String[] args) {
        ListService listService = new ListService();
        try {
            listService.in();
            listService.range();
//            listService.out();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
