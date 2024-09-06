package com.mycompany.projeto.kleiton;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PROJETO13KLEITON {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Digite o tamanho do vetor: ");
        int tamanhoVetor = getValidInteger(scanner);
        int[] vetor = new int[tamanhoVetor];

        for (int i = 0; i < tamanhoVetor; i++) {
            System.out.print("Digite o valor para o índice " + i + ": ");
            vetor[i] = getValidInteger(scanner);
        }

       
        System.out.println("Vetor atual:");
        for (int i = 0; i < vetor.length; i++) {
            System.out.println("Índice " + i + ": " + vetor[i]);
        }

        
        System.out.print("Escolha uma operação (adição, subtração, multiplicação, divisão): ");
        String operacao = scanner.next().toLowerCase().trim();

        
        System.out.print("Escolha o primeiro índice (0 a " + (tamanhoVetor - 1) + "): ");
        int index1 = getValidIndex(scanner, tamanhoVetor);

        System.out.print("Escolha o segundo índice (0 a " + (tamanhoVetor - 1) + "): ");
        int index2 = getValidIndex(scanner, tamanhoVetor);

        
        int[] resultados = new int[tamanhoVetor];
        for (int i = 0; i < resultados.length; i++) {
            resultados[i] = Integer.MIN_VALUE;  
        }

        
        try {
            int resultado = realizarOperacao(vetor, index1, index2, operacao);
            System.out.println("Resultado da operação entre os índices " + index1 + " e " + index2 + ": " + resultado);
            resultados[index1] = resultado;

            
            System.out.println("Vetor de resultados atualizado:");
            for (int i = 0; i < resultados.length; i++) {
                if (resultados[i] == Integer.MIN_VALUE) {
                    System.out.println("Índice " + i + ": Nenhum resultado");
                } else {
                    System.out.println("Índice " + i + ": " + resultados[i]);
                }
            }

        } catch (ArithmeticException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro: Índice fora dos limites do vetor.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        scanner.close();
    }

    
    private static int getValidInteger(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.next();
            }
        }
    }

    
    private static int getValidIndex(Scanner scanner, int tamanhoVetor) {
        while (true) {
            try {
                int index = scanner.nextInt();
                if (index >= 0 && index < tamanhoVetor) {
                    return index;
                } else {
                    throw new ArrayIndexOutOfBoundsException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.next();  
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Índice fora dos limites. Insira um número entre 0 e " + (tamanhoVetor - 1) + ".");
            }
        }
    }

    
    private static int realizarOperacao(int[] vetor, int index1, int index2, String operacao) throws ArithmeticException {
        switch (operacao) {
            case "adição":
                return vetor[index1] + vetor[index2];
            case "subtração":
                return vetor[index1] - vetor[index2];
            case "multiplicação":
                return vetor[index1] * vetor[index2];
            case "divisão":
                if (vetor[index2] == 0) {
                    throw new ArithmeticException("Tentativa de divisão por zero.");
                }
                return vetor[index1] / vetor[index2];
            default:
                throw new IllegalArgumentException("Operação inválida. Escolha entre adição, subtração, multiplicação ou divisão.");
        }
    }
}
