/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproj;

/**
 *
 * @author Jake
 */
public class RosenbrockValley implements ObjectiveFunction
{
    double a;
    double b;
    
    public RosenbrockValley(int a, int b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public int fitness(String binary)
    {
        // x is the first half of the string
        double x = Integer.parseInt(binary.substring(0, binary.length() / 2), 2);
        x = (x - 31) / 8.00;
        
        // y is the second half of the string
        double y = Integer.parseInt(binary.substring(binary.length() / 2), 2);
        y = (y - 31) / 8.00;

        int value = (int)Math.ceil(Math.pow(a - x, 2) + b * Math.pow((y - Math.pow(x, 2)),2));
        
        return value;
    }

    public void print_value(String binary)
    {
        // x is the first half of the string
        double x = Integer.parseInt(binary.substring(0, binary.length() / 2), 2);
        x = (x - 31) / 8.00;
        
        // y is the second half of the string
        double y = Integer.parseInt(binary.substring((binary.length() / 2)), 2);
        y = (y - 31) / 8.00;

        int value = (int)Math.ceil(Math.pow(a - x, 2) + b * Math.pow((y - Math.pow(x, 2)),2));
        
        System.out.printf("%s: x=%.2f, y=%.2f, output=%d\n", binary, x, y, value);
    }

    
}
