package fa.edu.vn.enums;

public enum InterviewEnum {
	
	    InterviewPass("Interview-Pass"),
	    InterviewFail("Interview-Fail");
	
	    private final String displayValue;
	    
	    
	    
	    InterviewEnum(String s) {
	         this.displayValue = s;
	    }
	    public String getDisplayValue(){
	         return displayValue;
	    }
}
