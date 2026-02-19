package GestorArchivo;

import Model.Pacientes.*;
import Restricciones.Restricciones;

import java.io.*;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class FileIO //File Input-Output
{
    private static final String NombreArchivo = "Pacientes.csv";

    public static void Registrar()
    {
        Math.random();
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(NombreArchivo));
            String encabezado = "Tipo de Paciente;Vacuna A;Suero B;Tanques de oxigeno";
            writer.write(encabezado);

            String TipoPaciente, Genoma;
            int NivelInfeccion, NivelSalud;
            int UnidadesVacunaA, UnidadesSueroB, TanquesOxigeno;

            for(int i = 1; i <= Restricciones.TotalPacientes; i++)
            {
                //Asigna aleatoriamente el tipo de paciente así como su nivel de infección y salud iniciales.
                TipoPaciente = "militar"; //Esto es una prueba.
                NivelInfeccion = 1; //Esto es una prueba.
                NivelSalud = 100; //Esto es una prueba.

                //Asigna Aleatoriamente la cantidad de recursos necesarios para curar al paciente y construye su genoma.
                UnidadesVacunaA = 5; //Esto es una prueba.
                UnidadesSueroB = 1;//Esto es una prueba.
                TanquesOxigeno = 1; //Esto es una prueba.
                Genoma = String.format("A%dB%dO%d", UnidadesVacunaA, UnidadesSueroB, TanquesOxigeno);

                //Registra el paciente en el archivo CSV
                writer.write(String.format("%s;%d;%d;%s", TipoPaciente, NivelInfeccion, NivelSalud, Genoma));
            }

        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void Leer()
    {
        Queue<Paciente> filaPacientes = new LinkedList<>();
        Paciente p;
        String TipoPaciente, Genoma;
        int NivelInfeccion, NivelSalud;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader((NombreArchivo)));
            String line;
            String[] InformacionPaciente;

            while ((line = reader.readLine()) != null)
            {
                try
                {
                    InformacionPaciente = line.split(";");
                    TipoPaciente = InformacionPaciente[0].toLowerCase();
                    NivelInfeccion = Integer.parseInt(InformacionPaciente[1]);
                    NivelSalud = Integer.parseInt(InformacionPaciente[2]);
                    Genoma = InformacionPaciente[3];
                }catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
