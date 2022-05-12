package com.spring.achareh.customException;

public class ScoreOutOfRange extends RuntimeException{
    public ScoreOutOfRange() {
        super("Score out of range");
    }
}
