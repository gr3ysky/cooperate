package cooperate.infrastructure.security;

import cooperate.infrastructure.dto.RolePermissionDto;

import java.util.List;

public class PermissionManager {
    public static boolean CheckPermission(int roleId, String permissionCode, List<RolePermissionDto> rolePermissionDtos) {
        boolean hasPermission = false;
        for (RolePermissionDto dto : rolePermissionDtos) {
            if (dto.getPermissionCode().equals(permissionCode) && dto.getRoleId() == roleId) {
                hasPermission = true;
                break;
            }
        }

        return hasPermission;
    }
}
