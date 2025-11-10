package Modelo;

public class GeneradorPrefijado implements Generador {

	private int[] numerosPrefijados;
	private int indiceActual;

	public GeneradorPrefijado(int[] numerosPrefijados) {
		this.numerosPrefijados = numerosPrefijados;
		this.indiceActual = 0;
	}

	@Override
	public int nextInt(int rango) {
		if (indiceActual >= numerosPrefijados.length) {
			throw new IndexOutOfBoundsException("No hay mas numeros prefijados disponibles");
		}
		int numero = numerosPrefijados[indiceActual];
		System.out.println(numero);
		indiceActual++;
		return numero-1;
	}
	
	@Override
	public int nextPosicion(int rango) {
		return indiceActual - 1;
	}

}
