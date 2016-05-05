package th.ac.kmutt.research.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.ac.kmutt.research.domain.ResearchGroup;
import th.ac.kmutt.research.xstream.common.Paging;


public interface ResearchGroupService {
    public Integer saveResearchGroup(ResearchGroup transientInstance) throws DataAccessException;

    public Integer updateResearchGroup(ResearchGroup transientInstance, String section) throws DataAccessException;

    public Integer updateResearchGroupPhoto(ResearchGroup transientInstance, String section) throws DataAccessException;

    public Integer deleteResearchGroup(ResearchGroup persistentInstance) throws DataAccessException;

    public ResearchGroup findResearchGroupById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchResearchGroup(ResearchGroup persistentInstance, Paging pagging) throws DataAccessException;


}