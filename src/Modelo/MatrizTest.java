package Modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrizTest {

	@Test
	public void ingresarValorValido() {
		Matriz m = new Matriz();
		testMode(m);
		boolean valor = m.esSeguro(0, 0, 5);
		assertTrue(valor);
	}
	
	@Test
	public void ingresarValorInvalido() {
		Matriz m = new Matriz();
		testMode(m);
		boolean valor = m.esSeguro(0, 0, 1);
		assertFalse(valor);
	}
	
	@Test
	public void ingresarValorInvalidoEnSubcasillas() {
		Matriz m = new Matriz();
		testMode(m);
		boolean valor = m.esSeguro(0, 0, 9);
		assertFalse(valor);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void ingresarValorNegativo() {
		Matriz m = new Matriz();
		assertTrue(m.esSeguro(0, 0, -1));
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void ingresarValorMuyAlto() {
		Matriz m = new Matriz();
		assertTrue(m.esSeguro(0, 0, 10));
	}
	
	@Test
	public void ingresarValorEnCasillaDeIgualValor() {
		Matriz m = new Matriz();
		testMode(m);
		boolean valor = m.esSeguro(0, 1, 6);
		assertFalse(valor);
	} 
	
	
	
	
	@Test
	public void setMatrizTest() {
		Matriz m = new Matriz();
		assertFalse(m.getValorEnMatriz(1, 4) == 6);
		m.setMatriz(1, 4, 6);
		
		assertTrue(m.getValorEnMatriz(1, 4) == 6);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void setMatrizConPosicion1NegativoTest() {
		Matriz m = new Matriz();
		m.setMatriz(-1, 4, 6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setMatrizConPosicion2NegativoTest() {
		Matriz m = new Matriz();
		m.setMatriz(1, -4, 6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setMatrizConPosicion1PasadaTest() {
		Matriz m = new Matriz();
		m.setMatriz(1123, 1, 6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setMatrizConPosicion2PasadaTest() {
		Matriz m = new Matriz();
		m.setMatriz(1, 120, 6);
	}
	
	@Test
	public void TestClonar() {
		Matriz m = new Matriz();
		testMode(m);
		Matriz c = new Matriz();
		boolean igualdad = true;
		c.setMatrizClonada(m.clonar());
		for(int fila=0;fila<9;fila++) {
			for(int col=0;col<9;col++) {
				igualdad &= (c.getValorEnMatriz(fila, col) == m.getValorEnMatriz(fila, col));
			}
		}
		assertTrue(igualdad);
	}
	
	@Test
	public void marcarCasillas() {
		Matriz m = new Matriz();
		int valorEsperado = 20;
		m.marcarCasillasConNumerosValidos(20);
		int cont=0;
		for(int fila=0;fila<9;fila++) {
			for(int col=0;col<9;col++) {
				if(m.getValorEnMatriz(fila, col) > 0)
					cont++;
			}
		}
		assertEquals(valorEsperado,cont);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void marcarExcesoDeCasillas() {
		Matriz m = new Matriz();
		m.marcarCasillasConNumerosValidos(50);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void marcarPoquisimasDeCasillas() {
		Matriz m = new Matriz();
		m.marcarCasillasConNumerosValidos(10);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void getValorEnMatrizFueraDeRangoPosicion1() {
		Matriz m = new Matriz();
		m.getValorEnMatriz(1283, 2);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void getValorEnMatrizFueraDeRangoPosicion2() {
		Matriz m = new Matriz();
		m.getValorEnMatriz(1, 212);
	}
	
	
	@Test (expected= IllegalArgumentException.class)
	public void getValorEnMatrizPosicion1Negativo() {
		Matriz m = new Matriz();
		m.getValorEnMatriz(-2, 2);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void getValorEnMatrizPosicion2Negativo() {
		Matriz m = new Matriz();
		m.getValorEnMatriz(2, -2);
	}
	
	@Test
	public void matrizEstaCompleta() {
		Matriz m = new Matriz();
		testMode(m);
		m.setMatriz(0, 0, 5);
		m.setMatriz(0, 7, 9);
		m.setMatriz(8, 0, 9);
		boolean completa= m.matrizCompleta();
		assertTrue(completa);
		
	}
	
	@Test
	public void matrizEstaIncompleta() {
		Matriz m = new Matriz();
		testMode(m);
		boolean completa= m.matrizCompleta();
		assertFalse(completa);
		
	}
	
	@Test
	public void casillaMarcadaTest() {
		Matriz m = new Matriz();
		testMode(m);
		assertTrue(m.casillaMarcada(0, 2));
		assertFalse(m.casillaMarcada(0, 0));
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void casillaMarcadaPosicion1FueraDeRangoTest() {
		Matriz m = new Matriz();
		m.casillaMarcada(1283, 2);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void casillaMarcadaPosicion2FueraDeRangoTest() {
		Matriz m = new Matriz();
		m.casillaMarcada(1, 212);
	}
	
	
	@Test (expected= IllegalArgumentException.class)
	public void casillaMarcadaPosicion1NegativaTest() {
		Matriz m = new Matriz();
		m.casillaMarcada(-2, 2);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void casillaMarcadaPosicion2NegativaTest() {
		Matriz m = new Matriz();
		m.casillaMarcada(2, -2);
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
		Matriz m = new Matriz();
		int esperado = 25;
		m.marcarCasillasConNumerosValidos(25);
		assertEquals(esperado, m.getCantDeValoresPrefijados());
	}
	
}
