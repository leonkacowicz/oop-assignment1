package maquinavirtualexpr;

class InstrucaoSomaAcum extends InstrucaoOperaAcum {

    protected double Opera(double Acumulador, double Operando)
    {
        return Acumulador + Operando;
    }
    public InstrucaoSomaAcum(MaquinaVirtual mv, int Endereco) throws InstrucaoInvalida
    {
        super(mv, Endereco);
        if (mv.getByteProg(Endereco) != '+') throw new InstrucaoInvalida();
    }
}
