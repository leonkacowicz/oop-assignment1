/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package maquinavirtualexpr;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Leon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        MaquinaVirtual mv;
        Scanner s = new Scanner(System.in);
        
        int intNumParametros;

        System.out.print("Digite o número de parâmetros do primeiro programa da seqüência: ");
        intNumParametros = s.nextInt();
        double [] vetParametros = new double [intNumParametros];
        for (int i = 1; i <= intNumParametros; i++)
        {
            System.out.print("Digite o valor do ");
	    System.out.print(i);
	    System.out.print("o. parâmetro: ");
            vetParametros[i - 1] = s.nextDouble();
        }

        mv = new MaquinaVirtual(vetParametros);
        mv.CarregaPrograma(args[0]);
        mv.ExecutaPrograma();

        for (int i = 1; i < args.length; i++)
        {
		vetParametros = new double[MaquinaVirtual.TAMANHO_VETORES];
		for (int j = 0; j < MaquinaVirtual.TAMANHO_VETORES; j++)
			vetParametros[j] = mv.getRetorno(j);	
        	mv = new MaquinaVirtual(vetParametros);
        	mv.CarregaPrograma(args[i]);
		mv.ExecutaPrograma();

        }
        
        mv.ImprimeValoresRetorno();

        
    }

}
