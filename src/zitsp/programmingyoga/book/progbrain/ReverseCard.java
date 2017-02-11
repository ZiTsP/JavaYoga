package zitsp.programmingyoga.book.progbrain;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class ReverseCard {

/**
 * @author zitsp
 * "プログラマ脳を鍛える数学パズル - 増井敏克"　より
 * p.21 Q03:n飛ばしのカード反転
 */
    private int[] cardNums;
    private boolean[] cardStatus;
    
    public ReverseCard(int start, int end) {
        cardNums = IntStream.rangeClosed(start, end).toArray();
        cardStatus = new boolean[cardNums.length];
        Arrays.fill(cardStatus, false);
        int i = 0;
//        System.out.println(printList(true));
        for (i = 1; i < cardStatus.length; i += 1 ) {
            reverse(i);
//            System.out.println(printList(true));
        }
        System.out.println(printList(false));
    }
    
    private void reverse(int n) {
        int i;
        for (i = n; i < cardStatus.length; i += (n + 1)) {
            cardStatus[i] = (cardStatus[i] == true) ? false : true;
        }
    }
    
    private String printList(boolean status) {
        int i;
        StringJoiner str = new StringJoiner(",");
        for (i = 0; i < cardStatus.length; i += 1) {
            if (cardStatus[i] == status) {
                str.add(String.valueOf(cardNums[i]));
            }
        }
        return str.toString();
    }
    
    
    public static void main(String[] args) {
        ReverseCard cards = new ReverseCard(1, 100);
    }

}
