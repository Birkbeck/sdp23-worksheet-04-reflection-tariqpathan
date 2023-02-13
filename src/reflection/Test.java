package reflection;
import java.lang.reflect.Field;

/*
For trying out little bits of code to help with understanding
*/
public class Test {

    enum ExampleEnum {
        ONE, TWO, THREE, FOUR
    };

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ExampleEnum value = ExampleEnum.FOUR;
//        ExampleEnum[] enumConstants = value.getClass().getEnumConstants();
//        for (ExampleEnum e : enumConstants) {
//            System.out.println("enum constant: " + e);
//        }
        System.out.println(value.getClass().getFields());
        Field[] flds = value.getClass().getDeclaredFields();
        for( Field f : flds )
        {
            System.out.println( f.getName() + " " + f.isEnumConstant() );
        }

        String stringer = "this is a String called stringer";
        Class<? extends String> stringGetClass = stringer.getClass();
        Class<String> stringclass = String.class;
        Field[] fields = stringclass.getDeclaredFields();
        for( Field field : fields ) {
            System.out.println("*************************");
            System.out.println("Name: " + field.getName());
            System.out.println("Type: " + field.getType());
            // values
            if( field.isAccessible() ) {
                System.out.println("Get: " + field.get(stringer));
            }
        }

    }


}
