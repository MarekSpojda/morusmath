package morusmath;

import morusmath.exceptions.MatrixException;

/**
 * <b>Matrix</b><br>
 * <br>
 * <i>public class Matrix</i><br>
 * <br>
 * Class for matrix operations.
 */
@SuppressWarnings("unused")
public class Matrix {
    private int precision;

    /**
     * <b>Matrix</b><br>
     * <br>
     * <i>public Matrix(int precision)</i><br>
     * <br>
     * Returns instance of Matrix and sets given precision.
     *
     * @param precision precision to be set.
     */
    public Matrix(int precision) {
        this.precision = precision;
    }

    /**
     * <b>checkIfMatrixesHaveTheSameSize</b><br>
     * <br>
     * <i>public boolean checkIfMatrixesHaveTheSameSize(String[][] matrixA, String[][] matrixB)</i><br>
     * <br>
     * Returns true if matrixes have the same size, false otherwise.
     *
     * @param matrixA first matrix to be compared.
     * @param matrixB second matrix to be compared.
     * @return true if matrixes have the same size, false otherwise.
     */
    public boolean checkIfMatrixesHaveTheSameSize(String[][] matrixA, String[][] matrixB) {
        return matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length;
    }

    /**
     * <b>getPrecision</b><br>
     * <br>
     * <i>public int getPrecision()</i><br>
     * <br>
     * Returns current Matrix instance precision.
     *
     * @return current Matrix instance precision.
     */
    public int getPrecision() {
        return this.precision;
    }

    /**
     * <b>putMatrixToString</b><br>
     * <br>
     * <i>public String putMatrixToString(String[][] matrix)</i><br>
     * <br>
     * Puts matrix to String. Mainly for demonstration purposes.
     *
     * @param matrix matrix to processed.
     * @return matrix as String.
     */
    public String putMatrixToString(String[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] strings : matrix) {
            stringBuilder.append("[");
            for (int j = 0; j < matrix[0].length; j++) {
                stringBuilder.append(strings[j]);
                if (j < (matrix[0].length - 1)) {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append("]").append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * <b>transposeMatrix</b><br>
     * <br>
     * <i>public String[][] transposeMatrix(String[][] matrix)</i><br>
     * <br>
     * Returns transposed matrix without checking cell content.
     *
     * @param matrix matrix to be transposed.
     * @return transposed matrix.
     */
    public String[][] transposeMatrix(String[][] matrix) {
        String[][] resultMatrix = new String[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                resultMatrix[j][i] = matrix[i][j];
            }
        }

        return resultMatrix;
    }

    /**
     * <b>validateMatrix</b><br>
     * <br>
     * <i>public void validateMatrix(String[][] matrix)</i><br>
     * <br>
     * Validates given matrix. Cell content is not checked.
     *
     * @param matrix matrix to be validated.
     * @throws MatrixException error throwed if matrix is null, row size or column size is 0 or row lengths are not equal.
     */
    public void validateMatrix(String[][] matrix) throws MatrixException {
        if (matrix == null) {
            throw new MatrixException("Matrix can't be null.");
        }

        int height = matrix.length;
        if (height == 0) {
            throw new MatrixException("Matrix column size can't be 0.");
        }

        int width = matrix[0].length;
        if (width == 0) {
            throw new MatrixException("Matrix row width can't be 0.");
        }

        for (String[] row : matrix) {
            if (row.length != width) {
                throw new MatrixException("Matrix rows can't have different lengths.");
            }
        }
    }

    /**
     * <b>validateMatrix</b><br>
     * <br>
     * <i>public void validateMatrix(String[][] matrix, String prefix)</i><br>
     * <br>
     * Validates given matrix and adds specified prefix to the beginning of error message. Cell content is not checked.
     *
     * @param matrix matrix to be validated.
     * @param prefix value to be added to the beginning of error message.
     * @throws MatrixException error throwed if matrix is null, row size or column size is 0 or row lengths are not equal.
     */
    public void validateMatrix(String[][] matrix, String prefix) throws MatrixException {
        try {
            validateMatrix(matrix);
        } catch (MatrixException e) {
            throw new MatrixException(prefix + e.getMessage());
        }
    }
}