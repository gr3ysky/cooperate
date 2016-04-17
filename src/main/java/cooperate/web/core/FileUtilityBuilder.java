package cooperate.web.core;


import org.springframework.web.multipart.MultipartFile;

public class FileUtilityBuilder {

    private FileUtility fileUtility;

    public FileUtilityBuilder() {
        fileUtility = new FileUtility();
    }

    public FileUtilityBuilder setRoot(String root) {
        fileUtility.setRoot(root);
        return this;
    }

    public FileUtilityBuilder setFile(MultipartFile file) {
        fileUtility.setFile(file);
        return this;
    }

    public FileUtilityBuilder setPath(String path) {
        fileUtility.setPath(path);
        return this;
    }

    public FileUtility build() {
        return fileUtility;
    }
}
