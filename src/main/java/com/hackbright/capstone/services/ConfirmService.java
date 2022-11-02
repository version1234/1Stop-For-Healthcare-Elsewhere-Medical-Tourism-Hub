package com.hackbright.capstone.services;

import com.hackbright.capstone.dtos.ConfirmDto;
import com.hackbright.capstone.dtos.ConfirmPolityDto;
import com.hackbright.capstone.entities.Confirm;

import javax.transaction.Transactional;
import java.util.List;

public interface ConfirmService {
    List<ConfirmDto> findAll();

    List<ConfirmDto> findByProfile(Long profileid);

    List<ConfirmPolityDto> getConfirmsByProfile(Long profileid);
    ConfirmDto findById(Long id);
    @Transactional
    void deleteById(Long id);
    @Transactional
    List<String> saveConfirmation(ConfirmDto confirmDto);
}
