//Patrick Fleet
public class Person
{
	private String name;
	public Person(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return name;
	}
	public String greet(String message)
	{
		if (message.equals("Hello"))
			return "Hi, I'm "+name;
		else if (message.equals("Goodbye"))
			return "Bye";
		else
			return message;
	}
}