package com.test;

public class Validator {
    private int attempts = 3;

    public int getAttempts() {
        return this.attempts;
    }

    public void decreaseEnterAttempts() {
        attempts--;
    }

    public void resetAttempts() {
        this.attempts = 3;
    }
}
