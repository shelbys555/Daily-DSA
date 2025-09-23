import java.lang.classfile.components.ClassPrinter.ListNode;

public /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = null, next = null;
        if(head == null) return null;
        prev = head;
        curr = head.next;
        if(curr == null){
            return prev;
        }
        next = curr.next;
        if(next == null){
            head = null;
            prev.next = null;
            curr.next = prev;
            return curr;
        }
        while(next != null){
            if(head != null){
                head.next = null;
                head = null;
            }
            curr.next = prev;
            prev = curr;
            curr = next;
            next = curr.next;
        }
        curr.next = prev;
        return curr;
    }
} {
    
}
