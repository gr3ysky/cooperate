package cooperate.app.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Role extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int RoleId;
    public String RoleCode;
    public String RoleName;
}
