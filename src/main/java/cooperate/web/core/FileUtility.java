package cooperate.web.core;

import cooperate.infrastructure.constant.CommonConstants;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtility {

    private MultipartFile file;
    private String path;
    private String root;
    private String fileRelativePath;

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getFileUrl() {
        return fileRelativePath;
    }

    private String getExactPath() {
        if (file == null)
            return root + "/" + path;
        fileRelativePath = String.format("/%s%s%s", getPath(), UUID.randomUUID(), getfileExtensionName());

        return String.format("%s%s", root, fileRelativePath);
    }

    private String getfileExtensionName() {
        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
    }

    public String getPath() {
        if (path == null) {
            path = CommonConstants.ImageUploadUrl;
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void saveFile() throws Exception {
        if (file.isEmpty()) return;
        //make upload dir if not exists

        Path folderpath = Paths.get(root + CommonConstants.ImageUploadUrl);

        if (!Files.exists(folderpath)) {
            Files.createDirectories(folderpath);
            //throw new CoopException("error.cannotCreateDirectory",root+CommonConstants.ImageUploadUrl);
        }

        File newFile = new File(getExactPath());
        newFile.createNewFile();

        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
        FileCopyUtils.copy(file.getInputStream(), stream);
        stream.close();
    }

    public void deleteFile() {

    }


}
