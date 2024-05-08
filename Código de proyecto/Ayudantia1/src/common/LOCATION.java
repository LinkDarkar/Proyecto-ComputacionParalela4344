package common;

public enum LOCATION
{
	CHILE (0),
	EUROPE (1),
	BRAZIL (2),
	ARGENTINA (3),
	URUGUAY (4),
	US (5);

	public final int value;

	private LOCATION (int value)
	{
		this.value = value;
	}
}
