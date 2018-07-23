package struct;
//構造体の参考
import java.util.ArrayList;

public class StrctTest {
    /** 構造体ArrayList */
    static ArrayList<MyStructure> arrayStr = new ArrayList<MyStructure>();
    public StrctTest() {
    }
    
    /**
     * 構造体ArrayListに値をセット.
     */
    public void setStructure() {
        arrayStr.add(setStr(1,2,11));
        arrayStr.add(setStr(3,4,12));
        arrayStr.add(setStr(5,6,13));
        arrayStr.add(setStr(7,8,14));
        arrayStr.add(setStr(9,10,15));
    }
    
    /**
     * 構造体に値をセット.
     * @param num1
     * @param num2
     * @return
     */
    public MyStructure setStr(int num1, int num2 , int num3) {
        MyStructure str = new MyStructure();
        str.num1 = num1;
        str.num2 = num2;
        str.num3 = num3;
        return str;
    }
    
    /**
     * メインメソッド.
     * @param args
     */
    static public void main(String[] args) {
        StrctTest strt = new StrctTest();
        strt.setStructure();
        for(int i=0;i<arrayStr.size();i++){
            System.out.println(arrayStr.get(i).num1+""
            		+ ","+arrayStr.get(i).num2
            		+ ","+arrayStr.get(i).num3
            		);
        }
    }
    
    /**
     * 構造体用の内部クラス.
     *
     */
    class MyStructure {
        int num1;
        int num2;
        int num3;
        }

}