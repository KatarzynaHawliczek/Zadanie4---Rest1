package domain;

import java.util.List;

public class Film
{
	
	private int id;
	private String title;
	private int rateSum = 0;
	private int rateCount = 0;
	private List<Comment> comments;
	private List<String> actors;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public int getRateSum()
	{
		return rateSum;
	}

	public void setRateSum(int rateSum)
	{
		this.rateSum = rateSum;
	}

	public int getRateCount()
	{
		return rateCount;
	}

	public void setRateCount(int rateCount)
	{
		this.rateCount = rateCount;
	}
	
	public int getRate()
	{
		if(this.rateCount>0)
		{
			return Math.round(this.rateSum/this.rateCount);
		}
		else
		{
			return 0;
		}
	}
	
	public List<Comment> getComments()
	{
		return comments;
	}
	
	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}
	
	public List<String> getActors()
	{
		return actors;
	}

	public void setActors(List<String> actors)
	{
		this.actors = actors;
		
	}

}
