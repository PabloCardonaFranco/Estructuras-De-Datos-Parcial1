package Model.Pacientes;

import Model.Pacientes.Enum.TipoPaciente;

public class Medico extends Paciente
{
    public Medico(int id, float nivel_infeccion, float nivel_salud, String genoma)
    {
        super(id, nivel_infeccion, nivel_salud, genoma);
    }

    @Override
    protected TipoPaciente getTipo() { return TipoPaciente.Medico; }

    @Override
    protected float getModificadorInfeccion() { return 1.2f; } //Su nivel de infeccion incrementa en 20% cada turno

    @Override
    protected float getModificadorSalud() { return 0.7f; } //Su nivel de salud disminuye en 30% cada turno
}
