package cooperate.app.data.read;

import cooperate.app.entity.Member;
import cooperate.infrastructure.dto.user.MemberDto;
import org.springframework.stereotype.Repository;

@Repository
public class MemberReadRepository extends GenericReadRepository<Member> {
    public MemberReadRepository() {
        setClazz(Member.class);
    }

    public MemberDto getMemberDto(int memberId, int userId, String userName) throws Exception {
        return exetuteReader(MemberDto.class, "p_search_member", memberId, userId, userName);

    }

    public boolean usernameExists(String username) throws Exception {
        return exetuteScalar(Boolean.class, "p_exists_username", username);
    }
}
