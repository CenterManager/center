package com.jyjx.yxdl.mapper;

import com.jyjx.yxdl.common.DataBaseType;
import com.jyjx.yxdl.common.DataSource;
import com.jyjx.yxdl.entity.GameServer;

import java.util.List;

public interface GameServerMapper {

    public List<GameServer> getGameServer(int appId);
    public GameServer findServerById(int serverId);

}
