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
		indiceActual++;
		return numero;
	}
	
	public int nextNumeroPrefijado() {
		return nextInt(9) + 1;
	}

}
