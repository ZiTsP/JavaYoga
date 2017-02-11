package zitsp.programmingyoga.book.progbrain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zitsp
 * "プログラマ脳を鍛える数学パズル - 増井敏克"　より
 * p.33 Q06:コラッツの予想
 * ArrayよりListの方が効率が良かった(無駄に領域を確保しないので)
 */

public class Collatz {

    private final int startVal;
//  private static final int ARRAY_MAX = (1 << 24);
//    private boolean[] numArrival = new boolean[ARRAY_MAX];
    private List<Integer> arrival = new ArrayList<>();
    
    public Collatz(int num) {
        startVal = num;
//        Arrays.fill(numArrival, false);
//        numArrival[num] = true;
    }
    
    private boolean check() {
        int num = startVal * 3 + 1;
//        numArrival[num] = true;
        arrival.add(num);
        while (true) {
            num = calc(num);
            if (num == startVal) {
                arrival.clear();
                return true;
//            } else if (numArrival[num] == true) {
            } else if (arrival.contains(num)) {
                arrival.clear();
                return false;
            }
//            numArrival[num] = true;
            arrival.add(num);
        }
    }

//    純粋なコラッツならともかく
//    private static boolean[] numTrueArrival = new boolean[ARRAY_MAX];
//    private static boolean[] numFalseArrival = new boolean[ARRAY_MAX];
//    static {
//        Arrays.fill(numTrueArrival, false);
//        Arrays.fill(numFalseArrival, false);
//    }
//    
//    private void learn(boolean isReturnVal) {
//        int num = startVal * 3 + 1;
//        if (doesStop(num, isReturnVal) == true) {
//            return;
//        } else {
//            while (true) {
//                num = calc(num);
//                if (num == startVal) {
//                    return;
//                } else if (doesStop(num, isReturnVal) == true) {
//                    return;
//                }
//            }
//        }
//    }
//    
//    private boolean doesStop(int num, boolean isReturnVal) {
//        if (isReturnVal == true) {
//            if (numTrueArrival[num] == true) {
//                return true;
//            } else {
//                numTrueArrival[num] = true;
//                return false;
//            }
//        } else {
//            if (numFalseArrival[num] == true) {
//                return true;
//            } else {
//                numFalseArrival[num] = true;
//                return false;
//            }
//        }
//    }
    
    
    private int calc(int n) {
        return (n % 2 == 0) ? (n / 2) : (n * 3 + 1);
    }
    
    public static void main(String[] args) {
        int limit = 10000;
        int i;
        int count = 0;
        for (i = 2; i <= limit; i += 2) {
            Collatz c = new Collatz(i);
            if (c.check() == true) {
                System.out.println(i + " Returned");
                count += 1;
            }
        }
        System.out.println("COUNT : " + count);
    }
}
