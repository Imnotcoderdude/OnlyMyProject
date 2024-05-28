package com.sparta.onlymyproject.repository;

import com.sparta.onlymyproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
