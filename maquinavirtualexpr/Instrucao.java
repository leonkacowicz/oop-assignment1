package maquinavirtualexpr;

abstract class Instrucao {

    protected MaquinaVirtual mv;
    protected int intNumBytes;
    public abstract void Executa () throws InstrucaoInvalida;
    public int getTamanho() { return intNumBytes; }


    public static Instrucao CriaInstrucao (MaquinaVirtual mv, int Endereco) throws InstrucaoInvalida
    {
        switch (mv.getByteProg(Endereco))
        {
            case '<':
                return new InstrucaoCarregaAcum(mv, Endereco);
            case '>':
                return new InstrucaoCopiaAcum(mv, Endereco);
            case '?':
                return new InstrucaoSaltoCondicional(mv, Endereco);
            case '+':
                return new InstrucaoSomaAcum(mv, Endereco);
            case '-':
                return new InstrucaoSubtraiAcum(mv, Endereco);
            case '*':
                return new InstrucaoMultiplicaAcum(mv, Endereco);
            case '/':
                return new InstrucaoDivideAcum(mv, Endereco);
            default:
                throw new InstrucaoInvalida();
        }
    }

}
