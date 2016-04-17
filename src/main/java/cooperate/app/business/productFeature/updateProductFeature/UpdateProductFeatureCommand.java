package cooperate.app.business.productFeature.updateProductFeature;

import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductFeatureCommand implements ICommand {
    @Autowired
    UpdateProductFeatureCommandHandler handler;
    private int productFeatureId;
    private String name;
    private String title;
    private String nameResourceKey;
    private String titleResourceKey;
    private String imageUrl;
    private int updateUserId;
    private boolean isActive;

    public int getProductFeatureId() {
        return productFeatureId;
    }

    public void setProductFeatureId(int productFeatureId) {
        this.productFeatureId = productFeatureId;
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

    public String getName() {
        return name;
    }

    public void setName(String fname) {
        this.name = fname;
    }


    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }


    public IHandleCommand<UpdateProductFeatureCommand> getHandler() {
        return handler;
    }


}
