package Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matriz {
	private int[][] sudoku;
	private int[][] sudokuConValoresPrefijados;
	private int cantDeValoresPrefijados;
	private int maximoValorDePrefijados = 40;
	private int minimoValorDePrefijados = 0;
	
	public Matriz() {
		sudoku= new int[9][9];
		rellenarDeCeros();
	}

	private void rellenarDeCeros() {
		for(int fila=0;fila<sudoku.length;fila++) {
			for(int col=0;col<sudoku.length;col++) {
				sudoku[fila][col]=0;
			}
		}
	}
	
	public int getValorEnMatriz(int x,int y) {
		if(x < 0 || y < 0 || x > sudoku.length || y > sudoku.length) {
			throw new IllegalArgumentException("Los parametros X / Y son invalidos");
		}
		return sudoku[x][y];
	}
	
	public boolean esSeguro(int x, int y, int valor) {
		if(valor<1 || valor >9)
			throw new IllegalArgumentException("el valor tiene que estar entre 1 y 9");
		if(sudoku[x][y] == valor || posicionValidaParaValor(x,y,valor)) {
			return false;
		}
		return true;
	}

	public void setMatriz(int x,int y, int valor) {
		if(x < 0 || y < 0 || x > sudoku.length || y > sudoku.length) {
			throw new IllegalArgumentException("Los parametros X / Y son invalidos");
		}
		sudoku[x][y] = valor;
		return;
	}

	public boolean posicionValidaParaValor(int x, int y, int valor) {
		return verficacionPorfila(x,y,valor) || verificacionPorColumna(x,y,valor) || verificacionPorCuadrante(x,y,valor);
	}

	private boolean verficacionPorfila(int x, int y, int valor) {
		int[] fila = sudoku[x];
		Set<Integer> filaSet =  new HashSet<>(deArrayASet(fila));
		
		return filaSet.contains(valor);
	}

	private boolean verificacionPorColumna(int x, int y, int valor) {
		int[] columna = IntStream.range(0, sudoku.length).map(i -> sudoku[i][y]).toArray();
		Set<Integer> columnaSet =  new HashSet<>(deArrayASet(columna));
		
		return columnaSet.contains(valor);
	}
	
	private boolean verificacionPorCuadrante(int x, int y, int valor) {
		   int filaInicio = (x / 3) * 3;
		    int colInicio = (y / 3) * 3;

		    for (int i = filaInicio; i < filaInicio + 3; i++) {
		        for (int j = colInicio; j < colInicio + 3; j++) { 
		            if (sudoku[i][j] == valor) 
		                return true;
		        }
		    }
		    return false;
	}
	
	private Set<Integer> deArrayASet(int[] array) {
		return Arrays.stream(array).boxed().collect(Collectors.toSet());
	}
	
	public boolean matrizCompleta(){
		for(int fila=0;fila<sudoku.length;fila++) {
			for(int col=0;col<sudoku.length;col++) {
				if(sudoku[fila][col]== 0)
					return false;
			}
		}
		return true;
	}
	
	public int[][] clonar(){
		int[][] clonar = new int[9][9];
		for(int fila=0;fila<sudoku.length;fila++) {
			for(int col=0;col<sudoku.length;col++) {
				clonar[fila][col] = sudoku[fila][col];
			}
		}
		return clonar;
	}
	
	public void marcarCasillasConNumerosValidos(int num) {
		setCantDeValoresPrefijados(num);
		if(num<minimoValorDePrefijados || num>maximoValorDePrefijados) {
			throw new IllegalArgumentException("numero de casillas no valido, tiene que estar entre 15 o 40");
		}
		for(int i=0;i<num;i++){
			buscarCasillaYmarcar();
		}
		sudokuConValoresPrefijados = clonar();
	}

	void setCantDeValoresPrefijados(int num) {
		sudokuConValoresPrefijados = clonar();
		cantDeValoresPrefijados = num;
	}
	
	public int[][] getMatrizConValoresPrefijados() {
		sudokuConValoresPrefijados = clonar();
		return sudokuConValoresPrefijados;
	}

	private void buscarCasillaYmarcar() {
		boolean marcar = true;
		while(marcar) {
			int numA = (int)(Math.random()*9)+1;
			int[] pos = posionesAleatoria();
			if(getValorEnMatriz(pos[0], pos[1])==0  && esSeguro(pos[0],pos[1],numA)) {
				setMatriz(pos[0],pos[1],numA);
				marcar=false;
				}
		}
	}

	private int[] posionesAleatoria() {
		int pos= (int) (Math.random()*81);
		return new int[] {pos/sudoku.length,pos%sudoku.length};
	}

	 void setMatrizClonada(int[][] c) {
		sudoku=c;
	}

	 public boolean casillaMarcada(int fila, int columna) {
		 if(fila < 0 || columna < 0 || fila > sudoku.length || columna > sudoku.length) {
				throw new IllegalArgumentException("Los parametros fila o columna son invalidos");
			}
		 if(sudoku[fila][columna] == 0) 
			return false;
		
		return true;
	 }
	 
	 public int getCantDeValoresPrefijados() {
		 return cantDeValoresPrefijados;
	 }

	 public void string() {
		for(int i=0;i<sudoku.length;i++) {
			for(int j=0;j<sudoku.length;j++) {
				System.out.print(sudoku[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	 }

	 public void aumetarCantidadDeNumerosIngresadosEnElTablero(int valor) {
			if (valor == 0) {
				cantDeValoresPrefijados--;
				System.out.println(cantDeValoresPrefijados);
				return;
			}
			cantDeValoresPrefijados++;
			System.out.println(cantDeValoresPrefijados);
	 }
	 
	 
	
}