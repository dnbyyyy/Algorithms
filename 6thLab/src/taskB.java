import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taskB {

    static class HashMapCustom<K, V> {

        private final Entry<K,V>[] table;
        private final int capacity= 4;

        static class Entry<K, V> {
            K key;
            V value;
            Entry<K,V> next;

            public Entry(K key, V value, Entry<K,V> next){
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }


        @SuppressWarnings("unchecked")
        public HashMapCustom(){
            table = new Entry[capacity];
        }

        public void put(K newKey, V data){
            if(newKey==null)
                return;

            int hash=hash(newKey);

            Entry<K,V> newEntry = new Entry<>(newKey, data, null);

            if(table[hash] == null){
                table[hash] = newEntry;
            }else{
                Entry<K,V> previous = null;
                Entry<K,V> current = table[hash];

                while(current != null){
                    if(current.key.equals(newKey)){
                        newEntry.next=current.next;
                        if(previous==null){
                            table[hash]=newEntry;
                        }
                        else{
                            previous.next=newEntry;
                        }
                        return;
                    }
                    previous=current;
                    current = current.next;
                }
                assert previous != null;
                previous.next = newEntry;
            }
        }

        public V get(K key){
            int hash = hash(key);
            if (table[hash] != null) {
                Entry<K, V> temp = table[hash];
                while (temp != null) {
                    if (temp.key.equals(key))
                        return temp.value;
                    temp = temp.next;
                }
            }
            return null;
        }

        public void remove(K deleteKey){

            int hash = hash(deleteKey);

            if (table[hash] != null) {
                Entry<K, V> previous = null;
                Entry<K, V> current = table[hash];

                while (current != null) {
                    if (current.key.equals(deleteKey)) {
                        if (previous == null) {
                            table[hash] = table[hash].next;
                        } else {
                            previous.next = current.next;
                        }
                        return;
                    }
                    previous = current;
                    current = current.next;
                }
            }

        }

        private int hash(K key){
            return Math.abs(key.hashCode()) % capacity;
        }
    }

    public static void main(String[] args) throws IOException {
        HashMapCustom<String, String> map = new HashMapCustom<>();

        Scanner reader = new Scanner(new FileReader("map.in"));
        FileWriter writer = new FileWriter("map.out");

        while (reader.hasNext()) {
            String[] cmd = reader.nextLine().split(" ");

            if (cmd[0].equals("put")) map.put(cmd[1], cmd[2]);

            if (cmd[0].equals("delete")) map.remove(cmd[1]);

            if (cmd[0].equals("get")) {
                if (map.get(cmd[1]) != null) writer.write(map.get(cmd[1]) + "\n");
                else writer.write("none\n");
            }
        }

        reader.close();
        writer.close();
    }
}
