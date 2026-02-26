package Model.Pacientes;

import Model.Pacientes.Enum.TipoPaciente;

public class Militar extends Paciente
{
    public Militar(int id, float nivel_infeccion, float nivel_salud, String genoma)
    {
        super(id, nivel_infeccion, nivel_salud, genoma);
    }

    @Override
    protected TipoPaciente getTipo() { return TipoPaciente.Militar; }

    @Override
    protected float getModificadorInfeccion() { return 1.07f; } //Su nivel de infección incrementa en 7% cada turno

    @Override
    protected float getModificadorSalud() { return 0.9f; } //Su nivel de salud disminuye en 10% cada turno
}
