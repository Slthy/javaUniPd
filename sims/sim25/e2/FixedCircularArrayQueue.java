public class FixedCircularArrayQueue extends FixedArrayQueue{
 

   public void enqueue(Object obj){
   
      if(increment(back) == front){
         throw new FullQueueException();
      }
      v[back] = obj;
      back = increment(back);
      
   }
   
      
   public Object dequeue(){
      Object result = getFront();
      front = increment(front);
      return result;
   }

   
   protected int increment(int index){
      return (index+1)%v.length;
   }
   
   public String toString(){
     
      if(front == back){
         return "";
      }
      
      if(front<back){
         String s = "";
         for(int i=front; i<back; i++){
            s += v[i]+" ";
         }
         return s;
      }
      else{
         String s = "";
         for(int i=front; i<v.length; i++){
            s += v[i]+" ";
         }
         for(int i=0; i<back; i++){
            s += v[i]+" ";
         }
         return s;   
      }
   }   
}

class FixedArrayQueue implements Queue{

   protected Object[] v;
   protected int front;
   protected int back;
   final int INITIAL_SIZE = 100;
   
   public FixedArrayQueue(){
      v = new Object[INITIAL_SIZE];
      makeEmpty();
   }

   public void enqueue(Object obj){
   
      if(back == v.length){
         throw new FullQueueException();
      }
      v[back] = obj;
      back++;
   }
   
   public Object getFront(){
          
      if (isEmpty()){
         throw new EmptyQueueException();
      }
      return v[front];
   }
   
   public Object dequeue(){
      Object result = getFront();
      front++;
      return result;
   }

   public boolean isEmpty(){
     
      return front == back;
   }
   
   public void makeEmpty(){
      front = 0;
      back = 0;
   }
}

class EmptyQueueException extends RuntimeException{}
class FullQueueException extends RuntimeException{}

interface Queue extends Container{
   void enqueue(Object obj);
   Object getFront();
   Object dequeue();

}

interface Container{
   boolean isEmpty();
   void makeEmpty();
}
