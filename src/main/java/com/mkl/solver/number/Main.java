package com.mkl.solver.number;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MKL.
 */
public class Main {
    public static void main(String... args) {
        List<List<Operation>> successes = new ArrayList<>();
        monkeyTest(459, successes, new ArrayList<>(), 1, 2, 3, 4, 5, 9);

        System.out.println(successes.size() + " results");
        for (List<Operation> success : successes) {
            System.out.println("Result !");
            for (Operation operation : success) {
                System.out.println(operation.param1 + " " + operation.operation.display + " " + operation.param2 + " = " + operation.result);
            }
        }
    }

    private static void monkeyTest(int result, List<List<Operation>> successes, List<Operation> onGoing, int... params) {
        if (params == null || params.length == 0) {
            return;
        }

        for (int param : params) {
            if (result == param) {
                successes.add(onGoing);
            }
        }

        if (params.length == 1) {
            return;
        }

        for (int i = 0; i < params.length; i++) {
            for (int j = 0; j < params.length; j++) {
                if (i == j) {
                    continue;
                }

                testOperation(result, successes, onGoing, i, j, OperationEnum.PLUS, params);
                testOperation(result, successes, onGoing, i, j, OperationEnum.MINUS, params);
                testOperation(result, successes, onGoing, i, j, OperationEnum.FACTOR, params);
                testOperation(result, successes, onGoing, i, j, OperationEnum.DIVISOR, params);
            }
        }
    }

    private static void testOperation(int result, List<List<Operation>> successes, List<Operation> onGoing, int i, int j, OperationEnum operation, int[] params) {
        int newParam = -1;
        switch (operation) {
            case PLUS:
                if (params[i] <= params[j]) {
                    newParam = params[i] + params[j];
                }
                break;
            case MINUS:
                newParam = params[i] - params[j];
                break;
            case FACTOR:
                if (params[i] <= params[j]) {
                    newParam = params[i] * params[j];
                }
                break;
            case DIVISOR:
                if (params[j] != 0 && params[i] % params[j] == 0) {
                    newParam = params[i] / params[j];
                }
                break;
        }

        if (newParam < 0 || newParam == params[i] || newParam == params[j]) {
            return;
        }
        int[] newParams = extractParams(params, i, j, newParam);
        List<Operation> newOngoing = new ArrayList<>(onGoing);
        newOngoing.add(new Operation(params[i], params[j], operation, newParam));
        monkeyTest(result, successes, newOngoing, newParams);
    }

    private static int[] extractParams(int[] params, int i, int j, int newParam) {
        List<Integer> newParams = new ArrayList<>();
        newParams.add(newParam);
        for (int k = 0; k < params.length; k++) {
            if (k == i || k == j) {
                continue;
            }
            newParams.add(params[k]);
        }
        return newParams.stream().mapToInt(l -> l).toArray();
    }

    private enum OperationEnum {
        PLUS("+"), MINUS("-"), FACTOR("*"), DIVISOR("/");
        private String display;
        OperationEnum(String display) {
            this.display = display;
        }
    }

    private static class Operation {
        private int param1;
        private int param2;
        private OperationEnum operation;
        private int result;

        public Operation(int param1, int param2, OperationEnum operation, int result) {
            this.param1 = param1;
            this.param2 = param2;
            this.operation = operation;
            this.result = result;
        }
    }
}
