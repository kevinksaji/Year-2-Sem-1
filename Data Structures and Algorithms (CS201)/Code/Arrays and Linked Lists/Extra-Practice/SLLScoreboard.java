// implementation of scoreboard using a SinglyLinkedList

public class SLLScoreboard {
    public SinglyLinkedList<GameEntry> board;
    public SLLScoreboard() {
        board = new SinglyLinkedList<>();

    }
    
    public void add(GameEntry e) {

        // if board is empty
        if (board.isEmpty()) {
            board.addFirst(e);
        }
        // if new highscore
        else if(board.first().getScore() < e.getScore()) {
            board.addFirst(e);
        }
        // to insert a new entry in the SLL
        else {
            SinglyLinkedList<GameEntry> temp = new SinglyLinkedList<>();
            while (board.last().getScore() < e.getScore()) {
                // add the elements that are to be shifted rightwards in board to temp (in descending order of score from head of temp)
                temp.addFirst(board.removeLast());
            }
            // add the new emtry to board
            board.addLast(e);
            // add the shifted elements back to board
            while(!temp.isEmpty()) {
                board.addLast(temp.removeFirst());
            }

        }
        

    }
    // remove high score at index i (head node is index 0)
    public GameEntry remove(int i) {
        SinglyLinkedList<GameEntry> temp = new SinglyLinkedList<>();
        int counter = 0;
        while (counter != i) {
            // add the elements that are before the element to be removed to temp (in descending order of score from head of temp)
            temp.addLast(board.removeFirst());
            counter++;
        }
        // remove the entry at index i and assign to ret variable
        GameEntry ret = board.removeFirst();
        // add the elements in temp back to board.
        while(!temp.isEmpty()) {
            board.addFirst(temp.removeLast());
        }
        return ret;
    }
    public void print() throws CloneNotSupportedException{
        System.out.println(board.toString());
    }
    public static void main(String[] args){
        SLLScoreboard myList = new SLLScoreboard();
        try {
            myList.add(new GameEntry("Kevin", 500));
            // myList.print();
    
            myList.add(new GameEntry("Jack", 240));
            // myList.print();

            myList.add(new GameEntry("Thomas", 440));
            // myList.print();

            myList.add(new GameEntry("Alice", 100));
            // myList.print();

            myList.add(new GameEntry("Betty", 200));
            // myList.print();

            myList.add(new GameEntry("Veronica", 362));
            myList.print();

            myList.remove(2);
            myList.print();
            
        } catch (Exception e) {
            System.out.println("Implement cloneable interface");
        }
        

        
        
      }
}

