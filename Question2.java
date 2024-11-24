
public int[] solution(int[] balances, String[] requests) {
        
    TreeMap<Long, List<int[]>> cashbackMap = new TreeMap<>();
    
    for (int requestIndex = 0; requestIndex < requests.length; requestIndex++) {
        String[] parts = requests[requestIndex].split(" ");
        String operationType = parts[0];
        long timestamp = Long.parseLong(parts[1]);
        int accountId = Integer.parseInt(parts[2]) - 1;
        int amount = Integer.parseInt(parts[3]);
        
   
        if (accountId < 0 || accountId >= balances.length) {
            return new int[] {-(requestIndex + 1)}; 
        }
        
        
        if (cashbackMap.containsKey(timestamp)) {
            for (int[] cashback : cashbackMap.get(timestamp)) {
                int account = cashback[0];
                int cashbackAmount = cashback[1];
                balances[account] += cashbackAmount;
            }
            cashbackMap.remove(timestamp); 
        }
        
      
        if (operationType.equals("deposit")) {
           
            balances[accountId] += amount;
        } else if (operationType.equals("withdraw")) {
          
            if (balances[accountId] < amount) {
                return new int[] {-(requestIndex + 1)}; 
            }
            balances[accountId] -= amount;
            
         
            int cashbackAmount = (amount * 2) / 100;
            if (cashbackAmount > 0) {
                long cashbackTimestamp = timestamp + 24 * 60 * 60; 
                cashbackMap.putIfAbsent(cashbackTimestamp, new ArrayList<>());
                cashbackMap.get(cashbackTimestamp).add(new int[] {accountId, cashbackAmount});
            }
        } else {
          
            return new int[] {-(requestIndex + 1)};
        }
    }
    
   
    long lastTimestamp = Long.parseLong(requests[requests.length - 1].split(" ")[1]);
    for (Map.Entry<Long, List<int[]>> entry : cashbackMap.entrySet()) {
        if (entry.getKey() <= lastTimestamp) {
            for (int[] cashback : entry.getValue()) {
                int account = cashback[0];
                int cashbackAmount = cashback[1];
                balances[account] += cashbackAmount;
            }
        }
    }
    
    return balances;
}
