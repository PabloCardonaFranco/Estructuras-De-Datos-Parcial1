package Model.Pacientes;

public class Civil extends Paciente
{
    public Civil(int id, float nivel_infeccion, float nivel_salud, String genoma)
    {
        super(id, nivel_infeccion, nivel_salud, genoma);
        ModificadorInfeccion = 1.1f; //Su nivel de infección incrementa en 10% cada turno.
        ModificadorSalud = 0.85f; //Su nivel de salud disminuye en 15% cada turno.
    }
}
