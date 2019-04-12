package com.zhy.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public abstract class AbsCommon {
	
	private static final String ZK_SERVER_BROKERS = "120.79.135.213:2180,120.79.135.213:2181,120.79.135.213:2182";;
	
	private static final int TIMEOUT = 2000;
	
	protected ZooKeeper zkclient;
	
	protected void getConnection() throws Exception {
		zkclient = new ZooKeeper(ZK_SERVER_BROKERS, TIMEOUT, new Watcher() {
			@Override
			public void process(WatchedEvent event) {

				watchHander(event);
				
			}
		});
	}

	protected abstract void watchHander(WatchedEvent event);
	
	protected abstract void register(String ...hostname) throws Exception;
	
	protected  void business() throws Exception {
		Thread.sleep(Long.MAX_VALUE);
	}
	
}
