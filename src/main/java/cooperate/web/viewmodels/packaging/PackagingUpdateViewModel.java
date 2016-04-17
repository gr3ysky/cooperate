package cooperate.web.viewmodels.packaging;

public class PackagingUpdateViewModel extends PackagingCreateViewModel {
    private int packagingId;

    public int getPackagingId() {
        return packagingId;
    }

    public void setPackagingId(int packagingId) {
        this.packagingId = packagingId;
    }
}
