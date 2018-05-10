package domain;

import java.util.List;

public class Actor
{
	
	private int id;
	private String name;
	private String surname;
	private List<String> films;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	public List<String> getFilms()
	{
		return films;
	}
	
	public void setFilms(List<String> films)
	{
		this.films = films;
	}  
	
}
