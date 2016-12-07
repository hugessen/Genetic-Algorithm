package termproj;

import java.util.Scanner;

public class Driver
{
	public static boolean excel_mode = true;
	static int GEN_COUNT = 20;
	static double[] gen_best = new double[GEN_COUNT];
    public static void main(String[] args)
    {
        int of_case;
        int num_iterations;
        if(excel_mode)
        	num_iterations = 10000;
        else
        	num_iterations = 1;
        Scanner r;
        String s = "";
        int DEJONG_DIM = 4;

        do
        {
            of_case = -1;

            while (of_case < 0 || of_case > 4)
            {
                r = new Scanner(System.in);
                System.out.println("Enter the number of the function you want to run: ");
                System.out.println("1 - Rosenbrock Valley");
                System.out.println("2 - Himmelblau");
                System.out.println("3 - De Jong Sphere");
                System.out.println("4 - Quit");

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

            for(int i = 0; i<num_iterations; i++){
	            if (of_case == 0) //Quadratic Function
	            {
	                QuadraticFunction of = new QuadraticFunction();
	                GA ga = new GA(60, of, true);
	            }
	            else if (of_case == 1) //Rosenbrock Valley
	            {
	                RosenbrockValley of = new RosenbrockValley(1, 100);
	                GA ga = new GA((int) Math.pow(2, 12) - 1, of, false);
	            }
	            else if (of_case == 2) //Himmelblau Function
	            {
	                Himmelblau of = new Himmelblau();
	                GA ga = new GA((int) Math.pow(2, 16) - 1, of, false);
	            }
	            else if (of_case == 3) //De Jong Sphere function
	            {
	                DeJongSphere of = new DeJongSphere(DEJONG_DIM);
	                GA ga = new GA((int) Math.pow(2, DEJONG_DIM * 7) - 1, of, false);
	            }
            }
            if(excel_mode)
	            for(int i = 0; i<gen_best.length; i++){
	            	gen_best[i] /= 10000;
	            	System.out.printf("%.4f \n",gen_best[i]);
	            }
        }
        while (of_case != 4);
    }
}