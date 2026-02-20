package Parametros;

public class Parametros //Parametros de estado global para la simulacion. Modificar a gusto.
{
    //Estado de los pacientes:

    //Nivel de Infeccion minimo y maximo.
    public static final float InfeccionMin = 0f;
    public static final float InfeccionMax = 100f;

    //Nivel de Salud minimo y maximo
    public static final float SaludMin = 0f;
    public static final float SaludMax = 100f;

    //Modificadores de estado inicial
    public static final float InfeccionInicialMax = 20f; //Los pacientes comienzan con un nivel de infección entre 0 y este valor.
    public static final float DebuffSaludMax = 51f; //Los pacientes sufren una pérdida de salud inicial entre 0  y este valor.

    //Modificadores de los recursos requeridos para sanar a los pacientes.
    public static final byte TotalAtributosGenomicos = 3; //Esto solo sirve para validar la lectura del genoma.
    public static final byte VacunaARequeridaMax = 5; //Los pacientes necesitan entre 1 y este valor unidades de la VacunaA para sanarse.
    public static final byte SueroBRequeridoMax = 5; //Los pacientes necesitan entre 1 y este valor unidades de SureoB para sanarse.
    public static final byte TanquesOxigenoRequeridoMax = 4; //Los pacientes necesitan entre 1 y este valor de tanques de oxigeno para sanarse.

    //Estados de la simulación / Estados del ambiente.
    public static final byte TotalPacientes = 5; //20 El Gestor de Archivo (File Input-Output) registra automáticamente esta cantidad de pacientes al comenzar la simulación.
    public static final int CargaInicial = 500; //Carga viral al comienzo de la simulación.
    public static final int UmbralColapso = 1200; //Carga viral máxima permitida antes de que el silo colapse.
    public static final byte IncrementoNatural = 20; //La carga viral de la simulación incrementa en 20 unidades en cada turno.
    public static final byte DebuffInfeccionMax = 70; //La carga viral incrementa en 70 unidades/paciente que haya alcanzado el máximo nivel de infeccion en cada turno.
}
