package com.alanjsantos.challenge.repository;

import com.alanjsantos.challenge.models.Wather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatherRepository extends JpaRepository<Wather, Integer> {
}
