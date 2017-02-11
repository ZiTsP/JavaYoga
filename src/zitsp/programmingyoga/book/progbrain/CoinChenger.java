package zitsp.programmingyoga.book.progbrain;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author zitsp
 * "プログラマ脳を鍛える数学パズル - 増井敏克"　より
 * p.29 Q05:両替
 */

public class CoinChenger {

    
    private int coinsMax;
    private int cash;
    
    public CoinChenger(int cash, int max) {
        this.cash = cash;
        this.coinsMax = max;
    }

    private static final int COIN_1 = 1;
    private static final int COIN_5 = 5;
    private static final int COIN_10 = 10;
    private static final int COIN_50 = 50;
    private static final int COIN_100 = 100;
    private static final int COIN_500 = 500;
    private static final int[] COINS = {1, 5, 10, 50, 100, 500};
    
    private static final int[] CHANGE_COINS_MIN = {0, 5, 2, 5, 2, 5};
    
//    500から順に、CoinChenger(500, コイン差)で再帰的に求める。枚数が0でない(要件を満たす)なら、500を多めに回して、差を大きくする。
    
    private int[] coinNum = new int[COINS.length];
    {
        Arrays.fill(coinNum, 0);
    }
    
    private boolean checkMin() {
        int coins = 0;
        int localCash = cash;
        int rIndex = COINS.length - 1;
        while (localCash > 0) {
            coins += localCash / COINS[rIndex];
            coinNum[rIndex] = localCash / COINS[rIndex];
            localCash = localCash % COINS[rIndex];
            rIndex -= 1;
        }
        System.out.println("COINS : " + coins);
        return (coins <= coinsMax) ? true : false;
    }
    
    private void printCoins() {
        int i;
        int max = COINS.length;
        StringJoiner sj = new StringJoiner(" "); 
        for (i = 0; i < max; i += 1) {
            StringBuffer sb = new StringBuffer();
            sb.append(COINS[i]).append("*[").append(coinNum[i]).append("]");
            sj.add(sb.toString());
        }
        System.out.println("MIN for " + cash + " : " + sj.toString());
    }
    
    public static void main(String[] args) {
        CoinChenger c = new CoinChenger(123909, 20);
        c.checkMin();
        c.printCoins();
    }

}
