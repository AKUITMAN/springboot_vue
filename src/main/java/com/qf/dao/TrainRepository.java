package com.qf.dao;

import com.qf.pojo.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train,Integer> {
}
