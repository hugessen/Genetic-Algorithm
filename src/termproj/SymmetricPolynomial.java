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
public class SymmetricPolynomial implements ObjectiveFunction
{
    int n;
    
    public SymmetricPolynomial(int n)
    {
        this.n = n;
    }
    
    @Override
    public double fitness(String binary)
    {
        double[] x = new double[n];
        double value = 0.00;

        // divide the string into individual x values
        for (int i = 0; i < n; i++)
        {
            x[i] = Integer.parseInt(binary.substring((binary.length() / n) * i, (binary.length() / n) * (i + 1)), 2);
            x[i] = (x[i] - 1.5) / 1.5;
        }

        // calculate values
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < i; j++)
            {
                value += x[i]*x[j];
            }
        }

        return value;
    }
    
    @Override
    public void print_value(String binary)
    {
        double[] x = new double[n];
        double value = 0.00;
        
        System.out.printf("%s: ", binary);

        // divide the string into individual x values
        for (int i = 0; i < n; i++)
        {
            x[i] = Integer.parseInt(binary.substring((binary.length() / n) * i, (binary.length() / n) * (i + 1)), 2);
            x[i] = (x[i] - 1.5) / 1.5;
            System.out.printf("x%d=%s=%.2f, ", (i+1), binary.substring((binary.length() / n) * i, (binary.length() / n) * (i + 1)), x[i]);
        }

        // calculate values
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < i; j++)
            {
                value += x[i]*x[j];
            }
        }
        
        System.out.printf("output=%.2f\n", value);
    }
}
