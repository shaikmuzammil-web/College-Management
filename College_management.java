package P1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class File{
	public String[] getArray(String path)
	{
		 String s = "";
	        try {
	            FileReader FR = new FileReader(path);
	            int ch;

	            while ((ch = FR.read()) != -1) {
	                s += (char) ch;
	            }
	            FR.close();
	           String[] S1=s.split("[\t\n]");
	           return S1;
	        } 
	        catch (IOException e) 
	        {
	            System.out.println(e.getMessage());
	        }
	        
	        return new String[0];
	}
	public void sendArray(Student[] A,String s,Attendence head)
	{
		FileWriter FW=null;
		Attendence cnode=head;
		try
		{
		FW=new FileWriter(s);
		for(int a=0;a<A.length;a++)
		{
			FW.write(A[a].Uid+"\t");
			FW.write(A[a].Name+"\t");
			FW.write(A[a].Branch+"\t");
			FW.write(A[a].Email+"\t");
			FW.write(A[a].Pnumber+"\t");
			FW.write(A[a].Addar+"\t");
			if(A[a].M1!=0&&A[a].M2!=0&&A[a].Grade!=0) {
			FW.write(A[a].M1+"\t");
			FW.write(A[a].M2+"\t");
			FW.write(A[a].Grade+"\t");
			}
			if(cnode!=null)
			{
				
				FW.write(Integer.toString(cnode.hours)+"\t");
				FW.write(Integer.toString(cnode.Phours)+"\t");
				FW.write(Integer.toString(cnode.Ahours)+"\t");
			}
			FW.write("\n");
			cnode=cnode.next;
		}
		
		}
		catch(IOException e)
		{
			System.out.println("Error "+e.getMessage());
		}
		try 
		{
			if(FW!=null)
			{
				FW.close();
			}
		}
		catch(IOException e)
		{
			System.out.println("Error "+e.getMessage());
		}
	}
	
}
class Faculty
{
	 public long Fid;
	    public String Name, Depat,Addar;

	    public Faculty(String uid, String name, String branch, String Addar) {
	        this.Fid = Long.parseLong(uid.trim());
	        this.Name = name.trim();
	        this.Depat = branch.trim();
	        this.Addar=Addar;
	       
	    }

	    public void display() {
	        System.out.println(this.Fid + "\t" + this.Name + "\t" + this.Depat+"\t"+this.Addar);
	    }
}
class Student{
    public long Uid,Pnumber;
    public String Name, Branch,Email,Addar;
    double M1,M2,Grade;

