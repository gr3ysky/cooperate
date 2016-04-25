package cooperate.app.business.user.member.addMember;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddMemberCommand implements ICommand {

    @Autowired
    private AddMemberCommandHandler handler;
    private int userId;
    private String userName;
    private String picture;
    private String profileName;
    private boolean volunteerForSelling;
    private boolean volunteerForPackaging;
    private boolean isPublic;

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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public IHandleCommand<AddMemberCommand> getHandler() {
        return handler;
    }


}
