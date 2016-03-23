package cooperate.app.data.write;

import cooperate.app.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends GenericRepository<User> {
    public UserRepository() {
        setClazz(User.class);
    }
}
