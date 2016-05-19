package cooperate.web.controllers;

import cooperate.app.business.producer.ProducerService;
import cooperate.app.business.producer.getProducer.GetProducerRequest;
import cooperate.app.business.producer.images.ProducerImageService;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.producer.ProducerDto;
import cooperate.infrastructure.dto.producer.ProducerFilterDto;
import cooperate.infrastructure.dto.producer.ProducerListDto;
import cooperate.infrastructure.dto.producerImage.ProducerImageFilterDto;
import cooperate.infrastructure.dto.producerImage.ProducerImageListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

@Controller
public class ProducersController extends BaseController {
    @Autowired
    ProducerService producerService;
    @Autowired
    ProducerImageService producerImageService;

    @RequestMapping(value = "/producers/{pageId}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView index(HttpSession session, @PathVariable("pageId") String pageId) throws Exception {
        ModelAndView model = new ModelAndView("producers/index");
        int page = 1;
        if (pageId != null) {
            page = Integer.parseInt(pageId);
        }
        if (page <= 1)
            page = 0;

        ListRequest<ProducerFilterDto, ProducerListDto> request = new ListRequest<ProducerFilterDto, ProducerListDto>();
        request.setStart(page);
        request.setPageSize(10);
        request.setDraw(0);
        request.setOrderColumn("0");
        request.setOrderDir("desc");
        ProducerFilterDto filterDto = new ProducerFilterDto();
        filterDto.setIsActive(1);
        //filterDto.setName("");
        request.setFilter(filterDto);
        List<ProducerListDto> list = producerService.ListProducers(request).getData();
        model.addObject("list", list);
        return model;
    }

    @RequestMapping(value = "/producers/view/{producerId}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView detail(@PathVariable("producerId") int producerId) throws Exception {
        ModelAndView model = new ModelAndView("producers/detail");
        GetProducerRequest request = (GetProducerRequest) context.getBean("getProducerRequest");
        request.setProducerId(producerId);

        ProducerDto dto = producerService.GetProducerDto(request);
        model.addObject("dto", dto);
        ListRequest<ProducerImageFilterDto, ProducerImageListDto> request2 = new ListRequest<ProducerImageFilterDto, ProducerImageListDto>();
        request2.setStart(0);
        request2.setPageSize(10);
        request2.setDraw(0);
        request2.setOrderColumn("0");
        request2.setOrderDir("desc");
        ProducerImageFilterDto filterDto = new ProducerImageFilterDto();
        filterDto.setIsActive(1);
        filterDto.setProducerId(producerId);
        request2.setFilter(filterDto);
        List<ProducerImageListDto> images = producerImageService.ListProducerImages(request2).getData();
        sort(images, new Comparator<ProducerImageListDto>() {
            public int compare(ProducerImageListDto i1, ProducerImageListDto i2) {
                return i2.getOrderNo() - i1.getOrderNo();
            }
        });
        model.addObject("images", images);
        return model;
    }

}
