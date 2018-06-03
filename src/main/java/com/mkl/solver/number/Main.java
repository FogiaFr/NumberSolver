package com.mkl.solver.number;

import com.mkl.solver.number.model.Operation;
import com.mkl.solver.number.model.OperationEnum;

import java.util.List;

/**
 * @author MKL.
 */
public class Main {
    public static void main(String... args) {
        List<List<Operation<OperationEnum>>> successes = Solver1.solve(459, 1, 2, 3, 4, 5, 9);

        System.out.println(successes.size() + " results");
        for (List<Operation<OperationEnum>> success : successes) {
            System.out.println("Result !");
            for (Operation<OperationEnum> operation : success) {
                System.out.println(operation.getParam1() + " " + operation.getOperation().getDisplay() + " " + operation.getParam2() + " = " + operation.getResult());
            }
        }
    }
}
