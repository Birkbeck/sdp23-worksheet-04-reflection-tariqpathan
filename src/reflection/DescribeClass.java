package reflection;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class DescribeClass {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: DescribeClass.java <ClassName>");
        } else {
            try {
                var classInfo = java.lang.Class.forName(args[0]);
                System.out.println("entered: " + args[0]);
                getClassInformation(classInfo);
            } catch (ClassNotFoundException e) {
                System.out.println(args[0] + " wasn't found");
            }
        }
    }

    private static void getClassInformation(Class<?> classInfo) {
        System.out.println(classInfo.getConstructors().length);
        Constructor<?>[] constructors = classInfo.getConstructors();
        Arrays.stream(constructors).limit(4).forEach(System.out::println);
        System.out.println(constructors.length > 4 ? "..." : "");
//        for (Constructor c : constructors) {
//            System.out.println(c.getName());
//        }
        //System.out.println(classInfo.getDeclaredConstructor().length);
    }
}
