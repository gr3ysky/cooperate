package cooperate.app.data.write;

import cooperate.app.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository extends GenericRepository<Member> {
    public MemberRepository() {
        setClazz(Member.class);
    }
}