    public Student(String uid, String name, String branch,String Email,String Addar,String number) {
        this.Uid =Long.parseLong(uid.trim());
        this.Name = name.trim();
        this.Branch = branch.trim();
        this.Email=Email.trim();
        this.Addar=Addar.trim();
        this.Pnumber=Long.parseLong(number.trim());
    }
    public void CalculateGrade() {
    	this.Grade=(this.M1+this.M2)/20;
    }
    public void display() {
        System.out.println(this.Uid + "\t" + this.Name + "\t" + this.Branch+ "\t" +this.Email+ "\t" +this.Addar+ "\t" +this.Pnumber);
    }
    public void DispalySinglestudent()
    {
    	System.out.println(this.Uid+"\t"+this.Name+"\t"+this.Branch+"\t"+this.Email+"\t"+this.Addar+"\t"+this.Pnumber);
    }
    public void DisplayUNB()
    {
    	System.out.print(this.Uid+"\t"+this.Name+"\t"+this.Branch+"\t");
    }
}
class Attendence{
	public static int hours;
	public int Phours,Ahours;
	public double per;
	public long id;
	Attendence next;
}
class Alist {
	Attendence newnode,cnode,delnode,head,tail;
	public boolean IsEmpty()
	{
		if(head==null)
		return true;
		return false;
		
	}
	public Alist(Student [] A)
	{
		int t=0;
		while(t<A.length-1)
		{
			newnode=new Attendence();
			newnode.id=A[t].Uid;
			newnode.next=null;
			if(this.IsEmpty())
			{
				head=newnode;
				tail=newnode;
			}
			else
			{
				cnode=head;
				while(cnode.next!=null)
				{
					cnode=cnode.next;
				}
				cnode.next=newnode;
				tail=newnode;
			}
			t++;
		}
	}
	public void display()
	{
		if(this.IsEmpty())
		{
			System.out.println("list is empty");
		}
		else
		{
			cnode=head;
			while(cnode!=null)
			{
				System.out.println(cnode.id +"\t"+ cnode.hours +" \t\t"+ cnode.Phours +"\t\t "+ cnode.Ahours+" \t\t"+(double)(cnode.Phours/cnode.hours*100));
				cnode=cnode.next;
			}
		}
	}
	public int countNodes()
	{
		int node =0;
		if(this.IsEmpty())
		{
			System.out.println("There is Data of the student");
		}
		else
		{
			cnode=head;
			while(cnode!=null)
			{
				node++;
				cnode=cnode.next;
			}
		}
		return node;
		
	}
	public void displaySingleStudent(Attendence A)
	{
		cnode=A;
		System.out.println(cnode.id +" "+ cnode.hours +" "+ cnode.Phours +" "+ cnode.Ahours);
	}
	public void gethours(int x)
	{
		head.hours+=x;
	}
	
	public void AddAttendence(Scanner sc)
	{
		System.out.println("enter the number of hours");
		int x=sc.nextInt();
		this.gethours(x);
		if(this.IsEmpty())
		{
			System.out.println("there are no students to add the attendence");
		}
		else
		{
			cnode=head;
			System.out.println("enter 'Y' if present else enter no for  ");
			while(cnode!=null)
			{
				System.out.println(cnode.id +"'Y' or 'N'");
				String C=sc.next();
				if(C.equalsIgnoreCase("Y"))
				{
					cnode.Phours+=x ;
				}
				else
				{
					cnode.Ahours+=x ;
				}
				cnode.per=(double)cnode.Phours/cnode.hours*100;
				cnode=cnode.next;
			}
			System.out.println("Attendence Posted!");
		}
	}
	public void conversion(long [][] A)
	{
	      cnode=head;
	      for(int i=0;i<A.length;i++)
	      {
	        A[i][0]=cnode.id;
	        A[i][1]=cnode.hours;
	        A[i][2]=cnode.Phours;
	        A[i][3]=cnode.Ahours;
	     
	      }
	}
	public Attendence  Searchstudent(long se)
	{
		cnode=null;
		if(this.IsEmpty())
		{
			System.out.println("list is Empty");
		}
		else
		{
			cnode=head;
			while(cnode!=null)
			{
				if(se==cnode.id)
				{
					System.out.println("Student Found!");
					break;
				}
				cnode=cnode.next;
					
			}
			
		}
		return cnode;
	}
	
	public void UpdateAttendenceS(long se,int APhour)
	{
		cnode=this.Searchstudent(se);
		if(cnode!=null)
		{
		if(cnode.hours>=cnode.Phours+APhour&&cnode.Phours+APhour>=0)
		{
		this.displaySingleStudent(cnode);
		cnode.Phours+=APhour;
		cnode.Ahours-=APhour;
		System.out.println("Student Attendence Has Successfully Update!");
		this.displaySingleStudent(cnode);
		cnode.per=(double)cnode.Phours/cnode.hours*100;
		}
		else
			System.out.println("Cannot Update Attendence");

		}
	}
	
}
class Snode{
	public int index;
	public long Uid, Number;
	public String Name,Email,Branch,Addar;
	Snode next;
	
}
class Qnode{
	public int index;
	public long Uid;
	public long Number;
	public String Name,Email,Branch,Addar;
	Qnode next;
}

