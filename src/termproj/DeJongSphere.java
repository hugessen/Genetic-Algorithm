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
public class DeJongSphere implements ObjectiveFunction
{
    int n;

    public DeJongSphere(int n)
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
            x[i] = (x[i] - 63) / 12;
        }

        // calculate the sum of squares
        for (int i = 0; i < n; i++)
        {
            value += Math.pow(x[i], 2);
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
            x[i] = (x[i] - 63) / 12;
            System.out.printf("x=%s=%.2f, ", binary.substring((binary.length() / n) * i, (binary.length() / n) * (i + 1)), x[i]);
        }

        // calculate the sum of squares
        for (int i = 0; i < n; i++)
        {
            value += Math.pow(x[i], 2);
        }

        System.out.printf("output=%.2f\n", value);
    }
}
