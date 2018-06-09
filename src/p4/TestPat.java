package p4;

//Example program for pattern matching of two strings 
public class TestPat{
	

public TestPat() {}





public int pat (char[] subject, char[] pattern){
//Post: if pattern is not a substring of subject, return -1
//else return (zero-based) index where the pattern (first) // starts in subject
	final int NOTFOUND = -1;
	int iSub = 0, rtnIndex = NOTFOUND; boolean isPat = false;
	int subjectLen = subject.length;
	int patternLen = pattern.length;
	while (isPat == false && iSub + patternLen - 1 < subjectLen){
		if (subject [iSub] == pattern [0]){
			rtnIndex = iSub; // Starting at zero
			isPat = true;
			for (int iPat = 1; iPat < patternLen; iPat ++) {
				if (subject[iSub + iPat] != pattern[iPat]){
					rtnIndex = NOTFOUND;
					isPat = false;
					break; // out of for loop
				}
			}
		}
		iSub++;
	}
	return (rtnIndex);
}


public static void main (String[] argv){
	final int MAX = 100;
	char subject[] = new char[MAX]; 
	char pattern[] = new char[MAX]; 
	if (argv.length != 2){
		System.out.println("java TestPat String-Subject String-Pattern"); 
		return;
	}
	subject = argv[0].toCharArray();
	pattern = argv[1].toCharArray();
	TestPat testPat = new TestPat ();
	int n = 0;
	if ((n = testPat.pat (subject, pattern)) == -1)
		System.out.println("Pattern string is not a substring of the subject string"); 
	else
		System.out.println("Pattern string begins at the character " + n); 
}



}
