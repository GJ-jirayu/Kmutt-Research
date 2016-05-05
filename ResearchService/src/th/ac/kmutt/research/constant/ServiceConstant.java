package th.ac.kmutt.research.constant;

import java.util.ResourceBundle;

public class ServiceConstant {
    public static final String hostReference = "http://10.2.0.76:10000/BPSService/RestletServlet/";

    public static final String LOG_APPENDER = "FinWizLog";

    public static final String INTERFACE_RETURN_TYPE = "java.util.List";
    public static final String VOID_RETURN_TYPE = "void";
    public static final String FLAG_INACTIVE = "0";
    public static final String FLAG_ACTIVE = "1";
    public static final String UPDATE_TYEP_FLAG = "flag";
    public static final String TAB_ALL = "all";
    public static final String TAB_MY_DATA = "myData";
    public static final int JOURNAL_PAPERS_TYPE_JOURNAL = 1;
    public static final int JOURNAL_PAPERS_TYPE_CONFERENCE = 2;
    public static final String[] FILTERS = {"0", "1", "2", "3"};
    public static final String[] DOCTYPES = {"PUBLISH", "DRAFT"};
    public static final String ERROR_MESSAGE_KEY = "errorMessage";
    public static final String SUCCESS_MESSAGE_KEY = "successMessage";
    public static final String WARNING_MESSAGE_KEY = "warningMessage";

    public static final String ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE = "error.constraintViolation";
    //public static final String SUCCESS_MESSAGE_CODE=".constraintViolation";
    /*public static  enum FILTER{
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
	    THURSDAY, FRIDAY, SATURDAY 
	}*/
    public static final ResourceBundle bundle;
    public static String SCHEMA = "";

    static {
        bundle = ResourceBundle.getBundle("jdbc");
        SCHEMA = bundle.getString("schema");
    }

    public static final String MESSAGE_CODE_OK = "ok";
    public static final String MESSAGE_CODE_ERROR = "error";

    public static final String DOC_TYPE_COPYRIGHT = "COPYRIGHT";
    public static final String DOC_TYPE_REWARD = "REWARD";
    public static final String DOC_TYPE_PATENT = "PATENT";
    public static final String DOC_TYPE_RESEARCH = "RESEARCH";
    public static final String DOC_TYPE_JOURNAL = "JOURNAL";
    public static final String DOC_TYPE_UTILIZATION = "UTILIZATION";


    // USER_PROFILE
    public static final String USER_PROFILE_LOGIN = "loginUserProfile";
    public static final String USER_PROFILE_LOOUT = "logoutUserProfile";
    public static final String USER_PROFILE_SEARCH = "SearchUserProfile";
    public static final String USER_PROFILE_SAVE = "saveUserProfile";
    public static final String MISS_ACCOUNT_UPDATE = "updateUserProfile";
    public static final String MISS_ACCOUNT_ITEMS_DELETE = "deleteUserProfileItems";
    public static final String MISS_ACCOUNT_DELETE = "deleteUserProfile";

    // RESEARCH_GROUP
    public static final String RESEARCH_GROUP_SEARCH = "searchResearchGroup";
    public static final String RESEARCH_GROUP_SAVE = "saveResearchGroup";
    public static final String RESEARCH_GROUP_UPDATE = "updateResearchGroup";
    public static final String RESEARCH_GROUP_ITEMS_DELETE = "deleteResearchGroupItems";
    public static final String RESEARCH_GROUP_DELETE = "deleteResearchGroup";
    public static final String RESEARCH_GROUP_FIND_BY_ID = "findByIdResearchGroup";


    // UTILIZATION_TYPE
    public static final String UTILIZATION_TYPE_SEARCH = "searchUtilizationType";
    public static final String UTILIZATION_TYPE_SAVE = "saveUtilizationType";
    public static final String UTILIZATION_TYPE_UPDATE = "updateUtilizationType";
    public static final String UTILIZATION_TYPE_ITEMS_DELETE = "deleteUtilizationTypeItems";
    public static final String UTILIZATION_TYPE_DELETE = "deleteUtilizationType";
    public static final String UTILIZATION_TYPE_FIND_BY_ID = "findByIdUtilizationType";

