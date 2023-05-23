package CALCYl;

import java.util.Scanner;

public class Main {
        public static void main(String[] args) {

                Scanner scanner = new Scanner(System.in);

                // Считываем строку, введенную пользователем
                System.out.println("Введите выражение:");
                String input = scanner.nextLine();

                // Разбиваем строку на операнды и операцию
                String[] parts = input.split(" ");
                if (parts.length != 3) {
                        System.out.println("Ошибка: некорректный формат ввода.");
                        return;
                }
                String operand1 = parts[0];
                String operator = parts[1];
                String operand2 = parts[2];

                // Определяем тип операндов
                boolean isArabic = true;
                try {
                        Integer.parseInt(operand1);
                        Integer.parseInt(operand2);
                } catch (NumberFormatException e) {
                        isArabic = false;
                }

                // Выполняем операцию
                int result = 0;
                if (isArabic) {
                        int a = Integer.parseInt(operand1);
                        int b = Integer.parseInt(operand2);
                        if (a > 10 || b > 10)
                        {
                                System.out.println("Ошибка: вводимое число больше 10");
                                return;
                        }
                        switch (operator) {
                                case "+":
                                        result = a + b;
                                        break;
                                case "-":
                                        result = a - b;
                                        break;
                                case "*":
                                        result = a * b;
                                        break;
                                case "/":
                                        if (b == 0) {
                                                System.out.println("Ошибка: деление на ноль.");
                                                return;
                                        }
                                        result = a / b;
                                        break;
                                default:
                                        System.out.println("Ошибка: неизвестный оператор.");
                                        return;
                        }
                        System.out.println(result);
                } else {
                        // Проверяем, что операнды римские
                        if (!isRoman(operand1) || !isRoman(operand2)) {
                                System.out.println("Ошибка: использованы разные системы счисления.");
                                return;
                        }
                        int a = romanToArabic(operand1);
                        int b = romanToArabic(operand2);
                        switch (operator) {
                                case "+":
                                        result = a + b;
                                        break;
                                case "-":
                                        result = a - b;
                                        break;
                                case "*":
                                        result = a * b;
                                        break;
                                case "/":
                                        if (b == 0) {
                                                System.out.println("Ошибка: деление на ноль.");
                                                return;
                                        }
                                        result = a / b;
                                        break;
                                default:
                                        System.out.println("Ошибка: неизвестный оператор.");
                                        return;
                        }
                        if (result < 1) {
                                System.out.println("Ошибка: результат меньше единицы.");
                                return;
                        }
                        System.out.println(arabicToRoman(result));
                }
        }

        // Проверяет, является ли строка римским числом
        private static boolean isRoman(String s) {
                String regex = "[MDCLXVI]+";
                return s.matches(regex);
        }

        // Преобразует римское число в арабское число
        private static int romanToArabic(String s) {
                switch (s) {
                        case "I":
                                return 1;
                        case "II":
                                return 2;
                        case "III":
                                return 3;
                        case "IV":
                                return 4;
                        case "V":
                                return 5;
                        case "VI":
                                return 6;
                        case "VII":
                                return 7;
                        case "VIII":
                                return 8;
                        case "IX":
                                return 9;
                        case "X":
                                return 10;
                        default:
                                throw new IllegalArgumentException("Некорректное римское число.");
                }
        }

        // Преобразует арабское число в римское число
        private static String arabicToRoman(int n) {
                if (n < 1 || n > 100) {
                        throw new IllegalArgumentException("Некорректное арабское число.");
                }
                String[] roman = {
                        "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
                };
                int[] arabic = {
                        1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
                };
                StringBuilder sb = new StringBuilder();
                int i = 0;
                while (n > 0) {
                        if (n >= arabic[i]) {
                                sb.append(roman[i]);
                                n -= arabic[i];
                        } else {
                                i++;
                        }
                }
                return sb.toString();
        }
}