package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Actor;

public class ActorService
{

	private static List<Actor> dba = new ArrayList<Actor>();
	private static int currentId = 0;
	
	public List<Actor> getAll()
	{
		return dba;
	}
	
	public Actor get(int id)
	{
		for(Actor a : dba)
		{
			if(a.getId()==id)
			{
				return a;
			}
		}
		return null;
	}

	public void add(Actor a)
	{
		a.setId(++currentId);
		dba.add(a);
	}
	
	public void update(Actor actor)
	{
		for(Actor a : dba)
		{
			if(a.getId()==actor.getId())
			{
				a.setName(actor.getName());
				a.setSurname(actor.getSurname());
			}
		}
	}
	
	public void delete(Actor a)
	{
		dba.remove(a);
	}
}
