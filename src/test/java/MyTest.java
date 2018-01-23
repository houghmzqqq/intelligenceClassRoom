public abstract class MyTest {
    public static int i ;
    private float f = 1.0f;
    public MyTest(){
        try{
            throw new Exception();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void testMethod(){
        System.out.println("testMethod");

    }

    public static void main(String[] args) {
        ((MyTest)null).testMethod();
    }
}
