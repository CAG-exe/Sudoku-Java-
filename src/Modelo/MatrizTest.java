package Modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MatrizTest {
	
	private Matriz matriz;

	@Before
	public void GenerarMatriz() {
		this.matriz = new Matriz(new GeneradorPrefijado(new int[] {}));
	}

	@Test
	public void ingresarValorValido() {
		testMode(matriz);
		boolean valor = matriz.esSeguro(0, 0, 5);
		assertTrue(valor);
	}
	
	@Test
	public void ingresarValorInvalido() {
		testMode(matriz);
		boolean valor = matriz.esSeguro(0, 0, 1);
		assertFalse(valor);
	}
	
	@Test
	public void ingresarValorInvalidoEnSubcasillas() {
		testMode(matriz);
		boolean valor = matriz.esSeguro(0, 0, 9);
		assertFalse(valor);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void ingresarValorNegativo() {
		assertTrue(matriz.esSeguro(0, 0, -1));
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void ingresarValorMuyAlto() {
		assertTrue(matriz.esSeguro(0, 0, 10));
	}
	
	@Test
	public void ingresarValorEnCasillaDeIgualValor() {
		testMode(matriz);
		boolean valor = matriz.esSeguro(0, 1, 6);
		assertFalse(valor);
	} 
	
	
	
	
	@Test
	public void setMatrizTest() {
		assertFalse(matriz.getValorEnMatriz(1, 4) == 6);
		matriz.setMatriz(1, 4, 6);
		
		assertTrue(matriz.getValorEnMatriz(1, 4) == 6);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void setMatrizConPosicion1NegativoTest() {
		matriz.setMatriz(-1, 4, 6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setMatrizConPosicion2NegativoTest() {
		matriz.setMatriz(1, -4, 6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setMatrizConPosicion1PasadaTest() {
		matriz.setMatriz(1123, 1, 6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setMatrizConPosicion2PasadaTest() {
		matriz.setMatriz(1, 120, 6);
	}
	
	@Test
	public void TestClonar() {
		testMode(matriz);
		Matriz c = new Matriz(new GeneradorAleatorio());
		boolean igualdad = true;
		c.setMatrizClonada(matriz.clonar());
		for(int fila=0;fila<9;fila++) {
			for(int col=0;col<9;col++) {
				igualdad &= (c.getValorEnMatriz(fila, col) == matriz.getValorEnMatriz(fila, col));
			}
		}
		assertTrue(igualdad);
	}
	
	@Test
	public void marcarCasillas() {
		Matriz mat = new Matriz(new GeneradorAleatorio());
		int valorEsperado = 20;
		mat.marcarCasillasConNumerosValidos(20);
		int cont=0;
		for(int fila=0;fila<9;fila++) {
			for(int col=0;col<9;col++) {
				if(mat.getValorEnMatriz(fila, col) > 0)
					cont++;
			}
		}
		assertEquals(valorEsperado,cont);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void marcarExcesoDeCasillas() {
		matriz.marcarCasillasConNumerosValidos(50);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void marcarPoquisimasDeCasillas() {
		matriz.marcarCasillasConNumerosValidos(10);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void getValorEnMatrizFueraDeRangoPosicion1() {
		matriz.getValorEnMatriz(1283, 2);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void getValorEnMatrizFueraDeRangoPosicion2() {
		matriz.getValorEnMatriz(1, 212);
	}
	
	
	@Test (expected= IllegalArgumentException.class)
	public void getValorEnMatrizPosicion1Negativo() {
		matriz.getValorEnMatriz(-2, 2);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void getValorEnMatrizPosicion2Negativo() {
		matriz.getValorEnMatriz(2, -2);
	}
	
	@Test
	public void matrizEstaCompleta() {
		testMode(matriz);
		matriz.setMatriz(0, 0, 5);
		matriz.setMatriz(0, 7, 9);
		matriz.setMatriz(8, 0, 9);
		boolean completa= matriz.matrizCompleta();
		assertTrue(completa);
		
	}
	
	@Test
	public void matrizEstaIncompleta() {
		testMode(matriz);
		boolean completa= matriz.matrizCompleta();
		assertFalse(completa);
		
	}
	
	@Test
	public void casillaMarcadaTest() {
		testMode(matriz);
		assertTrue(matriz.casillaMarcada(0, 2));
		assertFalse(matriz.casillaMarcada(0, 0));
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void casillaMarcadaPosicion1FueraDeRangoTest() {
		matriz.casillaMarcada(1283, 2);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void casillaMarcadaPosicion2FueraDeRangoTest() {
		matriz.casillaMarcada(1, 212);
	}
	
	
	@Test (expected= IllegalArgumentException.class)
	public void casillaMarcadaPosicion1NegativaTest() {
		matriz.casillaMarcada(-2, 2);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void casillaMarcadaPosicion2NegativaTest() {
		matriz.casillaMarcada(2, -2);
	}
	
	private void testMode(Matriz m) {
		int[][] Mtest =  new int[][]{{0,6,8,4,2,7,1,0,3}
		   							,{3,4,2,9,1,5,8,6,7}
		   							,{1,9,7,6,8,3,5,2,4}
		   							,{6,8,5,1,3,2,7,4,9}
		   							,{7,3,4,5,9,8,6,1,2}
		   							,{2,1,9,7,6,4,3,5,8}
		   							,{4,7,3,2,5,6,9,8,1}
		   							,{8,5,1,3,4,9,2,7,6}
		   							,{0,1,6,8,1,1,4,3,5}};
		 m.setMatrizClonada(Mtest);
	}
	
	public void getCantDeValoresPrefijadosTest() {
		int esperado = 25;
		matriz.marcarCasillasConNumerosValidos(25);
		assertEquals(esperado, matriz.getCantDeValoresPrefijados());
	}
	
}
