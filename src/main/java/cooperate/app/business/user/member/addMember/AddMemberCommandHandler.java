package cooperate.app.business.user.member.addMember;

import cooperate.app.data.read.MemberReadRepository;
import cooperate.app.data.read.UserReadRepository;
import cooperate.app.data.write.MemberRepository;
import cooperate.app.data.write.UserRepository;
import cooperate.app.entity.Member;
import cooperate.app.entity.User;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddMemberCommandHandler implements IHandleCommand<AddMemberCommand> {

    @Autowired
    UserReadRepository userReadRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MemberReadRepository memberReadRepository;
    @Autowired
    private MemberRepository memberRepository;

    public void Handle(AddMemberCommand command) throws Exception {
        User user = userReadRepository.findOne(command.getUserId());
        if (user == null)
            throw new CoopException("error.userNotFound");

        if (user.getMemberId() != null) {
            throw new CoopException("error.profileAlreadyCreated");
        }

        if (memberReadRepository.usernameExists(command.getUserName())) {
            throw new CoopException("error.userNameExists");
        }

        Member member = new Member();
        member.setCreateUserId(command.getUserId());
        member.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
        member.setIsPublic(command.isPublic());
        member.setIsVolunteerForPackaging(command.isVolunteerForPackaging());
        member.setIsVolunteerForSelling(command.isVolunteerForSelling());
        if (command.getProfileName().length() == 0) {
            member.setProfileName(user.getFullName());
        } else {
            member.setProfileName(command.getProfileName());
        }
        member.setPicture(command.getPicture());
        member.setUserName(command.getUserName());
        memberRepository.create(member);
        user.setMemberId(member.getMemberId());
        userRepository.update(user);
    }
}
