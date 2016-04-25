package cooperate.web.viewmodels.Profile;


import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public class ProfileCreateModel {
    @Length(max = 45, min = 5)
    private String userName;
    private String picture;
    private MultipartFile image;
    @Length(max = 100)
    private String profileName;
    private boolean volunteerForSelling;
    private boolean volunteerForPackaging;
    private boolean isPublic;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public boolean isVolunteerForSelling() {
        return volunteerForSelling;
    }

    public void setVolunteerForSelling(boolean volunteerForSelling) {
        this.volunteerForSelling = volunteerForSelling;
    }

    public boolean isVolunteerForPackaging() {
        return volunteerForPackaging;
    }

    public void setVolunteerForPackaging(boolean volunteerForPackaging) {
        this.volunteerForPackaging = volunteerForPackaging;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
