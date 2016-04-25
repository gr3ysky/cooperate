package cooperate.app.data.read;

import cooperate.app.entity.ProductImage;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.productImage.ProductImageFilterDto;
import cooperate.infrastructure.dto.productImage.ProductImageListDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductImageReadRepository extends GenericReadRepository<ProductImage> {
    public ProductImageReadRepository() {
        setClazz(ProductImage.class);
    }

    public ListResponse<ProductImageListDto> ListProductImages(ListRequest<ProductImageFilterDto, ProductImageListDto> request) throws Exception {
        ListResponse<ProductImageListDto> images = new ListResponse<ProductImageListDto>();
        List<ProductImageListDto> list = new ArrayList<ProductImageListDto>();
        list = exetuteListReader(ProductImageListDto.class, "p_list_product_images_grid",
                request.getFilter().getProductId(),
                SetNull(request.getFilter().getIsActive()),
                request.getStart(),
                request.getPageSize(),
                request.getOrderColumn(),
                request.getOrderDir(),
                null,
                null
        );
        images.setData(list);
        images.setDraw(request.getDraw());
        images.setRecordsFiltered((Long) getOutputValue("p_filtered_total"));
        images.setRecordsTotal((Long) getOutputValue("p_total"));
        return images;
    }
}
