package termproj;

// TODO:
// implement other objective functions
// Test effectiveness of different combinations of initial settings

public class Driver
{

    public static void main(String[] args)
    {
        int of_case = 1;
        int num_gen = 50;
        boolean excel_mode = false;
        int num_iterations;
        if(excel_mode)
        	num_iterations = 10000;
        else
        	num_iterations = 1;
        
        for(int i = 0; i < num_iterations; i++)
	        if (of_case == 0)
	        {
	            QuadraticFunction of = new QuadraticFunction();
	            GA ga = new GA(num_gen, 60, 0, of, true,excel_mode);
	        }
	        else if (of_case == 1)
	        {
	            RosenbrockValley of = new RosenbrockValley(1, 100);
	            GA ga = new GA(num_gen, (int)Math.pow(2, 12)-1, 0, of, false,excel_mode);
	        }
    }
}
