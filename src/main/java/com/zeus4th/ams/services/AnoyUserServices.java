package com.zeus4th.ams.services;

import com.zeus4th.ams.model.ano.models.AnoyUser;

import java.util.List;

public interface AnoyUserServices {

    List<AnoyUser> getAllAnoys();

    AnoyUser getByuserId(String userId);

    AnoyUser createAnoyUser(AnoyUser anoyUser);

}
