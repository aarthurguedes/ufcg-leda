package adt.avltree;

import adt.bst.BSTNode;
import adt.bt.Util;

import java.util.Arrays;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

   private int LLcounter;
   private int LRcounter;
   private int RRcounter;
   private int RLcounter;

   public AVLCountAndFillImpl() {

   }

   @Override
   public int LLcount() {
      return LLcounter;
   }

   @Override
   public int LRcount() {
      return LRcounter;
   }

   @Override
   public int RRcount() {
      return RRcounter;
   }

   @Override
   public int RLcount() {
      return RLcounter;
   }

   @Override
   protected void rebalance(BSTNode<T> node) {
      if (inputIsValid(node)) {
         BSTNode<T> newRoot = null;
         int balance = calculateBalance(node);

         if (balance > 1) {
            boolean lr = false;

            if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
               lr = true;
               Util.leftRotation((BSTNode<T>) node.getLeft());
            }

            if (lr) {
               this.LRcounter++;
            } else {
               this.LLcounter++;
            }

            newRoot = Util.rightRotation(node);
         } else if (balance < -1) {
            boolean rl = false;

            if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
               rl = true;
               Util.rightRotation((BSTNode<T>) node.getRight());
            }

            if (rl) {
               this.RLcounter++;
            } else {
               this.RRcounter++;
            }

            newRoot = Util.leftRotation(node);
         }

         if (newRoot != null && newRoot.getParent() == null) {
            this.root = newRoot;
         }
      }
   }

   @Override
   public void fillWithoutRebalance(T[] array) {
      if (array != null && array.length > 0) {
         Arrays.sort(array);
         insertWithoutRebalance(array, 0, array.length - 1);
      }
   }

   private void insertWithoutRebalance(T[] array, int leftIndex, int rightIndex) {
      if (leftIndex <= rightIndex) {
         int middleIndex = (leftIndex + rightIndex) / 2;
         T middleElement = array[middleIndex];

         insert(middleElement);

         // Processamento para a primeira metade (elementos menores do que o pivot)
         Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
               insertWithoutRebalance(array, leftIndex, middleIndex - 1);
            }
         });

         // Processamento para a segunda metade (elementos maiores do que o pivot)
         Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
               insertWithoutRebalance(array, middleIndex + 1, rightIndex);
            }
         });

         t1.start();
         t2.start();
      }
   }

}
