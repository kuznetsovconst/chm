import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.category.DefaultCategoryDataset;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;


public class Presenter {
    private List<Model> listModel;
    private mainWin view;

    public Presenter() {
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
                frame.setLocation(300,300);
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
                    Model temp = l.next();
                    temp.solve();
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
                ListIterator<Model> l = listModel.listIterator();
                while (l.hasNext()) {
                    Model temp = l.next();
                    if (temp.getVectorSolve() == null) {
                        view.createErrWind("Matrix is not solving .Please put on toSolve");
                    } else {
                        temp.printSolveInConsole();
                        System.out.println("Время выполнения: " + temp.getTime());
                        System.out.println("Количество итераций: " + temp.getIterator());
                    }
                }
            }
        };
    }

    public ActionListener getPlotFromDataL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plotGraph();
            }
        };
    }

    public void genMatrix(int i) {
//        System.out.println("Управление перешло в презентер (т.е контроллер)" + " " + i);
        Model temp = new Model(i);
        this.listModel.add(temp);
        this.listModel.add(new Model(temp, Model.MODEL_USE_E)); // создание копии уравнения, но только M = E
    }

    public void plotGraph(){
        final LineChart_AWT chart = new LineChart_AWT("Graph", "Iter of ORD");
        chart.pack();
        //chart.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);

        chart.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                chart.setVisible(false);
            }
        });

    }

    public class LineChart_AWT extends ApplicationFrame {

        public LineChart_AWT(String applTitle, String chartTitle) {
            super(applTitle);
            JFreeChart OrdOfIter = ChartFactory.createLineChart(
                    chartTitle,
                    "ordinary", "Iteration",
                    createDatasetOrdOfIter(),
                    PlotOrientation.VERTICAL,
                    true, true, false);


            ChartPanel chartPanel = new ChartPanel(OrdOfIter);
            chartPanel.setPreferredSize( new java.awt.Dimension(560, 360));
            super.getContentPane().add(chartPanel);
            this.addWindowListener(this);

        }

        private DefaultCategoryDataset createDatasetOrdOfIter() {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for(Model u: listModel){
                int i = u.getIterator();
                int o = u.getOrd();
                dataset.addValue(i, "help", o + "");
            }

            return dataset;
        }

        @Override
        public void windowClosing(WindowEvent event) {
            if (event.getWindow() == null) {
                this.dispose();
                System.exit(0);
            }
        }

        //        private DefaultCategoryDataset createDa() {
//            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//            for(Model u: listModel){
//                int i = u.getIterator();
//                int o = u.getOrd();
//                dataset.addValue(i, "help", o + "");
//            }
//
//            return dataset;
//        }
    }


}


