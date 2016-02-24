package cooperate.app.entity;

import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(appliesTo = "User")
public class User extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;
    private String FullName;
    private String Email;
    private byte[] Password;
    private java.sql.Timestamp CreateDate;
    private long CreateUserId;
    private Long UpdateUserId;
    private java.sql.Timestamp UpdateDate;
    private int RoleId;

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        this.UserId = userId;
    }

    public String getFullname() {
        return FullName;
    }

    public void setFullname(String fullname) {
        this.FullName = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public byte[] getPassword() {
        return Password;
    }

    public void setPassword(byte[] password) {
        this.Password = password;
    }

    public java.sql.Timestamp getCreatedate() {
        return CreateDate;
    }

    public void setCreatedate(java.sql.Timestamp createdate) {
        this.CreateDate = createdate;
    }

    public Long getUpdateuser() {
        return UpdateUserId;
    }

    public void setUpdateuser(Long updateuser) {
        this.UpdateUserId = updateuser;
    }

    public java.sql.Timestamp getUpdatedate() {
        return UpdateDate;
    }

    public void setUpdatedate(java.sql.Timestamp updatedate) {
        this.UpdateDate = UpdateDate;
    }

    public long getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(long createUserId) {
        CreateUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return UpdateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        UpdateUserId = updateUserId;
    }
}
