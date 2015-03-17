package com.webstoremvc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webstoremvc.database.GeneralQueries;

/**
 * Controller for accessing images
 */
@Controller
public class ImageController {
	/**
	 * callback function
	 */
	@RequestMapping(value="/images/{id}")
	public @ResponseBody ResponseEntity<byte[]> asd(@PathVariable String id, HttpServletRequest request) throws IOException{	
		// get the app's root path
		ServletContext context = request.getSession().getServletContext();
		String rootPath = context.getRealPath("/");
	
		// get variable sent from html page
		byte[] imgData = null;
		String path = new GeneralQueries().getImagePath(id);
		Path imgPath = Paths.get(rootPath + path);
		
		
		// prepare data to be sent back
		InputStream in = Files.newInputStream(imgPath);

	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);

	    imgData = IOUtils.toByteArray(in);
	    
	    // return image
	    return new ResponseEntity<byte[]>(imgData, headers, HttpStatus.CREATED);
	}
}
