package termproj;

// TODO:
// implement other objective functions
// Test effectiveness of different combinations of initial settings

public class Driver
{

    public static boolean excel_mode = true;

	public static void main(String[] args)
    {
        int of_case = 2;
        int num_gen = 50;
        int num_iterations;
        if(excel_mode)
        	num_iterations = 10000;
        else
        	num_iterations = 1;
        
        for(int i = 0; i < num_iterations; i++)
	        if (of_case == 0) //Quadratic Function
	        {
	            QuadraticFunction of = new QuadraticFunction();
	            GA ga = new GA(num_gen, 60, 0, of, true);
	        }
	        else if (of_case == 1) //Rosenbrock Valley
	        {
	            RosenbrockValley of = new RosenbrockValley(1, 100);
	            GA ga = new GA(num_gen, (int)Math.pow(2, 12)-1, 0, of, false);
	        }
	        else if (of_case == 2) //Himmelblau Function
	        {
	        	Himmelblau of = new Himmelblau();
	        	GA ga = new GA(num_gen, (int)Math.pow(2, 16)-1, 0, of, false);
	        }
    }
}
