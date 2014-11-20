import Jama.Matrix;

import javax.swing.*;

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
//        Matrix C,d,solve;
//        Algorithm solveC, solveb;
//        C = matrixA.getMatrix();
//        d = matrixb.getMatrix();
//
//        solveC = new Algorithm(C);
//
//        solve = solveC.ExplicitAndNoExplicitIteration(d, Matrix.identity(solveC.getMatrix().getRowDimension(), solveC.getMatrix().getColumnDimension()), 0.001);
//        solve.print(solveC.getMatrix().getRowDimension(), solveC.getMatrix().getColumnDimension());
//        System.out.println("Iteration: " + solveC.getIterator());

        Algorithm A = new Algorithm(12, 12, 50); //кол-во строк, столбцов, в каких пределах рандом
        Algorithm A2 = new Algorithm(A); //создание копии матрицы коэф.

        Algorithm b = new Algorithm();
        Algorithm M = new Algorithm();

        M.setRandomMatrix(A.getMatrix().getRowDimension(), A.getMatrix().getColumnDimension(), 1); // инициализация рандомной матрицы
        b.setRandomMatrix(A.getMatrix().getRowDimension(), 1, 50); // инициализация вектора свободных членов

        Matrix solveEx, solveNoEx, E;
        E = Matrix.identity(12,12);

        solveEx = A.ExplicitAndNoExplicitIteration(b.getMatrix(), E, 0.001);
        solveEx.print(solveEx.getColumnDimension(), solveEx.getRowDimension());
//        b.getMatrix().print(b.getMatrix().getRowDimension(), b.getMatrix().getRowDimension());
        System.out.println("Количество итераций Explicit " + A.getIterator());

        solveNoEx = A2.ExplicitAndNoExplicitIteration(b.getMatrix(), M.getMatrix(), 0.001);
        solveNoEx.print(solveNoEx.getColumnDimension(), solveNoEx.getRowDimension());
//        b.getMatrix().print(b.getMatrix().getRowDimension(), b.getMatrix().getRowDimension());
        System.out.print("Количество итераций NoExplicit " + A2.getIterator());

        initWindow();


    }

    public static void initWindow() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("OLOLO");
                JComponent a;
                Solver s = new Solver();
                a = new mainWin(s).$$$getRootComponent$$$();
                frame.setContentPane(a);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
