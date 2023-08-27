package fa.edu.vn.enums;

public enum CandidateStatusEnum {
	  New("New"),
	    TestPass("Test-Pass"),
	    TestFail("Test-Fail"),
	    InterviewPass("Interview-Pass"),
	    InterviewFail("Interview-Fail");
	
	    private final String displayValue;
	    
	    
	    
	    CandidateStatusEnum(String s) {
	         this.displayValue = s;
	    }
	    public String getDisplayValue(){
	         return displayValue;
	    }
}
