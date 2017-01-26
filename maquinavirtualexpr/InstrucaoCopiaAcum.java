package maquinavirtualexpr;

class InstrucaoCopiaAcum extends InstrucaoComAcum {

    public void Executa () throws InstrucaoInvalida
    {
        switch (chrTipoOperando)
        {
            case 'r':
                mv.setRetorno(intIndiceOperando, mv.getAcumulador());
                break;
            case 'v':
                mv.setVariavel(intIndiceOperando, mv.getAcumulador());
                break;
            default:
                throw new InstrucaoInvalida();
        }
        mv.setPtrInstrucao(mv.getPtrInstrucao() + intNumBytes);
    }

    public InstrucaoCopiaAcum(MaquinaVirtual mv, int Endereco) throws InstrucaoInvalida
    {
        super(mv, Endereco);
        if (mv.getByteProg(Endereco) != '>') throw new InstrucaoInvalida();
    }
}
