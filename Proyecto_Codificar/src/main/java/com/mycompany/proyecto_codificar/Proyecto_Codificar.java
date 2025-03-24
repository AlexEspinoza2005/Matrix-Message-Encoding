/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto_codificar;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Proyecto_Codificar {
public static void main(String[] args) {
        System.out.println("");
        System.out.println("            Proyecto Integrado Algoritmos – Álgebra");
        System.out.println("");
        System.out.println("Integrantes:");
        System.out.println("            1. Espinoza Alex");

        System.out.println("===============================================");
        System.out.println("Encriptación");
        System.out.println("===============================================");
        System.out.println("");

        Scanner scanner = new Scanner(System.in);

        // Mostrar el menú
        System.out.println("________________________________________________");
        System.out.println("Seleccione el tamaño de la matriz:");
        System.out.println("1. Matriz 2x2");
        System.out.println("2. Matriz 3x3");
        System.out.print("Ingrese su opción (1 o 2): ");
        int opcion = scanner.nextInt();
        int[][] matriz;
        int[][] matrizInversa;

        if (opcion == 1)
        {
            // Generar una matriz 2x2 con determinante 1 o -1
            matriz = generarMatriz2x2ConDeterminanteUnoOMenosUno();
            matrizInversa = calcularMatrizInversa2x2(matriz);
        } 
        else if (opcion == 2) 
        {
            // Generar una matriz 3x3 con determinante 1 o -1
            matriz = generarMatriz3x3ConDeterminanteUnoOMenosUno();
            matrizInversa = calcularMatrizInversa3x3(matriz);
        } 
        else 
        {
            System.out.println("Opción no válida. Seleccionando matriz 3x3 por defecto.");
            matriz = generarMatriz3x3ConDeterminanteUnoOMenosUno();
            matrizInversa = calcularMatrizInversa3x3(matriz);
        }

        // Mostrar la matriz generada
        System.out.println("MATRIZ SELECCIONADA:");
        mostrarMatriz(matriz);
        System.out.println("");

        // Generar y mostrar el diccionario aleatorio
        char[] alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ,.?¿ ".toCharArray();
        int[] valores = generarValoresAleatorios(alfabeto.length);
        System.out.println("             ABECEDARIO CODIFICADO");
        System.out.println("╔════════════════════════════════════════════════════════╗");
        for (int i = 0; i < alfabeto.length; i++) 
        {
            if (i % 6 == 0)
            {
                System.out.print("║");
            }
            System.out.printf(" %c = %2d ", alfabeto[i], valores[i]);
            if ((i + 1) % 6 == 0) {
                System.out.println("║");
            }
        }
        int remaining = alfabeto.length % 6;
        if (remaining != 0) 
        {
            for (int i = 0; i < 6 - remaining; i++)
            {
                System.out.print("         ");
            }
            System.out.println("║");
        }
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println("");

        // Obtener el mensaje a encriptar del usuario
        System.out.print("Ingrese su palabra: ");
        scanner.nextLine(); // Consumir el salto de línea
        String palabra = scanner.nextLine().toUpperCase();

        // Pasar lenguaje a números
        int[] codificado = codificarMensaje(palabra, alfabeto, valores);

        // Convertir a formato número
        System.out.print("Conversión a formato número: ");
        for (int num : codificado)
        {
            System.out.print(num + " ");
        }
        System.out.println();

        // Agrupamiento en grupos según el tamaño de la matriz
        int groupSize = (opcion == 1) ? 2 : 3;
        System.out.println("Agrupamiento en grupos de " + groupSize + ":");
        StringBuilder mensajeCodificado = new StringBuilder();

        // Obtener el valor que equivale al espacio en blanco en el diccionario
        int valorEspacio = -1; // Valor del espacio en blanco en el diccionario
        for (int i = 0; i < alfabeto.length; i++) 
        {
            if (alfabeto[i] == ' ') 
            {
                valorEspacio = valores[i];
                break;
            }
        }

        for (int i = 0; i < codificado.length; i += groupSize)
        {
            int[] vector = new int[groupSize];
            for (int j = 0; j < groupSize; j++)
            {
                vector[j] = (i + j < codificado.length) ? codificado[i + j] : valorEspacio;
                System.out.print(vector[j] + " ");
            }
            System.out.println();

            int[] resultado = multiplicarMatrizVector(matriz, vector);
            // Mostrar los resultados de cada multiplicación de matriz
            System.out.print("Matriz C" + (i / groupSize + 1) + ": ");
            for (int value : resultado) 
            {
                System.out.print(value + " ");
                mensajeCodificado.append(value).append(" ");
            }
            System.out.println();
        }
        System.out.println();

        // Mostrar el mensaje codificado
        System.out.print("MENSAJE CODIFICADO: ");
        String mensajeCodificadoStr = mensajeCodificado.toString().trim();
        System.out.println(mensajeCodificadoStr);

        // Sección de desencriptación
        System.out.println("===============================================");
        System.out.println("Desencriptación");
        System.out.println("===============================================");
        System.out.println("");
        System.out.println("DESCODIFICADO:");
        System.out.println("1. Usar el mensaje codificado generado");
        System.out.println("2. Desencriptar otro mensaje codificado");
        System.out.print("Opción elegida: ");
        int opcionDescifrar = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        if (opcionDescifrar == 1) 
        {
            // Mostrar la matriz inversa
            System.out.println("Matriz inversa de la matriz utilizada para encriptar:");
            mostrarMatriz(matrizInversa);

            // Convertir el mensaje codificado a formato de grupos de 2x2 o 3x3
            String[] mensajeEncriptado = mensajeCodificadoStr.split(" ");
            int n = mensajeEncriptado.length / groupSize;

            System.out.println("Mensaje encriptado actual pasado a grupos de " + groupSize + "x" + groupSize + ":");
            StringBuilder mensajeDescifradoCodificado = new StringBuilder();
            for (int i = 0; i < mensajeEncriptado.length; i += groupSize) 
            {
                int[] grupoCodificado = new int[groupSize];
                for (int j = 0; j < groupSize; j++) 
                {
                    grupoCodificado[j] = Integer.parseInt(mensajeEncriptado[i + j]);
                    System.out.print(grupoCodificado[j] + " ");
                }
                System.out.println();

                int[] resultado = multiplicarMatrizVector(matrizInversa, grupoCodificado);
                System.out.print("Matriz B: "+i);
                for (int value : resultado) 
                {
                    System.out.print(value + " ");
                    mensajeDescifradoCodificado.append(value).append(" ");
                }
                System.out.println();
            }
            System.out.println();

            // Convertir los números a letras según el abecedario
            String mensajeDescifrado = convertirNumerosALetras(mensajeDescifradoCodificado.toString().trim().split(" "), alfabeto, valores);
            System.out.println("Código desencriptado:");
            System.out.println(mensajeDescifradoCodificado.toString().trim());
            System.out.println("Mensaje desencriptado: " + mensajeDescifrado);

        } 
        else if (opcionDescifrar == 2)

{
           
           char[] alfabeto1 = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ,.?¿ ".toCharArray();
        int[] valores1 = new int[alfabeto1.length];

        // Pedir al usuario que ingrese un valor para cada carácter
        System.out.println("Ingrese los valores para cada carácter del abecedario:");
        for (int i = 0; i < alfabeto1.length; i++) 
        {
            System.out.printf("Valor para '%c': ", alfabeto1[i]);
            while (!scanner.hasNextInt()) 
            {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar el input inválido
                System.out.printf("Valor para '%c': ", alfabeto1[i]);
            }
            valores1[i] = scanner.nextInt();
        }

        // Mostrar el abecedario con sus valores ingresados
        System.out.println("             ABECEDARIO DEL USUARIO");
        System.out.println("╔════════════════════════════════════════════════════════╗");
        for (int i = 0; i < alfabeto1.length; i++) 
        {
            if (i % 6 == 0) {
                System.out.print("║");
            }
            System.out.printf(" %c = %2d ", alfabeto1[i], valores1[i]);
            if ((i + 1) % 6 == 0) {
                System.out.println("║");
            }
        }
        // Asegurarse de que la última línea tenga el formato correcto
        int remaining1 = alfabeto1.length % 6;
        if (remaining1 != 0) {
            for (int i = 0; i < 6 - remaining1; i++)
            {
                System.out.print("         ");
            }
            System.out.println("║");
        }
        System.out.println("╚════════════════════════════════════════════════════════╝");

        // Menú para seleccionar el tamaño de la matriz
        int tamaño1 = 0;
        System.out.println("\nSeleccione el tamaño de la matriz:");
        System.out.println("1. Matriz 2x2");
        System.out.println("2. Matriz 3x3");

        // Leer la opción del usuario
        System.out.print("Opción: ");
        int opcion1 = scanner.nextInt();

        switch (opcion1) 
        {
            case 1:
                tamaño1 = 2;
                break;
            case 2:
                tamaño1 = 3;
                break;
            default:
                System.out.println("Opción inválida. Elija 1 para 2x2 o 2 para 3x3.");
                scanner.close();
                return;
        }

        // Crear y llenar la matriz según el tamaño elegido
        int[][] matriz1 = new int[tamaño1][tamaño1];
        System.out.println("Ingrese los valores de la matriz " + tamaño1 + "x" + tamaño1 + ":");

        for (int i = 0; i < tamaño1; i++) 
        {
            for (int j = 0; j < tamaño1; j++)
            {
                System.out.print("Matriz[" + i + "][" + j + "] = ");
                matriz1[i][j] = scanner.nextInt();
            }
        }

        // Mostrar la matriz
        System.out.println("Matriz " + tamaño1 + "x" + tamaño1 + ":");
        for (int i = 0; i < tamaño1; i++) 
        {
            for (int j = 0; j < tamaño1; j++)
            {
                System.out.print(matriz1[i][j] + " ");
            }
            System.out.println();
        }

        // Leer el código del usuario para descifrar el mensaje
        System.out.println("\nIngrese el código (números separados por espacios):");
        scanner.nextLine(); // Limpiar el buffer del scanner
        String entrada1 = scanner.nextLine();
        String[] numerosStr1 = entrada1.split(" ");

        // Convertir los números en un arreglo de enteros
        int[] numerosCodificados1 = new int[numerosStr1.length];
        for (int i = 0; i < numerosStr1.length; i++)
        {
            numerosCodificados1[i] = Integer.parseInt(numerosStr1[i]);
        }

        // Completar el grupo con el valor del espacio si es necesario
        int espacioValor1 = valores1[buscarIndice1(alfabeto1, ' ')];
        while (numerosCodificados1.length % tamaño1 != 0)
        {
            numerosCodificados1 = agregarElemento1(numerosCodificados1, espacioValor1);
        }

        // Decodificar el mensaje
        String mensajeDescifrado1 = descifrarMensaje1(numerosCodificados1, alfabeto1, valores1, matriz1);
        System.out.println("Mensaje descifrado: " + mensajeDescifrado1);

        scanner.close();
    }
            
            
            
            
            
            
            
            
            
            
            
            
            

        
        else {
            System.out.println("Opción no válida.");
        }
    }

    // Función para generar una matriz 2x2 con determinante 1 o -1
    public static int[][] generarMatriz2x2ConDeterminanteUnoOMenosUno() {
        int[][] matriz;
        while (true) {
            matriz = new int[2][2];
            matriz[0][0] = (int) (Math.random() * 3) - 1; // -1, 0, 1
            matriz[0][1] = (int) (Math.random() * 3) - 1; // -1, 0, 1
            matriz[1][0] = (int) (Math.random() * 3) - 1; // -1, 0, 1
            matriz[1][1] = (int) (Math.random() * 3) - 1; // -1, 0, 1
            int determinante = matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
            if (determinante == 1 || determinante == -1) {
                break;
            }
        }
        return matriz;
    }

    // Función para calcular la matriz inversa de una matriz 2x2
    public static int[][] calcularMatrizInversa2x2(int[][] matriz) {
        int[][] inversa = new int[2][2];
        int determinante = matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        if (determinante == 0) {
            throw new IllegalArgumentException("La matriz no es invertible.");
        }
        inversa[0][0] = matriz[1][1] / determinante;
        inversa[0][1] = -matriz[0][1] / determinante;
        inversa[1][0] = -matriz[1][0] / determinante;
        inversa[1][1] = matriz[0][0] / determinante;
        return inversa;
    }

    // Función para generar una matriz 3x3 con determinante 1 o -1
    public static int[][] generarMatriz3x3ConDeterminanteUnoOMenosUno() {
        int[][] matriz;
        while (true) {
            matriz = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    matriz[i][j] = (int) (Math.random() * 3) - 1; // -1, 0, 1
                }
            }
            int determinante = matriz[0][0] * (matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1])
                             - matriz[0][1] * (matriz[1][0] * matriz[2][2] - matriz[1][2] * matriz[2][0])
                             + matriz[0][2] * (matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0]);
            if (determinante == 1 || determinante == -1) {
                break;
            }
        }
        return matriz;
    }

    // Función para calcular la matriz inversa de una matriz 3x3
    public static int[][] calcularMatrizInversa3x3(int[][] matriz) {
        int[][] inversa = new int[3][3];
        int a = matriz[0][0], b = matriz[0][1], c = matriz[0][2];
        int d = matriz[1][0], e = matriz[1][1], f = matriz[1][2];
        int g = matriz[2][0], h = matriz[2][1], i = matriz[2][2];

        int determinante = a * (e * i - f * h)
                         - b * (d * i - f * g)
                         + c * (d * h - e * g);

        if (determinante == 0) {
            throw new IllegalArgumentException("La matriz no es invertible.");
        }

        inversa[0][0] = (e * i - f * h) / determinante;
        inversa[0][1] = (c * h - b * i) / determinante;
        inversa[0][2] = (b * f - c * e) / determinante;
        inversa[1][0] = (f * g - d * i) / determinante;
        inversa[1][1] = (a * i - c * g) / determinante;
        inversa[1][2] = (c * d - a * f) / determinante;
        inversa[2][0] = (d * h - e * g) / determinante;
        inversa[2][1] = (b * g - a * h) / determinante;
        inversa[2][2] = (a * e - b * d) / determinante;

        return inversa;
    }

    // Función para mostrar una matriz
    public static void mostrarMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int elemento : fila) {
                System.out.printf("%4d ", elemento);
            }
            System.out.println();
        }
    }

    // Función para generar valores aleatorios para el diccionario
    public static int[] generarValoresAleatorios(int tamaño) {
        int[] valores = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            valores[i] = i + 1;
        }
        for (int i = 0; i < tamaño; i++) {
            int aleatorio = (int) (Math.random() * tamaño);
            int temp = valores[i];
            valores[i] = valores[aleatorio];
            valores[aleatorio] = temp;
        }
        return valores;
    }

    // Función para codificar un mensaje a números
    public static int[] codificarMensaje(String mensaje, char[] alfabeto, int[] valores) {
        int[] codificado = new int[mensaje.length()];
        for (int i = 0; i < mensaje.length(); i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                if (mensaje.charAt(i) == alfabeto[j]) {
                    codificado[i] = valores[j];
                    break;
                }
            }
        }
        return codificado;
    }

    // Función para convertir números a letras
    public static String convertirNumerosALetras(String[] numeros, char[] alfabeto, int[] valores) {
        StringBuilder mensajeDescifrado = new StringBuilder();
        for (String numero : numeros) {
            int valor = Integer.parseInt(numero);
            for (int i = 0; i < alfabeto.length; i++) {
                if (valores[i] == valor) {
                    mensajeDescifrado.append(alfabeto[i]);
                    break;
                }
            }
        }
        return mensajeDescifrado.toString();
    }

    // Función para multiplicar una matriz 2x2 o 3x3 por un vector
    public static int[] multiplicarMatrizVector(int[][] matriz, int[] vector) {
        int size = matriz.length;
        int[] resultado = new int[size];
        for (int i = 0; i < size; i++) {
            resultado[i] = 0;
            for (int j = 0; j < size; j++) {
                resultado[i] += matriz[i][j] * vector[j];
            }
        }
        return resultado;
    }
