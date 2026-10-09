package com.javarush.demo;

import com.javarush.demo.exception.InsufficientFundsException;

public class CheckedCustomExceptionDemo {

    private static int balance = 1000;

    public static void main(String[] args) {

        try {
            withdraw(300);
            withdraw(900);
            withdraw(100);
        } catch (InsufficientFundsException e) {
            System.out.println("Операция отклонена " + e.getMessage());
        }
        System.out.println("Остаток: " + balance);

    }

    /**
     * Получение наличной суммы со счета карты
     * @param amount - сумма которую нужно получить
     */
    private static void withdraw(int amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("запрошено "
                    + amount + ", на счете " + balance);
        }
        balance = balance - amount;
        System.out.println("Сняли " + amount + ", осталось " + balance);
    }

}
