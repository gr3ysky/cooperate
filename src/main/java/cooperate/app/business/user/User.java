package cooperate.app.business.user;

import cooperate.app.business.base.EntityBase;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(appliesTo = "User")
public class User extends EntityBase {
    @Id
    @GeneratedValue
    private Long UserId;
    private String FullName;
    private String Email;
    private String Password;
    private java.sql.Timestamp CreateDate;
    private Long UpdateUser;
    private java.sql.Timestamp UpdateDate;

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public java.sql.Timestamp getCreatedate() {
        return CreateDate;
    }

    public void setCreatedate(java.sql.Timestamp createdate) {
        this.CreateDate = createdate;
    }

    public Long getUpdateuser() {
        return UpdateUser;
    }

    public void setUpdateuser(Long updateuser) {
        this.UpdateUser = updateuser;
    }

    public java.sql.Timestamp getUpdatedate() {
        return UpdateDate;
    }

    public void setUpdatedate(java.sql.Timestamp updatedate) {
        this.UpdateDate = UpdateDate;
    }

}
