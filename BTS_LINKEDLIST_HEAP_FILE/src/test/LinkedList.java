package test;

/**
 *
 * @file LinkedList.java
 * @description Bağlı liste nesnesi
 * @assignment Ödev-2
 * @date 26.05.2020
 * @author Furkan Gündoğan furkan.gundogan@stu.fsm.edu.tr
 * 
 */
public class LinkedList {
    private Node head;
   
    void addFirst(Node newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.nextNode = head;
            head = newNode;
        }
    }

    void addFirst(String newData) {
        addFirst(new Node(newData));
    }

    void addLast(Node newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            Node temp = head;
            boolean zatenvar=false;
            
            while (temp.nextNode != null) {
                temp = temp.nextNode;
                // Kelime dosyada zaten varsa, dosya için freq değeri artırılıyor.
                
               if (temp.dosyaAdi.equals(newNode.dosyaAdi)){
                  temp.freq++;
                  zatenvar=true;
                }
            }
            if(temp.equals(head)){
                // Üstteki while döngüsü head için kontrol yapamıyordu o yüzden bunu da ekledim.
                if (temp.dosyaAdi.equals(newNode.dosyaAdi)){
                  temp.freq++;
                  zatenvar=true;
                }
            }
            
            // Kelime dosyada yoksa dosya bilgisi ve freq=1 olacak şekilde ekleniyor.
             if(!zatenvar)temp.nextNode = newNode;
        }
    }

    void addLast(String newData) {
        addLast(new Node(newData));
    }

    // addAfter kullanmadığım için değişiklik yapmadım
    boolean addAfter(String search, String newData) {
        if (isEmpty()) {
            System.out.println("Empty list !");
        } else {
            Node temp = head;

            while (temp != null && !temp.dosyaAdi.equals(search)) {
                temp = temp.nextNode;
            }

            if (temp != null) {
                Node newNode = new Node(newData);
                newNode.nextNode = temp.nextNode;
                temp.nextNode = newNode;
                return true;
            }
        }
        return false;
    }

    Node remove(String data) {
        Node removedNode = null;

        if (isEmpty()) {
            System.out.println("Empty list !");
        } else {

            if (head.dosyaAdi.equals(data)) {
                removedNode = head;
                head = head.nextNode;
            } else {
                Node temp = head;

                while (temp.nextNode != null && !temp.nextNode.dosyaAdi.equals(data)) {
                    temp = temp.nextNode;
                }

                if (temp.nextNode != null) {
                    removedNode = temp.nextNode;
                    temp.nextNode = temp.nextNode.nextNode;
                }
            }
        }

        return removedNode;
    }

    void print() {
        // DOSYANINADI-FREKANS -> DOSYANINADI-FREKANS ->  Şeklinde yazdırıyor.
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.dosyaAdi +"-"+temp.freq+" -> ");
            temp = temp.nextNode;
        }

        System.out.println("null");
    }
    
    String heapIcınBilgiGetir(){
    Node temp = head;
    String bilgi="";
        while (temp != null) {        
            bilgi+=" "+temp.dosyaAdi+" "+temp.freq;
            temp = temp.nextNode;
        }

        return bilgi;
    }

    boolean isEmpty() {
        return head == null;
    }

    int size() {
        Node temp = head;
        int count = 0;

        while (temp != null) {
            count++;
            temp = temp.nextNode;
        }

        return count;
    }
    
   
    void aramaSonuSırala(){
        LinkedList siraliListe=new LinkedList();
        MinHeap<Integer> heap=new MinHeap<Integer>(this.size());
        MinHeap<Integer> heapKucuktenBuyugeSirali=new MinHeap<Integer>(this.size());
       
        // İlk olarak bir bir heap oluşturup freq değerlerini ekliyorum.
        Node temp = head;
        while (temp != null) {     
            heap.insert(temp.freq);  
            temp = temp.nextNode;
        }
        // Daha sonra heapKucuktenBuyugeSirali isimli heape geçmiş heapten ekleme yapıyorum. Ve Sıralanmış oluyorlar.
        for (int i = 0; i <size(); i++)heapKucuktenBuyugeSirali.insert(heap.deleteMin());
        
             
       
        
        // Bizden alakalı->alakasız şeklinde sıralama istendiği için küçüktenBüyüğe olan heapi tersten dolaşıyorum.
        for (int i = size()-1; i >=0; i--) {
                        
             Node temp2 = head;
        while (temp2 != null) {      
            // heapin en büyük elemanından ek küçük elemanına doğru eğer listenin hangi elemanı o freq değerine sahipse yazdırıyorum.
            if(temp2.freq==heapKucuktenBuyugeSirali.getDeger(i))siraliListe.addLast(temp2.dosyaAdi+"("+temp2.freq+")");    
            temp2 = temp2.nextNode;
        }
        }
       
       
        System.out.println("En alakalı dosyadan en alakasız dosyaya sıralama : ");
        //Farklı bir printe ihtiyacım olduğu için bu şekilde burada kodu sadece bir eleman yazdıracak şekilde değiştirdim.
        Node temp3 = siraliListe.head;
        while (temp3 != null) { 
            System.out.println(temp3.dosyaAdi);
            temp3 = temp3.nextNode;
        }
    }
    
    void kullanilmayanFreqSifirla(){
    Node temp=head;
        while (temp != null) { 
            temp.freq=0;
            temp = temp.nextNode;
        }
    }
    
}
