package com.example.nguyenchihao.service;

import com.example.nguyenchihao.entity.ReceiveEntity;
import com.example.nguyenchihao.repository.ReceiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiveServiceImpl implements ReceiveService {
    @Autowired
    private ReceiveRepository repository;

    @Override
    public List<ReceiveEntity> getAll(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<ReceiveEntity> pageResult =  repository.findAll(paging);
        return pageResult.toList();
    }

    @Override
    public void save(ReceiveEntity entity) {
        repository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ReceiveEntity> findById(Integer id) {
        return repository.findById(id);
    }
}
