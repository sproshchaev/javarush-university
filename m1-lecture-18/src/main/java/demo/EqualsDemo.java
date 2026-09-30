package demo;

import java.util.HashSet;
import java.util.Set;

public class EqualsDemo {

    public static void main(String[] args) {

        Customer anna1 = new Customer("email", "Анна");
        Customer anna2 = new Customer("email", "Анна К.");

        System.out.println(anna1 == anna2);
        System.out.println(anna1.equals(anna2));

        // HashSet
        Set<Customer> customerSet = new HashSet<>();
        customerSet.add(anna1);
        customerSet.add(anna2);
        customerSet.add(new Customer("oleg@email", "Олег"));

        System.out.println("В множестве: " + customerSet.size());
        System.out.println("contains: " + customerSet.contains(new Customer("email", "?"))); // true

    }

}
