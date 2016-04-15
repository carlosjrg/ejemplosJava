package es.cjrg.http.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ClienteHTTP {

	public static void main(String[] args) throws IOException {
		String rawData = "id=10";
		String type = "application/x-www-form-urlencoded";
		String encodedData = URLEncoder.encode( rawData,java.nio.charset.StandardCharsets.UTF_8.toString() ); 
		URL u = new URL("http://localhost:9000/pagina?variable1=valor1&variable2=valor2&variable2=valor2");
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setConnectTimeout(5000);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty( "Content-Type", type );
		conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
		
		OutputStream os = conn.getOutputStream();
		os.write(encodedData.getBytes());
		InputStream is = conn.getInputStream();		
		
		byte[] array = new byte[1000]; // buffer temporal de lectura.
		int leido = is.read(array);			
		while (leido > 0) {					
			System.out.println(new String(array,0,leido));			
			leido = is.read(array);			
		}
	}

}
