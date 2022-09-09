package com.zeus4th.ams.services.implementations;

import com.zeus4th.ams.model.ano.models.AnoyUser;
import com.zeus4th.ams.repository.ano.repository.AnoyUserRepository;
import com.zeus4th.ams.services.AnoyUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnoyUserServiceImple implements AnoyUserServices {

    private AnoyUserRepository anoyUserRepository;

    @Autowired
    public AnoyUserServiceImple(AnoyUserRepository anoyUserRepository) {
        this.anoyUserRepository = anoyUserRepository;
    }

    @Override
    public List<AnoyUser> getAllAnoys() {
        return anoyUserRepository.findAll();
    }

    @Override
    public AnoyUser getByuserId(String userId) {
        return anoyUserRepository.findByUserId(userId);
    }

    @Override
    public AnoyUser createAnoyUser(AnoyUser anoyUser) {
        return anoyUserRepository.save(anoyUser);
    }
}
