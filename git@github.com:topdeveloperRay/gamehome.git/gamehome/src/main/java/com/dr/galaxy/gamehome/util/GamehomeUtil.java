package com.dr.galaxy.gamehome.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dr.galaxy.gamehome.model.ResourceCreatedMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GamehomeUtil {
	public static ResponseEntity resourceCreatedMessage(final String id, final String url) {
		return new ResponseEntity<>(new ResourceCreatedMessage(id, url), HttpStatus.CREATED);
	}

	public static byte[] readInputStream(InputStream inputStream) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getCurrentTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
	
    public static Map createQueryMap(final String q) throws IOException {
        /*
         * if (q == null) { return null; }
         */
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(q, new TypeReference<Map>() {
            });
        } catch (final IOException e) {
            throw new IOException(e.getMessage());
        }
    }
	
    public static Integer convertStringToInt(String s) {
		int a = 0;
		try {
			a = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return a;
	}
}
