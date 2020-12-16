import java.util.ArrayList;
import java.util.Collections;

// 1) отсортировать вероятности по неубыванию
// 2) Нати bx - суммы вероятностей
// 3) Перевести вероятности в требуюмую систему счисления
// 4) Найти Lx = -log(px) в требуемой системе счисления
// 5) Построить коды

public class Shennon {
    public static void main(String[] args) {
        ArrayList<Double> l = getPx(0.1, 0.2, 0.1, 0.1, 0.35, 0.15);
        ArrayList<Double> b = getBx(l);

        ArrayList<Double> lx = getLx(l, 2);
        lx.forEach(System.out::println);
    }

    public static ArrayList<Double> getPx(Double... agrs) {
        ArrayList<Double> px = new ArrayList<>();
        Collections.addAll(px, agrs);

        double sum = px.stream()
                .mapToDouble(a -> a)
                .sum();

        if (sum > 1) {
            throw new IllegalArgumentException("Сумма вероятностей не может быть > 1");
        }

        Collections.sort(px);
        Collections.reverse(px);

        return px;
    }

    public static ArrayList<Double> getBx(ArrayList<Double> px) {
        ArrayList<Double> bx = new ArrayList<>();
        double sum = 0;

        for (Double p : px) {
            bx.add(sum);
            sum += p;
        }

        return bx;

    }

    public static ArrayList<Double> getLx(ArrayList<Double> px, double b) {
        ArrayList<Double> lx = new ArrayList<>();
        px.forEach(p -> lx.add(-1 * Math.floor(log(p, b))));
        return lx;
    }

    public static double log(double x, double b) {
        return Math.log(x) / Math.log(b);
    }
}
