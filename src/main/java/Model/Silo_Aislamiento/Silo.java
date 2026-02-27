package Model.Silo_Aislamiento;

import Model.Pacientes.*;
import Model.Suministros.*;
import GestorArchivo.FileIO;
import Parametros.Parametros;

//Collections
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class Silo
{
    //Instancia
    private static Silo instance;

    //Instancias de prueba para la simulación.
    private final VacunaA Vacuna;
    private final SueroB Suero;
    private final TanqueOxigeno Pipeta;

    //Recursos del Silo
    private Bag<Suministro> Suministros;

    //Pacientes del silo
    private Queue<Paciente> FilaPacientes;
    private Queue<Paciente> UCI;

    //Atributo de estado
    public int CargaViral;

    //Getters
    public Queue<Paciente> getFilaPacientes() {return  FilaPacientes; }
    public Queue<Paciente> getUCI() {return UCI; }

    //Singleton
    public static Silo getInstance()
    {
        if (instance == null) { return  new Silo(); }

        return instance;
    }

    private Silo()
    {
        CargaViral = Parametros.CargaInicial;
        FilaPacientes = FileIO.ConsultarRegistroPacientes();
        UCI = new LinkedList<>();
        Suministros = new HashBag<>();
        Vacuna = new VacunaA();
        Suero = new SueroB();
        Pipeta = new TanqueOxigeno();
    }

    //Metodos de la clase
    public void SanarPaciente(Paciente paciente)
    {
        //Se consumen los medicamentos necesarios para sanar al paciente.
        ConsumirSuministros(
                paciente.getGenoma().getUnidadesVacunaA(),
                paciente.getGenoma().getUnidadesSueroB(),
                paciente.getGenoma().getTanquesOxigeno()
        );

        //Probar si el paciente se encuentra en la fila
        if (FilaPacientes.contains(paciente)) { FilaPacientes.remove(paciente); }

        //Sino esta en fila, esta en la UCI
        UCI.remove(paciente);
    }

    public void AumentarSuministros(int CantidadVacunas, int CantidadSuero, int TotalTanquesOxigeno)
    {
        Suministros.add(Vacuna, CantidadVacunas);
        Suministros.add(Suero, CantidadSuero);
        Suministros.add(Pipeta, TotalTanquesOxigeno);
    }

    private void ConsumirSuministros(int CantidadVacunas, int CantidadSuero, int TotalTanquesOxigeno)
    {
        Suministros.remove(Vacuna, CantidadVacunas);
        Suministros.remove(Suero, CantidadSuero);
        Suministros.remove(Pipeta, TotalTanquesOxigeno);
    }

    //$ToString
    @Override
    public String toString()
    {
        return String.format(
                "\nSilo de Aislamiento\n" +
                "\nPacientes en fila: %d.\n" +
                "\nPacientes en UCI: %d.\n" +
                "\nSuministros Disponibles:\n" +
                        "\n * Vacunas A: %d unidades.\n" +
                        "\n * Suero B: %d unidades.\n" +
                        "\n * %d Tanques de oxigeno.",
                FilaPacientes.size(), UCI.size(),
                Suministros.getCount(Vacuna), Suministros.getCount(Suero), Suministros.getCount(Pipeta));
    }
}
