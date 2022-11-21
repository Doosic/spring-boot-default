package com.setup.nuxtspringdefaultsetup.controller;

import com.setup.nuxtspringdefaultsetup.common.APIDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/sv/main")
@RestController
public class MainController {


    @GetMapping("/test")
    public APIDataResponse test() {
        ArrayList<Map> dummyDatas = new ArrayList<>();

        for (int i = 0; i < 30; i++){
            Map<String, String> response = new HashMap<>();
            response.put("id", i + "");
            response.put("name", "Alice: " + i);
            response.put("age", i + "");
            response.put("imageUrl", "http://placeimg.com/640/480/fashion");
            dummyDatas.add(response);
        }

        return APIDataResponse.of(dummyDatas);
    }
}
