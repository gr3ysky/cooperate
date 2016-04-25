package cooperate.app.business.user.member.updateMember;

import cooperate.app.data.read.MemberReadRepository;
import cooperate.app.data.write.MemberRepository;
import cooperate.app.entity.Member;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateMemberCommandHandler implements IHandleCommand<UpdateMemberCommand> {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberReadRepository memberReadRepository;

    public void Handle(UpdateMemberCommand command) throws Exception {
        Member member = memberReadRepository.findOne(command.getMemberId());
        if (member == null)
            throw new CoopException("error.memberNotFound");
        if (!member.getUserName().equals(command.getUserName()) && memberReadRepository.usernameExists(command.getUserName())) {
            throw new CoopException("error.userNameExists");
        }
        member.setUpdateUserId(command.getUserId());
        member.setUpdateDate(new java.sql.Timestamp(System.currentTimeMillis()));
        member.setIsPublic(command.isPublic());
        member.setIsVolunteerForPackaging(command.isVolunteerForPackaging());
        member.setIsVolunteerForSelling(command.isVolunteerForSelling());
        member.setProfileName(command.getProfileName());
        if (command.getPicture() != null) {
            member.setPicture(command.getPicture());
        }
        member.setUserName(command.getUserName());
        memberRepository.update(member);
    }
}
