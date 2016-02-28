package cooperate.app.data.read;

import cooperate.infrastructure.dto.MenuDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuReadRepository extends Database {

    public List<MenuDto> getMenu() throws Exception {
        return exetuteListReader(MenuDto.class, "p_list_menu");
    }
}
