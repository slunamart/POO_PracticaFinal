package ES;

import java.io.*;
import java.util.ArrayList;


/**
 * Clase con utilidades para la entrada de datos desde teclado y fichero
 * @author jvalvarez
 */
public class MyInput {
    // Lee una cadena de caracteres desde el teclado

    public static String readString( ){
        return readString( null );
    }

    /**
     * Método que permite leer una cadena de caracteres del teclado
     * @return retorna una cadena de caracteres
     */
    public static String readString( String prompt ) {
        if( prompt != null )
            System.out.print( prompt + ": " );
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in),1);
        String  string="";
        try {
            string = br.readLine(); }
        catch (IOException ex) {
            System.out.println(ex); }
        return string; }
// Lee un dato tipo int  desde el teclado

    public static boolean yesNoQuestion(){
        return readString("?" ).equalsIgnoreCase( "s" );
    }

    public static boolean yesNoQuestion(String prompt){
        return readString(prompt).equalsIgnoreCase("s");
    }

    public static void waitForIntro(String prompt){
        readString(prompt);
    }

    public static void waitForIntro(){
        readString("Pulse intro para continuar...");
    }

    public static int readInt( ){
        return readInt( null );
    }
    /**
     * Método que permite leer un número entero de simple precisión por teclado
     * @return retorna un número entero de precisión simple
     */
    public static int readInt( String prompt ) {
        if( prompt != null )
            System.out.print( prompt + ": " );
        try {
            return Integer.parseInt(readString());
        } catch (NumberFormatException ex) {
            System.out.println("La entrada no tiene formato de número. Inténtelo de nuevo");
            return readInt();
        }
    }
// Lee un dato tipo double  desde el teclado

    public static Double readDouble( ){
        return readDouble( null );
    }

    /**
     * Método que permite leer número real por teclado.
     * @return retorna un número de doble precisión.
     */
    public static double readDouble( String prompt) {
        if( prompt != null )
            System.out.print( prompt + ": " );
        try {
            return Double.parseDouble(readString());
        } catch (NumberFormatException ex) {
            System.out.println("La entrada no tiene formato de número. Inténtelo de nuevo");
            return  readDouble();
        }
    }

    /**
     * Método que permite leer un número entero por teclado.
     * @return retorna un número entero comprendido entre -128 y 127
     */
    public static byte readByte() {
        return Byte.parseByte(readString()); }
// Lee un dato tipo short  desde el teclado

    /**
     * Método que permite leer un número entero por teclado.
     * @return retorna un número entero comprendido entre -32768 y 32767
     */
    public static short readShort() {
        return Short.parseShort(readString()); }
// Lee un dato tipo long desde el teclado

    /**
     * Método que permite leer un número entero de doble precisión por teclado
     * @return retorna un número entero de doble precisión.
     */
    public static long readLong() {
        return Long.parseLong(readString()); }

//Lee un dato tipo float desde el teclado

    /**
     * Método que permite leer número real por teclado
     * @return retorna un número de precisión simple
     */
    public static float readFloat() {
        return Float.parseFloat(readString()); }



    public static <A> void serialize(A a, String nombreFichero) {
        try {
            FileOutputStream fos = new FileOutputStream(nombreFichero) ;
            ObjectOutputStream oos = new ObjectOutputStream(fos) ;
            oos.writeObject(a) ;
        } catch (IOException e) {
            System.err.println("Problem: "+e) ;}
    }

    public static <A> A deserialize(String nombreFichero) {
        try {
            FileInputStream fis = new FileInputStream(nombreFichero) ;
            ObjectInputStream iis = new ObjectInputStream(fis) ;
            return (A) iis.readObject() ;
        } catch (IOException e) {
            //System.err.println("Problem: "+e);
        }
        catch(ClassNotFoundException e)
        { System.out.println(e.getMessage()); }
        return null ;
    }

    public static ArrayList<String> leeFichero(String nombreFichero){
        ArrayList <String> v= new ArrayList <String>();
        File fichero=null;FileReader fr=null;
        BufferedReader br=null;
        try{
            fichero=new File(nombreFichero);
            fr=new FileReader(fichero);br=new BufferedReader(fr);
            String linea;
            while ((linea=br.readLine())!=null){
                v.add(linea);}
        }catch (Exception e){
            e.printStackTrace();}
        finally {
            try {if (null!= fr){fr.close();
                br.close();}
            }catch (Exception e1){
                e1.printStackTrace();}
        }return v;
    }

}