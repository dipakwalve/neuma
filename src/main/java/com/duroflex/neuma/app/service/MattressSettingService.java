package com.duroflex.neuma.app.service;

import com.duroflex.neuma.app.exception.exceptionutils.ResourceAlreadyExists;
import com.duroflex.neuma.app.exception.exceptionutils.ResourceNotFoundException;
import com.duroflex.neuma.app.model.ActorDetails;
import com.duroflex.neuma.app.model.MattressDetails;
import com.duroflex.neuma.app.repository.MattressSettingRepository;
import com.duroflex.neuma.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class MattressSettingService {

    @Autowired
    private final MattressSettingRepository settingRepository;

    @Autowired
    private final UserRepository userRepository;

    Logger log = LoggerFactory.getLogger(MattressSettingService.class);

    public MattressSettingService(MattressSettingRepository settingRepository, UserRepository userRepository) {

        this.settingRepository = settingRepository;
        this.userRepository = userRepository;
    }

    public String getOccupySideList(String mattressIdentifier, Integer userId) {
        MattressDetails side = null;
        try {
            if (userId == null) {
                userId = 0;
            }
            side = settingRepository.findByMattressIdentifier(mattressIdentifier, userId);
            log.info(side.getMattressSide());
        } catch (Exception exception) {
            throw new ResourceNotFoundException("DATA NOT FOUND,PLEASE CHECK INPUT DATA " + exception.getMessage());
        }
        return side.getMattressSide();
    }

    public MattressDetails addMattress(MattressDetails details) {
        List<MattressDetails> listSide = null;
        MattressDetails mattressDetails = null;
        String errorMessage = null;
        listSide = settingRepository.findMattressIdentifier(details.getMattressIdentifier());
        for (MattressDetails objMattress : listSide) {
            try {
                if (objMattress.getMattressSide().equalsIgnoreCase(details.getMattressSide())) {
                    errorMessage = details.getMattressSide().toUpperCase(Locale.ROOT) + " SIDE ALREADY ALLOCATED,PLEASE CHOOSE OTHER SIDE  ";
                    throw new ResourceNotFoundException(errorMessage);
                }
            } catch (Exception re) {
                throw new ResourceAlreadyExists(re.getMessage());
            }
        }
        if (details.getUserId() != null) {
            details.setMattressSideName(setSideName(details.getUserId()));
        }
        mattressDetails = settingRepository.save(details);

        return mattressDetails;

    }

    public MattressDetails changeMattressSide(Integer userId, String mattressSide) {
        MattressDetails mattressDetails = null;
        MattressDetails changeMattressSide = null;
        List<MattressDetails> listMattressDetails = null;
        try {
            mattressDetails = settingRepository.findByUserId(userId);
            listMattressDetails = settingRepository.findMattressIdentifier(mattressDetails.getMattressIdentifier());
            for (MattressDetails mattress : listMattressDetails) {
                if (mattress.getUserId().equals(userId)) {
                    settingRepository.updateSideDetails(mattress.getMattressId(), mattressSide);
                    mattressSide = mattress.getMattressSide();
                } else {
                    settingRepository.updateSideDetails(mattress.getMattressId(), mattressSide);
                }
                changeMattressSide = settingRepository.findByUserId(userId);

            }
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

        return changeMattressSide;
    }

    // get side names
    private String setSideName(Integer userId) {
        ActorDetails actor;
        String actorName = null;
        try {
            actor = userRepository.findByUserId(userId);
            actorName = actor.getFullName();
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return actorName;
    }

    public Optional<MattressDetails> getMattressSideNames(String mattressIdentifier, String mattressSide, Integer userId) {
        Optional<MattressDetails> mattressDetailsList = null;
        try {
            setSideName(userId);
            mattressDetailsList = settingRepository.findByMattressIdentifierAndSide(mattressIdentifier, mattressSide);
            if (mattressDetailsList.get().getMattressSideName() == null) {
                setSideName(userId);
            } else{
                mattressDetailsList = settingRepository.findByMattressIdentifierAndSide(mattressIdentifier, mattressSide);
        }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return mattressDetailsList;
    }

    public String changeMattressSide(Integer userId, Integer mattressId, String mattressSideName) {
        Integer mattressSetup = settingRepository.modifyMattressSideNames(mattressId, userId, mattressSideName);
        try {
            if (mattressSetup > 0) {
                Integer userStatus = userRepository.updateFullName(userId, mattressSideName);
                log.info("User and Mattress Details updated ");
                return "Mattress Side name updated";

            } else {
                return "Data not found ";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Integer countMattressUser(String mattressIdentifier) {
        return settingRepository.countMattressIdentifier(mattressIdentifier);
    }
}
