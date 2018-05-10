package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Film;

public class FilmService
{
	
	private static List<Film> db = new ArrayList<Film>();
	private static int currentId = 0;
	
	public List<Film> getAll()
	{
		return db;
	}
	
	public Film get(int id)
	{
		for(Film f : db)
		{
			if(f.getId()==id)
			{
				return f;
			}
		}
		return null;
	}

	public void add(Film f)
	{
		f.setId(++currentId);
		db.add(f);
	}
	
	public void update(Film film)
	{
		for(Film f : db)
		{
			if(f.getId()==film.getId())
			{
				f.setTitle(film.getTitle());
				f.setRateSum(film.getRateSum());
				f.setRateCount(film.getRateCount());
			}
		}
	}
	
	public void delete(Film f)
	{
		db.remove(f);
	}

}
