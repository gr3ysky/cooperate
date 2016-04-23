package cooperate.web.controllers.admin;

import cooperate.app.business.producer.ProducerService;
import cooperate.app.business.producer.activateProducer.ActivateProducerCommand;
import cooperate.app.business.producer.addProducer.AddProducerCommand;
import cooperate.app.business.producer.getProducer.GetProducerRequest;
import cooperate.app.business.producer.updateProducer.UpdateProducerCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.producer.ProducerDto;
import cooperate.infrastructure.dto.producer.ProducerFilterDto;
import cooperate.infrastructure.dto.producer.ProducerListDto;
import cooperate.web.controllers.BaseController;
import cooperate.web.core.HasPermission;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.producer.ProducerCreateModel;
import cooperate.web.viewmodels.producer.ProducerUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class AdminProducersController extends BaseController {
    @Autowired
    ProducerService producerService;

    @HasPermission(to = {PermissionConstants.ProducerIndex})
    @RequestMapping("/admin/producers")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("admin/producers/index");
        mav.addObject("pageDescription", context.getMessage("page.description.admin.producers.index", null, Locale.getDefault()));
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProducerIndex})
    @RequestMapping(value = "/admin/producers/list")
    public ResponseEntity<ListResponse<ProducerListDto>> list(@Valid @ModelAttribute("request") ListRequest<ProducerFilterDto, ProducerListDto> request, @ModelAttribute("filter") ProducerFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        request.setFilter(filter);
        ListResponse<ProducerListDto> response = producerService.ListProducers(request);
        return new ResponseEntity<ListResponse<ProducerListDto>>(response, httpHeaders, HttpStatus.OK);

    }

    @HasPermission(to = {PermissionConstants.ProducerCreate})
    @RequestMapping(value = "/admin/producers/create")
    public ModelAndView create() throws Exception {
        ModelAndView mav = new ModelAndView("admin/producers/create");
        ProducerCreateModel model = new ProducerCreateModel();
        mav.addObject("producer", model);

        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProducerUpdate})
    @RequestMapping(value = "/admin/producers/update/{producerId}")
    public ModelAndView update(@PathVariable int producerId) throws Exception {
        ModelAndView mav = new ModelAndView("admin/producers/update");
        GetProducerRequest request = (GetProducerRequest) context.getBean("getProducerRequest");
        request.setProducerId(producerId);
        ProducerDto dto = producerService.GetProducerDto(request);
        ProducerUpdateModel model = new ProducerUpdateModel();
        model.setDescription(dto.getDescription());
        model.setLatitude(dto.getLatitude());
        model.setLongitude(dto.getLongitude());
        model.setMemberSince(dto.getMemberSince());
        model.setProducerId(producerId);
        model.setActive(dto.getIsActive());
        model.setName(dto.getName());
        mav.addObject("producer", model);
        return mav;
    }


    @HasPermission(to = {PermissionConstants.ProducerCreate})
    @RequestMapping(value = "/admin/producers/createProducer", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createProducer(@Valid @ModelAttribute("producer") ProducerCreateModel producerCreateModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("admin/producers/create");
        if (result.hasErrors()) {
            mav.addObject("producer", producerCreateModel);
            return mav;
        }
        AddProducerCommand command = (AddProducerCommand) context.getBean("AddProducerCommand");
        command.setActive(producerCreateModel.isActive());
        command.setDescription(producerCreateModel.getDescription());
        command.setName(producerCreateModel.getName());
        command.setLatitude(producerCreateModel.getLatitude());
        command.setLongitude(producerCreateModel.getLongitude());
        command.setMemberSince(producerCreateModel.getMemberSince());
        command.setCreateUserId(getSessionUser().UserId);
        producerService.AddProducer(command);
        mav.addObject("producer", new ProducerCreateModel());
        mav.addObject("message", context.getMessage("message.genericSuccess", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }


    @HasPermission(to = {PermissionConstants.ProducerDelete})
    @RequestMapping(value = "/admin/producers/enable", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AjaxResponse> enable(@RequestParam int producerId) throws Exception {
        ActivateProducerCommand command = (ActivateProducerCommand) context.getBean("activateProducerCommand");
        command.setProducerId(producerId);
        command.setUpdateUserId(getSessionUser().UserId);
        producerService.ActivateProducer(command);
        AjaxResponse response = getAjaxResponse("message.genericSuccess", "success");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.ProducerUpdate})
    @RequestMapping(value = "/admin/producers/updateProducer", method = RequestMethod.POST, produces = "application/json")
    public ModelAndView updateProducer(@Valid @ModelAttribute("producer") ProducerUpdateModel producerUpdateModel, BindingResult result) throws Exception {
        ModelAndView mav = new ModelAndView("admin/producers/update");
        if (result.hasErrors()) {
            mav.addObject("producer", producerUpdateModel);
            return mav;
        }
        UpdateProducerCommand command = (UpdateProducerCommand) context.getBean("updateProducerCommand");
        command.setActive(producerUpdateModel.isActive());
        command.setDescription(producerUpdateModel.getDescription());
        command.setName(producerUpdateModel.getName());
        command.setLatitude(producerUpdateModel.getLatitude());
        command.setLongitude(producerUpdateModel.getLongitude());
        command.setMemberSince(producerUpdateModel.getMemberSince());
        command.setProducerId(producerUpdateModel.getProducerId());
        command.setUpdateUserId(getSessionUser().UserId);
        producerService.UpdateProducer(command);
        mav.addObject("producer", producerUpdateModel);
        mav.addObject("message", context.getMessage("message.genericSuccess", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }
}
