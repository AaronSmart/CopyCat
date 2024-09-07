import java.lang.Math;

public class DivisionByBase {

    public static void main(String[] args) {
        int total = 123; // 总数量
        int base = 10;   // 基数

        // 调用方法计算份额总数
        int portions = divideByBase(total, base);

        System.out.println("Total portions: " + portions);
    }

    /**
     * 根据基数计算总份额数。
     * 
     * @param total 总数量
     * @param base  基数
     * @return 总份额数
     */
    public static int divideByBase(int total, int base) {
        // 使用double类型进行除法运算，确保得到的小数部分不会被舍去
        double result = (double) total / base;
        
        // 使用Math.ceil方法将结果向上取整
        int portions = (int) Math.ceil(result);

        return portions;
    }
}