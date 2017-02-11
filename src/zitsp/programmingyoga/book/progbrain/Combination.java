package zitsp.programmingyoga.book.progbrain;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Combination {

/**
 * @author zitsp
 * "プログラマ脳を鍛える数学パズル - 増井敏克"　より
 * p.13 Q02:四則演算と一致する逆文
 */
    
    private final int target;
    private final int base;
    private final int[] bases;
    private final int operatorsMax;

    public Combination(int val) {
        base = val;
        target = this.reverseDecimal();
        bases = this.divide();
        operatorsMax = this.getMaxOperatorPattern();
    }
    
    private int reverseDecimal() {
        return reverseDecimal(this.base);
    }
    
    private static int reverseDecimal(int val) {
        char nums[] = String.valueOf(Integer.toString(val)).toCharArray();
        StringBuffer str = new StringBuffer();
        IntStream.range(0, nums.length).forEachOrdered(i -> str.append(nums[nums.length - 1 -i]));
        return Integer.parseInt(str.toString());
    }
    
//    組み合わせ生成器 -> 演算子で代用するので不要
//    private List<int[]> dividedList() {
//        List<int[]> list = new ArrayList<>();
//        int i = 0;
//        for (i = 0; i < String.valueOf(base).length() - 1; i++) {
//            int a = Integer.parseInt(String.valueOf(base).substring(0, i + 1));
//            int b = Integer.parseInt(String.valueOf(base).substring(i+1));
//            list.addAll(divide(new int[]{a}, b));
//        }
//        return list;
//    }
//    
//    private List<int[]> divide(int[] prefix, int val) {
//        List<int[]> list = new ArrayList<>();
//        int[] addArray = Arrays.copyOf(prefix, prefix.length + 1);
//        addArray[addArray.length - 1] = val;
//        list.add(addArray);
//        Arrays.asList(addArray).forEach(e -> System.out.print(e+","));
//        System.out.println("");
//        int i = 0;
//        for (i = 0; i < String.valueOf(val).length() - 1; i++) {
//            int[] newArray = Arrays.copyOf(prefix, prefix.length + 1);
//            newArray[newArray.length - 1] = Integer.parseInt(String.valueOf(val).substring(0, i + 1));
//            int b = Integer.parseInt(String.valueOf(val).substring(i+1));
//            list.addAll(divide(newArray, b));
//        }
//        return list;
//    }
    
    private int[] divide() {
        return divide(this.base);
    }
    
    private static int[] divide(int val) {
        char[] nums = String.valueOf(Integer.toString(val)).toCharArray();
        return IntStream.range(0, nums.length).map(i -> Integer.parseInt(String.valueOf(nums[i]))).toArray();
    }

    private int getMaxOperatorPattern() {
        return getMaxOperatorPattern(this.bases.length - 1);
    }
    
    private static int getMaxOperatorPattern(int size) {
        int i = 0;
        int length = OPERATORS.length;
        int max = length;
        for (i = 1; i < size; i += 1) {
            max *= length;
        }
//        System.out.println(length +"^"+size+" = 0 ~"+(max-1));
        return max - 1;
    }
    
    private int[] getOperatorArray(int val, int radix, int length) {
        String str = Integer.toString(val, radix);
        int fill = length - str.length();
        StringBuffer sb = new StringBuffer();
        while (fill > 0) {
            sb.append("0");
            fill -= 1;
        }
        sb.append(str);
        char[] chars = sb.toString().toCharArray();
        int[] array = new int[chars.length];
        int i = 0;
        for (i = 0; i < chars.length; i += 1) {
            array[i] = Integer.parseInt(Character.toString(chars[i]), radix);
        }
        return array;
    }
    
    private OptionalInt calc(int val, int op) {
        int[] nums = divide(val);
        if (nums.length <= 1 || op == 0) {
            return OptionalInt.of(nums[0]);
        }
        int[] ops = getOperatorArray(op, OPERATORS.length, nums.length - 1);
        int i = 0;
        Optional<Tuple> tuple = Optional.of(new Tuple(nums[i], nums[i + 1], OPERATORS[ops[i]]));
        for (i += 2; i < nums.length; i += 1) {
            if (!tuple.isPresent()) {
                return OptionalInt.empty();
            }
            tuple = priorCalc(tuple.get(), nums[i], OPERATORS[ops[i - 1]]);
        }
        if (!tuple.isPresent()) {
            return OptionalInt.empty();
        }
        return innerCalc(tuple.get());
    }
    

    private class Tuple {
        private int val1;
        private OPERATOR op1;
        private int val2;
        
         public Tuple(int val1, int val2, OPERATOR op) {
            this.val1 = val1;
            this.op1 = op;
            this.val2 = val2;
        }
    }
    
    private enum OPERATOR {
        NONE,
        ADD,
        MIN,
        MUL,
        DIV
    }
    
    private static final OPERATOR[] OPERATORS = OPERATOR.values();
    
    private Optional<Tuple> priorCalc(Tuple tuple, int val3, OPERATOR op2) {
        if (tuple.op1 == OPERATOR.NONE) {
            OptionalInt tmp = innerCalc(tuple);
            return (tmp.isPresent()) ? Optional.of(new Tuple(tmp.getAsInt(), val3, op2)) : Optional.empty();
        } else if (op2 == OPERATOR.NONE || op2 == OPERATOR.MUL || op2 == OPERATOR.DIV) {
//            return new Tuple(tuple.val1, innerCalc(tuple.val2, val3, op2), tuple.op1);
            OptionalInt tmp = innerCalc(tuple.val2, val3, op2);
            return (tmp.isPresent()) ? Optional.of(new Tuple(tuple.val1, tmp.getAsInt(), tuple.op1)) : Optional.empty();
        } else {
            OptionalInt tmp = innerCalc(tuple);
            return (tmp.isPresent()) ? Optional.of(new Tuple(tmp.getAsInt(), val3, op2)) : Optional.empty();
        }
    }

    private OptionalInt innerCalc(Tuple tuple) {
        return innerCalc(tuple.val1, tuple.val2, tuple.op1);
    }
    
    private OptionalInt innerCalc(int val1, int val2, OPERATOR op) {
        switch (op) {
        case NONE :
            StringBuffer sb = new StringBuffer();
            sb.append(String.valueOf(val1)).append(String.valueOf(val2));
            return OptionalInt.of(Integer.parseInt(sb.toString()));
        case ADD :
            return OptionalInt.of(val1 + val2);
        case MIN :
            return OptionalInt.of(val1 - val2);
        case MUL :
            return OptionalInt.of(val1 * val2);
        case DIV :
            return (val2 == 0) ? OptionalInt.empty() : OptionalInt.of(val1 / val2);
        }
        return OptionalInt.empty();
    }
    
    private String decode(int val, int op) {
        char[] nums = String.valueOf(Integer.toString(val)).toCharArray();
        int[] ops = getOperatorArray(op, OPERATORS.length, nums.length - 1);
        StringBuffer str = new StringBuffer();
        int i = 0;
        for (i = 0; i < nums.length - 1; i += 1) {
            str.append(nums[i]);
            switch (OPERATORS[ops[i]]) {
            case ADD :
                str.append(" + ");
                break;
            case MIN :
                str.append(" - ");
                break;
            case MUL :
                str.append(" * ");
                break;
            case DIV :
                str.append(" / ");
                break;
            }
        }
        str.append(nums[i]);
        return str.toString();
    }
    
    
    public void calcAllPatern() {
        int i = 1; // i=0の時は四則演算無しだから
        for (i = 1; i <= operatorsMax; i += 1) {
            OptionalInt res = calc(base, i);
            if (res.isPresent() && res.getAsInt() == target) {
                System.out.println(base + " : " +decode(this.base, i) + " = " + res.getAsInt());
            }
        }
    }
    
    
    
    public static void main(String[] args) {
        int i = 1000;
        for (; i < 10000; i ++) {
          Combination c = new Combination(i);
          c.calcAllPatern();
        }
//        IntStream.range(1000, 10000).forEach(i -> {
//            Combination c = new Combination(i);
//            c.calcAllPatern();
//        });
    }

}
