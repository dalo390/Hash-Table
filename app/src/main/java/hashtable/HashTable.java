package hashtable;


public class HashTable {

    String[] table;
    int size;

    public HashTable(){
        table = new String[11];
        size = 0;
    }

    public int hash(String input){
        char[] inputArr = input.toCharArray();
        int a = 33;
        //hash code w polynomial
        int hashCode = 0;
        int i = 1;
        for(char x : inputArr){
            hashCode += (int)x * Math.pow(a,i);
            i ++;
        }

        //compression function w linear probing
        int x = 1;
        int arraySpot = (hashCode + x) % size;
        while(table[arraySpot] !=  null) {
            if(size > table.length){
                resizeTable();
            }
            x++;
        }

        return arraySpot;
    }
    

    public void resizeTable(){
        String[] tableCopy = table.clone(); 
        //prime 2 ^ x-1
        int tableLength = (int)Math.pow(2, table.length - 1);
        table = new String[tableLength];
        System.arraycopy(tableCopy, 0, table, 0, tableCopy.length);
    }

    public void insert(String input){
        table[hash(input)] = input;
        size++;
    }

    public int size(){
        return size;
    }
}
