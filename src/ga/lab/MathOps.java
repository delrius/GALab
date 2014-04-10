package ga.lab;

public class MathOps {
    public static Double root(int number, int dimension) {
        return Math.pow(Math.E, Math.log(number)/ dimension );
    }

    public static void main(String[] args) {
        System.out.println(root(16*16*16*16, 4));
    }
}
