package maquinavirtualexpr;

class InstrucaoCarregaAcum extends InstrucaoComAcum
{
    public void Executa () throws InstrucaoInvalida
    {
        switch (chrTipoOperando)
        {
            case 'p':
                mv.setAcumulador(mv.getParametro(intIndiceOperando));
                break;
            case 'r':
                mv.setAcumulador(mv.getRetorno(intIndiceOperando));
                break;
            case 'v':
                mv.setAcumulador(mv.getVariavel(intIndiceOperando));
                break;
            case 'c':
                mv.setAcumulador((double)intIndiceOperando);
                break;
            default:
                throw new InstrucaoInvalida();
        }
        mv.setPtrInstrucao(mv.getPtrInstrucao() + intNumBytes);
    }

    public InstrucaoCarregaAcum(MaquinaVirtual mv, int Endereco) throws InstrucaoInvalida
    {        
        super(mv, Endereco);
        if (mv.getByteProg(Endereco) != '<') throw new InstrucaoInvalida();
    }
}