    // PUBLISH_TYPE
    public static final String PUBLISH_TYPE_SEARCH = "searchPublishType";
    public static final String PUBLISH_TYPE_SAVE = "savePublishType";
    public static final String PUBLISH_TYPE_UPDATE = "updatePublishType";
    public static final String PUBLISH_TYPE_ITEMS_DELETE = "deletePublishTypeItems";
    public static final String PUBLISH_TYPE_DELETE = "deletePublishType";
    public static final String PUBLISH_TYPE_FIND_BY_ID = "findByIdPublishType";

    // POSITION
    public static final String POSITION_SEARCH = "searchPosition";
    public static final String POSITION_SAVE = "savePosition";
    public static final String POSITION_UPDATE = "updatePosition";
    public static final String POSITION_ITEMS_DELETE = "deletePositionItems";
    public static final String POSITION_DELETE = "deletePosition";
    public static final String POSITION_FIND_BY_ID = "findByIdPosition";

    // ORGANIZATION
    public static final String ORGANIZATION_SEARCH = "searchOrganization";
    public static final String ORGANIZATION_SAVE = "saveOrganization";
    public static final String ORGANIZATION_UPDATE = "updateOrganization";
    public static final String ORGANIZATION_ITEMS_DELETE = "deleteOrganizationItems";
    public static final String ORGANIZATION_DELETE = "deleteOrganization";
    public static final String ORGANIZATION_FIND_BY_ID = "findByIdOrganization";

    // COUNTRY
    public static final String COUNTRY_SEARCH = "searchCountry";
    public static final String COUNTRY_SAVE = "saveCountry";
    public static final String COUNTRY_UPDATE = "updateCountry";
    public static final String COUNTRY_ITEMS_DELETE = "deleteCountryItems";
    public static final String COUNTRY_DELETE = "deleteCountry";
    public static final String COUNTRY_FIND_BY_ID = "findByIdCountry";

    // WORK_TYPE
    public static final String WORK_TYPE_SEARCH = "searchWorkType";
    public static final String WORK_TYPE_SAVE = "saveWorkType";
    public static final String WORK_TYPE_UPDATE = "updateWorkType";
    public static final String WORK_TYPE_ITEMS_DELETE = "deleteWorkTypeItems";
    public static final String WORK_TYPE_DELETE = "deleteWorkType";
    public static final String WORK_TYPE_FIND_BY_ID = "findByIdWorkType";

    // JOURNAL_PAPERS_TYPE
    public static final String JOURNAL_PAPERS_TYPE_SEARCH = "searchJournalPapersType";
    public static final String JOURNAL_PAPERS_TYPE_SAVE = "saveJournalPapersType";
    public static final String JOURNAL_PAPERS_TYPE_UPDATE = "updateJournalPapersType";
    public static final String JOURNAL_PAPERS_TYPE_ITEMS_DELETE = "deleteJournalPapersTypeItems";
    public static final String JOURNAL_PAPERS_TYPE_DELETE = "deleteJournalPapersType";
    public static final String JOURNAL_PAPERS_TYPE_FIND_BY_ID = "findByIdJournalPapersType";

    // FUNDING_RESOURCES_TYPE
    public static final String FUNDING_RESOURCES_TYPE_SEARCH = "searchFundingResouresType";
    public static final String FUNDING_RESOURCES_TYPE_SAVE = "saveFundingResouresType";
    public static final String FUNDING_RESOURCES_TYPE_UPDATE = "updateFundingResouresType";
    public static final String FUNDING_RESOURCES_TYPE_ITEMS_DELETE = "deleteFundingResouresTypeItems";
    public static final String FUNDING_RESOURCES_TYPE_DELETE = "deleteFundingResouresType";
    public static final String FUNDING_RESOURCES_TYPE_FIND_BY_ID = "findByIdFundingResouresType";

