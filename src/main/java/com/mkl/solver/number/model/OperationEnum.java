package com.mkl.solver.number.model;

public enum OperationEnum {
    PLUS("+"),
    MINUS("-"),
    FACTOR("*"),
    DIVISOR("/");
    private String display;

    OperationEnum(String display) {
        this.display = display;
    }

    /** @return the display. */
    public String getDisplay() {
        return display;
    }
}