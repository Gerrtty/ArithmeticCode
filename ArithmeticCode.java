import java.util.HashMap;
import java.util.Map;

public class ArithmeticCode {

    public static void main(String[] args) {

        String s = "abacaba";

        System.out.println(encode(s));
    }

    public static double encode(String s) {
        HashMap<Character, Segment> segment = defineSegments(s);

        double left = 0;
        double right = 1;

        char letter = '-';

        System.out.println("Symbol left right\n------------");

        for (int i = 0; i < s.length(); i++) {

            System.out.println(letter + " " + left + " " + right);
            System.out.println("------------");

            char symb = s.charAt(i);
            double newRight = left + (right - left) * segment.get(symb).getRight();
            left = left + (right - left) * segment.get(symb).getLeft();
            right = newRight;
            
            letter = symb;
        }

        return (left + right) / 2;
    }

    public static HashMap<Character, Segment> defineSegments(String s) {
        HashMap<Character, Double> map = getProbability(s);

        HashMap<Character, Segment> segmentMap = new HashMap<>();

        double l = 0;
        for (Map.Entry<Character, Double> entry: map.entrySet()) {
            Segment segment = new Segment(l, l + entry.getValue());
            l = segment.getRight();
            segmentMap.put(entry.getKey(), segment);
        }

        return segmentMap;
    }

    public static HashMap<Character, Double> getProbability(String s) {
        char[] chars = s.toCharArray();
        int len = s.length();

        HashMap<Character, Double> map = new HashMap<>();

        for (Character character : chars) {
            Double count = map.get(character);

            if (count == null || count == 0) {
                map.put(character, 1.0);
            } else {
                map.put(character, count + 1);
            }

        }

        for (Map.Entry<Character, Double> entry: map.entrySet()) {
            entry.setValue(entry.getValue() / len);
        }

        return map;
    }

}
