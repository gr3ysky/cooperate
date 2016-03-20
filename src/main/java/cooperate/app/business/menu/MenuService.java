package cooperate.app.business.menu;

import cooperate.app.ServiceBase;
import cooperate.app.data.read.MenuReadRepository;
import cooperate.infrastructure.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService extends ServiceBase {
    @Autowired
    MenuReadRepository menuReadRepository;
    @Transactional
    @Cacheable(value = "menuDtos")
    public List<MenuDto> getMenu() throws Exception {
        return menuReadRepository.getMenu();
    }

}
