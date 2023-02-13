package reflection;

import java.lang.reflect.Constructor;

public class InstantiateClass {
    public static void main(String[] args) {
        if (args.length < 0)
            System.out.println("Usage: java.lang.Class <params separated with space>");
        else
        {
            try {
                var cls = java.lang.Class.forName("java.lang.String");
                Constructor constructor = getConstructor(cls, args.length);
                Class[] parameterTypes = constructor.getParameterTypes();
                java.lang.reflect.Constructor.newInstance();

            } catch (ClassNotFoundException e) {
                System.out.println(args[0] + " is not a valid class name");
            } catch (NoSuchMethodException e) {
                System.out.println("A valid constructor was not found");
            }
        }
    }

    private static Constructor getConstructor(Class<?> cls, int argsLength) throws NoSuchMethodException {
        Constructor[] constructorsArray = cls.getConstructors(); // why not DeclaredConstructors
        for (Constructor c: constructorsArray) {
            if (c.getParameterCount() == argsLength - 1) {
                return c;
            }
        }
        throw new NoSuchMethodException();
    }

}
