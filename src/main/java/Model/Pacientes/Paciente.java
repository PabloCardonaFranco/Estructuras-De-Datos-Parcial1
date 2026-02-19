package Model.Pacientes;

import Model.Interface.IInfeccion;
import Restricciones.Restricciones;

import java.util.Objects;

public abstract class Paciente implements IInfeccion
{
    //Atributos
    protected int Id;
    protected float NivelInfeccion;
    protected float NivelSalud;
    protected String Genoma;
    protected float ModificadorInfeccion; //Esto funciona pero no es 100% correcto.
    protected float ModificadorSalud; //Esto funciona pero no es 100% correcto.

    //Constructor
    protected Paciente(int id, float nivel_infeccion, float nivel_salud, String genoma)
    {
        Id = id;
        NivelInfeccion = nivel_infeccion;
        NivelSalud = nivel_salud;
        Genoma = genoma;
    }

    //Getters
    public int getId() { return Id; }

    public float getNivelInfeccion() {return NivelInfeccion; }

    public float getNivelSalud() { return NivelSalud; }

    public String getGenoma() { return Genoma; }

    //Implementación de la Interfaz
    @Override
    public void IncrementarInfeccion()
    {
        if(NivelInfeccion < Restricciones.InfeccionMax)
        {
            if(NivelInfeccion * ModificadorInfeccion > Restricciones.InfeccionMax) NivelInfeccion = Restricciones.InfeccionMax; //Alcanza el máximo nivel de infección.

            NivelInfeccion *= ModificadorInfeccion;
        }
    }

    @Override
    public void DisminuirSalud()
    {
        if(NivelSalud > Restricciones.SaludMin)
        {
            if(NivelSalud * ModificadorSalud < Restricciones.SaludMin) NivelSalud = Restricciones.SaludMin; //La salud cae a 0. (el paciente muere)

            NivelSalud *= ModificadorSalud;
        }
    }

    //ToString
    @Override
    public String toString()
    {
        return String.format("\nId: %d.\n\nNivel de Infeccion: %.2f.\n\nNivel de Salud: %.2f.\n\nGenoma: %s.\n", Id, NivelInfeccion, NivelSalud, Genoma);
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
