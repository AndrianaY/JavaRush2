package com.javarush.test.level09.lesson08.task05;

/* Перехват unchecked исключений
В методе processExceptions обработайте все unchecked исключения.
Нужно вывести стек-трейс каждого возникшего исключения используя метод printStack.
Можно использовать только один блок try..
*/

import static com.sun.xml.internal.ws.model.RuntimeModeler.BEAN;

public class Solution
{
    public static void main(String[] args) {
        processExceptions(new Solution());
    }

    public static void processExceptions(Solution obj) {
        try
        {
            obj.method1();
            obj.method2();
            obj.method3();
        }
        catch (NumberFormatException e2){
            printStack(e2);
        }
        catch (IndexOutOfBoundsException e1){
            printStack(e1);
        }
        catch (NullPointerException e){

            printStack(e);
        }


    }

    public static void printStack(Throwable throwable) {
        System.out.println(throwable);
        for (StackTraceElement element : throwable.getStackTrace()) {
            System.out.println(element);
        }
    }

    public void method1(){
        throw new NullPointerException();
    }

    public void method2() {
        throw new IndexOutOfBoundsException();
    }

    public void method3() {
        throw new NumberFormatException();
    }
}
