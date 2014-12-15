import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;


public class mainWin {
    private JPanel rootPanel;
    private JLabel inputJLabel;
    private JLabel solve;
    private JRadioButton manualRadioButton;
    private JRadioButton autoRadioButton;
    private JTextField orderTextField1;
    private JCheckBox usingECheckBox;
    private JButton openFileButtonM;
    private JButton openFileButtonB;
    private JButton toSolveButton;
    private JButton getDataButton;
    private JButton plotFromDataButton;
    private JLabel matrixAJLabel;
    private JLabel matrixbJLabel;
    private JLabel matrixMJLabel;
    private JButton openFileButtonA;
    private JLabel orderJLabel;
    private JButton generateButton;
    public JLabel progresJLabel;
    private boolean flagM = true;
    public errorWind errorWindow;
    private File fileA;
    private File fileB;
    private File fileM;
    public int iter;

    public mainWin(Presenter presenter) {
        iter = 1;
        ButtonGroup group = new ButtonGroup();
        group.add(manualRadioButton);
        group.add(autoRadioButton);

        openFileButtonA.addActionListener(getOpenFileButtonAL());
        openFileButtonB.addActionListener(getOpenFileButtonBL());
        openFileButtonM.addActionListener(getOpenFileButtonML());
        usingECheckBox.addChangeListener(getECheckBoxL());
        autoRadioButton.addChangeListener(getAutoRadioButtonL());
        manualRadioButton.addChangeListener(getManualRadioButtonL());
        generateButton.addActionListener(presenter.getGenerateL());
        toSolveButton.addActionListener(presenter.getToSolveL());
        getDataButton.addActionListener(presenter.getDataButtonL());
        plotFromDataButton.addActionListener(presenter.getPlotFromDataL());

        autoRadioButton.doClick(); // изначальное состояние радио кнопки
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }




