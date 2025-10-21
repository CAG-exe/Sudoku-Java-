package Modelo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matriz {
	private int[][] sudoku;
	
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
		return sudoku[x][y];
	}

	public boolean setMatriz(int x,int y, int valor) {
		if(valor<1 || valor >9)
			throw new IllegalArgumentException("el valor tiene que estar entre 1 y 9");
		if(sudoku[x][y] == valor || posicionValidaParaValor(x,y,valor))	
			return false;
		sudoku[x][y] = valor;
		return true;
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
		        for (int j = colInicio; j < colInicio + 3; j++) {  //creo que tiene un error, cuando verifica la casilla x, y = valor antes de la verficacion
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
	
	public int[][] getMatriz() {
		return clonar();
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
		if(num<15 || num>40) {
			throw new IllegalArgumentException("numero de casillas no valido, tiene que estar entre 15 o 40");
		}
		for(int i=0;i<num;i++){
		buscarCasillaYmarcar();
		}
	}

	private void buscarCasillaYmarcar() {
		boolean marcar = true;
		while(marcar) {
			int numA = (int)(Math.random()*9)+1;
			int[] pos = posionesAleatoria();
			if(getValorEnMatriz(pos[0], pos[1])==0  && setMatriz(pos[0],pos[1],numA)) {
				marcar=false;
				}
		}
	}

	private int[] posionesAleatoria() {
		int pos= (int) (Math.random()*81);
		return new int[] {pos/sudoku.length,pos%sudoku.length};
	}
	
	///metodo para test
	public void testMode() {
		sudoku= new int[][]{{0,6,8,4,2,7,1,0,3}
						   ,{3,4,2,9,1,5,8,6,7}
						   ,{1,9,7,6,8,3,5,2,4}
						   ,{6,8,5,1,3,2,7,4,9}
						   ,{7,3,4,5,9,8,6,1,2}
						   ,{2,1,9,7,6,4,3,5,8}
						   ,{4,7,3,2,5,6,9,8,1}
						   ,{8,5,1,3,4,9,2,7,6}
						   ,{0,1,6,8,1,1,4,3,5}};
	}

	 void setMatrizClonada(int[][] c) {
		sudoku=c;
	}
	
}
