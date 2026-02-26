package Model.Pacientes;

import Model.Pacientes.Enum.TipoPaciente;

public class Civil extends Paciente
{
    public Civil(int id, float nivel_infeccion, float nivel_salud, String genoma)
    {
        super(id, nivel_infeccion, nivel_salud, genoma);
    }
    @Override
    protected TipoPaciente getTipo() { return TipoPaciente.Civil; }

    @Override
    protected float getModificadorInfeccion() { return 1.1f; } //Su nivel de infección incrementa en 10% cada turno

    @Override
    protected float getModificadorSalud() { return 0.85f; } //Su nivel de salud disminuye en 15% cada turno
}