    // RESEARCHER
    public static final String RESEARCHER_SEARCH = "searchResearcher";
    public static final String RESEARCHER_SAVE = "saveResearcher";
    public static final String RESEARCHER_UPDATE = "updateResearcher";
    public static final String RESEARCHER_ITEMS_DELETE = "deleteResearcherItems";
    public static final String RESEARCHER_DELETE = "deleteResearcher";
    public static final String RESEARCHER_FIND_BY_ID = "findByIdResearcher";

    public static final String RESEARCHER_GROUP_LIST = "listResearcherGroup";
/*	public static final String RESEARCHER_GROUP_SAVE= "saveResearcherGroup";	
	public static final String RESEARCHER_GROUP_UPDATE = "updateResearcherGroup";
	public static final String RESEARCHER_GROUP_DELETE = "deleteResearcherGroup";*/


    // RESEARCHER_GROUP
    public static final String RESEARCHER_GROUP_SEARCH = "searchResearcherGroup";
    public static final String RESEARCHER_GROUP_SAVE = "saveResearcherGroup";
    public static final String RESEARCHER_GROUP_UPDATE = "updateResearcherGroup";
    public static final String RESEARCHER_GROUP_ITEMS_DELETE = "deleteResearcherGroupItems";
    public static final String RESEARCHER_GROUP_DELETE = "deleteResearcherGroup";
    public static final String RESEARCHER_GROUP_FIND_BY_ID = "findByIdResearcherGroup";

    // PUBLISH_LEVEL
    public static final String PUBLISH_LEVEL_SEARCH = "searchPublishLevel";
    public static final String PUBLISH_LEVEL_SAVE = "savePublishLevel";
    public static final String PUBLISH_LEVEL_UPDATE = "updatePublishLevel";
    public static final String PUBLISH_LEVEL_ITEMS_DELETE = "deletePublishLevelItems";
    public static final String PUBLISH_LEVEL_DELETE = "deletePublishLevel";
    public static final String PUBLISH_LEVEL_FIND_BY_ID = "findByIdPublishLevel";


    // ORGANIZATION_EXT
    public static final String ORGANIZATION_EXT_SEARCH = "searchOrganizationExt";
    public static final String ORGANIZATION_EXT_SAVE = "saveOrganizationExt";
    public static final String ORGANIZATION_EXT_UPDATE = "updateOrganizationExt";
    public static final String ORGANIZATION_EXT_ITEMS_DELETE = "deleteOrganizationExtItems";
    public static final String ORGANIZATION_EXT_DELETE = "deleteOrganizationExt";
    public static final String ORGANIZATION_EXT_FIND_BY_ID = "findByIdOrganizationExt";

    // FUNDING_RESOURCES
    public static final String FUNDING_RESOURCES_SEARCH = "searchFundingResoures";
    public static final String FUNDING_RESOURCES_SAVE = "saveFundingResoures";
    public static final String FUNDING_RESOURCES_UPDATE = "updateFundingResoures";
    public static final String FUNDING_RESOURCES_ITEMS_DELETE = "deleteFundingResouresItems";
    public static final String FUNDING_RESOURCES_DELETE = "deleteFundingResoures";
    public static final String FUNDING_RESOURCES_FIND_BY_ID = "findByIdFundingResoures";

    // RESEARCH_PROJECT
    public static final String RESEARCH_PROJECT_SEARCH = "searchReSearchProject";
    public static final String RESEARCH_PROJECT_SAVE = "saveReSearchProject";
    public static final String RESEARCH_PROJECT_UPDATE = "updateReSearchProject";
    public static final String RESEARCH_PROJECT_ITEMS_DELETE = "deleteReSearchProjectItems";
    public static final String RESEARCH_PROJECT_DELETE = "deleteReSearchProject";
    public static final String RESEARCH_PROJECT_FIND_BY_ID = "findByIdReSearchProject";
    public static final String RESEARCH_PROJECT_UPDATE_FLAG = "updateFlagReSearchProject";

