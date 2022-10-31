package com.hackbright.capstone.controllers;

import com.hackbright.capstone.dtos.ConfirmDto;
import com.hackbright.capstone.dtos.ProfileDto;
import com.hackbright.capstone.entities.Confirm;
import com.hackbright.capstone.entities.Policy;
import com.hackbright.capstone.services.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/confirm")
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @PostMapping("/save")
    public List<String> saveConfirmation(@RequestBody ConfirmDto confirmDto){
        return confirmService.saveConfirmation(confirmDto);
    }

    @GetMapping("/{id}")
    public ConfirmDto getConfirmByid(@PathVariable Long id){
        return confirmService.findById(id);
    }

    @GetMapping("/profile/{id}")
    public List<ConfirmDto>  getConfirmByProfileid(@PathVariable Long id){
        return confirmService.findByProfile(id);
    }

}
