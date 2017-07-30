package com.dr.galaxy.gamehome.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dr.galaxy.gamehome.service.DocumentService;
import com.dr.galaxy.gamehome.util.GamehomeUtil;

@RestController
@CrossOrigin
@RequestMapping("document")
public class DocumentController {
	//文案
	@Autowired
	private DocumentService documentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllDocuments(){
		return documentService.getAllDocument();
	}
	/*@Autowired
	private DocumentService documentService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity uploadDocuemnt(@RequestPart("file") final MultipartFile file,
			final HttpServletRequest request) throws IOException {
		final String id = documentService.saveDocument(file);
		final String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ "/document/" + id;
		return GamehomeUtil.resourceCreatedMessage(id, url);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> getDocuemntById(@PathVariable("id") final String id) throws IOException {
		return createFileResponse(documentService.getDocumentById(id));
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public void getDocuemntById(@PathVariable("id") final String id,HttpServletResponse response) throws IOException {
		  GridFSDBFile file = documentService.getDocumentById(id);  
		  try {
				OutputStream out = response.getOutputStream();
				response.setContentType("image/png");
				//GridFSDBFile gridFSDBFile = find.get(0);
				file.writeTo(out);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
	}
	@RequestMapping(method = RequestMethod.GET)
	public List<GridFSDBFile> getAllDocuemnt(HttpServletResponse response) throws IOException {
		return documentService.getAllDocument();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<InputStreamResource> deleteDocuemntById(@PathVariable("id") final String id)
			throws IOException {
		documentService.deleteDocument(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	private ResponseEntity<InputStreamResource> createFileResponse(final GridFSDBFile file) throws IOException {
		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("contentType", file.getContentType());

		try (InputStream is = file.getInputStream()) {
			return new ResponseEntity<>(new InputStreamResource(is), responseHeaders, HttpStatus.OK);
		}

	}*/
}
