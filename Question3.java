// Singly-linked lists are already defined with this interface:
// class ListNode<T> {
//   ListNode(T x) {
//     value = x;
//   }
//   T value;
//   ListNode<T> next;
// }
//
ListNode<Integer> solution(ListNode<Integer> a, ListNode<Integer> b) {
   
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

   
    while (a != null) {
        s1.push(a.value);
        a = a.next;
    }

   
    while (b != null) {
        s2.push(b.value);
        b = b.next;
    }

    ListNode<Integer> head = null;
    int carry = 0;

    
    while (!s1.isEmpty() || !s2.isEmpty() || carry > 0) {
        int num1 = s1.isEmpty() ? 0 : s1.pop();
        int num2 = s2.isEmpty() ? 0 : s2.pop();

        int sum = num1 + num2 + carry;
        carry = sum / 10000; 
        int currentValue = sum % 10000;

        
        ListNode<Integer> newNode = new ListNode<>(currentValue);
        newNode.next = head; 
        head = newNode;
    }

    return head;
}
