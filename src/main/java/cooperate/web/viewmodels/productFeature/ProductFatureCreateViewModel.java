package cooperate.web.viewmodels.productFeature;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ProductFatureCreateViewModel {
    @NotEmpty
    @Length(max = 45)
    private String name;

    @NotEmpty
    @Length(max = 50)
    private String nameResourceKey;

    private String title;

    private String titleResourceKey;

    private MultipartFile image;

    private boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String getNameResourceKey() {
        return nameResourceKey;
    }

    public void setNameResourceKey(String nameResourceKey) {
        this.nameResourceKey = nameResourceKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleResourceKey() {
        return titleResourceKey;
    }

    public void setTitleResourceKey(String titleResourceKey) {
        this.titleResourceKey = titleResourceKey;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
