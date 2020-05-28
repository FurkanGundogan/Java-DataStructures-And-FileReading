package test;

/**
 *
 * @file Node.java
 * @description LinkedList için Node nesnesi
 * @assignment Ödev-2
 * @date 26.05.2020
 * @author Furkan Gündoğan furkan.gundogan@stu.fsm.edu.tr
 * 
 */
public class Node {
    // Node tanımı istenildiği üzere dosya adını ve frekansı tutacak şekilde değiştirildi.
    String dosyaAdi;
    Node nextNode;
    int freq = 1;

    public Node(String dosyaAdi, Node nextNode) {
        this.dosyaAdi = dosyaAdi;
        this.nextNode = nextNode;
    }

    public Node(String dosyaAdi) {
        this.dosyaAdi = dosyaAdi;
        this.freq=1;
    }
    private int getFreq(){
    return this.freq;
    }

}
