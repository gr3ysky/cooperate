package cooperate.infrastructure.dto.user;

import cooperate.infrastructure.dto.NotMapped;
import cooperate.infrastructure.mediation.IResponse;

import java.sql.Timestamp;

public class MemberDto implements IResponse {
    private int memberId;
    private int userId;
    private String userName;
    private String picture;
    private String profileName;
    private boolean volunteerForSelling;
    private boolean volunteerForPackaging;
    private boolean isPublic;
    private java.sql.Timestamp createDate;
    @NotMapped
    private boolean owned;

    public boolean getOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public boolean getVolunteerForSelling() {
        return volunteerForSelling;
    }

    public void setVolunteerForSelling(boolean volunteerForSelling) {
        this.volunteerForSelling = volunteerForSelling;
    }

    public boolean getVolunteerForPackaging() {
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
