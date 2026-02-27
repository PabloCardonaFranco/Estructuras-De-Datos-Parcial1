package Main;

import GestorArchivo.FileIO;
import Parametros.Parametros;
import Model.Silo_Aislamiento.Silo;
import Model.Pacientes.*;
import Model.Suministros.*;

import java.util.Random;

public class Main
{
    private static final Random r = new Random(); //Instancia local de la clase Random.

    public static void main(String[] args)
    {
        //Configuración de estado inicial.
        FileIO.RegistrarPacientes();
        Silo Lazaro = Silo.getInstance();

        System.out.println("This is a Test.");

        for(Paciente p : Lazaro.getFilaPacientes()) System.out.println(p);

        do
        {
            Lazaro.CargaViral += Parametros.IncrementoNatural;
        }while (Lazaro.CargaViral < Parametros.UmbralColapso && ! (Lazaro.getFilaPacientes().isEmpty() && Lazaro.getUCI().isEmpty()) );

        System.out.println(Lazaro);
        System.out.println("\nEjecucion exitosa.");
    }
}
