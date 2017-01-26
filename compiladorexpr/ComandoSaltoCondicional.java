/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compiladorexpr;

/**
 *
 * @author Leon
 */
class ComandoSaltoCondicional extends Comando {

    private char chrTipoArgumento = 0;
    private int intIndiceArgumento = -1;
    private int intNumLinhaDestino = -1;
    private int intEnderecoDestino = -1;
    
    
    protected void geraByteCode() throws SaltoComEnderecoNaoDeterminado
    {
        if (intEnderecoDestino == -1) throw new SaltoComEnderecoNaoDeterminado();
        
        byteCodes = new byte [10];
        byteCodes[0] = '?';
        byteCodes[1] = (byte)chrTipoArgumento;
        preenche_bytes(intIndiceArgumento, byteCodes, 2);
        preenche_bytes(intEnderecoDestino, byteCodes, 6);
    }
    public ComandoSaltoCondicional(String strLinhaOrg)
    {
        intTamanho = 10;
        
        String strLinha = new String(strLinhaOrg);

        strLinha = strLinha.substring(1); // retiramos o '?' do comeco da string

        chrTipoArgumento = strLinha.charAt(0);
        strLinha = strLinha.substring(2);
        
        intIndiceArgumento = Integer.valueOf(strLinha.substring(0, strLinha.indexOf("]")));

        strLinha = strLinha.substring(strLinha.indexOf("]") + 1);
        
        intNumLinhaDestino = Integer.valueOf(strLinha);
    }

    public int getLinhaDestino()
    {
        return intNumLinhaDestino;
    }
    public void setEnderecoDestino(int NovoEndereco)
    {
        intEnderecoDestino = NovoEndereco;
    }
}
