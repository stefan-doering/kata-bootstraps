public class GameOfLifeBoardPrinter {

    public String printMatrix(int generation, Cell[][] matrix) {
        StringBuilder sb = new StringBuilder();
        int matrixRowDimension = matrix.length;
        int matrixColDimension = matrix[0].length;

        sb.append("Generation " + generation + ":" + newLine());
        sb.append(matrixRowDimension + " " + matrixColDimension + newLine());

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                sb.append(matrix[row][col].getCellState() == CellState.ALIVE ? "*" : ".");
            }
            if (row != matrix.length - 1) {
                sb.append(newLine());
            }
        }
        return sb.toString();
    }

    private String newLine() {
        return "\n";
    }
}
