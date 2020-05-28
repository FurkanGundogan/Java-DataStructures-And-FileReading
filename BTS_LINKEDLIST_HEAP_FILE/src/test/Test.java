package test;

/**
 *
 * @file Test.java
 * @description İkili Arama Ağacı yapısı ile çoklu dosya okuma, içeriğindeki kelimeleri alfabetik sıralama ve frekans işlemlerinin testi
 * @assignment Ödev-2
 * @date 26.05.2020
 * @author Furkan Gündoğan furkan.gundogan@stu.fsm.edu.tr
 * 
 */


public class Test {


    public static void main(String[] args) {
        // CTRL ile dosyaları seçiniz.
        
        
        // Ağacımızı oluşturup dosyalardan okuma yapıyoruz.
        Tree t=new Tree();
        t.dosyalariOku();
        
        
        // Alfabetik sırada olmaları için inorder kullandım. Yazdırma komutu içerisinde bulunuyor.
        t.inorder();
        
        
        System.out.println("Heap Durumu:");
        t.heap.printArray();
        
        System.out.println("Yapılan Arama Sonucu:");
        // Sorgulama işlemi birden fazla arama yapıldığında hatalı çalışıyor.
        // Ekleme yaparken bazı elemanlar için artırma yaparken bazı elemanlar için aynı isimde tekrar ekliyor. Sorunu çözemedim.
        t.heapteAra("sql");
        
        
               
    }
    
}
