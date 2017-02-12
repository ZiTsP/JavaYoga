package zitsp.programmingyoga.book.progbrain;

import java.util.Arrays;

/**
 * @author zitsp
 * "プログラマ脳を鍛える数学パズル - 増井敏克"　より
 * p.39 Q08:経路探索
 * 経路のうち、結果的に同じエリアを通るは両立してカウントするのか？しないのか？
 * 両立するとして、力技でカウント
 */

public class Rooting {

    private ROUTINE[] root;
    private int rootNum;
    private int moveMax = 0;
    
    private enum ROUTINE {UP, DOWN, RIGHT, LEFT}
    private static ROUTINE[] ROUTINES = {ROUTINE.UP, ROUTINE.DOWN, ROUTINE.RIGHT, ROUTINE.LEFT};
    
    
    public Rooting(int move, int rootNum) {
        moveMax = move;
        this.rootNum = rootNum;
        root = decode(rootNum);
    }
    
    private static int getAllWayID(int move) {
        int i = 0;
        int length = ROUTINES.length;
        int max = length;
        for (i = 1; i < move; i += 1) {
            max *= length;
        }
        return max - 1;
    }

    private ROUTINE[] decode(int rootNum) {
        String str = Integer.toString(rootNum, ROUTINES.length);
        int fill = moveMax - str.length();
        StringBuffer sb = new StringBuffer();
        while (fill > 0) {
            sb.append("0");
            fill -= 1;
        }
        sb.append(str);
        char[] chars = sb.toString().toCharArray();
        ROUTINE[] array = new ROUTINE[chars.length];
        int i = 0;
        for (i = 0; i < chars.length; i += 1) {
            array[i] = ROUTINES[Integer.parseInt(Character.toString(chars[i]), ROUTINES.length)];
        }
        return array;
    }
    @SuppressWarnings("unused")
    private String decodeToString() {
        String str = Integer.toString(this.rootNum, ROUTINES.length);
        int fill = moveMax - str.length();
        StringBuffer sb = new StringBuffer();
        while (fill > 0) {
            sb.append("0");
            fill -= 1;
        }
        sb.append(str);
        char[] chars = sb.toString().toCharArray();
        sb = new StringBuffer();
        int i = 0;
        for (i = 0; i < chars.length; i += 1) {
            sb.append(ROUTINES[Integer.parseInt(Character.toString(chars[i]), ROUTINES.length)].toString().substring(0, 1));
        }
        return sb.toString();
    }
    
    public boolean isValid() {
      int i;
      int[] junctionCount = new int[ROUTINES.length];
      for (ROUTINE r : this.root) {
          int index = Arrays.asList(ROUTINES).indexOf(r);
          if (0 <= index && index < ROUTINES.length) {
              junctionCount[index] += 1;
          }
      }
      int wide = junctionCount[Arrays.asList(ROUTINES).indexOf(ROUTINE.LEFT)]
              + junctionCount[Arrays.asList(ROUTINES).indexOf(ROUTINE.RIGHT)] + 1;
      int hight = junctionCount[Arrays.asList(ROUTINES).indexOf(ROUTINE.UP)]
              + junctionCount[Arrays.asList(ROUTINES).indexOf(ROUTINE.DOWN)] + 1;
      boolean[][] map = new boolean[wide][hight];
      int x = junctionCount[Arrays.asList(ROUTINES).indexOf(ROUTINE.LEFT)];
      int y = junctionCount[Arrays.asList(ROUTINES).indexOf(ROUTINE.UP)];
      for (i = 0; i < wide; i += 1) {
          Arrays.fill(map[i], false);
      }
      map[x][y] = true;
//      System.out.print(" " + x + ":" + y);
      for (i = 0; i < this.moveMax; i += 1) {
          switch (this.root[i]) {
          case UP :
              y -= 1;
              break;
          case DOWN :
              y += 1;
              break;
          case RIGHT :
              x += 1;
              break;
          case LEFT :
              x -= 1;
              break;
          }
//          System.out.print(" " + x + ":" + y);
          if (map[x][y] == true) {
//              System.out.println(this.rootNum + " is HIT");
              return false;
          } else {
              map[x][y] = true;
          }
      }
//      System.out.println("");
//      System.out.println(this.rootNum + " is OK");
      return true;
    }
    
//    public void equalsWay(Rooting root1, Rooting root2) {
//        if (root1.moveMax != root2.moveMax) {
////            err;
//        }
//        if (root1.rootNum == root2.rootNum) {
////            true (PERFECT MATCH)
//        }
//        int i;
//        int[] root1RootCount = new int[ROUTINES.length];
//        for (ROUTINE r : root1.root) {
//            int index = Arrays.asList(ROUTINES).indexOf(r);
//            if (index >= 0) {
//                root1RootCount[index] += 1;
//            }
//        }
//        int[] root2RootCount = new int[ROUTINES.length];
//        for (ROUTINE r : root2.root) {
//            int index = Arrays.asList(ROUTINES).indexOf(r);
//            if (index >= 0) {
//                root2RootCount[index] += 1;
//            }
//        }
//        for (i = 0; i < ROUTINES.length; i += 1) {
//            if (root1RootCount[i] == root2RootCount[i]) {
////                false;
//            }
//        }
//        int wide = Arrays.asList(ROUTINES).indexOf(ROUTINE.LEFT) + Arrays.asList(ROUTINES).indexOf(ROUTINE.RIGHT);
//        int hight = Arrays.asList(ROUTINES).indexOf(ROUTINE.UP) + Arrays.asList(ROUTINES).indexOf(ROUTINE.DOWN);
//        boolean[][] root1Map = new boolean[wide][hight];
//        boolean[][] root2Map = new boolean[wide][hight];
//    }
    
    public static void main(String[] args) {
        int i;
        int max = getAllWayID(12);
        int count = 0;
        for (i = 0; i <= max; i += 1) {
            Rooting r = new Rooting(12, i);
            count = (r.isValid()) ? count + 1 : count;
//            if (r.isValid() == true) {
////                System.out.println(r.decodeToString());
//                count += 1;
//            } else {
//                count = count;
//            }
        }
        System.out.println(count + " patterns are valid (of " + (max + 1) +")");
    }

}
    