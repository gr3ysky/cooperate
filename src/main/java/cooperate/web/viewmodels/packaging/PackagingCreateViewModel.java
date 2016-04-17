package cooperate.web.viewmodels.packaging;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class PackagingCreateViewModel {
    @NotEmpty
    @Length(max = 45)
    private String name;

    @NotEmpty
    @Length(max = 50)
    private String resourceKey;

    private boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
