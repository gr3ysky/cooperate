package cooperate.app.business.user.member;

import cooperate.app.ServiceBase;
import cooperate.app.business.user.member.getMember.GetMemberRequest;
import cooperate.infrastructure.dto.user.MemberDto;
import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService extends ServiceBase {
    @Autowired
    Mediator mediator;

    @Transactional(readOnly = true)
    public MemberDto getMemberDto(GetMemberRequest request) throws Exception {
        return mediator.request(request);
    }

    @Transactional
    public void createMember(ICommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void updateMember(ICommand command) throws Exception {
        mediator.send(command);
    }

}
