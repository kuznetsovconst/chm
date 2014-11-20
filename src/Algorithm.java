import Jama.Matrix;
import java.math.*;
import Jama.CholeskyDecomposition;
import org.omg.CORBA.MARSHAL;

import java.math.*;



public class Algorithm {
    private Matrix A;
    private Matrix xk;
    private double accuracy;
    private int iterator;

    public Algorithm(){

    }

    public Algorithm(Algorithm a) {
        A = a.getMatrix();
    }

    public Algorithm(Matrix input) {
        A = input;
    }

    public Algorithm (int r, int c, int s) {
        setSymmetricMatrix(r,c,s);
    }

    public Matrix ExplicitAndNoExplicitIteration (Matrix b, Matrix M, double a) {
        accuracy = a;
        int rows = this.getMatrix().getRowDimension();
        int colms = b.getRowDimension();
        if (rows == colms) {

            Algorithm temp = new Algorithm(this.getMatrix().eig().getD());
            Algorithm mZero = new Algorithm();
            mZero.setRandomMatrix(b.getRowDimension(), b.getColumnDimension(), 0);
            Matrix mInv = M.inverse();
            Matrix B = this.getMatrix();
            double tau = getTauFromEigvalue(temp.minEigvalue(), temp.maxEigvalue());


            Matrix xOne = mZero.getMatrix(); // Zero iteration
//            xk = (mInv.times(tau)).times((b.minus(B.plus(E).times(xOne))));
            xk = (((mInv.times(tau)).times(b)).minus((B.times(tau).times(xOne))).plus(xOne));
            iterator = 0;
            while (epsilon(xOne, xk) >  accuracy) {
                iterator++;
                xOne = xk;
//                xk = (mInv.times(tau)).times((b.minus(B.plus(E).times(xOne))));
                xk = (((mInv.times(tau)).times(b)).minus((B.times(tau).times(xOne))).plus(xOne));
            }

        } else {
            System.out.println("The matrix of the coefficients is different in size from the matrix of free terms");
        }

        return xk;
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
        int row = this.getMatrix().getRowDimension();
        int j;
        double max, eigValue = 0;
        for (int i = 0; i < row; i++) {
            j = i;
            max = this.getMatrix().get(i, j);
            if (eigValue < max ) eigValue = max;
        }
        return eigValue;
    }

    public double minEigvalue () {
        int row = this.getMatrix().getRowDimension();
        int j;
        double min;
        double eigValue = Double.POSITIVE_INFINITY;
        for (int i = 0; i < row; i++) {
            j = i;
            min = this.getMatrix().get(i,j);
            if (eigValue > min ) eigValue = min;
        }
        return eigValue;
    }

    public void setSymmetricMatrix(int rows, int columns, int step) {
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

        Matrix B;
        A = new Matrix(tempMatrix);
        B = A.transpose();
        A = A.plus(B);
    }

    public void setRandomMatrix(int r, int c, int s){
        double[][] temp = new double[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                temp[i][j] = (Math.random()*s);
            }
        }

        A = new Matrix(temp);
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

    public Matrix getMatrix() {
        return A;
    }

}
