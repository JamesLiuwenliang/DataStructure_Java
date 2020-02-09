
class Recursive {

    public ListNode removeElements(ListNode head , int val){

        if(head == null){
            return null;
        }

        // 每个步骤分解 : res是除去头节点(要被删除的节点)外的,剩余链表;
        ListNode res = removeElements(head.next ,val);
        if(head.val == val){
            // res存的是不含要被删除的头结点的链表
            return res;
        }
        else{
            // 如果没有要删除的节点,就将head这个参数放在res链表的最前面
            head.next = res;
            return head;
        }

        // head.next = removeElements(head.next,val);
        // return head.val == val ? head.next :head;
    }

    public static void main(String[] args){
        int[]  nums = {1,2,3,4,5,6,7};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements(head ,7);
        System.out.println(res);
    }

}