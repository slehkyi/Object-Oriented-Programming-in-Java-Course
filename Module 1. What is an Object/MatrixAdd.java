import java.util.Arrays;

public class MatrixAdd {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 4, 4}};
        int[][] b = {{5, 5, 6}, {0, -1, 2}};
        int[][] sum = matrixAdd(a,b);
        System.out.println(Arrays.deepToString(sum));
    }

    public static int[][] matrixAdd (int[][] a, int[][] b) {
        if (a.length != b.length | a.length == 0 | b.length == 0) {
            return new int[0][0];
        }
        int[][] sum = new int[a.length][a[0].length];
        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < a[0].length; c++) {
                sum[r][c] = a[r][c] + b[r][c];
            }
        }
        return sum;
    }
}
