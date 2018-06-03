package com.mkl.solver.number;

import com.mkl.solver.number.model.Operation;
import com.mkl.solver.number.model.Operation2Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class Solver2 {
    public static List<List<Operation<Operation2Enum>>> solve(int result, int... params) {
        List<List<Operation<Operation2Enum>>> successes = new ArrayList<>();
        monkeyTest(result, successes, new ArrayList<>(), params);
        return successes;
    }

    private static void monkeyTest(int result, List<List<Operation<Operation2Enum>>> successes, List<Operation<Operation2Enum>> onGoing, int... params) {
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

                for (Operation2Enum operation : Operation2Enum.values()) {
                    testOperation(result, successes, onGoing, i, j, operation, params);
                }
            }
        }
    }

    public static int add(int i, int j) {
        if (i <= j) {
            return i + j;
        }
        return -1;
    }

    public static int subtract(int i, int j) {
        return i - j;
    }

    public static int multiply(int i, int j) {
        if (i <= j) {
            return i * j;
        }
        return -1;
    }

    public static int divide(int i, int j) {
        if (j != 0 && i % j == 0) {
            return i / j;
        }
        return -1;
    }

    private static void testOperation(int result, List<List<Operation<Operation2Enum>>> successes, List<Operation<Operation2Enum>> onGoing, int i, int j, Operation2Enum operation, int[] params) {
        int newParam = operation.getOperation().apply(params[i], params[j]);
        if (newParam < 0 || newParam == params[i] || newParam == params[j]) {
            return;
        }
        int[] newParams = extractParams(params, i, j, newParam);
        List<Operation<Operation2Enum>> newOngoing = new ArrayList<>(onGoing);
        newOngoing.add(new Operation<>(params[i], params[j], operation, newParam));
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
}
