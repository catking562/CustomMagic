package taewookim.math;

import java.util.ArrayList;

public class TriangleMath {

    public static ArrayList<Double> sin = new ArrayList<>();
    public static ArrayList<Double> cos = new ArrayList<>();

    static {
        for(int i = 0;i<360;i++) {
            sin.add(Math.sin(Math.toRadians(i)));
            cos.add(Math.cos(Math.toRadians(i)));
        }
    }
}
