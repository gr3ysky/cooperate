package cooperate.app.business.product;

import cooperate.app.ServiceBase;
import cooperate.app.business.product.activateProduct.ActivateProductCommand;
import cooperate.app.business.product.addProduct.AddProductCommand;
import cooperate.app.business.product.getProduct.GetProductRequest;
import cooperate.app.business.product.listProducts.ListProductsRequestHandler;
import cooperate.app.business.product.updateProduct.UpdateProductCommand;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.product.ProductDto;
import cooperate.infrastructure.dto.product.ProductFilterDto;
import cooperate.infrastructure.dto.product.ProductListDto;
import cooperate.infrastructure.mediation.Mediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService extends ServiceBase {
    @Autowired
    Mediator mediator;

    @Transactional(readOnly = true)
    public ListResponse<ProductListDto> ListProducts(ListRequest<ProductFilterDto, ProductListDto> request) throws Exception {
        request.setHandler((ListProductsRequestHandler) context.getBean("listProductsRequestHandler"));
        return mediator.request(request);
    }

    @Transactional(readOnly = true)
    public ProductDto GetProductDto(GetProductRequest request) throws Exception {
        return mediator.request(request);
    }

    @Transactional
    public void AddProduct(AddProductCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void ActivateProduct(ActivateProductCommand command) throws Exception {
        mediator.send(command);
    }

    @Transactional
    public void UpdateProduct(UpdateProductCommand command) throws Exception {
        mediator.send(command);
    }
}
