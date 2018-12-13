import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math.*;


class Caculator extends Frame implements ActionListener{
	Button[] num = new Button[10];    
	 Button[] option = new Button[6]; 
	 String[] optionDupli = {"=","C","BACK",".","Poa","Exit"};
	 char[] opsDupli = {'=','c','b','d','p','e'}; 
	 
	 TextField result;  
	 int i;   
	 public Caculator(String title) {
	  super(title);
	  setLayout(null);
	  
	  result = new TextField("p(x;m)");
	  result.setLocation(10, 30);
	  result.setSize(200, 60); 
	
	  for( i = 0; i < 6; i++) {
	    option[i] = new Button(optionDupli[i]);
	  }

	  for(i = 0; i < 10; i++) {
	     num[i] = new Button(i+"");
	     num[i].addActionListener(this);
	     num[i].setSize(40,30);
	  }  
	     option[0].setBounds(130,180,40,30);
	     option[1].setBounds(130,150,40,30); 
	     option[2].setBounds(130,90,40,30);  
	     option[3].setBounds(130,120,40,30);  
	     option[4].setBounds(170,90,40,120);
	     option[5].setBounds(50,180,80,30);

	  num[0].setLocation(10,180);   num[7].setLocation(10,150);
	  num[8].setLocation(50,150);   num[9].setLocation(90,150);
	  num[4].setLocation(10,120);   num[5].setLocation(50,120);
	  num[6].setLocation(90,120);   num[1].setLocation(10,90);
	  num[2].setLocation(50,90);    num[3].setLocation(90,90);  
	  for(i = 0; i < 6; i++) {
	   add(option[i]);
	   option[i].addActionListener(this);
	  }
	  for(i = 0 ; i < 10 ; i++) {
	   add(num[i]);
	  }
	  
	  add(result);
	  setSize(220,220);
	  setVisible(true);
	 }
	 String str;
	 double second = 0;
	 double first= 0;
	 boolean dotcheck=false;
	 public int Factorial(int k)
		{
			if(k==0)
			{
				return 1;
			}
			else
				return k*Factorial(k-1);
		}
		public double possible(double r,double b)
		{
			double sum=0;
			double bunmo=0; //분모
			double bunza=0; //분자
			double possi=0;
			for(int x=0;x<=r;x++) //시그마 
			{
				bunza=(Math.pow((Math.E),(-b)))*(Math.pow(b,x));
				bunmo=Factorial(x);
				possi=bunza/bunmo;
				sum+=possi;
				
			}
			return sum;
			
		}
	 public void operation(char opt)
	 {
		 switch(opt)
		 {
		 case '=':
			 result.setText(Double.toString(possible(first,second)));
			 break;
			 
		 case 'c':  
			    first=0.0;
			    second=0.0;
			    dotcheck=false;
			    out("0.");
			    break;
		 case 'p':
			 if(first == 0.0)
			     first = second;
			    else
			     first+= second;
			    out(String.valueOf(first));
			    second=0;
			    dotcheck=false;
			 break;
		 case 'd':   
			    dotcheck=true;
			    break;
		
		 case 'b':    
			    str = String.valueOf(result.getText());
			    
			    if(str == "0." || str.length() <= 2) {
			     out("0.");
			     second=0.0;
			     break;
			    }
			    else {
			     if(dotcheck) {
			      str = str.substring( 0, str.length()-1);
			      if(str.endsWith(".")) {
			       dotcheck = false;
			      }      
			      out(str);
			      return;
			     }
			     
			     str=str.substring( 0, str.length()-2);
			     str+=".";
			     out(str);
			     break;
			    }
		 }
		 
	 }

	 public void makenumber(String input) {
		  if(dotcheck) {
		   if( second == 0.0 ) {
		    second=0.0;
		    out(String.valueOf(second));
		    return;
		   }
		   else {
		    str = String.valueOf(result.getText());
		    str += input; 
		    second = Double.parseDouble(str);
		    out(str);
		   }
		  }
		  else {
		   if(second == 0.0) {
		    second = Double.parseDouble(input);
		    out(String.valueOf(second));
		    return;
		   }
		   else {
		    second = Double.parseDouble(result.getText()) * 10.0 + Double.parseDouble(input);
		   }
		  }
		  out(String.valueOf(second));
		 }
	 public void out(String str) {   
		  String out = str;
		  if(str.endsWith(".0"))  {
		   out = out.substring(0, out.indexOf('.')+1);
		  }
		  result.setText(String.valueOf(out));
		 }
	 	 public void actionPerformed(ActionEvent e) {   
	 		 for( i = 0; i < 10; i++) {
	 		     if(e.getSource() == num[i]) {
	 		        makenumber(i+"");
	 		     }
	 		 }
	 		  for( i = 0; i < 6; i++) {
	 		     if(e.getSource() == option[i]) {
	 		       operation(opsDupli[i]);
	 	
	 		     }
	 		  }
	 
	   if(e.getSource() == option[5]) {   // 
		      setVisible(false);
		
		      System.exit(0);
	   }
	  } 
	 

 public static void main(String[] args)
 {
	 new Caculator("계산기");
 }
}
