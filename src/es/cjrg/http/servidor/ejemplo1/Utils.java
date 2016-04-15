package es.cjrg.http.servidor.ejemplo1;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {

	public static void parseUri(String uri, Map<String, Object> parameters) throws UnsupportedEncodingException {

		if (uri != null) {
			String uriDecode = URLDecoder.decode(uri, System.getProperty("file.encoding"));
			String pairs[] = uriDecode.split("[&]");
			for (String pair : pairs) {
				String param[] = pair.split("[=]");
				String key = null;
				String value = null;
				if (param.length > 0) {
					key = param[0];
				}

				if (param.length > 1) {
					value = param[1];
				}

				if (parameters.containsKey(key)) {
					Object obj = parameters.get(key);
					if (obj instanceof List<?>) {

						@SuppressWarnings("unchecked")
						List<String> values = (List<String>) obj;
						values.add(value);

					} else if (obj instanceof String) {
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} else {
					parameters.put(key, value);
				}
			}
		}
	}
}
