import java.util.Locale;

/**
 * Calculadora simples com operações básicas e um CLI pequeno.
 */
public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double sub(double a, double b) {
        return a - b;
    }

    public double mul(double a, double b) {
        return a * b;
    }

    public double div(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Divisão por zero");
        }
        return a / b;
    }

    public double pow(double a, double b) {
        return Math.pow(a, b);
    }

    public double sqrt(double a) {
        if (a < 0.0) {
            throw new ArithmeticException("Raiz quadrada de número negativo");
        }
        return Math.sqrt(a);
    }

    /**
     * CLI simples:
     * - Uso: java -jar calculator.jar op a b
     *   onde op = add|sub|mul|div|pow
     * - Para sqrt: java -jar calculator.jar sqrt a
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            printUsageAndExit();
        }

        String op = args[0].toLowerCase(Locale.ROOT);
        Calculator calc = new Calculator();

        try {
            switch (op) {
                case "add":
                case "+":
                    checkArgCount(args, 3);
                    System.out.println(calc.add(parse(args[1]), parse(args[2])));
                    break;
                case "sub":
                case "-":
                    checkArgCount(args, 3);
                    System.out.println(calc.sub(parse(args[1]), parse(args[2])));
                    break;
                case "mul":
                case "*":
                case "x":
                    checkArgCount(args, 3);
                    System.out.println(calc.mul(parse(args[1]), parse(args[2])));
                    break;
                case "div":
                case "/":
                    checkArgCount(args, 3);
                    System.out.println(calc.div(parse(args[1]), parse(args[2])));
                    break;
                case "pow":
                case "^":
                    checkArgCount(args, 3);
                    System.out.println(calc.pow(parse(args[1]), parse(args[2])));
                    break;
                case "sqrt":
                    checkArgCount(args, 2);
                    System.out.println(calc.sqrt(parse(args[1])));
                    break;
                default:
                    System.err.println("Operador desconhecido: " + op);
                    printUsageAndExit();
            }
        } catch (NumberFormatException e) {
            System.err.println("Número inválido: " + e.getMessage());
            System.exit(2);
        } catch (ArithmeticException e) {
            System.err.println("Erro: " + e.getMessage());
            System.exit(3);
        }
    }

    private static double parse(String s) {
        return Double.parseDouble(s);
    }

    private static void checkArgCount(String[] args, int expected) {
        if (args.length != expected) {
            printUsageAndExit();
        }
    }

    private static void printUsageAndExit() {
        System.err.println("Uso:");
        System.err.println("  java -jar calculator.jar add a b    (ou +)");
        System.err.println("  java -jar calculator.jar sub a b    (ou -)");
        System.err.println("  java -jar calculator.jar mul a b    (ou * ou x)");
        System.err.println("  java -jar calculator.jar div a b    (ou /)");
        System.err.println("  java -jar calculator.jar pow a b    (ou ^)");
        System.err.println("  java -jar calculator.jar sqrt a");
        System.exit(1);
    }
}