class ProfileUpdate {
	public int index;
	Snode Scnode,Snewnode,Stop,Sdelnode;
	Qnode Qcnode,Qnewnode,Qfront,Qdelnode,Qrear;
	public void ApplyUpdate(Student[] A,Scanner sc)
	{
		System.out.println("Enter Your Roll Number");
		long se=sc.nextLong();
		int index=SearchSort.Search(A,se);
		if(index==-1)
			System.out.println("Your Roll number Doesn't Matches Our records");
		else
		{
		  Qnewnode=new Qnode();
		  Qnewnode.index=index;
		  Qnewnode.Uid=se;
		  System.out.println(Qnewnode.Uid);
		  if(Qfront==null) 
		  {
			 Qfront=Qrear=Qnewnode;
			 Qnewnode.next=null;
		  }
		  else
		  {
			 
			  Qnewnode.next=null;
			  Qrear.next=Qnewnode;
			  Qrear=Qnewnode;
		  }
		  String rep;
		  do {
			  System.out.println("Enter the choice\n1 Change Name\n2 Change Email\n3 Change Branch\n4 Change Addar Number\n5 Change Phone Number");
			  int op=sc.nextInt();
			  sc.nextLine();
			  switch (op)
			  {
			  case 1:
				  System.out.println("Enter the Name to Update");
				  Qnewnode.Name=sc.nextLine();
				  break;
			  case 2:
				  System.out.println("Enter The Email to update");
				  Qnewnode.Email=sc.next();
				  break;
			  case 3:
				  System.out.println("Enter The Branch to update");
				  Qnewnode.Branch=sc.next();
				  break;
			  case 4:
				  System.out.println("Enter The Addar Number to update");
				  Qnewnode.Addar=sc.next();
			  case 5:
				  System.out.println("Enter The Phone Number to update");
				  Qnewnode.Number=sc.nextLong();
				  break;
			  }
			  
			  System.out.println("IF you Want To Change More Details Enter Yes");
			  rep=sc.next();
		  }while(rep.equalsIgnoreCase("yes"));
		  
		}
	}
	public void GrantUP(Student[] A,Scanner sc) {
		Qcnode=Qfront;
		
		while(Qcnode!=null)
		{
		Snewnode=new Snode();
		Snewnode.index=Qcnode.index;
		Snewnode.Uid=Qcnode.Uid;
		if(Stop==null) 
		{
			Stop=Snewnode;
			Snewnode.next=null;
		}
		else
		{
			Snewnode.next=Stop;
			Stop=Snewnode;
		}	
		System.out.println("Current Details");
		A[Qcnode.index].DispalySinglestudent();
		System.out.println("The Details Wanted To Be Updated");
		String x;

		if(Qcnode.Name!=null)
		{
			System.out.println(Qcnode.Name);
			System.out.println("Enter Y to Update");
			x=sc.next();
			if(x.equalsIgnoreCase("y"))
			{
				Snewnode.Name=A[Qcnode.index].Name;
				A[Qcnode.index].Name=Qcnode.Name;
			}
		}
		if(Qcnode.Branch!=null)
		{
			System.out.println(Qcnode.Branch);
			System.out.println("Enter Y to Update");
			x=sc.next();
			if(x.equalsIgnoreCase("y"))
			{
				Snewnode.Branch=A[Qcnode.index].Branch;
				A[Qcnode.index].Branch=Qcnode.Branch;
			}
		}
		if(Qcnode.Email!=null)
		{
			System.out.println(Qcnode.Email);
			System.out.println("Enter Y to Update");
			x=sc.next();
			if(x.equalsIgnoreCase("y"))
			{
				Snewnode.Email=A[Qcnode.index].Email;
				A[Qcnode.index].Email=Qcnode.Email;
			}
		}
		if(Qcnode.Number!=0)
		{
			System.out.println(Qcnode.Number);
			System.out.println("Enter Y to Update");
			x=sc.next();
			if(x.equalsIgnoreCase("y"))
			{
				Snewnode.Number=A[Qcnode.index].Pnumber;
				A[Qcnode.index].Pnumber=Qcnode.Number;
			}
		}
		if(Qcnode.Addar!=null)
		{
			System.out.println(Qcnode.Addar);
			System.out.println("Enter Y to Update");
			x=sc.next();
			if(x.equalsIgnoreCase("y"))
			{
				Snewnode.Addar=A[Qcnode.index].Addar;
				A[Qcnode.index].Addar=Qcnode.Addar;
			}
		}

		System.out.println("Enter Yes To Undo the Updates");	
		String Undo=sc.next();
		if(Undo.equalsIgnoreCase("yes"))
		{
			this.Undooperation(A);
		
		System.out.println("Enter Yes To Redo the Updates");	
		String Redo=sc.next();
		if(Redo.equalsIgnoreCase("yes"))
		{
			this.Undooperation(A);
		}
		}
		System.out.println("The Details are ");
		A[Snewnode.index].DispalySinglestudent();
		
		Qcnode=Qcnode.next;
		}
	}
	public void Undooperation(Student[] A)
	{
		Scnode=Stop;
		if(Scnode.Name!=null)
		{
			String name=A[Scnode.index].Name;
			A[Scnode.index].Name=Scnode.Name;
			Scnode.Name=name;
		}
		if(Scnode.Branch!=null)
		{
			String branch=A[Scnode.index].Branch;
			A[Scnode.index].Branch=Scnode.Branch;
			Scnode.Branch=branch;
		}
		if(Scnode.Email!=null)
		{
			String email=A[Scnode.index].Email;
			A[Scnode.index].Email=Scnode.Email;
			Scnode.Branch=email;
		}
		if(Scnode.Number!=0)
		{
			long number=A[Scnode.index].Pnumber;
			A[Scnode.index].Pnumber=Scnode.Number;
			Scnode.Number=number;
		}
		if(Scnode.Addar!=null)
		{
			String Addar=A[Scnode.index].Addar;
			A[Scnode.index].Addar=Scnode.Addar;
			Scnode.Addar=Addar;
		}
		
		
	}
}
class SearchSort
{
	public static int Search(Student [] A,long se)
	{
		for(int a=0;a<A.length;a++)
		{
			if(A[a].Uid==(se))
			{
				return a;
			}	
		}
		return -1;
	}
	public static void d2Sort(double [][] A) {
	    for (int i = 0; i < A.length - 1; i++) {
	        for (int j = 0; j < A.length - i - 1; j++) {
	            if (A[j][1] < A[j+1][1]) {
	                
	                for (int k = 0; k < 2; k++) {
	                    double temp = A[j][k];
	                    A[j][k] = A[j+1][k];
	                    A[j+1][k] = temp;
	                }
	            }
	        }
	    }
	}
}
public class College_management {

    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       File CR=new File();
       String[] s1 = CR.getArray("C://Sample//S.txt");
       String[] f1 =CR.getArray("C://Sample//F.txt");
//      for(String a:f1)
//    	  System.out.print(a);
        String rep;
        long id;
        int hour;
        int Ssize=s1.length/6;
        Student[] A = new Student[Ssize];
        int index = 0;
        for (int a = 0; a < A.length; a++) 
        {
            A[a] = new Student(s1[index],s1[index + 1],s1[index + 2],s1[index+3],s1[index+4],s1[index+5]);
            index += 6;
         }
        int Fsize=f1.length/4;
        Faculty[] B=new Faculty[Fsize];
        index=0;
        for (int a = 0; a < B.length; a++) 
        {
            B[a] = new Faculty(f1[index],f1[index + 1],f1[index + 2],f1[index + 3]);
            index += 4;
         }

