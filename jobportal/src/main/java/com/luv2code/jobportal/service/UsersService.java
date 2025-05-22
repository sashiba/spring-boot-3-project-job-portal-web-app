package com.luv2code.jobportal.service;

import com.luv2code.jobportal.entity.JobSeekerProfile;
import com.luv2code.jobportal.entity.RecruiterProfile;
import com.luv2code.jobportal.entity.Users;
import com.luv2code.jobportal.repository.JobSeekerProfileRepository;
import com.luv2code.jobportal.repository.RecruiterProfileRepository;
import com.luv2code.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository,
                        RecruiterProfileRepository recruiterProfileRepository) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public Users addNew(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        int userTypeId = users.getUserTypeId().getUserTypeId();
        Users savedUsers = usersRepository.save(users);
        if (userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(savedUsers));
        } else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUsers));
        }

        return savedUsers;
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
