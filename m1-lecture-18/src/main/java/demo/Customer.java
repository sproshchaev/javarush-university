package demo;

import java.util.Objects;

public class Customer {

    private final String email;
    private final String name;

    public Customer(String email, String name) {
        this.email = email;
        this.name = name;
    }

    // a.equals(b)

    // Два покупателя равны, если у них email равны (email, name = оба участвуют в переопределении)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass())  {
            return false;
        }
        Customer other = (Customer) o;
        return Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return name + "<" + email + ">";
    }

}
