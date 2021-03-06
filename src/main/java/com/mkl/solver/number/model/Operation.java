package com.mkl.solver.number.model;

public class Operation<T> {
    private int param1;
    private int param2;
    private T operation;
    private int result;

    public Operation(int param1, int param2, T operation, int result) {
        this.param1 = param1;
        this.param2 = param2;
        this.operation = operation;
        this.result = result;
    }

    /** @return the param1. */
    public int getParam1() {
        return param1;
    }

    /** @return the param2. */
    public int getParam2() {
        return param2;
    }

    /** @return the operation. */
    public T getOperation() {
        return operation;
    }

    /** @return the result. */
    public int getResult() {
        return result;
    }
}