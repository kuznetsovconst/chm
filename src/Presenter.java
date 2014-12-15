import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class Presenter {
    private List<Model> listModel;
    private mainWin view;

    public Presenter(){
        listModel = new ArrayList<Model>();
        view = new mainWin(this);
        initWindow();
    }
    public void initWindow() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Iteration mepthods");
                JComponent a;
                a = view.$$$getRootComponent$$$();
                frame.setContentPane(a);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public ActionListener getGenerateL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ord = view.getText();
                try {
                    int o = view.parseTextField(ord);
                    genMatrix(o);

                    String it = view.iter + "";
                    view.progresJLabel.setText("Number of gen matrix: " + it);
                    ++view.iter;
                } catch (NumberFormatException ex) {
                    view.createErrWind("Incorrect input. Please input correctly order.");
                }


            }
        };
    }

    public ActionListener getToSolveL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Жмакнули кнопку toSolve");

                Iterator<Model> l = listModel.iterator();
                int i = 0;
                while (l.hasNext()) {
                    i++;
                    System.out.println("Решаю уравнение № " + i);
                    l.next().solve();
                    System.out.println("Закончил решать уравнение № " + i);
                }
            }
        };
    }

    public ActionListener getDataButtonL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Жмакнули кнопку getData");
                Iterator<Model> l = listModel.iterator();
                while (l.hasNext()) {
                    if (l.next().getVectorSolve() == null) {
                        view.createErrWind("Matrix is not solving .Please put on toSolve");
                    } else {
                        System.out.println("будет пробовать печатать");
                        l.next().printSolveInConsole();
                    }
                }
            }
        };
    }

    public ActionListener getPlotFromDataL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Жмакнули кнопку plot");
            }
        };
    }

    public void genMatrix(int i){
        System.out.println("Управление перешло в презентер (т.е контроллер)" + " " + i);
        this.listModel.add(new Model(i));
    }


    public static void update(){}


}
