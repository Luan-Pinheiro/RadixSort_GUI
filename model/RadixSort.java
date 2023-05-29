package model;

import java.util.Arrays;
import controller.Controlador;
/////////////////////////////////////
/////////////////////////////////////
/////////////////////////////////////
/*IMPLEMENTAR RADIX SORT DO GRUPO */
/////////////////////////////////////
/////////////////////////////////////
/////////////////////////////////////
public class RadixSort {
  Controlador cT;
  private static int tamanho;
  public RadixSort(){
  }
  public RadixSort(Controlador cT){
    this.cT = cT;
    tamanho = Integer.parseInt(this.cT.getTxtTamVet());
  }
    public static void radixSort(int[] array) {
      
        // Encontra o valor máximo no array para determinar o número de dígitos
        int max = Arrays.stream(array).max().getAsInt();
        
        // Aplica o Radix Sort para cada dígito
        for (int exp = 1; max / exp > 0; exp *= tamanho) {
            countingSort(array, exp);
        }
    }
    
    private static void countingSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[tamanho];
        Arrays.fill(count, 0);
        
        // Conta a ocorrência de cada dígito
        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % tamanho]++;
        }
        
        // Calcula as posições corretas no array de saída
        for (int i = 1; i < tamanho; i++) {
            count[i] += count[i - 1];
        }
        
        // Preenche o array de saída na ordem correta
        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % tamanho] - 1] = array[i];
            count[(array[i] / exp) % tamanho]--;
        }
        
        // Copia o array de saída de volta para o array original
        System.arraycopy(output, 0, array, 0, n);
    }
    
    public void print(int[] vetor){
        System.out.println("Array original: " + Arrays.toString(vetor));
        radixSort(vetor);
        System.out.println("Array ordenado: " + Arrays.toString(vetor));
    }
}