    //RESEARCH_PROJECT_DOCUMENT
    public static final String RESEARCH_PROJECT_DOCUMENT_LIST = "listResearchProjectDocument";
    public static final String RESEARCH_PROJECT_DOCUMENT_SAVE = "saveResearchProjectDocument";
    public static final String RESEARCH_PROJECT_DOCUMENT_UPDATE = "updateResearchProjectDocument";
    public static final String RESEARCH_PROJECT_DOCUMENT_DELETE = "deleteResearchProjectDocument";
    public static final String RESEARCH_PROJECT_DOCUMENT_FIND_BY_ID = "findByIdResearchProjectDocument";

    //RESEARCH_PROJECT_PAYMENT
    public static final String RESEARCH_PROJECT_PAYMENT_LIST = "listResearchProjectPayment";
    public static final String RESEARCH_PROJECT_PAYMENT_SAVE = "saveResearchProjectPayment";
    public static final String RESEARCH_PROJECT_PAYMENT_UPDATE = "updateResearchProjectPayment";
    public static final String RESEARCH_PROJECT_PAYMENT_DELETE = "deleteResearchProjectPayment";
    public static final String RESEARCH_PROJECT_PAYMENT_FIND_BY_ID = "findByIdResearchProjectPayment";

    //RESEARCH_PROJECT_PROGRESS
    public static final String RESEARCH_PROJECT_PROGRESS_LIST = "listResearchProjectProgress";
    public static final String RESEARCH_PROJECT_PROGRESS_SAVE = "saveResearchProjectProgress";
    public static final String RESEARCH_PROJECT_PROGRESS_UPDATE = "updateResearchProjectProgress";
    public static final String RESEARCH_PROJECT_PROGRESS_DELETE = "deleteResearchProjectProgress";
    public static final String RESEARCH_PROJECT_PROGRESS_FIND_BY_ID = "findByIdResearchProjectProgress";

    //RESEARCH_PROJECT_RESEARCHER
    public static final String RESEARCH_PROJECT_RESEARCHER_LIST = "listResearchProjectResearcher";
    public static final String RESEARCH_PROJECT_RESEARCHER_SAVE = "saveResearchProjectResearcher";
    public static final String RESEARCH_PROJECT_RESEARCHER_UPDATE = "updateResearchProjectResearcher";
    public static final String RESEARCH_PROJECT_RESEARCHER_DELETE = "deleteResearchProjectResearcher";
    public static final String RESEARCH_PROJECT_RESEARCHER_FIND_BY_ID = "findByIdResearchProjectResearcher";
    public static final String RESEARCH_PROJECT_RESEARCHER_COUNT = "countResearchProjectResearcher";


    //RESEARCH_PROJECT_WITHDRAW
    public static final String RESEARCH_PROJECT_WITHDRAW_LIST = "listResearchProjectWithDraw";
    public static final String RESEARCH_PROJECT_WITHDRAW_SAVE = "saveResearchProjectWithDraw";
    public static final String RESEARCH_PROJECT_WITHDRAW_UPDATE = "updateResearchProjectWithDraw";
    public static final String RESEARCH_PROJECT_WITHDRAW_DELETE = "deleteResearchProjectWithDraw";
    public static final String RESEARCH_PROJECT_WITHDRAW_FIND_BY_ID = "findByIdResearchProjectWithDraw";

    // JOURNAL_PAPERS
    public static final String JOURNAL_PAPERS_SEARCH = "searchJournalPapers";
    public static final String JOURNAL_PAPERS_SAVE = "saveJournalPapers";
    public static final String JOURNAL_PAPERS_UPDATE = "updateJournalPapers";
    public static final String JOURNAL_PAPERS_ITEMS_DELETE = "deleteJournalPapersItems";
    public static final String JOURNAL_PAPERS_DELETE = "deleteJournalPapers";
    public static final String JOURNAL_PAPERS_FIND_BY_ID = "findByIdJournalPapers";
    public static final String JOURNAL_PAPERS_UPDATE_FLAG = "updateFlagJournalPapers";

