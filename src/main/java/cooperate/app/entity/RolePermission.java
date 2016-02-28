package cooperate.app.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int RolePermissionId;
    public int RoleId;
    public int PermissionId;
    public boolean IsActive;
    public Timestamp CreateDate;
    public int CreateUserId;
}
