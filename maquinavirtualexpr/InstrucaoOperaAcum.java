package maquinavirtualexpr;

abstract class InstrucaoOperaAcum extends InstrucaoComAcum {

    abstract protected double Opera(double Acumulador, double Operando);

    public void Executa () throws InstrucaoInvalida
    {
        switch (chrTipoOperando)
        {
            case 'p':
                mv.setAcumulador(Opera(mv.getAcumulador(), mv.getParametro(intIndiceOperando)));
                break;
            case 'r':
                mv.setAcumulador(Opera(mv.getAcumulador(), mv.getRetorno(intIndiceOperando)));
                break;
            case 'v':
                mv.setAcumulador(Opera(mv.getAcumulador(), mv.getVariavel(intIndiceOperando)));
                break;
            case 'c':
                mv.setAcumulador(Opera(mv.getAcumulador(), (double)intIndiceOperando));
                break;
            default:
                throw new InstrucaoInvalida();
        }
        mv.setPtrInstrucao(mv.getPtrInstrucao() + intNumBytes);
    }
    public InstrucaoOperaAcum(MaquinaVirtual mv, int Endereco) throws InstrucaoInvalida
    {
        super(mv, Endereco);
    }
}
