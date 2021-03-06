import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import parsii.tokenizer.ParseException;


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
    private List<InterpolationFunctions> ListExperiment;
    private List<Grid> listXval;

    public Presenter() {
        listModel = new ArrayList<Model>();
        listXval = new ArrayList<Grid>();
        ListExperiment = new ArrayList<InterpolationFunctions>();
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
                frame.setLocation(300, 300);
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

    public void plotGraph() {
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

    public void initTestGird() {
        int k = 150;
        for (int i = 0; i < 10; i++) {
            Grid temp = new Grid(1, 25, k);
            this.listXval.add(temp);
            k += 50;
        }

    }

    public void initTestInterpolationFunction(String f, int el) {
        for (Grid v : listXval) {
            InterpolationFunctions temp = new InterpolationFunctions(f, v, el);
            this.ListExperiment.add(temp);
        }
    }

    public ActionListener getTestExample() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String func = view.getFuncTextField();
                try {
                    initTestGird();
                    initTestInterpolationFunction(func, 51);
                } catch (NumberFormatException ex) {
                    view.createErrWind("Incorrect function. Please input correctly function");
                }

            }
        };
    }

    public ActionListener getDataInterpolation() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (InterpolationFunctions t : ListExperiment) {
                    t.print();
                }
            }
        };

    }

    public void plotGraphFunction() {
        final IntrepolationChart_AWT chart = new IntrepolationChart_AWT("Graph", "Iter of ORD");
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

    public ActionListener getPlotFunc() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plotGraphFunction();
                if (view.getFuncTextField().equals("") | view.getBeginTextField().equals("") | view.getBTextField().equals("") | view.getIterTextField().equals("") | view.getEndTextField().equals("")) {
                    //view.createErrWind("ERROR.");
                } else {
                   // plotGraphFunction();
                }
            }
        };
    }

    public ActionListener getPlotData() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("строим график по данным");
            }
        };
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
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 360));
            super.getContentPane().add(chartPanel);
            this.addWindowListener(this);

        }

        private DefaultCategoryDataset createDatasetOrdOfIter() {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for (Model u : listModel) {
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


    }


    public class IntrepolationChart_AWT extends ApplicationFrame {

        public IntrepolationChart_AWT(String applTitle, String chartTitle) {
            super(applTitle);

            JFreeChart OrdOfIter = ChartFactory.createLineChart(
                    chartTitle,
                    "x", "f(x)",
                    createDatasetOrdOfIter(),
                    PlotOrientation.VERTICAL,
                    true, true, false);


            ChartPanel chartPanel = new ChartPanel(OrdOfIter);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 360));
            super.getContentPane().add(chartPanel);
            this.addWindowListener(this);
        }

        private DefaultCategoryDataset createDatasetOrdOfIter() {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for (InterpolationFunctions u : ListExperiment) {
                for (int i = 0; i < u.getGrid_InterpolationValue().getGrid().length; i++) {
                    double fy = u.getGrid_OriginalValue().getGrid()[i];
                    double Py = u.getGrid_InterpolationValue().getGrid()[i];
                    double x = u.getGridXval().getGrid()[i];
                    dataset.addValue(fy, "original", x + "");
                    dataset.addValue(Py, "interpolation", x + "");

                }
            }

            return dataset;
        }

        @Override
        public void windowClosing(WindowEvent event) {
            if (event.getWindow() == null) {
                this.dispose();
                System.exit(0);
            }
        };
    }
}



