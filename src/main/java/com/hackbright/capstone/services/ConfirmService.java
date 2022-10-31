package com.hackbright.capstone.services;

import com.hackbright.capstone.dtos.ConfirmDto;
import com.hackbright.capstone.entities.Confirm;

import javax.transaction.Transactional;
import java.util.List;

public interface ConfirmService {
    List<ConfirmDto> findAll();

    List<ConfirmDto> findByProfile(Long profileid);
    ConfirmDto findById(Long id);

    @Transactional
    List<String> saveConfirmation(ConfirmDto confirmDto);
}