    //JOURNAL_PAPERS_DOCUMENT
    public static final String JOURNAL_PAPERS_DOCUMENT_LIST = "listJournalPapersDocument";
    public static final String JOURNAL_PAPERS_DOCUMENT_SAVE = "saveJournalPapersDocument";
    public static final String JOURNAL_PAPERS_DOCUMENT_UPDATE = "updateJournalPapersDocument";
    public static final String JOURNAL_PAPERS_DOCUMENT_DELETE = "deleteJournalPapersDocument";
    public static final String JOURNAL_PAPERS_DOCUMENT_FIND_BY_ID = "findByIdJournalPapersDocument";

    //JOURNAL_PAPERS_WRITER
    public static final String JOURNAL_PAPERS_WRITER_LIST = "listJournalPapersWriter";
    public static final String JOURNAL_PAPERS_WRITER_SAVE = "saveJournalPapersWriter";
    public static final String JOURNAL_PAPERS_WRITER_UPDATE = "updateJournalPapersWriter";
    public static final String JOURNAL_PAPERS_WRITER_DELETE = "deleteJournalPapersWriter";
    public static final String JOURNAL_PAPERS_WRITER_FIND_BY_ID = "findByIdJournalPapersWriter";
    public static final String JOURNAL_PAPERS_WRITER_COUNT = "countJournalPapersWriter";


    // UTILIZATION
    public static final String UTILIZATION_SEARCH = "searchUtilization";
    public static final String UTILIZATION_SAVE = "saveUtilization";
    public static final String UTILIZATION_UPDATE = "updateUtilization";
    public static final String UTILIZATION_ITEMS_DELETE = "deleteUtilizationItems";
    public static final String UTILIZATION_DELETE = "deleteUtilization";
    public static final String UTILIZATION_FIND_BY_ID = "findByIdUtilization";
    public static final String UTILIZATION_ITEM_FIND_BY_ID = "findByIdUtilizationItem";
    public static final String UTILIZATION_UPDATE_FLAG = "updateFlagUtilization";

    public static final String UTILIZATION_LIST_DELETE = "deleteListUtilization";
    public static final String UTILIZATION_LIST_FIND_BY_ID = "findByIdUtilizationList";

    //UTILIZATION_DOCUMENT
    public static final String UTILIZATION_DOCUMENT_LIST = "listUtilizationDocument";
    public static final String UTILIZATION_DOCUMENT_SAVE = "saveUtilizationDocument";
    public static final String UTILIZATION_DOCUMENT_UPDATE = "updateUtilizationDocument";
    public static final String UTILIZATION_DOCUMENT_DELETE = "deleteUtilizationDocument";
    public static final String UTILIZATION_DOCUMENT_FIND_BY_ID = "findByIdUtilizationDocument";


    // COPYRIGHT
    public static final String COPYRIGHT_SEARCH = "searchCopyRight";
    public static final String COPYRIGHT_SAVE = "saveCopyRight";
    public static final String COPYRIGHT_UPDATE = "updateCopyRight";
    public static final String COPYRIGHT_ITEMS_DELETE = "deleteCopyRightItems";
    public static final String COPYRIGHT_DELETE = "deleteCopyRight";
    public static final String COPYRIGHT_FIND_BY_ID = "findByIdCopyRight";
    public static final String COPYRIGHT_UPDATE_FLAG = "updateFlagCopyRight";

    //COPYRIGHT_DOCUMENT
    public static final String COPYRIGHT_DOCUMENT_LIST = "listCopyRightDocument";
    public static final String COPYRIGHT_DOCUMENT_SAVE = "saveCopyRightDocument";
    public static final String COPYRIGHT_DOCUMENT_UPDATE = "updateCopyRightDocument";
    public static final String COPYRIGHT_DOCUMENT_DELETE = "deleteCopyRightDocument";
    public static final String COPYRIGHT_DOCUMENT_FIND_BY_ID = "findByIdCopyRightDocument";

