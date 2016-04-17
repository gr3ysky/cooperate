package cooperate.web.viewmodels.saleType;

public class SaleTypeUpdateViewModel extends SaleTypeCreateViewModel {
    private int saleTypeId;

    public int getSaleTypeId() {
        return saleTypeId;
    }

    public void setSaleTypeId(int saleTypeId) {
        this.saleTypeId = saleTypeId;
    }
}
