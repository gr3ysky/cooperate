package cooperate.app.business.user.member.getMember;

import cooperate.app.data.read.MemberReadRepository;
import cooperate.infrastructure.dto.user.MemberDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.infrastructure.mediation.IHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetMemberRequestHandler implements IHandleRequest<GetMemberRequest, MemberDto> {

    @Autowired
    MemberReadRepository memberReadRepository;

    public MemberDto Handle(GetMemberRequest request) throws Exception {
        MemberDto dto = memberReadRepository.getMemberDto(request.getMemberId(), request.getUserId(), request.getUserName());
        if (dto == null) {
            throw new CoopException("error.memberNotFound");
        }
        return dto;
    }
}