    //COPYRIGHT_CREATOR
    public static final String COPYRIGHT_CREATOR_LIST = "listCopyRightCreator";
    public static final String COPYRIGHT_CREATOR_SAVE = "saveCopyRightCreator";
    public static final String COPYRIGHT_CREATOR_UPDATE = "updateCopyRightCreator";
    public static final String COPYRIGHT_CREATOR_DELETE = "deleteCopyRightCreator";
    public static final String COPYRIGHT_CREATOR_FIND_BY_ID = "findByIdCopyRightCreator";

    // REWARD
    public static final String REWARD_SEARCH = "searchReward";
    public static final String REWARD_SAVE = "saveReward";
    public static final String REWARD_UPDATE = "updateReward";
    public static final String REWARD_ITEMS_DELETE = "deleteRewardItems";
    public static final String REWARD_DELETE = "deleteReward";
    public static final String REWARD_FIND_BY_ID = "findByIdReward";
    public static final String REWARD_UPDATE_FLAG = "updateFlagReward";

    //REWARD_CREATOR
    public static final String REWARD_CREATOR_LIST = "listRewardCreator";
    public static final String REWARD_CREATOR_SAVE = "saveRewardCreator";
    public static final String REWARD_CREATOR_UPDATE = "updateRewardCreator";
    public static final String REWARD_CREATOR_DELETE = "deleteRewardCreator";
    public static final String REWARD_CREATOR_FIND_BY_ID = "findByIdRewardCreator";

    //REWARD_DOCUMENT
    public static final String REWARD_DOCUMENT_LIST = "listRewardDocument";
    public static final String REWARD_DOCUMENT_SAVE = "saveRewardDocument";
    public static final String REWARD_DOCUMENT_UPDATE = "updateRewardDocument";
    public static final String REWARD_DOCUMENT_DELETE = "deleteRewardDocument";
    public static final String REWARD_DOCUMENT_FIND_BY_ID = "findByIdRewardDocument";

    // RESEARCH_PATENT
    public static final String PATENT_SEARCH = "searchPatent";
    public static final String PATENT_SAVE = "savePatent";
    public static final String PATENT_UPDATE = "updatePatent";
    public static final String PATENT_ITEMS_DELETE = "deletePatentItems";
    public static final String PATENT_DELETE = "deletePatent";
    public static final String PATENT_FIND_BY_ID = "findByIdPatent";
    public static final String PATENT_UPDATE_FLAG = "updateFlagPatent";

    //PATENT_CREATOR
    public static final String PATENT_CREATOR_LIST = "listPatentCreator";
    public static final String PATENT_CREATOR_SAVE = "savePatentCreator";
    public static final String PATENT_CREATOR_UPDATE = "updatePatentCreator";
    public static final String PATENT_CREATOR_DELETE = "deletePatentCreator";
    public static final String PATENT_CREATOR_FIND_BY_ID = "findByIdPatentCreator";

    //PATENT_DOCUMENT
    public static final String PATENT_DOCUMENT_LIST = "listPatentDocument";
    public static final String PATENT_DOCUMENT_SAVE = "savePatentDocument";
    public static final String PATENT_DOCUMENT_UPDATE = "updatePatentDocument";
    public static final String PATENT_DOCUMENT_DELETE = "deletePatentDocument";
    public static final String PATENT_DOCUMENT_FIND_BY_ID = "findByIdPatentDocument";

    //PATENT_EDIT_DATE
    public static final String PATENT_EDIT_DATE_LIST = "listPatentEditDate";
    public static final String PATENT_EDIT_DATE_SAVE = "savePatentEditDate";
    public static final String PATENT_EDIT_DATE_UPDATE = "updatePatentEditDate";
    public static final String PATENT_EDIT_DATE_DELETE = "deletePatentEditDate";
    public static final String PATENT_EDIT_DATE_FIND_BY_ID = "findByIdPatentEditDate";

