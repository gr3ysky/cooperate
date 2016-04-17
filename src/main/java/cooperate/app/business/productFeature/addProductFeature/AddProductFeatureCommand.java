package cooperate.app.business.productFeature.addProductFeature;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddProductFeatureCommand implements ICommand {
    @Autowired
    AddProductFeatureCommandHandler handler;

    private String name;
    private String title;
    private String nameResourceKey;
    private String titleResourceKey;
    private String imageUrl;
    private boolean isActive;
    private int createUserId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameResourceKey() {
        return nameResourceKey;
    }

    public void setNameResourceKey(String nameResourceKey) {
        this.nameResourceKey = nameResourceKey;
    }

    public String getTitleResourceKey() {
        return titleResourceKey;
    }

    public void setTitleResourceKey(String titleResourceKey) {
        this.titleResourceKey = titleResourceKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public IHandleCommand<AddProductFeatureCommand> getHandler() {
        return handler;
    }
}

