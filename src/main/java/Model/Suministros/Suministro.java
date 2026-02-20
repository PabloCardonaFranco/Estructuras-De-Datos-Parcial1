package Model.Suministros;

public abstract class Suministro
{
    protected Suministro() { }

    @Override
    public boolean equals(Object obj)
    {
        if(null == obj || getClass() != obj.getClass()) return false;

        return (this == obj);
    }
}
