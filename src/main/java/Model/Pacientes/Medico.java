package Model.Pacientes;

public class Medico extends Paciente
{
    public Medico(int id, float nivel_infeccion, float nivel_salud, String genoma)
    {
        super(id, nivel_infeccion, nivel_salud, genoma);
        ModificadorInfeccion = 1.2f; //Su nivel de infección incrementa en 20% cada turno.
        ModificadorSalud = 0.7f; //Su nivel de salud disminuye en 30% cada turno.
    }
}
