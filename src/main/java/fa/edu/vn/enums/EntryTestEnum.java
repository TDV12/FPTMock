package fa.edu.vn.enums;

public enum EntryTestEnum {
	
	    TestPass("Test-Pass"),
	    TestFail("Test-Fail");
	  
	
	    private final String displayValue;
	    
	    
	    
	    EntryTestEnum(String s) {
	         this.displayValue = s;
	    }
	    public String getDisplayValue(){
	         return displayValue;
	    }
}
