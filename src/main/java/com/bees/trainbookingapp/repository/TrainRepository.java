package com.bees.trainbookingapp.repository;

import com.bees.trainbookingapp.domain.Train;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long>
{
}
