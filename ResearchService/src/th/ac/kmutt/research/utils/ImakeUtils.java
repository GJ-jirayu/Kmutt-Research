
package th.ac.kmutt.research.utils;

import java.io.File;


public class ImakeUtils
{

    public ImakeUtils()
    {
    }
    public static void createDirectoryIfNeeded(String directoryName)
 	 {
 	   File theDir = new File(directoryName);

 	   // if the directory does not exist, create it
 	   if (!theDir.exists())
 	   {
 		   //boolean cancreate = theDir.mkdir();
 		   theDir.mkdir();
 	   }
 	  
 	 }
    public static String genToken(){
 		StringBuffer sb = new StringBuffer();
 	    for (int i = 36; i > 0; i -= 12) {
 	      int n = Math.min(12, Math.abs(i));
 	      sb.append(org.apache.commons.lang3.StringUtils.leftPad(Long.toString(Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
 	    }
 	    return sb.toString();
  }
    public static final int calculatePage(int perPage, int total)
    {
        return total % perPage != 0 ? total / perPage + 1 : total / perPage;
    }
    public static final int[] calculatePageStartEnd(int pageNo,int pageCount){
    	// int pageCount=300;
		//int offset=10;
		//int pageNo=300;
		
		 int pageStart;
		 int pageEnd;
		 pageEnd=pageNo+PAGE_OFFSET;
		// int xx = pageNo*offset;
		 if(pageCount>((pageNo/PAGE_OFFSET)+1)*PAGE_OFFSET){
			 pageEnd=((pageNo/PAGE_OFFSET)+1)*PAGE_OFFSET;
		 }
		 else{
			 pageEnd=pageCount;
		 }
		 if(pageEnd-PAGE_OFFSET>0){
			 pageStart=pageEnd-PAGE_OFFSET;
		 }else{
			 pageStart=1;
		 }
    	return new int[]{pageStart,pageEnd};
    }
    public static final int PAGE_SIZE=20;
   // public static final int PAGE_OFFSET=10;
    public static final int PAGE_OFFSET=5;
   // public static final int PAGE_SIZE=2;
    public static final String MODE_EDIT="edit";
    public static final String MODE_NEW="new";
    public static final String MODE_DELETE_ITEMS="deleteItems";
    public static final String MODE_DELETE="delete";
    public static final String MODE_DO_BACK="doBack";
}
