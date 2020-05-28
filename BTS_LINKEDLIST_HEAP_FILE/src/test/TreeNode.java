/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @file TreeNode.java
 * @description İkili Arama Ağacı yapısı için Node nesnesi
 * @assignment Ödev-2
 * @date 26.05.2020
 * @author Furkan Gündoğan furkan.gundogan@stu.fsm.edu.tr
 * 
 */
public class TreeNode {
    String data;
    TreeNode leftChild;
    TreeNode rightChild;
    
    // BinarySearchTree'nin her bir nodu liste özelliğine sahip olucak
    LinkedList list;
    public TreeNode(String data) {
        this.data = data;
        this.list=new LinkedList();
    }

    @Override
    public String toString() {
        return data + "";
    }
    
}
