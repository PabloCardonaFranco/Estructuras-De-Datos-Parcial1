package Main;

import GestorArchivo.FileIO;
import Model.Pacientes.*;

import java.util.Queue;

public class Main
{
    public static void main(String[] args)
    {
        FileIO.RegistrarPacientes();
        Queue<Paciente> FilaPacientes = FileIO.ConsultarRegistroPacientes();

        System.out.println("This is a Test.");

        for(Paciente p : FilaPacientes) System.out.println(p);

        System.out.println("\nEjecucion exitosa.");
    }
}
