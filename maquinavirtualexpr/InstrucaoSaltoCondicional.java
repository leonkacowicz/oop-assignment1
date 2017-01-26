package maquinavirtualexpr;

class InstrucaoSaltoCondicional extends Instrucao
{
    protected char chrTipoOperando;
    protected int intIndiceOperando;
    protected int intEnderecoDestino;

    public void Executa() throws InstrucaoInvalida
    {
        double dblOperando;
        switch (chrTipoOperando)
        {
            case 'p':
                dblOperando = mv.getParametro(intIndiceOperando);
                break;
            case 'r':
                dblOperando = mv.getRetorno(intIndiceOperando);
                break;
            case 'v':
                dblOperando = mv.getVariavel(intIndiceOperando);
                break;
            default:
                throw new InstrucaoInvalida();
        }
        
        if (dblOperando > 0)
            mv.setPtrInstrucao(intEnderecoDestino);
        else
            mv.setPtrInstrucao(mv.getPtrInstrucao() + intNumBytes);
    }

    public InstrucaoSaltoCondicional(MaquinaVirtual mv, int Endereco)
    {
        intNumBytes = 10;
        this.mv = mv;
        chrTipoOperando = (char)mv.getByteProg(Endereco + 1);

        intIndiceOperando = (256 + (int)mv.getByteProg(Endereco + 5)) % 256;
        intIndiceOperando <<= 8;
        intIndiceOperando += (256 + (int)mv.getByteProg(Endereco + 4)) % 256;
        intIndiceOperando <<= 8;
        intIndiceOperando += (256 + (int)mv.getByteProg(Endereco + 3)) % 256;
        intIndiceOperando <<= 8;
        intIndiceOperando += (256 + (int)mv.getByteProg(Endereco + 2)) % 256;

        intEnderecoDestino = (256 + (int)mv.getByteProg(Endereco + 9)) % 256;
        intEnderecoDestino <<= 8;
        intEnderecoDestino += (256 + (int)mv.getByteProg(Endereco + 8)) % 256;
        intEnderecoDestino <<= 8;
        intEnderecoDestino += (256 + (int)mv.getByteProg(Endereco + 7)) % 256;
        intEnderecoDestino <<= 8;
        intEnderecoDestino += (256 + (int)mv.getByteProg(Endereco + 6)) % 256;

    }
    
}
