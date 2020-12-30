package morusmath;

import morusmath.exceptions.MatrixException;

public class Experiments {
    public static void main(String[] args) {
        Matrix matrixInstance = new Matrix(0);
        String[][] matrixA = {{"1", "2", "3"}, {"4", "5", "6"}, {"7.1", "8.2", "9.3"}};
        String[][] matrixB = {{"21", "22", "23"}, {"24", "25", "26"}, {"27.1", "123.1", "28.2"}};
        try {
            System.out.println(matrixInstance.checkIfMatrixesHaveTheSameSize(matrixA, matrixB));
        } catch (MatrixException e) {
            e.printStackTrace();
        }
    }
}