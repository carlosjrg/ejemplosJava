package es.cjrg.http.servidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;

import com.sun.net.httpserver.HttpServer;

public class ServidorHTTP {

	static int port = 9000;

	public static void main(String[] args) {
		
		try {
			inicializarServidorHTTP();
		} catch (IOException e) {			
			e.printStackTrace();
		}

	}

	private static void inicializarServidorHTTP() throws IOException {

		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		System.out.println("server started at " + port);
		server.createContext("/", new ProcesarPeticionCompleta());
		//server.createContext("/", new RootHandler());
		server.createContext("/ruta2", new ProcesarPeticionCompleta());//Podría ser otro procesador	

		//Para un sólo hilo
		//server.setExecutor(null);
		
		//Para X hilos (3)
		int numHilos = 3;
		ExecutorService executors = java.util.concurrent.Executors.newFixedThreadPool(numHilos);
		server.setExecutor(executors);		
		server.start();
		
		//try {
		//	Thread.sleep(30000);
		//} catch (InterruptedException e) {			
		//}
		
		//Para parar el servidor		
		//server.stop(5000);
		//executors.shutdown();
	}

}
