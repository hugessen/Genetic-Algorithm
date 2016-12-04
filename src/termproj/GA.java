package termproj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA
{
	boolean excel_mode;
    int generation_counter; // the number of generations remaining
    int max_value; // the maximum possible value for an individual
    int min_value; // the minimum possible value for an individual
    int bit_size; // the number of bits in an individual
    int pop_size; // the number of individuals in a population
    List<String> population; // the population, with each individual represented by a string
    Random random = new Random(); // used to generate random numbers
    ObjectiveFunction objective_function; // a function to determine an individuals fitness
    boolean maximize; // true if we are maximizing the objective function, false if we are minimizing it

    /**
     * Constructor for GA class
     *
     * @param generation_counter the total number of generations
     * @param max_value the maximum possible value for an individual
     * @param min_value the minimum possible value for an individual
     * @param of a function to determine an individuals fitness
     * @param maximize true if we are maximizing the objective function, false if we are minimizing it
     */
    public GA(int generation_counter, int max_value, int min_value, ObjectiveFunction of, boolean maximize,boolean excel_mode)
    {
        this.generation_counter = generation_counter;
        this.max_value = max_value;
        this.min_value = min_value;
        population = new ArrayList<String>();
        this.objective_function = of;
        this.maximize = maximize;
        this.excel_mode = excel_mode;
        do_next_gen();
    }

    /**
     * the main function, cycles through the reproduction, crossover, and mutation stages
     */
    private void do_next_gen()
    {
        init_population(4);
        if(!excel_mode){
        	System.out.println("intial population");
        	print_population();
        }

        while (generation_counter > 0)
        {
        	if(!excel_mode)
        		System.out.println("Reproduction " + (generation_counter));
            reproduction();
            if(!excel_mode){
            	print_population();
            	System.out.println("Crossover " + (generation_counter));
            }
            crossover(1.0);
            mutation();
            generation_counter--;
            if(!excel_mode){
	            System.out.println("Population is now: ");
	            print_population();
            }

            // print the maximum value in the population if we are maximizing the function
            if (maximize)
            {
                double of_max = 0;

                for (String s : population)
                {
                    double curr_of = objective_function.fitness(s);
                    if (curr_of > of_max)
                    {
                        of_max = curr_of;
                    }
                }
                if(!excel_mode)
                	System.out.print("Max: ");
                if(!excel_mode || generation_counter == 0)
                	System.out.printf("%.2f \n",of_max);
            }
            // print the minimum value in the population if we are minimizing the function
            else
            {
                double of_min = Integer.MAX_VALUE;

                for (String s : population)
                {
                    double curr_of = objective_function.fitness(s);
                    if (curr_of < of_min)
                    {
                        of_min = curr_of;
                    }
                }
                if(!excel_mode) 
                	System.out.print("Min: ");
                if(!excel_mode || generation_counter == 0)
                	System.out.printf("%.2f \n",of_min);
            }
        }
    }

    /**
     * Creates the initial population
     */
    private void init_population(int m)
    {
        int n = 0;
        int r = 0;
        
        bit_size = get_bit_size(max_value);
        if(!excel_mode)
        	System.out.println("bit size: "+bit_size);
        pop_size = (bit_size / 2) * 2;

        if (pop_size < 6)
        {
            pop_size = 6;
        }

        while (population.size() < pop_size)
        {
            String current = "";

            // method 2: randomly assigns each digit individually
            if (m == 2)
            {
                while (current.length() < bit_size)
                {
                    if (random.nextInt(2) == 1)
                    {
                        current += "1";
                    }
                    else
                    {
                        current += "0";
                    }
                }
            }
            // method 4: first sample has bit_size 1s, second sample has bit_size - 1 1s, etc.
            else if (m == 4)
            {
                // create a string all all 0s
                while (current.length() < bit_size)
                {
                    current += "0";
                }

                int i = 0;
                // randomly assign between 0 and bit_size 1s into the string
                while (i < n % bit_size)
                {
                    r = random.nextInt(bit_size);
                    if (current.charAt(r) == '0')
                    {
                        current = current.substring(0, r) + "1" + current.substring(r + 1);
                        i++;
                    }
                }                
                n++;
            }
            // method 1: completely random number
            else
            {       
                current = Integer.toBinaryString((int) (Math.random() * (max_value - min_value)) + min_value); 
                current = pad_zero(current);
            }
            
            // insert the new individual into the population if it is not already in the population, and it is within the acceptable range
            if (!population.contains(current) && Integer.parseInt(current, 2) <= max_value && Integer.parseInt(current, 2) >= min_value)
            {
                population.add(current);
            }
        }
    }

    /**
     * Adds padded 0s as necessary
     */
    private String pad_zero(String value)
    {
        while (value.length() < bit_size)
        {
            value = '0' + value;
        }

        return value;
    }

    /**
     * Gets the number of bits in the binary representation of a given number
     */
    private int get_bit_size(int value)
    {
        return Integer.toBinaryString(value).length();
    }

    /**
     * creates the next generation using a biased roulette based on objective function values
     */
    private void reproduction()
    {
        List<String> old_list = new ArrayList<String>();

        for (String s : population)
        {
            if (!old_list.contains(s))
            {
                old_list.add(s);
            }
        }

        population.clear();

        // create new population using biased roulette to maximize objective function
        if (maximize)
        {
            int sum = 0;

            // get the total sum of objective function values
            for (int i = 0; i < old_list.size(); i++)
            {
                sum += objective_function.fitness(old_list.get(i));
            }

            for (int i = 0; i < pop_size; i++)
            {
                // get the random number
                int rand = random.nextInt(sum - 1);
                int index = -1;

                // find the value corresponding to the random number
                while (rand >= 0)
                {
                    index++;
                    rand -= objective_function.fitness(old_list.get(index));
                }

                // insert the winner of the biased roulette into the new population
                population.add(old_list.get(index));
            }
        }
        // create new population using biased roulette to minimize objective function
        else
        {
            double of_total = 0;
            double p[] = new double[old_list.size()];
            double fit[] = new double[old_list.size()];
            double sum = 0;

            // get the total sum of objective function values
            for (int i = 0; i < old_list.size(); i++)
            {
                fit[i] = objective_function.fitness(old_list.get(i));
                if (fit[i] > 0)
                {
                    p[i] = 1.000 / fit[i];
                }
                of_total += p[i];
            }

            // get the total sum of objective function values
            for (int i = 0; i < old_list.size(); i++)
            {
                if (fit[i] == 0)
                {
                    p[i] = 1;
                }
                else
                {
                    p[i] = p[i] / of_total;
                }
                sum += p[i];
            }

            for (int i = 0; i < pop_size; i++)
            {
                // get the random number
                double rand = Math.random() * sum;
                int index = -1;

                // find the value corresponding to the random number
                while (rand >= 0)
                {
                    index++;
                    rand -= p[index];
                }

                // insert the winner of the biased roulette into the new population
                population.add(old_list.get(index));
            }
        }
    }

    /**
     * Crossover pairs of bit strings at a random crossover point
     */
    private void crossover(Double probability)
    {
        String new1;
        String new2;

        int i = 0;

        while (i < population.size())
        {
            if (Math.random() < probability)
            {
                int count = 0;          
                do
                {
                    // get the crossover index
                    int index = random.nextInt(bit_size - 1) + 1;
                    //System.out.println("Crossing over " + population.get(i) + " and " + population.get(i + 1) + " at index " + index);

                    // crossover the pair of strings
                    new1 = population.get(i).substring(0, index) + population.get(i + 1).substring(index);
                    new2 = population.get(i + 1).substring(0, index) + population.get(i).substring(index);
                    count++; 
                }
                while ((Integer.parseInt(new1, 2) < min_value || Integer.parseInt(new1, 2) > max_value
                        || Integer.parseInt(new2, 2) < min_value || Integer.parseInt(new2, 2) > max_value)
                        && count < bit_size);

                // insert the new values into the population
                if (count < bit_size)
                {
                    population.set(i, new1);
                    population.set(i + 1, new2);
                }

                i += 2;
            }
        }
    }

    /**
     * for every digit in the population, it is modified with a 0.1% chance
     */
    private void mutation()
    {
        for (int i = 0; i < population.size(); i++)
        {
            for (int j = 0; j < bit_size; j++)
            {
                if (random.nextInt(1000) == 1)
                {
                	if(!excel_mode)
                		System.out.println("Mutation!");
                    char new_value = toggle(population.get(i).charAt(j));
                    String new_str = population.get(i).substring(0, j) + new_value + population.get(i).substring(j + 1);

                    // make sure the newly mutated sample is still an acceptable value before inserting it into the population
                    if (Integer.parseInt(new_str, 2) <= max_value && Integer.parseInt(new_str, 2) >= min_value)
                    {
                        population.set(i, new_str);
                    }
                }
            }
        }
    }

    /**
     * 'Toggles' a value from 1 to 0 or 0 to 1
     *
     * @param num a 1 or 0 to be toggled
     * @return 0 or 1
     */
    private char toggle(char num)
    {
        if (num == '1')
        {
            return '0';
        }
        else
        {
            return '1';
        }
    }

    /**
     * Prints the population's integer values and objective function values
     */
    private void print_population()
    {
        for (String s : population)
        {
            objective_function.print_value(s);
        }
    	System.out.println("");
    }
}
