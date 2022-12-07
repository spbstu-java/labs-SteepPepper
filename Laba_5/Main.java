package LAB5;

/*Чигирь А.И., группа в3530904/00321/

/*С использованием StreamAPI реализовать следующие методы:

        метод, возвращающий среднее значение списка целых чисел; 
        метод, приводящий все строки в списке в верхний регистр и добавляющий к ним префикс «_new_»; 
        метод, возвращающий список квадратов всех встречающихся только один раз элементов списка; 
        метод, принимающий на вход коллекцию строк и возвращающий все строки, начинающиеся с заданной буквы, отсортированные по алфавиту; 
        метод, принимающий на вход коллекцию и возвращающий её последний элемент или кидающий исключение, если коллекция пуста; 
        метод, принимающий на вход массив целых чисел, возвращающий сумму чётных чисел или 0, если чётных чисел нет;
        метод, преобразовывающий все строки в списке в Map, где первый символ – ключ, оставшиеся – значение; */

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList(
                "Test",
                "Stringus",
                "in list",
                "short stringin",
                "tra-tatata"
        );
        List<Integer> integerList = Arrays.asList(1, 1, 2, 3, 4, 4, 5, 6);

        String criteria = "T";


        System.out.println(getAvarage(integerList));

        System.out.println(getNewPref(stringList));

        System.out.println(getUnicPow(integerList));

        System.out.println(getFiltSort(stringList,criteria));



        try {
            System.out.println(getLastCollectionElement(integerList));
        } catch (Exception e) {
            System.out.println("Пустой объект");
        }

        System.out.println(getKeyMap(stringList));

        System.out.println((getSumus(integerList.stream().mapToInt(Integer::intValue).toArray())));
    }



//        метод, возвращающий среднее значение списка целых чисел;
    private static double getAvarage(List<Integer> integerList){
        return integerList.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage();
    }

//        метод, приводящий все строки в списке в верхний регистр и добавляющий к ним префикс «_new_»;
    private static List<String> getNewPref(List<String> stringList){
        return stringList.stream()
                .map(s -> {
                    s = s.toUpperCase();
                    s = "_new_" + s;
                    return s;})
                .collect(Collectors.toList());
    }

    //         метод, возвращающий список квадратов всех встречающихся только один раз элементов списка;
    private static List<Integer> getUnicPow(List<Integer> integerList) {
        return integerList.stream()
                .mapToInt(num -> num)
                .filter(num -> Collections.frequency(integerList, num) == 1)
                .mapToObj(num -> (int) Math.pow(num, 2))
                .collect(Collectors.toList());
    }

//        метод, принимающий на вход коллекцию строк и возвращающий все строки, начинающиеся с заданной буквы, отсортированные по алфавиту;
    private static List<String> getFiltSort(Collection<String> stringList, String criteria) {
        return stringList.stream()
                .filter(s -> s.substring(0, 1).equalsIgnoreCase(criteria.toLowerCase()))
                .sorted()
                .collect(Collectors.toList());
    }

//        метод, преобразовывающий все строки в списке в Map, где первый символ – ключ, оставшиеся – значение;*/
    private static Map<String, String> getKeyMap(List<String> stringList) {
        return stringList.stream()
                .collect(Collectors.toMap(s -> s.substring(0, 1), s -> s.substring(1)));
    }

//        метод, принимающий на вход коллекцию и возвращающий её последний элемент или кидающий исключение, если коллекция пуста;
    private static Object getLastCollectionElement(Collection<?> collection) throws Exception {
        return collection.stream()
                .reduce((f, s) -> s)
                .orElseThrow(Exception::new);
    }

    //        метод, принимающий на вход массив целых чисел, возвращающий сумму чётных чисел или 0, если чётных чисел нет;
    private static long getSumus(int[] arr) {
        return Arrays.stream(arr)
                .filter(num -> num % 2 == 0)
                .summaryStatistics()
                .getSum();
    }
}

