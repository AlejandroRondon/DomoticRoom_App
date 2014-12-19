package utilitiesApps;


public class NumbersSkills {
	
	public int digitsInt(int number){
		int digits;
		digits = Integer.toString(number).length();
		return digits;
	}
	
	public boolean isPrime(int number){
		int i;
		if(number != 1){
			for(i=2;i< number;i++){
				if((number % i) == 0){
					break;
				}
			}
			if(i == number){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
		
	}
	
	public int amountPairDigits(int number){
		int i;
		int pairDigits = 0;
		String strNumber;
		
		strNumber = Integer.toString(number);
		
		for(i=0;i<strNumber.length();i++){
			if( (Character.getNumericValue(strNumber.charAt(i))) % 2 ==0){
				pairDigits++;
			}
		}
		return pairDigits;
		
	}
	
	public int amountImpairDigits(int number){
		int i;
		int ImpairDigits = 0;
		String strNumber;
		
		strNumber = Integer.toString(number);
		
		for(i=0;i<strNumber.length();i++){
			if( (Character.getNumericValue(strNumber.charAt(i))) % 2 !=0){
				ImpairDigits++;
			}
		}
		return ImpairDigits;
		
	}
	public boolean isFibonacci(int number){
		int iNext = 1;
		int iLast = 0;
		int Aux = 0;
		
		while(iNext < number){
			Aux = iNext;
			iNext = iNext + iLast;
			iLast = Aux;
			if(iNext == number){
				return true;
			}
		}
		return false;
		
	}
}
