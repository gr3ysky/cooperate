package cooperate.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int RoleId;
    public String RoleCode;
    public String RoleName;
}
