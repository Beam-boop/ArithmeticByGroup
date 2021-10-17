package Util;

/**
 *
 * @author : [86135]
 * @version : [v1.0]
 * @createTime : [2021/10/16 10:59]
 */

enum Operator {
    ADD("+", 1), SUBTRACT("-", 1),
    MULTIPLY("×", 2), DIVIDE("÷", 2),
    LEFT_BRACKET("(", 3), RIGHT_BRACKET(")", 3); //括号优先级最高
    String value;
    int priority;

    Operator(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }
    /**
     * 比较两个符号的优先级
     * @param s1
     * @param s2
     * @return c1的优先级是否比c2的高，高则返回正数，等于返回0，小于返回负数
     */
    public static int cmp(String s1, String s2) {
        int p1 = 0;
        int p2 = 0;
        for (Operator o : Operator.values()) {
            if (o.value.equals(s1)) {
                p1 = o.priority;
            }
            if (o.value.equals(s2)) {
                p2 = o.priority;
            }
        }
        return p1 - p2;
    }

    /**
     * 枚举出来的才视为运算符，用于扩展
     * @param s
     * @return
     */
    public static boolean isOperator(String s) {
        for (Operator o : Operator.values()) {
            if (o.value.equals(s)) {
                return true;
            }
        }
        return false;
    }
}

