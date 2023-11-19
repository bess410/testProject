package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private final Map<String, String> users = new HashMap<>();
    private final ConsoleWorker consoleWorker;
    private final Validator validator;
    private boolean userEnterFlowFinished;

    public Application() {
        consoleWorker = new ConsoleWorker(new Scanner(System.in));
        validator = new Validator();
        userEnterFlowFinished = false;
    }

    public void start() {
        consoleWorker.printGreeting();

        while (!userEnterFlowFinished) {
            int answer = consoleWorker.printEnterMenu();
            switch (answer) {
                case 1 -> startLoginFlow();
                case 2 -> startRegistrationFlow();
                default -> startErrorFlow();
            }
        }
    }

    private void startRegistrationFlow() {
        boolean createLogin = false;
        while (!createLogin) {
            String login = consoleWorker.createLogin();
            if (users.containsKey(login)) {
                System.out.println("Данный логин уже занят, придумайте другой");
            } else {
                createLogin = true;
                String password = consoleWorker.createPassword();
                users.put(login, password);
                System.out.println("Привет " + login + ". Теперь вы можете выполнить вход в систему.");
            }
        }
    }

    private void startLoginFlow() {
        validator.resetAttempts();
        boolean checkLogin = false;
        while (!checkLogin) {
            String login = consoleWorker.getLogin();

            if (!users.containsKey(login)) {
                System.out.println("Пользователь с логином " + login + " не найден");
            } else {
                checkLogin = true;
                boolean checkPassword = false;
                while (!checkPassword) {
                    String password = consoleWorker.getPassword();
                    String savedPassword = users.get(login);
                    if (savedPassword != null && savedPassword.equals(password)) {
                        System.out.println("Вход в приложение выполнен");
                        checkPassword = true;
                        userEnterFlowFinished = true;
                    } else {
                        System.out.println("Введен неверный пароль");
                        validator.decreaseEnterAttempts();
                        int attempts = validator.getAttempts();
                        if (attempts <= 0) {
                            System.out.println("Слишком много попыток. Попробуйте позже.");
                            checkPassword = true;
                            userEnterFlowFinished = true;
                        } else {
                            System.out.println("Осталось попыток для ввода пароля: " + attempts);
                        }
                    }
                }
            }
        }
    }

    private void startErrorFlow() {
        validator.decreaseEnterAttempts();
        int attempts = validator.getAttempts();
        if (attempts <= 0) {
            System.out.println("Слишком много попыток. Попробуйте позже.");
            userEnterFlowFinished = true;
        } else {
            System.out.println("Осталось попыток: " + attempts);
        }
    }
}
