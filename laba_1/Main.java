package laba_1;
import java.util.Scanner;
/*Чигирь А.И., группа в3530904/00321*/

/*
Лабораторная_1
    В компьютерной игре герой (класс Hero) может перемещаться между двумя точками (метод move)
    различными способами: идти пешком, ехать на лошади, лететь и т.п..
    Реализовать классы, позволяющие выбирать и менять в ходе выполнения программы способ
    перемещения героя, используя паттерн “стратегия” (strategy).
    Продемонстрировать работу реализованных классов.
 */
public class Main {

    public static void main(String[] args) {
        System.out.print("1 - идти пешком\n2 - скакать на лошади\n3 - летать");
        Scanner input = new Scanner (System.in);
        Hero hero = new Hero();
loop:  while (true) {
            int inputNumberMovement;
            double newX, newY;
            System.out.print("Введите цифру для выбора передвижения: ");
            if(input.hasNextInt()) {
                inputNumberMovement = input.nextInt();
                if(inputNumberMovement < 1 || inputNumberMovement > 3) {
                    break;
                }
            } else {
                break;
            }
            System.out.print("Введите вещественное число для конченой кординаты X движения: ");
            if(input.hasNextDouble()) {
                newX = input.nextDouble();
            } else {
                break;
            }
            System.out.print("Введите вещественное число для конченой кординаты Y движения: ");
            if(input.hasNextDouble()) {
                newY = input.nextDouble();
            } else {
                break;
            }

            switch (inputNumberMovement) {
                case 1: hero.setMovement(new Walk()); break;
                case 2: hero.setMovement(new HourseRiding()); break;
                case 3: hero.setMovement(new Flight()); break;
                default: break loop;
            }
            System.out.println("Коэффициент скорости = " + hero.movement.getSpeedCoefficient());
            hero.executeMovement(new Point(newX, newY));
        }
        System.out.println("Движение закончено.");
        input.close();
    }
    
}
