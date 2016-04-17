package cooperate.app.business.producer.addProducer;


import cooperate.infrastructure.mediation.ICommand;
import cooperate.infrastructure.mediation.IHandleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AddProducerCommand implements ICommand {
    @Autowired
    AddProducerCommandHandler handler;

    private String name;
    private String description;
    private Date memberSince;
    private Double latitude;
    private Double longitude;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public IHandleCommand<AddProducerCommand> getHandler() {
        return handler;
    }
}
