package com.oit.oitcloud.service.impl;

import com.oit.oitcloud.entity.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CallRestServiceImpl {

	private static final Logger LOG = LoggerFactory.getLogger(CallRestServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;
	
	public RestResponse restTemplateCallPost(String eventUrl, String jsonData) {
		LOG.info("=====[CallRestService.restTemplateCallPost]=====params:{}", jsonData);
		try {
			HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=utf-8");
            HttpEntity<String> entity = new HttpEntity<String>(jsonData, headers);
            LOG.info("=====[CallRestService.restTemplateCallPost]=====eventUrl:{}", eventUrl);
			String result = restTemplate.postForObject(eventUrl, entity, String.class);
			LOG.info("=====[CallRestService.restTemplateCallPost]=====result:{}", result);
			return RestResponse.succuess(result);
		}catch (Exception e) {
			LOG.error("=====[CallRestService.restTemplateCallPost]=====error:", e);
			return RestResponse.fail();
		}
	}

	public RestResponse restTemplateCallGet(String eventUrl) {
		try {
			LOG.info("=====[CallRestService.restTemplateCallGet]=====eventUrl:{}", eventUrl);
			String result = restTemplate.getForObject(eventUrl, String.class);
			LOG.info("=====[CallRestService.restTemplateCallGet]=====result:{}", result);
			return RestResponse.succuess(result);
		}catch (Exception e) {
			LOG.error("=====[CallRestService.restTemplateCallGet]=====error:", e);
			return RestResponse.fail();
		}
	}
}
