package es.cjrg.math;

import java.util.Random;

public class EjRandom {

	public static void main(String[] args) {
		
		int randomNum;
		
		//OPCION 1
		Random aleatorio = new Random();
	    randomNum = aleatorio.nextInt(5);
	    System.out.println(randomNum);

	    //OPCION 2
	    randomNum =  (int)(Math.random()*5); 
	    System.out.println(randomNum);

	}

}
