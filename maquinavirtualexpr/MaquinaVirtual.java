/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package maquinavirtualexpr;

import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 *
 * @author Leon
 */
public class MaquinaVirtual {

    public static final int TAMANHO_VETORES = 100;
    private double [] vetParametros = new double [TAMANHO_VETORES];
    private double [] vetVariaveis = new double [TAMANHO_VETORES];
    private double [] vetRetornos = new double [TAMANHO_VETORES];
    private boolean [] vetRetornosAlterados = new boolean[TAMANHO_VETORES];

    private byte [] bcPrograma;

    private int intPtrInstrucao;
    
    private double dblAcumulador;

    public void CarregaPrograma(String strNomeArquivo) throws IOException
    {
        FileInputStream objFIS = new FileInputStream(strNomeArquivo);
        BufferedInputStream objBIS = new BufferedInputStream(objFIS);

        bcPrograma = new byte[objBIS.available()];
        objBIS.read(bcPrograma);
        
    }

    public MaquinaVirtual(double [] Parametros)
    {
        for (int i = 0; i < Math.min(TAMANHO_VETORES, Parametros.length); i++)
            vetParametros[i] = Parametros[i];

        for (int i = 0; i < TAMANHO_VETORES; i++)
            vetRetornosAlterados[i] = false;
        
    }

    public MaquinaVirtual(MaquinaVirtual mv)
    {
        for (int i = 0; i < TAMANHO_VETORES; i++)
        {
            vetParametros[i] = mv.getRetorno(i);
            vetRetornosAlterados[i] = false;
        }
    }

    public void ExecutaPrograma()
    {
        intPtrInstrucao = 0;
        while (intPtrInstrucao < bcPrograma.length)
        {
            try
            {
                ImprimeStatus();
                Instrucao.CriaInstrucao(this, intPtrInstrucao).Executa();
            } catch (InstrucaoInvalida e) {
                System.out.println("Instrucao invalida encontrada no endereço " + String.valueOf(intPtrInstrucao));
                return;
            }
        }

    }
    public void setAcumulador(double Acumulador) { this.dblAcumulador = Acumulador; }
    public void setPtrInstrucao(int PtrInstrucao) { this.intPtrInstrucao = PtrInstrucao; }
    public void setVariavel(int Indice, double NovoValor) { vetVariaveis[Indice] = NovoValor; }
    public void setRetorno(int Indice, double NovoValor) 
    {
        vetRetornos[Indice] = NovoValor;
        vetRetornosAlterados[Indice] = true;
    }


    public double getVariavel(int Indice) { return vetVariaveis[Indice]; }
    public double getRetorno(int Indice) { return vetRetornos[Indice]; }
    public double getParametro(int Indice) { return vetParametros[Indice]; }
    public double getAcumulador() { return this.dblAcumulador; }
    public int getPtrInstrucao() { return this.intPtrInstrucao; }

    public byte getByteProg(int Endereco) { return bcPrograma[Endereco]; }

    public void ImprimeValoresRetorno()
    {
	
        for (int i = 0; i < TAMANHO_VETORES; i++)
            if (vetRetornosAlterados[i]) 
	    {
		System.out.print("r[");
		System.out.print(i);
		System.out.print("] = ");
		System.out.println(vetRetornos[i]);
	    }
    }
    private void ImprimeStatus()
    {
	/*
        System.out.println(
            String.format(
                "IP = %2d | ACC = %f | v[0] = %f | v[1] = %f | v[2] = %f | r[0] = %f | r[1] = %f",
                intPtrInstrucao, dblAcumulador, vetVariaveis[0],
                vetVariaveis[1], vetVariaveis[2], vetRetornos[0], vetRetornos[1]
            )
        );
	*/
    }
}
