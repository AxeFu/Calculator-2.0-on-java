import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculate {
    static String Result(String text) {
        int R = 0,L = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') L = i;
            else if (text.charAt(i) == ')') {
                R = i;
                break;
            }
        }
        if (L == -1) return calc(text);
        String a,b,msg;
        a = getText(0, L, text);
        msg = getText(L+1, R, text);
        b = getText(R+1, text.length(), text);
        return Result(a + calc(msg) + b);
    }
    static String calc(String text) {
        boolean plusMinus = false;
        int operation = -1;
        for (int i = 0; i < text.length(); i++)
            if (text.charAt(i) == '*' || text.charAt(i) == '/') {
                operation = i;
                break;
            }
        if (operation == -1) {
            plusMinus = true;
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '+' || text.charAt(i) == '-') {
                    operation = i;
                    if (operation == 0) continue;
                    break;
                }
            }
        }
        String a = "", b = "",beforeA= "",afterB= "", result = "";
        if (operation > 0) {
            for (int i = operation - 1; i >= 0; i--) {
                if (((text.charAt(i) == '*' || text.charAt(i) == '/') && !plusMinus) || (plusMinus && text.charAt(i) == '+')) {
                    a = getText(i + 1, operation, text);
                   beforeA= getText(0, i, text);
                    break;
                } else if (text.charAt(i) == '+' && !plusMinus || text.charAt(i) == '-') {
                    a = getText(i, operation, text);
                   beforeA= getText(0, i, text);
                    break;
                }
            }
            for (int i = operation + 1; i < text.length(); i++) {
                if (((text.charAt(i) == '*' || text.charAt(i) == '/') && !plusMinus) || text.charAt(i) == '+' || text.charAt(i) == '-') {
                    b = getText(operation + 1, i, text);
                   afterB= getText(i, text.length(), text);
                    break;
                }
            }
            if (a.equals("")) a = getText(0, operation, text);
            if (b.equals("")) b = getText(operation + 1, text.length(), text);
            switch (text.charAt(operation)) {
                case '*':
                    result = BigDecimal.valueOf(Double.parseDouble(a)).multiply(BigDecimal.valueOf(Double.parseDouble(b))) + "";
                    break;
                case '/':
                    if (Double.parseDouble(b) == 0) return "Деление на ноль";
                    result = BigDecimal.valueOf(Double.parseDouble(a)).divide(BigDecimal.valueOf(Double.parseDouble(b)),1000, RoundingMode.HALF_UP) + "";
                    break;
                case '+':
                    result = BigDecimal.valueOf(Double.parseDouble(a)).add(BigDecimal.valueOf(Double.parseDouble(b))) + "";
                    break;
                case '-':
                    result = BigDecimal.valueOf(Double.parseDouble(a)).subtract(BigDecimal.valueOf(Double.parseDouble(b))) + "";
            }
            result = Double.parseDouble(result)+"";
            if (Double.parseDouble(result) == (int) Double.parseDouble(result))
                result = (int) Double.parseDouble(result) + "";
            if (beforeA.equals("")) return calc(beforeA + result + afterB);
            if (Double.parseDouble(result) < 0) return calc(beforeA + result + afterB);
            else return calc(beforeA + "+" + result + afterB);
        }
        return text;
    }
    public static String getText(int a, int b, String text) {
        StringBuilder result = new StringBuilder();
        for (int i = a; i < b; i++) result.append(text.charAt(i));
        return result.toString();
    }
}
