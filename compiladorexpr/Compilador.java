
package compiladorexpr;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;


class Compilador
{
	String strArquivoEntrada, strArquivoSaida;
	Comando [] cmdComandos;
    LinkedList<String> lstLinhasCodigo;


    private void CarregaLinhasCodigo() throws IOException
    {
        String strLinha;
        FileInputStream objFIS = new FileInputStream(strArquivoEntrada);
        BufferedInputStream objBIS = new BufferedInputStream(objFIS);
        BufferedReader objBR = new BufferedReader(new InputStreamReader(objBIS));

        lstLinhasCodigo = new LinkedList<String>();
        
        while (objBR.ready())
        {
            strLinha = objBR.readLine();

            // Caso seja um comentario, procuramos a proxima linha que nao seja um comentario
            while (objBR.ready() && strLinha.startsWith("#")) strLinha = objBR.readLine();

            lstLinhasCodigo.addLast(strLinha);
        }
        objBR.close();
    }
    private void ParseComandos () throws LinhaDeCodigoInvalida
    {
        int intEnderecoAtual = 0;
        cmdComandos = new Comando[lstLinhasCodigo.size()];

        int i = 0;
        while (lstLinhasCodigo.size() > 0)
        {
            String strLinha = lstLinhasCodigo.poll();
            cmdComandos[i] = Comando.NovoComando(strLinha);
            cmdComandos[i].setEndereco(intEnderecoAtual);
            intEnderecoAtual += cmdComandos[i].getNumBytesInstrucoes();

            i++;
        }

        for (i = 0; i < cmdComandos.length; i++)
        {
            if (cmdComandos[i] instanceof ComandoSaltoCondicional)
            {
                ComandoSaltoCondicional csc = (ComandoSaltoCondicional)cmdComandos[i];
                csc.setEnderecoDestino(cmdComandos[csc.getLinhaDestino() - 1].getEndereco());
            }
        }

    }
    private void GeraArquivoSaida() throws IOException, SaltoComEnderecoNaoDeterminado
    {
        FileOutputStream objFOS = new FileOutputStream(strArquivoSaida);

        for (int i = 0; i < cmdComandos.length; i++)
        {
            objFOS.write(cmdComandos[i].getByteCodes());
        }

        objFOS.close();

    }
    
	public Compilador (String strArquivoEntrada, String strArquivoSaida)
	{
		this.strArquivoEntrada = strArquivoEntrada;
		this.strArquivoSaida = strArquivoSaida;
	}
    public void Compila() throws IOException, LinhaDeCodigoInvalida, SaltoComEnderecoNaoDeterminado
    {
        CarregaLinhasCodigo();
        ParseComandos();
        GeraArquivoSaida();
    }
}

