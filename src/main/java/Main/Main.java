package Main;

import GestorArchivo.FileIO;
import Model.Pacientes.*;

import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import Model.Suministros.SueroB;
import Model.Suministros.Suministro;
import Model.Suministros.TanqueOxigeno;
import Model.Suministros.VacunaA;
import Parametros.Parametros;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class Main
{
    private static final Random r = new Random(); //Instancia local de la clase Random.

    public static void main(String[] args)
    {
        //Configuración de estado inicial.
        int CargaViral = Parametros.CargaInicial;

        FileIO.RegistrarPacientes();
        Queue<Paciente> FilaPacientes = FileIO.ConsultarRegistroPacientes();
        Stack<Paciente> UCI = new Stack<>();
        Bag<Suministro> Suministros = new HashBag<>();
        //Instancias de prueba para la simulación, no tienen atributos y solo implementan los métodos básicos.
        VacunaA vacuna = new VacunaA();
        SueroB suero = new SueroB();
        TanqueOxigeno pipeta = new TanqueOxigeno();

        System.out.println("This is a Test.");

        for(Paciente p : FilaPacientes) System.out.println(p);

        do
        {
            CargaViral += Parametros.IncrementoNatural;
        }while (CargaViral < Parametros.UmbralColapso && ! FilaPacientes.isEmpty() && ! UCI.empty());

        System.out.println("\nEjecucion exitosa.");
    }
}
