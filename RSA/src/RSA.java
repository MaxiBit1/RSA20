import GenerationSimpleNumber.GeneratorSimpleNumber;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
//Возьмем два больших простых числа p and q.
//Определим n, как результат умножения p on q (n= p*q).
//Выберем случайное число, которое назовем d. Это число должно быть взаимно простым (не иметь ни одного общего делителя, кроме 1) с результатом умножения (p-1)*(q-1).
//Определим такое число е, для которого является истинным следующее соотношение (e*d) mod ((p-1)*(q-1))=1.
//Hазовем открытым ключем числа e и n, а секретным - d и n.

public class RSA {

    private static GeneratorSimpleNumber gs = new GeneratorSimpleNumber();
    private static int p = gs.Proverka();
    private static int q = gs.Proverka();
    private static int peremnozhenie = (p-1) * (q-1);
    private static Random random= new Random();
    private static int d =D();

    public static int ShifAndRashifr(int m, int e, int n){
        BigInteger M = new BigInteger(Integer.toString(m));
        BigInteger E = new BigInteger(Integer.toString(e));
        BigInteger N = new BigInteger(Integer.toString(n));
        return (M.modPow(E,N)).intValue();
    }

    private static int gcd(int a, int b) {
        int c;
        while (b > 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return Math.abs(a);
    }


    private static int D(){
        int c,d;
        while (true) {
            c = random.nextInt((peremnozhenie - 1)+1)+1;
            int temp = gcd(c, peremnozhenie);
            if (temp == 1) {
                d = c;
                break;
            }
        }
        return d;
    }

    private static int E(){
        int e = 0;
        int fo,formula;
        while (true){
            fo = e*d;
            BigInteger F = new BigInteger(Integer.toString(fo));
            BigInteger PR = new BigInteger(Integer.toString(peremnozhenie));
            formula = (F.mod(PR)).intValue();
            if(formula == 1){
                break;
            }
            else {
                e++;
            }
        }
        return e;
    }

    public void rsaMain(){
        int n = p*q;
        int e = E();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите параметр для буквы: ");
        int m = scanner.nextInt();
        int c = ShifAndRashifr(m,e,n);
        System.out.println("C: "+c);
        int a = ShifAndRashifr(c,d,n);
        System.out.println("Result: "+a);
    }
}
