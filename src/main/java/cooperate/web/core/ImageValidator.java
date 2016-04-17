package cooperate.web.core;


import cooperate.infrastructure.constant.CommonConstants;
import cooperate.infrastructure.exception.CoopException;
import org.springframework.web.multipart.MultipartFile;

public final class ImageValidator {
    private static final String pattern = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public final static boolean isValid(MultipartFile file) throws Exception {
        boolean result = true;
        if (file.getBytes().length > CommonConstants.MaxUploadSize) {
            throw new CoopException("error.maxUploadSizeError", CommonConstants.MaxUploadSize);
        }
        if (!file.getContentType().startsWith("image/") && !file.getOriginalFilename().matches(pattern)) {
            throw new CoopException("error.onlyImagesAllowed");
        }

        return result;
    }
}
