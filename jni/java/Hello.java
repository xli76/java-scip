//import HelloJNI;

public class Hello
{
   public static void main(String args[])
   {
       System.out.println("Hello from java");
       
       System.loadLibrary("hello");
//       HelloJNI helloJNI = new HelloJNI();
   }
}