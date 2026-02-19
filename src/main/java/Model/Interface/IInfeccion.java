package Model.Interface;

public interface IInfeccion
{
    void IncrementarInfeccion(); //Los pacientes incrementan su nivel de infección con cada turno.
    void DisminuirSalud(); //Los pacientes pierden salud con cada turno
    private void InterpretarGenoma() {} //Los pacientes pueden curarse según su genoma particular.
}
