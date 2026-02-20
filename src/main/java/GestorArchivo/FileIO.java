package GestorArchivo;

import Model.Pacientes.*;
import Parametros.Parametros;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class FileIO //File Input-Output
{
    private static final String NombreArchivo = "Pacientes.csv";
    private static final Random r = new Random();

    public static void RegistrarPacientes()
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(NombreArchivo));
            String encabezado = "Tipo-de-Paciente;Id;Nivel-Infeccion;Nivel-Salud;Genoma";
            writer.write(encabezado);

            int Id;
            String TipoPaciente, Genoma;
            float NivelInfeccion, NivelSalud;
            int UnidadesVacunaA, UnidadesSueroB, TanquesOxigeno;

            for(int i = 1; i <= Parametros.TotalPacientes; i++)
            {
                //Asigna aleatoriamente el tipo de paciente así como su nivel de infección y salud iniciales.
                TipoPaciente = switch (r.nextInt(3))
                {
                    case 0 -> "civil";
                    case 1 -> "militar";
                    case 2 -> "medico";
                    default -> "";
                };

                Id = i; //Esto es poco práctico.
                NivelInfeccion = r.nextFloat(Parametros.InfeccionInicialMax) + 1; //El nivel de Infección debería comenzar entre 1 y 20.
                NivelSalud = Parametros.SaludMax - r.nextFloat(Parametros.DebuffSaludMax); //El nivel de salud debería comenzar en entr 50 y 100.

                //Asigna Aleatoriamente la cantidad de recursos necesarios para curar al paciente y construye su genoma.
                UnidadesVacunaA =  r.nextInt(Parametros.VacunaARequeridaMax) + 1; //Entre 1 y 5.
                UnidadesSueroB = r.nextInt(Parametros.SueroBRequeridoMax) + 1;//Entre 1 y 5.
                TanquesOxigeno = r.nextInt(Parametros.TanquesOxigenoRequeridoMax) + 1; //Entre 1 y 4.
                Genoma = String.format("A%d-B%d-O%d", UnidadesVacunaA, UnidadesSueroB, TanquesOxigeno);

                //Registra el paciente en el archivo CSV
                writer.write(String.format("\n%s;%d;%f;%f;%s", TipoPaciente, Id, NivelInfeccion, NivelSalud, Genoma));
            }

            writer.close();
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static Queue<Paciente> ConsultarRegistroPacientes()
    {
        Queue<Paciente> FilaPacientes = new LinkedList<>(); Paciente p;

        int Id;
        String TipoPaciente, Genoma;
        float NivelInfeccion, NivelSalud;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader((NombreArchivo)));
            reader.readLine(); //Lee y omite el encabezado.

            String line;
            String[] InformacionPaciente;

            while ((line = reader.readLine()) != null)
            {
                try
                {
                    InformacionPaciente = line.split(";");
                    TipoPaciente = InformacionPaciente[0];
                    Id = Integer.parseInt(InformacionPaciente[1]);
                    NivelInfeccion = Float.parseFloat(InformacionPaciente[2]);
                    NivelSalud = Float.parseFloat(InformacionPaciente[3]);
                    Genoma = InformacionPaciente[4];

                    p = switch (TipoPaciente)
                    {
                        case "medico" -> new Medico(Id, NivelInfeccion, NivelSalud, Genoma);
                        case "militar" -> new Militar(Id, NivelInfeccion, NivelSalud, Genoma);
                        case "civil" -> new Civil(Id, NivelInfeccion, NivelSalud, Genoma);
                        default -> throw new IllegalArgumentException("Tipo de Paciente no reconocido: " + TipoPaciente);
                    };

                    FilaPacientes.add(p);
                }catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }

            reader.close();
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return FilaPacientes;
    }
}
