package common;

public enum LOCATION
{
	CHILE (0),
	UNITED_STATES (1),
	EUROPE (2),
	BRAZIL (3),
	ARGENTINA (4),
	URUGUAY (5);

	public final int value;

	private LOCATION (int value)
	{
		this.value = value;
	}
}
