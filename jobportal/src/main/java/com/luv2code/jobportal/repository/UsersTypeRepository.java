package com.luv2code.jobportal.repository;

import com.luv2code.jobportal.entity.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {
}
