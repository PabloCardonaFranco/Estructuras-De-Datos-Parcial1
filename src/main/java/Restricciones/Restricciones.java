package Restricciones;

public class Restricciones
{
    //Estado de los pacientes
    public static final float InfeccionMin = 0f, InfeccionMax = 100f;
    public static final float SaludMin = 0f, SaludMax = 100f;
    public static final float InfeccionInicialMax = 20, DebuffSaludMax = 51; //Modificar a gusto.

    //Estados de la simulación / Estados del ambiente.
    public static final byte TotalPacientes = 20, TotalAtributosGenomicos = 3, VacunaARequeridaMax = 5, SueroBRequeridoMax = 5, TanquesOxigenoRequeridoMax = 4; //Modificar a gusto.
}
