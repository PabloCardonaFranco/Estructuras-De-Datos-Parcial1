package Model.Pacientes;

import Model.Interface.IInfeccion;
import Model.Pacientes.Enum.TipoPaciente;
import Parametros.Parametros;

import java.util.Objects;

public abstract class Paciente implements IInfeccion
{
    //Atributos
    private int Id;
    private float NivelInfeccion;
    private float NivelSalud;
    private String Genoma;

    //Recursos Requeridos para curarse:
    private byte UnidadesRequeridasVacunaA;
    private byte UnidadesRequeridasSueroB;
    private byte TanquesOxigenoRequeridos;

    //Esto funciona pero no es correcto
    protected TipoPaciente Tipo;
    protected float ModificadorInfeccion;
    protected float ModificadorSalud;

    //Constructor
    protected Paciente(int id, float nivel_infeccion, float nivel_salud, String genoma)
    {
        Id = id;
        NivelInfeccion = nivel_infeccion;
        NivelSalud = nivel_salud;
        Genoma = genoma;

        InterpretarGenoma();
    }

    //Getters

    //Atributos
    public int getId() { return Id; }

    public float getNivelInfeccion() {return NivelInfeccion; }

    public float getNivelSalud() { return NivelSalud; }

    public String getGenoma() { return Genoma; }

    //Recursos necesarios para curarse
    public byte getUnidadesRequeridasVacunaA() { return UnidadesRequeridasVacunaA; }

    public byte getUnidadesRequeridasSueroB() { return UnidadesRequeridasSueroB; }

    public byte getTanquesOxigenoRequeridos() { return TanquesOxigenoRequeridos; }

    //Implementación de la Interfaz
    @Override
    public void IncrementarInfeccion()
    {
        if(NivelInfeccion < Parametros.InfeccionMax)
        {
            if(NivelInfeccion * ModificadorInfeccion > Parametros.InfeccionMax) NivelInfeccion = Parametros.InfeccionMax; //Alcanza el máximo nivel de infección.

            NivelInfeccion *= ModificadorInfeccion;
        }
    }

    @Override
    public void DisminuirSalud()
    {
        if(NivelSalud > Parametros.SaludMin)
        {
            if(NivelSalud * ModificadorSalud < Parametros.SaludMin) NivelSalud = Parametros.SaludMin; //La salud cae a 0. (el paciente muere)

            NivelSalud *= ModificadorSalud;
        }
    }

    private void InterpretarGenoma()
    {
        try
        {
            String[] InformacionGenomica = Genoma.split("-"); //Necesita correccion.

            if((InformacionGenomica.length < Parametros.TotalAtributosGenomicos)) throw new IllegalArgumentException("Genoma no valido.");

            UnidadesRequeridasVacunaA = Byte.parseByte(InformacionGenomica[0]);
            UnidadesRequeridasSueroB = Byte.parseByte(InformacionGenomica[1]);
            TanquesOxigenoRequeridos = Byte.parseByte(InformacionGenomica[2]);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //ToString
    @Override
    public String toString()
    {
        return String.format(
                "\nPaciente #%d\n" +
                "\nTipo de Paciente: %s\n" +
                "\nNivel de Infeccion: %.2f\n" +
                "\nNivel de Salud: %.2f\n" +
                "\nGenoma: %s\n" +
                "\nMedicamentos necesarios para curarse:\n" +
                        "\n * Vacunas A: %d unidades\n" +
                        "\n * Suero B: %d unidades\n" +
                        "\n * Tanques de Oxigeno: %d unidades\n",
        Id, Tipo, NivelInfeccion, NivelSalud, Genoma, UnidadesRequeridasVacunaA, UnidadesRequeridasSueroB, TanquesOxigenoRequeridos);
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
