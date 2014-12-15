import Jama.Matrix;

/**
 * Created by konstantin on 11.11.14.
 */

public class main {

    public static void main (String[] args) {


//        ReadFileCSV matrixA = new ReadFileCSV();
//        ReadFileCSV matrixb = new ReadFileCSV();
//        matrixA.run("/Users/konstantin/IdeaProjects/chm/test.csv");
//        matrixb.run("/Users/konstantin/IdeaProjects/chm/testB.csv");

//
//        Model A = new Model(12);
//        Model A2 = new Model(A, Model.MODEL_USE_E); //создание копии матрицы коэф.
//
//        Matrix solveEx, solveNoEx, E;
//
//        A.ExplicitAndNoExplicitIteration(A.getMatrixb(), A.getMatrixM(), 0.0001);
//        solveEx = A.getVectorSolve();
//        solveEx.print(solveEx.getColumnDimension(), solveEx.getRowDimension());
//        System.out.println("Количество итераций noExplicit " + A.getIterator());
//            System.out.println("time is : " + A.getTime()/100 + "mSec");
//
//        A2.ExplicitAndNoExplicitIteration(A2.getMatrixb(), A2.getMatrixM(), 0.0001);
//        solveNoEx = A2.getVectorSolve();
//        solveNoEx.print(solveNoEx.getColumnDimension(), solveNoEx.getRowDimension());
//        System.out.println("Количество итераций Explicit " + A2.getIterator());
//            System.out.println("time is : " + A2.getTime()/100 + "mSec");

        Presenter main = new Presenter();

    }
}
