package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Actor;
import domain.Comment;
import domain.Film;
import domain.Rate;
import domain.services.ActorService;
import domain.services.FilmService;

@Path("/filmweb")
public class FilmsResources
{
	
	private FilmService db = new FilmService();
	private ActorService dba = new ActorService();
	private static int currentCommentId = 0;

	@GET
	@Path("/films")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Film> getAllFilms()
	{
		return db.getAll();
	}
	
	@POST
	@Path("/films")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFilm(Film film)
	{
		db.add(film);
		return Response.ok(film.getId()).build();
	}
	
	@GET
	@Path("/films/{filmId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFilm(@PathParam("filmId") int filmId)
	{
		Film result = db.get(filmId);
		if(result==null)
		{
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	
	@PUT
	@Path("/films/{filmId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateFilm(@PathParam("filmId") int filmId, Film f)
	{
		Film result = db.get(filmId);
		if(result==null)
		{
			return Response.status(404).build();
		}
		f.setId(filmId);
		db.update(f);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/films/{filmId}")
	public Response deleteFilm(@PathParam("filmId") int filmId)
	{
		Film result = db.get(filmId);
		if(result==null)
		{
			return Response.status(404).build();
		}
		db.delete(result);
		return Response.ok().build();
	}
	
	@GET
	@Path("/films/{filmId}/rate")
	@Produces(MediaType.APPLICATION_JSON)
	public Rate getRate(@PathParam("filmId") int filmId)
	{
		Film result = db.get(filmId);
		if(result==null)
		{
			return null;
		}
		Rate rate = new Rate();
		rate.setRate(result.getRate());
		return rate;
	}
	
	@POST
	@Path("/films/{filmId}/rate")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRate(@PathParam("filmId") int filmId, Rate rate)
	{
		Film result = db.get(filmId);
		if(result==null)
		{
			return Response.status(404).build();
		}
		int sum = result.getRateSum() + rate.getRate();
		int count = result.getRateCount() + 1;
		result.setRateSum(sum);
		result.setRateCount(count);
		return Response.ok().build();
	}
	
	@GET
	@Path("/films/{filmId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@PathParam("filmId") int filmId)
	{
		Film result = db.get(filmId);
		if(result==null)
		{
			return null;
		}
		if(result.getComments()==null)
		{
			result.setComments(new ArrayList<Comment>());
		}
		return result.getComments();
	}
	
	@POST
	@Path("/films/{filmId}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("filmId") int filmId, Comment comment)
	{
		Film result = db.get(filmId);
		if(result==null)
		{
			return Response.status(404).build();
		}
		if(result.getComments()==null)
		{
			result.setComments(new ArrayList<Comment>());
		}
		comment.setId(++currentCommentId);
		result.getComments().add(comment);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("films/{filmId}/comments/{commentId}")
	public Response deleteComment(@PathParam("filmId") int filmId, @PathParam("commentId") int commentId) 
	{
		Film result = db.get(filmId);
		if(result==null) 
		{
			return Response.status(404).build();
		}
		int i=0;
		for(Comment comment : result.getComments())
		{
			if(comment.getId()==commentId)
			{
				result.getComments().remove(i);
				return Response.ok().build();
			}
			i++;
		}
		return Response.status(404).build();
	}

	@GET
	@Path("/actors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getAllActors()
	{
		return dba.getAll();
	}
	
	@POST
	@Path("/actors")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addActor(Actor actor)
	{
		dba.add(actor);
		return Response.ok(actor.getId()).build();
	}
	
	@POST
	@Path("/actors/{actorId}/{filmId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addActorToFilm(@PathParam("filmId") int filmId, @PathParam("actorId") int actorId)
	{
		Film resultf = db.get(filmId);
		Actor resulta = dba.get(actorId); 
		if(resultf==null || resulta==null)
		{
			return Response.status(404).build();
		}
		if(resultf.getActors()==null)
		{
			resultf.setActors(new ArrayList<String>());
		}
		if(resulta.getFilms()==null)
		{
			resulta.setFilms(new ArrayList<String>());
		}
		resultf.getActors().add(resulta.getName() + " " + resulta.getSurname());
		resulta.getFilms().add(resultf.getTitle());
		return Response.ok().build();
	}
	
	@GET
	@Path("/films/{filmId}/actors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getActorsFromFilm(@PathParam("filmId") int filmId)
	{
		Film result = db.get(filmId);
		if(result==null)
		{
			return null;
		}
		if(result.getActors()==null)
		{
			result.setActors(new ArrayList<String>());
		}
		return result.getActors();
	}
	
	@GET
	@Path("/actors/{actorId}/films")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getFilmsFromActor(@PathParam("actorId") int actorId)
	{
		Actor result = dba.get(actorId);
		if(result==null)
		{
			return null;
		}
		if(result.getFilms()==null)
		{
			result.setFilms(new ArrayList<String>());
		}
		return result.getFilms();
	}
}
