package com.example.aes;

import java.io.Serializable;

public class Progress implements Serializable {
    public enum Options {
        ENCRYPT,
        DECRYPT,
        NEITHER
    }

    private Options choice;
    private String input;
    private String key;

    public Progress(Options choice) {
        this.choice = choice;
    }

    public Options getChoice() {
        return choice;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
