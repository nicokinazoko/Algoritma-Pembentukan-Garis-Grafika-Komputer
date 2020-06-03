package grafkom;

import java.awt.*;
import java.applet.Applet;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Bressenham_Skala extends Applet implements ActionListener{
    //definisikan JFrame untuk GUI
    JFrame jFrame               =   new JFrame("Garis Bressenham Skala");
    
    //memanggil method kanvas yang ada di sini
    Kanvas kanvas               =   new Kanvas();
    
    //membuat label untuk nama x dan y
    JLabel  label_x1            =   new JLabel("x_1");
    JLabel  label_x2            =   new JLabel("x_2");
    JLabel  label_y1            =   new JLabel("y_1");
    JLabel  label_y2            =   new JLabel("y_2");
    JLabel  label_x_trans       =   new JLabel("X Skala");
    JLabel  label_y_trans        =   new JLabel("Y Skala");    
    
    //membuat textfield untuk input x dan y
    JTextField  field_x1        =   new JTextField("");
    JTextField  field_x2        =   new JTextField("");
    JTextField  field_y1        =   new JTextField("");
    JTextField  field_y2        =   new JTextField("");
    JTextField  field_x_trans   =   new JTextField("");
    JTextField  field_y_trans   =   new JTextField("");    
    
    //membuat button untuk memproses data input
    JButton buttonProses        =   new JButton("Proses");
    
    //deklarasi variabel yang akan digunakan
    int x1,x2,y1,y2,p,xc,yc,tmp,dx,dy,step,x_inc,y_inc,xk,yk;

    //method baru untuk membuat kanvas 
    public Bressenham_Skala(){
        //set lokasi dari frame 
        jFrame.setLocation(200,200);
        
        //set ukuran frame 
        jFrame.setSize(1000,800);
        
        //set exit default
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        
        //set layout jadi null
        jFrame.setLayout(null);
        
        //menambahkan kanvas ke frame
        jFrame.add(kanvas);

        //mengatur posisi dari kanvas
        kanvas.setBounds(0,0,1000,500);
        
        //menghtiung selisih dari xc dan yc
        xc                          =   kanvas.getWidth() - (kanvas.getWidth()/2);
        yc                          =   kanvas.getHeight() - (kanvas.getHeight()/2);
        
        //mengatur lokasi dari label
        jFrame.add(label_x1);
        label_x1.setBounds(20, 500, 30, 20);
        
        jFrame.add(label_y1);
        label_y1.setBounds(80, 500, 30, 20);
        
        jFrame.add(label_x2);
        label_x2.setBounds(140, 500, 30, 20);
        
        jFrame.add(label_y2);
        label_y2.setBounds(200, 500, 30, 20);
        
        jFrame.add(label_x_trans);
        label_x_trans.setBounds(250, 500, 100, 20);
        
        jFrame.add(label_y_trans);
        label_y_trans.setBounds(360, 500, 100, 20);          
        
        
        //mengatur lokasi dari field
        jFrame.add(field_x1);
        field_x1.setBounds(20, 600, 50, 20);
        
        jFrame.add(field_y1);
        field_y1.setBounds(80, 600, 50, 20);
        
        jFrame.add(field_x2);
        field_x2.setBounds(140, 600, 50, 20);
        
        jFrame.add(field_y2);
        field_y2.setBounds(200, 600, 50, 20);
        
        jFrame.add(field_x_trans);
        field_x_trans.setBounds(250, 600, 100, 20);
        
        jFrame.add(field_y_trans);
        field_y_trans.setBounds(370, 600, 100, 20);          
        
        //menambah button ke jframe
        jFrame.add(buttonProses);
        buttonProses.setBounds(600, 600, 150, 40);
        
        //membuat button agar bisa ditekan
//        buttonProses.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Object object               =   e.getSource();
//                if(object   ==  buttonProses)
//                {
//                    kanvas.repaint();
//                }
//            }
//            
//        });

        buttonProses.addActionListener(this);
        
        //mengatur posisi allignment field
        field_x1.setHorizontalAlignment(JTextField.CENTER);
        field_x2.setHorizontalAlignment(JTextField.CENTER);
        field_y1.setHorizontalAlignment(JTextField.CENTER);
        field_y2.setHorizontalAlignment(JTextField.CENTER);


        jFrame.show();
        
        setVisible(true);
        
}
        @Override
    public void actionPerformed(ActionEvent e) {
        Object object               =   e.getSource();
        if(object   ==  buttonProses)
        {
            kanvas.repaint();
        }
    }
    
    
    private class Kanvas extends Canvas
    {
        public Kanvas() {
                setBackground(Color.PINK);        
        }
        
        public void paint(Graphics g)
    {
        //set warna putih
        g.setColor(Color.WHITE);
        
        //set gambar garis 1
        g.drawLine(0, yc, kanvas.getWidth(), yc);
        
        g.drawLine(xc, 0, xc, kanvas.getWidth());
        
        //memanggil method bressenham
        Bressenham_Skala(g);
        //DDA(g);
        
    }
    }
    
    
    public void Bressenham_Skala(Graphics g)
    {
        int x_trans,y_trans;        
        //konversi hasil dari input field ke integer
        x1                          =   Integer.parseInt(field_x1.getText());
        x2                          =   Integer.parseInt(field_x2.getText());
        y1                          =   Integer.parseInt(field_y1.getText());
        y2                          =   Integer.parseInt(field_y2.getText());
        x_trans                     =   Integer.parseInt(field_x_trans.getText());
        y_trans                     =   Integer.parseInt(field_y_trans.getText());

        
        //mengecek apakah x1 > x2
        //jika iya maka lakukan pertukaran nilai agar nilai x1 lebih kecil dari x2 
        if(x1 > x2)
        if(x1 > x2 && y1 > y2)
        {
            tmp                     =   x1;
            x1                      =   x2;
            x2                      =   tmp;
            tmp                     =   y1;
            y1                      =   y2;
            y2                      =   tmp;
        }        
        else
        if(x1 > x2)
        {
            tmp                     =   x1;
            x1                      =   x2;
            x2                      =   tmp;            
        }
        else
        if(y1 > y2)
        {
            tmp                     =   y1;
            y1                      =   y2;
            y2                      =   tmp;            
        }
        
        dx                          =   x2 - x1;
        dy                          =   y2 - y1;
        
        System.out.println("\np\tx,y\n-----");
        
        p                           =   (2 * dy) - dx;
        
        if(p != 0)
        {
            do
            {
                if((y2 < y1 && x2 < x1 ) || (x2 > x1 && y2 > y1))
                {
                    if(p < 0)
                    {
                        if(dx > dy)
                        {
                            x1      =   x1 + 1;
                            System.out.println(p + "\t" + x1 + "," + y1);
                            p       =   p + (2 * dy);
                        }
                        else
                        {
                            y1      =   y1 + 1;
                            System.out.println(p + "\t" + x1 + "," + y1);
                            p       =   p + (2 * dx);
                        }
                    }
                    else
                    {
                        x1          =   x1 + 1;
                        y1          =   y1 + 1;
                        if(dx > dy)
                        {
                            System.out.println(p + "\t" + x1 + "," + y1);
                            p       =   p + ((2*dy) - (2*dx));
                        }
                        else
                        {
                            System.out.println(p + "\t" + x1 + "," + y1);
                            p       =   p + ((2*dx) - (2*dy));
                        }
                    }
                }
                else
                {
                    if(p > 0)
                    {
                        if(dx < dy)
                        {
                            x1      =   x1 + 1;
                            System.out.println(p + "\t" + x1 + "," + y1);
                            p       =   p - (2*dy);
                        }
                        else
                        {
                            y1      =   y1 - 1;
                            System.out.println(p + "\t" + x1 + "," + y1);
                            p       =   p - (2*dx);
                        }
                    }
                    else
                    {
                        x1          =   x1 + 1;
                        y1          =   y1 - 1;
                        if(dx < (dy * (-1)))
                        {
                            System.out.println(p + "\t" + x1 + "," + y1);
                            p       =   p - ((2*dy) + (2*dx));
                        }
                        else
                        {
                            System.out.println(p + "\t" + x1 + "," + y1);
                            p       =   p - ((2*dx) + (2*dy));
                        }
                    }
                }
                
                //proses Skala di penggambaran
                g.setColor(Color.RED);
                g.fillRect(x1*x_trans + xc , -y1*y_trans + yc, 2, 2);                 
                
                g.setColor(Color.BLUE);
                g.fillRect(x1 + xc, -y1 + yc, 2, 2);
            }
            while((x1 != x2 ) && (y1 != y2));
        }
    

}
    public static void main(String[] args) {
        new Bressenham_Skala();
    }



}


