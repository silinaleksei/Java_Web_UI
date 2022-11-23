package org.example.lesson4;

public class Triangle {
    public static double squareOfTriangle(int a, int b, int c) throws MyException {
        if (!(a + b > c &&  b + c > a && c + a > b)) throw new MyException("It's not a triangle");
        int p = (a+b+c)/2;
        return Math.round(Math.sqrt(p*(p-a)*(p-b)*(p-c)));
    }
}
