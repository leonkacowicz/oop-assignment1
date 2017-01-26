/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compiladorexpr;

/**
 *
 * @author Leon
 */
public class SaltoComEnderecoNaoDeterminado extends Exception {

    
    public String toString()
    {
        return "Comando de salto condicional com endereço de destino não determinado.";
    }
}
