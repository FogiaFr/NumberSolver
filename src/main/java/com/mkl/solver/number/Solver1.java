package com.mkl.solver.number;

import com.mkl.solver.number.model.Operation;
import com.mkl.solver.number.model.OperationEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class Solver1 {
    public static List<List<Operation>> solve(int result, int... params) {
        List<List<Operation>> successes = new ArrayList<>();
        monkeyTest(result, successes, new ArrayList<>(), params);
        return successes;
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
}
