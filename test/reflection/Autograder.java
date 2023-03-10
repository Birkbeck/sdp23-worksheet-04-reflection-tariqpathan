package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Autograder {

    static final String CLASS_NAME = ""; // TODO: insert the class name to be tested

    Class clazz = Class.forName(CLASS_NAME);

    public Autograder() throws ClassNotFoundException {
    }

    @Test
    public void testMoreThanFourFields() {
        if (clazz.getDeclaredFields().length > 4) {
            throw new RuntimeException("Class contains more than 4 fields");
        }
    }

    @Test
    public void testNonPrivateFields() {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (!Modifier.isPrivate(f.getModifiers())) {
                throw new RuntimeException(f.getName() + "is a non-private field");
            }
        }
    }

    @Test
    public void testArrayListField() {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.getType().isAssignableFrom(ArrayList.class)) {
                throw new RuntimeException(f.getName() + " contains a field of ArrayList");
            }
        }
    }
    @Test
    public void testMoreThanTwoPrivateHelperMethods() {
        Method[] methods = clazz.getMethods();
        int countPrivateMethods = 0;
        for (Method m : methods) {
            if (Modifier.isPrivate(m.getModifiers()))
            {
                countPrivateMethods += 1;
                if (countPrivateMethods > 2) {
                    throw new RuntimeException("More than 2 private methods found");
                }
            }
        }
    }
    @Test
    public void testMethodHasThrowsClause() {
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.getExceptionTypes().length > 0) {
                throw new RuntimeException(m.getName() + " throws an Exception");
            }
        }
    }
    @Test
    public void testMethodReturnsInt() {
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.getReturnType().equals(Integer.TYPE)) {
                throw new RuntimeException("Method " + m.getName() + " returns an int");
            }
        }
    }
    @Test
    public void testMissingZeroArgumentConstructor() {
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors) {
            if (c.getParameterCount() == 0) {
                return;
            }
        }
        throw new RuntimeException("A zero argument constructor was not found");
    }
}
