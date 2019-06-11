package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      return blackHeight((RBNode<T>) root);
   }

   private int blackHeight(RBNode<T> node) {
      int result = 0;

      if (!node.isEmpty()) {
         if (node.getColour() == Colour.BLACK) {
            result++;
         }

         result += Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
      }

      return result;
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must
    * be BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return verifyChildrenOfRedNodes((RBNode<T>) root);
   }

   private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
      boolean valid = true;

      if (!node.isEmpty()) {
         RBNode<T> left = (RBNode<T>) node.getLeft();
         RBNode<T> right = (RBNode<T>) node.getRight();

         if (node.getColour() == Colour.RED) {
            valid = left.getColour() == Colour.BLACK && right.getColour() == Colour.BLACK;
         }

         if (valid) {
            valid = verifyChildrenOfRedNodes(left) && verifyChildrenOfRedNodes(right);
         }
      }

      return valid;
   }

   /**
    * Verifies the black-height property from the root.
    */
   private boolean verifyBlackHeight() {
      return verifyBlackHeight((RBNode<T>) root);
   }

   private boolean verifyBlackHeight(RBNode<T> node) {
      boolean valid = true;

      if (!node.isEmpty()) {
         valid = blackHeight((RBNode<T>) node.getLeft()) == blackHeight((RBNode<T>) node.getRight());

         if (valid) {
            valid = verifyBlackHeight((RBNode<T>) node.getLeft()) && verifyBlackHeight((RBNode<T>) node.getRight());
         }
      }

      return valid;
   }

   @Override
   public void insert(T value) {
      insert(value, (RBNode<T>) root);
   }

   private void insert(T element, RBNode<T> node) {
      if (inputIsValid(element)) {
         if (node.isEmpty()) {
            node.setData(element);
            node.setColour(Colour.RED);

            RBNode<T> left = new RBNode<>();
            node.setLeft(left);
            left.setParent(node);
            left.setColour(Colour.BLACK);

            RBNode<T> right = new RBNode<>();
            node.setRight(right);
            right.setParent(node);
            right.setColour(Colour.BLACK);

            fixUpCase1(node);
         } else if (element.compareTo(node.getData()) < 0) {
            insert(element, (RBNode<T>) node.getLeft());
         } else {
            insert(element, (RBNode<T>) node.getRight());
         }
      }
   }

   @Override
   public RBNode<T>[] rbPreOrder() {
      RBNode<T>[] array = new RBNode[size()];
      preOrder((RBNode<T>) root, array, 0);

      return array;
   }

   private int preOrder(RBNode<T> node, RBNode<T>[] array, int i) {
      if (!node.isEmpty()) {
         array[i] = node;
         i++;

         i = preOrder((RBNode<T>) node.getLeft(), array, i);
         i = preOrder((RBNode<T>) node.getRight(), array, i);
      }

      return i;
   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (node.equals(root)) {
         node.setColour(Colour.BLACK);
      } else {
         fixUpCase2(node);
      }
   }

   protected void fixUpCase2(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();

      if (parent.getColour() == Colour.RED) {
         fixUpCase3(node);
      }
   }

   private RBNode<T> getUncle(RBNode<T> node, RBNode<T> grandparent) {
      RBNode<T> uncle;

      if (node.getData().compareTo(grandparent.getData()) < 0) {
         uncle = (RBNode<T>) grandparent.getRight();
      } else {
         uncle = (RBNode<T>) grandparent.getLeft();
      }

      return uncle;
   }

   protected void fixUpCase3(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandparent = (RBNode<T>) parent.getParent();
      RBNode<T> uncle = getUncle(node, grandparent);

      if (uncle.getColour() == Colour.RED) {
         parent.setColour(Colour.BLACK);
         uncle.setColour(Colour.BLACK);
         grandparent.setColour(Colour.RED);

         fixUpCase1(grandparent);
      } else {
         fixUpCase4(node);
      }
   }

   protected void fixUpCase4(RBNode<T> node) {
      RBNode<T> next = node;
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandparent = (RBNode<T>) parent.getParent();

      if (node.equals(parent.getRight()) && parent.equals(grandparent.getLeft())) {
         Util.leftRotation(parent);
         next = (RBNode<T>) node.getLeft();
      } else if (node.equals(parent.getLeft()) && parent.equals(grandparent.getRight())) {
         Util.rightRotation(parent);
         next = (RBNode<T>) node.getRight();
      }

      fixUpCase5(next);
   }

   protected void fixUpCase5(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandparent = (RBNode<T>) parent.getParent();

      parent.setColour(Colour.BLACK);
      grandparent.setColour(Colour.RED);

      if (node.equals(parent.getLeft())) {
         Util.rightRotation(grandparent);
      } else {
         Util.leftRotation(grandparent);
      }
   }
}
