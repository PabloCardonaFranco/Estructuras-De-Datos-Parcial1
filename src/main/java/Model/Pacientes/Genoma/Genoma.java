package Model.Pacientes.Genoma;

import java.util.Objects;

public class Genoma
{
    //Atributos
    private final String genoma_texto;
    private byte UnidadesVacunaA;
    private byte UnidadesSueroB;
    private byte TanquesOxigeno;

    //Getters
    public byte getUnidadesVacunaA() { return  UnidadesVacunaA; }
    public byte getUnidadesSueroB() { return UnidadesSueroB; }
    public byte getTanquesOxigeno() { return TanquesOxigeno; }

    //Constructor
    public Genoma(String genoma)
    {
        genoma_texto = genoma;
        InterpretarGenoma(genoma);
    }

    @Override
    public  String toString()
    {
        return String.format(
                "\nGenoma: %s\n" +
                "\nMedicamentos necesarios:\n" +
                "\n * VacunaA: %d unidades.\n" +
                "\n * SueroB: %d unidades.\n" +
                "\n * %d Tanques de oxigeno.\n",
                genoma_texto, UnidadesVacunaA, UnidadesSueroB, TanquesOxigeno);
    }

    //Equals
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Genoma g = (Genoma) obj;
        return (UnidadesVacunaA == g.UnidadesVacunaA && UnidadesSueroB == g.UnidadesSueroB && TanquesOxigeno == g.TanquesOxigeno);
    }

    //Hashcode
    @Override
    public  int hashCode() { return Objects.hash(UnidadesVacunaA, UnidadesSueroB, TanquesOxigeno); }

    //Metodo privado para interpretar el genoma, se llama desde el constructor.
    private void InterpretarGenoma(String genoma)
    {
        //Los medicamentos necesarios para sanar al paciente comienzan en 0.
        UnidadesVacunaA = 0;
        UnidadesSueroB = 0;
        TanquesOxigeno = 0;

        try
        {
            genoma += '.'; //Agregar un punto al final evita IndexOutOfBoundsException.
            String Secuencia; //Una combinación entre A/B/O  y un número.
            char[] Informacion = genoma.toCharArray(); //Convertir el genoma a un arreglo de caracteres
            int inicio, fin;

            for (int i = 0; i < Informacion.length; i++)
            {
                try
                {
                    //Vacuna
                    if (Informacion[i] == 'A' || Informacion[i] == 'a')
                    {
                        inicio = i + 1;
                        fin = 0;

                        for (int j = inicio; j < Informacion.length; j++)
                        {
                            if (Informacion[j] == 'B' || Informacion[j] == 'b') { fin = j; break; }
                            if (Informacion[j] == 'O' || Informacion[j] == 'o') { fin = j; break; }
                        }

                        if (fin == 0) { fin = Informacion.length - 1; }

                        if (fin <= inicio) { throw new IllegalArgumentException("\nGenoma no valido.\n"); }

                        Secuencia = genoma.substring(inicio, fin);
                        UnidadesVacunaA += Byte.parseByte(Secuencia);

                        //El contador continua desde la siguiente letra
                        i = fin - 1;
                    }

                    //Suero
                    if (Informacion[i] == 'B' || Informacion[i] == 'b')
                    {
                        inicio = i + 1;
                        fin = 0;

                        for (int j = inicio; j < Informacion.length; j++)
                        {
                            if (Informacion[j] == 'A' || Informacion[j] == 'a') { fin = j; break; }
                            if (Informacion[j] == 'O' || Informacion[j] == 'o') { fin = j; break; }
                        }

                        if (fin == 0) { fin = Informacion.length - 1; }

                        if (fin <= inicio) { throw new IllegalArgumentException("\nGenoma no valido.\n"); }

                        Secuencia = genoma.substring(inicio, fin);
                        UnidadesSueroB += Byte.parseByte(Secuencia);

                        //El contador continua desde la siguiente letra
                        i = fin - 1;
                    }

                    //Tanques de Oxígeno
                    if (Informacion[i] == 'O' || Informacion[i] == 'o')
                    {
                        inicio = i + 1;
                        fin = 0;

                        for (int j = inicio; j < Informacion.length; j++)
                        {
                            if (Informacion[j] == 'A' || Informacion[j] == 'a') { fin = j; break; }
                            if (Informacion[j] == 'B' || Informacion[j] == 'b') { fin = j; break; }
                        }

                        if (fin == 0) { fin = Informacion.length - 1; }

                        if (fin <= inicio) { throw new IllegalArgumentException("\nGenoma no valido.\n"); }

                        Secuencia = genoma.substring(inicio, fin);
                        TanquesOxigeno += Byte.parseByte(Secuencia);

                        //El contador continua desde la siguiente letra
                        i = fin - 1;
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }

            if(UnidadesVacunaA == 0 || UnidadesSueroB == 0 || TanquesOxigeno == 0) { throw new IllegalArgumentException("\nGenoma no valido.\n"); }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
