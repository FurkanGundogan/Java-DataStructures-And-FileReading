package test;
/**
 *
 * @file Tree.java
 * @description İkili Arama Ağacı yapısı
 * @assignment Ödev-2
 * @date 26.05.2020
 * @author Furkan Gündoğan furkan.gundogan@stu.fsm.edu.tr
 * 
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;


public class Tree {
    
    // BinarySearchTree örneğimizdeki özellikleri aldım ama Node yerine TreeNode olarak adlandırdım.

    private TreeNode root;
    public MinHeap<String> heap;
    public int size=0;
    
    void dosyalariOku() {
        // Kullanıcının dosya seçimi yapabilmesi için JFileChooser kullanımını araştırdım ve projeme uyguladım.
        JFileChooser jfc = new JFileChooser("");
        // Çoklu dosya seçimini True yaptım.
        // BİRDEN FAZLA TXT DOSYASI CTRL İLE SEÇİLEREK OKUNABİLİYOR.
        jfc.setMultiSelectionEnabled(true);
        int kullaniciSecimi = jfc.showOpenDialog(null);
        if (kullaniciSecimi == JFileChooser.APPROVE_OPTION) {
            // Seçili dosyaları dönüp kelimelerini okuyor ve ağaca ekliyor.
            for (File f : jfc.getSelectedFiles()) {
                FileInputStream file = null;
                try {
                    System.out.println(f.toString());
                    file = new FileInputStream(f);
                    Scanner sc=new Scanner(file);
                    while(sc.hasNext()){
                    String eklenecek=sc.next();
                    insert(eklenecek.toLowerCase(),jfc.getName(f));
                    
                    }
                 
                } catch (FileNotFoundException ex) {
                    System.out.println("HATA: Dosya Bulunamadı !");
                
                }
            }
        }
        // heap size eklenmelerden sonra belirlendiği için burada atanması yapıldı.
        heap=new MinHeap<String>(size);
       
    }

    // iterative insert method
    void insert(String newData,String dosyaAdi) {
        TreeNode newNode = new TreeNode(newData);

        if (isEmpty()) {
            root = newNode;
            dosyayıListeyeEkle(root,dosyaAdi);
            
        } else {
            TreeNode temp = root;

            while (temp != null) {
                if (newData.compareTo(temp.data) > 0) {
                    if (temp.rightChild == null) {
                        temp.rightChild = newNode;
                        //Ağaca yeni node eklenirken node'un listesine de okunduğu dosya bilgisi ekleniyor.
                        dosyayıListeyeEkle(newNode,dosyaAdi);
                       
                        return;
                    }

                    temp = temp.rightChild;
                } else if (newData.compareTo(temp.data) < 0) {
                    if (temp.leftChild == null) {
                        temp.leftChild = newNode;
                        //Ağaca yeni node eklenirken node'un listesine de okunduğu dosya bilgisi ekleniyor.
                        dosyayıListeyeEkle(newNode,dosyaAdi);  
                        
                        return;
                    }

                    temp = temp.leftChild;
                } else {
                    // else durumu çakışma durumuydu. Bu durumda da eklemeyi deneyecek ama sadece adedini artıracak.
                    dosyayıListeyeEkle(temp,dosyaAdi);;
                    return;
                }
            }
        }
    }
    /* 
    Yeni node eklenirken dosya bilgisini eklemek için ksıa bir fonksiyon yazdım.
    Dosya mevcutsa addLast ile içeride değeri artırılıyor
    */
    void dosyayıListeyeEkle(TreeNode node,String adres){
        // Aynı zamanda oluşturacağım heap için gereken size bilgisini artırıyorum.
        size++;
        node.list.addLast(adres);
    }
    
    

    // iterative search method
    boolean search(String searchData) {
        if (isEmpty()) {
            System.out.println("BinarySearchTree is empty !");
        } else {
            TreeNode temp = root;

            while (temp != null) {
                if (searchData.compareTo(temp.data) > 0) {
                    temp = temp.rightChild;
                } else if (searchData.compareTo(temp.data) < 0) {
                    temp = temp.leftChild;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    

    
    // Alfabetik sırada ekleme yapılması için inorder fonksiyonunu kullandım. Print kısmını modifiye ettim.
    void inorder() {
        System.out.print("inorder : \n");
        System.out.println("Kelime : Konum-Adet - > Şeklinde Gösterilecektir.\n");
        inorder(root);
        System.out.println();
    }

    private void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.leftChild);
            System.out.print("Kelime : "+ node.data + " ");
            node.list.print();
            heap.insert(node.data+node.list.heapIcınBilgiGetir());
            inorder(node.rightChild);
        }
    }

   

    private boolean isEmpty() {
        return root == null;
    }
    
    
    void heapteAra(String metin){
        // Bu metot önce gelen metni kelimelerine ayırıyor.
        LinkedList freqList=new LinkedList();
        String[] metninKelimeleri=metin.split(" ");
        for (String kelime : metninKelimeleri) {
            // Daha sonra her kelime için heapi dönüyor.
             for(int i=0;i<size;i++){
           if(heap.getDeger(i)!=null){
               // Her heapi boşluğa göre parçalıyor. 
              //  Çünkü heapte kelime konum1 adet1 konum2 adet2 şeklinde bir saklanma var             
           String veri=heap.getDeger(i);
           String veriList[]=veri.split(" ");
              // Parçalanmış verilerin ilk verisi kelimedir kelime heapte varsa koşula giriyor
           
           if(veriList[0].equals(kelime)){
           int freq=0;
           for(int j=0;j<veriList.length;j++){
             //Parçalanan verilerden tek basamaklı olan(sayı olan) varsa koşula giriyor.
           if(veriList[j].length()==1){
               
               // Buradaki sayı kadar freqList'e o dosyaAdı ile ekleme yapıyor. Böylece Node'un freq özelliği artırılmış oluyor.
               freq=Integer.valueOf(veriList[j]);
               
               for(int z=0;z<freq;z++){
               freqList.addLast(veriList[j-1].toLowerCase());        
               }
                        
            }
           }
           
           
             System.out.println("");
           }
  
           }
          
           
           
    }
        }
        
       
        
        

      
      freqList.print();
      // freqList kendi içinde heap ve linkedlist kullanılarak sıralama yapılıyor ve son durumu ekrana çıktı olarak gösteriyor.
      freqList.aramaSonuSırala();
    }

   
   
   

   
}
