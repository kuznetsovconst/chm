import Jama.Matrix;

import java.io.*;
import java.util.*;

class ReadFileCSV {
   private String[] rixArray;
   private double[][] matrix;

    public void run (File fileCSV) {


        ArrayList<double[]> a;
        a = new ArrayList<double[]>();

        BufferedReader buff = null;
        String line = "";
        String SplitBy = ";";
        int i, j, arSize;

        try {

            buff = new BufferedReader(new FileReader(fileCSV));
            int k = 0;
            while ((line = buff.readLine()) != null) {

                rixArray = line.split(SplitBy); // пропускаем точки с запятой
                arSize = rixArray.length;
                double[] intArray = new double[arSize];

                for (i = 0; i < arSize; i++) {
                    k++;
                    intArray[i] = Double.parseDouble(rixArray[i]);
                }
                a.add(intArray);
            }
            matrix = new double[a.size()][rixArray.length];

            // заполнение матрицы
            for (j = 0; j < a.size(); j++) {
                for (i = 0; i < rixArray.length; i++) {
                     matrix[j][i] = a.get(j)[i];
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("------Файл не существует, проверьте путь------");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
                if (buff != null) {
                    try {
                        buff.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }



    }

    public Matrix getMatrix(){
        return (new Matrix(matrix));
    }

}
