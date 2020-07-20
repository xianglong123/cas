package com.cas.owner.algorithm.数组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 21:11 2020-07-15
 * @version: V1.0
 * @review: 扑克牌抽取一张，然后找出来，空间复杂度O(1)
 */
public class 扑克牌丢失问题 {

    public static void main(String[] args) {
        int delIndex = 15;
        int N = 13;
        getDelPk(delIndex, N);
    }

    /**
     * @param delIndex 删除节点 1 - 51
     * @param N        最大自然数
     */
    public static void getDelPk(int delIndex, int N) {
        if (delIndex > 50 || delIndex <= 0) {
            System.out.println("删除节点异常");
            return;
        }

        int count = 0;
        List<Pk> pks = new ArrayList<>();

        // 需要删除数据
        for (int i = 1; i <= N; i++) {
            count += i;
            pks.add(new Pk(i, PK_FLOW.F));
            pks.add(new Pk(i, PK_FLOW.M));
            pks.add(new Pk(i, PK_FLOW.H));
            pks.add(new Pk(i, PK_FLOW.T));
        }
        // 丢失一个节点
        Pk delPk = pks.get(delIndex);
        System.out.println("选择花色  [" + delPk.getPkFlow().type + "]  丢失号数 ----  " + delPk.num);

        pks.remove(delPk);
        // 查找丢失数据
        Map<String, Integer> map = new HashMap<>();
        for (Pk pk : pks) {
            map.put(pk.getPkFlow().type, pk.getNum() + (map.get(pk.getPkFlow().type) == null ? 0 : map.get(pk.getPkFlow().type)));
        }

        // 找出丢失数据在哪个类型中 11 12 13 + 55

        for (PK_FLOW pkFlow : PK_FLOW.values()) {
            if (!(map.get(pkFlow.type) == count)) {
                System.out.println("丢失花色  [" + pkFlow.type + "]  丢失号数 ----  " + (count - map.get(pkFlow.type)));
            }
        }
        System.out.println("");
    }

    static class Pk {
        int num;
        PK_FLOW pkFlow;


        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public PK_FLOW getPkFlow() {
            return pkFlow;
        }

        public void setPkFlow(PK_FLOW pkFlow) {
            this.pkFlow = pkFlow;
        }

        Pk(int num, PK_FLOW pkFlow) {
            this.num = num;
            this.pkFlow = pkFlow;
        }
    }

    enum PK_FLOW {
        M("梅花"),
        F("方块"),
        H("红桃"),
        T("黑桃");

        private String type;

        PK_FLOW(String type) {
            this.type = type;
        }
    }


}