    //PATENT_FEE_PAYMENT
    public static final String PATENT_FEE_PAYMENT_LIST = "listPatentFeePayment";
    public static final String PATENT_FEE_PAYMENT_SAVE = "savePatentFeePayment";
    public static final String PATENT_FEE_PAYMENT_UPDATE = "updatePatentFeePayment";
    public static final String PATENT_FEE_PAYMENT_DELETE = "deletePatentFeePayment";
    public static final String PATENT_FEE_PAYMENT_FIND_BY_ID = "findByIdPatentFeePayment";

    //PATENT_INHERITED
    public static final String PATENT_INHERITED_LIST = "listPatentInherited";
    public static final String PATENT_INHERITED_SAVE = "savePatentInherited";
    public static final String PATENT_INHERITED_UPDATE = "updatePatentInherited";
    public static final String PATENT_INHERITED_DELETE = "deletePatentInherited";
    public static final String PATENT_INHERITED_FIND_BY_ID = "findByIdPatentInherited";

    //PATENT_RIGHTHOLDER
    public static final String PATENT_RIGHTHOLDER_LIST = "listPatentRightholder";
    public static final String PATENT_RIGHTHOLDER_SAVE = "savePatentRightholder";
    public static final String PATENT_RIGHTHOLDER_UPDATE = "updatePatentRightholder";
    public static final String PATENT_RIGHTHOLDER_DELETE = "deletePatentRightholder";
    public static final String PATENT_RIGHTHOLDER_FIND_BY_ID = "findByIdPatentRightholder";

    // DOCTYPE
    public static final String DOCTYPE_LIST = "searchDocType";
    public static final String DOCTYPE_UPDATE = "updateDocType";

    //UserPortal
    public static final String USER_PORTAL_SEARCH = "searchUserPortal";
    public static final String USER_PORTAL_FIND_BY_ID = "findByIdUserPortal";

    //DocAssignMapping
    public static final String DOC_ASSIGN_MAPPING_BY_ID = "findByIDDocAssignMapping";
    public static final String DOC_ASSIGN_MAPPING_SAVE = "saveDocAssignMapping";
    public static final String DOC_ASSIGN_MAPPING_DELETE = "deleteDocAssignMapping";
    public static final String DOC_ASSIGN_MAPPING_LIST = "listDocAssignMapping";

    // TITLE
    public static final String TITLE_SEARCH = "searchTitle";
    public static final String TITLE_SAVE = "saveTitle";
    public static final String TITLE_UPDATE = "updateTitle";
    public static final String TITLE_ITEMS_DELETE = "deleteTitleItems";
    public static final String TITLE_DELETE = "deleteTitle";
    public static final String TITLE_FIND_BY_ID = "findByIdTitle";


    public static final String RESEARCH_GROUP_CHECK_UQ = "checkUQResearchGroup";
    public static final String UTILIZATION_TYPE_CHECK_UQ = "checkUQUtilizationType";
    public static final String PUBLISH_TYPE_CHECK_UQ = "checkUQPublishType";
    ;
    public static final String POSITION_CHECK_UQ = "checkUQPosition";
    ;
    public static final String COUNTRY_CHECK_UQ = "checkUQCountry";
    public static final String WORK_TYPE_CHECK_UQ = "checkUQWorkType";
    public static final String JOURNAL_PAPERS_TYPE_CHECK_UQ = "checkUQJournalPapersType";
    public static final String PUBLISH_LEVEL_CHECK_UQ = "checkUQPublishLevel";
    ;
    public static final String ORGANIZATION_EXT_CHECK_UQ = "checkUQOrganizationExt";
    ;
    public static final String FUNDING_RESOURCES_CHECK_UQ = "checkUQFundingResoures";
    public static final String FUNDING_RESOURCES_TYPE_CHECK_UQ = "checkUQFundingResouresType";
}
