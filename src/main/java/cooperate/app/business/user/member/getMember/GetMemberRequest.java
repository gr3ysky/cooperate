package cooperate.app.business.user.member.getMember;

import cooperate.infrastructure.dto.user.MemberDto;
import cooperate.infrastructure.mediation.IHandleRequest;
import cooperate.infrastructure.mediation.IRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetMemberRequest implements IRequest<GetMemberRequest, MemberDto> {
    @Autowired
    GetMemberRequestHandler handler;
    private int userId;

    private int memberId;
    private String userName;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public IHandleRequest<GetMemberRequest, MemberDto> getHandler() {
        return handler;
    }

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }
}
