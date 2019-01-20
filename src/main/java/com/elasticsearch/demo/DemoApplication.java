package com.elasticsearch.demo;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;

import org.elasticsearch.index.get.GetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
public class DemoApplication {

	@Autowired
    private TransportClient client;

	@GetMapping("/get/book/novel")
    @ResponseBody
    public ResponseEntity get(@RequestParam(name = "id", defaultValue = "") String id) {
        GetResponse result = this.client.prepareGet("book", "novel", id).get();
        if(!result.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

	    return new ResponseEntity(result.getSource(), HttpStatus.OK);
    }


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

