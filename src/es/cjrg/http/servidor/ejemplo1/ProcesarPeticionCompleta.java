package es.cjrg.http.servidor.ejemplo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ProcesarPeticionCompleta implements HttpHandler {

	@Override
	public void handle(HttpExchange he) throws IOException {
		
		StringBuffer response = new StringBuffer();
		
		//Se obtiene los datos de la cabecera
		response.append("\nCabecera\n");
		Headers headers = he.getRequestHeaders();
		Set<Entry<String, List<String>>> entries = headers.entrySet();		

		for (Iterator<Entry<String, List<String>>> iterator = entries
				.iterator(); iterator.hasNext();) {
			Entry<String, List<String>> entry = (Entry<String, List<String>>) iterator
					.next();
			response.append(entry.toString() + "\n");
		}

		//Se obtiene los datos recibidos por GET
		response.append("\nParametros por GET\n");
		URI requestedUri = he.getRequestURI();
		String uri = requestedUri.getRawQuery();
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		Utils.parseUri(uri, parameters);
		
		for (String key : parameters.keySet())
			response.append(key + " = " + parameters.get(key) + "\n");
		
		//Se obtiene los datos recibidos por POST
		response.append("\nParametros por POST\n");
		Map<String, Object> parametersPost = new HashMap<String, Object>();
		InputStreamReader isr = new InputStreamReader(he.getRequestBody(),"utf-8");
		BufferedReader br = new BufferedReader(isr);
		String query = br.readLine();
		Utils.parseUri(query, parametersPost);
		
		for (String key : parametersPost.keySet())
			response.append(key + " = " + parametersPost.get(key) + "\n");
		
		he.sendResponseHeaders(200, response.length());
		OutputStream os = he.getResponseBody();
		os.write(response.toString().getBytes());
		os.close();
	}

}