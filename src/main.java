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


        Model A = new Model(12); //кол-во строк, столбцов, в каких пределах рандом
        Model A2 = new Model(A); //создание копии матрицы коэф.

        Matrix solveEx, solveNoEx, E;
        E = Matrix.identity(12,12);

        solveEx = A.ExplicitAndNoExplicitIteration(A.getMatrixb(), E, 0.001);
        solveEx.print(solveEx.getColumnDimension(), solveEx.getRowDimension());
        System.out.println("Количество итераций Explicit " + A.getIterator());
            System.out.println("time is : " + A.getTime()/100 + "mSec");

        solveNoEx = A2.ExplicitAndNoExplicitIteration(A2.getMatrixb(), A2.getMatrixM(), 0.001);
        solveNoEx.print(solveNoEx.getColumnDimension(), solveNoEx.getRowDimension());
        System.out.print("Количество итераций NoExplicit " + A2.getIterator());
            System.out.println("time is : " + A2.getTime()/100 + "mSec");

        Presenter main = new Presenter();

    }
}
