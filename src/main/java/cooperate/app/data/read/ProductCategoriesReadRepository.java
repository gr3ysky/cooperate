package cooperate.app.data.read;

import cooperate.app.entity.ProductCategory;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.SelectListItem;
import cooperate.infrastructure.dto.productCategory.ProductCategoryFilterDto;
import cooperate.infrastructure.dto.productCategory.ProductCategoryListDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductCategoriesReadRepository extends GenericReadRepository<ProductCategory> {
    public ProductCategoriesReadRepository() {
        setClazz(ProductCategory.class);
    }

    public ListResponse<ProductCategoryListDto> ListProductCategories(ListRequest<ProductCategoryFilterDto, ProductCategoryListDto> request) throws Exception {
        ListResponse<ProductCategoryListDto> productCategories = new ListResponse<ProductCategoryListDto>();
        List<ProductCategoryListDto> list = new ArrayList<ProductCategoryListDto>();
        list = exetuteListReader(ProductCategoryListDto.class, "p_list_product_category_grid",
                request.getFilter().getName(),
                SetNull(request.getFilter().getIsActive()),
                request.getStart(),
                request.getPageSize(),
                request.getOrderColumn(),
                request.getOrderDir(),
                null,
                null
        );
        productCategories.setData(list);
        productCategories.setDraw(request.getDraw());
        productCategories.setRecordsFiltered((Long) getOutputValue("p_filtered_total"));
        productCategories.setRecordsTotal((Long) getOutputValue("p_total"));
        return productCategories;
    }

    public List<SelectListItem> getProductCategoriesDropDown() throws Exception {
        return exetuteListReader(SelectListItem.class, "p_dropdown_product_category");
    }
}
