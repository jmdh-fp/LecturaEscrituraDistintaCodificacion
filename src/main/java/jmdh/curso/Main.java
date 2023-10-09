/*
Crea un programa que, a partir de un fichero de texto codificado en UTF-8,
genere un fichero de texto codificado en ISO-8859-1 y otro en UTF-16. EI fichero codificado en UTF-8
debe crearse con un editor de texto, y debe incluir al menos vocales acentuadas.
Puedes leer el fichero línea a línea con readLine( ).
Para generar el fichero de salida, puedes utilizar un BufferedWriter (para escribir línea a línea)
construido sobre un OutputStreamWriter (para recodificar el texto) construido sobre un
FileOutputStream (para escribir a un fichero).
Busca una manera de verificar la codificación.
 */

package jmdh.curso;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    final static String DIRECTORIO = "D:\\tmp\\";
    final static String FICHERO = "fichero.txt";
    final static File FICHERO_ORIGEN = new File(DIRECTORIO + FICHERO);
    final static File FICHERO_ISO_859_1 =new File( DIRECTORIO +  "ISO_8859_1_" + FICHERO_ORIGEN.getName());
    final static File FICHERO_UTF_16 =new File( DIRECTORIO +  "UTF_16_" + FICHERO_ORIGEN.getName());
    final static String FIN_LINEA = System.lineSeparator();
    public static void main(String[] args)  {
        try (BufferedReader inUtf8 =  new BufferedReader(new FileReader(FICHERO_ORIGEN));
             BufferedWriter outIso = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FICHERO_ISO_859_1), StandardCharsets.ISO_8859_1));
             BufferedWriter outUtf16 = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(FICHERO_UTF_16), StandardCharsets.UTF_16))
        ) {
            String linea;
            while ((linea = inUtf8.readLine()) != null){
                outIso.write(linea); outIso.newLine();
                outUtf16.write(linea + FIN_LINEA); // Sin usar BufferedWriter.newLine()
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}