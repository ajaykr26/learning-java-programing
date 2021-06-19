package collections;

public class PracticeLinkedList {

    String name;
    int age;
    PracticeLinkedList(){
        this.name = "ajay";
    }
    PracticeLinkedList(String name){
        this.name=name;
        this.age=20;
    }
}

class Test extends PracticeLinkedList{

    public static void main(String[] arg){

//        PracticeLinkedList practiceLinkedList = new PracticeLinkedList();
        PracticeLinkedList practiceLinkedList1 = new PracticeLinkedList("vijay");
//        System.out.println(practiceLinkedList.name + practiceLinkedList.age);
        System.out.println(practiceLinkedList1.name + practiceLinkedList1.age);
    }
}
