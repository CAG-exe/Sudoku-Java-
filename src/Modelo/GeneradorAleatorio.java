package Modelo;

public class GeneradorAleatorio implements Generador {
	
	public GeneradorAleatorio() {
	}
	
	@Override
	public int nextInt(int rango) {
		return (int) (Math.random() * rango);
	}
	
	public int nextNumeroPrefijado() {
		return nextInt(9) + 1;
	}

}
