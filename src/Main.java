import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static String[] romesTen = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    public static String[] romesTwenty = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
            "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String[] massiv = scanner.nextLine().split(" ");

        // проверяем длину массива
        chekingLength(massiv);

        String operator = massiv[1];

        // проверка оператора на корректность
        chekingOperator(operator);

        String firstNumber = massiv[0];
        String secondNumber = massiv[2];

        // сравнение типов чисел и запись общего типа в переменную
        String type = compareAndType(firstNumber, secondNumber);

        // если тип чисел Римский, то преобразовываем числа в Арабский тип
        if (Objects.equals(type, "R")) {
            firstNumber = translateToArab(firstNumber);
            secondNumber = translateToArab(secondNumber);
        }

        // переводим числа в int для математических операций над ними
        int num1 = Integer.parseInt(firstNumber);
        int num2 = Integer.parseInt(secondNumber);

        // проверяем, входят ли числа в диапазон от 1 до 10
        checkingRange(num1, num2);

        // проводим математическую операцию над числами
        int answer = calculate(num1, num2, operator);

        // создаем стринговый ответ для будущей замены переводом числа
        String answerStr = Integer.toString(answer);

        // если тип - римский, то переводим число в него
        if (Objects.equals(type, "R")) {
            answerStr = translateToRome(answer);
        }

        // если число не перевелось, отдаем исключение
        chekingRomeAnswer(answerStr);

        System.out.println(answerStr);
    }

    static String compareAndType(String num1, String num2) throws Exception {
        String type1 = "A";
        String type2 = "A";
        for (int i = 1; i < 11; i++) {
            if (Objects.equals(num1, romesTen[i])) {
                type1 = "R";
            }

            if (Objects.equals(num2, romesTen[i])) {
                type2 = "R";
            }
        }
        if (!type1.equals(type2)) {
            throw new Exception();
        }

        return type1;
    }

    static void chekingOperator(String oper) throws Exception {
        String operators = "+-*/";
        if (!operators.contains(oper)) {
            throw new Exception();
        }
    }

    static String translateToArab(String num) {
        for (int i = 1; i < 11; i++) {
            if (Objects.equals(num, romesTen[i])) {
                num = Integer.toString(i);
            }
        }
        return num;
    }

    static String translateToRome(int num) {
        String romeNum = "";
        for (int i = 1; i < 21; i++) {
            if (num == i) {
                romeNum = romesTwenty[i];
            }
        }
        return romeNum;
    }

    static int calculate(int num1, int num2, String oper) {
        int answer = 0;
        switch (oper) {
            case "+":
                answer = num1 + num2;
                break;
            case "-":
                answer = num1 - num2;
                break;
            case "*":
                answer = num1 * num2;
                break;
            case "/":
                answer = num1 / num2;
                break;
        }
        return answer;
    }

    static void checkingRange(int num1, int num2) throws Exception {
        if (10 < num1 || num1 < 1 || 10 < num2 || num2 < 1) {
            throw new Exception();
        }
    }

    static void chekingRomeAnswer(String answer) throws Exception {
        if (Objects.equals(answer, "")) {
            throw new Exception();
        }
    }

    static void chekingLength(String[] arg) throws Exception {
        if (arg.length > 3){
            throw new Exception();
        }
    }
}
