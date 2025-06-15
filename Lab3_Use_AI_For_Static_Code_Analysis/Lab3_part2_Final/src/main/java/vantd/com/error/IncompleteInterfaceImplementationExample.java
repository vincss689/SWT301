package vantd.com.error;

interface Shape {
    void draw();
    void resize();
}

class Square implements Shape {
    public void draw() {
        System.out.println("Drawing square");
    }
    
}
