int solution(String[] crypt) {
    int[] digitUsed = new int[10]; 
    Map<Character, Integer> charToDigit = new HashMap<>();
    Set<Character> uniqueLetters = new HashSet<>();
    final int[] validCount = {0}; 

    
    for (String word : crypt) {
        for (char letter : word.toCharArray()) {
            uniqueLetters.add(letter);
        }
    }


    if (uniqueLetters.size() > 10) {
        return 0;
    }

    List<Character> uniqueLetterList = new ArrayList<>(uniqueLetters);
    solve(uniqueLetterList, 0, charToDigit, digitUsed, crypt, validCount);
    return validCount[0];
}

 void solve(List<Character> uniqueLetters, int index,
                   Map<Character, Integer> charToDigit, int[] digitUsed,
                   String[] crypt, int[] validCount) {
    if (index == uniqueLetters.size()) {
     
        if (!isValidAssignment(charToDigit, crypt)) {
            return;
        }

        
        long num1 = getNumberFromWord(crypt[0], charToDigit);
        long num2 = getNumberFromWord(crypt[1], charToDigit);
        long num3 = getNumberFromWord(crypt[2], charToDigit);

        
        if (num1 + num2 == num3) {
            validCount[0]++;
        }
        return;
    }

    char currentLetter = uniqueLetters.get(index);

    for (int i = 0; i < 10; i++) {
        if (digitUsed[i] == 0) {
            digitUsed[i] = 1;
            charToDigit.put(currentLetter, i);
            solve(uniqueLetters, index + 1, charToDigit, digitUsed, crypt, validCount);
            digitUsed[i] = 0;
            charToDigit.remove(currentLetter);
        }
    }
}

 boolean isValidAssignment(Map<Character, Integer> charToDigit, String[] crypt) {
    for (String word : crypt) {
        if (word.length() > 1 && charToDigit.get(word.charAt(0)) == 0) {
            return false; 
        }
    }
    return true;
}

 long getNumberFromWord(String word, Map<Character, Integer> charToDigit) {
    long number = 0;
    for (char c : word.toCharArray()) {
        number = number * 10 + charToDigit.get(c);
    }
    return number;
}