public static int buscarIndice1(char[] alfabeto, char letra) {
        for (int i = 0; i < alfabeto.length; i++) {
            if (alfabeto[i] == letra) {
                return i;
            }
        }
        return -1; // Retorna -1 si no encuentra el carácter
    }

    // Función para agregar un elemento a un array
    public static int[] agregarElemento1(int[] array, int elemento) {
        int[] nuevoArray1 = new int[array.length + 1];
        System.arraycopy(array, 0, nuevoArray1, 0, array.length);
        nuevoArray1[array.length] = elemento;
        return nuevoArray1;
    }

    // Función para calcular el determinante de una matriz 2x2
    public static int calcularDeterminante2x21(int[][] matriz) {
        return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
    }

    // Función para calcular la inversa de una matriz 2x2
    public static double[][] calcularMatrizInversa2x21(int[][] matriz) {
        int determinante = calcularDeterminante2x21(matriz);
        double[][] inversa = new double[2][2];
        if (determinante != 0) {
            inversa[0][0] = matriz[1][1];
            inversa[0][1] = -matriz[0][1];
            inversa[1][0] = -matriz[1][0];
            inversa[1][1] = matriz[0][0];
            double invDet = 1.0 / determinante;
            // Multiplicar por el inverso del determinante
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    inversa[i][j] *= invDet;
                }
            }
        }
        return inversa;
    }

    // Función para calcular el determinante de una matriz 3x3
    public static int calcularDeterminante3x31(int[][] matriz) {
        return matriz[0][0] * (matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1])
                - matriz[0][1] * (matriz[1][0] * matriz[2][2] - matriz[1][2] * matriz[2][0])
                + matriz[0][2] * (matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0]);
    }

    // Función para calcular la inversa de una matriz 3x3
    public static double[][] calcularMatrizInversa3x31(int[][] matriz) {
        int determinante = calcularDeterminante3x31(matriz);
        double[][] inversa = new double[3][3];
        if (determinante != 0) {
            inversa[0][0] = matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1];
            inversa[0][1] = matriz[0][2] * matriz[2][1] - matriz[0][1] * matriz[2][2];
            inversa[0][2] = matriz[0][1] * matriz[1][2] - matriz[0][2] * matriz[1][1];
            inversa[1][0] = matriz[1][2] * matriz[2][0] - matriz[1][0] * matriz[2][2];
            inversa[1][1] = matriz[0][0] * matriz[2][2] - matriz[0][2] * matriz[2][0];
            inversa[1][2] = matriz[0][2] * matriz[1][0] - matriz[0][0] * matriz[1][2];
            inversa[2][0] = matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0];
            inversa[2][1] = matriz[0][1] * matriz[2][0] - matriz[0][0] * matriz[2][1];
            inversa[2][2] = matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
            double invDet = 1.0 / determinante;
            // Multiplicar por el inverso del determinante
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    inversa[i][j] *= invDet;
                }
            }
        }
        return inversa;
    }

    // Función para calcular la inversa de una matriz (2x2 o 3x3)
    public static double[][] calcularMatrizInversa1(int[][] matriz) {
        int tamaño = matriz.length;
        if (tamaño == 2) {
            return calcularMatrizInversa2x21(matriz);
        } else if (tamaño == 3) {
            return calcularMatrizInversa3x31(matriz);
        } else {
            throw new IllegalArgumentException("Tamaño de matriz no soportado.");
        }
    }

    // Función para multiplicar una matriz por un vector
    public static double[] multiplicarMatrizVector1(double[][] matriz, double[] vector) {
        int tamaño = matriz.length;
        double[] resultado = new double[tamaño];
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                resultado[i] += matriz[i][j] * vector[j];
            }
        }
        return resultado;
    }

    // Función para descifrar el mensaje
    public static String descifrarMensaje1(int[] numerosCodificados, char[] alfabeto, int[] valores, int[][] matriz) {
        double[][] inversa = calcularMatrizInversa1(matriz);
        StringBuilder mensaje = new StringBuilder();
        int tamaño = matriz.length;
        for (int i = 0; i < numerosCodificados.length; i += tamaño) {
            int[] grupo = new int[tamaño];
            System.arraycopy(numerosCodificados, i, grupo, 0, tamaño);
            double[] vectorDescifrado = multiplicarMatrizVector1(inversa, toDoubleArray1(grupo));
            for (double valor : vectorDescifrado) {
                int valorEntero = (int) Math.round(valor); // Redondear al entero más cercano
                for (int j = 0; j < valores.length; j++) {
                    if (valores[j] == valorEntero) {
                        mensaje.append(alfabeto[j]);
                        break;
                    }
                }
            }
        }
        return mensaje.toString();
    }

    // Función para convertir el array de enteros a double
    public static double[] toDoubleArray1(int[] array) {
        double[] doubleArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubleArray[i] = array[i];
        }
        return doubleArray;
    }
}
