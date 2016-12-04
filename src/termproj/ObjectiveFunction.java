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
public interface ObjectiveFunction
{
    public double fitness(String binary);
    public void print_value(String binary);
}
