package com.shouse.restapi.service.user;

import com.shouse.restapi.domain.NodeInfoExtended;

import java.util.List;

public interface UsersService {

    public List<NodeInfoExtended> getActiveNodes();

    public List<NodeInfoExtended> getAllNodes();

    public UserResponse handleUserRequest(UserRequest userRequest);
}
