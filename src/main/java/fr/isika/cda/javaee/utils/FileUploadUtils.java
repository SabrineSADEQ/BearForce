package fr.isika.cda.javaee.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;

import org.primefaces.model.file.UploadedFile;

public class FileUploadUtils {

	private static final Logger LOGGER = Logger.getLogger(FileUploadUtils.class.getSimpleName());
	
	public static void uploadFileToApp(UploadedFile uploadedFile, final String fileName) {
		
		// => webapp/resources/....
		String resourcesDir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/");
		
		// => webapp/resources/documents
		String documentsDir = resourcesDir + "/" + "documents";
		
		// Check if /../documents exists , create the dir if not !!
		File documentsDirFile = new File(documentsDir);
		if(!documentsDirFile.exists()) {
			documentsDirFile.mkdirs();
		}
		
		// Upload the file
        try (InputStream inputStream = uploadedFile.getInputStream()) {
          Files.copy(inputStream, Paths.get(documentsDir, fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
           LOGGER.log(Level.SEVERE, e.getMessage());
        }
	}
	
}