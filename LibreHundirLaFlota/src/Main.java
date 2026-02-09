import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int tamanyoTablero = 10;
        int PORTAAVIONES = 5;
        int ACORAZADO = 4;


        System.out.println(" nombre del jugador 1:");
        String player1 = sc.nextLine();
        System.out.println(" nombre del jugador 2:");
        String player2 = sc.nextLine();

        char[][] tableroPlayer1 = new char[tamanyoTablero][tamanyoTablero];
        char[][] tableroPlayer2 = new char[tamanyoTablero][tamanyoTablero];
        char[][] tableroVisiblePlayer1 = new char[tamanyoTablero][tamanyoTablero];
        char[][] tableroVisiblePlayer2 = new char[tamanyoTablero][tamanyoTablero];


        for (int i = 0; i < tamanyoTablero; i++) {
            for (int j = 0; j < tamanyoTablero; j++) {
                tableroPlayer1[i][j] = '-';
                tableroPlayer2[i][j] = '-';
                tableroVisiblePlayer1[i][j] = '-';
                tableroVisiblePlayer2[i][j] = '-';
            }
        }


        System.out.println("\n--- " + player1 + " coloca sus barcos ---");
        int[] barcos = {PORTAAVIONES, ACORAZADO};
        String[] nombresBarcos = {"Portaaviones", "Acorazado", "Destructor", "Submarino"};

        for (int b = 0; b < barcos.length; b++) {
            int tamaño = barcos[b];
            String nombreBarco = nombresBarcos[b];
            boolean colocado = false;

            while (!colocado) {
                System.out.println("Coloca " + nombreBarco + " (" + tamaño + ") Ej: A1 H/V");
                String filaStr = sc.next().toUpperCase();
                int fila = filaStr.charAt(0) - 'A';
                int col = sc.nextInt() - 1;
                char orientacion = sc.next().toUpperCase().charAt(0);

                if (fila < 0 || fila >= 10 || col < 0 || col >= 10 || (orientacion != 'H' && orientacion != 'V')) {
                    System.out.println("Coordenadas inválidas. Intenta de nuevo.");
                    continue;
                }

                boolean cabe = true;
                if (orientacion == 'H') {
                    if (col + tamaño > 10) cabe = false;
                    else {
                        for (int i = 0; i < tamaño; i++)
                            if (tableroPlayer1[fila][col + i] != '-') cabe = false;
                    }
                } else {
                    if (fila + tamaño > 10) cabe = false;
                    else {
                        for (int i = 0; i < tamaño; i++)
                            if (tableroPlayer1[fila + i][col] != '-') cabe = false;
                    }
                }

                if (!cabe) {
                    System.out.println("No cabe el barco allí. Intenta otra posición.");
                    continue;
                }


                for (int i = 0; i < tamaño; i++) {
                    if (orientacion == 'H') tableroPlayer1[fila][col + i] = 'B';
                    else tableroPlayer1[fila + i][col] = 'B';
                }


                System.out.print("  ");
                for (int c = 1; c <= tamanyoTablero; c++)
                    System.out.print(c + " ");
                    System.out.println();

                for (int i = 0; i < tamanyoTablero; i++) {
                    System.out.print((char)('A'+i) + " ");
                    for (int j = 0; j < tamanyoTablero; j++) System.out.print(tableroPlayer1[i][j] + " ");
                    System.out.println();
                }

                colocado = true;
            }
        }


        System.out.println("\n--- " + player2 + " coloca sus barcos ---");
        for (int b = 0; b < barcos.length; b++) {
            int tamaño = barcos[b];
            String nombreBarco = nombresBarcos[b];
            boolean colocado = false;

            while (!colocado) {
                System.out.println("Coloca " + nombreBarco + " (" + tamaño + ") Ej: A1 H/V");
                String filaStr = sc.next().toUpperCase();
                int fila = filaStr.charAt(0) - 'A';
                int col = sc.nextInt() - 1;
                char orientacion = sc.next().toUpperCase().charAt(0);

                if (fila < 0 || fila >= 10 || col < 0 || col >= 10 || (orientacion != 'H' && orientacion != 'V')) {
                    System.out.println("Coordenadas inválidas. Intenta de nuevo.");
                    continue;
                }

                boolean cabe = true;
                if (orientacion == 'H') {
                    if (col + tamaño > 10) cabe = false;
                    else {
                        for (int i = 0; i < tamaño; i++)
                            if (tableroPlayer2[fila][col + i] != '-') cabe = false;
                    }
                } else {
                    if (fila + tamaño > 10) cabe = false;
                    else {
                        for (int i = 0; i < tamaño; i++)
                            if (tableroPlayer2[fila + i][col] != '-') cabe = false;
                    }
                }

                if (!cabe) {
                    System.out.println("No cabe el barco allí. Intenta otra posición.");
                    continue;
                }


                for (int i = 0; i < tamaño; i++) {
                    if (orientacion == 'H') tableroPlayer2[fila][col + i] = 'B';
                    else tableroPlayer2[fila + i][col] = 'B';
                }


                System.out.print("  ");
                for (int c = 1; c <= tamanyoTablero; c++) System.out.print(c + " ");
                System.out.println();
                for (int i = 0; i < tamanyoTablero; i++) {
                    System.out.print((char)('A'+i) + " ");
                    for (int j = 0; j < tamanyoTablero; j++) System.out.print(tableroPlayer2[i][j] + " ");
                    System.out.println();
                }

                colocado = true;
            }
        }


        boolean turnoJugador1 = true;

        while (true) {
            if (turnoJugador1) {
                System.out.println("\nTurno de " + player1);

                System.out.print("  ");
                for (int c = 1; c <= tamanyoTablero; c++) System.out.print(c + " ");
                System.out.println();
                for (int i = 0; i < tamanyoTablero; i++) {
                    System.out.print((char)('A'+i) + " ");
                    for (int j = 0; j < tamanyoTablero; j++) {
                        char c = tableroVisiblePlayer1[i][j];
                        System.out.print(c + " ");
                    }
                    System.out.println();
                }


                boolean disparoValido = false;
                while (!disparoValido) {
                    System.out.println("Introduce coordenadas de disparo (Ej: A1):");
                    String filaStr = sc.next().toUpperCase();
                    int fila = filaStr.charAt(0) - 'A';
                    int col = sc.nextInt() - 1;

                    if (fila < 0 || fila >= 10 || col < 0 || col >= 10) {
                        System.out.println("Coordenadas inválidas. Intenta otra vez.");
                        continue;
                    }

                    if (tableroVisiblePlayer1[fila][col] != '-') {
                        System.out.println("Ya has disparado allí. Intenta otra posición.");
                        continue;
                    }

                    if (tableroPlayer2[fila][col] == 'B') {
                        System.out.println("¡Tocado!");
                        tableroVisiblePlayer1[fila][col] = 'X';
                        tableroPlayer2[fila][col] = 'X';
                    } else {
                        System.out.println("Agua...");
                        tableroVisiblePlayer1[fila][col] = 'O';
                        tableroPlayer2[fila][col] = 'O';
                    }

                    disparoValido = true;
                }


                boolean gano = true;
                for (int i = 0; i < tamanyoTablero; i++) {
                    for (int j = 0; j < tamanyoTablero; j++) {
                        if (tableroPlayer2[i][j] == 'B') gano = false;
                    }
                }
                if (gano) {
                    System.out.println("\n¡" + player1 + " ha ganado!");
                    break;
                }

            } else {
                System.out.println("\nTurno de " + player2);

                System.out.print("  ");
                for (int c = 1; c <= tamanyoTablero; c++) System.out.print(c + " ");
                System.out.println();
                for (int i = 0; i < tamanyoTablero; i++) {
                    System.out.print((char)('A'+i) + " ");
                    for (int j = 0; j < tamanyoTablero; j++) {
                        char c = tableroVisiblePlayer2[i][j];
                        System.out.print(c + " ");
                    }
                    System.out.println();
                }

                boolean disparoValido = false;
                while (!disparoValido) {
                    System.out.println("Introduce coordenadas de disparo (Ej: A1):");
                    String filaStr = sc.next().toUpperCase();
                    int fila = filaStr.charAt(0) - 'A';
                    int col = sc.nextInt() - 1;

                    if (fila < 0 || fila >= 10 || col < 0 || col >= 10) {
                        System.out.println("Coordenadas inválidas. Intenta otra vez.");
                        continue;
                    }

                    if (tableroVisiblePlayer2[fila][col] != '-') {
                        System.out.println("Ya has disparado allí. Intenta otra posición.");
                        continue;
                    }

                    if (tableroPlayer1[fila][col] == 'B') {
                        System.out.println("¡Tocado!");
                        tableroVisiblePlayer2[fila][col] = 'X';
                        tableroPlayer1[fila][col] = 'X';
                    } else {
                        System.out.println("Agua...");
                        tableroVisiblePlayer2[fila][col] = 'O';
                        tableroPlayer1[fila][col] = 'O';
                    }

                    disparoValido = true;
                }

                boolean gano = true;
                for (int i = 0; i < tamanyoTablero; i++) {
                    for (int j = 0; j < tamanyoTablero; j++) {
                        if (tableroPlayer1[i][j] == 'B') gano = false;
                    }
                }
                if (gano) {
                    System.out.println("\n¡" + player2 + " ha ganado!");
                    break;
                }
            }

            turnoJugador1 = !turnoJugador1;
        }

        sc.close();
    }
}
