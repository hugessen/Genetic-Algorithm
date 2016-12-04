package termproj;

public class Himmelblau implements ObjectiveFunction {
	
	
	@Override
    public int fitness(String binary)
    {
		//First half of binary number
		double x = Integer.parseInt(binary.substring(0, binary.length() / 2), 2);
		x = (x - 60) / 15.00;
		
		
		//Second half of binary number
		double y = Integer.parseInt(binary.substring(binary.length() / 2), 2);
		y = (y - 60) / 15.00;
		
		int value=(int)(Math.pow(Math.pow(x,2) + y - 11 ,2) + Math.pow(x + Math.pow(y, 2) -7, 2));
		
		return value;
				
		
    }

	@Override
	public void print_value(String binary) {
		
	}
}
