package reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ToString {
    public static String toString(Object obj) {

        Class clazz = obj.getClass();
        clazz.getName();
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields).forEach(f -> {
            f.setAccessible(true);
            try {
                Object value = f.get(obj);
                System.out.print("The name of the field: " + f.getName());
                System.out.print(" and it's value is " + value + "\n");
            } catch (IllegalAccessException e) {
                //throw new RuntimeException(e);
            }
        });

        return null;
    }

    public static void main(String[] args) {
        Person p = new Person("T", 23, 78f);
        ToString t = new ToString();
        t.toString(p);
    }
}
