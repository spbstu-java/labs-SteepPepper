package LAB2;

/*Чигирь А.И., группа в3530904/00321*/
/*Лабораторная_2
        Написать консольное приложение, которое:

        Считывает из текстового файл размерность матрицы N*N.
        Создаёт и заполняет матрицу случайными числами от -N до N.
        Последовательно поворачивает матрицу на 90, 180 и 270 градусов против часовой стрелки и делит каждый элемент на сумму соседних.
        Каждую из трёх получившихся матриц вывести в общий файл
        Требования к обработке исключительных ситуаций:

        контролировать состояние потоков ввода/вывода (отсутствие записи в файле, недопустимые значения, etc);
        генерировать и обрабатывать исключение при некорректных математических операциях;
        выбрасывать исключение при нехватке памяти;
        реализовать собственные классы исключений для случаев:
        деление на 0
        файл не существует
        N > 1_000_000!!*/

import LAB2.ExceptionMass.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    static String INPUT_PATH = "src/LAB2/InData.txt";
    static String OUTPUT_PATH = "src/LAB2/OutData.txt";

    public static void main(String[] args) {

        int x;
        int[][] myArray;
        Random r = new Random();

        try{
            if(!Files.exists(Path.of(INPUT_PATH))){
                throw new FileNotFoundedException("Файл не найден");
            }
            Scanner scan = new Scanner(new File(INPUT_PATH));
            x = scan.nextInt();
            if (x > 1000000 || x < 1) {
                throw new NCheckExceptoin("Число в файле слишком большое или меньше 1");
            }
        } catch (FileNotFoundedException | NCheckExceptoin e) {
            System.out.println(e.getMessage());
            return;
        } catch (InputMismatchException e) {
            System.out.println("В файле текст");
            return;
        } catch (NoSuchElementException e) {
            System.out.println("Файл пустой");
            return;
        } catch (Exception e) {
            System.out.println("Не известная ошибка с файлом");
            return;
        }

        try {
            myArray = new int[x][x];
        } catch (OutOfMemoryError e) {
            System.out.println("Недостаточно памяти");
            return;
        }

        for(int i = 0; i < x; i ++){
            for (int j = 0; j < x; j ++){
                myArray[i][j] = r.nextInt((x - (-x)) + 1) + (-x);
            }
        }
        Arrays.stream(myArray).map(Arrays::toString).forEach(System.out::println);

        try(FileWriter writer = new FileWriter(OUTPUT_PATH)) {
            writeToFile(writer, reverseMatrix90(myArray));
            writer.write("\n");
            writeToFile(writer, reverseMatrix180(myArray));
            writer.write("\n");
            writeToFile(writer, reverseMatrix270(myArray));
            writer.flush();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int[][]  reverseMatrix90(int[][] myArray) {
        int[][] rotatedMatrix = new int[myArray.length][myArray.length];
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray.length; j++) {
                rotatedMatrix[i][j] = myArray[myArray.length - j - 1][i];
            }
        }
        return divideMatrix(rotatedMatrix);
    }
    public static int[][]  reverseMatrix180(int[][] myArray) {
        int[][] rotatedMatrix = new int[myArray.length][myArray.length];
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray.length; j++) {
                rotatedMatrix[i][j] = myArray[myArray.length - i - 1][myArray.length - j - 1];
            }
        }
        return divideMatrix(rotatedMatrix);
    }
    public static int[][]  reverseMatrix270(int[][] myArray) {
        int[][] rotatedMatrix = new int[myArray.length][myArray.length];
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray.length; j++) {
                rotatedMatrix[i][j] = myArray[j][myArray.length - i - 1];
            }
        }

        return divideMatrix(rotatedMatrix);
    }
    public static int[][]  divideMatrix(int[][] myArray) {
        int[][] divide = new int[myArray.length][myArray.length];
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray.length; j++) {
                try {int temp = ((i+1 < myArray.length ? myArray[i+1][j] : 0)
                            + (i-1 > 0 ? myArray[i-1][j] : 0)
                            + (j+1 < myArray.length ? myArray[i][j+1] : 0)
                            + (j-1 > 0 ? myArray[i][j-1] : 0));
                    if (temp == 0)
                        throw new DivideByZeroException("Деление на 0");
                    divide[i][j] = myArray[i][j] / temp;}
                catch (DivideByZeroException e) {
                    divide[i][j] = 0;
                }
            }
        }
    return divide;
    }

    public static void writeToFile(FileWriter writer, int[][] values) throws IOException {
        int size = values.length;
        for (int[] value : values) {
            writer.write("[");
            for (int j = 0; j < size; j++) {
                int element = value[j];
                String offset = element >= 0 ? " " : "";
                writer.write(offset + element + " ");
            }
            writer.write("]\n");
        }
    }

}
