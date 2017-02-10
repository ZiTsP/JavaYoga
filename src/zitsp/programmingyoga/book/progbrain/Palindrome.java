package zitsp.programmingyoga.book.progbrain;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author zitsp
 * "プログラマ脳を鍛える数学パズル - 増井敏克"　より
 * p.13 Q01:10進数で回文
 */


public class Palindrome {
    
    public Palindrome() {
    }
    
    private static OptionalInt searchNumPalidrome(int begin) {
//        Streamだとエラー落ちするので
//        return IntStream.range(10, Integer.MAX_VALUE)
//                .filter(i -> isDecPalidrome(i) && isOctPalidrome(i) && isBinPalidrome(i))
//                .findFirst();
        int i = begin;
        while (i < Integer.MAX_VALUE) {
            if (isNumPalidrome(i, 10) && isNumPalidrome(i, 8) && isNumPalidrome(i, 2)) {
                return OptionalInt.of(i);
            } else {
                i += 1;
            }
        }
        return OptionalInt.empty();
    }
    
    private static boolean isNumPalidrome(int val, int base) {
        String str = null;
        switch (base) {
        case 10:
            str = Integer.toString(val);
            break;
        case 16:
            str = Integer.toHexString(val);
            break;
        case 8:
            str = Integer.toOctalString(val);
            break;
        case 2:
            str = Integer.toBinaryString(val);
            break;
        default :
            return false;    
        }
        char nums[] = str.toCharArray();
        return IntStream.range(0, nums.length / 2).allMatch(i -> nums[i] == nums[nums.length - 1 - i]);
    }
    
    
	public static void main(String[] args) {
        System.out.println(Palindrome.searchNumPalidrome(10));
	}

}
