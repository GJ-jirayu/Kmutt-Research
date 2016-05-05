var PAGE_OFFSET=5;
var PAGE_SIZE_POPUP=10;
    function calculatePageStartEnd( pageNo, pageCount){
		 var pageStart;
		 var pageEnd;
		 pageEnd=pageNo+PAGE_OFFSET;
	
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
		 var pageArray=[pageStart,pageEnd];
    	return pageArray;
    }