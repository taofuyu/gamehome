package com.dr.galaxy.gamehome.service;

import org.springframework.stereotype.Service;

@Service
public class DocumentService {

	public String getAllDocument() {
		
		return null;
	}
/*
	@Autowired
	private GridFsTemplate gridFsTemplate;

	@Autowired  
	private MongoDbFactory mongodbfactory; 
	
	public String saveDocument(final MultipartFile multipartFile) throws IOException {
		//final String id = UUID.randomUUID().toString();
		String id="";
		//final Document document = new Document(id);
		try (InputStream is = multipartFile.getInputStream()) {
			 id  = gridFsTemplate.store(is, multipartFile.getOriginalFilename(), multipartFile.getContentType()).getId().toString();
		}
		return id;
	}

	public GridFSDBFile getDocumentById(final String id) {

		final GridFSDBFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		if (null == file) {
			return null;
		}
		//String id = "5602de6e5d8bba0d6f2e45e4";
		//GridFSDBFile gridFsdbFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		return file;
	}

	public void deleteDocument(final String id) {
		//getDocumentById(id);
		gridFsTemplate.delete(new Query(Criteria.where("._id").is(id)));
	}

	public GridFSDBFile getById(String id) {
		DBObject query  = new BasicDBObject("metadata._id", id);  
	    GridFS gridFS = new GridFS(mongodbfactory.getDb());  
	    GridFSDBFile gridFSDBFile = gridFS.findOne(query);  
	    return gridFSDBFile;
	    }

	public List<GridFSDBFile> getAllDocument() {
		List<GridFSDBFile> gridFSDBFiles = gridFsTemplate.find(null);
		return gridFSDBFiles;
	}
	*/
}
