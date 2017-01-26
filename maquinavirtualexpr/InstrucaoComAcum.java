package maquinavirtualexpr;

abstract class InstrucaoComAcum extends Instrucao
{
    protected char chrTipoOperando;
    protected int intIndiceOperando;
    
    public InstrucaoComAcum(MaquinaVirtual mv, int Endereco) throws InstrucaoInvalida
    {
        intNumBytes = 6;
        this.mv = mv;
        chrTipoOperando = (char)mv.getByteProg(Endereco + 1);

        intIndiceOperando = (256 + (int)mv.getByteProg(Endereco + 5)) % 256;
        intIndiceOperando <<= 8;
        intIndiceOperando += (256 + (int)mv.getByteProg(Endereco + 4)) % 256;
        intIndiceOperando <<= 8;
        intIndiceOperando += (256 + (int)mv.getByteProg(Endereco + 3)) % 256;
        intIndiceOperando <<= 8;
        intIndiceOperando += (256 + (int)mv.getByteProg(Endereco + 2)) % 256;
    }
}
