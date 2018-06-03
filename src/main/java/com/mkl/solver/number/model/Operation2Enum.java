package com.mkl.solver.number.model;

import com.mkl.solver.number.Solver2;

import java.util.function.BiFunction;

public enum Operation2Enum {
    PLUS("+", Solver2::add),
    MINUS("-", Solver2::subtract),
    FACTOR("*", Solver2::multiply),
    DIVISOR("/", Solver2::divide);
    private String display;
    private BiFunction<Integer, Integer, Integer> operation;

    Operation2Enum(String display, BiFunction<Integer, Integer, Integer> operation) {
        this.display = display;
        this.operation = operation;
    }

    /** @return the display. */
    public String getDisplay() {
        return display;
    }

    /** @return the operation. */
    public BiFunction<Integer, Integer, Integer> getOperation() {
        return operation;
    }
}