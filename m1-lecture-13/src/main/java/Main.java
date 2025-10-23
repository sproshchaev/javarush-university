import com.javarush.Car;
import com.javarush.InitializationOrder;
import com.javarush.StaticVsInstanceVariable;
import com.javarush.config.AppConfig;

import java.text.MessageFormat;

/**
 * JavaRush-University
 */
public class Main {

    public static void main(String[] args) {
        // StaticVariable staticVariable1 = new StaticVariable();
        // staticVariable1.showTotalCount();

        StaticVsInstanceVariable stident1 = new StaticVsInstanceVariable("Анна");
        stident1.showInfo();

        StaticVsInstanceVariable stident2 = new StaticVsInstanceVariable("Борис");
        stident2.showInfo();

        System.out.println(stident1 + " hashCode=" + stident1.hashCode());
        System.out.println(stident2 + " hashCode=" + stident2.hashCode());
        // System.out.println("Коллизия: " + stident1.equals(stident2) + " " + (stident1.hashCode() == stident2.hashCode()));

        System.out.println(StaticVsInstanceVariable.totalStudents); // одна на всех
        System.out.println(stident1.name); // одна на каждый экземпляр
        System.out.println("stident1.totalStudents: " + stident1.totalStudents++);
        System.out.println("stident1.totalStudents: " + stident1.totalStudents++);
        System.out.println(StaticVsInstanceVariable.totalStudents); // одна на всех
        // StaticVsInstanceVariable.name;

        AppConfig.count_run++;
        System.out.println("Приложение " + AppConfig.APPLICATION_NAME + " запущено " + AppConfig.count_run);
        AppConfig.init(0);
        System.out.println(String.format("Счетчик запуска обнулен %d", AppConfig.count_run));

        String template = "Hello, {0}! Hello for {1}";
        String result = MessageFormat.format(template, "Java", "Jon");
        System.out.println(result);

        // Экземпляр внешнего класса
        Car car = new Car();
        Car.Engine engine = car.new Engine();
        // Car.Webasto webasto = ...
        engine.start();

        InitializationOrder initializationOrder = new InitializationOrder();
        System.out.println(InitializationOrder.staticField);
        System.out.println(InitializationOrder.staticFieldOuter);
        System.out.println(InitializationOrder.staticCountryArray[0]);
    }

}
