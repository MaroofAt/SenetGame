package structure;

import java.util.HashMap;
import java.util.Map;

public class SpecialHouses {
    private static final Map<Integer, String> RULES = new HashMap<>();
    static {
        RULES.put(14, "isStartSquare: يُعاد إرسال الأحجار إلى هذا المربع من أجل «البعث». وإذا كان مشغولًا، يُنقل الحجر إلى أول مربع غير مشغول يسبقه.");
        RULES.put(25, "isBarrierSquare: يجب على جميع الأحجار المرور عبر هذا المربع. ولا يُسمح بالقفز فوقه، بل يجب الهبوط عليه مباشرة.");
        RULES.put(26, "isReturnSquare: إذا هبط حجر على هذا المربع، فعليه العودة فورًا إلى بيت البعث.");
        RULES.put(27, "isThreeSquare: إذا هبط حجر على هذا المربع، فإنه يمكن في الدور التالي خروج هذا الحجر إذا حصل اللاعب على رمية مقدارها 3.");
        RULES.put(28, "isTwoSquare: إذا هبط حجر على هذا المربع، فإنه يمكن في الدور التالي خروج هذا الحجر إذا حصل اللاعب على رمية مقدارها 2.");
        RULES.put(29, "isFreeSquare: إذا هبط حجر على هذا المربع، فإنه يمكن في الدور التالي خروج هذا الحجر بأي رمية، غير أنه إذا لم يُحرِّك في ذلك الدور، فسيُعاد إلى بيت البعث.");
    }
    public static boolean isStartSquare(int cellIndex) {
        return cellIndex == 14;
    }

    public static boolean isBarrierSquare(int cellIndex) {
        return cellIndex == 25; }

    public static boolean isReturnSquare(int cellIndex) {
        return cellIndex == 26;
    }

    public static boolean isThreeSquare(int cellIndex) {
        return cellIndex == 27;
    }

    public static boolean isTwoSquare(int cellIndex) {
        return cellIndex == 28;
    }

    public static boolean isFreeSquare(int cellIndex) {
        return cellIndex == 29;
    }

    public static String getRule(int cellIndex) {
        return RULES.getOrDefault(cellIndex, "No special rule");
    }
}
