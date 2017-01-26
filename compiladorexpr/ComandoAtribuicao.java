/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compiladorexpr;

/**
 *
 * @author Leon
 */
class ComandoAtribuicao extends Comando {

    private int intIndiceSaida = -1;
    private int intIndiceOperando1 = -1;
    private int intIndiceOperando2 = -1;

    private char chrTipoSaida = 0;
    private char chrTipoOperando1 = 0;
    private char chrTipoOperando2 = 0;

    private char chrOperacao = 0;

    protected void geraByteCode()
    {
        byteCodes = new byte[18];

        byteCodes[0] = '<';
        byteCodes[6] = (byte)chrOperacao;
        byteCodes[12] = '>';

        byteCodes[1] = (byte)chrTipoOperando1;
        byteCodes[7] = (byte)chrTipoOperando2;
        byteCodes[13] = (byte)chrTipoSaida;

        
        preenche_bytes(intIndiceOperando1, byteCodes, 2);
        preenche_bytes(intIndiceOperando2, byteCodes, 8);
        preenche_bytes(intIndiceSaida, byteCodes, 14);
    }
    public ComandoAtribuicao(String strLinhaOrg)
    {
        intTamanho = 18;
        
        String strLinha = new String(strLinhaOrg);
        // 1. Descobrimos o tipo de saida (para variavel ou para valor de retorno)
        chrTipoSaida = strLinha.charAt(0);
        strLinha = strLinha.substring(2); // retiramos o caracter extraido e '['

        // 2. Descobrimos o indice da saida
        intIndiceSaida = Integer.valueOf(strLinha.substring(0, strLinha.indexOf("]")));

        strLinha = strLinha.substring(strLinha.indexOf("=") + 1); // ja determinada a saida, limpamos que houver antes do '='

        // 3. Descobrimos o tipo do primeiro operando
        chrTipoOperando1 = strLinha.charAt(0);
        
        if (chrTipoOperando1 >= '0' && chrTipoOperando1 <= '9')
        {
            String strNum = "";
            chrTipoOperando1 = 'c';

            while (strLinha.charAt(0) >= '0' && strLinha.charAt(0) <= '9')
            {
                strNum += strLinha.charAt(0);
                strLinha = strLinha.substring(1);
            }
            intIndiceOperando1 = Integer.valueOf(strNum);
        } else {
            strLinha = strLinha.substring(2);
            intIndiceOperando1 = Integer.valueOf(strLinha.substring(0, strLinha.indexOf("]")));
            strLinha = strLinha.substring(strLinha.indexOf("]") + 1);
        }

        // 4. Descobrimos a operacao a ser efetuada
        chrOperacao = strLinha.charAt(0);
        strLinha = strLinha.substring(1);
        
        // 5. Descobrimos o tipo do primeiro operando
        chrTipoOperando2 = strLinha.charAt(0);
        
        if (chrTipoOperando2 >= '0' && chrTipoOperando2 <= '9')
        {
            String strNum = "";
            chrTipoOperando2 = 'c';

            while (strLinha.length() > 0 && strLinha.charAt(0) >= '0' && strLinha.charAt(0) <= '9')
            {
                strNum += strLinha.charAt(0);
                strLinha = strLinha.substring(1);
            }
            intIndiceOperando2 = Integer.valueOf(strNum);
        } else {
            strLinha = strLinha.substring(2);
            intIndiceOperando2 = Integer.valueOf(strLinha.substring(0, strLinha.indexOf("]")));
            strLinha = strLinha.substring(strLinha.indexOf("]") + 1);
        }
    }
        
}
