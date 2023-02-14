package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class InstantiateClass {
    public static void main(String[] args) throws InstantiationException, IllegalArgumentException, IllegalAccessException{
        if (args.length < 0)
            System.out.println("Usage: java.lang.Class <params separated with space>");
        else
        {
            try {
                var cls = java.lang.Class.forName(args[0]);
                Constructor constructor = getConstructor(cls, args.length);
                Object[] typedArgs = getTypedArgs(constructor, args);
                if (typedArgs.length != constructor.getParameterCount()) {
                    throw new IllegalArgumentException();
                }
                Object obj = constructor.newInstance(typedArgs);
                System.out.println(obj.toString());

            } catch (ClassNotFoundException e) {
                System.out.println(args[0] + " is not a valid class name");
            } catch (NoSuchMethodException e) {
                System.out.println("A valid constructor was not found for " + args[0]);
            } catch (InvocationTargetException e) {
                System.out.println("I don't know what this means yet");
            }
        }
    }

    private static Constructor getConstructor(Class<?> cls, int argsLength) throws NoSuchMethodException {
        Constructor[] constructorsArray = cls.getConstructors(); // why not DeclaredConstructors
        for (Constructor c: constructorsArray) {
            if (c.getParameterCount() != argsLength - 1) {
                continue;
            }
            if (Arrays.stream(c.getParameterTypes()).allMatch(String.class::equals)) return c;
        }
        throw new NoSuchMethodException();
    }

    private static Object[] getTypedArgs (Constructor c, String[] args) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object[] typedArgs = new Object[args.length - 1];
        Class[] parameterTypes = c.getParameterTypes();
        for (int i = 0; i < typedArgs.length; i++) {
            typedArgs[i] = parameterTypes[i].getConstructor(String.class).newInstance(args[i + 1]);
        }
        return typedArgs;
    }

}
