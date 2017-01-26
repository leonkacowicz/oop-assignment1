package compiladorexpr;

abstract class Comando
{
	byte [] byteCodes = null;
	private int intEndereco = -1;
    protected int intTamanho;
    
    protected abstract void geraByteCode() throws SaltoComEnderecoNaoDeterminado;
    public int getNumBytesInstrucoes()
    {
        return intTamanho;
    }

    public byte [] getByteCodes() throws SaltoComEnderecoNaoDeterminado
    {
        if (byteCodes == null) geraByteCode();
        return byteCodes;
    }
    protected static void preenche_bytes(int i, byte [] b, int offset)
    {
        b[offset + 3] = (byte)(i >> 24);
		b[offset + 2] = (byte)((i << 8) >> 24);
		b[offset + 1] = (byte)((i << 16) >> 24);
		b[offset + 0] = (byte)((i << 24) >> 24);
    }
    public static Comando NovoComando(String strLinhaCodigo) throws LinhaDeCodigoInvalida
    {
        char chrPrimeiro = strLinhaCodigo.charAt(0);
        if (chrPrimeiro == '?')
        {
            return new ComandoSaltoCondicional(strLinhaCodigo);
        } else if (chrPrimeiro == 'p' || chrPrimeiro == 'r' || chrPrimeiro == 'v') {
            return new ComandoAtribuicao(strLinhaCodigo);
        } else {
            throw new LinhaDeCodigoInvalida();
        }

    }

    public void setEndereco(int NovoEndereco) { intEndereco = NovoEndereco; }
    public int getEndereco() { return intEndereco; }
    
}

