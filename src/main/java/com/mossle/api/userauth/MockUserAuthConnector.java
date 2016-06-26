package com.mossle.api.userauth;

public class MockUserAuthConnector implements UserAuthConnector {
    public UserAuthDTO findByUsername(String username, String tenantId) {
        UserAuthDTO userAuthDto = new UserAuthDTO();
        userAuthDto.setId(username);
        userAuthDto.setUsername(username);
        userAuthDto.setDisplayName(username);
        userAuthDto.setEnabled(true);
        return userAuthDto;
    }

    public UserAuthDTO findByRef(String ref, String tenantId) {
    		return findByUsername(ref,tenantId);
    }

    public UserAuthDTO findById(String id, String tenantId) {
    		return findByUsername(id,tenantId);
    }
}
