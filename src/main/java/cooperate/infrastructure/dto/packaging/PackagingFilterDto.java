package cooperate.infrastructure.dto.packaging;


public class PackagingFilterDto {
    private String name;
    private int isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
