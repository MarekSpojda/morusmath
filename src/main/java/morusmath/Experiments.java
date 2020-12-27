package morusmath;

public class Experiments {
    public static void main(String[] args) {
        Calculator bdc = new Calculator(100);
        String number1 = bdc.cotangentToCosine("0.75");
        String number2 = "0.6";
        System.out.println(bdc.absolute(bdc.subtract(number1, number2)));
        System.out.println("N1|" + number1);
        System.out.println("N2|" + number2);
    }
}