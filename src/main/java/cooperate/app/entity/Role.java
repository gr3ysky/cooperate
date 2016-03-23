package cooperate.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int RoleId;
    public String RoleCode;
    public String RoleName;
}
