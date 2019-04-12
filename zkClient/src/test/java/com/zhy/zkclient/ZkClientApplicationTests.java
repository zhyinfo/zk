package com.zhy.zkclient;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkClientApplicationTests {

	private static final String ZK_SERVER_BROKER = "120.79.135.213:2180,120.79.135.213:2181,120.79.135.213:2182";
	
	private ZooKeeper zkClint;

	@Before
	public void init() throws Exception {
		zkClint = new ZooKeeper(ZK_SERVER_BROKER, 2000, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
//				System.out.println( "收到事件通知：" + event.getState());
				
				
//				try {
//					System.out.println("------------START---------------");
//					List<String> children = zkClint.getChildren("/", true);
//					children.parallelStream().forEach(System.out::println);
//					System.out.println("-------------END----------------");
//				} catch (KeeperException e) {
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		});	
	}

	
	@Test
	public void createNode() throws Exception {
		
		String node = zkClint.create("/sanguo", "xiaoqiao".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		
		System.out.println(node);
		
	}
	
	//获取子节点，并监听节点变化
	@Test
	public void getChildNode() throws Exception {
		
		List<String> children = zkClint.getChildren("/", true);
		children.parallelStream().forEach(System.out::println);
		
		Thread.sleep(Long.MAX_VALUE);
	}
	
	//判断节点是否存在
	@Test
	public void isExist() throws Exception {
		
		Stat stat = zkClint.exists("/sanguo", false);
		
		System.out.println(stat == null ? "not exist" : "exist");
	}
	
	
}
