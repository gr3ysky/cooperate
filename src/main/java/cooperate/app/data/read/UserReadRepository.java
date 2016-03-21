package cooperate.app.data.read;


import cooperate.app.business.user.login.LoginDto;
import cooperate.infrastructure.dto.ListRequest;
import cooperate.infrastructure.dto.ListResponse;
import cooperate.infrastructure.dto.UserDto;
import cooperate.infrastructure.dto.UserFilterDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserReadRepository extends Database {
    public LoginDto getLoginDtobyEmail(String email) throws Exception {
        LoginDto loginDto = exetuteReader(LoginDto.class, "p_get_user_by_email", email);
        return loginDto;
    }

    public ListResponse<UserDto> ListUsers(ListRequest<UserFilterDto, UserDto> request) throws Exception {
        ListResponse<UserDto> users = new ListResponse<UserDto>();
        List<UserDto> list = new ArrayList<UserDto>();
        list = exetuteListReader(UserDto.class, "p_list_users_grid",
                request.getFilter().getFullname(),
                SetNull(request.getFilter().getIsActive()),
                request.getStart(),
                request.getPageSize(),
                request.getOrderColumn(),
                request.getOrderDir(),
                null,
                null
        );
        users.setData(list);
        users.setDraw(request.getDraw());
        users.setRecordsFiltered((Long) getOutputValue("p_filtered_total"));
        users.setRecordsTotal((Long) getOutputValue("p_total"));
        return users;
    }

    public boolean emailExists(String email) throws Exception {
        return exetuteScalar(Boolean.class, "p_exists_email", email);
    }
}
