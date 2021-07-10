package com.example.nguyenchihao.repository;

import com.example.nguyenchihao.entity.ReceiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveRepository extends JpaRepository<ReceiveEntity, Integer> {
}
