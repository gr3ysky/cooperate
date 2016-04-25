package cooperate.web.controllers.admin;

import cooperate.app.business.producer.images.ProducerImageService;
import cooperate.app.business.producer.images.activateProducerImage.ActivateProducerImageCommand;
import cooperate.app.business.producer.images.addProducerImage.AddProducerImageCommand;
import cooperate.app.business.producer.images.getProducerImage.GetProducerImageRequest;
import cooperate.app.business.producer.images.updateProducerImage.UpdateProducerImageCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.producerImage.ProducerImageDto;
import cooperate.infrastructure.dto.producerImage.ProducerImageFilterDto;
import cooperate.infrastructure.dto.producerImage.ProducerImageListDto;
import cooperate.web.controllers.BaseController;
import cooperate.web.core.FileUtility;
import cooperate.web.core.FileUtilityBuilder;
import cooperate.web.core.HasPermission;
import cooperate.web.core.ImageValidator;
import cooperate.web.viewmodels.AjaxResponse;
import cooperate.web.viewmodels.producerImage.ProducerImageCreateModel;
import cooperate.web.viewmodels.producerImage.ProducerImageUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class AdminProducerImagesController extends BaseController {
    @Autowired
    ProducerImageService producerImageService;


    @HasPermission(to = {PermissionConstants.ProducerIndex})
    @RequestMapping("/admin/producer-images/{producerId}")
    public ModelAndView index(@PathVariable int producerId) {
        ModelAndView mav = new ModelAndView("admin/producerImages/index");
        mav.addObject("producerId", producerId);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProducerIndex})
    @RequestMapping(value = "/admin/producer-images/list")
    public ResponseEntity<ListResponse<ProducerImageListDto>> list(@Valid @ModelAttribute("request") ListRequest<ProducerImageFilterDto, ProducerImageListDto> request, @ModelAttribute("filter") ProducerImageFilterDto filter, BindingResult result) throws Exception {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        request.setFilter(filter);
        ListResponse<ProducerImageListDto> response = producerImageService.ListProducerImages(request);
        return new ResponseEntity<ListResponse<ProducerImageListDto>>(response, httpHeaders, HttpStatus.OK);

    }

    @HasPermission(to = {PermissionConstants.ProducerIndex})
    @RequestMapping(value = "/admin/producer-images/create/{producerId}")
    public ModelAndView create(@PathVariable int producerId) throws Exception {
        ModelAndView mav = new ModelAndView("admin/producerImages/create");
        ProducerImageCreateModel model = new ProducerImageCreateModel();
        model.setProducerId(producerId);
        mav.addObject("producerImage", model);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProducerIndex})
    @RequestMapping(value = "/admin/producer-images/update/{producerId}/{producerImageId}")
    public ModelAndView update(@PathVariable int producerId, @PathVariable int producerImageId) throws Exception {
        ModelAndView mav = new ModelAndView("admin/producerImages/update");
        GetProducerImageRequest request = (GetProducerImageRequest) context.getBean("getProducerImageRequest");
        request.setProducerId(producerId);
        request.setProducerImageId(producerImageId);
        ProducerImageDto dto = producerImageService.GetProducerImageDto(request);
        ProducerImageUpdateModel model = new ProducerImageUpdateModel();
        model.setProducerImageId(producerImageId);
        model.setImageUrl(dto.getImageUrl());
        model.setAltText(dto.getAltText());
        model.setOrderNo(dto.getOrderNo());
        model.setProducerId(producerId);
        model.setActive(dto.getIsActive());
        mav.addObject("producerImage", model);
        return mav;
    }


    @HasPermission(to = {PermissionConstants.ProducerIndex})
    @RequestMapping(value = "/admin/producer-images/createProducerImage", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createProducerImage(@Valid @ModelAttribute("producerImage") ProducerImageCreateModel producerImageCreateModel, BindingResult result, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("admin/producerImages/create");
        if (result.hasErrors()) {
            mav.addObject("producerImage", producerImageCreateModel);
            return mav;
        }
        String imageUrl = null;
        if (!producerImageCreateModel.getImage().isEmpty() && ImageValidator.isValid(producerImageCreateModel.getImage())) {
            //Save image
            FileUtility fileUtility = new FileUtilityBuilder()
                    .setFile(producerImageCreateModel.getImage())
                    .setRoot(request.getServletContext().getRealPath("/"))
                    .build();
            fileUtility.saveFile();
            imageUrl = fileUtility.getFileUrl();
        }

        AddProducerImageCommand command = (AddProducerImageCommand) context.getBean("addProducerImageCommand");
        command.setActive(producerImageCreateModel.isActive());
        command.setAltText(producerImageCreateModel.getAltText());
        command.setCreateUserId(getSessionUser().UserId);
        command.setImageUrl(imageUrl);
        command.setProducerId(producerImageCreateModel.getProducerId());
        command.setOrderNo(producerImageCreateModel.getOrderNo());
        producerImageService.AddProducerImage(command);
        ProducerImageCreateModel model = new ProducerImageCreateModel();
        model.setProducerId(producerImageCreateModel.getProducerId());
        mav.addObject("producerImage", model);
        mav.addObject("message", context.getMessage("message.genericSuccess", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }


    @HasPermission(to = {PermissionConstants.ProducerIndex})
    @RequestMapping(value = "/admin/producer-images/enable", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AjaxResponse> enable(@RequestParam int producerImageId) throws Exception {
        ActivateProducerImageCommand command = (ActivateProducerImageCommand) context.getBean("activateProducerImageCommand");
        command.setProducerImageId(producerImageId);
        command.setUpdateUserId(getSessionUser().UserId);
        producerImageService.ActivateProducerImage(command);
        AjaxResponse response = getAjaxResponse("message.genericSuccess", "success");
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<AjaxResponse>(response, httpHeaders, HttpStatus.OK);
    }

    @HasPermission(to = {PermissionConstants.ProducerIndex})
    @RequestMapping(value = "/admin/producer-images/updateProducerImage", method = RequestMethod.POST, produces = "application/json")
    public ModelAndView updateProducerImage(@Valid @ModelAttribute("producerImage") ProducerImageUpdateModel producerImageUpdateModel, BindingResult result, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("admin/producerImages/update");
        if (result.hasErrors()) {
            mav.addObject("producerImage", producerImageUpdateModel);
            return mav;
        }
        String imageUrl = null;
        if (!producerImageUpdateModel.getImage().isEmpty() && ImageValidator.isValid(producerImageUpdateModel.getImage())) {
            //Save image
            FileUtility fileUtility = new FileUtilityBuilder()
                    .setFile(producerImageUpdateModel.getImage())
                    .setRoot(request.getServletContext().getRealPath("/"))
                    .build();
            fileUtility.saveFile();
            imageUrl = fileUtility.getFileUrl();
        }
        UpdateProducerImageCommand command = (UpdateProducerImageCommand) context.getBean("updateProducerImageCommand");
        command.setActive(producerImageUpdateModel.isActive());
        command.setAltText(producerImageUpdateModel.getAltText());
        command.setUpdateUserId(getSessionUser().UserId);
        command.setImageUrl(imageUrl);
        command.setProducerImageId(producerImageUpdateModel.getProducerImageId());
        command.setOrderNo(producerImageUpdateModel.getOrderNo());
        command.setProducerId(producerImageUpdateModel.getProducerId());
        producerImageService.UpdateProducerImage(command);
        producerImageUpdateModel.setImageUrl(imageUrl);
        mav.addObject("producerImage", producerImageUpdateModel);
        mav.addObject("message", context.getMessage("message.genericSuccess", null, Locale.getDefault()));
        mav.addObject("status", "success");
        return mav;
    }
}
