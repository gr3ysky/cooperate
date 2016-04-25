package cooperate.web.controllers;

import cooperate.app.business.user.UserService;
import cooperate.app.business.user.getUser.GetUserRequest;
import cooperate.app.business.user.login.LoginDto;
import cooperate.app.business.user.member.MemberService;
import cooperate.app.business.user.member.addMember.AddMemberCommand;
import cooperate.app.business.user.member.getMember.GetMemberRequest;
import cooperate.app.business.user.member.updateMember.UpdateMemberCommand;
import cooperate.infrastructure.constant.PermissionConstants;
import cooperate.infrastructure.constant.SessionConstants;
import cooperate.infrastructure.dto.user.MemberDto;
import cooperate.infrastructure.dto.user.UserDto;
import cooperate.infrastructure.exception.CoopException;
import cooperate.web.core.FileUtility;
import cooperate.web.core.FileUtilityBuilder;
import cooperate.web.core.HasPermission;
import cooperate.web.core.ImageValidator;
import cooperate.web.viewmodels.Profile.ProfileCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ProfileController extends BaseController {
    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/profile/{userName}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView index(@PathVariable String userName) throws Exception {
        ModelAndView mav = new ModelAndView("profile/index");
        GetMemberRequest getMemberRequest = (GetMemberRequest) context.getBean("getMemberRequest");
        if (userName.isEmpty()) {
            getMemberRequest.setMemberId(getSessionUser().MemberId);
            getMemberRequest.setUserId(getSessionUser().UserId);
        } else {
            getMemberRequest.setUserName(userName);
        }
        MemberDto dto = memberService.getMemberDto(getMemberRequest);
        dto.setOwned(dto.getMemberId() == getSessionUser().MemberId);
        if (!dto.getIsPublic() && !dto.getOwned()) {
            throw new CoopException("error.profileIsNotPublic");
        }
        mav.addObject("profile", dto);
        return mav;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView index() throws Exception {
        ModelAndView mav = new ModelAndView("profile/index");
        GetMemberRequest getMemberRequest = (GetMemberRequest) context.getBean("getMemberRequest");
        getMemberRequest.setMemberId(getSessionUser().MemberId);
        getMemberRequest.setUserId(getSessionUser().UserId);
        MemberDto dto = memberService.getMemberDto(getMemberRequest);
        dto.setOwned(dto.getMemberId() == getSessionUser().MemberId);
        if (!dto.getIsPublic() && !dto.getOwned()) {
            throw new CoopException("error.profileIsNotPublic");
        }
        mav.addObject("profile", dto);
        return mav;
    }

    @HasPermission(to = PermissionConstants.ProfileCreate)
    @RequestMapping(value = "/profile/create", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("profile/create");
        if (getSessionUser().MemberId > 0) {
            mav.setViewName("redirect:/profile/update");
            return mav;
        }
        mav.addObject("profile", new ProfileCreateModel());
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProfileCreate})
    @RequestMapping(value = "/profile/createProfile", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView createProfile(@Valid @ModelAttribute("profile") ProfileCreateModel profileCreateModel, BindingResult result, HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView("profile/create");
        if (result.hasErrors()) {
            mav.addObject("profile", profileCreateModel);
            return mav;
        }
        String imageUrl = null;
        if (!profileCreateModel.getImage().isEmpty() && ImageValidator.isValid(profileCreateModel.getImage())) {
            //Save image
            FileUtility fileUtility = new FileUtilityBuilder()
                    .setFile(profileCreateModel.getImage())
                    .setRoot(request.getServletContext().getRealPath("/"))
                    .build();
            fileUtility.saveFile();
            imageUrl = fileUtility.getFileUrl();
        }
        AddMemberCommand command = (AddMemberCommand) context.getBean("addMemberCommand");
        command.setProfileName(profileCreateModel.getProfileName());
        command.setUserName(profileCreateModel.getUserName());
        command.setPicture(imageUrl);
        command.setUserId(getSessionUser().UserId);
        command.setProfileName(profileCreateModel.getProfileName());
        command.setPublic(profileCreateModel.getIsPublic());
        command.setVolunteerForPackaging(profileCreateModel.isVolunteerForPackaging());
        command.setVolunteerForSelling(profileCreateModel.isVolunteerForSelling());
        memberService.createMember(command);
        LoginDto login = getSessionUser();
        UserService userService = (UserService) context.getBean("userService");
        GetUserRequest getUserRequest = (GetUserRequest) context.getBean("getUserRequest");
        getUserRequest.setUserId(login.UserId);
        UserDto user = userService.GetUserDto(getUserRequest);
        login.MemberId = user.getMemberId();
        session.setAttribute(SessionConstants.User, login);
        mav.setViewName("redirect:/profile");
        return mav;
    }

    @HasPermission(to = PermissionConstants.ProfileUpdate)
    @RequestMapping(value = "/profile/update", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView update() throws Exception {
        ModelAndView mav = new ModelAndView("profile/update");
        GetMemberRequest getMemberRequest = (GetMemberRequest) context.getBean("getMemberRequest");
        getMemberRequest.setMemberId(getSessionUser().MemberId);
        getMemberRequest.setUserId(getSessionUser().UserId);
        MemberDto dto = memberService.getMemberDto(getMemberRequest);
        ProfileCreateModel model = new ProfileCreateModel();
        model.setProfileName(dto.getProfileName());
        model.setVolunteerForSelling(dto.getVolunteerForSelling());
        model.setPicture(dto.getPicture());
        model.setUserName(dto.getUserName());
        model.setIsPublic(dto.getIsPublic());
        model.setVolunteerForPackaging(dto.getVolunteerForPackaging());
        mav.addObject("profile", model);
        return mav;
    }

    @HasPermission(to = {PermissionConstants.ProfileCreate})
    @RequestMapping(value = "/profile/updateProfile", method = RequestMethod.POST, produces = "text/html")
    public ModelAndView updateProfile(@Valid @ModelAttribute("profile") ProfileCreateModel profileCreateModel, BindingResult result, HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView("profile/create");
        if (result.hasErrors()) {
            mav.addObject("profile", profileCreateModel);
            return mav;
        }
        String imageUrl = null;
        if (!profileCreateModel.getImage().isEmpty() && ImageValidator.isValid(profileCreateModel.getImage())) {
            //Save image
            FileUtility fileUtility = new FileUtilityBuilder()
                    .setFile(profileCreateModel.getImage())
                    .setRoot(request.getServletContext().getRealPath("/"))
                    .build();
            fileUtility.saveFile();
            imageUrl = fileUtility.getFileUrl();
        }
        UpdateMemberCommand command = (UpdateMemberCommand) context.getBean("updateMemberCommand");
        command.setProfileName(profileCreateModel.getProfileName());
        command.setUserName(profileCreateModel.getUserName());
        command.setPicture(imageUrl);
        command.setUserId(getSessionUser().UserId);
        command.setProfileName(profileCreateModel.getProfileName());
        command.setPublic(profileCreateModel.getIsPublic());
        command.setVolunteerForPackaging(profileCreateModel.isVolunteerForPackaging());
        command.setVolunteerForSelling(profileCreateModel.isVolunteerForSelling());
        command.setMemberId(getSessionUser().MemberId);
        memberService.updateMember(command);
        mav.setViewName("redirect:/profile");
        return mav;
    }

}
