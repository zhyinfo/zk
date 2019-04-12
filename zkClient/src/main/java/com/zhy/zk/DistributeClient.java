package com.zhy.zk;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;

public class DistributeClient extends AbsCommon {

	public static void main(String[] args) throws Exception {
		
		DistributeClient client = new DistributeClient();
		
		//连接zookeeper集群
		client.getConnection();
		
		//注册监听
//		client.register();
		
		//业务逻辑处理
		client.business();
	}
	
	@Override
	protected void watchHander(WatchedEvent event) {
		try {
			register();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void register(String... hostname) throws Exception {

		List<String> children = zkclient.getChildren("/servers", true);

		ArrayList<String> nodesData = new ArrayList<>();
		
		children.forEach(node -> {
			try {
				
				byte[] data = zkclient.getData("/servers/" + node, false, null);
				
				nodesData.add(new String(data));
				
			} catch (KeeperException | InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(nodesData);
	}

}
