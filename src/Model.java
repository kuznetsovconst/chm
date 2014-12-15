import Jama.Matrix;
import com.sun.deploy.ui.MacJavaTrayIcon;

import java.util.ArrayList;


public class Model {
    private static int ONE = 1;
    private static int RANDOM_SIZE = 50;
    private static double ACCURACY = 0.0001;
    public static double MODEL_USE_E = 1;
    private Matrix A;
    private Matrix b;
    private Matrix M;
    private Matrix xk;
    private double accuracy;
    private int iterator;
    private long time;
    private int ord;


    public Model(int i){
        ord = i;
        A = setSymmetricMatrix(i, i, RANDOM_SIZE); //интервал псевдослучайных захардкожен
        b = setRandomMatrix(i, ONE, RANDOM_SIZE);  //интервал псевдослучайных захардкожен
        M = setDiagonalRandomMatrix(i, i, RANDOM_SIZE);
//        M.print(i,i);
//        A.print(i,i);
        System.out.println("Инициализированно уравнение из случайных матриц с диагональной матрицей М");
    }

    public Model(Matrix a, Matrix b, Matrix m) {
        A = a;
        this.b = b;
        M = m;
    }

    public Model(Model a) {
        A = a.getMatrixA();
        b = a.getMatrixb();
        M = a.getMatrixM();
    }

    public Model(Model a, double usageE) {
        A = a.getMatrixA();
        b = a.getMatrixb();
        M = Matrix.identity(a.getMatrixA().getRowDimension(), a.getMatrixA().getRowDimension());
        ord = A.getColumnDimension();
        System.out.println("Инициализированно уравнение из случайных матриц с М = Е");
    }


    public Model(Matrix j){
        A = j;
    }

    public void ExplicitAndNoExplicitIteration (Matrix b, Matrix M, double a) {
        long t = System.nanoTime();
        accuracy = a;
        int rows = this.getMatrixA().getRowDimension();
        int colms = b.getRowDimension();
        if (rows == colms) {

            Model temp = new Model(this.getMatrixA().eig().getD());
            Matrix mZero = setRandomMatrix(b.getRowDimension(), b.getColumnDimension(), 0);
            Matrix mInv = M.inverse();
            double tau = getTauFromEigvalue(temp.minEigvalue(), temp.maxEigvalue());


            Matrix xOne = mZero; // Zero iteration
            //xk = (((mInv.times(tau)).times(b)).minus((B.times(tau).times(xOne))).plus(xOne));
            xk = ((mInv.times(tau)).times((b.minus((A.times(xOne)))))).plus(xOne);
//            xk = mInv.times(tau).times(b).minus(mInv.times(tau).times(this.getMatrixA().times(xOne))).plus(xOne);
            iterator = 0;
            while (epsilon(xOne, xk) >  accuracy) {
                iterator++;
                xOne = xk;
                //xk = (((mInv.times(tau)).times(b)).minus((B.times(tau).times(xOne))).plus(xOne));
                xk = ((mInv.times(tau)).times((b.minus((A.times(xOne)))))).plus(xOne);
//                xk = mInv.times(tau).times(b).minus(mInv.times(tau).times(this.getMatrixA().times(xOne))).plus(xOne);
            }

        } else {
            System.out.println("The matrix of the coefficients is different in size from the matrix of free terms");
        }
        time = System.nanoTime() - t;
        //return xk;
    }

    public void solve() {
        this.ExplicitAndNoExplicitIteration(this.getMatrixb(), this.getMatrixM(), ACCURACY);
    }

    public double epsilon(Matrix before, Matrix after) {
        double temp, max = -1;

        for (int i = 0; i < before.getRowDimension(); i++) {
            for (int j = 0; j < before.getColumnDimension(); j++) {
                temp = Math.abs(after.get(i,j)-before.get(i,j));
                if (max < temp) max = temp;
            }
        }
        return max;
    }

    public double getTauFromEigvalue(double l1, double l2){
        double t = 2/(l1+l2);
        return t;
    }

    public double maxEigvalue () {
        int row = this.getMatrixA().getRowDimension();
        int j;
        double max, eigValue = 0;
        for (int i = 0; i < row; i++) {
            j = i;
            max = this.getMatrixA().get(i, j);
            if (eigValue < max ) eigValue = max;
        }
        return eigValue;
    }

    public double minEigvalue() {
        int row = this.getMatrixA().getRowDimension();
        int j;
        double min;
        double eigValue = Double.POSITIVE_INFINITY;
        for (int i = 0; i < row; i++) {
            j = i;
            min = this.getMatrixA().get(i,j);
            if (eigValue > min ) eigValue = min;
        }
        return eigValue;
    }

    private static Matrix setDiagonalRandomMatrix(int r, int c, int s) {
        double[][] temp = new double[r][c];
        int j;
        for (int i = 0; i < r; i++){
            j = i;
            temp[i][j] = (Math.random()*s+50);
        }

        return new Matrix(temp);
    }

    private static Matrix setSymmetricMatrix(int rows, int columns, int step) {
        double[][] tempMatrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < columns; j++) {
                tempMatrix[i][j] = (Math.random()*step);
            }
        }
        int j;
        for (int i = 0; i < rows; i++) {
            j = i;
            tempMatrix[i][j] = rows + step + (Math.random()*step);
        }

        Matrix tempOne, tempTwo,q;
        tempOne = new Matrix(tempMatrix);
        tempTwo = tempOne.transpose();
        return tempOne.plus(tempTwo);
    }

    private static Matrix setRandomMatrix(int r, int c, int s){
        double[][] temp = new double[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                temp[i][j] = (Math.random()*s);
            }
        }

        return new Matrix(temp);
    }

    private static Matrix setEMatrix(int r, int c, double vol) {
        double[][] t = new double[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++){
                t[i][j] = vol;
            }
        }
        return new Matrix(t);
    }

    public void setAccuracy(double a) {
        accuracy = a;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getIterator() {
        return iterator;
    }

    public Matrix getMatrixA() {
        return A;
    }

    public Matrix getMatrixb(){
        return b;
    }

    public Matrix getMatrixM(){
        return M;
    }

    public Matrix getVectorSolve(){
        return xk;
    }

    public void printSolveInConsole() {
        this.getVectorSolve().print(this.getOrd(), ONE);
    }

    public long getTime() {
        return time;
    }

    public int getOrd(){
        return ord;
    }

}
