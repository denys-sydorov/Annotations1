package com.gmail.kpisid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
@interface Test {
    int a();
    int b();
}

class Summa {
    @Test(a = 2, b = 5)
    public static int sum(int a, int b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) throws Exception{
        try {
            Class<?> cls = Summa.class;
            Method method = cls.getMethod("sum", int.class, int.class);
            if (method.isAnnotationPresent(Test.class)) {

                Test test = method.getAnnotation(Test.class);
                int res = (Integer) method.invoke(null, test.a(), test.b());
                System.out.println("a + b = " + res);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
