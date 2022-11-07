package com.hackbright.capstone.controllers;

import com.hackbright.capstone.dtos.ConfirmDto;
import com.hackbright.capstone.dtos.ConfirmPolityDto;
import com.hackbright.capstone.services.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/confirm")
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @PostMapping("/save")
    public List<String> saveConfirmation(@RequestBody ConfirmDto confirmDto) {
        return confirmService.saveConfirmation(confirmDto);
    }

    @GetMapping("/{id}")
    public ConfirmDto getConfirmByid(@PathVariable Long id) {
        return confirmService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteConfirmById(@PathVariable Long id) {
        confirmService.deleteById(id);
    }

    @GetMapping("/profile/{id}")
    public List<ConfirmPolityDto> getConfirmByProfileid(@PathVariable Long id) {
        List<ConfirmPolityDto> confirmPolityDtos = confirmService.getConfirmsByProfile(id);
        for (ConfirmPolityDto confirmPolityDto : confirmPolityDtos) {
            confirmPolityDto.setStartDateDisplay(convertDatetoString(confirmPolityDto.getStartDate()));
            confirmPolityDto.setEndDateDisplay(convertDatetoString(confirmPolityDto.getEndDate()));
        }
        return confirmPolityDtos;
    }

    public String convertDatetoString(Date dt) {
        String strDate = dt.toString();
//        strDate.substring(0,10);

        return strDate.substring(0, 10);

    }
}
