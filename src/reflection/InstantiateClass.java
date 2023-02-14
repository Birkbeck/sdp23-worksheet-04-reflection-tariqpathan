package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

// need to handle cases where there are same parameter counts but only one set accepts
// only strings
public class InstantiateClass {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalArgumentException, IllegalAccessException{
        if (args.length < 0)
            System.out.println("Usage: java.lang.Class <params separated with space>");
        else
        {
            try {
                var cls = java.lang.Class.forName(args[0]);
                System.out.println(cls.getName());
                Constructor constructor = getConstructor(cls, args.length);
                Class[] parameterTypes = constructor.getParameterTypes();
                System.out.println(Arrays.asList(parameterTypes));

                // these will be the args used to create the args[0] object
                // these first need to be converted from strings as they are
                // taken from the command line and then converted to their
                // appropriate type
                Object[] typedArgs = new Object[args.length - 1];
                for (int i = 0; i < typedArgs.length; i++) {
                    typedArgs[i] = parameterTypes[i].getConstructor(String.class).newInstance(args[i + 1]);
                }
                Object obj = constructor.newInstance(typedArgs);
                System.out.println(obj.toString());
//                java.lang.reflect.Constructor.newInstance();
                // for each parameterType, I need to check that constructor for
                // that type accepts Strings as an argument, then I need to
                // create that object, then I need to pass these objects into the
                // constructor for the class which I wanted to create at args[0]

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
            System.out.println("paramCount: " + c.getParameterCount());
            if (c.getParameterCount() != argsLength - 1) {
                continue;
            }
            if (Arrays.stream(c.getParameterTypes()).allMatch("java.lang.String"::equals)) return c;

        }
        throw new NoSuchMethodException();
    }

    private static Object[] getTypedArgs (Constructor c, String[] args, int argsLength)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException {
        Object[] typedArgs = new Object[argsLength - 1];
        Class[] parameterTypes = c.getParameterTypes();
        for (int i = 0; i < typedArgs.length; i++) {
            typedArgs[i] = parameterTypes[i].getConstructor(String.class).newInstance(args[i + 1]);
        }
        return typedArgs;
    }

}
