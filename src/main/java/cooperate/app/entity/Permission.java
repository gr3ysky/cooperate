package cooperate.app.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
