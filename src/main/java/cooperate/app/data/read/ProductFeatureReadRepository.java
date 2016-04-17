package cooperate.app.data.read;

import cooperate.app.entity.ProductFeature;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.productFeature.ProductFeatureFilterDto;
import cooperate.infrastructure.dto.productFeature.ProductFeatureListDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductFeatureReadRepository extends GenericReadRepository<ProductFeature> {
    public ProductFeatureReadRepository() {
        setClazz(ProductFeature.class);
    }

    public ListResponse<ProductFeatureListDto> ListProductFeatures(ListRequest<ProductFeatureFilterDto, ProductFeatureListDto> request) throws Exception {
        ListResponse<ProductFeatureListDto> productFeatures = new ListResponse<ProductFeatureListDto>();
        List<ProductFeatureListDto> list = new ArrayList<ProductFeatureListDto>();
        list = exetuteListReader(ProductFeatureListDto.class, "p_list_product_features_grid",
                request.getFilter().getName(),
                request.getFilter().getTitle(),
                SetNull(request.getFilter().getIsActive()),
                request.getStart(),
                request.getPageSize(),
                request.getOrderColumn(),
                request.getOrderDir(),
                null,
                null
        );
        productFeatures.setData(list);
        productFeatures.setDraw(request.getDraw());
        productFeatures.setRecordsFiltered((Long) getOutputValue("p_filtered_total"));
        productFeatures.setRecordsTotal((Long) getOutputValue("p_total"));
        return productFeatures;

    }
}