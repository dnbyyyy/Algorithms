import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HashSet {
    static class HashSetCustom<E>{


        private final HashMapCustom<E, Object> hashMapCustom;

        public HashSetCustom(){
            hashMapCustom=new HashMapCustom<>();
        }

        public void add(E value){
            hashMapCustom.put(value, null);
        }

        public boolean contains(E obj){
            return hashMapCustom.contains(obj) != null;
        }

        public void remove(E obj){
            hashMapCustom.remove(obj);
        }

    }

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

        public void remove(K deleteKey){

            int hash = hash(deleteKey);

            if(table[hash] != null){
                Entry<K,V> previous = null;
                Entry<K,V> current = table[hash];

                while(current != null){
                    if(current.key.equals(deleteKey)){
                        if(previous == null){
                            table[hash] = table[hash].next;
                        }
                        else{
                            previous.next = current.next;
                        }
                        return;
                    }
                    previous = current;
                    current = current.next;
                }
            }

        }

        public K contains(K key){
            int hash = hash(key);
            if (table[hash] != null) {
                Entry<K, V> temp = table[hash];
                while (temp != null) {
                    if (temp.key.equals(key))
                        return key;
                    temp = temp.next;
                }
            }
            return null;
        }

        private int hash(K key){
            return Math.abs(key.hashCode()) % capacity;
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner reader  = new Scanner(new FileReader("set.in"));
        FileWriter writer = new FileWriter("set.out");

        HashSetCustom<Integer> set = new HashSetCustom<>();

        while (reader.hasNext()) {
            String cmd = reader.next();
            int key = reader.nextInt();

            switch (cmd) {
                case "insert":
                    set.add(key);
                    break;
                case "delete":
                    set.remove(key);
                    break;
                case "exists":
                    if (set.contains(key)) writer.write("true\n");
                    else writer.write("false\n");
                    break;
            }
        }
        reader.close();
        writer.close();
    }
}
