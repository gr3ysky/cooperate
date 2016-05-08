package cooperate.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int PermissionId;
    public String PermissionName;
    public String PermissionCode;
    public Boolean IsActive;
    public java.sql.Timestamp CreateDate;
    public long CreateUserId;
    public java.sql.Timestamp UpdateDate;
    public Long UpdateUserId;
}