    public ChangeListener getManualRadioButtonL() {
      return  new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                boolean flag = true;
                matrixAJLabel.setEnabled(flag);
                matrixbJLabel.setEnabled(flag);
                matrixMJLabel.setEnabled(flag);
                openFileButtonM.setEnabled(flag);
                openFileButtonA.setEnabled(flag);
                openFileButtonB.setEnabled(flag);
                usingECheckBox.setEnabled(flag);


                orderJLabel.setEnabled(!flag);
                orderTextField1.setEnabled(!flag);
                generateButton.setEnabled(!flag);
                progresJLabel.setEnabled(!flag);
            }
        };
    }

    public ChangeListener getAutoRadioButtonL() {
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                boolean flag = false;
                matrixAJLabel.setEnabled(flag);
                matrixbJLabel.setEnabled(flag);
                matrixMJLabel.setEnabled(flag);
                openFileButtonM.setEnabled(flag);
                openFileButtonA.setEnabled(flag);
                openFileButtonB.setEnabled(flag);
                usingECheckBox.setEnabled(flag);


                orderJLabel.setEnabled(!flag);
                orderTextField1.setEnabled(!flag);
                generateButton.setEnabled(!flag);
                progresJLabel.setEnabled(!flag);
            }
        };
    }

    public ChangeListener getECheckBoxL() {
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (flagM) {
                    flagM = false;
                    openFileButtonM.setEnabled(flagM);

                } else {
                    flagM = true;
                    openFileButtonM.setEnabled(flagM);

                }
            }
        };
    }

    public ActionListener getOpenFileButtonAL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileA = ChoiseFile();
                if (fileA != null) {
                }
            }
        };
    }

    public ActionListener getOpenFileButtonML() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileM = ChoiseFile();
            }
        };
    }

    public ActionListener getOpenFileButtonBL() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileB = ChoiseFile();
            }
        };
    }

    public int parseTextField(String s) throws NumberFormatException {
        int a;
        a = Integer.parseInt(s);
        return a;
    }

    public File ChoiseFile() {
        File file;
        JFileChooser fileopen = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Only file *.csv", "csv");
        fileopen.setFileFilter(filter);
        int ret = fileopen.showDialog(null, "Выбрать файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileopen.getSelectedFile();
            String str = FilenameUtils.getExtension(fileopen.getName(file));
            if (str.equals("csv")) {
                return file;
            } else {
                file = null;
                createErrWind("Вы выбрали не подходящий тип файла");
                return file;
            }
        } else {
            file = null;
            return file;
        }
    }

    public File getFileA() {
        return fileA;
    }

    public File getFileB() {
        return fileB;
    }

    public File getFileM() {
        return fileM;
    }

    public String getText() {
        return orderTextField1.getText();
    }

    public void createErrWind(String a) {
        errorWindow = new errorWind(a);
        errorWindow.setVisible(true);
        errorWindow.setLocationRelativeTo(null);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        rootPanel.setBackground(new Color(-4143920));
        rootPanel.setEnabled(false);
        rootPanel.setFocusTraversalPolicyProvider(true);
        rootPanel.setInheritsPopupMenu(false);
        solve = new JLabel();
        solve.setText("Solve");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        rootPanel.add(solve, gbc);
        autoRadioButton = new JRadioButton();
        autoRadioButton.setText("Auto");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 50, 0, 0);
        rootPanel.add(autoRadioButton, gbc);
        matrixAJLabel = new JLabel();
        matrixAJLabel.setText("Matrix A");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 20, 0, 0);
        rootPanel.add(matrixAJLabel, gbc);
        openFileButtonA = new JButton();
        openFileButtonA.setText("Open file");
        openFileButtonA.setToolTipText("Choise file format *.csv with matrix A");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(openFileButtonA, gbc);
        orderJLabel = new JLabel();
        orderJLabel.setText("Order:");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 30, 0, 0);
        rootPanel.add(orderJLabel, gbc);
        orderTextField1 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(orderTextField1, gbc);
        matrixbJLabel = new JLabel();
        matrixbJLabel.setText("Matrix b");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 20, 0, 0);
        rootPanel.add(matrixbJLabel, gbc);
        matrixMJLabel = new JLabel();
        matrixMJLabel.setText("Matrix M");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 20, 0, 5);
        rootPanel.add(matrixMJLabel, gbc);
        usingECheckBox = new JCheckBox();
        usingECheckBox.setText("Using E");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        rootPanel.add(usingECheckBox, gbc);
        openFileButtonM = new JButton();
        openFileButtonM.setText("Open file");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(openFileButtonM, gbc);
        openFileButtonB = new JButton();
        openFileButtonB.setText("Open file");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(openFileButtonB, gbc);
        toSolveButton = new JButton();
        toSolveButton.setText("toSolve");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(toSolveButton, gbc);
        getDataButton = new JButton();
        getDataButton.setText("getData");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(getDataButton, gbc);
        plotFromDataButton = new JButton();
        plotFromDataButton.setText("plotFromData");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(plotFromDataButton, gbc);
        manualRadioButton = new JRadioButton();
        manualRadioButton.setText("Manual");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 80, 0, 0);
        rootPanel.add(manualRadioButton, gbc);
        inputJLabel = new JLabel();
        inputJLabel.setEnabled(true);
        inputJLabel.setHorizontalAlignment(2);
        inputJLabel.setHorizontalTextPosition(2);
        inputJLabel.setOpaque(false);
        inputJLabel.setText("Input Data");
        inputJLabel.setVerifyInputWhenFocusTarget(true);
        inputJLabel.setVerticalAlignment(0);
        inputJLabel.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        rootPanel.add(inputJLabel, gbc);
        generateButton = new JButton();
        generateButton.setText("Generate");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(generateButton, gbc);
        progresJLabel = new JLabel();
        progresJLabel.setText("Number of gen matrix: 0");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.weightx = 3.0;
        rootPanel.add(progresJLabel, gbc);
        orderJLabel.setLabelFor(orderTextField1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}


