package com.welearn;

import com.welearn.entity.po.common.LinkCode;
import com.welearn.entity.qo.common.LinkCodeQueryCondition;
import com.welearn.feign.common.LinkCodeFeignClient;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.ImageFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WelearnStorageServerApplication.class)
public class WelearnStorageServerApplicationTests {

	@Autowired
	private ImageFileService imageFileService;

	@Autowired
	private LinkCodeFeignClient linkCodeFeignClient;

	@Test
	public void contextLoads() {
//		LinkCodeQueryCondition condition = new LinkCodeQueryCondition();
//		condition.setIsEnable(true);
//		List<LinkCode> linkCodes = linkCodeFeignClient.search(condition).getData();
//		imageFileService.generateLinkCodeQrCodeImage(linkCodes);
	}

}
