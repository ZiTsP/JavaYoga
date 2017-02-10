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
    
    private OptionalInt searchPalidrome(int begin) {
//        Streamだとエラー落ちするので
//        return IntStream.range(10, Integer.MAX_VALUE)
//                .filter(i -> isDecPalidrome(i) && isOctPalidrome(i) && isBinPalidrome(i))
//                .findFirst();
        int i = begin;
        while (i < Integer.MAX_VALUE) {
            if (isDecPalidrome(i) && isOctPalidrome(i) && isBinPalidrome(i)) {
                return OptionalInt.of(i);
            } else {
                i += 1;
            }
        }
        return OptionalInt.empty();
    }

    private boolean isOctPalidrome(int val) {
        return this.isPalidrome(val, 8);
    }
    
    private boolean isBinPalidrome(int val) {
        return this.isPalidrome(val, 2);
    }
    private boolean isDecPalidrome(int val) {
        return this.isPalidrome(val, 10);
    }
    
    private boolean isPalidrome(int val, int base) {
        if (base == 2 || base == 8 || base == 10) {
            char nums[] = this.numToString(val, base).toCharArray();
            return IntStream.range(0, nums.length / 2).allMatch(i -> nums[i] == nums[nums.length - 1 - i]);
        } else {
            return false;
        }
    }
    
    private String numToString(int val, int base) {
        if (base == 10) {
            return Integer.toString(val);
        } else if (base == 8) {
            return Integer.toOctalString(val);
        } else if (base == 2) {
            return Integer.toBinaryString(val);
        } else {
            return null;
        }
    }
    
    
	public static void main(String[] args) {
	    Palindrome p = new Palindrome();
	    System.out.println(p.searchPalidrome(10));
	}

}
