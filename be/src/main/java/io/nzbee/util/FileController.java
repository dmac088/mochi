package io.nzbee.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Autowired
    private FileStorageServiceDownload fileStorageServiceDownload;
    
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
    
    @Autowired
    private ProductMasterService productMasterService;
    
    @Autowired
    private CategoryMasterService categoryMasterService;
    
    @Autowired
    private BrandMasterService brandMasterService; 
    
	@Autowired 
	private FileStorageProperties fileStorageProperties;
    
    @PostMapping("/Product/Upload/")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile uploadFile) {
    	
    	logger.debug("called uploadFile with parameters {} ", uploadFile );

        String fileName = fileStorageServiceUpload.storeFile(uploadFile);

        productMasterService.writeFoodMaster(fileName);
      
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(fileStorageProperties.getUploadDir())	
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
        		uploadFile.getContentType(), uploadFile.getSize());
    }

    @GetMapping("/Product/Download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFoodFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
    	logger.debug("called downloadFoodFile with parameters {} ", fileStorageProperties.getDownloadDir() + fileName );
    	
    	//generate the file for downloading
    	File file = new File(fileStorageProperties.getDownloadDir() + fileName);
        
    	//persist the file
    	try {
    		file.createNewFile();
    	} catch (IOException ex)  {
    		logger.error(ex.toString());
    	}
    	
        // Load file as Resource
        Resource resource = fileStorageServiceDownload.loadFileAsResource(file.getName());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
 
 		//  Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        
        //write the product master data to file
        productMasterService.extractProductMaster(resource);
        
        return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(contentType))
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
               .body(resource);
    }
    
    
    @GetMapping("/Category/Download/{fileName:.+}")
    public ResponseEntity<Resource> downloadCategoryFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(fileStorageProperties.getDownloadDir());
    	
    	logger.debug("called downloadCategoryFile with parameters {} ", fileStorageProperties.getDownloadDir() + fileName );
    	
    	//generate the file for downloading
    	File file = new File(fileStorageProperties.getDownloadDir() + fileName);
        
    	//persist the file
    	try {
    		file.createNewFile();
    	} catch (IOException ex)  {
    		logger.error(ex.toString());
    	}
    	
        // Load file as Resource
        Resource resource = fileStorageServiceDownload.loadFileAsResource(file.getName());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
 
 		//  Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        
        //write the product master data to file
        categoryMasterService.extractCategoryMaster(resource);
        
        return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(contentType))
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
               .body(resource);
    }
    
    @GetMapping("/Brand/Download/{fileName:.+}")
    public ResponseEntity<Resource> downloadBrandFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(fileStorageProperties.getDownloadDir());
    	
    	logger.debug("called downloadBrandFile with parameters {} ", fileStorageProperties.getDownloadDir() + fileName );
    	
    	//generate the file for downloading
    	File file = new File(fileStorageProperties.getDownloadDir() + fileName);
        
    	//persist the file
    	try {
    		file.createNewFile();
    	} catch (IOException ex)  {
    		logger.error(ex.toString());
    	}
    	
        // Load file as Resource
        Resource resource = fileStorageServiceDownload.loadFileAsResource(file.getName());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
 
 		//  Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        
        //write the brand master data to file
        brandMasterService.extractBrandMaster(resource);
        
        return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(contentType))
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
               .body(resource);
    }
    
}
