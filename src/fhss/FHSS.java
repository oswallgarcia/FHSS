package fhss;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author OSWALL
 */
public class FHSS {

    public static void main(String args[]) throws InterruptedException {

        //Declaramos variables, objetos y arreglos
        Scanner in = new Scanner(System.in);
        int bits[] =
        {
            128, 64, 32, 16, 8, 4, 2, 1
        };
        char alfabeto[] =
        {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        String canales[] = new String[80];
        String mensaje = "";
        String Dmensaje = "";

        //Entrada de mensaje del Emisor
        System.out.println("Ingresa el Mensaje");
        mensaje = in.nextLine();

        char caracter[] = new char[mensaje.length()];
        int decimales[] = new int[mensaje.length()];
        String num_binario[] = new String[mensaje.length()];
        String frecuencia[] = new String[mensaje.length()];
        int Decimal_Receptor[] = new int[mensaje.length()];

        //parte lógica del emisor 
        for (int i = 0; i < mensaje.length(); i++)
        {

            caracter[i] = mensaje.charAt(i);

        }

        for (int i = 0; i < caracter.length; i++)
        {

            int num = ASCII(caracter[i], alfabeto);
            System.out.print(caracter[i] + "->" + num);

            decimales[i] = num;

            System.out.println("");

        }

        for (int i = 0; i < decimales.length; i++)
        {

            System.out.println(decimales[i]);

        }

        for (int i = 0; i < decimales.length; i++)
        {

            String num = Binario(decimales[i], bits);
            System.out.print(decimales[i] + "->" + num);

            num_binario[i] = num;

            System.out.println("");

        }

        for (int i = 0; i < num_binario.length; i++)
        {

            System.out.print(num_binario[i]);

        }

        System.out.println("");
        System.out.println("Enviando Mensaje...");
        System.out.println("");

        //Saltos de frecuencia del paquete de datos
        for (int i = 0; i < num_binario.length; i++)
        {

            System.out.println("");

            for (int j = 0; j < 3; j++)
            {

                int num_aleatorio = Aleatorio();
                int Frecuencia = 400 + num_aleatorio;

                canales[num_aleatorio] = num_binario[i];

                Thread.sleep(500);
                System.out.print("Channel " + num_aleatorio + " Data " + num_binario[i] + " Frecuencia 2." + Frecuencia + "GHz |");

                if (j == 2)
                {

                    frecuencia[i] = " Frecuencia 2." + String.valueOf(Frecuencia);

                }

            }

        }

        System.out.println("");
        System.out.println("");
        System.out.println("");

        //parte lógica del receptor 
        System.out.println("...Recibiendo Mensaje");

        for (int i = 0; i < num_binario.length; i++)
        {

            System.out.println(num_binario[i] + frecuencia[i]);

        }

        System.out.println("");

        for (int i = 0; i < num_binario.length; i++)
        {

            System.out.print(num_binario[i]);

        }

        System.out.println("");

        for (int i = 0; i < num_binario.length; i++)
        {

            int Decimal = DBinario(num_binario[i], bits);
            System.out.println(num_binario[i] + "->" + Decimal);

        }



        for (int i = 0; i < num_binario.length; i++)
        {

            int Decimal = DBinario(num_binario[i], bits);
            Decimal_Receptor[i] = Decimal;
            System.out.println(Decimal);

        }

      

        for (int i = 0; i < Decimal_Receptor.length; i++)
        {

            char Mensaje = DMensaje(Decimal_Receptor[i], alfabeto);
            Dmensaje += String.valueOf(Mensaje);
            System.out.println(Decimal_Receptor[i] + "->" + Mensaje);

        }

        System.out.println("");

        System.out.println(Dmensaje);

    }

    //Métodos para la codificación y decodificación
    public static int ASCII(char letra, char[] alfabeto) {

        for (int i = 0; i <= alfabeto.length; i++)
        {

            if (letra == alfabeto[i])
            {

                return i + 97;

            }

            if (letra == 'ñ')
            {

                return 164;

            }

            if (letra == ' ')
            {

                return 32;

            }

        }

        return 0;

    }

    public static String Binario(int x, int[] bits) {

        String cadena = "";

        for (int i = 0; i < bits.length; i++)
        {

            if (x >= bits[i])
            {

                x -= bits[i];

                cadena += "1";

            } else
            {

                cadena += "0";

            }

        }

        return cadena;

    }

    public static int Aleatorio() {

        int x = (int) (Math.random() * 80);
        return x;

    }

    public static int DBinario(String cadena, int[] bits) {

        int numero = 0;
        char bit;

        for (int i = 0; i < bits.length; i++)
        {

            bit = cadena.charAt(i);
            if (bit == '1')
            {

                numero += bits[i];
                bit = ' ';

            }

        }

        return numero;

    }

    public static char DMensaje(int decimal, char[] alfabeto) {

        char cadena = ' ';

        for (int i = 0; i <= alfabeto.length; i++)
        {

            if (decimal == i + 97)
            {

                cadena = alfabeto[i];

            }

            if (decimal == 164)
            {

                cadena = 'ñ';

            }

            if (decimal == 32)
            {

                cadena = ' ';

            }

        }

        return cadena;

    }

}
