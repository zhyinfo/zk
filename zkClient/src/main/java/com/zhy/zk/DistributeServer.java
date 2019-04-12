package com.zhy.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs.Ids;

public class DistributeServer extends AbsCommon{
	
	
	public static void main(String[] args) throws Exception {
		
		DistributeServer server = new DistributeServer();
		
		//连接zookeeper集群
		server.getConnection();
		
		//注册节点
		server.register(args[0]);
		
		//业务逻辑处理
		server.business();
		
	}

	@Override
	protected void watchHander(WatchedEvent event) {

//		System.out.println(event.getPath() + " is offline!");
		
	}

	@Override
	protected void register(String ... hostname) throws Exception {
		
		String path = zkclient.create("/servers/server", hostname[0].getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		
		System.out.println(path + " is online!");
	}

}
