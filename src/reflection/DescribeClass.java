package reflection;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class DescribeClass {
    /* java.lang.Class.forName requires fully qualified name
        avoids ambiguity, e.g. if two packages have the same class name */

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: DescribeClass.java <ClassName>");
        } else {

            for (String arg : args) {
                System.out.println("*************");
                try {
                    var classInfo = java.lang.Class.forName(arg);
                    System.out.println("class info for: " + arg);
                    getClassInformation(classInfo);
                } catch (ClassNotFoundException e) {
                    System.out.println(arg + " Class wasn't found");
                }
            }
        }
    }

    private static void getClassInformation(Class<?> classInfo) {
        Class<?>[] interfaces = classInfo.getInterfaces();
        Arrays.stream(interfaces).forEach(System.out::println);
    }
}
