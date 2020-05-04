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
	private FileStorageProperties fileStorageProperties;
    
    @PostMapping("/Product/Upload/")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile uploadFile) {

        String fileName = fileStorageServiceUpload.storeFile(uploadFile);

        productMasterService.writeProductMaster(fileName);
      
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(fileStorageProperties.getUploadDir())	
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
        		uploadFile.getContentType(), uploadFile.getSize());
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/Product/Download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {
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
}
