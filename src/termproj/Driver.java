package termproj;

import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        int of_case;
        boolean excel_mode = false;
        Scanner r;
        String s = "";
        int DEJONG_DIM = 4;
        int SYMPOL_DIM = 17;  

        do
        {
            of_case = -1;

            while (of_case < 0 || of_case > 5)
            {
                r = new Scanner(System.in);
                System.out.println("Enter the number of the function you want to run: ");
                System.out.println("1 - Rosenbrock Valley");
                System.out.println("2 - Himmelblau");
                System.out.println("3 - De Jong Sphere");
                System.out.println("4 - 2nd ESF Benchmark");
                System.out.println("5 - Quit");

                s = r.next();
                if (Character.isDigit(s.charAt(0)))
                {
                    of_case = Integer.parseInt(s, 10);
                }
                else
                {
                    System.out.println("Invalid input, try again");
                }
            }

            if (of_case == 0) //Quadratic Function
            {
                QuadraticFunction of = new QuadraticFunction();
                GA ga = new GA(60, of, true, excel_mode);
            }
            else if (of_case == 1) //Rosenbrock Valley
            {
                RosenbrockValley of = new RosenbrockValley(1, 100);
                GA ga = new GA((int) Math.pow(2, 12) - 1, of, false, excel_mode);
            }
            else if (of_case == 2) //Himmelblau Function
            {
                Himmelblau of = new Himmelblau();
                GA ga = new GA((int) Math.pow(2, 16) - 1, of, false, excel_mode);
            }
            else if (of_case == 3) //De Jong Sphere function
            {
                DeJongSphere of = new DeJongSphere(DEJONG_DIM);
                GA ga = new GA((int) Math.pow(2, DEJONG_DIM * 7) - 1, of, false, excel_mode);
            }
            else if (of_case == 4)
            {
                SymmetricPolynomial of = new SymmetricPolynomial(SYMPOL_DIM);
                GA ga = new GA((long) Math.pow(2, SYMPOL_DIM * 2) - 1, of, false, excel_mode);
            }
        }
        while (of_case != 5);
    }
}
