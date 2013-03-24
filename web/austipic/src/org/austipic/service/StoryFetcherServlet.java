package org.austipic.service;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.austipic.exception.StoryNotFoundException;
import org.austipic.story.Story;

import com.mongodb.MongoClient;

/**
 * Servlet implementation class StoryFetcherServlet
 */
@WebServlet(urlPatterns={"/fetch", "/mystory"})
public final class StoryFetcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String STORY_NOT_FOUND = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
			"<story><title>Story not found.</title></story>";
	
	MongoClient mongo;
	
    @Override
	public void init() throws ServletException {
		super.init();

		try {
			System.out.println("Connecting to mongo on " + getServletContext().getInitParameter("db-host"));
			mongo = new MongoClient(getServletContext().getInitParameter("db-host"));
		} catch (UnknownHostException e) {
			throw new ServletException("Unable to connect to database at ");
		}   
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public StoryFetcherServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.getWriter().println(processRequest(request));
		response.flushBuffer();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.getWriter().println(processRequest(request));
		response.flushBuffer();
	}
	
	protected String processRequest(HttpServletRequest request) {
		String storyId = request.getParameter("story");
		String readerId = request.getParameter("reader");
		
		if(storyId != null) {
			try {
				String myStory = "";
				
				if(request.getRequestURI().endsWith("mystory") && readerId != null) {
					myStory = Story.getAsJson(storyId, readerId,  mongo.getDB("austipic"));
				} else {
					myStory = Story.getAsJson(storyId, mongo.getDB("austipic"));					
				}
				
				return myStory;
			} catch (StoryNotFoundException e) {
				return STORY_NOT_FOUND;
			}
		}

		return STORY_NOT_FOUND;
	}
	

}