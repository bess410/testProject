package com.test;

import java.util.Scanner;

public class ConsoleWorker {
    private final Scanner scanner;

    public ConsoleWorker(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printGreeting() {
        System.out.println("Добро пожаловать. Войдите в систему или зарегистрируйтесь");
    }

    public int printEnterMenu() {
        System.out.println("""
                1 - Войти в ситему
                2 - Зарегистрироваться""");
        return scanner.nextInt();
    }

    public String getLogin() {
        System.out.println("Введите логин");
        return scanner.next();
    }

    public String getPassword() {
        System.out.println("Введите пароль");
        return scanner.next().trim();
    }

    public String createLogin() {
        System.out.println("Придумайте себе логин");
        return scanner.next();
    }

    public String createPassword() {
        System.out.println("Придумайте пароль");
        return scanner.next().trim();
    }
}
