package logico;

public class Vivienda 
{
	private String id;
	private float costoXnoche;
	
	public Vivienda ( String id, float costoXnoche )
	{
		this.id = id;
		this.costoXnoche = costoXnoche;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getCostoXnoche() {
		return costoXnoche;
	}

	public void setCostoXnoche(float costoXnoche) {
		this.costoXnoche = costoXnoche;
	}
	
}
