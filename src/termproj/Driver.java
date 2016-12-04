package termproj;

public class Driver
{

    public static void main(String[] args)
    {
        int of_case;
        int num_gen = 50;
        boolean excel_mode = false;
	Scanner r;
        String s = "";

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
		
	        if (of_case == 0) //Quadratic Function
	        {
	            QuadraticFunction of = new QuadraticFunction();
	            GA ga = new GA(num_gen, 60, 0, of, true,excel_mode);
	        }
	        else if (of_case == 1) //Rosenbrock Valley
	        {
	            RosenbrockValley of = new RosenbrockValley(1, 100);
	            GA ga = new GA(num_gen, (int)Math.pow(2, 12)-1, 0, of, false,excel_mode);
	        }
	        else if (of_case == 2) //Himmelblau Function
	        {
	        	Himmelblau of = new Himmelblau();
	        	GA ga = new GA(num_gen, (int)Math.pow(2, 16)-1, 0, of, false,excel_mode);
	        }
		else if (of_case == 3) //De Jong Sphere function
		{
		    int n = 4;
		    DeJongSphere of = new DeJongSphere(n);
		    GA ga = new GA(num_gen, (int) Math.pow(2, n * 7) - 1, 0, of, false);
		}
	} while (of_case != 4);
    }
}
