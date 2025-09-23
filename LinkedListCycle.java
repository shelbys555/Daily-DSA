/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = null, fast = null;
        if(head == null) return false;
        if(head.next == null) return false;

        slow = head;
        fast = head.next;
        boolean cycle = false;
        while(fast != null && fast.next != null){
            if(slow.next == fast.next){
                cycle = true;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return cycle;
    }
}