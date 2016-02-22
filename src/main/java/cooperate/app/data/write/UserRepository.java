package cooperate.app.data.write;

import cooperate.app.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends GenericRepository<User> {
    public void UserReadRepository() {
        setClazz(User.class);
    }
}
