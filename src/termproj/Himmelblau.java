package termproj;

public class Himmelblau implements ObjectiveFunction {
	
	
	@Override
    public int fitness(String binary)
    {
		//First half of binary number
		double x = Integer.parseInt(binary.substring(0, binary.length() / 2), 2);
		x = (x - 127) / 32.00;
		
		
		//Second half of binary number
		double y = Integer.parseInt(binary.substring(binary.length() / 2), 2);
		y = (y - 127) / 32.00;
		
		int value=(int)(Math.pow(Math.pow(x,2) + y - 11 ,2) + Math.pow(x + Math.pow(y, 2) -7, 2));
		
		return value;
				
		
    }

	@Override
	public void print_value(String binary) {
		// x is the first half of the string
        double x = Integer.parseInt(binary.substring(0, binary.length() / 2), 2);
        x = (x - 127) / 32.00;
        
        // y is the second half of the string
        double y = Integer.parseInt(binary.substring((binary.length() / 2)), 2);
        y = (y - 127) / 32.00;

        double value = (Math.pow(Math.pow(x,2) + y - 11 ,2) + Math.pow(x + Math.pow(y, 2) -7, 2));
        
        System.out.printf("%s: x=%.2f, y=%.2f, output=%.2f\n", binary, x, y, value);
	}
}
