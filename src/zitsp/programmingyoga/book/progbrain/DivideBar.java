package zitsp.programmingyoga.book.progbrain;

import java.util.LinkedList;

public class DivideBar {

    private int length;
    private int divMax;
    
    public DivideBar(int n, int m) {
        length = n;
        divMax = m;
        que.add(n);
        divide();
    }
    
    public static void main(String[] args) {
        DivideBar div = new DivideBar(20, 3);
        div = new DivideBar(100, 5);
    }
    
    private LinkedList<Integer> que = new LinkedList<>();
    
    private void divide() {
        int count = 0;
        while (!que.isEmpty()) {
            count += 1;
            int indexMax = (que.size() < divMax) ? que.size() : divMax;
            int i;
            for (i = 0; i < indexMax; i += 1) {
                int tmp = que.removeFirst();
                if (tmp % 2 == 0) {
                    tmp = tmp / 2;
                    if (1 < tmp) {
                        que.add(tmp);
                        que.add(tmp);
                    }
                } else {
                    tmp = tmp /2;
                    if (1 < tmp) {
                        que.add(tmp);
                        que.add(tmp + 1);
                    } else {
                        que.add(tmp + 1);
                    }
                }
            }
        }
        System.out.println(length + "divided by lim of " + divMax + " : " + count);
    }

}
