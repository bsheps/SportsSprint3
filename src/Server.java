
/**
 * Simple HTTP handler for testing ChronoTimer
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

	private static Queue<Racer> results;
	// a shared area where we get the POST data and then use it in the other handler
	static String sharedResponse = "";
	static boolean gotMessageFlag = false;
	private static final String pageStart = "<!DOCTYPE html><html><head><title>Race Results</title><style>.results {font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;border-collapse: collapse;width: 800px;}td, th {border: 1px solid #ddd;	padding: 8px;}th {padding-top: 12px;padding-bottom: 12px;text-align: left;background-color: MediumBlue;	color: white;}tr:nth-child(even) {background-color: LightBlue;}tr:hover {background-color: #ddd;}</style></head><body><h2>Race Results</h2><table class=\"results\"><tr><th>Place</th><th>Bib Number</th><th>Name</th><th>Time</th></tr>";
	private static final String pageEnd = "</table></body></html>";

	public Server() throws Exception {

		// set up a simple HTTP server on our local host
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

		// create a context to get the request to display the results
		server.createContext("/displayresults", new DisplayHandler());

		// create a context to get the request for the POST
		// server.createContext("/sendresults", new PostHandler());
		server.setExecutor(null); // creates a default executor

		// get it going
		System.out.println("Starting Server...");
		server.start();
	}

	static class DisplayHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {

			String response = getPage(results);

			// write out the response
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

	public void updateResults(Queue<Racer> results) {
		this.results = results;
	}

	private static String getPage(Queue<Racer> toShow) {
		
		ArrayList<Racer> finished = new ArrayList<Racer>();
		for(Racer r : toShow) {
			finished.add(r);
		}
		Collections.sort(finished);
		
		Iterator<Racer> racers = finished.iterator();
		StringBuilder dynamic = new StringBuilder(pageStart);

		for (int i = 0; i < toShow.size(); i++) {
			Racer r = racers.next();
			dynamic.append("<tr><td>" + (i + 1) + "</td><td>" + r.getBibNum() + "</td><td>" + r.getName() + "</td><td>" + r.results() + "</td></tr>");
		}

		dynamic.append(pageEnd);

		return dynamic.toString();
	}

}