package service.impl;

import DAO.IntroductionDAO;
import domain.Introduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IntroductionService;

/**
 * Created by zky on 2017/1/12.
 */
@Service("IntroductionService")
public class IntroductionServiceImpl implements IntroductionService{

    @Autowired
    IntroductionDAO introductionDAO;

    public void add(Introduction introduction) {
        introductionDAO.add(introduction);
    }

    public void update(Introduction introduction) {
        Introduction oldIntroduction = introductionDAO.get();
        oldIntroduction.setConstitution(introduction.getConstitution());
        oldIntroduction.setIntroduction(introduction.getIntroduction());
        oldIntroduction.setRule(introduction.getRule());
        introductionDAO.update(oldIntroduction);
    }

    public Introduction get() {
        return introductionDAO.get();
    }
}
