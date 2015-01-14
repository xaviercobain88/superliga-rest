package xaw.rest_services.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class EmailUtils {
	public String getTemplateContent(String propertyName,
			HashMap<String, String> parameters) throws IOException {

		String content = getContent(propertyName);
		content = replaceParameters(content, parameters);
		return content;
	}

	private String getContent(String propertyName)
			throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		String propFileName = "email.properties";

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		}

		String content = prop.getProperty(propertyName);
		return content;
	}

	private String replaceParameters(String content,
			HashMap<String, String> parameters) {
		if (parameters != null && parameters.size() > 0) {
			for (String key : parameters.keySet()) {
				content = content.replace(key, parameters.get(key));
			}
		}
		return content;

	}

}
