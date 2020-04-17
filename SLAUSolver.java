package mathcomp.oletsky.mathhelper;

import org.apache.commons.math3.linear.*;

/**
 * Solving systems of linear equations by means of SVD or LU decompositions
 * @author Oleksiy Oletsky
 */

public class SLAUSolver {

    public static enum SolverType {LU, SVD}

    /**
     * Solving system of linear equations
     * @param m matrix of coefficients
     * @param v right part
     * @param solverType SVD or LU
     * @return vector of solution
     */
    public static double[] solveSLAR(double[][] m, double[] v, SolverType solverType) {
        RealMatrix coefs = new Array2DRowRealMatrix(m);
        DecompositionSolver solver;

        switch (solverType) {
            case LU: solver = new LUDecomposition(coefs).getSolver(); break;
            case SVD: solver = new SingularValueDecomposition(coefs).getSolver(); break;
            default: throw new RuntimeException("Incorrect type for solver of SLAU");
        }

        RealVector right = new ArrayRealVector(v);
        RealVector solution = solver.solve(right);
        double[] res = solution.toArray();
        return res;

    }


}
