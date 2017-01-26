/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compiladorexpr;

/**
 *
 * @author Leon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        System.out.println("Iniciando compilacao.");
        Compilador c = new Compilador(args[0], args[1]);
        c.Compila();
	System.out.println("Compilação concluída.");	
    }

}
