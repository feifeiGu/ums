package com.oit.oitcloud.controller;

import com.oit.oitcloud.constants.OitUmsConstants;
import com.oit.oitcloud.dto.AreaDTO;
import com.oit.oitcloud.entity.RestResponse;
import com.oit.oitcloud.util.RedisHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author guff
 * @since 2020-06-24 14:52:26
 */
@Api(tags = "城市区域")
@RestController
@RequestMapping("cityArea")
public class AreaController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取城市区域列表
     * @return
     */
    @ApiOperation(value = "获取城市区域列表", notes = "获取城市区域列表", httpMethod = "GET", produces = "application/json")
    @GetMapping("list")
    public RestResponse getCityArea() {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        Map<String,String> countryEntries = redisTemplate.opsForHash().entries(OitUmsConstants.DISTRICT_COUNTRY);
        for(Map.Entry<String,String> countryEntry : countryEntries.entrySet()){
            AreaDTO countryDTO = new AreaDTO();
            countryDTO.setId(countryEntry.getKey());
            countryDTO.setName(countryEntry.getValue());
            areaDTOList.add(countryDTO);
            Map<String,String> provinceEntries = redisTemplate.opsForHash().entries(OitUmsConstants.DISTRICT_PROVINCE + countryEntry.getKey());
            for(Map.Entry<String,String> provinceEntry : provinceEntries.entrySet()){
                AreaDTO provinceDTO = new AreaDTO();
                provinceDTO.setId(provinceEntry.getKey());
                provinceDTO.setName(provinceEntry.getValue());
                provinceDTO.setPid(countryEntry.getKey());
                areaDTOList.add(provinceDTO);
                Map<String,String> cityEntries = redisTemplate.opsForHash().entries(OitUmsConstants.DISTRICT_CITY + provinceEntry.getKey());
                for(Map.Entry<String,String> cityEntry : cityEntries.entrySet()){
                    AreaDTO cityDTO = new AreaDTO();
                    cityDTO.setId(cityEntry.getKey());
                    cityDTO.setName(cityEntry.getValue());
                    cityDTO.setPid(provinceEntry.getKey());
                    areaDTOList.add(cityDTO);
                }
            }
        }
        return RestResponse.succuess(areaDTOList);
    }

    /**
     * 获取城市区域列表(层级)
     * @return
     */
    @ApiOperation(value = "获取城市区域列表(层级)", notes = "获取城市区域列表(层级)", httpMethod = "GET", produces = "application/json")
    @GetMapping("listLevel")
    public RestResponse getCityAreaLevel() {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        Map<String,String> countryEntries = redisTemplate.opsForHash().entries(OitUmsConstants.DISTRICT_COUNTRY);
        for(Map.Entry<String,String> countryEntry : countryEntries.entrySet()){
            AreaDTO countryDTO = new AreaDTO();
            countryDTO.setId(countryEntry.getKey());
            countryDTO.setName(countryEntry.getValue());
            Map<String,String> provinceEntries = redisTemplate.opsForHash().entries(OitUmsConstants.DISTRICT_PROVINCE + countryEntry.getKey());
            List<AreaDTO> provinceList = new ArrayList<>();
            for(Map.Entry<String,String> provinceEntry : provinceEntries.entrySet()){
                AreaDTO provinceDTO = new AreaDTO();
                provinceDTO.setId(provinceEntry.getKey());
                provinceDTO.setName(provinceEntry.getValue());
                provinceDTO.setPid(countryEntry.getKey());
                provinceList.add(provinceDTO);
                Map<String,String> cityEntries = redisTemplate.opsForHash().entries(OitUmsConstants.DISTRICT_CITY + provinceEntry.getKey());
                List<AreaDTO> cityList = new ArrayList<>();
                for(Map.Entry<String,String> cityEntry : cityEntries.entrySet()){
                    AreaDTO cityDTO = new AreaDTO();
                    cityDTO.setId(cityEntry.getKey());
                    cityDTO.setName(cityEntry.getValue());
                    cityDTO.setPid(provinceEntry.getKey());
                    cityList.add(cityDTO);
                }
                provinceDTO.setAreaDTOList(cityList);
            }
            countryDTO.setAreaDTOList(provinceList);
            areaDTOList.add(countryDTO);
        }
        return RestResponse.succuess(areaDTOList);
    }
}