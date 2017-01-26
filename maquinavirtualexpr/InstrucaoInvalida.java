package maquinavirtualexpr;

public class InstrucaoInvalida extends Exception
{
    public String toString()
    {
        return "Instrução inexistente ou inválida.";
    }
}
