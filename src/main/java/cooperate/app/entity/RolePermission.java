package cooperate.app.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "role_permission")
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
