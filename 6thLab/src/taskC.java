import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskC {

    static class LinkedHashMapCustom<K, V> {

        private Entry<K,V>[] table;   //Array of Entry.
        private int capacity= 4;  //Initial capacity of HashMap
        private Entry<K,V> header; //head of the doubly linked list.
        private Entry<K,V> last; //last of the doubly linked list.

        class Entry<K, V> {
            K key;
            V value;
            Entry<K,V> next;
            Entry<K,V> before,after;

            public Entry(K key, V value, Entry<K,V> next){
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }


        @SuppressWarnings("unchecked")
        public LinkedHashMapCustom(){
            table = new Entry[capacity];
        }

        public void put(K newKey, V data){
            if(newKey==null)
                return;    //does not allow to store null.

            int hash=hash(newKey);

            Entry<K,V> newEntry = new Entry<K,V>(newKey, data, null);
            maintainOrderAfterInsert(newEntry);
            if(table[hash] == null){
                table[hash] = newEntry;
            }else{
                Entry<K,V> previous = null;
                Entry<K,V> current = table[hash];
                while(current != null){ //we have reached last entry of bucket.
                    if(current.key.equals(newKey)){
                        if(previous==null){  //node has to be insert on first of bucket.
                            newEntry.next=current.next;
                            table[hash]=newEntry;
                            return;
                        }
                        else{
                            newEntry.next=current.next;
                            previous.next=newEntry;
                            return;
                        }
                    }
                    previous=current;
                    current = current.next;
                }
                previous.next = newEntry;
            }
        }

        private void maintainOrderAfterInsert(Entry<K, V> newEntry) {

            if(header==null){
                header=newEntry;
                last=newEntry;
                return;
            }

            if(header.key.equals(newEntry.key)){
                deleteFirst();
                insertFirst(newEntry);
                return;
            }

            if(last.key.equals(newEntry.key)){
                deleteLast();
                insertLast(newEntry);
                return;
            }

            Entry<K, V> beforeDeleteEntry=    deleteSpecificEntry(newEntry);
            if(beforeDeleteEntry==null){
                insertLast(newEntry);
            }
            else{
                insertAfter(beforeDeleteEntry,newEntry);
            }


        }

        private void maintainOrderAfterDeletion(Entry<K, V> deleteEntry) {

            if(header.key.equals(deleteEntry.key)){
                deleteFirst();
                return;
            }

            if(last.key.equals(deleteEntry.key)){
                deleteLast();
                return;
            }

            deleteSpecificEntry(deleteEntry);

        }

        private void insertAfter(Entry<K, V> beforeDeleteEntry, Entry<K, V> newEntry) {
            Entry<K, V> current=header;
            while(current!=beforeDeleteEntry){
                current=current.after;  //move to next node.
            }

            newEntry.after=beforeDeleteEntry.after;
            beforeDeleteEntry.after.before=newEntry;
            newEntry.before=beforeDeleteEntry;
            beforeDeleteEntry.after=newEntry;

        }

        private void deleteFirst(){

            if(header==last){ //only one entry found.
                header=last=null;
                return;
            }
            header=header.after;
            header.before=null;

        }

        private void insertFirst(Entry<K, V> newEntry){

            if(header==null){ //no entry found
                header=newEntry;
                last=newEntry;
                return;
            }

            newEntry.after=header;
            header.before=newEntry;
            header=newEntry;

        }

        private void insertLast(Entry<K, V> newEntry){

            if(header==null){
                header=newEntry;
                last=newEntry;
                return;
            }
            last.after=newEntry;
            newEntry.before=last;
            last=newEntry;

        }

        /**
         * deletes entry from last.
         */
        private void deleteLast(){

            if(header==last){
                header=last=null;
                return;
            }

            last=last.before;
            last.after=null;
        }

        private Entry<K, V> deleteSpecificEntry(Entry<K, V> newEntry){

            Entry<K, V> current=header;
            while(!current.key.equals(newEntry.key)){
                if(current.after==null){   //entry not found
                    return null;
                }
                current=current.after;  //move to next node.
            }

            Entry<K, V> beforeDeleteEntry=current.before;
            current.before.after=current.after;
            current.after.before=current.before;  //entry deleted
            return beforeDeleteEntry;
        }

        public V get(K key){
            int hash = hash(key);
            if(table[hash] == null){
                return null;
            }else{
                Entry<K,V> temp = table[hash];
                while(temp!= null){
                    if(temp.key.equals(key))
                        return temp.value;
                    temp = temp.next; //return value corresponding to key.
                }
                return null;   //returns null if key is not found.
            }
        }

        public boolean remove(K deleteKey){

            int hash=hash(deleteKey);

            if(table[hash] == null){
                return false;
            }else{
                Entry<K,V> previous = null;
                Entry<K,V> current = table[hash];

                while(current != null){ //we have reached last entry node of bucket.
                    if(current.key.equals(deleteKey)){
                        maintainOrderAfterDeletion(current);
                        if(previous==null){  //delete first entry node.
                            table[hash]=table[hash].next;
                            return true;
                        }
                        else{
                            previous.next=current.next;
                            return true;
                        }
                    }
                    previous=current;
                    current = current.next;
                }
                return false;
            }

        }

        public void display(){

            Entry<K, V> currentEntry=header;
            while(currentEntry!=null){
                System.out.print("{"+currentEntry.key+"="+currentEntry.value+"}" +" ");
                currentEntry=currentEntry.after;
            }

        }

        private int hash(K key){
            return Math.abs(key.hashCode()) % capacity;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new FileReader("linkedmap.in"));
        FileWriter writer = new FileWriter("linkedmap.out");

        LinkedHashMapCustom<String, String> map = new LinkedHashMapCustom<>();

        while (reader.hasNext()) {
            String[] cmd = reader.nextLine().split(" ");
            if (cmd[0].equals("put")) map.put(cmd[1], cmd[2]);

            if (cmd[0].equals("delete")) map.remove(cmd[1]);

            if (cmd[0].equals("get")) {
                if (map.get(cmd[1]) != null) writer.write(map.get(cmd[1]) + "\n");
                else writer.write("none\n");
            }

            if (cmd[0].equals("prev")) {
                if (map.)
            }
        }
    }
}
