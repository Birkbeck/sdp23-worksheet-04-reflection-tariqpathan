package reflection;

public class Person {
    String fName;
    int age;
    Float weight;


    public Person(String fName, int age, Float weight) {
        this.fName = fName;
        this.age = age;
        this.weight = weight;
    }

    public Person (String fName, String age, String weight) {
        this.fName = fName;
        this.age = Integer.valueOf(age);
        this.weight = Float.valueOf(weight);
    }

    @Override
    public String toString() {
        return this.fName + " has an age of " + this.age + " and a weight of " + this.weight + " kg";
    }

    public static void main(String[] args) {
        Person p = new Person("abc", 23, 45.4f);
        System.out.println(p.toString());
        System.out.println(p.getClass());
    }
}
