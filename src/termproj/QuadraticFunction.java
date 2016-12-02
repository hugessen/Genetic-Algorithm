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
public class QuadraticFunction implements ObjectiveFunction
{
    public QuadraticFunction()
    {
        
    }

    @Override
    public int fitness(String binary)
    {
        int value = (Integer.parseInt(binary, 2) - 30) * -1;
        int result = value * value;
        return result;
    }
    
    public void print_value(String binary)
    {
        int value = (Integer.parseInt(binary, 2) - 30);
        int result = value * value;
        System.out.printf("%s: x=%d, output=%d\n", binary, value, result);
    }
}
