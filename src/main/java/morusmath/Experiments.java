package morusmath;

public class Experiments {
    public static void main(String[] args) {
        Matrix matrixInstance = new Matrix(0);
        String[][] matrixA = {{"1", "2", "3"}, {"4", "5", "6"}, {"7.1", "8.2", "9.3"}};
        String[][] matrixB = {{"21", "22"}, {"24", "25"}, {"27.1", "123.1"}};
        System.out.println(matrixInstance.matrixIsSquare(matrixA));
        System.out.println(matrixInstance.matrixIsSquare(matrixB));
    }
}