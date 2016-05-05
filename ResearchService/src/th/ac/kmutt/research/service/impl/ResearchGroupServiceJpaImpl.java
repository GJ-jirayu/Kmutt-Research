package th.ac.kmutt.research.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import th.ac.kmutt.research.domain.ResearchGroup;
import th.ac.kmutt.research.repository.ResearchRepository;
import th.ac.kmutt.research.service.ResearchGroupService;
import th.ac.kmutt.research.xstream.common.Paging;

@Service("researchGroupServiceJpaImpl")
public class ResearchGroupServiceJpaImpl implements ResearchGroupService {


    @Autowired
    @Qualifier("researchRepository")
    private ResearchRepository researchRepository;

    @Override
    public Integer saveResearchGroup(ResearchGroup transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer updateResearchGroup(ResearchGroup transientInstance,
                                       String section) throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer updateResearchGroupPhoto(ResearchGroup transientInstance,
                                            String section) throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer deleteResearchGroup(ResearchGroup persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResearchGroup findResearchGroupById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List searchResearchGroup(ResearchGroup persistentInstance,
                                    Paging pagging) throws DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }

}