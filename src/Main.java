import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String[] massiv = splittingStr(scanner.nextLine());
        String operator = massiv[1];

        // проверка оператора на корректность
        chekingOperator(operator);

        String firstNumber = massiv[0];
        String secondNumber = massiv[2];

        // сравнение типов чисел и запись общего типа в переменную
        String type = compareAndType(firstNumber, secondNumber);

        // если тип чисел Римский, то преобразовываем числа в Арабский тип
        if(Objects.equals(type, "R")){
            firstNumber = translateToArab(firstNumber);
            secondNumber = translateToArab(secondNumber);
        }

        // переводим числа в int для математических операций над ними
        int num1 = Integer.parseInt(firstNumber);
        int num2 = Integer.parseInt(secondNumber);

        // проверяем, входят ли числа в диапазон от 1 до 10
        if (10<num1 || num1<1 || 10<num2 || num2<1){
            Exception E = new Exception();
            throw E;
        }

        // проводим математическую операцию над числами
        Calc Calc = new Calc();
        int answer = Calc.calculate(num1, num2, operator);

        // создаем стринговый ответ для будущей замены переводом числа
        String answerStr = Integer.toString(answer);

        // если тип - римский, то переводим число в него
        if (Objects.equals(type, "R")){
            answerStr = translateToRome(answer);
        }

        // если число не перевелось, отдаем исключение
        if (Objects.equals(answerStr, "")){
            Exception E = new Exception();
            throw E;
        }
        System.out.println(answerStr);
    }

    static String[] splittingStr(String arg) {
        return arg.split(" ");
    }

    static String compareAndType(String num1, String num2) throws Exception {
        String[] romeNums = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String type1 = "A";
        String type2 = "A";
        for (int i = 1; i < 11; i++){
            if (Objects.equals(num1, romeNums[i])) {
                type1 = "R";
            }

            if (Objects.equals(num2, romeNums[i])) {
                type2 = "R";
            }
        }
        if (!type1.equals(type2)){
            Exception E = new Exception();
            throw E;
        }

        return type1;
    }

    static void chekingOperator(String oper) throws Exception {
        String operators = "+-*/";
        if (!operators.contains(oper)){
            Exception E = new Exception();
            throw E;
        }
    }

    static String translateToArab(String num) {
        String[] romeNums = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 1; i < 11; i++){
            if (Objects.equals(num, romeNums[i])){
                num = Integer.toString(i);
            }
        }
        return num;
    }

    static String translateToRome(int num){
        String[] romeNums = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
                "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
        String romeNum = "";
        for (int i = 1; i < 21; i++){
            if (num==i){
                romeNum = romeNums[i];
            }
        }
        return romeNum;
    }
}

class Calc{
    int calculate(int num1, int num2, String oper){
        int answer = 0;
        switch (oper){
            case "+":
                answer = num1+num2;
                break;
            case "-":
                answer = num1-num2;
                break;
            case "*":
                answer = num1*num2;
                break;
            case "/":
                answer = num1/num2;
                break;
        }
        return answer;
    }
}