        Alist obj=new Alist(A);
        ProfileUpdate PF=null;
        do {
        System.out.println("Enter your choice\n1 Student list\n2 Post Student Attendence\n"
        		+ "3 Enter Marks Of Students \n4 Add a Student Attendence\n5 Print the Student Attendence\n6 Faculty list\n7 Profile Update Application\n8 Profile Verifecation"
        		+ "\n9 Details of All students\n10 Sorted Marks list\n11 Sorted Attendence List");
        int choice=sc.nextInt();
        switch(choice)
        {
        case 1:
        	System.out.println("student list is  as follows ");
        	for(int a=0;a<A.length;a++)
        	{
        		A[a].display();
        	}
        	break;
        case 2:
        	 obj.AddAttendence(sc);
        	 break;
        case 3:
        	 System.out.println("Enter The Marks Of The Student Of Two Subjects");
        	 for(int a=0;a<A.length;a++)
        	 {
        		 System.out.println(A[a].Uid+"\t"+A[a].Name);
        		 System.out.print("Subject M1:");
        		 A[a].M1=sc.nextDouble();
        		 System.out.print("Subject M2:");
        		 A[a].M2=sc.nextDouble();
        		 A[a].CalculateGrade();
        	 }
        	 break;
        case 4:
        	System.out.println("Enter the Student College ID ");
        	id=sc.nextLong();
        	System.out.println("Enter The Number Of Hours To Be Added");
        	hour=sc.nextInt();
        	obj.UpdateAttendenceS(id, hour);
        	break;
        case 5:
        	System.out.println("Roll No\t Total Hours\tPresent Hour\tAbsent Hour\t Attendence Percentage");
        	obj.display();
        	break;
       case 6:
       	System.out.println("Faculy List Is As Follows");
       	for(int a=0;a<B.length;a++)
       	{
       		B[a].display();
       	}
       	break;
       case 7:
    	   PF=new ProfileUpdate();
    	   PF.ApplyUpdate(A, sc);
    	   break;
       case 8:
    	   if(PF!=null)
    	   {
    		  PF.GrantUP(A, sc);
    	   }
    	   else
    	   {
    		   System.out.println("There are no Application For Profile Available");
    	   }
    	   break;	
       case 9:
    	   System.out.println("Enter The Location to Enter the Student Details");
    	   CR.sendArray(A, sc.next(),obj.head);
    	   break;
       case 10:
    	   double Marks[][] =new double[Ssize][2];
    	   if(A[1].Grade!=0)
    	   {
    	   for(int a=0;a<Marks.length;a++)
    	   {
    		   
    		   Marks[a][0]=a;
    		   Marks[a][1]=A[a].Grade;
    		   SearchSort.d2Sort(Marks);
    		   
    		}
    	   for(int a=0;a<Marks.length;a++)
    	   {
    		   A[(int)Marks[a][0]].DisplayUNB();
    		   System.out.println(Marks[a][1]);
    	   }
    	   }
    	   else
    	   {
    		   System.out.println("Marks have not entered");
    	   }
    	   break;
       case 11:
    	   double AP[][] =new double[Ssize][2];
    	   if(obj.head.hours!=0)
		   {
    		   obj.cnode=obj.head;
    		   for(int a=0;a<AP.length-1;a++)
    		   {
    			 
    			   AP[a][0]=a;
    			   AP[a][1]=obj.cnode.per;
    			   obj.cnode=obj.cnode.next;
    		   } 
    		   SearchSort.d2Sort(AP);
    		   System.out.println("The list of The Student in the sorted order is ");
    		   for(int a=0;a<AP.length-1;a++)
    		   {	
    			   A[(int)AP[a][0]].DisplayUNB();
    			   System.out.println(AP[a][1]);
    		   }
    	   }
    	   else
		   {
			   System.out.println("No Attendence posted yet");
		   }

        default :
        	System.out.println("Enter The Correct Choice");
        	
        }
        System.out.println("Enter 'yes' to repeate the above operation");
        rep=sc.next();
        }while(rep.equalsIgnoreCase("yes"));
    }
}
