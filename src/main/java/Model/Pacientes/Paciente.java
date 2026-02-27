package Model.Pacientes;

import Model.Interface.IInfeccion;
import Model.Pacientes.Enum.TipoPaciente;
import Model.Pacientes.Genoma.Genoma;
import Parametros.Parametros;

import java.util.Objects;

public abstract class Paciente implements IInfeccion
{
    //Atributos
    private final int Id;
    private float NivelInfeccion;
    private float NivelSalud;
    private final Genoma Genoma;

    //Getters
    public int getId() { return Id; }

    public float getNivelInfeccion() {return NivelInfeccion; }

    public float getNivelSalud() { return NivelSalud; }

    public Genoma getGenoma() { return Genoma; }

    //Esto funciona y es correcto
    protected abstract TipoPaciente getTipo();

    protected abstract float getModificadorInfeccion(); // > 1

    protected abstract float getModificadorSalud(); // 0 < && < 1

    //Constructor
    protected Paciente(int id, float nivel_infeccion, float nivel_salud, String genoma)
    {
        Id = id;
        NivelInfeccion = nivel_infeccion;
        NivelSalud = nivel_salud;
        Genoma = new Genoma(genoma);
    }

    //Implementación de la Interfaz
    @Override
    public void IncrementarInfeccion()
    {
        if(NivelInfeccion < Parametros.InfeccionMax)
        {
            if(NivelInfeccion * getModificadorInfeccion() > Parametros.InfeccionMax) NivelInfeccion = Parametros.InfeccionMax; //Alcanza el máximo nivel de infección.

            NivelInfeccion *= getModificadorInfeccion();
        }
    }

    //Implementacion de la Interfaz
    @Override
    public void DisminuirSalud()
    {
        if(NivelSalud > Parametros.SaludMin)
        {
            if(NivelSalud * getModificadorSalud() < Parametros.SaludMin) NivelSalud = Parametros.SaludMin; //La salud cae a 0. (el paciente muere)

            NivelSalud *= getModificadorSalud();
        }
    }

    //$ToString
    @Override
    public String toString()
    {
        return String.format(
                "\nPaciente #%d\n" +
                "\nTipo de Paciente: %s\n" +
                "\nNivel de Infeccion: %.2f\n" +
                "\nNivel de Salud: %.2f\n" +
                "%s", //ToString method of Genoma class shown here
                Id, getTipo(), NivelInfeccion, NivelSalud, Genoma);
    }

    //Equals
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Paciente p = (Paciente) obj;

        return (Id == p.Id && NivelInfeccion == p.NivelInfeccion && NivelSalud == p.NivelSalud && Genoma.equals(p.Genoma));
    }

    //Hashcode
    @Override
    public  int hashCode() { return Objects.hash(Id, NivelInfeccion, NivelSalud, Genoma); }
}
