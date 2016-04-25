package cooperate.app.data.read;

import cooperate.app.entity.Product;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.product.ProductFilterDto;
import cooperate.infrastructure.dto.product.ProductListDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductReadRepository extends GenericReadRepository<Product> {
    public ProductReadRepository() {
        setClazz(Product.class);
    }

    public ListResponse<ProductListDto> ListProducts(ListRequest<ProductFilterDto, ProductListDto> request) throws Exception {
        ListResponse<ProductListDto> producers = new ListResponse<ProductListDto>();
        List<ProductListDto> list = new ArrayList<ProductListDto>();
        list = exetuteListReader(ProductListDto.class, "p_list_product_grid",
                request.getFilter().getName(),
                request.getFilter().getProducerName(),
                request.getFilter().getTag(),
                SetNull(request.getFilter().getIsActive()),
                request.getStart(),
                request.getPageSize(),
                request.getOrderColumn(),
                request.getOrderDir(),
                null,
                null
        );
        producers.setData(list);
        producers.setDraw(request.getDraw());
        producers.setRecordsFiltered((Long) getOutputValue("p_filtered_total"));
        producers.setRecordsTotal((Long) getOutputValue("p_total"));
        return producers;
    }
}
