package com.example.nguyenchihao.service;

import com.example.nguyenchihao.entity.ReceiveEntity;

import java.util.List;
import java.util.Optional;

public interface ReceiveService {
    List<ReceiveEntity> getAll(int pageNo, int pageSize);

    Optional<ReceiveEntity> findById(Integer id);

    void save(ReceiveEntity entity);

    void deleteById(Integer id);


}
