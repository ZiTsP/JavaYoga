package zitsp.programmingyoga.book.progbrain;

import java.util.stream.IntStream;

/**
 * @author zitsp
 * "プログラマ脳を鍛える数学パズル - 増井敏克"　より
 * p.35 Q07:日付の逆文
 */

public class BinaryDate {
    
    private class YMD {
        
        private final int year;
        private final int month;
        private final int day;
        private final int val;
        
        @SuppressWarnings("unused")
        public YMD(String YYYYMMDD) {
            this(Integer.parseInt(YYYYMMDD));
        }

        public YMD(int YYYYMMDD) {
            this((YYYYMMDD / 10000), ((YYYYMMDD / 100) % 100), (YYYYMMDD % 100));
        }

        public YMD(int YYYY, int MM, int DD) {
            this.day = DD;
            this.month = MM;
            this.year = YYYY;
            this.val = Integer.parseInt(this.toString());
        }
        
        public int getIntVal() {
            return this.val;
        }

        public String toString() {
            StringBuffer str = new StringBuffer();
            str.append(zeroFill(year, 4));
            str.append(zeroFill(month, 2));
            str.append(zeroFill(day, 2));
            return str.toString();
        }
        
        public String zeroFill(int val, int length) {
            String valStr = String.valueOf(val);
            int index = length - valStr.length();
            if (index < 0) {
                return "";
            } else if (index == 0) {
                return valStr;
            } else {
                StringBuffer str = new StringBuffer();
                while (index > 0) {
                    str.append("0");
                    index -= 1;
                }
                str.append(valStr);
                return str.toString();
            }
            
        }
        
        public boolean isValid() {
            if (year < 0 || 9999 < year || month < 0 || 12 < month || day < 0 || 99 < day) {
                return false;
            }
            switch (month) {
            case 1 :
            case 3 :
            case 5 :
            case 7 :
            case 8 :
            case 10 :
            case 12 :
                return (day <= 31) ? true : false;
            case 4 :
            case 6 :
            case 9 :
            case 11 :
                return (day <= 30) ? true : false;
            case 2 :
                if ((year % 4) == 0) {
                    return (day <= 29) ? true : false;
                } else {
                    return (day <= 28) ? true : false;
                }
            default :
                return false;
            }
        }
        
        public YMD next() {
            YMD newYMD = new YMD(year, month, day + 1);
            if (newYMD.isValid()) {
                return newYMD;
            }
            newYMD = new YMD(year, month + 1, 1);
            if (newYMD.isValid()) {
                return newYMD;
            }
            newYMD = new YMD(year + 1, 1, 1);
            if (newYMD.isValid()) {
                return newYMD;
            } else {
                return null;
            }
        }
    }


    private static boolean isPalidrome(int val, int radix) {
        String str = Integer.toString(val, radix);
        char nums[] = str.toCharArray();
        return IntStream.range(0, nums.length / 2).allMatch(i -> nums[i] == nums[nums.length - 1 - i]);
    }
    
    public BinaryDate(int start, int end) {
        YMD ymd = new YMD(start);
        YMD endYMD = new YMD(end);
        if (!ymd.isValid() || !endYMD.isValid()) { 
            return;
        }
        while (ymd.getIntVal() <= end) {
            if (isPalidrome(ymd.getIntVal(), 2)) {
                System.out.println(ymd.getIntVal() + " is palidrome (" + Integer.toBinaryString(ymd.getIntVal()) + ")");
            }
            ymd = ymd.next();
        }
//        YMD ymd = new YMD(19660713);
//        System.out.println(ymd.toString());
//        System.out.println(ymd.getIntVal() + " is palidrome (" + Integer.toBinaryString(ymd.getIntVal()) + ")");
    }
    
    public static void main(String[] args) {
        new BinaryDate(19641010, 20200724);
    }

}
