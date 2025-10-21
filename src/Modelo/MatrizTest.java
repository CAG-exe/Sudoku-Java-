package Modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrizTest {

	@Test
	public void ingresarValorValido() {
		Matriz m = new Matriz();
		m.testMode();
		boolean valor = m.setMatriz(0, 0, 5);
		assertTrue(valor);
	}
	
	@Test
	public void ingresarValorInvalido() {
		Matriz m = new Matriz();
		m.testMode();
		boolean valor = m.setMatriz(0, 0, 1);
		assertFalse(valor);
	}
	
	@Test
	public void ingresarValorInvalidoEnSubcasillas() {
		Matriz m = new Matriz();
		m.testMode();
		boolean valor = m.setMatriz(0, 0, 9);
		assertFalse(valor);
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void ingresarValorNegativo() {
		Matriz m = new Matriz();
		assertTrue(m.setMatriz(0, 0, -1));
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void ingresarValorMuyAlto() {
		Matriz m = new Matriz();
		assertTrue(m.setMatriz(0, 0, 10));
	}
	
	@Test
	public void TestClonar() {
		Matriz m = new Matriz();
		m.testMode();
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
	
	
	@Test
	public void matrizEstaCompleta() {
		Matriz m = new Matriz();
		m.testMode();
		m.setMatriz(0, 0, 5);
		m.setMatriz(0, 7, 9);
		m.setMatriz(8, 0, 9);
		boolean completa= m.matrizCompleta();
		assertTrue(completa);
		
	}
	
	@Test
	public void matrizEstaIncompleta() {
		Matriz m = new Matriz();
		m.testMode();
		boolean completa= m.matrizCompleta();
		assertFalse(completa);
		
	}
	
	
}
