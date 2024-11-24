private static int[] parseFraction(String fraction) {
    String[] parts = fraction.split("/");
    int numerator = Integer.parseInt(parts[0]);
    int denominator = Integer.parseInt(parts[1]);
    return new int[]{numerator, denominator};
}


private static int[] reduceFraction(int numerator, int denominator) {
    int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
    return new int[]{numerator / gcd, denominator / gcd};
}


private static int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}


private static int lcm(int a, int b) {
    return a * (b / gcd(a, b));
}


String[] solution(String[] fractionExpressions) {
    List<String> resultList = new ArrayList<>();
    
    for (String expression : fractionExpressions) {
        
        String[] parts = expression.split("\\+");
        String firstFraction = parts[0];
        String secondFraction = parts[1];
        
       
        int[] fraction1 = parseFraction(firstFraction);
        int[] fraction2 = parseFraction(secondFraction);
        
        int numerator1 = fraction1[0], denominator1 = fraction1[1];
        int numerator2 = fraction2[0], denominator2 = fraction2[1];
        
       
        int commonDenominator = lcm(denominator1, denominator2);
        int multiplier1 = commonDenominator / denominator1;
        int multiplier2 = commonDenominator / denominator2;
        
        int combinedNumerator = numerator1 * multiplier1 + numerator2 * multiplier2;
        int combinedDenominator = commonDenominator;
        
    
        int[] reducedFraction = reduceFraction(combinedNumerator, combinedDenominator);
        int reducedNumerator = reducedFraction[0], reducedDenominator = reducedFraction[1];
        
        
        resultList.add(reducedNumerator + "/" + reducedDenominator);
    }
    
   
    return resultList.toArray(new String[0]);
}

