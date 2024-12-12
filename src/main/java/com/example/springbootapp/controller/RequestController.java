package com.example.springbootapp.controller;

import com.example.springbootapp.service.RequestService;
import com.example.springbootapp.util.RequestStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/{requestId}-update-status")
    public void updateStatus(@PathVariable Integer requestId, @RequestParam("status") RequestStatusEnum status) {
        requestService.updateStatus(requestId, status);
    }
}
